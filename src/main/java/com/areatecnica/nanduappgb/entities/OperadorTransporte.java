/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ianfrancoconcha
 */
@Entity
@Table(name = "operador_transporte", catalog = "sigf", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OperadorTransporte.findAll", query = "SELECT o FROM OperadorTransporte o")
    , @NamedQuery(name = "OperadorTransporte.findByOperadorTransporteId", query = "SELECT o FROM OperadorTransporte o WHERE o.operadorTransporteId = :operadorTransporteId")
    , @NamedQuery(name = "OperadorTransporte.findByOperadorTransporteRut", query = "SELECT o FROM OperadorTransporte o WHERE o.operadorTransporteRut = :operadorTransporteRut")
    , @NamedQuery(name = "OperadorTransporte.findByOperadorTransporteNombre", query = "SELECT o FROM OperadorTransporte o WHERE o.operadorTransporteNombre = :operadorTransporteNombre")
    , @NamedQuery(name = "OperadorTransporte.findByOperadorTransporteFechaIngreso", query = "SELECT o FROM OperadorTransporte o WHERE o.operadorTransporteFechaIngreso = :operadorTransporteFechaIngreso")
    , @NamedQuery(name = "OperadorTransporte.findByOperadorTransporteRecaudaBoleto", query = "SELECT o FROM OperadorTransporte o WHERE o.operadorTransporteRecaudaBoleto = :operadorTransporteRecaudaBoleto")
    , @NamedQuery(name = "OperadorTransporte.findByOperadorTransporteRecaudaCombustible", query = "SELECT o FROM OperadorTransporte o WHERE o.operadorTransporteRecaudaCombustible = :operadorTransporteRecaudaCombustible")
    , @NamedQuery(name = "OperadorTransporte.findByOperadorTransporteRecaudaGuias", query = "SELECT o FROM OperadorTransporte o WHERE o.operadorTransporteRecaudaGuias = :operadorTransporteRecaudaGuias")
    , @NamedQuery(name = "OperadorTransporte.findByOperadorTransporteRecaudaMinuto", query = "SELECT o FROM OperadorTransporte o WHERE o.operadorTransporteRecaudaMinuto = :operadorTransporteRecaudaMinuto")})
