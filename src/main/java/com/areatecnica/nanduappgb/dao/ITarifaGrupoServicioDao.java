/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao;

import com.areatecnica.nanduappgb.entities.GrupoServicio;
import com.areatecnica.nanduappgb.entities.TarifaGrupoServicio;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface ITarifaGrupoServicioDao<T> extends IGenericDAO<T> {

    public TarifaGrupoServicio findById(int id);
    
    public List<TarifaGrupoServicio> findAllByGrupoServicio(GrupoServicio grupoServicio);
    
    public List<TarifaGrupoServicio> findAllByCuenta();
}
