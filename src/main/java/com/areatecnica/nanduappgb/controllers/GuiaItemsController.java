/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.controllers;

import com.areatecnica.nanduappgb.behaviors.DeleteGuiaAction;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.models.GuiaItemsModel;
import com.areatecnica.nanduappgb.models.RegistroBoletoTableModel;
import com.areatecnica.nanduappgb.views.GuiaItemsView;
import com.areatecnica.nanduappgb.views.MainView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ianfr
 */
public class GuiaItemsController {

    private MainView mainView;
    private GuiaItemsView view;
    private Guia selected;
    private List<Guia> items;
    private IGuiaDao dao;
    private Date fecha;
    private GuiaItemsModel model;
    private RegistroBoletoTableModel boletosModel;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    public GuiaItemsController() {
    }

    public GuiaItemsController(GuiaItemsView view, MainView mainView) {
        this.view = view;
        this.mainView = mainView; 
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

        this.view.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                getBoletosModel();
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

    private void getBoletosModel() {
        if (this.view.getTable().getSelectedRow() > -1) {
            int index = this.view.getTable().getSelectedRow();

            this.selected = this.model.getRowAt(index);

            if (!this.selected.getVueltaGuiaList().isEmpty()) {
                this.boletosModel = new RegistroBoletoTableModel(selected);
                this.view.getTableBoletos().setModel(this.boletosModel);
            }else{
                this.boletosModel = new RegistroBoletoTableModel();
                this.view.getTableBoletos().setModel(this.boletosModel);
            }
        }
    }

    private void remove(ActionEvent e) {
        //rasca
        int index = this.view.getTable().getSelectedRow();

        if (index > -1) {
            this.selected = this.model.getRowAt(index);
            DeleteGuiaAction action = new DeleteGuiaAction(this.selected);
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
