/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.controllers;

import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.views.GuiaItemsView;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class GuiaItemsController {

    private GuiaItemsView view;
    private Guia selected;
    private List<Guia> items;
    private IGuiaDao dao;
    private Date fecha;
    

    public GuiaItemsController() {
    }

    public GuiaItemsController(GuiaItemsView view) {
        this.view = view;
    }

    private void init() {
        this.view.getFecha().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (view.getFecha().getDate() != null) {
                    fecha = view.getFecha().getDate();
                }
            }
        });
    }

    private void load() {
        this.dao = new GuiaDaoImpl();
        this.items = this.dao.findByDate(fecha);
        
        
    }

}
