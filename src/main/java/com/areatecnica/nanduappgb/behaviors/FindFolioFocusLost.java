/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroGuiaController;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.models.RegistroBoletoTableModel;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 *
 * @author ianfrancoconcha
 */
public class FindFolioFocusLost extends FocusAdapter {

    private final RegistroGuiaController controller;
    private final IGuiaDao dao;
    private int folio;

    public FindFolioFocusLost(RegistroGuiaController controller) {
        this.controller = controller;
        this.dao = new GuiaDaoImpl();
    }

    @Override
    public void focusLost(FocusEvent e) {
        String _folio = this.controller.getView().getFolioTextField().getText();

        if (!_folio.equals("")) {
            try {
                this.folio = Integer.parseInt(_folio);

                this.controller.getView().getFolioTextField().setBackground(Color.WHITE);
                RegistroBoletoTableModel model = null;
                Guia _guia = this.dao.findByFolio(folio);

                if (_guia != null) {
                    this.controller.setGuia(_guia);
                    
                    model = new RegistroBoletoTableModel(_guia);
                    this.controller.getView().getObservacionTextField().setText("Registro Vuelta Nº "+model.getNumeroVuelta());
                    this.controller.setModel(model);
                    this.controller.getView().getEstadoBoletoTextField().setText("");
                    this.controller.setFlag(Boolean.FALSE);
                    this.controller.getView().getBusTextField().setText(String.valueOf(_guia.getGuiaIdBus().getBusNumero()));
                    this.controller.getView().getPpuTextField().setText(_guia.getGuiaIdBus().getBusPatente());
                    this.controller.getView().getConductorTextField().setText(String.valueOf(_guia.getGuiaIdTrabajador().getTrabajadorCodigo()));
                    this.controller.getView().getNombreConductorTextField().setText(_guia.getGuiaIdTrabajador().toString());
                    this.controller.getView().getServicioTextField().requestFocus();
                    this.controller.getView().getEstadoBoletoTextField().setBackground(Color.WHITE);
                }else{
                    this.controller.getGuia().setGuiaFolio(folio);
                    this.controller.getView().getObservacionTextField().setText("Nueva Guía");
                    model = new RegistroBoletoTableModel();
                    
                    this.controller.setModel(model);
                    this.controller.setFlag(Boolean.TRUE);
                }

            } catch (NumberFormatException ex) {
                this.controller.getView().getFolioTextField().setBackground(Color.RED);
            }
        } else {
            this.controller.getView().getFolioTextField().setBackground(Color.RED);
        }

    }

}
