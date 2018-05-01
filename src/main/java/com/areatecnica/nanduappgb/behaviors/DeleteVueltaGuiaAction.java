/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.dao.IVueltaGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.VueltaGuiaDaoImpl;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.VueltaGuia;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author ianfrancoconcha
 */
public class DeleteVueltaGuiaAction extends AbstractAction {

    private IVueltaGuiaDao dao;
    private VueltaGuia selected;

    public DeleteVueltaGuiaAction(VueltaGuia selected) {
        this.selected = selected;
        init();
    }

    private void init() {
        this.dao = new VueltaGuiaDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        save();
    }

    public boolean save() {
        if (this.selected != null) {

            int option = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar la Vuelta?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {

                this.dao.delete(selected);
                return true;
            }

        } else {
            //JOptionPane.showMessageDialog(null, "Debe seleccionar una guía", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

}
