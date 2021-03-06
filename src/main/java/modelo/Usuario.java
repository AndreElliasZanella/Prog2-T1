/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author André
 */
public class Usuario implements Comparable<Usuario>{
    private int id;
    private String cpf;
    private String nome;
    private float receita;
    private String senha;
    Set<Categoria> categorias;

    public Usuario(String cpf, String nome, float receita, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.receita = receita;
        this.senha = senha;
        this.categorias = new HashSet<>();        
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getReceita() {
        return receita;
    }

    public void setReceita(float receita) {
        this.receita = receita;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Set<Categoria> getCategorias() {
        return categorias;
    }

    @Override
    public int compareTo(Usuario usu) {
        return this.nome.compareTo(usu.getNome());
    }
    
}
