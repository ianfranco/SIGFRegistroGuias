/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;

import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import com.areatecnica.nanduappgb.entities.Servicio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ianfrancoconcha
 */
public class EstructuraRegistroBoleto {

    private int numero;
    private Servicio servicio;
    private int directo;
    private int serieDirecto;
    private int planVina;
    private int seriePlanVina;
    private int local;
    private int serieLocal;
    private int escolarDirecto;
    private int serieEscolarDirecto;
    private int escolarLocal;
    private int serieEscolarLocal;
    private List<RegistroBoleto> registro;

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

    public int getSerieDirecto() {
        return serieDirecto;
    }

    public void setSerieDirecto(int serieDirecto) {
        this.serieDirecto = serieDirecto;
    }

    public int getSeriePlanVina() {
        return seriePlanVina;
    }

    public void setSeriePlanVina(int seriePlanVina) {
        this.seriePlanVina = seriePlanVina;
    }

    public int getSerieLocal() {
        return serieLocal;
    }

    public void setSerieLocal(int serieLocal) {
        this.serieLocal = serieLocal;
    }

    public int getSerieEscolarDirecto() {
        return serieEscolarDirecto;
    }

    public void setSerieEscolarDirecto(int serieEscolarDirector) {
        this.serieEscolarDirecto = serieEscolarDirector;
    }

    public int getSerieEscolarLocal() {
        return serieEscolarLocal;
    }

    public void setSerieEscolarLocal(int serieEscolarLocal) {
        this.serieEscolarLocal = serieEscolarLocal;
    }

    public void addRegistroBoleto(RegistroBoleto registro) {
        if (this.registro == null) {
            this.registro = new ArrayList<>();
        }
        
        this.registro.add(registro);

        switch (registro.getRegistroBoletoIdBoleto().getBoletoOrden()) {
            case 1:
                this.setSerieDirecto(registro.getRegistroBoletoSerie());
                this.setDirecto(registro.getRegistroBoletoInicio());
                System.err.println("Directo:" + directo);
            case 2:
                this.setSeriePlanVina(registro.getRegistroBoletoSerie());
                this.setPlanVina(registro.getRegistroBoletoInicio());
                System.err.println("Plan:" + planVina);
            case 3:
                this.setSerieLocal(registro.getRegistroBoletoSerie());
                this.setLocal(registro.getRegistroBoletoInicio());
                System.err.println("Local:" + local);
            case 4:
                this.setSerieEscolarDirecto(registro.getRegistroBoletoSerie());
                this.setEscolarDirecto(registro.getRegistroBoletoInicio());
                System.err.println("Esc.1:" + escolarDirecto);
            case 5:
                this.setSerieEscolarLocal(registro.getRegistroBoletoSerie());
                this.setEscolarLocal(registro.getRegistroBoletoInicio());
                System.err.println("Esc.2:" + escolarLocal);
        }
    }

    public void setRegistroBoleto() {

        for (RegistroBoleto r : registro) {
            switch (r.getRegistroBoletoIdBoleto().getBoletoOrden()) {
                case 1:
                    this.setSerieDirecto(r.getRegistroBoletoSerie());
                    this.setDirecto(r.getRegistroBoletoInicio());
                case 2:
                    this.setSeriePlanVina(r.getRegistroBoletoSerie());
                    this.setPlanVina(r.getRegistroBoletoInicio());
                case 3:
                    this.setSerieLocal(r.getRegistroBoletoSerie());
                    this.setLocal(r.getRegistroBoletoInicio());
                case 4:
                    this.setSerieEscolarDirecto(r.getRegistroBoletoSerie());
                    this.setEscolarDirecto(r.getRegistroBoletoInicio());
                case 5:
                    this.setSerieEscolarLocal(r.getRegistroBoletoSerie());
                    this.setEscolarLocal(r.getRegistroBoletoInicio());
            }
        }
    }

    public List<RegistroBoleto> getRegistro() {
        return registro;
    }

}
