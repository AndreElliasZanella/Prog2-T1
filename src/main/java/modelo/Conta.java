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
public class Conta {
    private static int codigo;
    private int numero;
    private float valor;
    private String nomeBanco;

    public Conta(int numero, float valor, String nomeBanco) {
        this.numero = codigo++;
        this.valor = valor;
        this.nomeBanco = nomeBanco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    @Override
    public String toString() {
        return "Conta{" + " numero=" + numero + ", valor=" + valor + ", nomeBanco=" + nomeBanco + '}';
    }
}
