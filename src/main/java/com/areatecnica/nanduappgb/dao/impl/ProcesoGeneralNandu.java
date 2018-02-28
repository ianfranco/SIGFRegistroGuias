/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.dao.impl;

import com.areatecnica.nanduappgb.dao.IProcesoRecaudacionDao;
import com.areatecnica.nanduappgb.entities.ProcesoRecaudacion;



/**
 *
 * @author ianfrancoconcha
 */
public class ProcesoGeneralNandu implements IProcesoGeneral{
    
    private IProcesoRecaudacionDao dao; 
    private ProcesoRecaudacion proceso; 

    public ProcesoGeneralNandu() {
        init();
    }

    private void init(){
        this.dao = new ProcesoRecaudacionDaoImpl();
        this.proceso = this.dao.findById(2);
    }
    
    @Override
    public ProcesoRecaudacion getProceso() {
        return proceso;
    }

    public void setProceso(ProcesoRecaudacion proceso) {
        this.proceso = proceso;
    }
    
    
    
}
