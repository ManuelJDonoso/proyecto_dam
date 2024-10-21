/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academy.util;

import es.manueldonoso.academy.Main;
import es.manueldonoso.academy.modelos.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author donpe
 */
public class Metodos {

    public static void closeEffect(Node node) {
        final Stage stage = (Stage) node.getScene().getWindow();
        ScaleTransition st = new ScaleTransition(Duration.millis(500), node);
        st.setToX(0);
        st.setToY(0);
        st.setToZ(0);
        st.play();
        st.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                stage.close();

            }
        });
    }

    public static void MisDatos(Pane root) {

        Stage primaryStage = new Stage();
        // Cargo la ventana inicial
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/vistas/PerfilUsuario.fxml"));

        // Ventana a cargar
        AnchorPane ventana;
        try {
            ventana = (AnchorPane) loader.load();
            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Mis Datos");
            // primaryStage.setMaximized(true);

            Stage stage = new Stage();
            // Modifico el stage
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }

        //  primaryStage.show();
    }

    public static void nuevoUsuario(VBox root) {
        try {
            Stage primaryStage = new Stage();
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            Stage stage = new Stage();
            loader.setLocation(Main.class.getResource("/vistas/AltaUsuario.fxml"));

            // Ventana a cargar
            AnchorPane ventana = (AnchorPane) loader.load();

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void cargarLogin() {
        Stage primaryStage = new Stage();
        try {
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vistas/Login.fxml"));

            // Ventana a cargar
            HBox ventana = (HBox) loader.load();

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
            // primaryStage.setMaximized(true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.show();
    }

    public static void CargarDasboard(Usuario user,Node root) {
        Session.setUsuarioLogin(user);
        try {
            Stage primaryStage = new Stage();
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            Scene scene;
            switch (user.getTipo()) {
                case 1:
                    loader.setLocation(Main.class.getResource("/vistas/Dashboard_admin.fxml"));
                    primaryStage.setTitle("Administrador: " + user.getNick());
                    // Ventana a cargar
                    VBox ventana = (VBox) loader.load();

                    // Creo la escena
                    scene = new Scene(ventana);
                    break;
                case 2:
                    loader.setLocation(Main.class.getResource("/vistas/Dashboard_profesor.fxml"));
                    primaryStage.setTitle("Profesor: " + user.getNick());
                    // Ventana a cargar
                    AnchorPane ventana2 = (AnchorPane) loader.load();

                    // Creo la escena
                    scene = new Scene(ventana2);
                    break;

                case 3:
                    loader.setLocation(Main.class.getResource("/vistas/Dashboard_alumno.fxml"));
                    primaryStage.setTitle("Alumno: " + user.getNick());
                    // Ventana a cargar
                    AnchorPane ventana3 = (AnchorPane) loader.load();

                    // Creo la escena
                    scene = new Scene(ventana3);
                    break;
                default:
                    throw new AssertionError();
            }

            // Modifico el stage
            primaryStage.setScene(scene);

            primaryStage.setMaximized(true);

            Metodos.closeEffect(root);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
