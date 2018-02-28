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
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import com.areatecnica.nanduappgb.utils.NextObject;
import com.areatecnica.nanduappgb.utils.TextSelectionFocusAdapter;
import com.areatecnica.nanduappgb.views.RegistroGuiaView;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroGuiaController {

    private RegistroGuiaView view;
    private ProcesoGeneralNandu proceso;
    private Guia guia;
    private RegistroBoleto registroBoleto;

    public RegistroGuiaController() {
    }

    public RegistroGuiaController(RegistroGuiaView view) {
        this.view = view;
        init();
    }

    private void init() {
        this.guia = new Guia();
        this.proceso = new ProcesoGeneralNandu();

        this.view.getFolioTextField().addFocusListener(new FindFolioFocusLost(this));
        this.view.getFolioTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getFolioTextField()));
        this.view.getFolioTextField().addKeyListener(new NextObject(null, this.view.getBusTextField(), null, null));

        this.view.getBusTextField().addFocusListener(new FindBusSolyMarFocusLost(this));
        this.view.getBusTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getBusTextField()));
        this.view.getBusTextField().addKeyListener(new NextObject(this.view.getFolioTextField(), this.view.getConductorTextField(), null, null));
        
        this.view.getConductorTextField().addKeyListener(new NextObject(this.view.getBusTextField(), this.view.getServicioTextField(), null, null));
        this.view.getConductorTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getConductorTextField()));
        this.view.getConductorTextField().addFocusListener(new FindConductorFocusLost(this));
        
        this.view.getServicioTextField().addKeyListener(new NextObject(this.view.getConductorTextField(), this.view.getDirectoTextField(), this.view.getDirectoTextField(), null));
        this.view.getServicioTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getServicioTextField()));
        
        this.view.getDirectoTextField().addKeyListener(new NextObject(this.view.getServicioTextField(), this.view.getPlanVinaTextField(), this.view.getPlanVinaTextField(), this.view.getServicioTextField()));
        this.view.getDirectoTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getDirectoTextField()));
        
        this.view.getPlanVinaTextField().addKeyListener(new NextObject(this.view.getDirectoTextField(), this.view.getLocalTextField(), this.view.getLocalTextField(), this.view.getDirectoTextField()));
        this.view.getPlanVinaTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getPlanVinaTextField()));
        
        this.view.getLocalTextField().addKeyListener(new NextObject(this.view.getPlanVinaTextField(), this.view.getEscolarDirectoTextField(), this.view.getEscolarDirectoTextField(), this.view.getPlanVinaTextField()));
        this.view.getLocalTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getLocalTextField()));
        
        this.view.getEscolarDirectoTextField().addKeyListener(new NextObject(this.view.getLocalTextField(), this.view.getEscolarLocalTextField(), this.view.getEscolarLocalTextField(), this.view.getLocalTextField()));
        this.view.getEscolarDirectoTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getEscolarDirectoTextField()));
        
        this.view.getEscolarLocalTextField().addKeyListener(new NextObject(this.view.getEscolarDirectoTextField(), this.view.getAddButton(), this.view.getAddButton(), this.view.getEscolarDirectoTextField()));
        this.view.getEscolarLocalTextField().addFocusListener(new TextSelectionFocusAdapter(this.view.getEscolarLocalTextField()));
        
        this.view.getAddButton().addKeyListener(new NextObject(this.view.getEscolarLocalTextField(), this.view.getSaveButton(), null, this.view.getEscolarLocalTextField()));
    
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

}
