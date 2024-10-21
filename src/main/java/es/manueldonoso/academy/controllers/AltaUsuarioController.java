/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academy.util.Metodos;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author donpe
 */
public class AltaUsuarioController implements Initializable {

    @FXML
    private AnchorPane root;
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
    private JFXButton btn_cancelar;
    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private RadioButton rb_admin;
    @FXML
    private ToggleGroup tipo;
    @FXML
    private RadioButton rb_profesor;
    @FXML
    private RadioButton rb_alumno;
    @FXML
    private ImageView iw;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void cancelar_registro(ActionEvent event) {
        Metodos.closeEffect(root);
    }

    private boolean estadoCamara = false;
    private Webcam selWebCam = null;
    private BufferedImage bufferedImage;
    // private ObjectProperty<Image>=new SimpleObjectProperty<>();

    @FXML
    private void TomarFoto(ActionEvent event) {

        if (Webcam.getWebcams().size() < 1) {

            System.out.println("no hay camaras");
            Alert a = new Alert(Alert.AlertType.INFORMATION, "No hay camaras disponibles", ButtonType.OK);
            a.showAndWait();
            return;
        }
     
    }

}
