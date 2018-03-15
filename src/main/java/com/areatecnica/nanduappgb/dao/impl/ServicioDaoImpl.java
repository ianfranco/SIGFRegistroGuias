/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao.impl;

import com.areatecnica.nanduappgb.dao.IServicioDao;
import com.areatecnica.nanduappgb.entities.Servicio;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ServicioDaoImpl extends GenericDAOImpl<Servicio> implements IServicioDao<Servicio> {

    public ServicioDaoImpl() {
        super(Servicio.class);
    }

    @Override
    public List<Servicio> findAllBy() {
        try {
            return this.entityManager.createNamedQuery("Servicio.findAll").getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
