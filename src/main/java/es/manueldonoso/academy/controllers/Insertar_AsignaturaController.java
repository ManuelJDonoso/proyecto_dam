/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academy.util.ConexionBDLocal;
import es.manueldonoso.academy.util.Metodos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author donpe
 */
public class Insertar_AsignaturaController implements Initializable {

    @FXML
    private JFXTextField txt_asignatura;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
    }    

    
    @FXML
    private void AgregarAsignatura(ActionEvent event) {
        ConexionBDLocal db =new ConexionBDLocal();
       int i= db.AnadirAsignatura(txt_asignatura.getText(),root);
       if (i==1){Metodos.closeEffect(root);}
        
    }
    
}
