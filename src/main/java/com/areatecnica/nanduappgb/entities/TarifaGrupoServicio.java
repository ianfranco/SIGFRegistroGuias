/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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
@Table(name = "tarifa_grupo_servicio", catalog = "sigf", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TarifaGrupoServicio.findAll", query = "SELECT t FROM TarifaGrupoServicio t")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioId", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioId = :tarifaGrupoServicioId")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioDirecto", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioDirecto = :tarifaGrupoServicioDirecto")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioPlanVina", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioPlanVina = :tarifaGrupoServicioPlanVina")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioLocal", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioLocal = :tarifaGrupoServicioLocal")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioEscolarDirecto", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioEscolarDirecto = :tarifaGrupoServicioEscolarDirecto")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioEscolarLocal", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioEscolarLocal = :tarifaGrupoServicioEscolarLocal")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioFechaDesde", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioFechaDesde = :tarifaGrupoServicioFechaDesde")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioFechaHasta", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioFechaHasta = :tarifaGrupoServicioFechaHasta")
    , @NamedQuery(name = "TarifaGrupoServicio.findByTarifaGrupoServicioActiva", query = "SELECT t FROM TarifaGrupoServicio t WHERE t.tarifaGrupoServicioActiva = :tarifaGrupoServicioActiva")})
