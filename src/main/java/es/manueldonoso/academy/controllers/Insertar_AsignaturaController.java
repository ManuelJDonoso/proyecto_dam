/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academy.util.ConexionBDLocal;
import es.manueldonoso.academy.util.Metodos;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    @FXML
    private VBox vboxLista;
    @FXML
    private Label lb_subtitulo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ConexionBDLocal db=new ConexionBDLocal();
        
        ArrayList lista=db.listarAsignaturas();
          // Ordenar alfabéticamente
        lista.sort(Comparator.naturalOrder());
       
        
        //lista.stream().forEach(elemento -> System.out.println(elemento));
        lista.stream().forEach(new Consumer() {
            @Override
            public void accept(Object elemento) {
                 Label lb =new Label((String) " * " + elemento  );
                vboxLista.getChildren().add(lb);
            }
        });
    }    

    
    @FXML
    private void AgregarAsignatura(ActionEvent event) {
        ConexionBDLocal db =new ConexionBDLocal();
       int i= db.AnadirAsignatura(txt_asignatura.getText(),root);
       if (i==1){Metodos.closeEffect(root);}
        
    }
    
}
