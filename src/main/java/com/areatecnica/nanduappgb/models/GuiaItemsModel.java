/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;

import com.areatecnica.nanduappgb.entities.Guia;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ianfrancoconcha
 */
public class GuiaItemsModel extends AbstractTableModel {

    private List<Guia> items;
    private Guia selected;
    private String[] columnNames = {"#", "Folio", "Nº Bus", "Conductor", "Turno", "Fecha Ingreso"};
    private final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

    public GuiaItemsModel(List<Guia> items) {
        this.items = items;
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
                return rowIndex + 1;
            case 1:
                return items.get(rowIndex).getGuiaFolio();
            case 2:
                return items.get(rowIndex).getGuiaIdBus().getBusNumero();
            case 3:
                return items.get(rowIndex).getGuiaIdTrabajador().getTrabajadorApellidoPaterno() + " " + items.get(rowIndex).getGuiaIdTrabajador().getTrabajadorApellidoMaterno() + " " + items.get(rowIndex).getGuiaIdTrabajador().getTrabajadorNombres().substring(0, 1) + ".";
            case 4:
                return items.get(rowIndex).getGuiaTurno();
            case 5:
                return sdf.format(items.get(rowIndex).getGuiaFechaIngreso());
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public void remove(Guia toRemove) {
        if (toRemove != null) {
            if (this.items.remove(toRemove)) {
                fireTableChanged(null);
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado el objeto en la lista", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Guia getRowAt(int index) {
        if (index < items.size()) {
            return this.items.get(index);
        }
        return null;
    }

    public Guia getSelected() {
        return this.selected;
    }

}
