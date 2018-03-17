/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;


import com.areatecnica.nanduappgb.dao.ITrabajadorDao;
import com.areatecnica.nanduappgb.dao.impl.TrabajadorDaoImpl;
import com.areatecnica.nanduappgb.entities.Terminal;
import com.areatecnica.nanduappgb.entities.Trabajador;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ianfr
 */
public class TrabajadorTableModel extends AbstractTableModel {

    private Terminal terminal;
    private ITrabajadorDao dao;
    private List<Trabajador> items;
    private final static String[] columnNames = {"Cod.", "Apellido P.", "Apellido M.", "Nombres"};

    public TrabajadorTableModel(Terminal terminal) {
        this.terminal = terminal;
        init();
    }

    private void init() {
        this.dao = new TrabajadorDaoImpl();
        this.items = this.dao.findByTerminal(terminal);
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {            
            case 0:
                return items.get(rowIndex).getTrabajadorCodigo();
            case 1:
                return items.get(rowIndex).getTrabajadorApellidoPaterno();
            case 2:
                return items.get(rowIndex).getTrabajadorApellidoMaterno();
            case 3:
                return items.get(rowIndex).getTrabajadorNombres();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Trabajador getSelectedItem(int row) {
        return this.items.get(row);
    }

}
