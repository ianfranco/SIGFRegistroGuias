/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroGuiaController;
import javax.swing.AbstractAction;

/**
 *
 * @author ianfrancoconcha
 */
public abstract class RegistroGuiaAbstractAction extends AbstractAction{
    
    private RegistroGuiaController controller;

    public RegistroGuiaAbstractAction(RegistroGuiaController controller) {
        this.controller = controller;
    }

    public RegistroGuiaController getController() {
        return controller;
    }
    
    
    
}
