/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao.impl;

import com.areatecnica.nanduappgb.dao.ITarifaGrupoServicioDao;
import com.areatecnica.nanduappgb.entities.GrupoServicio;
import com.areatecnica.nanduappgb.entities.TarifaGrupoServicio;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class TarifaGrupoServicioDaoImpl extends GenericDAOImpl<TarifaGrupoServicio> implements ITarifaGrupoServicioDao<TarifaGrupoServicio> {

    @Override
    public List<TarifaGrupoServicio> findAllByGrupoServicio(GrupoServicio grupoServicio) {
        try {
            return this.entityManager.createNamedQuery("TarifaGrupoServicio.findAllByGrupo").setParameter("tarifaGrupoServicioIdGrupo", grupoServicio).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<TarifaGrupoServicio> findAllByCuenta() {
        try {
            return this.entityManager.createNamedQuery("TarifaGrupoServicio.findAll").getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public TarifaGrupoServicio findById(int id) {
        return (TarifaGrupoServicio) this.entityManager.createNamedQuery("TarifaGrupoServicio.findByTarifaGrupoServicioId").setParameter("tarifaGrupoSevicioId", id).getSingleResult();
    }
}
