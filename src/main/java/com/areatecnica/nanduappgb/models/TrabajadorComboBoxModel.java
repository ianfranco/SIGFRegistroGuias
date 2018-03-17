/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;

import com.areatecnica.nanduappgb.dao.ITrabajadorDao;
import com.areatecnica.nanduappgb.dao.impl.TrabajadorDaoImpl;
import com.areatecnica.nanduappgb.entities.Trabajador;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author ianfr
 */
public class TrabajadorComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private ITrabajadorDao dao;
    private List<Trabajador> busItems;
    private Trabajador item;

    public TrabajadorComboBoxModel() {
        this.dao = new TrabajadorDaoImpl();
        this.busItems = this.dao.findAll();
    }

    @Override
    public int getSize() {
        return this.busItems.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.busItems.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.item = (Trabajador)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return item; 
    }

}
