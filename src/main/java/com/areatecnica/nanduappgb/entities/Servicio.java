/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "servicio", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s WHERE s.servicioId<>1")
    , @NamedQuery(name = "Servicio.findByServicioId", query = "SELECT s FROM Servicio s WHERE s.servicioId = :servicioId")
    , @NamedQuery(name = "Servicio.findByServicioNumeroServicio", query = "SELECT s FROM Servicio s WHERE s.servicioNumeroServicio = :servicioNumeroServicio")
    , @NamedQuery(name = "Servicio.findByServicioLongitud", query = "SELECT s FROM Servicio s WHERE s.servicioLongitud = :servicioLongitud")
    , @NamedQuery(name = "Servicio.findByServicioNombre", query = "SELECT s FROM Servicio s WHERE s.servicioNombre = :servicioNombre")
    , @NamedQuery(name = "Servicio.findByServicioOrigen", query = "SELECT s FROM Servicio s WHERE s.servicioOrigen = :servicioOrigen")
    , @NamedQuery(name = "Servicio.findByServicioDestino", query = "SELECT s FROM Servicio s WHERE s.servicioDestino = :servicioDestino")
    , @NamedQuery(name = "Servicio.findByServicioFolio", query = "SELECT s FROM Servicio s WHERE s.servicioFolio = :servicioFolio")
    , @NamedQuery(name = "Servicio.findByServicioTieneBusesCompartidos", query = "SELECT s FROM Servicio s WHERE s.servicioTieneBusesCompartidos = :servicioTieneBusesCompartidos")})
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "servicio_id")
    private Integer servicioId;
    @Basic(optional = false)
    @Column(name = "servicio_numero_servicio")
    private int servicioNumeroServicio;
    @Basic(optional = false)
    @Column(name = "servicio_longitud")
    private int servicioLongitud;
    @Basic(optional = false)
    @Column(name = "servicio_nombre")
    private String servicioNombre;
    @Basic(optional = false)
    @Column(name = "servicio_origen")
    private String servicioOrigen;
    @Basic(optional = false)
    @Column(name = "servicio_destino")
    private String servicioDestino;
    @Column(name = "servicio_folio")
    private String servicioFolio;
    @Column(name = "servicio_tiene_buses_compartidos")
    private Boolean servicioTieneBusesCompartidos;
    @JoinColumn(name = "servicio_id_cuenta", referencedColumnName = "cuenta_id")
    @ManyToOne(optional = false)
    private Cuenta servicioIdCuenta;
    @JoinColumn(name = "servicio_id_grupo_servicio", referencedColumnName = "grupo_servicio_id")
    @ManyToOne(optional = false)
    private GrupoServicio servicioIdGrupoServicio;
    @JoinColumn(name = "servicio_id_terminal", referencedColumnName = "terminal_id")
    @ManyToOne(optional = false)
    private Terminal servicioIdTerminal;
    @JoinColumn(name = "servicio_id_unidad", referencedColumnName = "unidad_negocio_id")
    @ManyToOne(optional = false)
    private UnidadNegocio servicioIdUnidad;

    public Servicio() {
    }

    public Servicio(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public Servicio(Integer servicioId, int servicioNumeroServicio, int servicioLongitud, String servicioNombre, String servicioOrigen, String servicioDestino) {
        this.servicioId = servicioId;
        this.servicioNumeroServicio = servicioNumeroServicio;
        this.servicioLongitud = servicioLongitud;
        this.servicioNombre = servicioNombre;
        this.servicioOrigen = servicioOrigen;
        this.servicioDestino = servicioDestino;
    }

    public Integer getServicioId() {
        return servicioId;
    }

    public void setServicioId(Integer servicioId) {
        this.servicioId = servicioId;
    }

    public int getServicioNumeroServicio() {
        return servicioNumeroServicio;
    }

    public void setServicioNumeroServicio(int servicioNumeroServicio) {
        this.servicioNumeroServicio = servicioNumeroServicio;
    }

    public int getServicioLongitud() {
        return servicioLongitud;
    }

    public void setServicioLongitud(int servicioLongitud) {
        this.servicioLongitud = servicioLongitud;
    }

    public String getServicioNombre() {
        return servicioNombre;
    }

    public void setServicioNombre(String servicioNombre) {
        this.servicioNombre = servicioNombre;
    }

    public String getServicioOrigen() {
        return servicioOrigen;
    }

    public void setServicioOrigen(String servicioOrigen) {
        this.servicioOrigen = servicioOrigen;
    }

    public String getServicioDestino() {
        return servicioDestino;
    }

    public void setServicioDestino(String servicioDestino) {
        this.servicioDestino = servicioDestino;
    }

    public String getServicioFolio() {
        return servicioFolio;
    }

    public void setServicioFolio(String servicioFolio) {
        this.servicioFolio = servicioFolio;
    }

    public Boolean getServicioTieneBusesCompartidos() {
        return servicioTieneBusesCompartidos;
    }

    public void setServicioTieneBusesCompartidos(Boolean servicioTieneBusesCompartidos) {
        this.servicioTieneBusesCompartidos = servicioTieneBusesCompartidos;
    }

    public Cuenta getServicioIdCuenta() {
        return servicioIdCuenta;
    }

    public void setServicioIdCuenta(Cuenta servicioIdCuenta) {
        this.servicioIdCuenta = servicioIdCuenta;
    }

    public GrupoServicio getServicioIdGrupoServicio() {
        return servicioIdGrupoServicio;
    }

    public void setServicioIdGrupoServicio(GrupoServicio servicioIdGrupoServicio) {
        this.servicioIdGrupoServicio = servicioIdGrupoServicio;
    }

    public Terminal getServicioIdTerminal() {
        return servicioIdTerminal;
    }

    public void setServicioIdTerminal(Terminal servicioIdTerminal) {
        this.servicioIdTerminal = servicioIdTerminal;
    }

    public UnidadNegocio getServicioIdUnidad() {
        return servicioIdUnidad;
    }

    public void setServicioIdUnidad(UnidadNegocio servicioIdUnidad) {
        this.servicioIdUnidad = servicioIdUnidad;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servicioId != null ? servicioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.servicioId == null && other.servicioId != null) || (this.servicioId != null && !this.servicioId.equals(other.servicioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(servicioNumeroServicio);
    }
    
}
