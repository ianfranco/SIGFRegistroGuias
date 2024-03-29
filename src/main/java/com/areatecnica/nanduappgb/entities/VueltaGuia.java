/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.entities;

import com.areatecnica.nanduappgb.models.EstructuraRegistroBoletoÑandu;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfr
 */
@Entity
@Table(name = "vuelta_guia", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VueltaGuia.findAll", query = "SELECT v FROM VueltaGuia v")
    , @NamedQuery(name = "VueltaGuia.findByGuia", query = "SELECT v FROM VueltaGuia v WHERE v.vueltaGuiaIdGuia = :vueltaGuiaIdGuia ORDER BY v.vueltaGuiaNumero ASC")
    , @NamedQuery(name = "VueltaGuia.findByVueltaGuiaId", query = "SELECT v FROM VueltaGuia v WHERE v.vueltaGuiaId = :vueltaGuiaId")
    , @NamedQuery(name = "VueltaGuia.findByVueltaGuiaTotal", query = "SELECT v FROM VueltaGuia v WHERE v.vueltaGuiaTotal = :vueltaGuiaTotal")
    , @NamedQuery(name = "VueltaGuia.findByVueltaGuiaNumero", query = "SELECT v FROM VueltaGuia v WHERE v.vueltaGuiaNumero = :vueltaGuiaNumero")})
public class VueltaGuia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vuelta_guia_id", nullable = false)
    private Integer vueltaGuiaId;
    @Basic(optional = false)
    @Column(name = "vuelta_guia_total", nullable = false)
    private int vueltaGuiaTotal;
    @Basic(optional = false)
    @Column(name = "vuelta_guia_numero", nullable = false)
    private int vueltaGuiaNumero;
    @JoinColumn(name = "vuelta_guia_id_guia", referencedColumnName = "guia_id", nullable = false)
    @ManyToOne(optional = false)
    private Guia vueltaGuiaIdGuia;
    @JoinColumn(name = "vuelta_guia_id_servicio", referencedColumnName = "servicio_id", nullable = false)
    @ManyToOne(optional = false)
    private Servicio vueltaGuiaIdServicio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "registroBoletoIdVueltaGuia")
    @OrderBy("registroBoletoIdBoleto")
    private List<RegistroBoleto> registroBoletoList;
    @Transient
    private EstructuraRegistroBoletoÑandu estructura;

    public VueltaGuia() {
    }

    public VueltaGuia(Integer vueltaGuiaId) {
        this.vueltaGuiaId = vueltaGuiaId;
    }

    public VueltaGuia(Integer vueltaGuiaId, int vueltaGuiaTotal, int vueltaGuiaNumero) {
        this.vueltaGuiaId = vueltaGuiaId;
        this.vueltaGuiaTotal = vueltaGuiaTotal;
        this.vueltaGuiaNumero = vueltaGuiaNumero;
    }

    public Integer getVueltaGuiaId() {
        return vueltaGuiaId;
    }

    public void setVueltaGuiaId(Integer vueltaGuiaId) {
        this.vueltaGuiaId = vueltaGuiaId;
    }

    public int getVueltaGuiaTotal() {
        return vueltaGuiaTotal;
    }

    public void setVueltaGuiaTotal(int vueltaGuiaTotal) {
        this.vueltaGuiaTotal = vueltaGuiaTotal;
    }

    public int getVueltaGuiaNumero() {
        return vueltaGuiaNumero;
    }

    public void setVueltaGuiaNumero(int vueltaGuiaNumero) {
        this.vueltaGuiaNumero = vueltaGuiaNumero;
    }

    public Guia getVueltaGuiaIdGuia() {
        return vueltaGuiaIdGuia;
    }

    public void setVueltaGuiaIdGuia(Guia vueltaGuiaIdGuia) {
        this.vueltaGuiaIdGuia = vueltaGuiaIdGuia;
    }

    public Servicio getVueltaGuiaIdServicio() {
        return vueltaGuiaIdServicio;
    }

    public void setVueltaGuiaIdServicio(Servicio vueltaGuiaIdServicio) {
        this.vueltaGuiaIdServicio = vueltaGuiaIdServicio;
    }

    @XmlTransient
    public List<RegistroBoleto> getRegistroBoletoList() {
        return registroBoletoList;
    }

    public void setRegistroBoletoList(List<RegistroBoleto> registroBoletoList) {
        this.registroBoletoList = registroBoletoList;
    }

    public EstructuraRegistroBoletoÑandu getEstructura() {
        if (this.estructura == null) {
            this.estructura = new EstructuraRegistroBoletoÑandu();

            for (RegistroBoleto r : this.registroBoletoList) {
                switch (r.getRegistroBoletoIdBoleto().getBoletoOrden()) {
                    case 1:
                        this.estructura.setDirecto(r);
                        break;
                    case 2:
                        this.estructura.setPlanVina(r);
                        break;
                    case 3:
                        this.estructura.setLocal(r);
                        break;
                    case 4:
                        this.estructura.setEscolarDirecto(r);
                        break;
                    case 5:
                        this.estructura.setEscolarLocal(r);
                        break;
                    default:
                }
            }
        }
        return estructura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vueltaGuiaId != null ? vueltaGuiaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VueltaGuia)) {
            return false;
        }
        VueltaGuia other = (VueltaGuia) object;
        if ((this.vueltaGuiaId == null && other.vueltaGuiaId != null) || (this.vueltaGuiaId != null && !this.vueltaGuiaId.equals(other.vueltaGuiaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(vueltaGuiaNumero);
    }

}
