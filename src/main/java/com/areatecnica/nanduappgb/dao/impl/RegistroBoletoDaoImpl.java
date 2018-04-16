/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao.impl;

import com.areatecnica.nanduappgb.dao.IRegistroBoletoDao;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class RegistroBoletoDaoImpl extends GenericDAOImpl<RegistroBoleto> implements IRegistroBoletoDao<RegistroBoleto> {

    public RegistroBoletoDaoImpl() {
        super(RegistroBoleto.class);
    }

    @Override
    public List<RegistroBoleto> findUltimaVueltaGuia(Guia guia) {
        try {
            return this.entityManager.createNamedQuery("RegistroBoleto.findUltimaVuelta").setParameter("registroBoletoIdGuia", guia).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

}
