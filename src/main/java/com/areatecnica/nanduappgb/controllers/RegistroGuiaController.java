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
import com.areatecnica.nanduappgb.models.EstructuraRegistroBoleto;
import com.areatecnica.nanduappgb.models.EstructuraRegistroBoletoÑandu;
import com.areatecnica.nanduappgb.models.RegistroBoletoTableModel;
import com.areatecnica.nanduappgb.utils.NextObject;
import com.areatecnica.nanduappgb.utils.NumberLimiter;
import com.areatecnica.nanduappgb.utils.TextSelectionFocusAdapter;
import com.areatecnica.nanduappgb.views.RegistroVueltaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroGuiaController {

    private RegistroVueltaView view;
    private ProcesoGeneralNandu proceso;
    private Guia guia;
    private Servicio servicio;
    private RegistroBoleto registroBoleto;
    private Map<Integer, RegistroBoleto> map;
    /*Optimización*/
    private Map<String, Servicio> mapServicios;
    private EstructuraRegistroBoleto erb;
    private RegistroBoletoTableModel model;
    private TarifaGrupoServicioSolyMar tarifaSolyMar;
    private Boolean flag;

    public RegistroGuiaController() {
    }

    public RegistroGuiaController(RegistroVueltaView view) {
        this.view = view;
        init();
    }

    private void init() {
        this.guia = new Guia();
        this.guia.setGuiaFecha(new Date());
        this.guia.setGuiaTotalIngreso(0);

        this.proceso = new ProcesoGeneralNandu();
        this.tarifaSolyMar = new TarifaGrupoServicioSolyMar();

        //Rasca 
        this.servicio = this.tarifaSolyMar.getGrupo().getGrupoServicio().getServicioList().get(0);
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

        this.view.getServicioTextField().addKeyListener(new NextObject(this.view.getConductorTextField(), this.view.getDirectoTextField(), this.view.getDirectoTextField(), this.view.getConductorTextField()));
        this.view.getServicioTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getServicioTextField()));
        this.view.getServicioTextField().setDocument(new NumberLimiter());

        this.view.getDirectoTextField().addKeyListener(new NextObject(this.view.getServicioTextField(), this.view.getPlanVinaTextField(), this.view.getPlanVinaTextField(), this.view.getServicioTextField()));
        this.view.getDirectoTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getDirectoTextField()));
        this.view.getDirectoTextField().setDocument(new NumberLimiter());

        this.view.getPlanVinaTextField().addKeyListener(new NextObject(this.view.getDirectoTextField(), this.view.getLocalTextField(), this.view.getLocalTextField(), this.view.getDirectoTextField()));
        this.view.getPlanVinaTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getPlanVinaTextField()));
        this.view.getPlanVinaTextField().setDocument(new NumberLimiter());

        this.view.getLocalTextField().addKeyListener(new NextObject(this.view.getPlanVinaTextField(), this.view.getEscolarDirectoTextField(), this.view.getEscolarDirectoTextField(), this.view.getPlanVinaTextField()));
        this.view.getLocalTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getLocalTextField()));
        this.view.getLocalTextField().setDocument(new NumberLimiter());

        this.view.getEscolarDirectoTextField().addKeyListener(new NextObject(this.view.getLocalTextField(), this.view.getEscolarLocalTextField(), this.view.getEscolarLocalTextField(), this.view.getLocalTextField()));
        this.view.getEscolarDirectoTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getEscolarDirectoTextField()));
        this.view.getEscolarDirectoTextField().setDocument(new NumberLimiter());

        this.view.getEscolarLocalTextField().addKeyListener(new NextObject(this.view.getEscolarDirectoTextField(), this.view.getAddButton(), this.view.getAddButton(), this.view.getEscolarDirectoTextField()));
        this.view.getEscolarLocalTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getEscolarLocalTextField()));
        this.view.getEscolarLocalTextField().setDocument(new NumberLimiter());

        this.view.getAddButton().addKeyListener(new NextObject(this.view.getEscolarLocalTextField(), null, null, this.view.getEscolarLocalTextField()));

        this.view.getAddButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag) {
                    setUpBoletos();
                } else {
                    addRow();
                }
            }
        });

        this.view.getSaveButton().addActionListener(new RegistroGuiaSaveAction(this));

        this.view.getFolioTextField().requestFocus();
    }

    public RegistroVueltaView getView() {
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

    public RegistroBoletoTableModel getModel() {
        return model;
    }

    public void setModel(RegistroBoletoTableModel model) {
        getView().getTable().setModel(model);
        this.model = model;

        if (this.model.getRowCount() > 1) {
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

        try {
            String _servicio = (this.view.getServicioTextField().getText());

            if (mapServicios.containsKey(_servicio)) {
                this.servicio = mapServicios.get(_servicio);
            }

            String _directo = (this.view.getDirectoTextField().getText());
            String _planVina = (this.view.getPlanVinaTextField().getText());
            String _local = (this.view.getLocalTextField().getText());
            String _escolarDirecto = (this.view.getEscolarDirectoTextField().getText());
            String _escolarLocal = (this.view.getEscolarLocalTextField().getText());

            serie.setNumero(0);
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

            this.clearTextField();

        } catch (NumberFormatException numberFormatException) {
        }

    }

    private void addRow() {
        EstructuraRegistroBoletoÑandu serie = new EstructuraRegistroBoletoÑandu();

        String _servicio = (this.view.getServicioTextField().getText());

        if (mapServicios.containsKey(_servicio)) {
            this.servicio = mapServicios.get(_servicio);

        }

        serie.setServicio(servicio);

        String _directo = (this.view.getDirectoTextField().getText());
        String _planVina = (this.view.getPlanVinaTextField().getText());
        String _local = (this.view.getLocalTextField().getText());
        String _escolarDirecto = (this.view.getEscolarDirectoTextField().getText());
        String _escolarLocal = (this.view.getEscolarLocalTextField().getText());

        map.forEach((k, v) -> {
            v.setRegistroBoletoIdGuia(this.guia);
            switch (k) {
                case 1:
                    v.setRegistroBoletoInicio(Integer.parseInt(_directo) % 1000);
                    break;
                case 2:
                    v.setRegistroBoletoInicio(Integer.parseInt(_planVina) % 1000);
                    break;
                case 3:
                    v.setRegistroBoletoInicio(Integer.parseInt(_local) % 1000);
                    break;
                case 4:
                    v.setRegistroBoletoInicio(Integer.parseInt(_escolarDirecto) % 1000);
                    break;
                case 5:
                    v.setRegistroBoletoInicio(Integer.parseInt(_escolarLocal) % 1000);
                    break;
            }
            serie.addRegistroBoleto(v);
        });

        

        this.model.addRow(serie);

        this.guia.getRegistroBoletoList().addAll(serie.getRegistro());
        clearTextField();
    }

    public void reset() {
        this.guia = new Guia();
        this.model = new RegistroBoletoTableModel();
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
    }

    public void clearTextField() {
        this.view.getDirectoTextField().setText("");
        this.view.getPlanVinaTextField().setText("");
        this.view.getLocalTextField().setText("");
        this.view.getEscolarDirectoTextField().setText("");
        this.view.getEscolarLocalTextField().setText("");
        this.view.getServicioTextField().setText("");
    }
}
