/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.controllers;

import com.areatecnica.nanduappgb.entities.Guia;

/**
 *
 * @author ianfrancoconcha
 */
public abstract class RegistroController {

    private Guia guia;

    public RegistroController() {
    }

    public Guia getGuia() {
        return guia;
    }

    public void reset() {

    }

}
