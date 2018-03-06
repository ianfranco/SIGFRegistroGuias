/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao;

import com.areatecnica.nanduappgb.entities.GrupoServicio;
import com.areatecnica.nanduappgb.entities.Terminal;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IGrupoServicioDao<T> extends IGenericDAO<T> {
    
    public List<GrupoServicio> findByTerminal(Terminal terminal);
    
    public GrupoServicio findById(int id);
    
}
