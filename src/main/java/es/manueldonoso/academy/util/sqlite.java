/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rocio 
 */
public class sqlite {
    private static final String DB_URL = "jdbc:sqlite:src/main/resources/db/BaseDatos.db";
    private static Connection conexion = null;
    public static Statement stm = null;
    
    /**
     * abre conexion con la base de datos
     */
    private static void CrearConexion() {
        System.out.println("Creando Conexion");
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection(DB_URL);
            stm = conexion.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * cierra la conexion con la base de datos
     */
     private static void CerrarConexion() {
        try {
            conexion.close();
            System.out.println("Conexion finalizada");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * devuelve los resultado de una consulta
     * @param Consulta consulta sql
     * @return resultado en una variable resulset
     */
    public static ResultSet consulta(String Consulta) {
        CrearConexion();
        
        ResultSet rt = null;
        try {
            rt = stm.executeQuery(Consulta);
            while (rt.next()) {
                System.out.println(rt.getString(1) + " " + rt.getString(2));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(rt);
        CerrarConexion();
        return rt;

    }
    /**
     * Insertar datos en una base de datos
     * @param consulta sql para insertar,modificar o borrar datos
     * @return devuelve 0 si no se inserta datos, 0> si se ha insertado datos
     */
    public static int InsertarConsulta(String consulta){
        int i=0;
        CrearConexion();
        try {
           i= stm.executeUpdate(consulta);

        } catch (SQLException ex) {
            System.err.println("Error al insertar datos");
        }

        CerrarConexion();
        return i;
    }
}

