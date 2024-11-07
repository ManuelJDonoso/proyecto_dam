/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academy.util;

import es.manueldonoso.academy.modelos.Usuario;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public static int actualizarBBDD(String sql) {
        int i = 0;
        CrearConexion();
        try {
            i = stm.executeUpdate(sql);

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
            Metodos.error(root, "La asignatura ya existe en la base de datos.");
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

    public static ResultSet asignaturasAct() {
        return consulta("SELECT asignatura FROM Asignaturas WHERE ACTIVA =1");
    }

    public static ResultSet asignaturasDes_Act() {
        return consulta("SELECT asignatura FROM Asignaturas WHERE ACTIVA =0");
    }

    public static void DesactivarAsignatura(String asignatura) {
        String sql = "UPDATE Asignaturas SET ACTIVA =0 WHERE asignatura='" + asignatura + "'";
        System.out.println(sql);
        int i = actualizarBBDD(sql);
        if (i == 1) {
            System.out.println("Se actualizo correctamente");
        } else if (i == 0) {
            System.err.println("No se actualizo");
        }
        {
        }
    }

    public static void ActivarAsignatura(String asignatura) {
        String sql = "UPDATE Asignaturas SET ACTIVA =1 WHERE asignatura='" + asignatura + "'";
        System.out.println(sql);
        int i = actualizarBBDD(sql);
        if (i == 1) {
            System.out.println("Se actualizo correctamente");
        } else if (i == 0) {
            System.err.println("No se actualizo");
        }
        {
        }
    }

    public static void GuardarUsuario(Usuario user) {
        String nombre = user.getNombre().trim();
        String Apellido = user.getApellidos().trim();
        String Direccion = user.getDireccion().trim();
        String email = user.getEmail().trim();
        String telefono = user.getTelefono().trim();
        String url_foto = user.getUrlFoto();

        String sql = "UPDATE usuarios SET Nombre ='" + nombre + "', Apellidos= '" + Apellido + "', Direccion ='" + Direccion
                + "', Email ='" + email + "', Telefono= '" + telefono + "' , urlFoto = '" + url_foto
                + "' WHERE id = " + user.getId();

        System.out.println(sql);
        int i = ConexionBDLocal.actualizarBBDD(sql);

        if (i != 0) {
            System.out.println("Actualizacion Correcta");
        } else {
            System.out.println("Fallo al actualizar los datos");
        }

    }

    public static ObservableList<Usuario> buscarUser(String Nombre, String Apellidos,
            String Telefono, String Email, String Usuario, String id, String Direccion) {
        String sql = "SELECT * FROM usuarios WHERE fk_ClaseUser =" + id
                + " AND Nombre LIKE '%" + Nombre + "%'"
                + " AND Apellidos LIKE '%" + Apellidos + "%'"
                + " AND Email LIKE '%" + Email + "%'"
                + " AND Telefono LIKE '%" + Telefono + "%'"
                + " AND Usuario LIKE '%" + Usuario + "%'"
                + " AND Direccion LIKE '%" + Direccion + "%'";

        ObservableList<Usuario> data = FXCollections.observableArrayList();

        ResultSet rs = consulta(sql);

        try {
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId(Integer.parseInt(rs.getString("id")));
                user.setNombre(rs.getString("Nombre"));
                user.setApellidos(rs.getString("Apellidos"));
                user.setDireccion(rs.getString("Direccion"));
                user.setEmail(rs.getString("Email"));
                user.setNick(rs.getString("Usuario"));
                user.setTelefono(rs.getString("Telefono"));
                user.setTipo(Integer.parseInt(rs.getString("fk_ClaseUser")));
                user.setUrlFoto(rs.getString("urlFoto"));
                user.setAsignaturas(buscaAsignaturasUsuario(user.getId() + ""));
                System.out.println(user.toString());

                data.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    private static ArrayList<String> buscaAsignaturasUsuario(String id) {
        ArrayList<String> lista = new ArrayList<>();
        String sql = "SELECT Asignaturas.asignatura "
                + "FROM Usuarios "
                + "JOIN usuario_asignatura ON Usuarios.id = usuario_asignatura.fk_usuario "
                + "JOIN Asignaturas ON usuario_asignatura.fkAsignatura = Asignaturas.id "
                + "WHERE Usuarios.id = " + id
                + " ORDER BY asignatura;";

        ResultSet rt = consulta(sql);
        try {
            while (rt.next()) {
                lista.add(rt.getString("asignatura"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDLocal.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public static void crearUsuarioNuevo(Usuario user) {
        String nick = Metodos.generaNick(user.getNombre(), user.getApellidos());
        String sql = "INSERT INTO Usuarios('Usuario', 'Pass', 'fk_ClaseUser', 'Nombre', 'Apellidos', 'Direccion', 'Telefono', 'Email') "
                + "VALUES "
                + "('" + nick + "', "
                + "'Cambiame', '"
                + user.getTipo() + "', '"
                + user.getNombre() + "', '"
                + user.getApellidos() + "', '"
                + user.getDireccion() + "', '"
                + user.getTelefono() + "', '"
                + user.getEmail() + "')";
        
        ConexionBDLocal.actualizarBBDD(sql);
        user.setId(Buscar_id_Nick(nick));
        System.out.println(user.getId());
        File f = new File("src/main/resources/temp/foto.jpg");
        if (f.exists()) {
            Metodos.copyFile(f, "src/main/resources/images/users/" + user.getId() + ".jpg");
            f.delete();
        } else {
            user.setUrlFoto(null);
        }
          GuardarUsuario(user);
    }

    public static void UpdateUsuario(Usuario User) {
    }

    public static boolean isNick(String nick) throws SQLException {
        String sql = "Select * FROM Usuarios  WHERE Usuario= '" + nick + "'";

        ResultSet rt = consulta(sql);

        while (rt.next()) {

            return true;
        }

        return false;

    }

    public static int Buscar_id_Nick(String nick) {
        String sql = "Select * FROM Usuarios  WHERE Usuario= '" + nick + "'";
        ResultSet rt = consulta(sql);

        try {
            while (rt.next()) {

                return Integer.parseInt(rt.getString("id"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
