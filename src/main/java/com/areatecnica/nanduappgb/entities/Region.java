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
@Table(name = "region", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r")
    , @NamedQuery(name = "Region.findByRegionId", query = "SELECT r FROM Region r WHERE r.regionId = :regionId")
    , @NamedQuery(name = "Region.findByRegionNumero", query = "SELECT r FROM Region r WHERE r.regionNumero = :regionNumero")
    , @NamedQuery(name = "Region.findByRegionNombre", query = "SELECT r FROM Region r WHERE r.regionNombre = :regionNombre")})
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "region_id")
    private Integer regionId;
    @Basic(optional = false)
    @Column(name = "region_numero")
    private int regionNumero;
    @Basic(optional = false)
    @Column(name = "region_nombre")
    private String regionNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadNegocioIdRegion")
    private List<UnidadNegocio> unidadNegocioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadIdRegion")
    private List<Ciudad> ciudadList;

    public Region() {
    }

    public Region(Integer regionId) {
        this.regionId = regionId;
    }

    public Region(Integer regionId, int regionNumero, String regionNombre) {
        this.regionId = regionId;
        this.regionNumero = regionNumero;
        this.regionNombre = regionNombre;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public int getRegionNumero() {
        return regionNumero;
    }

    public void setRegionNumero(int regionNumero) {
        this.regionNumero = regionNumero;
    }

    public String getRegionNombre() {
        return regionNombre;
    }

    public void setRegionNombre(String regionNombre) {
        this.regionNombre = regionNombre;
    }

    @XmlTransient
    public List<UnidadNegocio> getUnidadNegocioList() {
        return unidadNegocioList;
    }

    public void setUnidadNegocioList(List<UnidadNegocio> unidadNegocioList) {
        this.unidadNegocioList = unidadNegocioList;
    }

    @XmlTransient
    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (regionId != null ? regionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Region)) {
            return false;
        }
        Region other = (Region) object;
        if ((this.regionId == null && other.regionId != null) || (this.regionId != null && !this.regionId.equals(other.regionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.nanduappgb.entities.Region[ regionId=" + regionId + " ]";
    }
    
}
