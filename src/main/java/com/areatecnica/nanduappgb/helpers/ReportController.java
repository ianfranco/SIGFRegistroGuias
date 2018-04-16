/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.helpers;

import com.areatecnica.nanduappgb.controllers.BoletosFactory;
import com.areatecnica.nanduappgb.dao.Conexion;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ianfrancoconcha
 */
public class ReportController {

    private JasperReport report;
    private JasperPrint jasperPrint;
    private String path;
    private String title;
    private InputStream file;
    private JTabbedPane panel;
    private Map map;
    private Boolean flag = Boolean.TRUE;
    private BoletosFactory factory;

    public ReportController() {
    }

    public ReportController(InputStream file) {
        this.file = file;
    }

    public ReportController(InputStream file, BoletosFactory factory) {
        this.file = file;
        this.factory = factory;
    }

    public ReportController(String path) {
        this.path = path;
    }

    public ReportController(JTabbedPane panel, String path) {
        this.path = path;
        this.panel = panel;
        load();
    }

    public ReportController(JTabbedPane panel, InputStream file, Map map, String title) {
        this.file = file;
        this.panel = panel;
        this.map = map;
        this.title = title;
        //loadFile();
    }

    public void load() {
        try {
            this.report = JasperCompileManager.compileReport(this.path);

            this.jasperPrint = JasperFillManager.fillReport(report, map, new Conexion().getConnection());

            if (flag) {
                JRViewer jr = new JRViewer(jasperPrint);
                this.panel.add(jr, title);
                this.panel.setSelectedComponent(jr);

            } else {
                JasperPrintManager.printReport(jasperPrint, false);
            }

        } catch (JRException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se ha encontrado la ruta del informe", "Error al generar la petición", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadFile() {
        try {
            this.report = JasperCompileManager.compileReport(this.file);

            this.jasperPrint = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(factory.load()));

            System.err.println("llega acà en impresión");
            JasperViewer.viewReport(jasperPrint, false);
            //  JasperPrintManager.printReport(jasperPrint, false);

        } catch (JRException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void setMap(Map map) {
        this.map = map;
    }

}
