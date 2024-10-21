/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import es.manueldonoso.academy.util.Metodos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author donpe
 */
public class MensajeDeErrorController implements Initializable {

    @FXML
    private Text mensaje_error;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setMensaje_error(String mensaje) {
       mensaje_error.setText(mensaje);
    }

    @FXML
    private void Accion_boton_aceptar(ActionEvent event) {
        Metodos.closeEffect(root);
    }
    
    
    
    
}
