/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.helpers;

import com.areatecnica.nanduappgb.controllers.RegistroController;
import com.areatecnica.nanduappgb.controllers.RegistroGuiaController;
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
public class VoucherGuiaPrintAction extends AbstractAction {

    private RegistroGuiaController controller;
    private Date fecha;
    private ReportController report;
    private Map map;
    //private static final String path = ""; 
    private InputStream file = getClass().getResourceAsStream("/Voucher_boletos.jrxml");

    public VoucherGuiaPrintAction(RegistroController controller) {
        this.controller = (RegistroGuiaController) controller;
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
        this.map.put("serieEscolarLocal", this.controller.getModel().getPrimerRegistro().getEscolarLocal().getRegistroBoletoInicio());
        this.map.put("inicioDirecto", this.controller.getModel().getPrimerRegistro().getDirecto().getRegistroBoletoInicio());
        this.map.put("inicioPlan", this.controller.getModel().getPrimerRegistro().getPlanVina().getRegistroBoletoInicio());
        this.map.put("inicioLocal", this.controller.getModel().getPrimerRegistro().getLocal().getRegistroBoletoInicio());
        this.map.put("inicioEscolarDirecto", this.controller.getModel().getPrimerRegistro().getEscolarDirecto().getRegistroBoletoInicio());
        this.map.put("inicioEscolarLocal", this.controller.getModel().getPrimerRegistro().getEscolarLocal().getRegistroBoletoInicio());

        this.report = new ReportController(file);
        this.report.setMap(map);

        this.report.loadFile();

        this.file = null;

        this.file = getClass().getResourceAsStream("/Voucher_boletos.jrxml");
    }

}
