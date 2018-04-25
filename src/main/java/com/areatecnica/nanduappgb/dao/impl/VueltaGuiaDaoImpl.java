/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao.impl;

import com.areatecnica.nanduappgb.dao.IVueltaGuiaDao;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.VueltaGuia;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfrancoconcha
 */
public class VueltaGuiaDaoImpl  extends GenericDAOImpl<VueltaGuia> implements IVueltaGuiaDao<VueltaGuia> {
    
    @Override
    public List<VueltaGuia> findByGuia(Guia guia){
        try {
            return this.entityManager.createNamedQuery("VueltaGuia.findByGuia").setParameter("vueltaGuiaIdGuia", guia).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
}
