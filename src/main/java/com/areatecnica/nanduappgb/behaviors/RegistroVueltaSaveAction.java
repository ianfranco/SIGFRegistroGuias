/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroVueltaController;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author ianfrancoconcha
 */
public class RegistroVueltaSaveAction extends AbstractAction {

    private IGuiaDao dao;
    private RegistroVueltaController controller;

    public RegistroVueltaSaveAction(RegistroVueltaController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        this.dao = new GuiaDaoImpl();
    }

    public void actionPerformed(ActionEvent e) {
        save();
    }

    public void save() {
        if (this.controller.getGuia() != null) {

            try {
                this.dao.update(this.controller.getGuia());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            VoucherRegistroVueltaPrintAction v = new VoucherRegistroVueltaPrintAction(this.controller);
            v.print();

            this.controller.reset();
        }
    }

}
