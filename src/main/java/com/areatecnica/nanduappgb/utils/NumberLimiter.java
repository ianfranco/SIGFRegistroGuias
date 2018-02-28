/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author ianfr
 */
public class NumberLimiter extends PlainDocument {

    public void insertString(int arg0, String arg1, AttributeSet arg2) throws BadLocationException {
        for (int i = 0; i < arg1.length(); i++) // si no es digit, volvemos
        {
            if (!Character.isDigit(arg1.charAt(i))) {
                return;
            }
        }

        // Si todos son digit, insertamos el texto en el JTextField
        super.insertString(arg0, arg1, arg2);
    }

}
