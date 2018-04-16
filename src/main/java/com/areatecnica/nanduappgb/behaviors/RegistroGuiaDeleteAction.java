/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.GuiaItemsController;
import com.areatecnica.nanduappgb.controllers.RegistroController;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroGuiaDeleteAction extends AbstractAction {

    private IGuiaDao dao;
    private GuiaItemsController controller;

    public RegistroGuiaDeleteAction(GuiaItemsController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        this.dao = new GuiaDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        save();
    }

    public boolean save() {
        if (this.controller.getSelected() != null) {

            int option = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar la guía?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {

                this.dao.delete(this.controller.getSelected());
                return true;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una guía", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

}
