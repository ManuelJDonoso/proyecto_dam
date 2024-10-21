/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academy.util.Session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Manuel Jesús Donoso Pérez
 */
public class PerfilUsuarioController implements Initializable {

    @FXML
    private JFXTextField txt_nombre;
    @FXML
    private JFXTextField txt_apellidos;
    @FXML
    private JFXTextField txt_direccion;
    @FXML
    private JFXTextField txt_telefono;
    @FXML
    private JFXTextField txt_email;
    @FXML
    private JFXButton btn_cargarF;
    @FXML
    private JFXButton btn_eliminar_Fotps;
    @FXML
    private JFXButton btn_tomarFoto;
    @FXML
    private ImageView iw;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private JFXButton btn_aceptar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        txt_nombre.setText(Session.getUsuarioLogin().getNombre());
        txt_apellidos.setText(Session.getUsuarioLogin().getApellidos());
        txt_direccion.setText(Session.getUsuarioLogin().getDireccion());
        txt_email.setText(Session.getUsuarioLogin().getEmail());
        txt_telefono.setText(Session.getUsuarioLogin().getTelefono());
        
    }    

    @FXML
    private void TomarFoto(ActionEvent event) {
    }

    @FXML
    private void cancelar_registro(ActionEvent event) {
    }
    
}
