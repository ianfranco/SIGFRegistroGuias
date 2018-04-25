/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.controllers;

import com.areatecnica.nanduappgb.behaviors.FindBusFocusLost;
import com.areatecnica.nanduappgb.behaviors.FindConductorFocusLost;
import com.areatecnica.nanduappgb.behaviors.FindFolioBoletoEnterPressed;
import com.areatecnica.nanduappgb.behaviors.SaveGuiaAction;
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
import java.util.ArrayList;
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
    private List<VueltaGuia> vueltasItems;
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
        this.view.getServicioComboBox().setModel(servicioModel);
        this.flag = false;

        this.mapServicios = new HashMap<String, Servicio>();

        for (Servicio s : this.tarifaSolyMar.getGrupo().getGrupoServicio().getServicioList()) {
            this.mapServicios.put(String.valueOf(s.getServicioNumeroServicio()), s);
        }

        this.map = new HashMap<Integer, RegistroBoleto>();
        //getTarifas();

        this.view.getFolioTextField().addKeyListener(new FindFolioBoletoEnterPressed(this));
        this.view.getFolioTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getFolioTextField()));
        this.view.getFolioTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getFolioTextField()));

        this.view.getBusTextField().addFocusListener(new FocusAdapter() {
            FindBusFocusLost fb = new FindBusFocusLost(RegistroBoletoController.this);
        });
        this.view.getBusTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getBusTextField()));
        this.view.getBusTextField().addKeyListener(new NextObject(this.view.getFolioTextField(), this.view.getConductorTextField(), null, null));

        this.view.getConductorTextField().addKeyListener(new NextObject(this.view.getBusTextField(), this.view.getServicioComboBox(), null, null));
        this.view.getConductorTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getConductorTextField()));
        this.view.getConductorTextField().addFocusListener(new FindConductorFocusLost(this));

        this.view.getVueltaComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!flag) {
                    //changeTableModel();
                }
            }
        });

        this.view.getServicioComboBox().addKeyListener(new NextObject(this.view.getVueltaComboBox(), this.view.getTable(), null, null, Boolean.TRUE));
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

        this.view.getUltimaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeUltimaVuelta();
            }
        });

        this.view.getFolioTextField().requestFocus();
    }

    public List<RegistroBoleto> getTarifas() {

        List<RegistroBoleto> items = new ArrayList<>();

        for (TarifaGrupoServicio t : this.tarifaSolyMar.getTarifaGrupoServicio()) {
            RegistroBoleto r = new RegistroBoleto();
            r.setRegistroBoletoIdBoleto(t.getTarifaGrupoServicioIdBoleto());
            System.err.println("VALOR DE LA VUELTA:" + this.vueltaGuia.getVueltaGuiaIdGuia().getGuiaFolio());
            r.setRegistroBoletoIdVueltaGuia(this.vueltaGuia);
//            r.setRegistroBoletoIdServicio(this.servicio);
            r.setRegistroBoletoEsNuevo(Boolean.TRUE);
            r.setRegistroBoletoValor(t.getTarifaGrupoServicioValor());
            r.setRegistroBoletoObservacion("Nuevo Boleto");

            map.put(r.getRegistroBoletoIdBoleto().getBoletoOrden(), r);

            items.add(r);
        }

        return items;
    }

    private void save() {
        PanelGuia panel = new PanelGuia();
        panel.getVueltaLabel().setText("¿Ingresar vuelta/boletos a la Guía N° Folio: " + this.selected.getGuiaFolio() + "?");
        int option = JOptionPane.showConfirmDialog(null, panel, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            SaveGuiaAction saveGuia = new SaveGuiaAction(this);
            saveGuia.save();
        }
    }

    private void removeUltimaVuelta() {
        if (this.model.getRowCount() > 1) {
            int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el ultimo registro de boletos/vuelta?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {
            }
        }
    }

    private void changeTableModel() {
        int index = this.view.getVueltaComboBox().getSelectedIndex();
        if (index > -1 && !flag) {
            this.vueltaGuia = this.vueltaGuiaComboBoxModel.getElementAt(index);

            this.model = new BoletoTableModel(this.vueltaGuia.getRegistroBoletoList(), false);
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
        this.selected = new Guia();
        this.model = new BoletoTableModel();
        this.view.getTable().setModel(model);
        this.view.getFolioTextField().setText("");
        this.view.getBusTextField().setText("");
        this.view.getConductorTextField().setText("");
        this.view.getPpuTextField().setText(" ");
        this.view.getFlotaTextField().setText(" ");
        this.view.getNombreConductorTextField().setText(" ");
        this.view.getObservacionTextField().setText(" ");
        this.view.getEstadoBoletoTextField().setText(" ");

        this.view.getFolioTextField().requestFocus();
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
        this.view.getVueltaComboBox().setSelectedIndex(vueltaGuiaComboBoxModel.getSize() - 1);
    }

    public void setModel(BoletoTableModel model) {
        this.model = model;
        this.view.getTable().setModel(model);
    }

    public List<VueltaGuia> getVueltasItems() {
        return vueltasItems;
    }

    public void setVueltasItems(List<VueltaGuia> vueltasItems) {
        this.vueltasItems = vueltasItems;
    }

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
