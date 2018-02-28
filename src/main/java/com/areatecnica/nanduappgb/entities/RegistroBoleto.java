/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "registro_boleto", catalog = "sigf", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroBoleto.findAll", query = "SELECT r FROM RegistroBoleto r")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoId", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoId = :registroBoletoId")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoDirecto", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoDirecto = :registroBoletoDirecto")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoPlanVina", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoPlanVina = :registroBoletoPlanVina")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoLocal", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoLocal = :registroBoletoLocal")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoEscolarDirecto", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoEscolarDirecto = :registroBoletoEscolarDirecto")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoEscolarLocal", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoEscolarLocal = :registroBoletoEscolarLocal")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoTotal", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoTotal = :registroBoletoTotal")})
public class RegistroBoleto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "registro_boleto_id")
    private Integer registroBoletoId;
    @Basic(optional = false)
    @Column(name = "registro_boleto_directo")
    private int registroBoletoDirecto;
    @Basic(optional = false)
    @Column(name = "registro_boleto_plan_vina")
    private int registroBoletoPlanVina;
    @Basic(optional = false)
    @Column(name = "registro_boleto_local")
    private int registroBoletoLocal;
    @Basic(optional = false)
    @Column(name = "registro_boleto_escolar_directo")
    private int registroBoletoEscolarDirecto;
    @Basic(optional = false)
    @Column(name = "registro_boleto_escolar_local")
    private int registroBoletoEscolarLocal;
    @Basic(optional = false)
    @Column(name = "registro_boleto_total")
    private int registroBoletoTotal;
    @JoinColumn(name = "registro_boleto_id_guia", referencedColumnName = "guia_id")
    @ManyToOne(optional = false)
    private Guia registroBoletoIdGuia;
    @JoinColumn(name = "registro_boleto_id_servicio", referencedColumnName = "servicio_id")
    @ManyToOne(optional = false)
    private Servicio registroBoletoIdServicio;

    public RegistroBoleto() {
    }

    public RegistroBoleto(Integer registroBoletoId) {
        this.registroBoletoId = registroBoletoId;
    }

    public RegistroBoleto(Integer registroBoletoId, int registroBoletoDirecto, int registroBoletoPlanVina, int registroBoletoLocal, int registroBoletoEscolarDirecto, int registroBoletoEscolarLocal, int registroBoletoTotal) {
        this.registroBoletoId = registroBoletoId;
        this.registroBoletoDirecto = registroBoletoDirecto;
        this.registroBoletoPlanVina = registroBoletoPlanVina;
        this.registroBoletoLocal = registroBoletoLocal;
        this.registroBoletoEscolarDirecto = registroBoletoEscolarDirecto;
        this.registroBoletoEscolarLocal = registroBoletoEscolarLocal;
        this.registroBoletoTotal = registroBoletoTotal;
    }

    public Integer getRegistroBoletoId() {
        return registroBoletoId;
    }

    public void setRegistroBoletoId(Integer registroBoletoId) {
        this.registroBoletoId = registroBoletoId;
    }

    public int getRegistroBoletoDirecto() {
        return registroBoletoDirecto;
    }

    public void setRegistroBoletoDirecto(int registroBoletoDirecto) {
        this.registroBoletoDirecto = registroBoletoDirecto;
    }

    public int getRegistroBoletoPlanVina() {
        return registroBoletoPlanVina;
    }

    public void setRegistroBoletoPlanVina(int registroBoletoPlanVina) {
        this.registroBoletoPlanVina = registroBoletoPlanVina;
    }

    public int getRegistroBoletoLocal() {
        return registroBoletoLocal;
    }

    public void setRegistroBoletoLocal(int registroBoletoLocal) {
        this.registroBoletoLocal = registroBoletoLocal;
    }

    public int getRegistroBoletoEscolarDirecto() {
        return registroBoletoEscolarDirecto;
    }

    public void setRegistroBoletoEscolarDirecto(int registroBoletoEscolarDirecto) {
        this.registroBoletoEscolarDirecto = registroBoletoEscolarDirecto;
    }

    public int getRegistroBoletoEscolarLocal() {
        return registroBoletoEscolarLocal;
    }

    public void setRegistroBoletoEscolarLocal(int registroBoletoEscolarLocal) {
        this.registroBoletoEscolarLocal = registroBoletoEscolarLocal;
    }

    public int getRegistroBoletoTotal() {
        return registroBoletoTotal;
    }

    public void setRegistroBoletoTotal(int registroBoletoTotal) {
        this.registroBoletoTotal = registroBoletoTotal;
    }

    public Guia getRegistroBoletoIdGuia() {
        return registroBoletoIdGuia;
    }

    public void setRegistroBoletoIdGuia(Guia registroBoletoIdGuia) {
        this.registroBoletoIdGuia = registroBoletoIdGuia;
    }

    public Servicio getRegistroBoletoIdServicio() {
        return registroBoletoIdServicio;
    }

    public void setRegistroBoletoIdServicio(Servicio registroBoletoIdServicio) {
        this.registroBoletoIdServicio = registroBoletoIdServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registroBoletoId != null ? registroBoletoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegistroBoleto)) {
            return false;
        }
        RegistroBoleto other = (RegistroBoleto) object;
        if ((this.registroBoletoId == null && other.registroBoletoId != null) || (this.registroBoletoId != null && !this.registroBoletoId.equals(other.registroBoletoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.nanduappgb.entities.RegistroBoleto[ registroBoletoId=" + registroBoletoId + " ]";
    }
    
}
