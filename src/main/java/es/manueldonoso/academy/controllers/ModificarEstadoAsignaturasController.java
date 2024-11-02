/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import com.jfoenix.controls.JFXButton;
import es.manueldonoso.academy.util.ConexionBDLocal;
import es.manueldonoso.academy.util.Metodos;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author donpe
 */
public class ModificarEstadoAsignaturasController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private VBox vBox_Act;
    @FXML
    private VBox VBox_des;

    private ResultSet Rs_act;
    private ResultSet Rs_Des_act;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Rs_act = ConexionBDLocal.asignaturasAct();
        System.out.println("ACTIVADAS");

        try {
            while (Rs_act.next()) {
                // Obtener los datos de cada columna

                String asignatura = Rs_act.getString("asignatura");

               
                vBox_Act.getChildren().add(crearbtnact(asignatura));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ModificarEstadoAsignaturasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Rs_Des_act = ConexionBDLocal.asignaturasDes_Act();
        System.out.println("DESACTIVADAS");

        try {
            while (Rs_Des_act.next()) {
                // Obtener los datos de cada columna

                String asignatura = Rs_Des_act.getString("asignatura");

              

                VBox_des.getChildren().add(crearbtnDesact(asignatura));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ModificarEstadoAsignaturasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ConexionBDLocal.CerrarConexion();

    }

    private JFXButton crearbtnact(String asignatura){
     JFXButton btn = new JFXButton(asignatura);
                btn.setFocusTraversable(false);
                btn.setOnAction((t) -> {
                    ConexionBDLocal.DesactivarAsignatura(asignatura);
                    vBox_Act.getChildren().remove(btn);
                    VBox_des.getChildren().add( crearbtnDesact(asignatura));
                    
                });
        return btn;
    }
    
     private JFXButton crearbtnDesact(String asignatura){
     JFXButton btn = new JFXButton(asignatura);
                btn.setFocusTraversable(false);
                btn.setOnAction((t) -> {
                    ConexionBDLocal.ActivarAsignatura(asignatura);
                    VBox_des.getChildren().remove(btn);
                    vBox_Act.getChildren().add(crearbtnact(asignatura));
                    
                });
        return btn;
    }

    @FXML
    private void btn_aceptar(ActionEvent event) {
        
        Metodos.closeEffect(root);
    }
}
