/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;

import com.areatecnica.nanduappgb.dao.IServicioDao;
import com.areatecnica.nanduappgb.dao.impl.ServicioDaoImpl;
import com.areatecnica.nanduappgb.entities.Servicio;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author ianfr
 */
public class ServicioComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private IServicioDao dao;
    private List<Servicio> items;
    private Servicio item;

    public ServicioComboBoxModel() {
        this.dao = new ServicioDaoImpl();
        this.items = this.dao.findAllBy();
    }

    @Override
    public int getSize() {
        return this.items.size();
    }

    @Override
    public Servicio getElementAt(int index) {
        return this.items.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.item = (Servicio)anItem;
    }

    @Override
    public Servicio getSelectedItem() {
        return item; 
    }

}
