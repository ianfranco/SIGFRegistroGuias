/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

/**
 *
 * @author ianfr
 */
public class NextObject extends KeyAdapter {

    private final JComponent up;
    private final JComponent down;
    private final JComponent right;
    private final JComponent left;

    public NextObject(JComponent up, JComponent down, JComponent right, JComponent left) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        move(e, up, down, right, left);
    }

    private void move(KeyEvent e, JComponent up, JComponent down, JComponent right, JComponent left) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_TAB:
                if (down != null) {
                    down.requestFocus();
                }
                break;
            case KeyEvent.VK_UP:
                if (up != null) {
                    up.requestFocus();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (right != null) {
                    right.requestFocus();
                }
                break;
            case KeyEvent.VK_LEFT:
                if (left != null) {
                    left.requestFocus();
                }
                break;

        }
    }
}
