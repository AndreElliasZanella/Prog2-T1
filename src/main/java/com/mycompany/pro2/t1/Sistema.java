/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pro2.t1;

import controlador.ControladorLogin;
import visao.TelaLogin;

/**
 *
 * @author André
 */
public class Sistema {
    public static void main(String[] args) {        
        ControladorLogin controlador = new ControladorLogin(new TelaLogin());
        controlador.exibirLogin();
    }
}
