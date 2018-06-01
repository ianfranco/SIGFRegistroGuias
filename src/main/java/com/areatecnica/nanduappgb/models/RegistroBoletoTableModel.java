/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;

import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import com.areatecnica.nanduappgb.entities.VueltaGuia;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroBoletoTableModel extends AbstractTableModel {

    private List<RegistroBoleto> registroBoletoItems;
    private List<EstructuraRegistroBoletoÑandu> list;
    private Guia guia;
    private final static String[] columnNames = {"#", "Servicio", "Directo", "Plan Viña", "Local", "Esc.Directo", "Esc. Local", "Total Vuelta", "Total Acumulado"};
    private int numeroVuelta;

    public RegistroBoletoTableModel(Guia guia) {
        this.guia = guia;
        init();
    }

    public RegistroBoletoTableModel() {
        this.list = new ArrayList<>();
    }

    private void init() {
        this.list = new ArrayList<>();

        for (VueltaGuia v : this.guia.getVueltaGuiaList()) {
            this.list.add(new EstructuraRegistroBoletoÑandu(v));
        }

        ArrayList aux = new ArrayList<>(this.list);
        System.err.println("TAMAÑO DE LISTAs AUXILIAR:" + aux.size());

        this.list.add(0, this.list.get(0));
        this.list.add(this.list.get(this.list.size() - 1));
        this.list.add(setTotales(aux));

        //setTotales(aux);
        aux = null;
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

        int ultimo = this.list.size() - 1;

        switch (columnIndex) {
            case 0:
                switch (rowIndex) {
                    case 0:
                        return "Serie";
                    default:
                        if (rowIndex == ultimo) {
                            return "Totales";
                        } else if (rowIndex == ultimo - 1) {
                            return "Termino";
                        }
                        return list.get(rowIndex).getVueltaGuia().getVueltaGuiaNumero();

                }
            case 1:
                switch (rowIndex) {
                    case 0:
                        return "";
                    default:
                        if (rowIndex == ultimo) {
                            return "";
                        }
                        return list.get(rowIndex).getVueltaGuia().getVueltaGuiaIdServicio().getServicioNumeroServicio();

                }
            case 2:
                switch (rowIndex) {
                    case 0:
                        return list.get(rowIndex).getDirecto().getRegistroBoletoSerie();
                    default:
                        if (rowIndex == ultimo) {
                            return list.get(rowIndex).getDirecto().getRegistroBoletoTotal();
                        } else if (rowIndex == ultimo - 1) {
                            return list.get(rowIndex).getDirecto().getRegistroBoletoTermino();
                        }
                        return list.get(rowIndex).getDirecto().getRegistroBoletoInicio();

                }

            case 3:
                switch (rowIndex) {
                    case 0:
                        return list.get(rowIndex).getPlanVina().getRegistroBoletoSerie();
                    default:
                        if (rowIndex == ultimo) {
                            return list.get(rowIndex).getPlanVina().getRegistroBoletoTotal();
                        } else if (rowIndex == ultimo - 1) {
                            return list.get(rowIndex).getPlanVina().getRegistroBoletoTermino();
                        }
                        return list.get(rowIndex).getPlanVina().getRegistroBoletoInicio();

                }
            case 4:
                switch (rowIndex) {
                    case 0:
                        return list.get(rowIndex).getLocal().getRegistroBoletoSerie();
                    default:
                        if (rowIndex == ultimo) {
                            return list.get(rowIndex).getLocal().getRegistroBoletoTotal();
                        } else if (rowIndex == ultimo - 1) {
                            return list.get(rowIndex).getLocal().getRegistroBoletoTermino();
                        }
                        return list.get(rowIndex).getLocal().getRegistroBoletoInicio();

                }
            case 5:
                switch (rowIndex) {
                    case 0:
                        return list.get(rowIndex).getEscolarDirecto().getRegistroBoletoSerie();
                    default:
                        if (rowIndex == ultimo) {
                            return list.get(rowIndex).getEscolarDirecto().getRegistroBoletoTotal();
                        } else if (rowIndex == ultimo - 1) {
                            return list.get(rowIndex).getEscolarDirecto().getRegistroBoletoTermino();
                        }
                        return list.get(rowIndex).getEscolarDirecto().getRegistroBoletoInicio();

                }
            case 6:
                switch (rowIndex) {
                    case 0:
                        return list.get(rowIndex).getEscolarLocal().getRegistroBoletoSerie();
                    default:
                        if (rowIndex == ultimo) {
                            return list.get(rowIndex).getEscolarLocal().getRegistroBoletoTotal();
                        } else if (rowIndex == ultimo - 1) {
                            return list.get(rowIndex).getEscolarLocal().getRegistroBoletoTermino();
                        }
                        return list.get(rowIndex).getEscolarLocal().getRegistroBoletoInicio();

                }
            case 7:
                switch (rowIndex) {
                    case 0:
                        return "";
                    default:
                        if (rowIndex == ultimo) {
                            return "";
                        }
                        return (rowIndex == ultimo - 1) ? "" : list.get(rowIndex).getTotalVuelta();

                }
            case 8:
                switch (rowIndex) {
                    case 0:
                        return "";
                    default:
                        if (rowIndex == ultimo) {
                            return "";
                        }
                        return (rowIndex == ultimo - 1) ? "" : list.get(rowIndex).getTotalVuelta();

                }
        }

        return null;
    }

    public void addRow(EstructuraRegistroBoletoÑandu erb) {
        erb.setNumero(numeroVuelta);
        this.numeroVuelta++;

        if (this.list.size() > 2) {
            this.list.remove(this.list.size() - 1); //Aqui estaba el problema, solucionado

        }

        this.list.add(erb);

        //this.list.add(getTotales());
        fireTableChanged(null);
    }

    public boolean removeLast() {
        if (this.list.size() > 2) { //Si tiene una vuelta ingresada
            if (this.list.remove(getUltimoRegistro())) {
                fireTableChanged(null);
                return true;
            }
        }
        return false;
    }

    public int getNumeroVuelta() {
        return numeroVuelta;
    }

    public EstructuraRegistroBoletoÑandu getPrimerRegistro() {
        return (this.list.isEmpty()) ? null : list.get(0);
    }

    public EstructuraRegistroBoletoÑandu getUltimoRegistro() {
        return (this.list.isEmpty()) ? null : list.get(numeroVuelta);
    }

    public EstructuraRegistroBoletoÑandu getTotalRegistro() {
        return (this.list.isEmpty()) ? null : list.get(getRowCount() - 1);
    }

    public EstructuraRegistroBoletoÑandu setTotales(List<EstructuraRegistroBoletoÑandu> lista) {
        EstructuraRegistroBoletoÑandu totales = new EstructuraRegistroBoletoÑandu(true);

        int dir = 0;
        int pla = 0;
        int loc = 0;
        int esc1 = 0;
        int esc2 = 0;

        int i = 0;
        lista.add(totales);
        for (EstructuraRegistroBoletoÑandu e : lista) {
            System.err.println("n° DE VUELTAS:" + i);
            System.err.println("DIRECTO:" + e.getDirecto().getRegistroBoletoTotal());
            dir += e.getDirecto().getRegistroBoletoTotal();

            pla += e.getPlanVina().getRegistroBoletoTotal();
            loc += e.getLocal().getRegistroBoletoTotal();
            esc1 += e.getEscolarDirecto().getRegistroBoletoTotal();
            esc2 += e.getEscolarLocal().getRegistroBoletoTotal();
            i++;
            totales.setTotalVuelta(dir + pla + esc1 + esc2 + loc);

            if (this.list.contains(e)) {
                this.list.get(this.list.indexOf(e)).setTotalVuelta(dir + pla + esc1 + esc2 + loc);
            }
        }

        totales.getDirecto().setRegistroBoletoTotal(dir);
        totales.getPlanVina().setRegistroBoletoTotal(pla);
        totales.getLocal().setRegistroBoletoTotal(loc);
        totales.getEscolarDirecto().setRegistroBoletoTotal(esc1);
        totales.getEscolarLocal().setRegistroBoletoTotal(esc2);

        return totales;
    }

    public List<EstructuraRegistroBoletoÑandu> getList() {
        return this.list;
    }

}
