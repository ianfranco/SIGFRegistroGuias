/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.controllers;

import com.areatecnica.nanduappgb.models.EstructuraRegistroBoletoÑandu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ianfrancoconcha
 */
public class BoletosFactory {

    private static List<EstructuraRegistroBoletoÑandu> items;

    public BoletosFactory(List<EstructuraRegistroBoletoÑandu> items) {
        this.items = new ArrayList<>(items.subList(1, items.size()-1));
    }

    public static java.util.Collection<EstructuraRegistroBoletoÑandu> load() {
        return items;
    }

}
