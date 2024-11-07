/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package es.manueldonoso.academy.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import es.manueldonoso.academy.modelos.Usuario;
import es.manueldonoso.academy.util.ConexionBDLocal;
import es.manueldonoso.academy.util.Metodos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author "Manuel Jesús Donoso Pérez";
 */
public class BuscaUsuarioController implements Initializable {

    @FXML
    private VBox root;
    @FXML
    private JFXTextField tf_Nombre;
    @FXML
    private JFXTextField tf_Apellidos;
    @FXML
    private JFXTextField tf_Direccion;
    @FXML
    private JFXTextField tf_Email;
    @FXML
    private JFXTextField tf_Telefono;
    @FXML
    private JFXTextField tf_Usuario;
    @FXML
    private TableView<Usuario> tabla_admin;
    @FXML
    private TableColumn<Usuario, String> colum_usuario_admin;
    @FXML
    private TableColumn<Usuario, Void> columna_Accion_administrador;
    @FXML
    private TableView<Usuario> tabla_profesor;
    @FXML
    private TableColumn<Usuario, String> usuario_profesor;
    @FXML
    private TableColumn<Usuario, Void> accion_profesor;
    @FXML
    private TableView<Usuario> tabla_alumnoa;
    @FXML
    private TableColumn<Usuario, String> Usuario_alumno;
    @FXML
    private TableColumn<Usuario, Void> accion_alumno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //ConexionBDLocal.buscarAdmin(tf_Nombre.getText(), tf_Apellidos.getText(), tf_Telefono.getText(), tf_Email.getText(), tf_Usuario.getText(), "1",tf_Direccion.getText());
        // colum_usuario_admin.setCellValueFactory(new PropertyValueFactory<>("nombre") );
        colum_usuario_admin.setCellValueFactory(cellData -> {
            Usuario usuario = cellData.getValue();
            String nombreCompleto = usuario.getNombre() + " " + usuario.getApellidos();
            return new SimpleStringProperty(nombreCompleto);
        });
        columna_Accion_administrador.setCellFactory(createButtonCellFactory());

        usuario_profesor.setCellValueFactory(cellData -> {
            Usuario usuario = cellData.getValue();
            String nombreCompleto = usuario.getNombre() + " " + usuario.getApellidos();
            return new SimpleStringProperty(nombreCompleto);
        });
        accion_profesor.setCellFactory(createButtonCellFactory());
        
       Usuario_alumno.setCellValueFactory(cellData -> {
            Usuario usuario = cellData.getValue();
            String nombreCompleto = usuario.getNombre() + " " + usuario.getApellidos();
            return new SimpleStringProperty(nombreCompleto);
        });
        accion_alumno.setCellFactory(createButtonCellFactory());


        buscarUsuarios();
    }

    @FXML
    private void btn_cerrar(ActionEvent event) {
        Metodos.closeEffect(root);
    }

    @FXML
    private void buscarUsuarios(KeyEvent event) {
        buscarUsuarios();
    }

    private void buscarUsuarios() {
        System.out.println("Buscando...");
        ObservableList<Usuario> dataAdmin = ConexionBDLocal.buscarUser(tf_Nombre.getText(), tf_Apellidos.getText(), tf_Telefono.getText(), tf_Email.getText(), tf_Usuario.getText(), "1", tf_Direccion.getText());
        tabla_admin.setItems(dataAdmin);

        ObservableList<Usuario> datapro = ConexionBDLocal.buscarUser(tf_Nombre.getText(), tf_Apellidos.getText(), tf_Telefono.getText(), tf_Email.getText(), tf_Usuario.getText(), "2", tf_Direccion.getText());
        tabla_profesor.setItems(datapro);

        ObservableList<Usuario> dataalum = ConexionBDLocal.buscarUser(tf_Nombre.getText(), tf_Apellidos.getText(), tf_Telefono.getText(), tf_Email.getText(), tf_Usuario.getText(), "3", tf_Direccion.getText());
        tabla_alumnoa.setItems(dataalum);
    }

    private Callback<TableColumn<Usuario, Void>, TableCell<Usuario, Void>> createButtonCellFactory() {
        return column -> new TableCell<Usuario, Void>() {
            JFXButton button = new JFXButton("Editar");

            {
                // Cargar la imagen
                Image image = new Image(getClass().getResourceAsStream("/images/app/edit_log.png"));
                ImageView imageView = new ImageView(image);

                // Configurar el tamaño de la imagen si es necesario
                imageView.setFitWidth(16);
                imageView.setFitHeight(16);

                // Asignar el ImageView al botón
                button.setGraphic(imageView);

                button.setOnAction(event -> {
                    Usuario usuario = getTableView().getItems().get(getIndex());
                    System.out.println("Botón clicado en: " + usuario.getNombre());
                    // Aquí puedes definir la acción que deseas realizar al presionar el botón
                    Metodos.nuevoUsuario(root, usuario);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        };
    }

}
