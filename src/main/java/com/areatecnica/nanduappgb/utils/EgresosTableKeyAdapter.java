/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JTable;

/**
 *
 * @author ianfr
 */
public class EgresosTableKeyAdapter extends KeyAdapter {

    private final JComponent up;
    private final JComponent down;
    private final JTable table;

    public EgresosTableKeyAdapter(JComponent up, JComponent down, JTable table) {
        this.up = up;
        this.down = down;
        this.table = table;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        move(e);
    }

    public void move(KeyEvent e) {
        int row = this.table.getSelectedRow();
        int lastRow = this.table.getRowCount() - 2;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                if (down != null && row == lastRow) {
                    down.requestFocus();
                    this.table.changeSelection(lastRow, 2, false, false);
                    System.err.println("abajo");
                }
                break;
            case KeyEvent.VK_DOWN:
                if (down != null && row == lastRow) {
                    down.requestFocus();
                    
                    System.err.println("abajo");
                }
                break;
            case KeyEvent.VK_UP:
                if (up != null && row == 0) {
                    up.requestFocus();
                    System.err.println("arriba");
                }
                break;
        }

    }

}
