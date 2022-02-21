/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author André
 */
public class UsuarioException extends Exception{
    public UsuarioException() {
        super("Informe todos os campos");
    }

    public UsuarioException(String message) {
        super(message);
    } 
}
