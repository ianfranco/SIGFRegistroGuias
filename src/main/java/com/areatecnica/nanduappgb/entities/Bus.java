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
@Table(name = "bus", catalog = "sigf", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bus.findAll", query = "SELECT b FROM Bus b")
    , @NamedQuery(name = "Bus.findByBusId", query = "SELECT b FROM Bus b WHERE b.busId = :busId")
    , @NamedQuery(name = "Bus.findByBusNumero", query = "SELECT b FROM Bus b WHERE b.busNumero = :busNumero")
    , @NamedQuery(name = "Bus.findByBusPatente", query = "SELECT b FROM Bus b WHERE b.busPatente = :busPatente")
    , @NamedQuery(name = "Bus.findByBusTieneAdministrador", query = "SELECT b FROM Bus b WHERE b.busTieneAdministrador = :busTieneAdministrador")
    , @NamedQuery(name = "Bus.findByBusNumeroProcesoRecaudacion", query = "SELECT b FROM Bus b WHERE b.busIdProcesoRecaudacion = :busIdProcesoRecaudacion AND b.busNumero =:busNumero")
    , @NamedQuery(name = "Bus.findByBusAdministrador", query = "SELECT b FROM Bus b WHERE b.busAdministrador = :busAdministrador")
    , @NamedQuery(name = "Bus.findByBusAnio", query = "SELECT b FROM Bus b WHERE b.busAnio = :busAnio")
    , @NamedQuery(name = "Bus.findByBusFechaRevisionTecnica", query = "SELECT b FROM Bus b WHERE b.busFechaRevisionTecnica = :busFechaRevisionTecnica")
    , @NamedQuery(name = "Bus.findByBusNumeroMotor", query = "SELECT b FROM Bus b WHERE b.busNumeroMotor = :busNumeroMotor")
    , @NamedQuery(name = "Bus.findByBusNumeroChasis", query = "SELECT b FROM Bus b WHERE b.busNumeroChasis = :busNumeroChasis")
    , @NamedQuery(name = "Bus.findByBusCarroceria", query = "SELECT b FROM Bus b WHERE b.busCarroceria = :busCarroceria")
    , @NamedQuery(name = "Bus.findByBusTieneEgresoIndividual", query = "SELECT b FROM Bus b WHERE b.busTieneEgresoIndividual = :busTieneEgresoIndividual")
    , @NamedQuery(name = "Bus.findByBusTieneEgresoFlota", query = "SELECT b FROM Bus b WHERE b.busTieneEgresoFlota = :busTieneEgresoFlota")
    , @NamedQuery(name = "Bus.findByBusComparteServicio", query = "SELECT b FROM Bus b WHERE b.busComparteServicio = :busComparteServicio")
    , @NamedQuery(name = "Bus.findByBusActivo", query = "SELECT b FROM Bus b WHERE b.busActivo = :busActivo")
})
public class Bus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bus_id")
    private Integer busId;
    @Basic(optional = false)
    @Column(name = "bus_numero")
    private int busNumero;
    @Basic(optional = false)
    @Column(name = "bus_patente")
    private String busPatente;
    @Column(name = "bus_tiene_administrador")
    private Boolean busTieneAdministrador;
    @Column(name = "bus_administrador")
    private String busAdministrador;
    @Basic(optional = false)
    @Column(name = "bus_anio")
    private int busAnio;
    @Column(name = "bus_fecha_revision_tecnica")
    @Temporal(TemporalType.DATE)
    private Date busFechaRevisionTecnica;
    @Column(name = "bus_numero_motor")
    private String busNumeroMotor;
    @Column(name = "bus_numero_chasis")
    private String busNumeroChasis;
    @Column(name = "bus_carroceria")
    private String busCarroceria;
    @Column(name = "bus_tiene_egreso_individual")
    private Boolean busTieneEgresoIndividual;
    @Column(name = "bus_tiene_egreso_flota")
    private Boolean busTieneEgresoFlota;
    @Column(name = "bus_comparte_servicio")
    private Boolean busComparteServicio;
    @Column(name = "bus_activo")
    private Boolean busActivo;
    @JoinColumn(name = "bus_id_empresa", referencedColumnName = "empresa_id")
    @ManyToOne(optional = false)
    private Empresa busIdEmpresa;
    @JoinColumn(name = "bus_id_estado_bus", referencedColumnName = "estado_bus_id")
    @ManyToOne(optional = false)
    private EstadoBus busIdEstadoBus;
    @JoinColumn(name = "bus_id_flota", referencedColumnName = "flota_id")
    @ManyToOne(optional = false)
    private Flota busIdFlota;
    @JoinColumn(name = "bus_id_grupo_servicio", referencedColumnName = "grupo_servicio_id")
    @ManyToOne(optional = false)
    private GrupoServicio busIdGrupoServicio;
    @JoinColumn(name = "bus_id_modelo", referencedColumnName = "modelo_marca_bus_id")
    @ManyToOne(optional = false)
    private ModeloMarcaBus busIdModelo;
    @JoinColumn(name = "bus_id_proceso_recaudacion", referencedColumnName = "proceso_recaudacion_id")
    @ManyToOne(optional = false)
    private ProcesoRecaudacion busIdProcesoRecaudacion;
    @JoinColumn(name = "bus_id_terminal", referencedColumnName = "terminal_id")
    @ManyToOne(optional = false)
    private Terminal busIdTerminal;
    @JoinColumn(name = "bus_id_unidad_negocio", referencedColumnName = "unidad_negocio_id")
    @ManyToOne(optional = false)
    private UnidadNegocio busIdUnidadNegocio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "guiaIdBus")
    private List<Guia> guiaList;

    public Bus() {
    }

    public Bus(Integer busId) {
        this.busId = busId;
    }

    public Bus(Integer busId, int busNumero, String busPatente, int busAnio) {
        this.busId = busId;
        this.busNumero = busNumero;
        this.busPatente = busPatente;
        this.busAnio = busAnio;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public int getBusNumero() {
        return busNumero;
    }

    public void setBusNumero(int busNumero) {
        this.busNumero = busNumero;
    }

    public String getBusPatente() {
        return busPatente;
    }

    public void setBusPatente(String busPatente) {
        this.busPatente = busPatente;
    }

    public Boolean getBusTieneAdministrador() {
        return busTieneAdministrador;
    }

    public void setBusTieneAdministrador(Boolean busTieneAdministrador) {
        this.busTieneAdministrador = busTieneAdministrador;
    }

    public String getBusAdministrador() {
        return busAdministrador;
    }

    public void setBusAdministrador(String busAdministrador) {
        this.busAdministrador = busAdministrador;
    }

    public int getBusAnio() {
        return busAnio;
    }

    public void setBusAnio(int busAnio) {
        this.busAnio = busAnio;
    }

    public Date getBusFechaRevisionTecnica() {
        return busFechaRevisionTecnica;
    }

    public void setBusFechaRevisionTecnica(Date busFechaRevisionTecnica) {
        this.busFechaRevisionTecnica = busFechaRevisionTecnica;
    }

    public String getBusNumeroMotor() {
        return busNumeroMotor;
    }

    public void setBusNumeroMotor(String busNumeroMotor) {
        this.busNumeroMotor = busNumeroMotor;
    }

    public String getBusNumeroChasis() {
        return busNumeroChasis;
    }

    public void setBusNumeroChasis(String busNumeroChasis) {
        this.busNumeroChasis = busNumeroChasis;
    }

    public String getBusCarroceria() {
        return busCarroceria;
    }

    public void setBusCarroceria(String busCarroceria) {
        this.busCarroceria = busCarroceria;
    }

    public Boolean getBusTieneEgresoIndividual() {
        return busTieneEgresoIndividual;
    }

    public void setBusTieneEgresoIndividual(Boolean busTieneEgresoIndividual) {
        this.busTieneEgresoIndividual = busTieneEgresoIndividual;
    }

    public Boolean getBusTieneEgresoFlota() {
        return busTieneEgresoFlota;
    }

    public void setBusTieneEgresoFlota(Boolean busTieneEgresoFlota) {
        this.busTieneEgresoFlota = busTieneEgresoFlota;
    }

    public Boolean getBusComparteServicio() {
        return busComparteServicio;
    }

    public void setBusComparteServicio(Boolean busComparteServicio) {
        this.busComparteServicio = busComparteServicio;
    }

    public Boolean getBusActivo() {
        return busActivo;
    }

    public void setBusActivo(Boolean busActivo) {
        this.busActivo = busActivo;
    }

    public Empresa getBusIdEmpresa() {
        return busIdEmpresa;
    }

    public void setBusIdEmpresa(Empresa busIdEmpresa) {
        this.busIdEmpresa = busIdEmpresa;
    }

    public EstadoBus getBusIdEstadoBus() {
        return busIdEstadoBus;
    }

    public void setBusIdEstadoBus(EstadoBus busIdEstadoBus) {
        this.busIdEstadoBus = busIdEstadoBus;
    }

    public Flota getBusIdFlota() {
        return busIdFlota;
    }

    public void setBusIdFlota(Flota busIdFlota) {
        this.busIdFlota = busIdFlota;
    }

    public GrupoServicio getBusIdGrupoServicio() {
        return busIdGrupoServicio;
    }

    public void setBusIdGrupoServicio(GrupoServicio busIdGrupoServicio) {
        this.busIdGrupoServicio = busIdGrupoServicio;
    }

    public ModeloMarcaBus getBusIdModelo() {
        return busIdModelo;
    }

    public void setBusIdModelo(ModeloMarcaBus busIdModelo) {
        this.busIdModelo = busIdModelo;
    }

    public ProcesoRecaudacion getBusIdProcesoRecaudacion() {
        return busIdProcesoRecaudacion;
    }

    public void setBusIdProcesoRecaudacion(ProcesoRecaudacion busIdProcesoRecaudacion) {
        this.busIdProcesoRecaudacion = busIdProcesoRecaudacion;
    }

    public Terminal getBusIdTerminal() {
        return busIdTerminal;
    }

    public void setBusIdTerminal(Terminal busIdTerminal) {
        this.busIdTerminal = busIdTerminal;
    }

    public UnidadNegocio getBusIdUnidadNegocio() {
        return busIdUnidadNegocio;
    }

    public void setBusIdUnidadNegocio(UnidadNegocio busIdUnidadNegocio) {
        this.busIdUnidadNegocio = busIdUnidadNegocio;
    }

    @XmlTransient
    public List<Guia> getGuiaList() {
        return guiaList;
    }

    public void setGuiaList(List<Guia> guiaList) {
        this.guiaList = guiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (busId != null ? busId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus other = (Bus) object;
        if ((this.busId == null && other.busId != null) || (this.busId != null && !this.busId.equals(other.busId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.areatecnica.nanduappgb.entities.Bus[ busId=" + busId + " ]";
    }

}
