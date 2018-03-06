/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;

import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroGuiaTableModel extends AbstractTableModel {

    private Guia guia;
    private List<RegistroBoleto> registroBoletoItems;
    private static final String[] columnNames = {"Nº", "Boleto", "Serie", "Inicio"};
    private final Boolean flag;

    public RegistroGuiaTableModel(Guia guia, Boolean flag) {
        this.guia = guia;
        this.registroBoletoItems = this.guia.getRegistroBoletoList();
        this.flag = flag;
    }
    
    public RegistroGuiaTableModel(List<RegistroBoleto> registroBoletoItems, Boolean flag){
        this.registroBoletoItems = registroBoletoItems;
        this.flag = flag;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return this.registroBoletoItems.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return super.isCellEditable(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return registroBoletoItems.get(rowIndex).getRegistroBoletoIdBoleto().getBoletoNombre();
            case 2:
                return registroBoletoItems.get(rowIndex).getRegistroBoletoInicio();
            case 3:
                return registroBoletoItems.get(rowIndex).getRegistroBoletoTermino();
        }

        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        int _value = (Integer) aValue;

        switch (columnIndex) {
            case 1:
                if (flag) {
                    this.registroBoletoItems.get(rowIndex).setRegistroBoletoInicio(_value);
                }
                break;
            case 2: 
                    this.registroBoletoItems.get(rowIndex).setRegistroBoletoTermino(_value);
                break;
                
        }
    }
    
    public void addRow(RegistroBoleto registroBoleto, int index){
        this.registroBoletoItems.add(index, registroBoleto);
        fireTableChanged(null);
    }

    public void deleteRow(RegistroBoleto registroBoleto){
        this.registroBoletoItems.remove(registroBoleto);
        fireTableChanged(null);
    }
    
}
