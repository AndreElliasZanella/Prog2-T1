/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import modelo.Gasto;
import modelo.GastoTableModel;
import visao.TelaLogin;
import modelo.Usuario;
import visao.TelaCadastrarUsuario;
import visao.TelaListar;
/**
 *
 * @author André
 */
public class ControladorLogin {
    
    private TelaLogin telaLogin;
    private TelaCadastrarUsuario telaCadastrarUsuario;
    private Map<Integer, Usuario> usuarios;
    private ControladorCadastrarUsuario controladorCadastrarUsuario;
    private ControladorListarGastos controladorListarGastos;
    private GastoTableModel gastoTableModel;
    
    public ControladorLogin(TelaLogin telaLogin, Map<Integer,Usuario> usuarios) {
        this.telaLogin = telaLogin;
        this.usuarios = usuarios;
        inicializarTelaCadastrarUsuario();
        inicializarTelaListar();
        inicializarAcaoBotoes();
    }

    public Map<Integer, Usuario> getUsuarios() {
        return usuarios;
    }
    
    public void inserirUsuarioMap(Usuario usuario){
        usuarios.put(usuario.getCpf(), usuario);
    }
    
    public void inicializarAcaoBotoes() {
        telaLogin.adicionarAcaoLogar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int cpfMap: usuarios.keySet()){
                    if (cpfMap == Integer.parseInt(telaLogin.getCpf())){
                        if(usuarios.get(cpfMap).getSenha().equals(telaLogin.getSenha())){
                            telaLogin.fecharTela();
                            controladorListarGastos.exibir();
                        }
                    }
                }
            }
        });

        telaLogin.adicionarAcaoCriarUsuario(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaLogin.limparTela();
                telaLogin.fecharTela();
                controladorCadastrarUsuario.exibir();
            }
        });

    }            
    
    public void inicializarTelaCadastrarUsuario() {
        controladorCadastrarUsuario = new ControladorCadastrarUsuario(new TelaCadastrarUsuario(), new Usuario(0, "", 0,""), this);
    }
    
    public void inicializarTelaListar(){
        gastoTableModel = new GastoTableModel(new ArrayList<Gasto>());
        controladorListarGastos = new ControladorListarGastos(new TelaListar(), gastoTableModel);
    }
    
    public void exibirLogin(){
        telaLogin.exibirTela();
    }
            
}
