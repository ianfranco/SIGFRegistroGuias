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
@Table(name = "cuenta", catalog = "sigfdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuenta.findAll", query = "SELECT c FROM Cuenta c")
    , @NamedQuery(name = "Cuenta.findByCuentaId", query = "SELECT c FROM Cuenta c WHERE c.cuentaId = :cuentaId")
    , @NamedQuery(name = "Cuenta.findByCuentaAdministrador", query = "SELECT c FROM Cuenta c WHERE c.cuentaAdministrador = :cuentaAdministrador")
    , @NamedQuery(name = "Cuenta.findByCuentaRut", query = "SELECT c FROM Cuenta c WHERE c.cuentaRut = :cuentaRut")
    , @NamedQuery(name = "Cuenta.findByCuentaActiva", query = "SELECT c FROM Cuenta c WHERE c.cuentaActiva = :cuentaActiva")})
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cuenta_id")
    private Integer cuentaId;
    @Basic(optional = false)
    @Column(name = "cuenta_administrador")
    private String cuentaAdministrador;
    @Basic(optional = false)
    @Column(name = "cuenta_rut")
    private String cuentaRut;
    @Basic(optional = false)
    @Column(name = "cuenta_activa")
    private boolean cuentaActiva;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadNegocioIdCuenta")
    private List<UnidadNegocio> unidadNegocioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignacionFamiliarIdCuenta")
    private List<AsignacionFamiliar> asignacionFamiliarList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionSaludIdCuenta")
    private List<InstitucionSalud> institucionSaludList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flotaIdCuenta")
    private List<Flota> flotaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boletoIdCuenta")
    private List<Boleto> boletoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "procesoRecaudacionIdCuenta")
    private List<ProcesoRecaudacion> procesoRecaudacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sindicatoIdCuenta")
    private List<Sindicato> sindicatoList;
    @JoinColumn(name = "cuenta_id_tipo", referencedColumnName = "tipo_cuenta_id")
    @ManyToOne(optional = false)
    private TipoCuenta cuentaIdTipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionPrevisionIdCuenta")
    private List<InstitucionPrevision> institucionPrevisionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicioIdCuenta")
    private List<Servicio> servicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cajaCompensacionIdCuenta")
    private List<CajaCompensacion> cajaCompensacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "representanteLegalIdCuenta")
    private List<RepresentanteLegal> representanteLegalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grupoServicioIdCuenta")
    private List<GrupoServicio> grupoServicioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "terminalIdCuenta")
    private List<Terminal> terminalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trabajadorIdCuenta")
    private List<Trabajador> trabajadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "centroCostoIdCuenta")
    private List<CentroCosto> centroCostoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mutualIdCuenta")
    private List<Mutual> mutualList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "institucionApvIdCuenta")
    private List<InstitucionApv> institucionApvList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaIdCuenta")
    private List<Empresa> empresaList;

    public Cuenta() {
    }

    public Cuenta(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }

    public Cuenta(Integer cuentaId, String cuentaAdministrador, String cuentaRut, boolean cuentaActiva) {
        this.cuentaId = cuentaId;
        this.cuentaAdministrador = cuentaAdministrador;
        this.cuentaRut = cuentaRut;
        this.cuentaActiva = cuentaActiva;
    }

    public Integer getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getCuentaAdministrador() {
        return cuentaAdministrador;
    }

    public void setCuentaAdministrador(String cuentaAdministrador) {
        this.cuentaAdministrador = cuentaAdministrador;
    }

    public String getCuentaRut() {
        return cuentaRut;
    }

    public void setCuentaRut(String cuentaRut) {
        this.cuentaRut = cuentaRut;
    }

    public boolean getCuentaActiva() {
        return cuentaActiva;
    }

    public void setCuentaActiva(boolean cuentaActiva) {
        this.cuentaActiva = cuentaActiva;
    }

    @XmlTransient
    public List<UnidadNegocio> getUnidadNegocioList() {
        return unidadNegocioList;
    }

    public void setUnidadNegocioList(List<UnidadNegocio> unidadNegocioList) {
        this.unidadNegocioList = unidadNegocioList;
    }

    @XmlTransient
    public List<AsignacionFamiliar> getAsignacionFamiliarList() {
        return asignacionFamiliarList;
    }

    public void setAsignacionFamiliarList(List<AsignacionFamiliar> asignacionFamiliarList) {
        this.asignacionFamiliarList = asignacionFamiliarList;
    }

    @XmlTransient
    public List<InstitucionSalud> getInstitucionSaludList() {
        return institucionSaludList;
    }

    public void setInstitucionSaludList(List<InstitucionSalud> institucionSaludList) {
        this.institucionSaludList = institucionSaludList;
    }

    @XmlTransient
    public List<Flota> getFlotaList() {
        return flotaList;
    }

    public void setFlotaList(List<Flota> flotaList) {
        this.flotaList = flotaList;
    }

    @XmlTransient
    public List<Boleto> getBoletoList() {
        return boletoList;
    }

    public void setBoletoList(List<Boleto> boletoList) {
        this.boletoList = boletoList;
    }

    @XmlTransient
    public List<ProcesoRecaudacion> getProcesoRecaudacionList() {
        return procesoRecaudacionList;
    }

    public void setProcesoRecaudacionList(List<ProcesoRecaudacion> procesoRecaudacionList) {
        this.procesoRecaudacionList = procesoRecaudacionList;
    }

    @XmlTransient
    public List<Sindicato> getSindicatoList() {
        return sindicatoList;
    }

    public void setSindicatoList(List<Sindicato> sindicatoList) {
        this.sindicatoList = sindicatoList;
    }

    public TipoCuenta getCuentaIdTipo() {
        return cuentaIdTipo;
    }

    public void setCuentaIdTipo(TipoCuenta cuentaIdTipo) {
        this.cuentaIdTipo = cuentaIdTipo;
    }

    @XmlTransient
    public List<InstitucionPrevision> getInstitucionPrevisionList() {
        return institucionPrevisionList;
    }

    public void setInstitucionPrevisionList(List<InstitucionPrevision> institucionPrevisionList) {
        this.institucionPrevisionList = institucionPrevisionList;
    }

    @XmlTransient
    public List<Servicio> getServicioList() {
        return servicioList;
    }

    public void setServicioList(List<Servicio> servicioList) {
        this.servicioList = servicioList;
    }

    @XmlTransient
    public List<CajaCompensacion> getCajaCompensacionList() {
        return cajaCompensacionList;
    }

    public void setCajaCompensacionList(List<CajaCompensacion> cajaCompensacionList) {
        this.cajaCompensacionList = cajaCompensacionList;
    }

    @XmlTransient
    public List<RepresentanteLegal> getRepresentanteLegalList() {
        return representanteLegalList;
    }

    public void setRepresentanteLegalList(List<RepresentanteLegal> representanteLegalList) {
        this.representanteLegalList = representanteLegalList;
    }

    @XmlTransient
    public List<GrupoServicio> getGrupoServicioList() {
        return grupoServicioList;
    }

    public void setGrupoServicioList(List<GrupoServicio> grupoServicioList) {
        this.grupoServicioList = grupoServicioList;
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

    @XmlTransient
    public List<CentroCosto> getCentroCostoList() {
        return centroCostoList;
    }

    public void setCentroCostoList(List<CentroCosto> centroCostoList) {
        this.centroCostoList = centroCostoList;
    }

    @XmlTransient
    public List<Mutual> getMutualList() {
        return mutualList;
    }

    public void setMutualList(List<Mutual> mutualList) {
        this.mutualList = mutualList;
    }

    @XmlTransient
    public List<InstitucionApv> getInstitucionApvList() {
        return institucionApvList;
    }

    public void setInstitucionApvList(List<InstitucionApv> institucionApvList) {
        this.institucionApvList = institucionApvList;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuentaId != null ? cuentaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuenta)) {
            return false;
        }
        Cuenta other = (Cuenta) object;
        if ((this.cuentaId == null && other.cuentaId != null) || (this.cuentaId != null && !this.cuentaId.equals(other.cuentaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.nanduappgb.entities.Cuenta[ cuentaId=" + cuentaId + " ]";
    }
    
}
