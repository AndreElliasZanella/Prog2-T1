/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import modelo.Gasto;

/**
 *
 * @author André
 */
public class GastoDAO {
    private static void createTable() {
        Connection connection = Conexao.getConnection();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS GASTO"
                + "   (ID            INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "   nome           VARCHAR(100),"
                + "   limite         NUMERIC(11,2),"
                + "   saldo          NUMERIC(11,2),"
                + "   data           DATE,"
                + "   id_usuario     INTEGER(4))";

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(sqlCreate);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static boolean salvarGasto(Gasto gasto){
        createTable();
        Connection connection = Conexao.getConnection();
        String sql = "INSERT INTO GASTO (nome,limite,saldo,data,id_usuario) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstmt;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, gasto.getNome());
            pstmt.setFloat(2, gasto.getLimite());
            pstmt.setFloat(3, gasto.getSaldo());
            pstmt.setString(4, gasto.getData());
            pstmt.setInt(5, gasto.getIdUsuario());

            pstmt.execute();

            System.out.println("Categoria de gasto gravada com sucesso!");

            final ResultSet resultado = pstmt.getGeneratedKeys();
            if (resultado.next()) {
                int id = resultado.getInt(1);
                gasto.setId(id);
            }
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } 
    }
    
    public static boolean atualizarGasto(Gasto gasto){
        createTable();
        Connection connection = Conexao.getConnection();
        String sql = "UPDATE GASTO SET nome=?, limite=?, saldo=?, data=? WHERE nome=?";
        PreparedStatement pstmt;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, gasto.getNome());
            pstmt.setFloat(2, gasto.getLimite());
            pstmt.setFloat(3, gasto.getSaldo());
            pstmt.setString(4, gasto.getData());
            pstmt.setString(5, gasto.getNome());
            pstmt.execute();

            System.out.println("Categoria de gasto atualizado com sucesso!");
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } 
    }
    
    public static List<Gasto> getTodosGasto(){
        createTable();
        List<Gasto> gastos = new ArrayList<>();
        Connection connection = Conexao.getConnection();
        String sql = "SELECT * FROM GASTO";
        Statement stmt;

        try {
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            while (resultado.next()) {                
                String nome = resultado.getString("nome");
                Float limite = resultado.getFloat("limite");
                Float saldo = resultado.getFloat("saldo");
                String data = resultado.getString("data");
                int idUsuario = resultado.getInt("id_usuario");
                int id = resultado.getInt("ID");
                
                Gasto gas = new Gasto(nome,limite,saldo,data,idUsuario);
                gas.setId(id);
                gastos.add(gas);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } 
        return gastos;
    }
    
    public static List<Gasto> getTodosGastoDoUsuario(int idAux){
        createTable();
        List<Gasto> gastos = new ArrayList<>();
        Connection connection = Conexao.getConnection();
        String sql = "SELECT * FROM GASTO WHERE id_usuario="+idAux;
        Statement stmt;
        
        try {
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(sql);
            
            while (resultado.next()) {                
                String nome = resultado.getString("nome");
                Float limite = resultado.getFloat("limite");
                Float saldo = resultado.getFloat("saldo");
                String data = resultado.getString("data");
                int idUsuario = resultado.getInt("id_usuario");
                int id = resultado.getInt("ID");

                Gasto gas = new Gasto(nome,limite,saldo,data,idUsuario);
                gas.setId(id);
                gastos.add(gas);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        Collections.sort(gastos, new Comparator<Gasto>() {
            @Override
            public int compare(Gasto g1, Gasto g2) {
                            if(g1.getSaldo() > (g2.getSaldo())){
                                return -1;
                            }else{
                                return 1;
                            }
            }
        });
        return gastos;
    }
    
    public static boolean excluirGasto(String nome){
        createTable();
        Connection connection = Conexao.getConnection();
        String sql = "DELETE FROM GASTO WHERE nome = ?";
        PreparedStatement pstmt;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            pstmt.execute();
            System.out.println("Categoria de gasto apagado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public static boolean inserirValor(Float saldo,String nome){
        createTable();
        Connection connection = Conexao.getConnection();
        String sql = "UPDATE GASTO SET saldo=? WHERE nome=?";
        PreparedStatement pstmt;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setFloat(1, saldo);
            pstmt.setString(2, nome);
            pstmt.execute();

            System.out.println("Valor inserido na cateogira "+nome+" com sucesso!");
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
