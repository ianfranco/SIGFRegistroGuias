/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroGuiaController;
import com.areatecnica.nanduappgb.dao.IBusDao;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.BusDaoImpl;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import com.areatecnica.nanduappgb.entities.Bus;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.models.RegistroBoletoTableModel;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;

/**
 *
 * @author ianfr
 */
public class FindBusSolyMarFocusLost extends FocusAdapter {

    private RegistroGuiaController controller;
    private final IBusDao dao;
    private final IGuiaDao guiaDao;
    private Date fecha;

    public FindBusSolyMarFocusLost(RegistroGuiaController controller) {
        this.controller = controller;
        this.dao = new BusDaoImpl();
        this.guiaDao = new GuiaDaoImpl();
        this.fecha = new Date();
    }

    @Override
    public void focusLost(FocusEvent e) {
        find();
    }

    public void find() {
        try {

            String _busNumero = this.controller.getView().getBusTextField().getText();
            
            Bus _bus = this.dao.findByNumeroBusProceso(Integer.valueOf(_busNumero), this.controller.getProceso().getProceso());

            if (_bus != null) {
                this.controller.getView().getBusTextField().setBackground(Color.WHITE);
                this.controller.getGuia().setGuiaIdBus(_bus);
                this.controller.getView().getPpuTextField().setText(_bus.getBusPatente());
                this.controller.getView().getFlotaTextField().setText(_bus.getBusIdFlota().getFlotaNombre());

                Guia _guia = this.guiaDao.findLastGuiaByBusFecha(_bus, fecha);
                
                RegistroBoletoTableModel model = null;
                
                if (_guia != null) {
                    System.err.println("LA GUIA NO ES NULA");
                    model = new RegistroBoletoTableModel(_guia.getRegistroBoletoList());
                    this.controller.setModel(model);
                    this.controller.getView().getEstadoBoletoTextField().setText("");
                    this.controller.setFlag(Boolean.FALSE);
                } else {
                    model = new RegistroBoletoTableModel(null, true);
                    this.controller.getView().getEstadoBoletoTextField().setText("Atención: Deben registrar los boletos");
                    this.controller.setModel(model);
                    this.controller.setFlag(Boolean.TRUE);
                }

            } else {
                this.controller.getView().getBusTextField().setBackground(Color.RED);
                this.controller.getView().getPpuTextField().setText("No existe el N° Ingresado");
                this.controller.getView().getFlotaTextField().setText("");

            }

        } catch (NullPointerException ee) {
            this.controller.getView().getBusTextField().setBackground(Color.RED);
            ee.printStackTrace();
        } catch (NumberFormatException ex) {
            this.controller.getView().getBusTextField().setBackground(Color.GREEN);
            this.controller.getView().getSaveButton().setEnabled(Boolean.FALSE);
        }
    }

}
