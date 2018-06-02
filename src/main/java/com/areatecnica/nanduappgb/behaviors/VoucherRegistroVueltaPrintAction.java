/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.BoletosFactory;
import com.areatecnica.nanduappgb.controllers.RegistroBoletoController;
import com.areatecnica.nanduappgb.entities.VueltaGuia;
import com.areatecnica.nanduappgb.helpers.ReportController;
import com.areatecnica.nanduappgb.models.EstructuraRegistroBoletoÑandu;
import java.awt.event.ActionEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;

/**
 *
 * @author ianfrancoconcha
 */
public class VoucherRegistroVueltaPrintAction extends AbstractAction {

    private RegistroBoletoController controller;
    private Date fecha;
    private ReportController report;
    private Map map;
    private BoletosFactory factory;
    //private static final String path = ""; 
    private InputStream file = getClass().getResourceAsStream("/Voucher_boletos2.jrxml");

    public VoucherRegistroVueltaPrintAction(RegistroBoletoController controller) {
        this.controller = controller;

        List<EstructuraRegistroBoletoÑandu> items = new ArrayList<>();

        for (VueltaGuia v : this.controller.getGuia().getVueltaGuiaList()) {
            items.add(new EstructuraRegistroBoletoÑandu(v));
        }
        System.err.println("TAMAÑO ITEMS:" + items.size());

        this.factory = new BoletosFactory(items);
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
        this.map.put("serieDirecto", this.controller.getGuia().getVueltaGuiaList().get(0).getEstructura().getDirecto().getRegistroBoletoSerie());
        this.map.put("seriePlan", this.controller.getGuia().getVueltaGuiaList().get(0).getEstructura().getPlanVina().getRegistroBoletoSerie());
        this.map.put("serieLocal", this.controller.getGuia().getVueltaGuiaList().get(0).getEstructura().getLocal().getRegistroBoletoSerie());
        this.map.put("serieEscolarDirecto", this.controller.getGuia().getVueltaGuiaList().get(0).getEstructura().getEscolarDirecto().getRegistroBoletoSerie());
        this.map.put("serieEscolarLocal", this.controller.getGuia().getVueltaGuiaList().get(0).getEstructura().getEscolarLocal().getRegistroBoletoSerie());

        this.map.put("inicioDirecto", this.controller.getGuia().getVueltaGuiaList().get(0).getEstructura().getDirecto().getRegistroBoletoInicio());
        this.map.put("inicioPlan", this.controller.getGuia().getVueltaGuiaList().get(0).getEstructura().getPlanVina().getRegistroBoletoInicio());
        this.map.put("inicioLocal", this.controller.getGuia().getVueltaGuiaList().get(0).getEstructura().getLocal().getRegistroBoletoInicio());
        this.map.put("inicioEscolarDirecto", this.controller.getGuia().getVueltaGuiaList().get(0).getEstructura().getEscolarDirecto().getRegistroBoletoInicio());
        this.map.put("inicioEscolarLocal", this.controller.getGuia().getVueltaGuiaList().get(0).getEstructura().getEscolarLocal().getRegistroBoletoInicio());

        this.report = new ReportController(file, factory);
        this.report.setMap(map);

        this.report.loadFile();

        this.file = null;

        this.file = getClass().getResourceAsStream("/Voucher_boletos2.jrxml");
    }

}
