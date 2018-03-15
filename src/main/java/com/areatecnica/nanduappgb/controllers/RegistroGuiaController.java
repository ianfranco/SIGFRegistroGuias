/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.controllers;

import com.areatecnica.nanduappgb.behaviors.FindBusSolyMarFocusLost;
import com.areatecnica.nanduappgb.behaviors.FindConductorFocusLost;
import com.areatecnica.nanduappgb.behaviors.FindFolioFocusLost;
import com.areatecnica.nanduappgb.behaviors.RegistroGuiaSaveAction;
import com.areatecnica.nanduappgb.dao.impl.ProcesoGeneralNandu;
import com.areatecnica.nanduappgb.dao.impl.TarifaGrupoServicioSolyMar;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import com.areatecnica.nanduappgb.entities.Servicio;
import com.areatecnica.nanduappgb.entities.TarifaGrupoServicio;
import com.areatecnica.nanduappgb.models.EstructuraRegistroBoletoÑandu;
import com.areatecnica.nanduappgb.models.RegistroBoletoTableModel;
import com.areatecnica.nanduappgb.models.RegistroGuiaTableModel;
import com.areatecnica.nanduappgb.models.ServicioComboBoxModel;
import com.areatecnica.nanduappgb.utils.NextObject;
import com.areatecnica.nanduappgb.utils.NumberLimiter;
import com.areatecnica.nanduappgb.utils.TextSelectionFocusAdapter;
import com.areatecnica.nanduappgb.views.RegistroGuiaView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroGuiaController extends RegistroController {

    private RegistroGuiaView view;
    private ProcesoGeneralNandu proceso;
    private Guia guia;
    private Servicio servicio;
    private ServicioComboBoxModel servicioModel;
    private Map<Integer, RegistroBoleto> map;
    /*Optimización*/
    private Map<String, Servicio> mapServicios;
    private RegistroGuiaTableModel model;
    private TarifaGrupoServicioSolyMar tarifaSolyMar;
    private Boolean flag;

    public RegistroGuiaController() {
    }

    public RegistroGuiaController(RegistroGuiaView view) {
        this.view = view;
        init();
    }

    private void init() {
        this.guia = new Guia();
        this.guia.setGuiaFecha(new Date());
        this.guia.setGuiaTotalIngreso(0);

        this.proceso = new ProcesoGeneralNandu();
        this.tarifaSolyMar = new TarifaGrupoServicioSolyMar();
        this.model = new RegistroGuiaTableModel();
        this.servicioModel = new ServicioComboBoxModel();
        //Rasca 
        this.servicio = this.servicioModel.getElementAt(0);
        this.view.getServicioTextField().setModel(servicioModel);

        this.mapServicios = new HashMap<String, Servicio>();

        for (Servicio s : this.tarifaSolyMar.getGrupo().getGrupoServicio().getServicioList()) {
            this.mapServicios.put(String.valueOf(s.getServicioNumeroServicio()), s);
        }

        this.map = new HashMap<Integer, RegistroBoleto>();
        getTarifas();

        this.view.getFolioTextField().addFocusListener(new FindFolioFocusLost(this));
        this.view.getFolioTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getFolioTextField()));
        this.view.getFolioTextField().addKeyListener(new NextObject(null, this.view.getBusTextField(), null, null));

        this.view.getBusTextField().addFocusListener(new FindBusSolyMarFocusLost(this));
        this.view.getBusTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getBusTextField()));
        this.view.getBusTextField().addKeyListener(new NextObject(this.view.getFolioTextField(), this.view.getConductorTextField(), null, null));

        this.view.getConductorTextField().addKeyListener(new NextObject(this.view.getBusTextField(), this.view.getServicioTextField(), null, null));
        this.view.getConductorTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getConductorTextField()));
        this.view.getConductorTextField().addFocusListener(new FindConductorFocusLost(this));

        this.view.getServicioTextField().addKeyListener(new NextObject(this.view.getConductorTextField(), this.view.getDirectoTextField(), this.view.getDirectoTextField(), this.view.getConductorTextField(), true));

        this.view.getDirectoTextField().addKeyListener(new NextObject(this.view.getServicioTextField(), this.view.getPlanVinaTextField(), this.view.getPlanVinaTextField(), this.view.getServicioTextField()));
        this.view.getDirectoTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getDirectoTextField()));
        this.view.getDirectoTextField().setDocument(new NumberLimiter());
        this.view.getDirectoTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String _value = view.getDirectoTextField().getText();
                    int inicio = Integer.parseInt(_value);

                    if (!flag) {
                        if (inicio < model.getUltimoRegistro().getDirecto().getRegistroBoletoInicio()) {
                            view.getDirectoTextField().setText(String.valueOf(model.getUltimoRegistro().getDirecto().getRegistroBoletoInicio()));
                        }
                    }
                    view.getDirectoTextField().setBackground(Color.white);
                } catch (NumberFormatException ex) {
                    view.getDirectoTextField().setBackground(Color.red);
                }
            }
        });

        this.view.getPlanVinaTextField().addKeyListener(new NextObject(this.view.getDirectoTextField(), this.view.getLocalTextField(), this.view.getLocalTextField(), this.view.getDirectoTextField()));
        this.view.getPlanVinaTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getPlanVinaTextField()));
        this.view.getPlanVinaTextField().setDocument(new NumberLimiter());
        this.view.getPlanVinaTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String _value = view.getPlanVinaTextField().getText();
                    int inicio = Integer.parseInt(_value);

                    if (!flag) {
                        if (inicio < model.getUltimoRegistro().getPlanVina().getRegistroBoletoInicio()) {
                            view.getPlanVinaTextField().setText(String.valueOf(model.getUltimoRegistro().getPlanVina().getRegistroBoletoInicio()));
                        }
                    }
                    view.getPlanVinaTextField().setBackground(Color.white);
                } catch (NumberFormatException ex) {
                    view.getPlanVinaTextField().setBackground(Color.red);
                }
            }
        });

        this.view.getLocalTextField().addKeyListener(new NextObject(this.view.getPlanVinaTextField(), this.view.getEscolarDirectoTextField(), this.view.getEscolarDirectoTextField(), this.view.getPlanVinaTextField()));
        this.view.getLocalTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getLocalTextField()));
        this.view.getLocalTextField().setDocument(new NumberLimiter());
        this.view.getLocalTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String _value = view.getLocalTextField().getText();
                    int inicio = Integer.parseInt(_value);

                    if (!flag) {
                        if (inicio < model.getUltimoRegistro().getLocal().getRegistroBoletoInicio()) {
                            view.getLocalTextField().setText(String.valueOf(model.getUltimoRegistro().getLocal().getRegistroBoletoInicio()));
                        }
                    }
                    view.getLocalTextField().setBackground(Color.white);
                } catch (NumberFormatException ex) {
                    view.getLocalTextField().setBackground(Color.red);
                }
            }
        });

        this.view.getEscolarDirectoTextField().addKeyListener(new NextObject(this.view.getLocalTextField(), this.view.getEscolarLocalTextField(), this.view.getEscolarLocalTextField(), this.view.getLocalTextField()));
        this.view.getEscolarDirectoTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getEscolarDirectoTextField()));
        this.view.getEscolarDirectoTextField().setDocument(new NumberLimiter());
        this.view.getEscolarDirectoTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String _value = view.getEscolarDirectoTextField().getText();
                    int inicio = Integer.parseInt(_value);

                    if (!flag) {
                        if (inicio < model.getUltimoRegistro().getEscolarDirecto().getRegistroBoletoInicio()) {
                            view.getEscolarDirectoTextField().setText(String.valueOf(model.getUltimoRegistro().getEscolarDirecto().getRegistroBoletoInicio()));
                        }
                    }
                    view.getEscolarDirectoTextField().setBackground(Color.white);
                } catch (NumberFormatException ex) {
                    view.getEscolarDirectoTextField().setBackground(Color.red);
                }
            }
        });

        this.view.getEscolarLocalTextField().addKeyListener(new NextObject(this.view.getEscolarDirectoTextField(), this.view.getAddButton(), this.view.getAddButton(), this.view.getEscolarDirectoTextField()));
        this.view.getEscolarLocalTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getEscolarLocalTextField()));
        this.view.getEscolarLocalTextField().setDocument(new NumberLimiter());
        this.view.getEscolarLocalTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String _value = view.getEscolarLocalTextField().getText();
                    int inicio = Integer.parseInt(_value);

                    if (!flag) {
                        if (inicio < model.getUltimoRegistro().getEscolarLocal().getRegistroBoletoInicio()) {
                            view.getEscolarLocalTextField().setText(String.valueOf(model.getUltimoRegistro().getEscolarLocal().getRegistroBoletoInicio()));
                        }
                    }
                    view.getEscolarLocalTextField().setBackground(Color.white);
                } catch (NumberFormatException ex) {
                    view.getEscolarLocalTextField().setBackground(Color.red);
                }
            }
        });

        this.view.getAddButton().addKeyListener(new NextObject(this.view.getEscolarLocalTextField(), null, null, this.view.getEscolarLocalTextField()));

        this.view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (guia != null) {
                    if (flag) {
                        System.err.println("ESTO");
                        setUpBoletos();
                    } else {
                        System.err.println("LO OTRO");
                        addRow();
                    }
                }
            }
        });

        this.view.getSaveButton().addActionListener(new RegistroGuiaSaveAction(this));

        this.view.getFolioTextField().requestFocus();
    }

    public RegistroGuiaView getView() {
        return view;
    }

    public Guia getGuia() {
        return guia;
    }

    public void setGuia(Guia guia) {
        this.guia = guia;
    }

    public ProcesoGeneralNandu getProceso() {
        return proceso;
    }

    public RegistroGuiaTableModel getModel() {
        return model;
    }

    public void setModel(RegistroGuiaTableModel model) {
        getView().getTable().setModel(model);
        this.model = model;

        if (this.model.getRowCount() > 1) {
            if (this.guia.getRegistroBoletoList() == null) {
                List<RegistroBoleto> list = new ArrayList<>();
                list.addAll(this.model.getPrimerRegistro().getRegistro());
                this.guia.setRegistroBoletoList(list);
            }
            for (RegistroBoleto r : this.model.getUltimoRegistro().getRegistro()) {
                switch (r.getRegistroBoletoIdBoleto().getBoletoOrden()) {
                    case 1:
                        this.view.getDirectoTextField().setText(String.valueOf(r.getRegistroBoletoInicio()));
                        break;
                    case 2:
                        this.view.getPlanVinaTextField().setText(String.valueOf(r.getRegistroBoletoInicio()));
                        break;
                    case 3:
                        this.view.getLocalTextField().setText(String.valueOf(r.getRegistroBoletoInicio()));
                        break;
                    case 4:
                        this.view.getEscolarDirectoTextField().setText(String.valueOf(r.getRegistroBoletoInicio()));
                        break;
                    case 5:
                        this.view.getEscolarLocalTextField().setText(String.valueOf(r.getRegistroBoletoInicio()));
                        break;
                }
            }
        }

    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getFlag() {
        return flag;
    }

    public List<RegistroBoleto> getTarifas() {

        List<RegistroBoleto> items = new ArrayList<>();

        for (TarifaGrupoServicio t : this.tarifaSolyMar.getTarifaGrupoServicio()) {
            RegistroBoleto r = new RegistroBoleto();
            r.setRegistroBoletoIdBoleto(t.getTarifaGrupoServicioIdBoleto());
            r.setRegistroBoletoIdGuia(this.guia);
            r.setRegistroBoletoIdServicio(this.servicio);
            r.setRegistroBoletoEsNuevo(Boolean.TRUE);
            r.setRegistroBoletoFechaIngreso(new Date());
            r.setRegistroBoletoValor(t.getTarifaGrupoServicioValor());
            r.setRegistroBoletoObservacion("Nuevo Boleto");

            map.put(r.getRegistroBoletoIdBoleto().getBoletoOrden(), r);

            items.add(r);
        }

        return items;
    }

    public void setUpBoletos() {
        EstructuraRegistroBoletoÑandu serie = new EstructuraRegistroBoletoÑandu();

        int _auxServicio = this.view.getServicioTextField().getSelectedIndex();

        if (_auxServicio > -1) {
            this.servicio = this.servicioModel.getElementAt(_auxServicio);
            try {

                String _directo = (this.view.getDirectoTextField().getText());
                String _planVina = (this.view.getPlanVinaTextField().getText());
                String _local = (this.view.getLocalTextField().getText());
                String _escolarDirecto = (this.view.getEscolarDirectoTextField().getText());
                String _escolarLocal = (this.view.getEscolarLocalTextField().getText());

                serie.setNumero(1111);
                serie.setServicio(this.servicio);

                map.forEach((k, v) -> {
                    v.setRegistroBoletoIdGuia(this.guia);
                    switch (k) {
                        case 1:
                            v.setRegistroBoletoSerie(Integer.parseInt(_directo) / 1000);
                            v.setRegistroBoletoInicio(Integer.parseInt(_directo) % 1000);
                            break;
                        case 2:
                            v.setRegistroBoletoSerie(Integer.parseInt(_planVina) / 1000);
                            v.setRegistroBoletoInicio(Integer.parseInt(_planVina) % 1000);
                            break;
                        case 3:
                            v.setRegistroBoletoSerie(Integer.parseInt(_local) / 1000);
                            v.setRegistroBoletoInicio(Integer.parseInt(_local) % 1000);
                            break;
                        case 4:
                            v.setRegistroBoletoSerie(Integer.parseInt(_escolarDirecto) / 1000);
                            v.setRegistroBoletoInicio(Integer.parseInt(_escolarDirecto) % 1000);
                            break;
                        case 5:
                            v.setRegistroBoletoSerie(Integer.parseInt(_escolarLocal) / 1000);
                            v.setRegistroBoletoInicio(Integer.parseInt(_escolarLocal) % 1000);
                            break;
                    }
                    serie.addRegistroBoleto(v);
                });

                this.guia.setRegistroBoletoList(serie.getRegistro());
                this.model.addRow(serie);//Serie
                this.model.addRow(serie);//Inicio
                this.clearTextField();

                int option = JOptionPane.showConfirmDialog(null, "¿Registrar Vuelta?", "Confirmación", JOptionPane.YES_NO_OPTION);

                if (option == JOptionPane.YES_OPTION) {
                    RegistroGuiaSaveAction action = new RegistroGuiaSaveAction(this);
                    action.save();
                    reset();
                }

            } catch (NumberFormatException numberFormatException) {

            }

        }

    }

    private void addRow() {
        EstructuraRegistroBoletoÑandu serie = new EstructuraRegistroBoletoÑandu();

        int _auxServicio = this.view.getServicioTextField().getSelectedIndex();

        if (_auxServicio > -1) {
            this.servicio = this.servicioModel.getElementAt(_auxServicio);

            serie.setServicio(servicio);

            String _directo = (this.view.getDirectoTextField().getText());
            String _planVina = (this.view.getPlanVinaTextField().getText());
            String _local = (this.view.getLocalTextField().getText());
            String _escolarDirecto = (this.view.getEscolarDirectoTextField().getText());
            String _escolarLocal = (this.view.getEscolarLocalTextField().getText());

            RegistroBoleto nuevoRegistro = null;
            
            for (RegistroBoleto r : this.model.getUltimoRegistro().getRegistro()) {
                nuevoRegistro = new RegistroBoleto();
                nuevoRegistro.setRegistroBoletoIdBoleto(r.getRegistroBoletoIdBoleto());
                nuevoRegistro.setRegistroBoletoIdGuia(this.guia);
                nuevoRegistro.setRegistroBoletoIdServicio(this.servicio);
                nuevoRegistro.setRegistroBoletoSerie(r.getRegistroBoletoSerie());
                nuevoRegistro.setRegistroBoletoNumeroVuelta(this.model.getNumeroVuelta());
                nuevoRegistro.setRegistroBoletoValor(r.getRegistroBoletoValor());
                nuevoRegistro.setRegistroBoletoEsNuevo(false);
                nuevoRegistro.setRegistroBoletoObservacion("");
                nuevoRegistro.setRegistroBoletoFechaIngreso(new Date());
                switch (r.getRegistroBoletoIdBoleto().getBoletoOrden()) {
                    case 1:
                        nuevoRegistro.setRegistroBoletoInicio(Integer.parseInt(_directo) % 1000);
                        r.setRegistroBoletoTermino(nuevoRegistro.getRegistroBoletoInicio());
                        r.setRegistroBoletoCantidad(r.getRegistroBoletoTermino() - r.getRegistroBoletoInicio());
                        r.setRegistroBoletoTotal(r.getRegistroBoletoCantidad() * r.getRegistroBoletoValor());
                        break;
                    case 2:
                        nuevoRegistro.setRegistroBoletoInicio(Integer.parseInt(_planVina) % 1000);
                        r.setRegistroBoletoTermino(nuevoRegistro.getRegistroBoletoInicio());
                        r.setRegistroBoletoCantidad(r.getRegistroBoletoTermino() - r.getRegistroBoletoInicio());
                        r.setRegistroBoletoTotal(r.getRegistroBoletoCantidad() * r.getRegistroBoletoValor());
                        break;
                    case 3:
                        nuevoRegistro.setRegistroBoletoInicio(Integer.parseInt(_local) % 1000);
                        r.setRegistroBoletoTermino(nuevoRegistro.getRegistroBoletoInicio());
                        r.setRegistroBoletoCantidad(r.getRegistroBoletoTermino() - r.getRegistroBoletoInicio());
                        r.setRegistroBoletoTotal(r.getRegistroBoletoCantidad() * r.getRegistroBoletoValor());
                        break;
                    case 4:
                        nuevoRegistro.setRegistroBoletoInicio(Integer.parseInt(_escolarDirecto) % 1000);
                        r.setRegistroBoletoTermino(nuevoRegistro.getRegistroBoletoInicio());
                        r.setRegistroBoletoCantidad(r.getRegistroBoletoTermino() - r.getRegistroBoletoInicio());
                        r.setRegistroBoletoTotal(r.getRegistroBoletoCantidad() * r.getRegistroBoletoValor());
                        break;
                    case 5:
                        nuevoRegistro.setRegistroBoletoInicio(Integer.parseInt(_escolarLocal) % 1000);
                        r.setRegistroBoletoTermino(nuevoRegistro.getRegistroBoletoInicio());
                        r.setRegistroBoletoCantidad(r.getRegistroBoletoTermino() - r.getRegistroBoletoInicio());
                        r.setRegistroBoletoTotal(r.getRegistroBoletoCantidad() * r.getRegistroBoletoValor());
                        break;
                }
                
            }
            
            serie.addRegistroBoleto(nuevoRegistro);

            this.model.addRow(serie);

            if (this.guia.getRegistroBoletoList() == null) {
                this.guia.setRegistroBoletoList(new ArrayList<>(serie.getRegistro()));
            } else {
                this.guia.getRegistroBoletoList().addAll(serie.getRegistro());
            }

            clearTextField();

            int option = JOptionPane.showConfirmDialog(null, "¿Registrar Vuelta?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                RegistroGuiaSaveAction action = new RegistroGuiaSaveAction(this);
                action.save();
                reset();
            }
        }

    }

    public void reset() {
        this.guia = new Guia();
        this.model = new RegistroGuiaTableModel();
        this.view.getTable().setModel(model);
        this.view.getFolioTextField().setText("");
        this.view.getBusTextField().setText("");
        this.view.getConductorTextField().setText("");
        this.view.getPpuTextField().setText("");
        this.view.getFlotaTextField().setText("");
        this.view.getNombreConductorTextField().setText("");
        this.view.getObservacionTextField().setText("");
        this.view.getEstadoBoletoTextField().setText("");
        clearTextField();
        this.view.getFolioTextField().requestFocus();
    }

    public void clearTextField() {
        this.view.getDirectoTextField().setText("");
        this.view.getPlanVinaTextField().setText("");
        this.view.getLocalTextField().setText("");
        this.view.getEscolarDirectoTextField().setText("");
        this.view.getEscolarLocalTextField().setText("");
        this.view.getServicioTextField().setSelectedIndex(0);
    }
}
