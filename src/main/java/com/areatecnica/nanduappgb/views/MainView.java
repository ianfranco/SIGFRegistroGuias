/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.areatecnica.nanduappgb.views;

import com.areatecnica.nanduappgb.controllers.GuiaItemsController;
import com.areatecnica.nanduappgb.controllers.RegistroBoletoController;
import com.areatecnica.nanduappgb.controllers.RegistroGuiaController;
import com.areatecnica.nanduappgb.utils.ButtonTabComponent;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import javax.swing.ImageIcon;
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
        jPanel1 = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        creacionGuiaMenuItem = new javax.swing.JMenuItem();
        registroBoletoMenuItem = new javax.swing.JMenuItem();
        buscarGuiaMenuItem = new javax.swing.JMenuItem();
        buscarBoletoMenuItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        advancedMenu = new javax.swing.JMenu();
        tarifasMenuItem = new javax.swing.JMenuItem();
        infoMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro de Guías / Boletos");

        tabbedPane.setBackground(new java.awt.Color(19, 50, 71));
        tabbedPane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        jPanel1.setBackground(new java.awt.Color(19, 50, 71));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 751, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        tabbedPane.addTab("Inicio", jPanel1);

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

        creacionGuiaMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        creacionGuiaMenuItem.setMnemonic('t');
        creacionGuiaMenuItem.setText("Creación de Guías");
        creacionGuiaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creacionGuiaMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(creacionGuiaMenuItem);

        registroBoletoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        registroBoletoMenuItem.setText("Registro de Boletos");
        registroBoletoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registroBoletoMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(registroBoletoMenuItem);

        buscarGuiaMenuItem.setText("Buscar guía");
        editMenu.add(buscarGuiaMenuItem);

        buscarBoletoMenuItem.setText("Buscar boleto");
        editMenu.add(buscarBoletoMenuItem);

        jMenuItem1.setText("Guías x Fecha");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        editMenu.add(jMenuItem1);

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

        infoMenu.setText("Información");
        menuBar.add(infoMenu);

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
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
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

    private void creacionGuiaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creacionGuiaMenuItemActionPerformed

        MainView.registroGuiaView = new RegistroGuiaView();
        RegistroGuiaController controller = new RegistroGuiaController(registroGuiaView);
        setNewTab(registroGuiaView, "Creación de Guía");

    }//GEN-LAST:event_creacionGuiaMenuItemActionPerformed

    private void registroBoletoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registroBoletoMenuItemActionPerformed
        MainView.registroVueltaView = new RegistroBoletoView();
        RegistroBoletoController controller = new RegistroBoletoController(registroVueltaView);
        setNewTab(registroVueltaView, "Registro Vueltas / Boleto");
    }//GEN-LAST:event_registroBoletoMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        guiaItems = new GuiaItemsView();
        GuiaItemsController controller = new GuiaItemsController(guiaItems);
        setNewTab(guiaItems, "Guías x Fecha");
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void setNewTab(JPanel panel, String title) {
        this.tabbedPane.add(title, panel);
        this.tabbedPane.setSelectedComponent(panel);
        this.tabbedPane.setTabComponentAt(this.tabbedPane.getSelectedIndex(), new ButtonTabComponent(this.tabbedPane));
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void displayMessage(String message) throws AWTException{
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();
        //Creating a tray icon
        ImageIcon icon = new ImageIcon(getClass().getResource("/bus.png"));
        Image image = icon.getImage();
        //System.out.println(image);
        TrayIcon trayIcon = new TrayIcon(image, "Información");
        //Let the system resizes the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("SIGF - Registro de Boletos");
        tray.add(trayIcon);
        trayIcon.displayMessage(message, null, MessageType.INFO);
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
        //</editor-fold

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
    private javax.swing.JMenuItem buscarBoletoMenuItem;
    private javax.swing.JMenuItem buscarGuiaMenuItem;
    private javax.swing.JMenuItem creacionGuiaMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu infoMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem registroBoletoMenuItem;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JMenuItem tarifasMenuItem;
    // End of variables declaration//GEN-END:variables
    private TarifaGrupoServicioView tarifaGrupoServicioView;
    private static RegistroBoletoView registroVueltaView;
    private static RegistroGuiaView registroGuiaView;
    private GuiaItemsView guiaItems; 
}
