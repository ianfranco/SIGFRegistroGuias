/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao;

import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.VueltaGuia;
import java.util.List;

/**
 *
 * @author ianfr
 */
public interface IVueltaGuiaDao<T> extends IGenericDAO<T> {
    
    public List<VueltaGuia> findByGuia(Guia guia);
    
}
