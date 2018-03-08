/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroGuiaController;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import java.awt.event.ActionEvent;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroGuiaSaveAction extends RegistroGuiaAbstractAction {

    private IGuiaDao dao;

    public RegistroGuiaSaveAction(RegistroGuiaController controller) {
        super(controller);
        init();
    }

    private void init() {
        this.dao = new GuiaDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        save();
    }

    private void save() {
        if (this.getController().getGuia() != null) {
            this.dao.create(this.getController().getGuia());
        }
    }

}
