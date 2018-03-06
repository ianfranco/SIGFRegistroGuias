/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao.impl;

import com.areatecnica.nanduappgb.dao.IGrupoServicioDao;
import com.areatecnica.nanduappgb.entities.GrupoServicio;

/**
 *
 * @author ianfrancoconcha
 */
public class GrupoServicioSolyMar implements IGrupoServicio {

    private GrupoServicio grupoServicioSolyMar;
    private IGrupoServicioDao dao;

    public GrupoServicioSolyMar() {
        this.dao = new GrupoServicioDaoImpl();
        this.grupoServicioSolyMar = this.dao.findById(2);
    }

    @Override
    public GrupoServicio getGrupoServicio() {
        return this.grupoServicioSolyMar;
    }

}
