/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao.impl;

import com.areatecnica.nanduappgb.dao.IBusDao;
import com.areatecnica.nanduappgb.entities.Bus;
import com.areatecnica.nanduappgb.entities.EstadoBus;
import com.areatecnica.nanduappgb.entities.Flota;
import com.areatecnica.nanduappgb.entities.ProcesoRecaudacion;
import com.areatecnica.nanduappgb.entities.Terminal;
import com.areatecnica.nanduappgb.entities.UnidadNegocio;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author ianfr
 */
public class BusDaoImpl extends GenericDAOImpl<Bus> implements IBusDao<Bus> {

    public BusDaoImpl() {
        super(Bus.class);
    }

    @Override
    public int findMaxNumeroUnidad(UnidadNegocio unidadNegocio) {
        try {
            Bus bus = (Bus) this.entityManager.createNamedQuery("Bus.findMaxNumeroUnidad").setParameter("busIdUnidadNegocio", unidadNegocio).setMaxResults(1).getSingleResult();
            return bus.getBusNumero() + 1;
        } catch (NoResultException ne) {
            return 1;
        }
    }

    @Override
    public Bus findDefaultBus(EstadoBus estadoBus) {
        try {
            Bus bus = (Bus) this.entityManager.
                    createNamedQuery("Bus.findDefaultBus").
                    setParameter("busIdEstadoBus", estadoBus).
                    getSingleResult();
            return bus;
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findByUnidad(UnidadNegocio unidadNegocio) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByBusIdUnidadNegocio").setParameter("busIdUnidadNegocio", unidadNegocio).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findByFlotaAndUnidad(Flota flota, UnidadNegocio unidadNegocio) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByFlotaUnidadNegocio").setParameter("busIdUnidadNegocio", unidadNegocio).setParameter("busIdFlota", flota).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findByFlota(Flota flota) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByFlota").setParameter("busIdFlota", flota).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findByProceso(ProcesoRecaudacion procesoRecaudacion) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByProcesoRecaudacion").setParameter("idProcesoRecaudacion", procesoRecaudacion).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public List<Bus> findByTerminal(Terminal terminal) {
        try {
            return this.entityManager.createNamedQuery("Bus.findByTerminal").setParameter("busIdTerminal", terminal).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public Bus findByNumeroBus(int numero) {
        try {
            Bus bus = (Bus) this.entityManager.
                    createNamedQuery("Bus.findByBusNumero").
                    setParameter("busNumero", numero).
                    getSingleResult();
            return bus;
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public Bus findByNumeroBusProceso(int numero, ProcesoRecaudacion procesoRecaudacion) {
        try {
            Bus bus = (Bus) this.entityManager.
                    createNamedQuery("Bus.findByBusNumeroProcesoRecaudacion").
                    setParameter("busNumero", numero).
                    setParameter("busIdProcesoRecaudacion", procesoRecaudacion).
                    setMaxResults(1).
                    getSingleResult();
            return bus;
        } catch (NoResultException ne) {
            return null;
        }
    }

    @Override
    public Bus findById(int id) {
        try {
            return (Bus) this.entityManager.createNamedQuery("Bus.findByBusId").setParameter("busId", id).setMaxResults(1).getSingleResult();
        } catch (NoResultException ne) {
            return null;
        }
    }
}
