/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academy.util;

import com.github.sarxos.webcam.Webcam;
import es.manueldonoso.academy.Main;
import es.manueldonoso.academy.controllers.AltaUsuarioController;
import es.manueldonoso.academy.controllers.Asignar_asignaturasController;
import es.manueldonoso.academy.controllers.MensajeDeErrorController;
import es.manueldonoso.academy.modelos.Usuario;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
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
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;

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
            stage.initStyle(StageStyle.UNDECORATED);
            darMovimientoStage(stage);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void nuevoUsuario(VBox root, Usuario user) {
        try {
            Stage primaryStage = new Stage();
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            Stage stage = new Stage();
            loader.setLocation(Main.class.getResource("/vistas/AltaUsuario.fxml"));

            // Ventana a cargar
            AnchorPane ventana = (AnchorPane) loader.load();

            // Obtengo el controlador y le paso el objeto Usuario
            AltaUsuarioController controller = loader.getController();
            controller.setUsuario(user);

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setIconified(false);
            stage.initStyle(StageStyle.UNDECORATED);
            darMovimientoStage(stage);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void asignarAsignatas(AnchorPane root, Usuario user) {
        try {
            Stage primaryStage = new Stage();
            // Cargo la ventana inicial
            FXMLLoader loader = new FXMLLoader();
            Stage stage = new Stage();
            loader.setLocation(Main.class.getResource("/vistas/Asignar_asignaturas.fxml"));

            // Ventana a cargar
            AnchorPane ventana = (AnchorPane) loader.load();

            // Obtengo el controlador y le paso el objeto Usuario
            Asignar_asignaturasController controller = loader.getController();
            controller.setUsuario(user);

            // Creo la escena
            Scene scene = new Scene(ventana);

            // Modifico el stage
            stage.setScene(scene);
            stage.initOwner(root.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.setIconified(false);
            //stage.initStyle(StageStyle.UNDECORATED);
            darMovimientoStage(stage);
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

            //  primaryStage.setMaximized(true);
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

    public static void AbrirBuscarUsuario(Pane root) {
        System.out.println("abrir buscar formulario");
        Stage primaryStage = new Stage();
        // Cargo la ventana inicial
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/vistas/BuscaUsuario.fxml"));

        // Ventana a cargar
        VBox ventana;
        try {
            ventana = (VBox) loader.load();
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
            stage.setResizable(true);
            stage.setIconified(false);
//            stage.setTitle("Insertar asignatura");
//
//            // Configurar el ícono del Stage
//            String urlLogo = "/images/app/edit_log.png"; // Ruta del icono
//
//            icon_stag_person(urlLogo, stage);
            stage.initStyle(StageStyle.UNDECORATED);
            darMovimientoStage(stage);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    public static void imagenView_cambiarImage(Class<?> claseReferencia, ImageView imageView, String Url) {
        try {
            File file = new File(Url);

            Image image = new Image(file.toURI().toString());

            imageView.imageProperty().unbind();

            imageView.setImage(image);

        } catch (Exception e) {
            System.out.println("Error cargando la imagen: " + e.getMessage());
        }
    }

    public static byte[] ImageToByte(Image image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (image != null) {
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            try {
                ImageIO.write(bImage, "jpg", baos);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        byte[] res = baos.toByteArray();

        try {
            baos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static void saveImageToFile(Image image, String filePath) {
        if (image != null) {
            // Convertir la imagen de JavaFX a BufferedImage
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            try {
                // Guardar la imagen en la ruta especificada
                File outputFile = new File(filePath);
                ImageIO.write(bImage, "jpg", outputFile);
                System.out.println("Imagen guardada en: " + filePath);
            } catch (IOException e) {
                System.out.println("Error al guardar la imagen: " + e.getMessage());
            }
        } else {
            System.out.println("La imagen es nula, no se puede guardar.");
        }
    }

    public static File openImageFileChooser() {
        Stage stage = null;
        FileChooser fileChooser = new FileChooser();

        // Configura el título del diálogo
        fileChooser.setTitle("Seleccionar imagen");

        // Agrega filtros de extensión para limitar a archivos JPG y PNG
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Archivos JPG (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("Archivos PNG (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        // Muestra el diálogo de selección de archivo y obtiene el archivo seleccionado
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Retorna el archivo seleccionado (puede ser null si el usuario canceló)
        return selectedFile;
    }

    public static void copyFile(String sourcePath, String destinationPath) {
        Path source = Path.of(sourcePath);
        Path destination = Path.of(destinationPath);

        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado correctamente a: " + destinationPath);
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }

    public static boolean copyFile(File f, String Destino) {
        // Ruta del archivo de origen
        boolean copia = false;

        Path source = Path.of(f.getAbsolutePath());

        // Ruta de destino relativa al proyecto, renombrando el archivo
        Path destination = Path.of(Destino);

        try {
            // Crear el directorio "temp" si no existe
            if (!Files.exists(destination.getParent())) {
                Files.createDirectories(destination.getParent());
            }

            // Copiar el archivo y renombrarlo en la nueva ubicación
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Archivo copiado correctamente a: " + destination.toAbsolutePath());
            copia = true;
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
        return copia;
    }

    public static void CapturarFoto(ImageView imageView, BooleanProperty estadoCamara,
            AtomicReference<Webcam> selWebCam, AtomicReference<BufferedImage> bufferedImage,
            ObjectProperty<Image> imageProperty, String path) {

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
                                            // Guardar la imagen en 
                                            guardarImagen(bufferedImage.get(), path);
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

    private static void guardarImagen(BufferedImage imagen, String relativePath) {
        // Obtener la ruta absoluta del archivo en el sistema de archivos
        String rutaAbsoluta = new File("src/main/resources/" + relativePath).getAbsolutePath();
        File outputFile = new File(rutaAbsoluta);

        try {
            // Asegúrate de que el directorio existe
            outputFile.getParentFile().mkdirs();
            ImageIO.write(imagen, "jpg", outputFile);
            System.out.println("Imagen guardada en: " + outputFile.getPath());
        } catch (IOException e) {
            System.out.println("Error al guardar la imagen: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    public static String generaNick (String nombre,String apellido){
        String usuario=nombre.substring(0,3)+ apellido.substring(0, 3);
        usuario=usuario.toUpperCase();
        int i=0;
        try {
            while(ConexionBDLocal.isNick(usuario)){
            i++;
            usuario=usuario.substring(0, 5)+i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(usuario);
    return usuario;
    }
}
