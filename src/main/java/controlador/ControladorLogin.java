/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.GastoDAO;
import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
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
    
    //telas
    private TelaLogin telaLogin;
    private TelaCadastrarUsuario telaCadastrarUsuario;
    
    //map
    private Map<String, Usuario> usuarios = new HashMap<>();
    
    //controlador
    private ControladorCadastrarUsuario controladorCadastrarUsuario;
    private ControladorListarGastos controladorListarGastos;
    
    //tableModel
    private GastoTableModel gastoTableModel;
    
    public ControladorLogin(TelaLogin telaLogin) {
        this.telaLogin = telaLogin;
        atualizarMap();
        inicializarTelaCadastrarUsuario();
        inicializarAcaoBotoes();
    }
    
    public void inserirUsuarioMap(Usuario usuario){
        usuarios.put(usuario.getCpf(), usuario);
    }
    
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
    
    //não está sendo usado
    public SortedSet<Usuario> Ordenar(Map<String, Usuario> map){
        SortedSet<Usuario> sortUsuarios = new TreeSet<>(map.values());
        return sortUsuarios;
    }
    
    public void inicializarAcaoBotoes() {
        telaLogin.adicionarAcaoLogar(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarMap();
                for(String cpfMap: usuarios.keySet()){
                    if (cpfMap.equals(telaLogin.getCpf())){
                        if(usuarios.get(cpfMap).getSenha().equals(telaLogin.getSenha())){
                            inicializarTelaListar(usuarios.get(cpfMap).getId());
                            telaLogin.fecharTela();
                            telaLogin.limparTela();
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
    
    public void atualizarMap(){
        List<Usuario> usuariosList;
        usuariosList = UsuarioDAO.getTodosUsuario();
        usuarios.clear();
        if (!usuariosList.isEmpty()){
            for(Usuario usu: usuariosList){
                usuarios.put(usu.getCpf(), usu);
            }
        }        
    }
    
    public void inicializarTelaCadastrarUsuario() {
        controladorCadastrarUsuario = new ControladorCadastrarUsuario(new TelaCadastrarUsuario(), new Usuario("", "", 0,""), this);
    }
    
    public void inicializarTelaListar(int idUsuario){
        gastoTableModel = new GastoTableModel(GastoDAO.getTodosGastoDoUsuario(idUsuario));
        controladorListarGastos = new ControladorListarGastos(new TelaListar(), gastoTableModel, idUsuario,this);
    }
    
    public void exibirLogin(){
        telaLogin.exibirTela();
    }
            
}