public class OperadorTransporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "operador_transporte_id")
    private Integer operadorTransporteId;
    @Basic(optional = false)
    @Column(name = "operador_transporte_rut")
    private String operadorTransporteRut;
    @Basic(optional = false)
    @Column(name = "operador_transporte_nombre")
    private String operadorTransporteNombre;
    @Basic(optional = false)
    @Column(name = "operador_transporte_fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date operadorTransporteFechaIngreso;
    @Basic(optional = false)
    @Column(name = "operador_transporte_recauda_boleto")
    private boolean operadorTransporteRecaudaBoleto;
    @Basic(optional = false)
    @Column(name = "operador_transporte_recauda_combustible")
    private boolean operadorTransporteRecaudaCombustible;
    @Basic(optional = false)
    @Column(name = "operador_transporte_recauda_guias")
    private boolean operadorTransporteRecaudaGuias;
    @Basic(optional = false)
    @Column(name = "operador_transporte_recauda_minuto")
    private boolean operadorTransporteRecaudaMinuto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadNegocioIdOperadorTransporte")
    private List<UnidadNegocio> unidadNegocioList;
    @JoinColumn(name = "operador_transporte_id_representante", referencedColumnName = "representante_legal_id")
    @ManyToOne(optional = false)
    private RepresentanteLegal operadorTransporteIdRepresentante;

    public OperadorTransporte() {
    }

    public OperadorTransporte(Integer operadorTransporteId) {
        this.operadorTransporteId = operadorTransporteId;
    }

    public OperadorTransporte(Integer operadorTransporteId, String operadorTransporteRut, String operadorTransporteNombre, Date operadorTransporteFechaIngreso, boolean operadorTransporteRecaudaBoleto, boolean operadorTransporteRecaudaCombustible, boolean operadorTransporteRecaudaGuias, boolean operadorTransporteRecaudaMinuto) {
        this.operadorTransporteId = operadorTransporteId;
        this.operadorTransporteRut = operadorTransporteRut;
        this.operadorTransporteNombre = operadorTransporteNombre;
        this.operadorTransporteFechaIngreso = operadorTransporteFechaIngreso;
        this.operadorTransporteRecaudaBoleto = operadorTransporteRecaudaBoleto;
        this.operadorTransporteRecaudaCombustible = operadorTransporteRecaudaCombustible;
        this.operadorTransporteRecaudaGuias = operadorTransporteRecaudaGuias;
        this.operadorTransporteRecaudaMinuto = operadorTransporteRecaudaMinuto;
    }

    public Integer getOperadorTransporteId() {
        return operadorTransporteId;
    }

    public void setOperadorTransporteId(Integer operadorTransporteId) {
        this.operadorTransporteId = operadorTransporteId;
    }

    public String getOperadorTransporteRut() {
        return operadorTransporteRut;
    }

    public void setOperadorTransporteRut(String operadorTransporteRut) {
        this.operadorTransporteRut = operadorTransporteRut;
    }

    public String getOperadorTransporteNombre() {
        return operadorTransporteNombre;
    }

    public void setOperadorTransporteNombre(String operadorTransporteNombre) {
        this.operadorTransporteNombre = operadorTransporteNombre;
    }

    public Date getOperadorTransporteFechaIngreso() {
        return operadorTransporteFechaIngreso;
    }

    public void setOperadorTransporteFechaIngreso(Date operadorTransporteFechaIngreso) {
        this.operadorTransporteFechaIngreso = operadorTransporteFechaIngreso;
    }

    public boolean getOperadorTransporteRecaudaBoleto() {
        return operadorTransporteRecaudaBoleto;
    }

    public void setOperadorTransporteRecaudaBoleto(boolean operadorTransporteRecaudaBoleto) {
        this.operadorTransporteRecaudaBoleto = operadorTransporteRecaudaBoleto;
    }

    public boolean getOperadorTransporteRecaudaCombustible() {
        return operadorTransporteRecaudaCombustible;
    }

    public void setOperadorTransporteRecaudaCombustible(boolean operadorTransporteRecaudaCombustible) {
        this.operadorTransporteRecaudaCombustible = operadorTransporteRecaudaCombustible;
    }

    public boolean getOperadorTransporteRecaudaGuias() {
        return operadorTransporteRecaudaGuias;
    }

    public void setOperadorTransporteRecaudaGuias(boolean operadorTransporteRecaudaGuias) {
        this.operadorTransporteRecaudaGuias = operadorTransporteRecaudaGuias;
    }

    public boolean getOperadorTransporteRecaudaMinuto() {
        return operadorTransporteRecaudaMinuto;
    }

    public void setOperadorTransporteRecaudaMinuto(boolean operadorTransporteRecaudaMinuto) {
        this.operadorTransporteRecaudaMinuto = operadorTransporteRecaudaMinuto;
    }

    @XmlTransient
    public List<UnidadNegocio> getUnidadNegocioList() {
        return unidadNegocioList;
    }

    public void setUnidadNegocioList(List<UnidadNegocio> unidadNegocioList) {
        this.unidadNegocioList = unidadNegocioList;
    }

    public RepresentanteLegal getOperadorTransporteIdRepresentante() {
        return operadorTransporteIdRepresentante;
    }

    public void setOperadorTransporteIdRepresentante(RepresentanteLegal operadorTransporteIdRepresentante) {
        this.operadorTransporteIdRepresentante = operadorTransporteIdRepresentante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (operadorTransporteId != null ? operadorTransporteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OperadorTransporte)) {
            return false;
        }
        OperadorTransporte other = (OperadorTransporte) object;
        if ((this.operadorTransporteId == null && other.operadorTransporteId != null) || (this.operadorTransporteId != null && !this.operadorTransporteId.equals(other.operadorTransporteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.nanduappgb.entities.OperadorTransporte[ operadorTransporteId=" + operadorTransporteId + " ]";
    }
    
}
