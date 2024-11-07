/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import animatefx.animation.Swing;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academy.modelos.Usuario;
import es.manueldonoso.academy.util.ConexionBDLocal;
import es.manueldonoso.academy.util.Metodos;
import es.manueldonoso.academy.util.Session;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Stage;

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
    private JFXButton btn_cancelar;
    private JFXButton btn_aceptar;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btn_avalidar;
    @FXML
    private ImageView imageView;
    private boolean eliminarFoto;

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
        File file = new File("src/main/resources/images/users/" + Session.getUsuarioLogin().getId() + ".jpg");

        if (file.exists()) {
            Metodos.imagenView_cambiarImage(this.getClass(), imageView, file.getPath());
        } else {
            Metodos.imagenView_cambiarImage(this.getClass(), imageView, "src/main/resources/images/incorgnito.png");
        }
      eliminarFoto=false;
    }

    private BooleanProperty estadoCamara = new SimpleBooleanProperty(false);
    private AtomicReference<Webcam> selWebCam = new AtomicReference<>(null); // Usar AtomicReference para Webcam
    private AtomicReference<BufferedImage> bufferedImage = new AtomicReference<>(null); // Usar AtomicReference para BufferedImage
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<>();

    @FXML
    private void TomarFoto(ActionEvent event) {

        System.out.println(selWebCam.get());
        if (selWebCam.get() != null) {
            if (selWebCam.get().isOpen()) {
                System.out.println("enable");
                btn_eliminar_Fotps.setDisable(false);
                btn_cargarF.setDisable(false);
            } else {
                btn_eliminar_Fotps.setDisable(true);
                btn_cargarF.setDisable(true);
            }
        } else {
            btn_eliminar_Fotps.setDisable(true);
            btn_cargarF.setDisable(true);
        }
// Desenlazar la propiedad de imagen si está enlazada
        imageView.imageProperty().unbind();

        // Establecer la imagen de carga
        imageView.setImage(new Image(getClass().getResourceAsStream("/images/app/cargando.gif")));

        // Llamar al método de captura de foto
        Metodos.CapturarFoto(imageView, estadoCamara, selWebCam, bufferedImage, imageProperty, "temp/foto.jpg");

        // Volver a enlazar la propiedad de imagen
        imageView.imageProperty().bind(imageProperty);
        btn_avalidar.setDisable(false);
    }

    @FXML
    private void cancelar_registro(ActionEvent event) {
        Metodos.cerrarCamara(estadoCamara, selWebCam);
        Metodos.closeEffect(root);
    }

    @FXML
    private void btn_aceptar(ActionEvent event) {
        eliminarFoto(eliminarFoto);
        File f = new File("src/main/resources/temp/foto.jpg");
        if (f.exists()) {
            Metodos.copyFile(f, "src/main/resources/images/users/" + Session.getUsuarioLogin().getId() + ".jpg");
            f.delete();
        }
        
         guardardatos();
           Metodos.cerrarCamara(estadoCamara, selWebCam);
        Metodos.closeEffect(root);
    }

    @FXML
    private void acep_btn_enable() {
        btn_avalidar.setDisable(false);
       
    }

    @FXML
    private void imagen_default(ActionEvent event) {
        Metodos.imagenView_cambiarImage(this.getClass(), imageView, "src/main/resources/images/incorgnito.png");
        eliminarFoto=true;
        btn_avalidar.setDisable(false);

    }

    @FXML
    private void btn_cargarFoto(ActionEvent event) {

        File f = Metodos.openImageFileChooser();
        System.out.println(f);
        if (f != null) {
            if (Metodos.copyFile(f, "src/main/resources/temp/foto.jpg")) {
                Metodos.imagenView_cambiarImage(this.getClass(), imageView, "src/main/resources/temp/foto.jpg");
                btn_avalidar.setDisable(false);
            }
        }
    }

    private void guardardatos() {
        Usuario user = Session.getUsuarioLogin();
        user.setNombre(txt_nombre.getText());
        user.setApellidos(txt_apellidos.getText());
        user.setDireccion(txt_direccion.getText());
        user.setEmail(txt_email.getText());
        user.setTelefono(txt_telefono.getText());
        File file = new File("src/main/resources/images/users/" + Session.getUsuarioLogin().getId() + ".jpg");
        if (file.exists()) {
            user.setUrlFoto("src/main/resources/images/users/" + Session.getUsuarioLogin().getId() + ".jpg");
        } else {
            user.setUrlFoto(null);
        }
          ConexionBDLocal.GuardarUsuario(user);
   
    }
    
    public static void eliminarFoto(boolean eliminar){
        File file = new File("src/main/resources/images/users/" + Session.getUsuarioLogin().getId() + ".jpg");
        if (eliminar) {
            file.delete();
        }
    }
}
