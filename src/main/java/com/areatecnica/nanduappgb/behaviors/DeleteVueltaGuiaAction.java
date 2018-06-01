/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.IVueltaGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
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
    private IGuiaDao guiaDao; 
    private VueltaGuia selected;
    private Guia guia;

    public DeleteVueltaGuiaAction(VueltaGuia selected) {
        this.selected = selected;
        this.guia = this.selected.getVueltaGuiaIdGuia();
        this.guiaDao = new GuiaDaoImpl();
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

            if (this.guia.getVueltaGuiaList().contains(selected)) {
                if (this.guia.getVueltaGuiaList().size() > 1) {
                    int option = JOptionPane.showConfirmDialog(null, "¿Realmente desea eliminar la Vuelta?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if (option == JOptionPane.YES_OPTION) {

                        try {
                            System.err.println("CANTIDAD DE VUELTAS ANTES DE BORRAR:"+this.guia.getVueltaGuiaList().size());
                            this.guia.getVueltaGuiaList().remove(selected);
                            int i = 1;
                            for (VueltaGuia v : this.guia.getVueltaGuiaList()) {
                                v.setVueltaGuiaNumero(i);
                                i++;
                                guia.setGuiaTotalIngreso(v.getVueltaGuiaTotal()+guia.getGuiaTotalIngreso());
                            }

                            System.err.println("CANTIDAD DE VUELTAS DESPUES DE BORRAR:"+this.guia.getVueltaGuiaList().size());
                            this.dao.delete(this.selected);
                            //this.guiaDao.update(this.guia);

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        this.guia = null;
                        this.selected = null;
                        return true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No puede borrar la primera vuelta. Intente actualizar series de boletos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "La vuelta no se ha registrado aun", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            //JOptionPane.showMessageDialog(null, "Debe seleccionar una guía", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

}
