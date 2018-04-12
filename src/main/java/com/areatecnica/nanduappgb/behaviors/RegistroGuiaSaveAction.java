/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroController;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import java.awt.event.ActionEvent;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroGuiaSaveAction extends RegistroGuiaAbstractAction {

    private IGuiaDao dao;

    public RegistroGuiaSaveAction(RegistroController controller) {
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

    public void save() {
        if (this.getController().getGuia() != null) {

            if (this.getController().getGuia().getGuiaIdBus() != null && this.getController().getGuia().getGuiaIdTrabajador() != null) {
                this.dao.update(this.getController().getGuia());
                this.getController().reset();
            } else {

            }

        }
    }

}
