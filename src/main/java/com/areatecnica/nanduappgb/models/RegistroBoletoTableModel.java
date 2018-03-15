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
    private final static String[] columnNames = {"#", "Servicio", "Directo", "Plan Viña", "Local", "Esc.Directo", "Esc. Local"};
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

        this.numeroVuelta = map.size();

        map.forEach((k, v) -> list.add(v));

        list.add(0, list.get(0));

        if (getRowCount() > 1) {
            list.add(getTotales());
        }
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
                return (rowIndex == 0) ? "Serie" : (rowIndex != getRowCount() - 1) ? list.get(rowIndex).getNumero() : "Totales";
            case 1:
                return (rowIndex == 0) ? "" : list.get(rowIndex).getServicio();
            case 2:
                return (rowIndex == 0) ? list.get(rowIndex).getDirecto().getRegistroBoletoSerie() : (rowIndex != getRowCount() - 1) ? list.get(rowIndex).getDirecto().getRegistroBoletoInicio() : list.get(rowIndex).getDirecto().getRegistroBoletoTotal();
            case 3:
                return (rowIndex == 0) ? list.get(rowIndex).getPlanVina().getRegistroBoletoSerie() : (rowIndex != getRowCount() - 1) ? list.get(rowIndex).getPlanVina().getRegistroBoletoInicio() : list.get(rowIndex).getPlanVina().getRegistroBoletoTotal();
            case 4:
                return (rowIndex == 0) ? list.get(rowIndex).getLocal().getRegistroBoletoSerie() : (rowIndex != getRowCount() - 1) ? list.get(rowIndex).getLocal().getRegistroBoletoInicio() : list.get(rowIndex).getLocal().getRegistroBoletoTotal();
            case 5:
                return (rowIndex == 0) ? list.get(rowIndex).getEscolarDirecto().getRegistroBoletoSerie() : (rowIndex != getRowCount() - 1) ? list.get(rowIndex).getEscolarDirecto().getRegistroBoletoInicio() : list.get(rowIndex).getEscolarDirecto().getRegistroBoletoTotal();
            case 6:
                return (rowIndex == 0) ? list.get(rowIndex).getEscolarLocal().getRegistroBoletoSerie() : (rowIndex != getRowCount() - 1) ? list.get(rowIndex).getEscolarLocal().getRegistroBoletoInicio() : list.get(rowIndex).getEscolarLocal().getRegistroBoletoTotal();
        }

        return null;
    }

    public void addRow(EstructuraRegistroBoletoÑandu erb) {
        erb.setNumero(numeroVuelta);
        this.numeroVuelta++;
        this.list.add(erb);

        this.list.add(getTotales());

        fireTableChanged(null);
    }

    public int getNumeroVuelta() {
        return numeroVuelta;
    }

    public EstructuraRegistroBoletoÑandu getPrimerRegistro() {
        return (this.list.isEmpty()) ? null : list.get(0);
    }

    public EstructuraRegistroBoletoÑandu getUltimoRegistro() {
        return (this.list.isEmpty()) ? null : list.get(list.size() - 1);
    }

    public EstructuraRegistroBoletoÑandu getTotales() {
        EstructuraRegistroBoletoÑandu totales = new EstructuraRegistroBoletoÑandu();

        EstructuraRegistroBoletoÑandu inicio = getPrimerRegistro();

        EstructuraRegistroBoletoÑandu ultimo = getUltimoRegistro();

        totales.setRegistro(ultimo.getRegistro());

        totales.setDirecto(new RegistroBoleto(ultimo.getDirecto().getRegistroBoletoInicio(), inicio.getDirecto().getRegistroBoletoInicio(), ultimo.getDirecto().getRegistroBoletoValor()));
        totales.setPlanVina(new RegistroBoleto(ultimo.getPlanVina().getRegistroBoletoInicio(), inicio.getPlanVina().getRegistroBoletoInicio(), ultimo.getPlanVina().getRegistroBoletoValor()));
        totales.setLocal(new RegistroBoleto(ultimo.getLocal().getRegistroBoletoInicio(), inicio.getLocal().getRegistroBoletoInicio(), ultimo.getLocal().getRegistroBoletoValor()));
        totales.setEscolarDirecto(new RegistroBoleto(ultimo.getEscolarDirecto().getRegistroBoletoInicio(), inicio.getEscolarDirecto().getRegistroBoletoInicio(), ultimo.getEscolarDirecto().getRegistroBoletoValor()));
        totales.setEscolarLocal(new RegistroBoleto(ultimo.getEscolarLocal().getRegistroBoletoInicio(), inicio.getEscolarLocal().getRegistroBoletoInicio(), ultimo.getEscolarLocal().getRegistroBoletoValor()));

        return totales;
    }

}
