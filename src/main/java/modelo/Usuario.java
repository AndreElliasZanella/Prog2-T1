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
public class Usuario {
    private int cpf;
    private String nome;
    private float receita;
    private String senha;
    Set<Categoria> categorias;

    public Usuario(int cpf, String nome, float receita, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.receita = receita;
        this.senha = senha;
        this.categorias = new HashSet<>();        
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
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
    
    public Set<Categoria> getCategorias() {
        return categorias;
    }
    
}
