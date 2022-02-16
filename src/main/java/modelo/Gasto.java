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
public class Gasto extends Categoria{
    
    private String data;

    public Gasto(String nome, float limite, Float saldo, String data) {
        super(nome, limite, saldo);
        this.data = data;
    }

    public String getData() {
        return data;
    }
    
    
            
}
