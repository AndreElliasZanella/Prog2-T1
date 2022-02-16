/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author André
 */
public abstract class Categoria {
    private String nome;
    private float limite;
    private float saldo;

    public Categoria(String nome, float limite, Float saldo) {
        this.nome = nome;
        this.limite = limite;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }    
    
    public void addSaldo(float valor){
        this.saldo += valor;
    }
    
}
