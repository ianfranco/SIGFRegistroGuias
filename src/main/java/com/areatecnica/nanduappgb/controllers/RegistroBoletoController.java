/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.controllers;

import com.areatecnica.nanduappgb.behaviors.DeleteGuiaAction;
import com.areatecnica.nanduappgb.behaviors.DeleteVueltaGuiaAction;
import com.areatecnica.nanduappgb.behaviors.FindBusFocusLost;
import com.areatecnica.nanduappgb.behaviors.FindConductorFocusLost;
import com.areatecnica.nanduappgb.behaviors.FindFolioGuiaEnterPressed;
import com.areatecnica.nanduappgb.behaviors.SaveGuiaAction;
import com.areatecnica.nanduappgb.behaviors.VoucherRegistroVueltaPrintAction;
import com.areatecnica.nanduappgb.dao.impl.ProcesoGeneralNandu;
import com.areatecnica.nanduappgb.dao.impl.TarifaGrupoServicioSolyMar;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import com.areatecnica.nanduappgb.entities.Servicio;
import com.areatecnica.nanduappgb.entities.TarifaGrupoServicio;
import com.areatecnica.nanduappgb.entities.VueltaGuia;
import com.areatecnica.nanduappgb.models.BoletoTableModel;
import com.areatecnica.nanduappgb.models.ServicioComboBoxModel;
import com.areatecnica.nanduappgb.models.VueltaGuiaComboBoxModel;
import com.areatecnica.nanduappgb.utils.NextObject;
import com.areatecnica.nanduappgb.utils.TextSelectionFocusAdapter;
import com.areatecnica.nanduappgb.views.MainView;
import com.areatecnica.nanduappgb.views.PanelGuia;
import com.areatecnica.nanduappgb.views.RegistroBoletoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author ianfr
 */
public class RegistroBoletoController extends MainView {

    private RegistroBoletoView view;
    private Guia selected;
    private BoletoTableModel model;
    private TarifaGrupoServicioSolyMar tarifaSolyMar;
    private ProcesoGeneralNandu proceso;
    private Servicio servicio;
    private VueltaGuia vueltaGuia;
    //private List<VueltaGuia> vueltasItems;
    private ServicioComboBoxModel servicioModel;
    private VueltaGuiaComboBoxModel vueltaGuiaComboBoxModel;
    private Map<Integer, RegistroBoleto> map;
    private Map<String, Servicio> mapServicios;
    private Boolean flag;

    public RegistroBoletoController(RegistroBoletoView view) {
        this.view = view;
        init();
    }

