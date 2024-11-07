/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import com.jfoenix.controls.JFXButton;
import es.manueldonoso.academy.modelos.Usuario;
import es.manueldonoso.academy.util.ConexionBDLocal;
import es.manueldonoso.academy.util.Metodos;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class Asignar_asignaturasController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private VBox vBox_Act;
    @FXML
    private VBox VBox_des;
    
    private Usuario usuario;
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_aceptar(ActionEvent event) {
        Metodos.closeEffect(root);
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        System.out.println("obteniendo los datos de las asignaturas");
        System.out.println(this.usuario.toString());
        anadirBotonesAsignar();
        
       
    }
    
    private void anadirBotonesAsignar(){
        ResultSet Rs_act = ConexionBDLocal.asignaturasAct();
        System.out.println("ACTIVADAS");
        
// asignaturas que se pueden asignar
        try {
            while (Rs_act.next()) {
                // Obtener los datos de cada columna

                String asignatura = Rs_act.getString("asignatura");
                if(!usuario.getAsignaturas().contains(asignatura)){
                VBox_des.getChildren().add(crearbtnAsignable(asignatura));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ModificarEstadoAsignaturasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //asignaturas asignadas
        
        ArrayList lista = usuario.getAsignaturas();
        
        for (Object object : lista) {
           vBox_Act.getChildren().add(crearbtnAsignadas((String) object));
            
            
        }
        
        
    }
    
    
    private JFXButton crearbtnAsignable(String asignatura){
     JFXButton btn = new JFXButton(asignatura);
                btn.setFocusTraversable(false);
                btn.setOnAction((t) -> {
                   
                    VBox_des.getChildren().remove(btn);
                    vBox_Act.getChildren().add(crearbtnAsignadas(asignatura));
                    usuario.addAsignatura(asignatura);
                  
                    
                });
        return btn;
    }
    
    /**
     * 
     * @param asignatura
     * @return 
     */
     private JFXButton crearbtnAsignadas(String asignatura){
     JFXButton btn = new JFXButton(asignatura);
                btn.setFocusTraversable(false);
                btn.setOnAction((t) -> {
                    
                    vBox_Act.getChildren().remove(btn);
                    VBox_des.getChildren().add(crearbtnAsignable(asignatura));
                    usuario.ElininarAsignatura(asignatura);
                   
                    
                });
        return btn;
    }
}
