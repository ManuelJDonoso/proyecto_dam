/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academy.util;

import es.manueldonoso.academy.modelos.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;

/**
 *
 * @author donpe
 */
public class ConexionBDLocal {

    private static final String DB_URL = "jdbc:sqlite:src/main/resources/db/BaseDatos.db";
    private static Connection conexion = null;
    private static Statement stm = null;

    public static void CrearConexion() {
        System.out.println("Creando Conexion");
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection(DB_URL);
            stm = conexion.createStatement();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet consulta(String Consulta) {
        CrearConexion();
        
        ResultSet rt = null;
        try {
            rt = stm.executeQuery(Consulta);
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
      
     
        return rt;

    }

    public static int actualizarBBDD(String sql){
           int i=0;
        CrearConexion();
        try {
           i= stm.executeUpdate(sql);

        } catch (SQLException ex) {
            System.err.println("Error al insertar datos");
        }

        CerrarConexion();
        return i;
    }
    
    public static boolean LoginCorrecto(String user, String pass) {
        boolean correcto = false;

        CrearConexion();
        ResultSet rt = null;
        String sql = "SELECT Usuario,Pass FROM Usuarios WHERE Usuario='" + user + "' AND Pass ='" + pass + "'";

        try {
            rt = stm.executeQuery(sql);
            while (rt.next()) {
                correcto = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (correcto) {
            System.out.println("Conexion Exitosa");
        } else {
            System.out.println("Usuario o pass mal");
        };
        return correcto;
    }

    public static void CerrarConexion() {
        try {
            conexion.close();
            System.out.println("Conexion finalizada");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean ExisteAdmin() {
        boolean correcto = false;

        CrearConexion();
        ResultSet rt = null;
        String sql = "SELECT fk_ClaseUser FROM Usuarios where fk_ClaseUser='1'";

        try {
            rt = stm.executeQuery(sql);
            while (rt.next()) {
                correcto = true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (correcto) {
            System.out.println("Existe Administrador");
        } else {
            System.out.println("No Existe Administrador");
        };
        return correcto;
    }

    public static int tipo_usuario(String user) {
        CrearConexion();
        ResultSet rt = null;
        String sql = "SELECT fk_ClaseUser FROM Usuarios WHERE Usuario = '" + user + "'";
        int tipo = 0;
        try {
            rt = stm.executeQuery(sql);
            while (rt.next()) {
                tipo = Integer.parseInt(rt.getString(1));
            }
        } catch (SQLException ex) {
            tipo = -1;
        }
        return tipo;
    }

    public static Usuario user(String nick, String pass) {
        CrearConexion();
        ResultSet rt = null;
        String sql = "SELECT id,Usuario,Pass,fk_ClaseUser,Nombre,Apellidos,Direccion,Telefono,Email,urlFoto FROM Usuarios WHERE Usuario='" + nick + "' AND Pass='" + pass + "';";
        Usuario user = new Usuario();

        try {
            rt = stm.executeQuery(sql);

            while (rt.next()) {
                user.setId(rt.getInt("id"));
                user.setNick(rt.getString("Usuario"));
                user.setPass(rt.getString("Pass"));
                user.setTipo(rt.getInt("fk_ClaseUser"));
                user.setNombre(rt.getString("Nombre"));
                user.setApellidos(rt.getString("Apellidos"));
                user.setDireccion(rt.getString("Direccion"));
                user.setTelefono(rt.getString("Telefono"));
                user.setEmail(rt.getString("Email"));
                user.setUrlFoto(rt.getString("urlFoto"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public static void CrearAdministrativo(Usuario user) {
        String nombre = user.getNombre();
        String apellidos = user.getApellidos();
        String Direccion = user.getDireccion();
        String email = user.getEmail();
        String usuario = user.getNombre();
        String pass = "cambiame";
        String telefono = user.getTelefono();
        String urlFoto = user.getUrlFoto();
        int tipo = 1;
        String sql = "INSERT INTO \"main\".\"Usuarios\" "
                + "(\"Usuario\", \"Pass\", \"fk_ClaseUser\", \"Nombre\", \"Apellidos\", \"Direccion\", \"Telefono\", \"Email\", \"urlFoto\") "
                + "VALUES "
                + "( '" + usuario + "', "
                + "'" + pass + "',"
                + "'" + tipo + "', "
                + "'" + nombre + "', "
                + "'" + apellidos + "', "
                + "'" + Direccion + "', "
                + "'" + telefono + "', "
                + "'" + email + "', "
                + "'" + urlFoto + "');";

        CrearConexion();

        try {
            stm.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDLocal.class.getName()).log(Level.SEVERE, null, ex);
        }

        CerrarConexion();
    }

    public static void CrearDuenoContrato(Usuario user) {
        String nombre = user.getNombre();
        String apellidos = user.getApellidos();

        String sql = "INSERT INTO \"main\".\"Contrato\" (\"id\", \"Nombre\", \"Apellidos\") "
                + "VALUES ('1', '" + nombre + "', '" + apellidos + "');";

        CrearConexion();

        try {
            stm.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDLocal.class.getName()).log(Level.SEVERE, null, ex);
        }

        CerrarConexion();
    }

    public static int AnadirAsignatura(String Asignatura, Pane root) {
        int i = 0;
        String sql = "INSERT INTO \"main\".\"Asignaturas\" (\"asignatura\", \"activa\") "
                + "VALUES ('" + Asignatura + "','1')";

        CrearConexion();

        try {
            i = stm.executeUpdate(sql);

        } catch (SQLException ex) {
            Metodos.error(root,"La asignatura ya existe en la base de datos.");
        }

        CerrarConexion();
        return i;
    }

    public static ArrayList listarAsignaturas() {
        CrearConexion();
        ResultSet rt = null;
        String sql = "SELECT asignatura FROM Asignaturas";
        ArrayList<String> listaAsignaturas = new ArrayList<>();
        try {
            rt = stm.executeQuery(sql);

            while (rt.next()) {
               listaAsignaturas.add(rt.getString("asignatura"));
                 System.out.println(rt.getString("asignatura"));
            }
        } catch (SQLException ex) {

        }
        CerrarConexion();
        return listaAsignaturas;
        
    }

    public static ResultSet asignaturasAct(){
    return consulta("SELECT asignatura FROM Asignaturas WHERE ACTIVA =1");
    }
    public static ResultSet asignaturasDes_Act(){
    return consulta("SELECT asignatura FROM Asignaturas WHERE ACTIVA =0");
    }
    
    public static void DesactivarAsignatura(String asignatura){
    String sql ="UPDATE Asignaturas SET ACTIVA =0 WHERE asignatura='"+asignatura+"'";
        System.out.println(sql);
        int i = actualizarBBDD(sql);
        if (i==1){System.out.println("Se actualizo correctamente");}else if (i==0) {
            System.err.println("No se actualizo");
        }
{}
    }
    
      public static void ActivarAsignatura(String asignatura){
    String sql ="UPDATE Asignaturas SET ACTIVA =1 WHERE asignatura='"+asignatura+"'";
        System.out.println(sql);
        int i = actualizarBBDD(sql);
        if (i==1){System.out.println("Se actualizo correctamente");}else if (i==0) {
            System.err.println("No se actualizo");
        }
{}
    }

}
