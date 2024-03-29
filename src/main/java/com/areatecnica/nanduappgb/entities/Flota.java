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
@Table(name = "flota", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Flota.findAll", query = "SELECT f FROM Flota f")
    , @NamedQuery(name = "Flota.findByFlotaId", query = "SELECT f FROM Flota f WHERE f.flotaId = :flotaId")
    , @NamedQuery(name = "Flota.findByFlotaNombre", query = "SELECT f FROM Flota f WHERE f.flotaNombre = :flotaNombre")
    , @NamedQuery(name = "Flota.findByFlotaTieneEgresos", query = "SELECT f FROM Flota f WHERE f.flotaTieneEgresos = :flotaTieneEgresos")})
public class Flota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "flota_id")
    private Integer flotaId;
    @Basic(optional = false)
    @Column(name = "flota_nombre")
    private String flotaNombre;
    @Column(name = "flota_tiene_egresos")
    private Boolean flotaTieneEgresos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "busIdFlota")
    private List<Bus> busList;
    @JoinColumn(name = "flota_id_cuenta", referencedColumnName = "cuenta_id")
    @ManyToOne(optional = false)
    private Cuenta flotaIdCuenta;

    public Flota() {
    }

    public Flota(Integer flotaId) {
        this.flotaId = flotaId;
    }

    public Flota(Integer flotaId, String flotaNombre) {
        this.flotaId = flotaId;
        this.flotaNombre = flotaNombre;
    }

    public Integer getFlotaId() {
        return flotaId;
    }

    public void setFlotaId(Integer flotaId) {
        this.flotaId = flotaId;
    }

    public String getFlotaNombre() {
        return flotaNombre;
    }

    public void setFlotaNombre(String flotaNombre) {
        this.flotaNombre = flotaNombre;
    }

    public Boolean getFlotaTieneEgresos() {
        return flotaTieneEgresos;
    }

    public void setFlotaTieneEgresos(Boolean flotaTieneEgresos) {
        this.flotaTieneEgresos = flotaTieneEgresos;
    }

    @XmlTransient
    public List<Bus> getBusList() {
        return busList;
    }

    public void setBusList(List<Bus> busList) {
        this.busList = busList;
    }

    public Cuenta getFlotaIdCuenta() {
        return flotaIdCuenta;
    }

    public void setFlotaIdCuenta(Cuenta flotaIdCuenta) {
        this.flotaIdCuenta = flotaIdCuenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flotaId != null ? flotaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flota)) {
            return false;
        }
        Flota other = (Flota) object;
        if ((this.flotaId == null && other.flotaId != null) || (this.flotaId != null && !this.flotaId.equals(other.flotaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.nanduappgb.entities.Flota[ flotaId=" + flotaId + " ]";
    }
    
}
