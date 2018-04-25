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
import java.util.List;

/**
 *
 * @author ianfrancoconcha
 */
public class EstructuraRegistroBoletoÑandu {

    private int numero;
    private Servicio servicio;
    private RegistroBoleto directo;
    private RegistroBoleto planVina;
    private RegistroBoleto local;
    private RegistroBoleto escolarLocal;
    private RegistroBoleto escolarDirecto;
    private int totalVuelta;

    private List<RegistroBoleto> registro;

    public EstructuraRegistroBoletoÑandu() {
        this.totalVuelta = 0;
    }

    public EstructuraRegistroBoletoÑandu(List<RegistroBoleto> _registro, Guia guia) {

        _registro.forEach((r) -> {
            RegistroBoleto nuevo = r;
//            nuevo.setRegistroBoletoIdGuia(guia);

            addRegistroBoleto(nuevo);
        });
        this.totalVuelta = 0;
    }

    public EstructuraRegistroBoletoÑandu(int numero, Servicio servicio, RegistroBoleto directo, RegistroBoleto planVina, RegistroBoleto local, RegistroBoleto escolarLocal, RegistroBoleto escolarDirecto, List<RegistroBoleto> registro) {
        this.numero = numero;
        this.servicio = servicio;
        this.directo = directo;
        this.planVina = planVina;
        this.local = local;
        this.escolarLocal = escolarLocal;
        this.escolarDirecto = escolarDirecto;
        this.registro = registro;
        this.totalVuelta = 0;
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

    public RegistroBoleto getDirecto() {
        return directo;
    }

    public void setDirecto(RegistroBoleto directo) {
        this.directo = directo;
    }

    public RegistroBoleto getPlanVina() {
        return planVina;
    }

    public void setPlanVina(RegistroBoleto planVina) {
        this.planVina = planVina;
    }

    public RegistroBoleto getLocal() {
        return local;
    }

    public void setLocal(RegistroBoleto local) {
        this.local = local;
    }

    public RegistroBoleto getEscolarLocal() {
        return escolarLocal;
    }

    public void setEscolarLocal(RegistroBoleto escolarLocal) {
        this.escolarLocal = escolarLocal;
    }

    public RegistroBoleto getEscolarDirecto() {
        return escolarDirecto;
    }

    public void setEscolarDirecto(RegistroBoleto escolarDirecto) {
        this.escolarDirecto = escolarDirecto;
    }

    public int getTotalVuelta() {
        return totalVuelta;
    }

    public void setTotalVuelta(int totalVuelta) {
        this.totalVuelta = totalVuelta;
    }

    public void addRegistroBoleto(RegistroBoleto registro) {
        if (this.registro == null) {
            this.registro = new ArrayList<>();
        }

        this.registro.add(registro);

        switch (registro.getRegistroBoletoIdBoleto().getBoletoOrden()) {
            case 1:
                this.directo = registro;
                break;
            case 2:
                this.planVina = registro;
                break;
            case 3:
                this.local = registro;
                break;
            case 4:
                this.escolarDirecto = registro;
                break;
            case 5:
                this.escolarLocal = registro;
                break;
        }
    }

    public List<RegistroBoleto> getRegistro() {
        return registro;
    }
    
    public List<RegistroBoleto> getUltimoRegistro() {
        List<RegistroBoleto> reg = new ArrayList<>();
        reg.add(directo);
        reg.add(planVina);
        reg.add(local);
        reg.add(escolarLocal);
        reg.add(escolarDirecto);
        
        return reg;
    }

    public void setRegistro(List<RegistroBoleto> registro) {
        this.registro = registro;
    }

}
