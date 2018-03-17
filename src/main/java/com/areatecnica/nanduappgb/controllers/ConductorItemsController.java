/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.controllers;

import com.areatecnica.nanduappgb.dao.impl.IProcesoGeneral;
import com.areatecnica.nanduappgb.entities.Terminal;
import com.areatecnica.nanduappgb.entities.Trabajador;
import com.areatecnica.nanduappgb.models.TrabajadorTableModel;
import com.areatecnica.nanduappgb.views.ConductorItemsView;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import javax.swing.JTextField;

/**
 *
 * @author ianfr
 */
public class ConductorItemsController extends Observable {

    private Terminal terminal;
    private ConductorItemsView view;
    private JTextField textField;
    private TrabajadorTableModel model;
    private Trabajador bus;

    public ConductorItemsController(JTextField textField, Terminal terminal, Trabajador bus) {
        super();
        this.bus = bus;
        this.textField = textField;
        this.terminal = terminal;
        if (this.terminal != null) {
            init();
        }
    }

    private void init() {
        this.model = new TrabajadorTableModel(terminal);

        this.view = new ConductorItemsView(null, Boolean.TRUE);
        this.view.setTitle("Lista de Conductores");
        this.view.getConductorTable().setModel(model);
        this.view.getConductorTable().doLayout();
        this.view.getConductorTable().requestFocus();

        if (this.model.getRowCount() > 0) {
            this.view.getConductorTable().setRowSelectionInterval(0, 0);
        }

        this.view.getConductorTable().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    getBus();
                }
            }
        });

        this.view.getConductorTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    getBus();
                }
            }

        });

        this.view.setLocationRelativeTo(null);
        this.view.setSize(800, 400);
        this.view.setVisible(true);
    }

    public void getBus() {
        int row = view.getConductorTable().getSelectedRow();

        if (row > -1) {
            bus = model.getSelectedItem(row);
            setChanged();
            notifyObservers(bus);

            view.dispose();
            textField.setText(String.valueOf(bus.getTrabajadorCodigo()));
            textField.requestFocus();
        }

    }
}
