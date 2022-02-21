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
import java.util.List;
import modelo.Usuario;

/**
 *
 * @author André
 */
public class UsuarioDAO {
    private static void createTable() {
        Connection connection = Conexao.getConnection();
        String sqlCreate = "CREATE TABLE IF NOT EXISTS USUARIO"
                + "   (ID            INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "   CPF            VARCHAR(11),"
                + "   nome           VARCHAR(100),"
                + "   receita        NUMERIC(11,2),"
                + "   senha          VARCHAR(11))";

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(sqlCreate);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static boolean salvarUsuario(Usuario usuario){
        createTable();
        Connection connection = Conexao.getConnection();
        String sql = "INSERT INTO USUARIO (CPF,nome,receita,senha) VALUES(?, ?, ?, ?)";
        PreparedStatement pstmt;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usuario.getCpf());
            pstmt.setString(2, usuario.getNome());
            pstmt.setFloat(3, usuario.getReceita());
            pstmt.setString(4, usuario.getSenha());

            pstmt.execute();

            System.out.println("Usuario gravado com sucesso!");

            final ResultSet resultado = pstmt.getGeneratedKeys();
            if (resultado.next()) {
                int id = resultado.getInt(1);
                usuario.setId(id);
            }
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } 
    }
    
    public static boolean atualizarUsuario(Usuario usuario){
        createTable();
        Connection connection = Conexao.getConnection();
        String sql = "UPDATE USUARIO SET NOME=?, CPF=?, RECEITA=?, SENHA=? WHERE CPF=?";
        PreparedStatement pstmt;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, usuario.getCpf());
            pstmt.setString(2, usuario.getNome());
            pstmt.setFloat(3, usuario.getReceita());
            pstmt.setString(4, usuario.getSenha());
            pstmt.setString(5, usuario.getCpf());
            pstmt.execute();

            System.out.println("Paciente atualizado com sucesso!");
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } 
    }
    
    public static List<Usuario> getTodosUsuario(){
        createTable();
        List<Usuario> usuarios = new ArrayList<>();
        Connection connection = Conexao.getConnection();
        String sql = "SELECT * FROM USUARIO";
        Statement stmt;

        try {
            stmt = connection.createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            while (resultado.next()) {                
                String cpf = resultado.getString("CPF");
                String nome = resultado.getString("NOME");
                Float receita = resultado.getFloat("RECEITA");
                String senha = resultado.getString("SENHA");
                int id = resultado.getInt("ID");
                
                Usuario u = new Usuario(cpf,nome,receita,senha);
                u.setId(id);
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } 
        return usuarios;
    }
    
    public static boolean excluirPaciente(String CPF){
        createTable();
        Connection connection = Conexao.getConnection();
        String sql = "DELETE FROM USUARIO WHERE CPF = ?";
        PreparedStatement pstmt;

        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, CPF);
            pstmt.execute();
            System.out.println("Usuário apagado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
