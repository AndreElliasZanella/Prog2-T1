/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author André
 */
public class Conexao {
    
    private static Connection connection = null;
    static final String DB_URL = "jdbc:sqlite:identifier.sqlite";
    static final String USER = "";
    static final String PASS = "";   
   
    public static Connection conectar() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Conexão realizada!");    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
    
    public static void descontecar(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static Connection getConnection(){
        if(connection == null)
            conectar();
        return connection;
    }
}
