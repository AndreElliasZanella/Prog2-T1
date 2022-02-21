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
    
    private int Id;
    private int idUsuario;
    private String data;

    public Gasto(String nome, float limite, Float saldo, String data, int idUsuario) {
        super(nome, limite, saldo);
        this.data = data;
        this.idUsuario = idUsuario;
    }

    public String getData() {
        return data;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
