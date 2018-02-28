/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.utils;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

/**
 *
 * @author ianfr
 */
public class TextSelectionFocusAdapter extends FocusAdapter {

    private JTextField textField;

    public TextSelectionFocusAdapter(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.textField.selectAll();
    }

}
