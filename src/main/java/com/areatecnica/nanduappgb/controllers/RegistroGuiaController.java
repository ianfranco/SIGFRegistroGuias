/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.controllers;

import com.areatecnica.nanduappgb.behaviors.FindBusSolyMarFocusLost;
import com.areatecnica.nanduappgb.behaviors.FindConductorFocusLost;
import com.areatecnica.nanduappgb.behaviors.FindFolioFocusLost;
import com.areatecnica.nanduappgb.dao.impl.ProcesoGeneralNandu;
import com.areatecnica.nanduappgb.dao.impl.TarifaGrupoServicioSolyMar;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import com.areatecnica.nanduappgb.entities.Servicio;
import com.areatecnica.nanduappgb.entities.TarifaGrupoServicio;
import com.areatecnica.nanduappgb.models.RegistroGuiaTableModel;
import com.areatecnica.nanduappgb.utils.NextObject;
import com.areatecnica.nanduappgb.utils.TextSelectionFocusAdapter;
import com.areatecnica.nanduappgb.views.RegistroGuiaView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroGuiaController {

    private RegistroGuiaView view;
    private ProcesoGeneralNandu proceso;
    private Guia guia;
    private Servicio servicio; 
    private RegistroBoleto registroBoleto;
    private RegistroGuiaTableModel model; 
    private TarifaGrupoServicioSolyMar tarifaSolyMar;

    public RegistroGuiaController() {
    }

    public RegistroGuiaController(RegistroGuiaView view) {
        this.view = view;
        init();
    }

    private void init() {
        this.guia = new Guia();
        this.proceso = new ProcesoGeneralNandu();
        this.tarifaSolyMar = new TarifaGrupoServicioSolyMar();
        
        //Rasca 
        this.servicio = this.tarifaSolyMar.getGrupo().getGrupoServicio().getServicioList().get(0);
        getTarifas();

        this.view.getFolioTextField().addFocusListener(new FindFolioFocusLost(this));
        this.view.getFolioTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getFolioTextField()));
        this.view.getFolioTextField().addKeyListener(new NextObject(null, this.view.getBusTextField(), null, null));

        this.view.getBusTextField().addFocusListener(new FindBusSolyMarFocusLost(this));
        this.view.getBusTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getBusTextField()));
        this.view.getBusTextField().addKeyListener(new NextObject(this.view.getFolioTextField(), this.view.getConductorTextField(), null, null));
        
        this.view.getConductorTextField().addKeyListener(new NextObject(this.view.getBusTextField(), this.view.getTable(), null, null));
        this.view.getConductorTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getConductorTextField()));
        this.view.getConductorTextField().addFocusListener(new FindConductorFocusLost(this));
        
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
        this.model = model;
    }

    public List<RegistroBoleto> getTarifas(){
        
        List<RegistroBoleto> items = new ArrayList<>();
        
        for(TarifaGrupoServicio t: this.tarifaSolyMar.getTarifaGrupoServicio()){
            RegistroBoleto r = new RegistroBoleto();
            r.setRegistroBoletoIdBoleto(t.getTarifaGrupoServicioIdBoleto());
            r.setRegistroBoletoIdGuia(this.guia);
            r.setRegistroBoletoIdServicio(this.servicio);
            r.setRegistroBoletoEsNuevo(Boolean.TRUE);
            r.setRegistroBoletoFechaIngreso(new Date());
            r.setRegistroBoletoObservacion("Nuevo Boleto");
            
            items.add(r);
        }
        
        System.err.println("TAMAÑO DE BOLETOS;"+items.size());
        
        return items;
    }
    
}
