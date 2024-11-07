/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academy.modelos.Usuario;
import es.manueldonoso.academy.util.ConexionBDLocal;
import es.manueldonoso.academy.util.Metodos;
import java.awt.image.BufferedImage;
import java.io.File;
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
import javafx.scene.text.Text;

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
    
    private boolean eliminarFoto;
    @FXML
    private Text txt_titulo;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXButton btn_asignaturas;
    
    private Usuario usuario;
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        System.out.println("se añadio datos del usuario");
        System.out.println(this.usuario.toString());
        CargaUsuario();
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  System.out.println(usuario.toString());
        usuario = new Usuario();
        
        rb_admin.selectedProperty().addListener((ov, t, t1) -> {
            if (t1) {
                visible_asignaturas();
            }
        });
        rb_alumno.selectedProperty().addListener((ov, t, t1) -> {
            if (t1) {
                visible_asignaturas();
            }
        });
        rb_profesor.selectedProperty().addListener((ov, t, t1) -> {
            if (t1) {
                visible_asignaturas();
            }
        });
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
    
    private void CargaUsuario() {
        txt_nombre.setText(usuario.getNombre());
        txt_apellidos.setText(usuario.getApellidos());
        txt_direccion.setText(usuario.getDireccion());
        txt_email.setText(usuario.getEmail());
        txt_telefono.setText(usuario.getTelefono());
        switch (usuario.getTipo()) {
            case 1:
                rb_admin.setSelected(true);
                rb_alumno.setSelected(false);
                rb_profesor.setSelected(false);
                break;
            case 2:
                rb_admin.setSelected(false);
                rb_alumno.setSelected(false);
                rb_profesor.setSelected(true);
                break;
            case 3:
                rb_admin.setSelected(false);
                rb_alumno.setSelected(true);
                rb_profesor.setSelected(false);
                break;
            default:
            
        }
        
        File file = new File("src/main/resources/images/users/" + usuario.getId() + ".jpg");
        
        if (file.exists()) {
            Metodos.imagenView_cambiarImage(this.getClass(), imageView, file.getPath());
        } else {
            Metodos.imagenView_cambiarImage(this.getClass(), imageView, "src/main/resources/images/incorgnito.png");
        }
        
        txt_titulo.setText("DATOS USUARIO " + usuario.getNick());
        eliminarFoto = false;
        
    }
    
    @FXML
    private void btn_asignaturas(ActionEvent event) {
        Metodos.asignarAsignatas(root, usuario);
    }
    
    private void visible_asignaturas() {
        if (rb_admin.isSelected()) {
            btn_asignaturas.setVisible(false);
        } else {
            btn_asignaturas.setVisible(true);
        }
    }
    
    @FXML
    private void btn_aceptar(ActionEvent event) {
        obtenerDatosUser();
        if (usuario.getId() == 0) {
           // usuario nuevo
            
            System.out.println("guardar Usuario");
            System.out.println(usuario.toString());
             ConexionBDLocal.crearUsuarioNuevo(usuario);
        } else {
            //actualizar datos
            
        }
        
    }
    
    private void obtenerDatosUser() {
        usuario.setNombre(txt_nombre.getText());
        usuario.setApellidos(txt_apellidos.getText());
        usuario.setDireccion(txt_direccion.getText());
        usuario.setEmail(txt_email.getText());
        usuario.setTelefono(txt_telefono.getText());
        if (rb_admin.isSelected()) {
            usuario.setTipo(1);
        } else if (rb_profesor.isSelected()) {
            usuario.setTipo(2);
        } else {
            usuario.setTipo(3);
        }
       
        
    }
    
}
