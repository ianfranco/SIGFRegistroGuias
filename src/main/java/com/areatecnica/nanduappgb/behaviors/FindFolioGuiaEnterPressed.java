/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroBoletoController;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import com.areatecnica.nanduappgb.entities.VueltaGuia;
import com.areatecnica.nanduappgb.models.BoletoTableModel;
import com.areatecnica.nanduappgb.models.VueltaGuiaComboBoxModel;
import com.areatecnica.nanduappgb.views.PanelGuia;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ianfrancoconcha
 */
public class FindFolioGuiaEnterPressed extends KeyAdapter {

    private RegistroBoletoController controller;
    private final IGuiaDao dao;
    private int folio;
    private int totalGuia;
//    private IVueltaGuiaDao vueltaDao;
//    private VueltaGuia vueltaGuia;
//    private List<VueltaGuia> vueltaGuiaItems;
    private BoletoTableModel model;

    public FindFolioGuiaEnterPressed(RegistroBoletoController controller) {
        this.controller = controller;
        this.dao = new GuiaDaoImpl();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        press(e);
    }

    public void press(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_TAB) {
            String _folio = this.controller.getView().getFolioTextField().getText();

            //Verifico si el texto del folio no esta vacío
            if (!_folio.equals("")) {
                try {

                    this.folio = Integer.parseInt(_folio);

                    this.controller.getView().getFolioTextField().setBackground(Color.WHITE);

                    //Busco guía con el folio ingresado
                    Guia _guia = this.dao.findByFolio(folio);

                    //Si la guía existe
                    if (_guia != null) {
                        //Seteo el valor de la guía en el controlador
                        this.controller.setGuia(_guia);

                        //Obtengo el total de ingresos 
                        for (VueltaGuia v : this.controller.getGuia().getVueltaGuiaList()) {
                            for (RegistroBoleto r : v.getRegistroBoletoList()) {
                                totalGuia = totalGuia + r.getRegistroBoletoTotal();
                            }
                        }

                        this.controller.getView().getTotalIngresosLabel().setText("$ " + String.format("%d", totalGuia));

                        //Obtengo el número de vueltas que existen ingresadas a la guía 
                        int numeroVueltas = _guia.getVueltaGuiaList().size();

                        //Seteo valores de la vista
                        this.controller.getView().getBusTextField().setText(String.valueOf(_guia.getGuiaIdBus().getBusNumero()));
                        this.controller.getView().getPpuTextField().setText(_guia.getGuiaIdBus().getBusPatente());
                        this.controller.getView().getFlotaTextField().setText(_guia.getGuiaIdBus().getBusIdFlota().getFlotaNombre());
                        this.controller.getView().getFechaGuiaTextField().setValue(_guia.getGuiaFecha());
                        this.controller.getView().getConductorTextField().setText(String.valueOf(_guia.getGuiaIdTrabajador().getTrabajadorCodigo()));
                        this.controller.getView().getNombreConductorTextField().setText(_guia.getGuiaIdTrabajador().toString());
                        this.controller.getView().getObservacionTextField().setForeground(Color.WHITE);
                        this.controller.getView().getEstadoBoletoTextField().setBackground(Color.WHITE);
                        this.controller.getView().getTurnoTextField().setText(String.valueOf(_guia.getGuiaTurno()));
                        //Seteo el foco en la tabla
                        this.controller.getView().getTable().requestFocus();

                        this.controller.getView().getServicioComboBox().setEnabled(Boolean.TRUE);
                        this.controller.getView().getBusTextField().setEnabled(Boolean.TRUE);
                        this.controller.getView().getConductorTextField().setEnabled(Boolean.TRUE);
                        this.controller.getView().getSaveButton().setEnabled(Boolean.TRUE);

                        PanelGuia panel = null;
                        panel = new PanelGuia();
                        panel.getVueltaLabel().setText(String.valueOf(numeroVueltas + 1) + " ?");
                        int option = JOptionPane.showConfirmDialog(null, panel, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                        if (option == JOptionPane.YES_OPTION) {

                            //Aumento el número en 1 <<<< debería aumentar si elige ingresar nueva vuelta
                            numeroVueltas = numeroVueltas + 1;

                            this.controller.getView().getObservacionTextField().setText("Registro Vuelta Nº " + numeroVueltas);
                            this.controller.getView().getEstadoBoletoTextField().setText("x Registrar");

//                            if (_guia.getVueltaGuiaList().isEmpty()) {
//                                VueltaGuia vueltaGuia = new VueltaGuia();
//                                vueltaGuia.setVueltaGuiaIdGuia(this.controller.getGuia());
//                                vueltaGuia.setVueltaGuiaNumero(0);
//                                vueltaGuia.setRegistroBoletoList(this.controller.getTarifas());
//                                this.controller.setVueltaGuia(vueltaGuia);
//                                _guia.getVueltaGuiaList().add(this.controller.getVueltaGuia());
//                                
//                            } 
                            //this.controller.setVueltasItems(_guia.getVueltaGuiaList());
                            //this.controller.setVueltaGuia(this.controller.getVueltasItems().get(this.controller.getVueltasItems().size() - 1));
                            VueltaGuia nuevaVuelta = new VueltaGuia();
                            nuevaVuelta.setRegistroBoletoList(new ArrayList());
                            nuevaVuelta.setVueltaGuiaIdGuia(this.controller.getGuia());
                            nuevaVuelta.setVueltaGuiaNumero(numeroVueltas);

                            for (RegistroBoleto r : this.controller.getGuia().getVueltaGuiaList().get(this.controller.getGuia().getVueltaGuiaList().size() - 1).getRegistroBoletoList()) {
                                //System.err.println("BOLETO:" + r.getRegistroBoletoIdBoleto().getBoletoNombre());
                                RegistroBoleto nuevoBoleto = new RegistroBoleto();
                                nuevoBoleto.setRegistroBoletoIdVueltaGuia(nuevaVuelta);
                                nuevoBoleto.setRegistroBoletoIdBoleto(r.getRegistroBoletoIdBoleto());
                                nuevoBoleto.setRegistroBoletoValor(r.getRegistroBoletoValor());
                                nuevoBoleto.setRegistroBoletoInicio(r.getRegistroBoletoTermino());
                                nuevoBoleto.setRegistroBoletoSerie(r.getRegistroBoletoSerie());
                                nuevoBoleto.setRegistroBoletoCantidad(0);
                                nuevoBoleto.setRegistroBoletoTotal(0);
                                nuevoBoleto.setRegistroBoletoTermino(0);

                                nuevaVuelta.getRegistroBoletoList().add(nuevoBoleto);
                            }

                            this.controller.setVueltaGuia(nuevaVuelta);

                            this.controller.getGuia().getVueltaGuiaList().add(nuevaVuelta);

                            model = new BoletoTableModel(nuevaVuelta.getRegistroBoletoList(), false);

                            this.controller.setModel(model);
                            this.controller.getView().getServicioComboBox().setSelectedIndex(0);

                        } else {
                            this.controller.getView().getVueltaComboBox().requestFocus();
                        }

                        this.controller.getView().getServicioComboBox().requestFocus();

                        VueltaGuiaComboBoxModel vueltasModel = new VueltaGuiaComboBoxModel(this.controller.getGuia().getVueltaGuiaList());
                        this.controller.setVueltaGuiaComboBoxModel(vueltasModel);
                        this.controller.getView().getVueltaComboBox().setSelectedItem(this.controller.getVueltaGuia());

                    } else {
                        try {

                            this.controller.setGuia(new Guia());
                            this.controller.setVueltaGuia(new VueltaGuia());
                            this.controller.getGuia().setGuiaTurno(1);
                            this.model = new BoletoTableModel();
                            this.controller.setVueltaGuiaComboBoxModel(new VueltaGuiaComboBoxModel(new ArrayList<>()));
                            this.controller.getView().getVueltaComboBox().setModel(this.controller.getVueltaGuiaComboBoxModel());
                            this.controller.getGuia().setGuiaFolio(folio);
                            this.controller.displayMessage("Nueva Guía");
                            this.controller.getView().getBusTextField().setText("");
                            this.controller.getView().getConductorTextField().setText("");
                            this.controller.getView().getTurnoTextField().setText("1");

                            this.controller.getView().getFechaGuiaTextField().requestFocus();

                        } catch (AWTException ex) {
                            Logger.getLogger(FindFolioGuiaEnterPressed.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } catch (NumberFormatException ex) {
                    this.controller.getView().getFolioTextField().setBackground(Color.RED);
                }
            } else {
                this.controller.getView().getFolioTextField().setBackground(Color.RED);
            }
        }
    }

}
