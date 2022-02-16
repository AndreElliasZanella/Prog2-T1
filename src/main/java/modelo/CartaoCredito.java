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
public class CartaoCredito extends Categoria{
    private String Banco;

    public CartaoCredito(String Banco, float limite, Float saldo) {
        super("Cartão de Crédito", limite, saldo);
        this.Banco = Banco;
    }
    
    
}
