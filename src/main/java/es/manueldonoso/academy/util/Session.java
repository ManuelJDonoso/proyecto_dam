/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.manueldonoso.academy.util;

import es.manueldonoso.academy.modelos.Usuario;

/**
 *
 * @author donpe
 */
public class Session {
    public static Usuario usuarioLogin;

    public static Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public static void setUsuarioLogin(Usuario usuarioLogin) {
        Session.usuarioLogin = usuarioLogin;
    }
    
    public static void finSession(){
        Session.usuarioLogin = new Usuario();
    }
    
}
