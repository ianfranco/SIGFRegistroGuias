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
public class EstructuraRegistroBoletoÑandu {

    
    private int numero;
    private Servicio servicio;
    private RegistroBoleto directo;
    private RegistroBoleto planVina;
    private RegistroBoleto local;
    private RegistroBoleto escolarLocal;
    private RegistroBoleto escolarDirecto;
    
    private List<RegistroBoleto> registro;

    public EstructuraRegistroBoletoÑandu() {
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

    public void setEscolaLocal(RegistroBoleto escolarLocal) {
        this.escolarLocal = escolarLocal;
    }

    public RegistroBoleto getEscolarDirecto() {
        return escolarDirecto;
    }

    public void setEscolarDirecto(RegistroBoleto escolarDirecto) {
        this.escolarDirecto = escolarDirecto;
    }

    

    public void addRegistroBoleto(RegistroBoleto registro) {
        if (this.registro == null) {
            this.registro = new ArrayList<>();
        }
        
        this.registro.add(registro);

        switch (registro.getRegistroBoletoIdBoleto().getBoletoOrden()) {
            case 1:
                this.directo = registro;
                System.err.println("Directo:" + directo);
                break;
            case 2:
                this.planVina = registro;
                System.err.println("Plan:" + planVina);
                break;
            case 3:
                this.local = registro;
                System.err.println("Local:" + local);
                break;
            case 4:
                this.escolarDirecto = registro;
                System.err.println("Esc.1:" + escolarDirecto);
                break;
            case 5:
                this.escolarLocal = registro;
                System.err.println("Esc.2:" + escolarLocal);
                break;
        }
    }

    public List<RegistroBoleto> getRegistro() {
        return registro;
    }

}
