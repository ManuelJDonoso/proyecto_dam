/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import es.manueldonoso.academy.Main;
import es.manueldonoso.academy.util.Metodos;
import es.manueldonoso.academy.util.Session;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Manuel Jesús Donoso Pérez
 */
public class Dashboard_adminController implements Initializable {

    @FXML
    private MenuItem MenuItemSalir;
    @FXML
    private VBox root;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(Session.usuarioLogin.toString());

    }    

    @FXML
    private void Salir(ActionEvent event) {
        Metodos.closeEffect(root);
        System.exit(0);
    }

    @FXML
    private void crearCliente(ActionEvent event) {
         Metodos.nuevoUsuario(root);
    }
    
    
    @FXML
    private void cargarmisdatos(){
    Metodos.MisDatos(root);
    }

    @FXML
    private void add_user(ActionEvent event) {
        Metodos.nuevoUsuario(root);
    }

    @FXML
    private void delete_user(ActionEvent event) {
    }

    @FXML
    private void Salir_login(ActionEvent event) {
       Metodos.closeEffect(root);
       Session.finSession();
       Metodos.cargarLogin();
    }

    @FXML
    private void anadir_Asignatura(ActionEvent event) {
        Metodos.InsertarAsignatura(root);
    }

    @FXML
    private void modificar_estado_asignaturas(ActionEvent event) {
        Metodos.CargarModificarEstadoAsignaturas(root);
    }
}
