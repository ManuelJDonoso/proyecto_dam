/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academy.modelos.Usuario;
import es.manueldonoso.academy.util.ConexionBDLocal;
import es.manueldonoso.academy.util.Metodos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import es.manueldonoso.academy.util.contrato;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Manuel Jesús Donoso Pérez
 */
public class ContratoController implements Initializable {

    @FXML
    private JFXTextArea TA_contrato;
    @FXML
    private JFXTextField tf_nombre;
    @FXML
    private JFXTextField tf_user;
    @FXML
    private JFXTextField tf_apellido;
    @FXML
    private JFXTextField tf_email;
    @FXML
    private JFXCheckBox ck_aceptar;
    @FXML
    private JFXButton btn_Finalizar;
    @FXML
    private GridPane root;
    @FXML
    private JFXButton btn_cancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TA_contrato.setText(contrato.getContrato());
    }    
    
    
    @FXML
    private void instalar(){
        if(ck_aceptar.isSelected()&& !tf_nombre.getText().isEmpty()&& !tf_apellido.getText().isEmpty()&& !tf_user.getText().isEmpty()&& !tf_email.getText().isEmpty()){
            btn_Finalizar.setDisable(false);
        }else{
             btn_Finalizar.setDisable(true);
        }
    }
    
    @FXML
    private void crearContrato(ActionEvent event){
        ConexionBDLocal cbl=new ConexionBDLocal();
        
        Usuario user= new Usuario();
        user.setNombre(tf_nombre.getText());
        user.setApellidos(tf_apellido.getText());
        user.setNick(tf_user.getText());
        user.setEmail(tf_email.getText());
        cbl.CrearAdministrativo(user);
        cbl.CrearDuenoContrato(user);
        Metodos.closeEffect(root);
       
    }
    
    @FXML
    private void cerrarventana(){
        Metodos.closeEffect(root);
    }
    
    
}
