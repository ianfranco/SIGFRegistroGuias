/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroVueltaController;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.models.RegistroBoletoTableModel;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author ianfrancoconcha
 */
public class FindFolioVueltaFocusLost extends FocusAdapter {

    private RegistroVueltaController controller;
    private final IGuiaDao dao;
    private int folio;

    public FindFolioVueltaFocusLost(RegistroVueltaController controller) {
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
                    this.controller.getView().getObservacionTextField().setText("Registro Vuelta Nº " + model.getNumeroVuelta());
                    this.controller.setModel(model);
                    this.controller.getView().getEstadoBoletoTextField().setText("");
                    this.controller.setFlag(Boolean.FALSE);
                    this.controller.getView().getBusTextField().setText(String.valueOf(_guia.getGuiaIdBus().getBusNumero()));
                    this.controller.getView().getPpuTextField().setText(_guia.getGuiaIdBus().getBusPatente());
                    this.controller.getView().getConductorTextField().setText(String.valueOf(_guia.getGuiaIdTrabajador().getTrabajadorCodigo()));
                    this.controller.getView().getNombreConductorTextField().setText(_guia.getGuiaIdTrabajador().toString());
                    this.controller.getView().getObservacionTextField().setForeground(Color.BLACK);
                    this.controller.getView().getEstadoBoletoTextField().setBackground(Color.WHITE);

                    this.controller.getView().getServicioTextField().requestFocus();

                    this.controller.getView().getServicioTextField().setEnabled(Boolean.TRUE);
                    this.controller.getView().getBusTextField().setEnabled(Boolean.TRUE);
                    this.controller.getView().getConductorTextField().setEnabled(Boolean.TRUE);
                    this.controller.getView().getDirectoTextField().setEnabled(Boolean.TRUE);
                    this.controller.getView().getPlanVinaTextField().setEnabled(Boolean.TRUE);
                    this.controller.getView().getLocalTextField().setEnabled(Boolean.TRUE);
                    this.controller.getView().getEscolarDirectoTextField().setEnabled(Boolean.TRUE);
                    this.controller.getView().getEscolarLocalTextField().setEnabled(Boolean.TRUE);
                    this.controller.getView().getSaveButton().setEnabled(Boolean.TRUE);
                    this.controller.getView().getAddButton().setEnabled(Boolean.TRUE);

                } else {
                    JOptionPane.showMessageDialog(null, "Guía Folio: " + _folio + " No se encuentra ingresado", "Error", JOptionPane.ERROR_MESSAGE);
                    this.controller.getView().getObservacionTextField().setText("Guía Folio: " + _folio + " No se encuentra ingresado");
                    this.controller.getView().getObservacionTextField().setForeground(Color.red);
                    this.controller.getView().getServicioTextField().setEnabled(Boolean.FALSE);
                    this.controller.getView().getBusTextField().setEnabled(Boolean.FALSE);
                    this.controller.getView().getConductorTextField().setEnabled(Boolean.FALSE);
                    this.controller.getView().getDirectoTextField().setEnabled(Boolean.FALSE);
                    this.controller.getView().getPlanVinaTextField().setEnabled(Boolean.FALSE);
                    this.controller.getView().getLocalTextField().setEnabled(Boolean.FALSE);
                    this.controller.getView().getEscolarDirectoTextField().setEnabled(Boolean.FALSE);
                    this.controller.getView().getEscolarLocalTextField().setEnabled(Boolean.FALSE);
                    this.controller.getView().getSaveButton().setEnabled(Boolean.FALSE);
                    this.controller.getView().getAddButton().setEnabled(Boolean.FALSE);
                }

            } catch (NumberFormatException ex) {
                this.controller.getView().getFolioTextField().setBackground(Color.RED);
            }
        } else {
            this.controller.getView().getFolioTextField().setBackground(Color.RED);
        }

    }

}
