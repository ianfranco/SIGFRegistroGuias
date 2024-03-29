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
@Table(name = "comuna", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comuna.findAll", query = "SELECT c FROM Comuna c")
    , @NamedQuery(name = "Comuna.findByComunaId", query = "SELECT c FROM Comuna c WHERE c.comunaId = :comunaId")
    , @NamedQuery(name = "Comuna.findByComunaNombre", query = "SELECT c FROM Comuna c WHERE c.comunaNombre = :comunaNombre")})
public class Comuna implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "comuna_id")
    private Integer comunaId;
    @Basic(optional = false)
    @Column(name = "comuna_nombre")
    private String comunaNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terminalIdComuna")
    private List<Terminal> terminalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorIdComuna")
    private List<Trabajador> trabajadorList;
    @JoinColumn(name = "comuna_id_ciudad", referencedColumnName = "ciudad_id")
    @ManyToOne(optional = false)
    private Ciudad comunaIdCiudad;

    public Comuna() {
    }

    public Comuna(Integer comunaId) {
        this.comunaId = comunaId;
    }

    public Comuna(Integer comunaId, String comunaNombre) {
        this.comunaId = comunaId;
        this.comunaNombre = comunaNombre;
    }

    public Integer getComunaId() {
        return comunaId;
    }

    public void setComunaId(Integer comunaId) {
        this.comunaId = comunaId;
    }

    public String getComunaNombre() {
        return comunaNombre;
    }

    public void setComunaNombre(String comunaNombre) {
        this.comunaNombre = comunaNombre;
    }

    @XmlTransient
    public List<Terminal> getTerminalList() {
        return terminalList;
    }

    public void setTerminalList(List<Terminal> terminalList) {
        this.terminalList = terminalList;
    }

    @XmlTransient
    public List<Trabajador> getTrabajadorList() {
        return trabajadorList;
    }

    public void setTrabajadorList(List<Trabajador> trabajadorList) {
        this.trabajadorList = trabajadorList;
    }

    public Ciudad getComunaIdCiudad() {
        return comunaIdCiudad;
    }

    public void setComunaIdCiudad(Ciudad comunaIdCiudad) {
        this.comunaIdCiudad = comunaIdCiudad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comunaId != null ? comunaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comuna)) {
            return false;
        }
        Comuna other = (Comuna) object;
        if ((this.comunaId == null && other.comunaId != null) || (this.comunaId != null && !this.comunaId.equals(other.comunaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.nanduappgb.entities.Comuna[ comunaId=" + comunaId + " ]";
    }
    
}
