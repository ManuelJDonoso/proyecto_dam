/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academy.util;

import com.github.sarxos.webcam.Webcam;
import es.manueldonoso.academy.Main;
import es.manueldonoso.academy.controllers.MensajeDeErrorController;
import es.manueldonoso.academy.modelos.Usuario;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
            stage.initStyle(StageStyle.UNDECORATED);
            // Configurar el ícono del Stage
            String urlLogo = "/images/app/MisDatos.png"; // Ruta del icono

            icon_stag_person(urlLogo, stage);
            //  stage.initStyle(StageStyle.UNDECORATED);
            darMovimientoStage(stage);
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

    public static void CargarDasboard(Usuario user, Node root) {
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

    public static void InsertarAsignatura(Pane root) {

        Stage primaryStage = new Stage();
        // Cargo la ventana inicial
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/vistas/Insertar_Asignatura.fxml"));

        // Ventana a cargar
        AnchorPane ventana;
        try {
            ventana = (AnchorPane) loader.load();
            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            primaryStage.setScene(scene);

            // primaryStage.setMaximized(true);
            Stage stage = new Stage();
            // Modifico el stage
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.setTitle("Insertar asignatura");

            // Configurar el ícono del Stage
            String urlLogo = "/images/app/edit_log.png"; // Ruta del icono

            icon_stag_person(urlLogo, stage);
            //  stage.initStyle(StageStyle.UNDECORATED);
            darMovimientoStage(stage);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }

        //  primaryStage.show();
    }

    public static void CargarModificarEstadoAsignaturas(Pane root) {

        Stage primaryStage = new Stage();
        // Cargo la ventana inicial
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/vistas/ModificarEstadoAsignaturas.fxml"));

        // Ventana a cargar
        AnchorPane ventana;
        try {
            ventana = (AnchorPane) loader.load();
            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            primaryStage.setScene(scene);

            // primaryStage.setMaximized(true);
            Stage stage = new Stage();
            stage.setTitle("Modificar estado de las asignaturas");
            stage.initStyle(StageStyle.UNDECORATED);

            // Modifico el stage
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setIconified(false);
            darMovimientoStage(stage);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }

        //  primaryStage.show();
    }

    public static void error(Pane root, String mensaje) {

        Stage primaryStage = new Stage();
        // Cargo la ventana inicial
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/vistas/Mensaje_error.fxml"));

        // Ventana a cargar
        AnchorPane ventana;
        try {
            ventana = (AnchorPane) loader.load();
            MensajeDeErrorController controller = loader.getController();

            controller.setMensaje_error(mensaje);

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("error");
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

    private static double xOffset = 0;
    private static double yOffset = 0;

    public static void darMovimientoStage(Stage stage) {

        stage.getScene().setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        stage.getScene().setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
    }

    private static void icon_stag_person(String url, Stage stage) {
        // Configurar el ícono del Stage
        String urlLogo = url; // Ruta del icono
        try {
            Image icon = new Image(Metodos.class.getResource(urlLogo).toExternalForm());
            stage.getIcons().add(icon);
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen desde la URL: " + urlLogo);
            e.printStackTrace();
        }
    }

    public static void CapturarFoto(ImageView imageView, BooleanProperty estadoCamara,
            AtomicReference<Webcam> selWebCam, AtomicReference<BufferedImage> bufferedImage,
            ObjectProperty<Image> imageProperty) {

        if (Webcam.getWebcams().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "No hay cámaras web disponibles", ButtonType.OK);
            alert.showAndWait();
            System.out.println("No hay cámaras");
            return;
        }

        if (!estadoCamara.get()) {
            imageView.imageProperty().unbind();

            Task<Void> webCamInitializer = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    if (selWebCam.get() == null || !selWebCam.get().isOpen()) {
                        selWebCam.set(Webcam.getWebcams().get(0));
                        selWebCam.get().open();
                    }
                    estadoCamara.set(true);

                    Task<Void> task = new Task<>() {
                        @Override
                        protected Void call() throws Exception {
                            while (estadoCamara.get()) {
                                try {
                                    BufferedImage currentImage = selWebCam.get().getImage();
                                    bufferedImage.set(currentImage);

                                    if (bufferedImage.get() != null) {
                                        Platform.runLater(() -> {
                                            final Image mainImage = SwingFXUtils.toFXImage(bufferedImage.get(), null);
                                            imageProperty.set(mainImage);
                                        });
                                        bufferedImage.get().flush();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            return null;
                        }
                    };

                    Thread th = new Thread(task);
                    th.setDaemon(true);
                    th.start();
                    imageView.imageProperty().bind(imageProperty);
                    return null;
                }
            };

            Thread webcamThread = new Thread(webCamInitializer);
            webcamThread.setDaemon(true);
            webcamThread.start();
        } else {
            estadoCamara.set(false);
            if (selWebCam.get() != null) {
                selWebCam.get().close();
            }
        }
    }

    public static void cerrarCamara(BooleanProperty estadoCamara, AtomicReference<Webcam> selWebCam) {
        estadoCamara.set(false);
        if (selWebCam.get() != null) {
            selWebCam.get().close();
        }
    }

    public static void imagenView_cambiarImage(Class<?> claseReferencia,ImageView imageView, String Url) {
     try {
        imageView.imageProperty().unbind();
        imageView.setImage(new Image(claseReferencia.getResource(Url).toExternalForm()));

    } catch (Exception e) {
        System.out.println("Error cargando la imagen: " + e.getMessage());
    }
    }
}
