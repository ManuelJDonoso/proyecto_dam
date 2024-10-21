package es.manueldonoso.academy;

import es.manueldonoso.academy.util.ConexionBDLocal;
import es.manueldonoso.academy.util.Metodos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * JavaFX Main
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
  System.out.println("cargo el ventana login");
        Metodos.cargarLogin();

     
       

    }

   
   }
