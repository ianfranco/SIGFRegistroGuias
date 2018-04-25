/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;

import com.areatecnica.nanduappgb.dao.IRegistroBoletoDao;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ianfr
 */
public class BoletoTableModel extends AbstractTableModel {

    private Guia selected;
    private List<RegistroBoleto> items;
    private String[] names = {"N°", "Boleto", "Tarifa", "Serie", "Inicio", "Término", "Cantidad", "Total"};
    private IRegistroBoletoDao dao;
    private Boolean flag;
    private RegistroBoleto total;

    public BoletoTableModel() {
        this.items = new ArrayList<>();
    }

    public BoletoTableModel(List<RegistroBoleto> items, Boolean flag) {
        this.items = items;
        this.flag = flag;
        
        if (!items.isEmpty()) {
            Collections.sort(items, new Comparator<RegistroBoleto>() {
                @Override
                public int compare(RegistroBoleto o1, RegistroBoleto o2) {
                    return (o1.getRegistroBoletoIdBoleto().getBoletoOrden() > o2.getRegistroBoletoIdBoleto().getBoletoOrden()) ? 1 : -1;
                }
            });
        }
        System.err.println("TAMAÑOS DE ITEMS:"+items.size());
        
        this.total = new RegistroBoleto();
        this.items.add(total);
    }

    public Guia getSelected() {
        return selected;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return names.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return (rowIndex != getRowCount() - 1) ? rowIndex + 1 : "";
            case 1:
                return (rowIndex != getRowCount() - 1) ? items.get(rowIndex).getRegistroBoletoIdBoleto().getBoletoNombre() : "";
            case 2:
                return (rowIndex != getRowCount() - 1) ? items.get(rowIndex).getRegistroBoletoValor() : "";
            case 3:
                return (rowIndex != getRowCount() - 1) ? items.get(rowIndex).getRegistroBoletoSerie() : "";
            case 4:
                return (rowIndex != getRowCount() - 1) ? items.get(rowIndex).getRegistroBoletoInicio() : "";
            case 5:
                return (rowIndex != getRowCount() - 1) ? items.get(rowIndex).getRegistroBoletoTermino() : "Totales";
            case 6:
                return items.get(rowIndex).getRegistroBoletoCantidad();
            case 7:
                return items.get(rowIndex).getRegistroBoletoTotal();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return names[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            int value = (Integer) aValue;
            if (rowIndex != getRowCount() - 1) {
                switch (columnIndex) {
                    case 3:
                        items.get(rowIndex).setRegistroBoletoSerie(value);
                        break;
                    case 4:
                        items.get(rowIndex).setRegistroBoletoInicio(value);
                        setTotales();
                        break;
                    case 5:
                        if (value >= items.get(rowIndex).getRegistroBoletoInicio()) {
                            items.get(rowIndex).setRegistroBoletoTermino(value);
                            int cantidad = items.get(rowIndex).getRegistroBoletoTermino() - items.get(rowIndex).getRegistroBoletoInicio();
                            items.get(rowIndex).setRegistroBoletoCantidad(cantidad);
                            items.get(rowIndex).setRegistroBoletoTotal(cantidad * items.get(rowIndex).getRegistroBoletoValor());
                        }
                        setTotales();
                        break;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número", "Error", JOptionPane.WARNING_MESSAGE);
        }

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 3:
                if (flag && rowIndex != getRowCount() - 1) {
                    return true;
                }
            case 4:
                if (flag && rowIndex != getRowCount() - 1) {
                    return true;
                }
            case 5:
                if (!flag) {
                    return true;
                }
        }
        return false;
    }

    public List<RegistroBoleto> getItems() {
        return items;
    }

    private void setTotales() {
        int totalCantidad = 0;
        int totalDinero = 0;

        for (RegistroBoleto r : this.items) {
            totalCantidad = +r.getRegistroBoletoCantidad();
            totalDinero = +r.getRegistroBoletoTotal();
        }

        this.getItems().get(this.getRowCount() - 1).setRegistroBoletoCantidad(totalCantidad);
        this.getItems().get(this.getRowCount() - 1).setRegistroBoletoTotal(totalDinero);
    }

    public void removeTotal() {
        this.items.remove(total);
    }

}
