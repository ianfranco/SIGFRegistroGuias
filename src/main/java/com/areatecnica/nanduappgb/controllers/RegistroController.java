/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.controllers;

import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.models.RegistroBoletoTableModel;

/**
 *
 * @author ianfrancoconcha
 */
public abstract class RegistroController {

    private Guia guia;
    private RegistroBoletoTableModel boletoTableModel;
    
    public RegistroController() {
    }

    public Guia getGuia() {
        return guia;
    }

    public void reset() {

    }

}
