/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.utils;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author ianfr
 */
public class ImageComponentFocusAdapter extends FocusAdapter {

    private JButton component;

    public ImageComponentFocusAdapter(JButton component) {
        this.component = component;
    }

    @Override
    public void focusGained(FocusEvent e) {
        component.setIcon(new ImageIcon(getClass().getResource("/com/areatecnica/nanduapp/resources/save.png")));
    }

    @Override
    public void focusLost(FocusEvent e) {
        component.setIcon(null);
    }
    
}
