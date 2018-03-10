/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.behaviors;

import com.areatecnica.nanduappgb.controllers.RegistroGuiaController;
import com.areatecnica.nanduappgb.entities.RegistroBoleto;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

/**
 *
 * @author ianfrancoconcha
 */
public class ValidateRegistroBoletoFocusLost extends FocusAdapter {
    
    private RegistroGuiaController controller;
    private JTextField textField;
    private RegistroBoleto registroBoleto;
    private int inicio;
    
    public ValidateRegistroBoletoFocusLost() {
    }
    
    public ValidateRegistroBoletoFocusLost(RegistroGuiaController controller, JTextField textField, RegistroBoleto registroBoleto) {
        this.controller = controller;
        this.textField = textField;
        this.registroBoleto = registroBoleto;
    }
    
    public RegistroGuiaController getController() {
        return controller;
    }
    
    @Override
    public void focusLost(FocusEvent e) {
        validate();
    }
    
    private void validate() {
        if (this.registroBoleto != null) {
            try {
                String _value = this.textField.getText();
                this.inicio = Integer.parseInt(_value);
                
                if (this.inicio < this.registroBoleto.getRegistroBoletoInicio()) {
                    this.textField.setText(String.valueOf(this.registroBoleto.getRegistroBoletoInicio()));
                }
                
            } catch (NumberFormatException e) {
                this.textField.setBackground(Color.red);
                
            }
        }
    }
    
}
