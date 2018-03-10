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
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroBoletoTableModel extends AbstractTableModel {

    private List<RegistroBoleto> registroBoletoItems;
    private List<EstructuraRegistroBoletoÑandu> list;
    private EstructuraRegistroBoletoÑandu ultimoRegistro;
    private final static String[] columnNames = {"#", "Servicio", "Directo", "Plan Viña", "Local", "Esc.Directo", "Esc. Local"};
    private Boolean flag;
    private int numeroVuelta;

    public RegistroBoletoTableModel(Guia guia) {
        this.registroBoletoItems = guia.getRegistroBoletoList();
        init();
    }

    public RegistroBoletoTableModel() {
        this.list = new ArrayList<>();
    }

    private void init() {
        this.list = new ArrayList<>();
        System.err.println("TAMAÑO DE REGISTRO DE BOLETOS:" + this.registroBoletoItems.size());
        Map<Integer, EstructuraRegistroBoletoÑandu> map = new HashMap<>();

        for (RegistroBoleto r : this.registroBoletoItems) {

            EstructuraRegistroBoletoÑandu e = new EstructuraRegistroBoletoÑandu();

            if (map.containsKey(r.getRegistroBoletoNumeroVuelta())) {
                map.get(r.getRegistroBoletoNumeroVuelta()).addRegistroBoleto(r);
            } else {
                e.setNumero(r.getRegistroBoletoNumeroVuelta());
                e.setServicio(r.getRegistroBoletoIdServicio());
                e.addRegistroBoleto(r);
                map.put(r.getRegistroBoletoNumeroVuelta(), e);
            }
        }
        //Falta ordenar boletos
        this.numeroVuelta = map.size();
        //Seteo de la serie
//        EstructuraRegistroBoletoÑandu erb = map.get(0);
//        EstructuraRegistroBoletoÑandu serie = new EstructuraRegistroBoletoÑandu();
//        for (RegistroBoleto r : erb.getRegistro()) {
//            switch (r.getRegistroBoletoIdBoleto().getBoletoOrden()) {
//                case 1:
//                    serie.setDirecto(r.getRegistroBoletoSerie());
//                    break;
//                case 2:
//                    serie.setPlanVina(r.getRegistroBoletoSerie());
//                    break;
//                case 3:
//                    serie.setLocal(r.getRegistroBoletoSerie());
//                    break;
//                case 4:
//                    serie.setEscolarDirecto(r.getRegistroBoletoSerie());
//                    break;
//                case 5:
//                    serie.setEscolarLocal(r.getRegistroBoletoSerie());
//                    break;
//            }
//        }
//
//        list.add(serie);

//aqui está el problema        
//        EstructuraRegistroBoletoÑandu serie = new EstructuraRegistroBoletoÑandu();
//
//        serie.setDirecto(erb.getSerieDirecto());
//        serie.setPlanVina(erb.getSeriePlanVina());
//        serie.setLocal(erb.getSerieLocal());
//        serie.setSerieEscolarDirecto(erb.getSerieEscolarDirecto());
//        serie.setSerieEscolarLocal(erb.getSerieEscolarLocal());
//
//        list.add(serie);
        map.forEach((k, v) -> list.add(v));
        
        list.add(0, list.get(0));
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
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return String.class;
        }
        return Integer.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return (rowIndex == 0) ? "Serie" : list.get(rowIndex).getNumero();
            case 1:
                return list.get(rowIndex).getServicio();
            case 2:
                return (rowIndex == 0)? list.get(rowIndex).getDirecto().getRegistroBoletoSerie():list.get(rowIndex).getDirecto().getRegistroBoletoInicio();
            case 3:
                return (rowIndex == 0)? list.get(rowIndex).getPlanVina().getRegistroBoletoSerie():list.get(rowIndex).getPlanVina().getRegistroBoletoInicio();
            case 4:
                return (rowIndex == 0)? list.get(rowIndex).getLocal().getRegistroBoletoSerie():list.get(rowIndex).getLocal().getRegistroBoletoInicio();
            case 5:
                return (rowIndex == 0)? list.get(rowIndex).getEscolarDirecto().getRegistroBoletoSerie():list.get(rowIndex).getEscolarDirecto().getRegistroBoletoInicio();
            case 6:
                return (rowIndex == 0)? list.get(rowIndex).getEscolarLocal().getRegistroBoletoSerie():list.get(rowIndex).getEscolarLocal().getRegistroBoletoInicio();
        }

        return null;
    }

//    public void addFirstRow(List<RegistroBoleto> items) {
//        Map<Integer, EstructuraRegistroBoletoÑandu> map = new HashMap<Integer, EstructuraRegistroBoletoÑandu>();
//        for (RegistroBoleto r : items) {
//            EstructuraRegistroBoletoÑandu serie = new EstructuraRegistroBoletoÑandu();
//            EstructuraRegistroBoletoÑandu inicio = new EstructuraRegistroBoletoÑandu();
//
//            serie.setServicio(r.getRegistroBoletoIdServicio());
//            inicio.setServicio(r.getRegistroBoletoIdServicio());
//
//            serie.setNumero(0);
//            inicio.setNumero(1);
//
//            serie.setDirecto(r.getRegistroBoletoSerie());
//        }
//        Falta ordenar boletos
//        map.forEach((k, v) -> list.add(v));
//    }

    public void addRow(EstructuraRegistroBoletoÑandu erb) {
        erb.setNumero(numeroVuelta);
        this.numeroVuelta++;
        this.list.add(erb);
        fireTableChanged(null);
    }

    public int getNumeroVuelta() {
        return numeroVuelta;
    }

    public EstructuraRegistroBoletoÑandu getUltimoRegistro() {
        return list.get(list.size() - 1);
    }

}
