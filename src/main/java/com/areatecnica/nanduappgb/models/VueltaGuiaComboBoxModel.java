/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.models;

import com.areatecnica.nanduappgb.entities.VueltaGuia;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author ianfr
 */
public class VueltaGuiaComboBoxModel extends AbstractListModel implements ComboBoxModel {

    public VueltaGuiaComboBoxModel() {
    }

    public VueltaGuiaComboBoxModel(List<VueltaGuia> items) {
        this.items = items;
    }

    private List<VueltaGuia> items;
    private VueltaGuia item;

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public VueltaGuia getElementAt(int index) {
        return items.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.item = (VueltaGuia) anItem;
    }

    @Override
    public VueltaGuia getSelectedItem() {
        return this.item;
    }

}
