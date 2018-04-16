/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao;


import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import java.util.List;

/**
 *
 * @author ianfr
 * @param <T>
 */
public interface IRegistroBoletoDao<T> extends IGenericDAO<T> {
    
    public List<RegistroBoleto> findUltimaVueltaGuia(Guia guia);
    
}
