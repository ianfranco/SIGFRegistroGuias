/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao.impl;


import com.areatecnica.nanduappgb.dao.IGrupoServicioDao;
import com.areatecnica.nanduappgb.entities.GrupoServicio;
import com.areatecnica.nanduappgb.entities.Terminal;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class GrupoServicioDaoImpl extends GenericDAOImpl<GrupoServicio> implements IGrupoServicioDao<GrupoServicio> {

    @Override
    public List<GrupoServicio> findByTerminal(Terminal terminal) {
        try {
            return this.entityManager.createNamedQuery("GrupoServicio.findByTerminal").setParameter("grupoServicioIdTerminal", terminal).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public GrupoServicio findById(int id) {
        return (GrupoServicio)this.entityManager.createNamedQuery("GrupoServicio.findByGrupoServicioId").setParameter("grupoServicioId", id).getSingleResult();
    }

}
