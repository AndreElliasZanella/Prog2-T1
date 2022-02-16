/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pro2.t1;

import controlador.ControladorLogin;
import java.util.HashMap;
import java.util.Map;
import modelo.Usuario;
import visao.TelaLogin;

/**
 *
 * @author André
 */
public class Sistema {
    public static void main(String[] args) {
        Map<Integer,Usuario> usuarios = new HashMap<>();
        Usuario admin = new Usuario(1234,"admin",1000,"admin");
        usuarios.put(admin.getCpf(), admin);
        
        ControladorLogin controlador = new ControladorLogin(new TelaLogin(), usuarios);
        controlador.exibirLogin();
    }    
}
