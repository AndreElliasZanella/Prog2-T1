/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.GastoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.TelaInserirValor;

/**
 *
 * @author André
 */
public class ControladorInserirValor {
    private ControladorListarGastos controladorListarGastos;
    private TelaInserirValor telaInserirValor;

    public ControladorInserirValor(ControladorListarGastos controladorListarGastos, TelaInserirValor telaInserirValor) {
        this.controladorListarGastos = controladorListarGastos;
        this.telaInserirValor = telaInserirValor;
        inicializarAcao();
    }
    
    public void inserirValor(){
        String nome = controladorListarGastos.getNomeLinha();
        if(GastoDAO.inserirValor(Float.parseFloat(telaInserirValor.getValor()), nome)){
            telaInserirValor.exibirMensagem("Valor inserido na categoria "+nome);
            controladorListarGastos.atualizarDados();
        }
        else {
            telaInserirValor.exibirMensagem("Não foi possível inserir o valor");
        }
    }
    
    public void inicializarAcao(){
        
        telaInserirValor.adicionarAcaoInserir(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                inserirValor();
                telaInserirValor.limparTela();
                telaInserirValor.fechar();
            }
        });       
    }
    
    public void exibir(){
        telaInserirValor.exibirTela();
    }
    
    public void fechar(){
        telaInserirValor.fechar();
    }
    
}
