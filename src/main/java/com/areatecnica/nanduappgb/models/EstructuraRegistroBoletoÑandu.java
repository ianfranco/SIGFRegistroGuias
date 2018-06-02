/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;

import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import com.areatecnica.nanduappgb.entities.Servicio;
import com.areatecnica.nanduappgb.entities.VueltaGuia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ianfrancoconcha
 */
public class EstructuraRegistroBoletoÑandu {

    private int numero;
    private VueltaGuia vueltaGuia;
    private RegistroBoleto directo;
    private RegistroBoleto planVina;
    private RegistroBoleto local;
    private RegistroBoleto escolarLocal;
    private RegistroBoleto escolarDirecto;
    private int totalVuelta;

    public EstructuraRegistroBoletoÑandu() {
        this.totalVuelta = 0;
    }

    public EstructuraRegistroBoletoÑandu(Boolean defecto) {
        this.totalVuelta = 0;
        this.directo = new RegistroBoleto();
        this.planVina = new RegistroBoleto();
        this.local = new RegistroBoleto();
        this.escolarDirecto = new RegistroBoleto();
        this.escolarLocal = new RegistroBoleto();
    }

    public EstructuraRegistroBoletoÑandu(VueltaGuia vueltaGuia) {
        this.vueltaGuia = vueltaGuia;
        for (RegistroBoleto r : this.vueltaGuia.getRegistroBoletoList()) {
            if (r.getRegistroBoletoIdBoleto() != null) {
                switch (r.getRegistroBoletoIdBoleto().getBoletoOrden()) {
                    case 1:
                        setDirecto(r);
                        break;
                    case 2:
                        setPlanVina(r);
                        break;
                    case 3:
                        setLocal(r);
                        break;
                    case 4:
                        setEscolarDirecto(r);
                        break;
                    case 5:
                        setEscolarLocal(r);
                        break;
                }
            }
        }
        this.numero = this.vueltaGuia.getVueltaGuiaNumero();
    }

    public EstructuraRegistroBoletoÑandu(int numero, Servicio servicio, RegistroBoleto directo, RegistroBoleto planVina, RegistroBoleto local, RegistroBoleto escolarLocal, RegistroBoleto escolarDirecto, List<RegistroBoleto> registro) {
        this.numero = numero;
        this.directo = directo;
        this.planVina = planVina;
        this.local = local;
        this.escolarLocal = escolarLocal;
        this.escolarDirecto = escolarDirecto;
        this.totalVuelta = 0;
    }

    public void setVueltaGuia(VueltaGuia vueltaGuia) {
        this.vueltaGuia = vueltaGuia;
    }

    public VueltaGuia getVueltaGuia() {
        return vueltaGuia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public List<RegistroBoleto> getUltimoRegistro() {
        List<RegistroBoleto> reg = new ArrayList<>();
        reg.add(directo);
        reg.add(planVina);
        reg.add(local);
        reg.add(escolarLocal);
        reg.add(escolarDirecto);

        return reg;
    }

}
