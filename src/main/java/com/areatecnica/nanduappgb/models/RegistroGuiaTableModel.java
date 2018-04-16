/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;

import com.areatecnica.nanduappgb.dao.IRegistroBoletoDao;
import com.areatecnica.nanduappgb.dao.impl.RegistroBoletoDaoImpl;
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
public class RegistroGuiaTableModel extends AbstractTableModel {

    private List<RegistroBoleto> registroBoletoItems;
    private List<EstructuraRegistroBoletoÑandu> list;
    private final static String[] columnNames = {"#", "Servicio", "Directo", "Plan Viña", "Local", "Esc.Directo", "Esc. Local"};
    private int numeroVuelta;
    private IRegistroBoletoDao dao;
    private Guia guia;

    public RegistroGuiaTableModel(Guia guia) {
        this.guia = guia;
        this.dao = new RegistroBoletoDaoImpl();
        this.registroBoletoItems = this.dao.findUltimaVueltaGuia(this.guia);
        init();
    }

    public RegistroGuiaTableModel() {
        this.list = new ArrayList<>();
    }

    private void init() {
        this.list = new ArrayList<>();

        System.err.println("TAMAÑO DE REGISTRO DE BOLETOS:" + this.registroBoletoItems.size());
        Map<Integer, EstructuraRegistroBoletoÑandu> map = new HashMap<>();

//        for (RegistroBoleto r : this.registroBoletoItems) {
//
//            EstructuraRegistroBoletoÑandu e = new EstructuraRegistroBoletoÑandu();
//
//            if (map.containsKey(r.getRegistroBoletoNumeroVuelta())) {
//                map.get(r.getRegistroBoletoNumeroVuelta()).addRegistroBoleto(r);
//            } else {
//                e.setNumero(r.getRegistroBoletoNumeroVuelta());
//                e.setServicio(r.getRegistroBoletoIdServicio());
//                e.addRegistroBoleto(r);
//                map.put(r.getRegistroBoletoNumeroVuelta(), e);
//            }
//        }
        this.numeroVuelta = 0;

        EstructuraRegistroBoletoÑandu a = new EstructuraRegistroBoletoÑandu(this.registroBoletoItems, this.guia);

        list = null;
        list = new ArrayList<>();

        list.add(a);
        list.add(a);

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
                return (rowIndex == 0) ? "Serie" : (rowIndex == 1) ? "Ùltimo Registro" : "Inicio";
            case 1:
                return (rowIndex == 0) ? "" : list.get(rowIndex).getServicio();
            case 2:
                return (rowIndex == 0) ? list.get(rowIndex).getDirecto().getRegistroBoletoSerie() : list.get(rowIndex).getDirecto().getRegistroBoletoInicio();
            case 3:
                return (rowIndex == 0) ? list.get(rowIndex).getPlanVina().getRegistroBoletoSerie() : list.get(rowIndex).getPlanVina().getRegistroBoletoInicio();
            case 4:
                return (rowIndex == 0) ? list.get(rowIndex).getLocal().getRegistroBoletoSerie() : list.get(rowIndex).getLocal().getRegistroBoletoInicio();
            case 5:
                return (rowIndex == 0) ? list.get(rowIndex).getEscolarDirecto().getRegistroBoletoSerie() : list.get(rowIndex).getEscolarDirecto().getRegistroBoletoInicio();
            case 6:
                return (rowIndex == 0) ? list.get(rowIndex).getEscolarLocal().getRegistroBoletoSerie() : list.get(rowIndex).getEscolarLocal().getRegistroBoletoInicio();
        }

        return null;
    }

    public void addRow(EstructuraRegistroBoletoÑandu erb) {
        erb.setNumero(numeroVuelta);
        this.numeroVuelta++;
        this.list.add(erb);
        fireTableChanged(null);
    }

    public void remove(EstructuraRegistroBoletoÑandu erb) {
        this.numeroVuelta--;
        erb.setNumero(numeroVuelta);
        this.list.remove(erb);
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

    public List<EstructuraRegistroBoletoÑandu> getList() {
        return list;
    }

}
