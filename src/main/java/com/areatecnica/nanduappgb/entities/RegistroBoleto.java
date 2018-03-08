/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoNumeroVuelta", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoNumeroVuelta = :registroBoletoNumeroVuelta")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoValor", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoValor = :registroBoletoValor")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoInicio", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoInicio = :registroBoletoInicio")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoTermino", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoTermino = :registroBoletoTermino")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoCantidad", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoCantidad = :registroBoletoCantidad")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoTotal", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoTotal = :registroBoletoTotal")
    , @NamedQuery(name = "RegistroBoleto.findByRegistroBoletoEsNuevo", query = "SELECT r FROM RegistroBoleto r WHERE r.registroBoletoEsNuevo = :registroBoletoEsNuevo")})
public class RegistroBoleto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "registro_boleto_id")
    private Integer registroBoletoId;
    @Basic(optional = false)
    @Column(name = "registro_boleto_numero_vuelta")
    private int registroBoletoNumeroVuelta;
    @Basic(optional = false)
    @Column(name = "registro_boleto_serie")
    private int registroBoletoSerie;
    @Basic(optional = false)
    @Column(name = "registro_boleto_valor")
    private int registroBoletoValor;
    @Basic(optional = false)
    @Column(name = "registro_boleto_inicio")
    private int registroBoletoInicio;
    @Basic(optional = false)
    @Column(name = "registro_boleto_termino")
    private int registroBoletoTermino;
    @Basic(optional = false)
    @Column(name = "registro_boleto_cantidad")
    private int registroBoletoCantidad;
    @Basic(optional = false)
    @Column(name = "registro_boleto_total")
    private int registroBoletoTotal;
    @Column(name = "registro_boleto_es_nuevo")
    private Boolean registroBoletoEsNuevo;
    @Column(name = "registro_boleto_observacion")
    private String registroBoletoObservacion;
    @Column(name = "registro_boleto_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registroBoletoFechaIngreso;
    @Transient
    private int diferencia;
    @JoinColumn(name = "registro_boleto_id_boleto", referencedColumnName = "boleto_id")
    @ManyToOne(optional = false)
    private Boleto registroBoletoIdBoleto;
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

    public RegistroBoleto(Integer registroBoletoId, int registroBoletoNumeroVuelta, int registroBoletoSerie, int registroBoletoValor, int registroBoletoInicio, int registroBoletoTermino, int registroBoletoCantidad, int registroBoletoTotal) {
        this.registroBoletoId = registroBoletoId;
        this.registroBoletoNumeroVuelta = registroBoletoNumeroVuelta;
        this.registroBoletoSerie = registroBoletoSerie;
        this.registroBoletoValor = registroBoletoValor;
        this.registroBoletoInicio = registroBoletoInicio;
        this.registroBoletoTermino = registroBoletoTermino;
        this.registroBoletoCantidad = registroBoletoCantidad;
        this.registroBoletoTotal = registroBoletoTotal;
    }

    public Integer getRegistroBoletoId() {
        return registroBoletoId;
    }

    public void setRegistroBoletoId(Integer registroBoletoId) {
        this.registroBoletoId = registroBoletoId;
    }

    public int getRegistroBoletoNumeroVuelta() {
        return registroBoletoNumeroVuelta;
    }

    public void setRegistroBoletoNumeroVuelta(int registroBoletoNumeroVuelta) {
        this.registroBoletoNumeroVuelta = registroBoletoNumeroVuelta;
    }

    public int getRegistroBoletoSerie() {
        return registroBoletoSerie;
    }

    public void setRegistroBoletoSerie(int registroBoletoSerie) {
        this.registroBoletoSerie = registroBoletoSerie;
    }
    
    public int getRegistroBoletoValor() {
        return registroBoletoValor;
    }

    public void setRegistroBoletoValor(int registroBoletoValor) {
        this.registroBoletoValor = registroBoletoValor;
    }

    public int getRegistroBoletoInicio() {
        return registroBoletoInicio;
    }

    public void setRegistroBoletoInicio(int registroBoletoInicio) {
        this.registroBoletoInicio = registroBoletoInicio;
    }

    public int getRegistroBoletoTermino() {
        return registroBoletoTermino;
    }

    public void setRegistroBoletoTermino(int registroBoletoTermino) {
        this.registroBoletoTermino = registroBoletoTermino;
    }

    public int getRegistroBoletoCantidad() {
        return registroBoletoCantidad;
    }

    public void setRegistroBoletoCantidad(int registroBoletoCantidad) {
        this.registroBoletoCantidad = registroBoletoCantidad;
    }

    public int getRegistroBoletoTotal() {
        return registroBoletoTotal;
    }

    public void setRegistroBoletoTotal(int registroBoletoTotal) {
        this.registroBoletoTotal = registroBoletoTotal;
    }

    public Boolean getRegistroBoletoEsNuevo() {
        return registroBoletoEsNuevo;
    }

    public void setRegistroBoletoEsNuevo(Boolean registroBoletoEsNuevo) {
        this.registroBoletoEsNuevo = registroBoletoEsNuevo;
    }
    
    public String getRegistroBoletoObservacion() {
        return registroBoletoObservacion;
    }

    public void setRegistroBoletoObservacion(String registroBoletoObservacion) {
        this.registroBoletoObservacion = registroBoletoObservacion;
    }

    public Date getRegistroBoletoFechaIngreso() {
        return registroBoletoFechaIngreso;
    }

    public void setRegistroBoletoFechaIngreso(Date registroBoletoFechaIngreso) {
        this.registroBoletoFechaIngreso = registroBoletoFechaIngreso;
    }

    public Boleto getRegistroBoletoIdBoleto() {
        return registroBoletoIdBoleto;
    }

    public void setRegistroBoletoIdBoleto(Boleto registroBoletoIdBoleto) {
        this.registroBoletoIdBoleto = registroBoletoIdBoleto;
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

    public int getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(int diferencia) {
        this.diferencia = diferencia;
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
