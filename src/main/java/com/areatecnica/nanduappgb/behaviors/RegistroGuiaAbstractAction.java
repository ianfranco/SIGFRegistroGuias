/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroController;
import javax.swing.AbstractAction;

/**
 *
 * @author ianfrancoconcha
 */
public abstract class RegistroGuiaAbstractAction extends AbstractAction{
    
    private RegistroController controller;

    public RegistroGuiaAbstractAction(RegistroController controller) {
        this.controller = controller;
    }

    public RegistroController getController() {
        return controller;
    }
    
    
    
}
