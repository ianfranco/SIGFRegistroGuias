/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao.impl;

import com.areatecnica.nanduappgb.dao.ITarifaGrupoServicioDao;
import com.areatecnica.nanduappgb.entities.TarifaGrupoServicio;
import java.util.List;

/**
 *
 * @author ianfrancoconcha
 */
public class TarifaGrupoServicioSolyMar implements ITarifaGrupoServicio {

    private ITarifaGrupoServicioDao dao;
    private final static GrupoServicioSolyMar grupo = new GrupoServicioSolyMar();
    private List<TarifaGrupoServicio> tarifasSolyMar;

    public TarifaGrupoServicioSolyMar() {
        this.dao = new TarifaGrupoServicioDaoImpl();
        this.tarifasSolyMar = this.dao.findAllByGrupoServicio(grupo.getGrupoServicio());
        System.err.println("TAMAÑO DE TARIFAS"+this.tarifasSolyMar.size());
    }

    @Override
    public List<TarifaGrupoServicio> getTarifaGrupoServicio() {
        return tarifasSolyMar;
    }

    public static GrupoServicioSolyMar getGrupo() {
        return grupo;
    }

}