    private void init() {
        this.proceso = new ProcesoGeneralNandu();
        this.tarifaSolyMar = new TarifaGrupoServicioSolyMar();
        this.servicioModel = new ServicioComboBoxModel();
        this.servicio = this.servicioModel.getElementAt(0);
        this.selected = new Guia();
        this.selected.setGuiaFecha(new Date());
        this.view.getServicioComboBox().setModel(servicioModel);
        this.flag = false;

        this.mapServicios = new HashMap<String, Servicio>();

        for (Servicio s : this.tarifaSolyMar.getGrupo().getGrupoServicio().getServicioList()) {
            this.mapServicios.put(String.valueOf(s.getServicioNumeroServicio()), s);
        }

        this.map = new HashMap<Integer, RegistroBoleto>();
        //getTarifas();

        this.view.getFolioTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                findFolio(e);
            }
        });
        this.view.getFolioTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getFolioTextField()));

        this.view.getTurnoTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getTurnoTextField()));
        this.view.getTurnoTextField().addKeyListener(new NextObject(this.view.getConductorTextField(), this.view.getServicioComboBox(), null, this.view.getVueltaComboBox(), true));
        this.view.getTurnoTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                setTurno();
            }
        });

        this.view.getBusTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                findBus();
            }
        });
        this.view.getBusTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getBusTextField()));
        this.view.getBusTextField().addKeyListener(new NextObject(this.view.getFechaGuiaTextField(), this.view.getConductorTextField(), null, null));

        this.view.getFechaGuiaTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                view.getFechaGuiaTextField().setSelectionStart(0);
                view.getFechaGuiaTextField().setSelectionEnd(1);
            }
        });
        this.view.getFechaGuiaTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                selected.setGuiaFecha(view.getDate());
                System.err.println("FECHA DE LA GUIA: " + selected.getGuiaFecha());
            }
        });
        this.view.getFechaGuiaTextField().addKeyListener(new NextObject(this.view.getFolioTextField(), this.view.getBusTextField(), null, null));

        this.view.getConductorTextField().addKeyListener(new NextObject(this.view.getBusTextField(), this.view.getServicioComboBox(), null, null));
        this.view.getConductorTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getConductorTextField()));
        this.view.getConductorTextField().addFocusListener(new FindConductorFocusLost(this));

        this.view.getVueltaComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTableModel();
            }
        });

        this.view.getServicioComboBox().addKeyListener(new NextObject(this.view.getVueltaComboBox(), this.view.getTable(), this.view.getTurnoTextField(), null, Boolean.TRUE));
        this.view.getServicioComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeServicio();
            }
        });

        this.view.getTable().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && view.getTable().getSelectedRow() == (view.getTable().getRowCount() - 1) && ((view.getTable().getSelectedColumn() == 4) || (view.getTable().getSelectedColumn() == 5))) {
                    save();
                }
            }

        });
        this.view.getTable().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (flag) {
                    view.getTable().changeSelection(0, 3, true, true);
                } else {
                    view.getTable().changeSelection(0, 5, true, true);
                }
            }
        });

        this.view.getDeleteGuiaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteGuiaAction action = new DeleteGuiaAction(selected);
                if (action.save()) {
                    reset();
                };
            }
        });

        this.view.getUltimaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteVuelta();
            }
        });

        this.view.getUltimaButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteVuelta();
            }
        }
        );

        this.view.getPrintButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.err.println("CLICKEO");
                printGuia();
            }
        });

        this.view.getFolioTextField().requestFocus();
    }

    public List<RegistroBoleto> getTarifas() {

        List<RegistroBoleto> items = new ArrayList<>();

        for (TarifaGrupoServicio t : this.tarifaSolyMar.getTarifaGrupoServicio()) {
            RegistroBoleto r = new RegistroBoleto();
            r.setRegistroBoletoIdBoleto(t.getTarifaGrupoServicioIdBoleto());

            r.setRegistroBoletoIdVueltaGuia(this.vueltaGuia);
            r.setRegistroBoletoEsNuevo(Boolean.TRUE);
            r.setRegistroBoletoValor(t.getTarifaGrupoServicioValor());
            r.setRegistroBoletoObservacion("Nuevo Boleto");

            map.put(r.getRegistroBoletoIdBoleto().getBoletoOrden(), r);

            items.add(r);
        }

        return items;
    }

    private void findFolio(KeyEvent e) {
        FindFolioGuiaEnterPressed enter = new FindFolioGuiaEnterPressed(this);
        enter.press(e);;
    }

    private void findBus() {
        FindBusFocusLost fb = new FindBusFocusLost(RegistroBoletoController.this);
        fb.find();
    }

    private void deleteVuelta() {
        System.err.println("QUIERE ELIMINAR VUELTA");
        DeleteVueltaGuiaAction action = new DeleteVueltaGuiaAction(this.vueltaGuia);
        if (action.save()) {
            reset();
            action = null;
        }
    }

    private void printGuia() {
        VoucherRegistroVueltaPrintAction v = new VoucherRegistroVueltaPrintAction(this);
        v.print();
    }

    private void setTurno() {
        try {
            String _turno = this.view.getTurnoTextField().getText();
            int turno = Integer.parseInt(_turno);

            this.selected.setGuiaTurno(turno);

        } catch (NumberFormatException e) {
            this.selected.setGuiaTurno(1);
            System.err.println("Error al transformar el numero");
        }
    }

    private void save() {
        if (this.view.getServicioComboBox().getSelectedIndex() > -1) {
            PanelGuia panel = new PanelGuia();
            panel.getVueltaLabel().setText("");

            panel.getLabel().setText("¿Ingresar vuelta/boletos a la Guía N° Folio: " + this.selected.getGuiaFolio() + "?");
            int option = JOptionPane.showConfirmDialog(null, panel, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {
                SaveGuiaAction saveGuia = new SaveGuiaAction(this);
                saveGuia.save();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar el Servicio", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void changeTableModel() {
        int index = this.view.getVueltaComboBox().getSelectedIndex();
        if (index > -1) {
            this.vueltaGuia = this.vueltaGuiaComboBoxModel.getSelectedItem();
            this.model = new BoletoTableModel(this.vueltaGuia.getRegistroBoletoList(), false);

            System.err.println("total boletos despues de cambiar:" + this.vueltaGuia.getRegistroBoletoList().size());

            this.view.getTable().setModel(model);
        }
    }

    private void changeServicio() {
        int index = this.view.getServicioComboBox().getSelectedIndex();
        if (index > -1) {
            this.servicio = this.servicioModel.getElementAt(index);
            if (this.vueltaGuia != null) {
                this.vueltaGuia.setVueltaGuiaIdServicio(servicio);
            } else {
                this.vueltaGuia = new VueltaGuia();
                this.vueltaGuia.setVueltaGuiaIdServicio(servicio);
                this.vueltaGuia.setVueltaGuiaIdGuia(this.selected);
            }

        }
    }

    public void reset() {
        Date auxDate = this.selected.getGuiaFecha();

        this.selected = new Guia();
        this.selected.setGuiaFecha(auxDate);
        this.selected.setGuiaTurno(1);
        this.model = new BoletoTableModel();
        this.vueltaGuiaComboBoxModel = new VueltaGuiaComboBoxModel(new ArrayList<>());
        this.view.getVueltaComboBox().setModel(vueltaGuiaComboBoxModel);
        //this.vueltasItems = new ArrayList();
        this.vueltaGuia = null;
        this.view.getTurnoTextField().setText("");
        this.view.getTable().setModel(model);
        this.view.getFolioTextField().setText("");
        this.view.getBusTextField().setText("");
        this.view.getConductorTextField().setText("");
        this.view.getPpuTextField().setText(" ");
        this.view.getFlotaTextField().setText(" ");
        this.view.getNombreConductorTextField().setText(" ");
        this.view.getObservacionTextField().setText(" ");
        this.view.getEstadoBoletoTextField().setText(" ");
        this.view.getTotalIngresosLabel().setText("---");
        this.view.getFolioTextField().requestFocus();
    }

    public VueltaGuiaComboBoxModel getVueltaGuiaComboBoxModel() {
        return vueltaGuiaComboBoxModel;
    }

    public BoletoTableModel getModel() {
        return model;
    }

    public VueltaGuia getVueltaGuia() {

        return vueltaGuia;
    }

    public void setVueltaGuia(VueltaGuia vueltaGuia) {
        this.vueltaGuia = vueltaGuia;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setVueltaGuiaComboBoxModel(VueltaGuiaComboBoxModel vueltaGuiaComboBoxModel) {
        this.vueltaGuiaComboBoxModel = vueltaGuiaComboBoxModel;
        this.view.getVueltaComboBox().setModel(this.vueltaGuiaComboBoxModel);
        if (!this.vueltaGuiaComboBoxModel.getItems().isEmpty()) {
            this.view.getVueltaComboBox().setSelectedIndex(vueltaGuiaComboBoxModel.getSize() - 1);
        }
    }

    public void setModel(BoletoTableModel model) {
        this.model = model;
        this.view.getTable().setModel(model);
    }

//    public List<VueltaGuia> getVueltasItems() {
//        return vueltasItems;
//    }
//
//    public void setVueltasItems(List<VueltaGuia> vueltasItems) {
//        this.vueltasItems = vueltasItems;
//        setVueltaGuia(getVueltasItems().get(getVueltasItems().size() - 1));
//    }
    public Guia getGuia() {
        return selected;
    }

    public void setGuia(Guia guia) {
        this.selected = guia;
    }

    public RegistroBoletoView getView() {
        return view;
    }

    public ProcesoGeneralNandu getProceso() {
        return proceso;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }
}
