/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;

import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import com.areatecnica.nanduappgb.entities.Servicio;
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
        map.forEach((k, v) -> list.add(v));
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

        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).numero;
            case 1:
                return list.get(rowIndex).servicio;
            case 2:
                return list.get(rowIndex).directo;
            case 3:
                return list.get(rowIndex).planVina;
            case 4:
                return list.get(rowIndex).local;
            case 5:
                return list.get(rowIndex).escolarDirecto;
            case 6:
                return list.get(rowIndex).escolarLocal;
        }

        return null;
    }
    
    public void addFirstRow(List<RegistroBoleto> items){
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

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

    public class EstructuraRegistroBoleto {

        private int numero;
        private Servicio servicio;
        private int directo;
        private int planVina;
        private int local;
        private int escolarDirecto;
        private int escolarLocal;
        private RegistroBoleto registro;

        public EstructuraRegistroBoleto() {
        }

        public EstructuraRegistroBoleto(int numero, Servicio servicio, int directo, int planVina, int local, int escolarDirecto, int escolarLocal) {
            this.numero = numero;
            this.servicio = servicio;
            this.directo = directo;
            this.planVina = planVina;
            this.local = local;
            this.escolarDirecto = escolarDirecto;
            this.escolarLocal = escolarLocal;
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public Servicio getServicio() {
            return servicio;
        }

        public void setServicio(Servicio servicio) {
            this.servicio = servicio;
        }

        public int getDirecto() {
            return directo;
        }

        public void setDirecto(int directo) {
            this.directo = directo;
        }

        public int getPlanVina() {
            return planVina;
        }

        public void setPlanVina(int planVina) {
            this.planVina = planVina;
        }

        public int getLocal() {
            return local;
        }

        public void setLocal(int local) {
            this.local = local;
        }

        public int getEscolarDirecto() {
            return escolarDirecto;
        }

        public void setEscolarDirecto(int escolarDirecto) {
            this.escolarDirecto = escolarDirecto;
        }

        public int getEscolarLocal() {
            return escolarLocal;
        }

        public void setEscolarLocal(int escolarLocal) {
            this.escolarLocal = escolarLocal;
        }

        public void setRegistroBoleto(RegistroBoleto registro) {
            switch (registro.getRegistroBoletoIdBoleto().getBoletoOrden()) {
                case 1:
                    this.setDirecto(registro.getRegistroBoletoInicio());
                case 2:
                    this.setPlanVina(registro.getRegistroBoletoInicio());
                case 3:
                    this.setLocal(registro.getRegistroBoletoInicio());
                case 4:
                    this.setEscolarDirecto(registro.getRegistroBoletoInicio());
                case 5:
                    this.setEscolarLocal(registro.getRegistroBoletoInicio());
            }
        }

    }

}
