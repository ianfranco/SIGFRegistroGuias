/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;

import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroBoletoTableModel extends AbstractTableModel {

    private Guia guia;
    private List<RegistroBoleto> registroBoletoItems;
    private List<EstructuraRegistroBoleto> list;
    private final static String[] columnNames = {"#", "Servicio", "Directo", "Plan Viña", "Local", "Esc.Directo", "Esc. Local"};
    private Boolean flag;

    public RegistroBoletoTableModel(List<RegistroBoleto> registroBoletoItems) {
        this.registroBoletoItems = registroBoletoItems;
        init();
    }

    public RegistroBoletoTableModel(List<RegistroBoleto> registroBoletoItems, Boolean flag) {
        this.registroBoletoItems = registroBoletoItems;
        this.flag = flag;
        this.list = new ArrayList<>();
    }

    private void init() {
        this.list = new ArrayList<>();

        Map<Integer, EstructuraRegistroBoleto> map = new HashMap<Integer, EstructuraRegistroBoleto>();
        for (RegistroBoleto r : this.registroBoletoItems) {
            EstructuraRegistroBoleto e = new EstructuraRegistroBoleto();

            if (map.containsKey(r.getRegistroBoletoNumeroVuelta())) {
                map.get(r.getRegistroBoletoNumeroVuelta()).setRegistroBoleto(r);
            } else {
                e.setNumero(r.getRegistroBoletoNumeroVuelta());
                e.setRegistroBoleto(r);
                map.put(r.getRegistroBoletoNumeroVuelta(), e);
            }
        }
        //Falta ordenar boletos

        Map<Integer, EstructuraRegistroBoleto> treeMap = new TreeMap<Integer, EstructuraRegistroBoleto>();

        /*EstructuraRegistroBoleto erb = treeMap.get(0);
        
        EstructuraRegistroBoleto serie = new EstructuraRegistroBoleto();
        
        serie.setDirecto(erb.getSerieDirecto());
        serie.setPlanVina(0);*/
        treeMap.forEach((k, v) -> list.add(v));
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (rowIndex > 0) {
            switch (columnIndex) {
                case 0:
                    return "Serie";
                case 1:
                    return list.get(rowIndex).getServicio();
                case 2:
                    return list.get(rowIndex).getSerieDirecto();
                case 3:
                    return list.get(rowIndex).getSeriePlanVina();
                case 4:
                    return list.get(rowIndex).getSerieLocal();
                case 5:
                    return list.get(rowIndex).getSerieEscolarDirecto();
                case 6:
                    return list.get(rowIndex).getSerieEscolarLocal();
            }
        } else {
            switch (columnIndex) {
                case 0:
                    return list.get(rowIndex).getNumero();
                case 1:
                    return list.get(rowIndex).getServicio();
                case 2:
                    return list.get(rowIndex).getDirecto();
                case 3:
                    return list.get(rowIndex).getPlanVina();
                case 4:
                    return list.get(rowIndex).getLocal();
                case 5:
                    return list.get(rowIndex).getEscolarDirecto();
                case 6:
                    return list.get(rowIndex).getEscolarLocal();
            }
        }

        return null;
    }

    public void addFirstRow(List<RegistroBoleto> items) {
        Map<Integer, EstructuraRegistroBoleto> map = new HashMap<Integer, EstructuraRegistroBoleto>();
        for (RegistroBoleto r : items) {
            EstructuraRegistroBoleto serie = new EstructuraRegistroBoleto();
            EstructuraRegistroBoleto inicio = new EstructuraRegistroBoleto();

            serie.setServicio(r.getRegistroBoletoIdServicio());
            inicio.setServicio(r.getRegistroBoletoIdServicio());

            serie.setNumero(0);
            inicio.setNumero(1);

            serie.setDirecto(r.getRegistroBoletoSerie());
        }
        //Falta ordenar boletos
        map.forEach((k, v) -> list.add(v));
    }

    public void addRow(EstructuraRegistroBoleto erb) {
        this.list.add(erb);
        fireTableChanged(null);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return String.class;
        }
        return Integer.class;
    }

}
