/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.controllers;

import com.areatecnica.nanduappgb.behaviors.RegistroGuiaDeleteAction;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.models.GuiaItemsModel;
import com.areatecnica.nanduappgb.views.GuiaItemsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

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
    private GuiaItemsModel model;

    public GuiaItemsController() {
    }

    public GuiaItemsController(GuiaItemsView view) {
        this.view = view;
        init();
        load();
    }

    private void init() {
        this.view.getFecha().setDate(new Date());
        
        this.view.getFecha().addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (view.getFecha().getDate() != null) {
                    fecha = view.getFecha().getDate();
                }
            }
        });

        this.view.getFindButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
            }
        });

        this.view.getDeleteButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(e);
            }
        });

    }

    private void load() {
        try {
            this.fecha = this.view.getFecha().getDate();

            this.dao = new GuiaDaoImpl();
            this.items = this.dao.findByDate(this.fecha);

            if (!this.items.isEmpty()) {
                this.model = new GuiaItemsModel(items);
                this.view.getTable().setModel(model);
            } else {
                JOptionPane.showMessageDialog(null, "No se han encontrado registros", "Información", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NullPointerException n) {
            JOptionPane.showMessageDialog(null, "Favor revisar la fecha seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void remove(ActionEvent e) {
        //rasca
        int index = this.view.getTable().getSelectedRow();

        if (index > -1) {
            this.selected = this.model.getRowAt(index);
            RegistroGuiaDeleteAction action = new RegistroGuiaDeleteAction(this);
            if (action.save()) {
                this.model.remove(selected);
                this.selected = null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una guía", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public List<Guia> getItems() {
        return items;
    }

    public Guia getSelected() {
        return selected;
    }

    public void setItems(List<Guia> items) {
        this.items = items;
    }

    public void setSelected(Guia selected) {
        this.selected = selected;
    }

}
