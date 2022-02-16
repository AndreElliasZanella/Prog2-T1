/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Gasto;
import modelo.Usuario;
import visao.TelaCadastrarGasto;
import visao.TelaCadastrarUsuario;

/**
 *
 * @author André
 */
public class ControladorCadastrarGasto {
    
    private TelaCadastrarGasto telaCadastrarGasto;
    private Gasto gasto;
    
    public ControladorCadastrarGasto(TelaCadastrarGasto telaCadastrarGasto, Gasto gasto) {
        this.telaCadastrarGasto = telaCadastrarGasto;
        this.gasto = gasto;
        inicializarAcao();
        exibir();
    }
    
    public void inicializarAcao(){
        telaCadastrarGasto.adicionarAcaoCriarGasto(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarGasto();
            }
        });
    }
    
    public void exibir(){
        telaCadastrarGasto.exibirTela();
    }
    
    public void salvarGasto(){
        gasto = new Gasto(telaCadastrarGasto.getNome(), Float.parseFloat(telaCadastrarGasto.getLimite()), null, "");
        if(validarUsuarioVazio()){
            telaCadastrarGasto.exibirMensagem("Usuario salvo com sucesso. " + gasto.getNome());
            telaCadastrarGasto.limparTela();
            telaCadastrarGasto.fecharTela();      
        }
        else {
            telaCadastrarGasto.exibirMensagem("Nome/CPF vazio");
        }
    }
    
    public boolean validarUsuarioVazio(){
        if (this.gasto.getNome().equals(""))
            return false;
        if (this.gasto.getLimite() == 0)
            return false;
        return true;
    }

    public TelaCadastrarGasto getTelaCadastrarGasto() {
        return telaCadastrarGasto;
    }
    
}