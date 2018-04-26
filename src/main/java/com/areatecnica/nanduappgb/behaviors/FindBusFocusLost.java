/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroBoletoController;
import com.areatecnica.nanduappgb.dao.IBusDao;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.IVueltaGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.BusDaoImpl;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import com.areatecnica.nanduappgb.dao.impl.VueltaGuiaDaoImpl;
import com.areatecnica.nanduappgb.entities.Bus;
import com.areatecnica.nanduappgb.entities.Guia;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import com.areatecnica.nanduappgb.entities.VueltaGuia;
import com.areatecnica.nanduappgb.models.BoletoTableModel;
import com.areatecnica.nanduappgb.models.VueltaGuiaComboBoxModel;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ianfr
 */
public class FindBusFocusLost extends FocusAdapter {

    private RegistroBoletoController controller;
    private final IBusDao dao;
    private final IGuiaDao guiaDao;
    private final IVueltaGuiaDao vueltaDao;
    private VueltaGuia vueltaGuia;
    private List<VueltaGuia> vueltaGuiaItems;
    private Date fecha;

    public FindBusFocusLost(RegistroBoletoController controller) {
        this.controller = controller;
        this.dao = new BusDaoImpl();
        this.guiaDao = new GuiaDaoImpl();
        this.vueltaDao = new VueltaGuiaDaoImpl();
        this.fecha = new Date();
    }

    @Override
    public void focusLost(FocusEvent e) {
        find();
    }

    public void find() {
//        if (this.controller.getGuia().getGuiaId() == null) {
        try {
            System.err.println("FINDBUSFOCUSLOST");
            String _busNumero = this.controller.getView().getBusTextField().getText();

            Bus _bus = this.dao.findByNumeroBusProceso(Integer.valueOf(_busNumero), this.controller.getProceso().getProceso());

            if (_bus != null) {
                this.controller.getView().getBusTextField().setBackground(Color.WHITE);
                this.controller.getGuia().setGuiaIdBus(_bus);
                this.controller.getView().getPpuTextField().setText(_bus.getBusPatente());
                this.controller.getView().getFlotaTextField().setText(_bus.getBusIdFlota().getFlotaNombre());

                Guia _guia = this.guiaDao.findLastGuiaByBusFecha(_bus, fecha);

                BoletoTableModel model = null;

                if (_guia != null && !_guia.getVueltaGuiaList().isEmpty()) {

                    VueltaGuia nuevaVuelta = new VueltaGuia();
                    nuevaVuelta.setRegistroBoletoList(new ArrayList());
                    nuevaVuelta.setVueltaGuiaIdGuia(this.controller.getGuia());
                    nuevaVuelta.setVueltaGuiaNumero(1);

                    System.err.println("N° DE VUELTA ANTIGUA:" + this.controller.getVueltaGuia().getVueltaGuiaNumero());

                    for (RegistroBoleto r : _guia.getVueltaGuiaList().get(_guia.getVueltaGuiaList().size() - 1).getRegistroBoletoList()) {
                        System.err.println("BOLETO:" + r.getRegistroBoletoIdBoleto().getBoletoNombre() + " Serie:" + r.getRegistroBoletoSerie());
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

                    List<VueltaGuia> array = new ArrayList<>();
                    array.add(nuevaVuelta);
                    this.controller.setVueltasItems(array);
                    
                    
//                    this.controller.setVueltaGuia(nuevaVuelta);
//                    this.controller.getVueltasItems().add(nuevaVuelta);
                    this.controller.getGuia().setVueltaGuiaList(this.controller.getVueltasItems());
                    VueltaGuiaComboBoxModel vueltasModel = new VueltaGuiaComboBoxModel(this.controller.getGuia().getVueltaGuiaList());
                    this.controller.setVueltaGuiaComboBoxModel(vueltasModel);

                    this.controller.getView().getServicioComboBox().setSelectedIndex(0);

                    model = new BoletoTableModel(this.controller.getVueltaGuia().getRegistroBoletoList(), false);

                    this.controller.setModel(model);
                    this.controller.getView().getServicioComboBox().setSelectedIndex(0);

                    this.controller.getView().getEstadoBoletoTextField().setText("");
                    this.controller.getView().getEstadoBoletoTextField().setForeground(Color.WHITE);
                    this.controller.setFlag(Boolean.FALSE);
                    
                    this.controller.getView().getObservacionTextField().setText("Nueva Guía");
                    this.controller.getView().getEstadoBoletoTextField().setText("Continuidad desde Guía Folio N°:"+_guia.getGuiaFolio());
                    
                } else {
                    System.err.println("NUEVA GUÍA PARA EL BUS");
                    this.vueltaGuia = new VueltaGuia();
                    this.vueltaGuia.setVueltaGuiaIdGuia(this.controller.getGuia());
                    this.vueltaGuia.setVueltaGuiaIdServicio(this.controller.getServicio());
                    this.vueltaGuia.setRegistroBoletoList(this.controller.getTarifas());
                    this.vueltaGuia.setVueltaGuiaNumero(1);
                    this.vueltaGuiaItems = new ArrayList<>();
                    this.vueltaGuiaItems.add(this.vueltaGuia);

                    this.controller.getGuia().setVueltaGuiaList(this.vueltaGuiaItems);
                    model = new BoletoTableModel(this.vueltaGuia.getRegistroBoletoList(), true);
                    this.controller.getView().getEstadoBoletoTextField().setForeground(Color.RED);
                    this.controller.getView().getEstadoBoletoTextField().setText("ATENCIÓN -> DEBE INGRESAR SERIE DE BOLETOS COMPLETA");

                    this.controller.getView().getTotalIngresosLabel().setText("---");
                    this.controller.setModel(model);
                    this.controller.setFlag(Boolean.TRUE);
                    VueltaGuiaComboBoxModel vueltasModel = new VueltaGuiaComboBoxModel(this.controller.getGuia().getVueltaGuiaList());
                    this.controller.setVueltaGuiaComboBoxModel(vueltasModel);
                    
                    this.controller.getView().getServicioComboBox().setSelectedIndex(0);
                }

            } else {
                this.controller.getView().getBusTextField().setBackground(Color.RED);
                this.controller.getView().getPpuTextField().setText("Error");
                this.controller.getView().getFlotaTextField().setText("");

            }

        } catch (NullPointerException ee) {
            this.controller.getView().getBusTextField().setBackground(Color.RED);
            ee.printStackTrace();
        } catch (NumberFormatException ex) {
            this.controller.getView().getBusTextField().setBackground(Color.GREEN);
            this.controller.getView().getSaveButton().setEnabled(Boolean.FALSE);
        }
//        } else {
//            this.controller.getView().getBusTextField().setBackground(Color.WHITE);
//            this.controller.getView().getSaveButton().setEnabled(Boolean.TRUE);
//        }
    }

}
