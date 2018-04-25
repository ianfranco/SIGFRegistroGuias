/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroBoletoController;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import com.areatecnica.nanduappgb.entities.VueltaGuia;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author ianfrancoconcha
 */
public class SaveGuiaAction extends AbstractAction {

    private IGuiaDao dao;
    private RegistroBoletoController controller;

    public SaveGuiaAction(RegistroBoletoController controller) {
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

    public void save() {
        if (this.controller.getGuia() != null) {

            if (this.controller.getGuia().getGuiaIdBus() != null && this.controller.getGuia().getGuiaIdTrabajador() != null) {
                try {
                    this.controller.getGuia().setGuiaFecha(this.controller.getView().getFechaGuiaTextField().getDate());
                    //this.controller.getModel().removeTotal();

                    System.err.println("TOTAL DE VUELTAS ASIGNADAS A LA GUIA:" + this.controller.getGuia().getVueltaGuiaList().size());
                    List<RegistroBoleto> list = new ArrayList<>();

                    for (VueltaGuia r : this.controller.getGuia().getVueltaGuiaList()) {
                        r.setVueltaGuiaIdGuia(this.controller.getGuia());
                        System.err.println("N° DE VUELTA:" + r.getVueltaGuiaNumero());
                        System.err.println("Folio Vuelta:" + r.getVueltaGuiaIdGuia().getGuiaFolio());
                        System.err.println("Total de Registros:" + r.getRegistroBoletoList().size());
                        RegistroBoleto toDelete = null;
                        for (RegistroBoleto b : r.getRegistroBoletoList()) {
                            if (b.getRegistroBoletoIdBoleto() == null) {
                                toDelete = b;
                            } else {
                                System.err.println("NOMBRE DE BOLETO:" + b.getRegistroBoletoIdBoleto().getBoletoNombre());
                                list.add(b);
                            }

                        }
                        r.setRegistroBoletoList(list);
                        list = new ArrayList<>();
                    }

                    this.dao.update(this.controller.getGuia());
                    this.controller.reset();
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {

            }

        }
    }

}
