/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.BoletosFactory;
import com.areatecnica.nanduappgb.controllers.RegistroVueltaController;
import com.areatecnica.nanduappgb.helpers.ReportController;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;

/**
 *
 * @author ianfrancoconcha
 */
public class VoucherRegistroVueltaPrintAction extends AbstractAction {

    private RegistroVueltaController controller;
    private Date fecha;
    private ReportController report;
    private Map map;
    private BoletosFactory factory;
    //private static final String path = ""; 
    private InputStream file = getClass().getResourceAsStream("/Voucher_boletos2.jrxml");

    public VoucherRegistroVueltaPrintAction(RegistroVueltaController controller) {
        this.controller = controller;
        this.factory = new BoletosFactory(this.controller.getModel().getList());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        print();
    }

    public void print() {

        this.map = new HashMap();

        this.map.put("folio", this.controller.getGuia().getGuiaFolio());
        this.map.put("fecha", this.controller.getGuia().getGuiaFecha());
        this.map.put("numeroBus", this.controller.getGuia().getGuiaIdBus().getBusNumero());
        this.map.put("patente", this.controller.getGuia().getGuiaIdBus().getBusPatente());
        this.map.put("flota", this.controller.getGuia().getGuiaIdBus().getBusIdFlota().getFlotaNombre());
        this.map.put("empresa", this.controller.getGuia().getGuiaIdBus().getBusIdEmpresa().getEmpresaNombre());
        this.map.put("serieDirecto", this.controller.getModel().getPrimerRegistro().getDirecto().getRegistroBoletoSerie());
        this.map.put("seriePlan", this.controller.getModel().getPrimerRegistro().getPlanVina().getRegistroBoletoSerie());
        this.map.put("serieLocal", this.controller.getModel().getPrimerRegistro().getLocal().getRegistroBoletoSerie());
        this.map.put("serieEscolarDirecto", this.controller.getModel().getPrimerRegistro().getEscolarDirecto().getRegistroBoletoSerie());
        this.map.put("serieEscolarLocal", this.controller.getModel().getPrimerRegistro().getEscolarLocal().getRegistroBoletoSerie());
        this.map.put("totalDirecto", this.controller.getModel().getUltimoRegistro().getDirecto().getRegistroBoletoTotal());
        this.map.put("totalPlan", this.controller.getModel().getUltimoRegistro().getPlanVina().getRegistroBoletoTotal());
        this.map.put("totalLocal", this.controller.getModel().getUltimoRegistro().getLocal().getRegistroBoletoTotal());
        this.map.put("totalEscolar1", this.controller.getModel().getUltimoRegistro().getEscolarDirecto().getRegistroBoletoTotal());
        this.map.put("totalEscolar2", this.controller.getModel().getUltimoRegistro().getEscolarLocal().getRegistroBoletoTotal());

        this.report = new ReportController(file, factory);
        this.report.setMap(map);

        this.report.loadFile();

        this.file = null;

        this.file = getClass().getResourceAsStream("/Voucher_boletos2.jrxml");
    }

}
