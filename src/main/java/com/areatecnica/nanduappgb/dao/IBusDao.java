/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao;



import com.areatecnica.nanduappgb.entities.Bus;
import com.areatecnica.nanduappgb.entities.EstadoBus;
import com.areatecnica.nanduappgb.entities.Flota;
import com.areatecnica.nanduappgb.entities.ProcesoRecaudacion;
import com.areatecnica.nanduappgb.entities.Terminal;
import com.areatecnica.nanduappgb.entities.UnidadNegocio;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IBusDao<T> extends IGenericDAO<T> {
    
    public int findMaxNumeroUnidad(UnidadNegocio unidadNegocio);
    
    public List<Bus> findByUnidad(UnidadNegocio unidadNegocio);
    
    public List<Bus> findByFlotaAndUnidad(Flota flota, UnidadNegocio unidadNegocio);
        
    public List<Bus> findByFlota(Flota flota);
    
    public List<Bus> findByProceso(ProcesoRecaudacion procesoRecaudacion);
    
    public List<Bus> findByTerminal(Terminal terminal);
    
    public Bus findDefaultBus(EstadoBus estadoBus);
    
    public Bus findByNumeroBus(int numero);
    
    public Bus findById(int id);
    
    public Bus findByNumeroBusProceso(int numero, ProcesoRecaudacion procesoRecaudacion);
}
