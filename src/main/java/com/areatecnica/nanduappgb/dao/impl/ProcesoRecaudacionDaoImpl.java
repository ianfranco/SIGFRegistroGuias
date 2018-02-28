/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao.impl;

import com.areatecnica.nanduappgb.dao.IProcesoRecaudacionDao;
import com.areatecnica.nanduappgb.entities.Cuenta;
import com.areatecnica.nanduappgb.entities.ProcesoRecaudacion;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class ProcesoRecaudacionDaoImpl extends GenericDAOImpl<ProcesoRecaudacion> implements IProcesoRecaudacionDao<ProcesoRecaudacion> {

    public ProcesoRecaudacionDaoImpl() {
        super(ProcesoRecaudacion.class);
    }

    @Override
    public List<ProcesoRecaudacion> findAllByCuenta(Cuenta cuenta) {
        try {
            return this.entityManager.createNamedQuery("ProcesoRecaudacion.findByProcesoRecaudacionIdCuenta").setParameter("procesoRecaudacionIdCuenta", cuenta).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public ProcesoRecaudacion findById(int id) {
        try {
            return (ProcesoRecaudacion) this.entityManager.createNamedQuery("ProcesoRecaudacion.findByProcesoRecaudacionId").setParameter("procesoRecaudacionId", id).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
