/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.TelaCadastrarUsuario;
import modelo.Usuario;

/**
 *
 * @author André
 */
public class ControladorCadastrarUsuario {
    
    private TelaCadastrarUsuario telaCadastrarUsuario;
    private Usuario usuario;
    private ControladorLogin controladorLogin;

    public ControladorCadastrarUsuario(TelaCadastrarUsuario telaCadastrarUsuario, Usuario usuario, ControladorLogin controladorLogin) {
        this.telaCadastrarUsuario = telaCadastrarUsuario;
        this.usuario = usuario;
        this.controladorLogin = controladorLogin;
        inicializarAcao();
    }
    
    public void inicializarAcao(){
        telaCadastrarUsuario.adicionarAcaoCriarUsuario(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarPaciente();
            }
        });
    }
    
    public void exibir(){
        telaCadastrarUsuario.exibirTela();
    }
    
    public void salvarPaciente(){
        usuario = new Usuario(Integer.parseInt(telaCadastrarUsuario.getCpf()), telaCadastrarUsuario.getNome(), Float.parseFloat(telaCadastrarUsuario.getReceita()), telaCadastrarUsuario.getSenha());
        if(validarUsuarioVazio()){
            if(validarUsuarioMap(usuario)){
                controladorLogin.inserirUsuarioMap(usuario);
                telaCadastrarUsuario.exibirMensagem("Usuario salvo com sucesso. " + usuario.getNome());
                telaCadastrarUsuario.limparTela();
                telaCadastrarUsuario.fecharTela();
                controladorLogin.exibirLogin();
            }else{
                telaCadastrarUsuario.exibirMensagem("Usuário já Cadastrado");
            }
            
        }
        else {
            telaCadastrarUsuario.exibirMensagem("Nome/CPF vazio");
        }
        
    }
    
    public boolean validarUsuarioVazio(){
        if (this.usuario.getCpf() == 0)
            return false;
        if (this.usuario.getNome().equals(""))
            return false;        
        if (this.usuario.getReceita() == 0)
            return false;
        if (this.usuario.getSenha().isEmpty())
            return false;
        return true;
    }
    
    public boolean validarUsuarioMap(Usuario usu){
        boolean retorno = true;
        for(int cpfMap: controladorLogin.getUsuarios().keySet()){
            if (usu.getCpf() == cpfMap){
                retorno = false;
            }
        }
        return retorno;
    }

    public TelaCadastrarUsuario getTelaCadastrarUsuario() {
        return telaCadastrarUsuario;
    }
}