public class TarifaGrupoServicio implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tarifa_grupo_servicio_id")
    private Integer tarifaGrupoServicioId;
    @Basic(optional = false)
    @Column(name = "tarifa_grupo_servicio_directo")
    private int tarifaGrupoServicioDirecto;
    @Basic(optional = false)
    @Column(name = "tarifa_grupo_servicio_plan_vina")
    private int tarifaGrupoServicioPlanVina;
    @Basic(optional = false)
    @Column(name = "tarifa_grupo_servicio_local")
    private int tarifaGrupoServicioLocal;
    @Basic(optional = false)
    @Column(name = "tarifa_grupo_servicio_escolar_directo")
    private int tarifaGrupoServicioEscolarDirecto;
    @Basic(optional = false)
    @Column(name = "tarifa_grupo_servicio_escolar_local")
    private int tarifaGrupoServicioEscolarLocal;
    @Basic(optional = false)
    @Column(name = "tarifa_grupo_servicio_fecha_desde")
    @Temporal(TemporalType.DATE)
    private Date tarifaGrupoServicioFechaDesde;
    @Basic(optional = false)
    @Column(name = "tarifa_grupo_servicio_fecha_hasta")
    @Temporal(TemporalType.DATE)
    private Date tarifaGrupoServicioFechaHasta;
    @Basic(optional = false)
    @Column(name = "tarifa_grupo_servicio_activa")
    private boolean tarifaGrupoServicioActiva;
    @JoinColumn(name = "tarifa_grupo_servicio_id_grupo", referencedColumnName = "grupo_servicio_id")
    @ManyToOne(optional = false)
    private GrupoServicio tarifaGrupoServicioIdGrupo;

    public TarifaGrupoServicio() {
    }

    public TarifaGrupoServicio(Integer tarifaGrupoServicioId) {
        this.tarifaGrupoServicioId = tarifaGrupoServicioId;
    }

    public TarifaGrupoServicio(Integer tarifaGrupoServicioId, int tarifaGrupoServicioDirecto, int tarifaGrupoServicioPlanVina, int tarifaGrupoServicioLocal, int tarifaGrupoServicioEscolarDirecto, int tarifaGrupoServicioEscolarLocal, Date tarifaGrupoServicioFechaDesde, Date tarifaGrupoServicioFechaHasta, boolean tarifaGrupoServicioActiva) {
        this.tarifaGrupoServicioId = tarifaGrupoServicioId;
        this.tarifaGrupoServicioDirecto = tarifaGrupoServicioDirecto;
        this.tarifaGrupoServicioPlanVina = tarifaGrupoServicioPlanVina;
        this.tarifaGrupoServicioLocal = tarifaGrupoServicioLocal;
        this.tarifaGrupoServicioEscolarDirecto = tarifaGrupoServicioEscolarDirecto;
        this.tarifaGrupoServicioEscolarLocal = tarifaGrupoServicioEscolarLocal;
        this.tarifaGrupoServicioFechaDesde = tarifaGrupoServicioFechaDesde;
        this.tarifaGrupoServicioFechaHasta = tarifaGrupoServicioFechaHasta;
        this.tarifaGrupoServicioActiva = tarifaGrupoServicioActiva;
    }

    public Integer getTarifaGrupoServicioId() {
        return tarifaGrupoServicioId;
    }

    public void setTarifaGrupoServicioId(Integer tarifaGrupoServicioId) {
        Integer oldTarifaGrupoServicioId = this.tarifaGrupoServicioId;
        this.tarifaGrupoServicioId = tarifaGrupoServicioId;
        changeSupport.firePropertyChange("tarifaGrupoServicioId", oldTarifaGrupoServicioId, tarifaGrupoServicioId);
    }

    public int getTarifaGrupoServicioDirecto() {
        return tarifaGrupoServicioDirecto;
    }

    public void setTarifaGrupoServicioDirecto(int tarifaGrupoServicioDirecto) {
        int oldTarifaGrupoServicioDirecto = this.tarifaGrupoServicioDirecto;
        this.tarifaGrupoServicioDirecto = tarifaGrupoServicioDirecto;
        changeSupport.firePropertyChange("tarifaGrupoServicioDirecto", oldTarifaGrupoServicioDirecto, tarifaGrupoServicioDirecto);
    }

    public int getTarifaGrupoServicioPlanVina() {
        return tarifaGrupoServicioPlanVina;
    }

    public void setTarifaGrupoServicioPlanVina(int tarifaGrupoServicioPlanVina) {
        int oldTarifaGrupoServicioPlanVina = this.tarifaGrupoServicioPlanVina;
        this.tarifaGrupoServicioPlanVina = tarifaGrupoServicioPlanVina;
        changeSupport.firePropertyChange("tarifaGrupoServicioPlanVina", oldTarifaGrupoServicioPlanVina, tarifaGrupoServicioPlanVina);
    }

    public int getTarifaGrupoServicioLocal() {
        return tarifaGrupoServicioLocal;
    }

    public void setTarifaGrupoServicioLocal(int tarifaGrupoServicioLocal) {
        int oldTarifaGrupoServicioLocal = this.tarifaGrupoServicioLocal;
        this.tarifaGrupoServicioLocal = tarifaGrupoServicioLocal;
        changeSupport.firePropertyChange("tarifaGrupoServicioLocal", oldTarifaGrupoServicioLocal, tarifaGrupoServicioLocal);
    }

    public int getTarifaGrupoServicioEscolarDirecto() {
        return tarifaGrupoServicioEscolarDirecto;
    }

    public void setTarifaGrupoServicioEscolarDirecto(int tarifaGrupoServicioEscolarDirecto) {
        int oldTarifaGrupoServicioEscolarDirecto = this.tarifaGrupoServicioEscolarDirecto;
        this.tarifaGrupoServicioEscolarDirecto = tarifaGrupoServicioEscolarDirecto;
        changeSupport.firePropertyChange("tarifaGrupoServicioEscolarDirecto", oldTarifaGrupoServicioEscolarDirecto, tarifaGrupoServicioEscolarDirecto);
    }

    public int getTarifaGrupoServicioEscolarLocal() {
        return tarifaGrupoServicioEscolarLocal;
    }

    public void setTarifaGrupoServicioEscolarLocal(int tarifaGrupoServicioEscolarLocal) {
        int oldTarifaGrupoServicioEscolarLocal = this.tarifaGrupoServicioEscolarLocal;
        this.tarifaGrupoServicioEscolarLocal = tarifaGrupoServicioEscolarLocal;
        changeSupport.firePropertyChange("tarifaGrupoServicioEscolarLocal", oldTarifaGrupoServicioEscolarLocal, tarifaGrupoServicioEscolarLocal);
    }

    public Date getTarifaGrupoServicioFechaDesde() {
        return tarifaGrupoServicioFechaDesde;
    }

    public void setTarifaGrupoServicioFechaDesde(Date tarifaGrupoServicioFechaDesde) {
        Date oldTarifaGrupoServicioFechaDesde = this.tarifaGrupoServicioFechaDesde;
        this.tarifaGrupoServicioFechaDesde = tarifaGrupoServicioFechaDesde;
        changeSupport.firePropertyChange("tarifaGrupoServicioFechaDesde", oldTarifaGrupoServicioFechaDesde, tarifaGrupoServicioFechaDesde);
    }

    public Date getTarifaGrupoServicioFechaHasta() {
        return tarifaGrupoServicioFechaHasta;
    }

    public void setTarifaGrupoServicioFechaHasta(Date tarifaGrupoServicioFechaHasta) {
        Date oldTarifaGrupoServicioFechaHasta = this.tarifaGrupoServicioFechaHasta;
        this.tarifaGrupoServicioFechaHasta = tarifaGrupoServicioFechaHasta;
        changeSupport.firePropertyChange("tarifaGrupoServicioFechaHasta", oldTarifaGrupoServicioFechaHasta, tarifaGrupoServicioFechaHasta);
    }

    public boolean getTarifaGrupoServicioActiva() {
        return tarifaGrupoServicioActiva;
    }

    public void setTarifaGrupoServicioActiva(boolean tarifaGrupoServicioActiva) {
        boolean oldTarifaGrupoServicioActiva = this.tarifaGrupoServicioActiva;
        this.tarifaGrupoServicioActiva = tarifaGrupoServicioActiva;
        changeSupport.firePropertyChange("tarifaGrupoServicioActiva", oldTarifaGrupoServicioActiva, tarifaGrupoServicioActiva);
    }

    public GrupoServicio getTarifaGrupoServicioIdGrupo() {
        return tarifaGrupoServicioIdGrupo;
    }

    public void setTarifaGrupoServicioIdGrupo(GrupoServicio tarifaGrupoServicioIdGrupo) {
        GrupoServicio oldTarifaGrupoServicioIdGrupo = this.tarifaGrupoServicioIdGrupo;
        this.tarifaGrupoServicioIdGrupo = tarifaGrupoServicioIdGrupo;
        changeSupport.firePropertyChange("tarifaGrupoServicioIdGrupo", oldTarifaGrupoServicioIdGrupo, tarifaGrupoServicioIdGrupo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tarifaGrupoServicioId != null ? tarifaGrupoServicioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarifaGrupoServicio)) {
            return false;
        }
        TarifaGrupoServicio other = (TarifaGrupoServicio) object;
        if ((this.tarifaGrupoServicioId == null && other.tarifaGrupoServicioId != null) || (this.tarifaGrupoServicioId != null && !this.tarifaGrupoServicioId.equals(other.tarifaGrupoServicioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.nanduappgb.entities.TarifaGrupoServicio[ tarifaGrupoServicioId=" + tarifaGrupoServicioId + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
