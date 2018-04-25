/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroBoletoController;
import com.areatecnica.nanduappgb.dao.ITrabajadorDao;
import com.areatecnica.nanduappgb.dao.impl.TrabajadorDaoImpl;
import com.areatecnica.nanduappgb.entities.Trabajador;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 *
 * @author ianfr
 */
public class FindConductorFocusLost extends FocusAdapter {

    private RegistroBoletoController controller;
    private final ITrabajadorDao dao;

    public FindConductorFocusLost(RegistroBoletoController controller) {
        this.controller = controller;
        this.dao = new TrabajadorDaoImpl();
    }

    @Override
    public void focusLost(FocusEvent e) {
        find();
    }

    public void find() {
        try {
            this.controller.getView().getConductorTextField().setBackground(Color.WHITE);
            String _codigo = this.controller.getView().getConductorTextField().getText();

            Trabajador _trabajador = this.dao.findByTrabajadorCodigo(Integer.valueOf(_codigo));

            if (_trabajador != null) {
//                if (this.controller.getGuia().getGuiaId() == null) {
                this.controller.getView().getConductorTextField().setBackground(Color.WHITE);
                this.controller.getGuia().setGuiaIdTrabajador(_trabajador);
                this.controller.getView().getNombreConductorTextField().setText(_trabajador.toString());
//                } else {
//                    if (_trabajador.getTrabajadorId() != this.controller.getGuia().getGuiaIdTrabajador().getTrabajadorId()) {
//                        int option = JOptionPane.showConfirmDialog(null, "¿Iniciar segundo turno?", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
//                        
//                    }
//                }
            } else {
                this.controller.getView().getConductorTextField().setBackground(Color.RED);
                this.controller.getView().getNombreConductorTextField().setText("No existe el N° Ingresado");
            }

        } catch (NullPointerException ee) {
            this.controller.getView().getConductorTextField().setBackground(Color.RED);
            ee.printStackTrace();
        } catch (NumberFormatException ex) {
            this.controller.getView().getConductorTextField().setBackground(Color.GREEN);
        }
    }

}
