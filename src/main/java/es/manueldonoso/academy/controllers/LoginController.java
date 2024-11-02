/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import es.manueldonoso.academy.Main;
import es.manueldonoso.academy.util.ConexionBDLocal;
import es.manueldonoso.academy.util.Metodos;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import animatefx.animation.*;
import es.manueldonoso.academy.modelos.Usuario;
import es.manueldonoso.academy.util.Session;
import java.net.URISyntaxException;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Manuel Jesús Donoso Pérez
 */
public class LoginController implements Initializable {

    @FXML
    private HBox root;
    @FXML
    private JFXButton btn_whatsapp;
    @FXML
    private JFXButton btn_link;
    @FXML
    private JFXCheckBox ck_recorgar;
    @FXML
    private JFXButton btn_iniciar;
    @FXML
    private JFXButton btn_olvideContraseña;
    @FXML
    private PasswordField txt_pass;
    @FXML
    private TextField txt_nombre;
    @FXML
    private ToggleGroup online;
    @FXML
    private JFXRadioButton rb_online;
    @FXML
    private JFXRadioButton rb_local;
    @FXML
    private Pane Panel_registrar;
    @FXML
    private JFXButton btn_registrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("inicio");

        new animatefx.animation.FadeInDown(root).setSpeed(0.25).play();
        comprobarInstalacion();

    }

    @FXML
    private void Iniciar_Sesion(ActionEvent event) {
        System.out.println("inicio sesion");
        String nombre = txt_nombre.getText();
        String pass = txt_pass.getText();
        if (rb_local.isSelected()) {
            
            if (ConexionBDLocal.LoginCorrecto(nombre, pass)) {
                Session.setUsuarioLogin(ConexionBDLocal.user(nombre, pass));

                Metodos.CargarDasboard(Session.getUsuarioLogin(),root);
            }
           
        } else if (rb_online.isSelected()) {
            System.out.println("Opcion no implementada");
        }
    }

    public void cargarDasboard(Usuario user, String titulo, int tipo) {

        switch (tipo) {
            case 1:
                CargarDasboardAdmin(titulo, user);
                break;
            case 2:
                System.out.println("Profesor");
                break;

            case 3:
                System.out.println("Alumno");
                break;
            default:
                throw new AssertionError();
        }

    }

    private void CargarDasboardAdmin(String titulo, Usuario user) {
        Session.setUsuarioLogin(user);
        try {
            Stage primaryStage = new Stage();
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vistas/Dashboard_admin.fxml"));

            // Ventana a cargar
            VBox ventana = (VBox) loader.load();

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Administrador: " + titulo);
            primaryStage.setMaximized(true);

            Metodos.closeEffect(root);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    public void cargarContrato() {
        try {
            Stage primaryStage = new Stage();
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vistas/Contrato.fxml"));

            // Ventana a cargar
            GridPane ventana = (GridPane) loader.load();

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Licencia de uso");

            //detectar cierre de ventana
            primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {

                    System.out.println("se cerro la ventana de configuración");
                    comprobarInstalacion();

                }
            });

            //mostrar ventana
            primaryStage.show();

            // primaryStage.setMaximized(true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void comprobarInstalacion() {

    
        if (ConexionBDLocal.ExisteAdmin()) {
            rb_local.setDisable(false);
            rb_local.setSelected(true);
            Panel_registrar.setVisible(false);

        } else {
            rb_local.setDisable(true);
            rb_online.setSelected(true);
            Panel_registrar.setVisible(true);

            // cargarContrato(primaryStage);
        }
    }

    @FXML
    private void abrirweb() {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop Dt = java.awt.Desktop.getDesktop();
            if (Dt.isSupported(java.awt.Desktop.Action.BROWSE)) {

                java.net.URI uri;
                try {
                    uri = new java.net.URI("https://www.manueldonoso.es");
                    Dt.browse(uri);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
}
