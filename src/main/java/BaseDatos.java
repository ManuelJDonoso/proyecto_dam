/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.*;
import java.util.List;

/**
 * Clase para gestionar la conexión y operaciones en la base de datos mirecetario.
 * La base de datos contiene tres tablas:
 * - receta: almacena las recetas con sus datos y una imagen.
 * - ingredientes: almacena los ingredientes individuales.
 * - receta_ingrediente: tabla de relación entre recetas e ingredientes.
 * 
 * @author "Rocio Piñero Luca";
 */
public class BaseDatos {
    private static final String URL = "jdbc:mysql://localhost:3306/mirecetario";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Establece una conexión con la base de datos.
     *
     * @return Connection objeto de conexión a la base de datos.
     * @throws SQLException si ocurre un error al conectar.
     */
    private static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Inserta una nueva receta en la tabla receta junto con sus ingredientes.
     *
     * @param nombreReceta   Nombre de la receta.
     * @param preparacion    Descripción de la preparación.
     * @param tiempo         Tiempo de preparación en minutos.
     * @param comensales     Número de comensales.
     * @param foto           Imagen de la receta en formato BLOB.
     * @param ingredientes   Lista de nombres de ingredientes para la receta.
     * @throws SQLException si ocurre un error en la inserción.
     */
    public static void insertarReceta(String nombreReceta, String preparacion, int tiempo, int comensales, byte[] foto, List<String> ingredientes) throws SQLException {
        String recetaQuery = "INSERT INTO receta (receta, preparacion, tiempo, comensales, foto) VALUES (?, ?, ?, ?, ?)";
        String recetaIngredienteQuery = "INSERT INTO receta_ingrediente (receta, ingrediente) VALUES (?, ?)";

        try (Connection conn = conectar();
             PreparedStatement recetaStmt = conn.prepareStatement(recetaQuery);
             PreparedStatement recetaIngredienteStmt = conn.prepareStatement(recetaIngredienteQuery)) {

            conn.setAutoCommit(false);

            // Insertar la receta
            recetaStmt.setString(1, nombreReceta);
            recetaStmt.setString(2, preparacion);
            recetaStmt.setInt(3, tiempo);
            recetaStmt.setInt(4, comensales);
            recetaStmt.setBytes(5, foto);
            recetaStmt.executeUpdate();

            // Insertar los ingredientes asociados
            for (String ingrediente : ingredientes) {
                recetaIngredienteStmt.setString(1, nombreReceta);
                recetaIngredienteStmt.setString(2, ingrediente);
                recetaIngredienteStmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Inserta un nuevo ingrediente en la tabla ingredientes.
     *
     * @param ingrediente Nombre del ingrediente.
     * @throws SQLException si ocurre un error en la inserción.
     */
    public static void insertarIngrediente(String ingrediente) throws SQLException {
        String query = "INSERT INTO ingredientes (ingrediente) VALUES (?)";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, ingrediente);
            stmt.executeUpdate();
        }
    }

    /**
     * Realiza una búsqueda de recetas que contengan uno o más ingredientes específicos.
     *
     * @param ingredientes Lista de ingredientes a buscar.
     * @return ResultSet con las recetas que contienen los ingredientes buscados.
     * @throws SQLException si ocurre un error en la consulta.
     */
    public static ResultSet busquedaPorIngredientes(List<String> ingredientes) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT DISTINCT r.receta, r.preparacion, r.tiempo, r.comensales FROM receta r ");
        query.append("JOIN receta_ingrediente ri ON r.receta = ri.receta WHERE ri.ingrediente IN (");
        for (int i = 0; i < ingredientes.size(); i++) {
            query.append("?");
            if (i < ingredientes.size() - 1) query.append(", ");
        }
        query.append(")");

        Connection conn = conectar();
        PreparedStatement stmt = conn.prepareStatement(query.toString());
        for (int i = 0; i < ingredientes.size(); i++) {
            stmt.setString(i + 1, ingredientes.get(i));
        }
        return stmt.executeQuery();
    }

    /**
     * Realiza una búsqueda de recetas por nombre.
     *
     * @param nombreReceta Nombre de la receta a buscar.
     * @return ResultSet con las recetas que coinciden con el nombre buscado.
     * @throws SQLException si ocurre un error en la consulta.
     */
    public static ResultSet busquedaPorNombreReceta(String nombreReceta) throws SQLException {
        String query = "SELECT * FROM receta WHERE receta LIKE ?";
        Connection conn = conectar();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, "%" + nombreReceta + "%");
        return stmt.executeQuery();
    }

    /**
     * Realiza una búsqueda de recetas que pueden prepararse en menos o igual tiempo del especificado.
     *
     * @param tiempoMax Tiempo máximo de preparación en minutos.
     * @return ResultSet con las recetas que se pueden preparar en el tiempo especificado.
     * @throws SQLException si ocurre un error en la consulta.
     */
    public static ResultSet busquedaPorTiempo(int tiempoMax) throws SQLException {
        String query = "SELECT * FROM receta WHERE tiempo <= ?";
        Connection conn = conectar();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, tiempoMax);
        return stmt.executeQuery();
    }
}