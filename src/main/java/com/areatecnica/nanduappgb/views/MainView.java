/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.views;

import com.areatecnica.nanduappgb.controllers.RegistroGuiaController;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author ianfrancoconcha
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainView
     */
    public MainView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        registroBoletoMenuItem = new javax.swing.JMenuItem();
        registroVueltaMenuItem = new javax.swing.JMenuItem();
        advancedMenu = new javax.swing.JMenu();
        tarifasMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Archivo");

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Salir");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Acciones");

        registroBoletoMenuItem.setMnemonic('t');
        registroBoletoMenuItem.setText("Registro de Guías");
        registroBoletoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registroBoletoMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(registroBoletoMenuItem);

        registroVueltaMenuItem.setText("Registro de Vueltas");
        registroVueltaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registroVueltaMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(registroVueltaMenuItem);

        advancedMenu.setText("Avanzado");

        tarifasMenuItem.setText("Tarifas");
        tarifasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarifasMenuItemActionPerformed(evt);
            }
        });
        advancedMenu.add(tarifasMenuItem);

        editMenu.add(advancedMenu);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Ayuda");

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("Acerca de");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void tarifasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarifasMenuItemActionPerformed
        tarifaGrupoServicioView = new TarifaGrupoServicioView();
        setNewTab(tarifaGrupoServicioView, "Tarifas");
    }//GEN-LAST:event_tarifasMenuItemActionPerformed

    private void registroBoletoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroBoletoMenuItemActionPerformed
        if (MainView.registroGuiaView == null) {
            MainView.registroGuiaView = new RegistroGuiaView();
            RegistroGuiaController controller = new RegistroGuiaController(registroGuiaView);
            setNewTab(registroGuiaView, "Registro Guía / Boleto");
        }else{
            this.getTabbedPane().setSelectedComponent(MainView.registroGuiaView);
        }
    }//GEN-LAST:event_registroBoletoMenuItemActionPerformed

    private void registroVueltaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroVueltaMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_registroVueltaMenuItemActionPerformed

    private void setNewTab(JPanel panel, String title) {
        this.tabbedPane.add(title, panel);
        this.tabbedPane.setSelectedComponent(panel);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainView main = new MainView();
                main.setExtendedState(JFrame.MAXIMIZED_BOTH);
                main.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu advancedMenu;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem registroBoletoMenuItem;
    private javax.swing.JMenuItem registroVueltaMenuItem;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JMenuItem tarifasMenuItem;
    // End of variables declaration//GEN-END:variables
    private TarifaGrupoServicioView tarifaGrupoServicioView;
    private static RegistroGuiaView registroGuiaView;
}
