/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroBoletoController;
import com.areatecnica.nanduappgb.dao.IGuiaDao;
import com.areatecnica.nanduappgb.dao.impl.GuiaDaoImpl;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import com.areatecnica.nanduappgb.entities.VueltaGuia;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author ianfrancoconcha
 */
public class SaveGuiaAction extends AbstractAction {

    private IGuiaDao dao;
    private RegistroBoletoController controller;

    public SaveGuiaAction(RegistroBoletoController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        this.dao = new GuiaDaoImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        save();
    }

    public void save() {
        if (this.controller.getGuia() != null) {

            if (this.controller.getGuia().getGuiaIdBus() != null && this.controller.getGuia().getGuiaIdTrabajador() != null) {
                try {
                    this.controller.getGuia().setGuiaFecha(this.controller.getView().getDate());

                    System.err.println("TOTAL DE VUELTAS ASIGNADAS A LA GUIA:" + this.controller.getGuia().getVueltaGuiaList().size());
                    List<RegistroBoleto> list = new ArrayList<>();

//                    for (VueltaGuia r : this.controller.getGuia().getVueltaGuiaList()) {
//                        r.setVueltaGuiaIdGuia(this.controller.getGuia());
//                        r.setVueltaGuiaIdServicio(this.controller.getServicio());
//                                                
////                        r.setRegistroBoletoList(list);
////                        list = new ArrayList<>();
//                    }
//                    for(RegistroBoleto r:this.controller.getVueltaGuia().getRegistroBoletoList()){
//                        System.err.println("ID BOLETO:"+r.getRegistroBoletoIdBoleto().getBoletoNombre());
//                    }
                    for (VueltaGuia r : this.controller.getGuia().getVueltaGuiaList()) {
                        r.setVueltaGuiaIdGuia(this.controller.getGuia());
                        r.setVueltaGuiaIdServicio(this.controller.getServicio());
                        System.err.println("N° DE VUELTA:" + r.getVueltaGuiaNumero());
                        System.err.println("Folio Vuelta:" + r.getVueltaGuiaIdGuia().getGuiaFolio());
                        System.err.println("Total de Registros:" + r.getRegistroBoletoList().size());
                        RegistroBoleto toDelete = null;
                        for (RegistroBoleto b : r.getRegistroBoletoList()) {
                            if (b.getRegistroBoletoIdBoleto() == null) {
                                toDelete = b;
                            } else {
                                System.err.println("NOMBRE DE BOLETO:" + b.getRegistroBoletoIdBoleto().getBoletoNombre());
                                b.setRegistroBoletoIdVueltaGuia(r);
                                list.add(b);
                            }
                        }
                        
                        r.setRegistroBoletoList(list);
                        list = new ArrayList<>();
                    }

                    System.err.println("cantidad de vueltas en la guia nueva " + this.controller.getGuia().getVueltaGuiaList().size());
                    this.controller.getGuia().setGuiaFechaIngreso(new Date());
                    this.dao.update(this.controller.getGuia());


                    VoucherRegistroVueltaPrintAction v = new VoucherRegistroVueltaPrintAction(this.controller);
                    v.print();
                    this.controller.reset();

                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {

            }

        }
    }

}
