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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private Gasto gastoRet;
    
    private ControladorListarGastos controladorListarGastos;
    
    public ControladorCadastrarGasto(TelaCadastrarGasto telaCadastrarGasto, Gasto gasto, ControladorListarGastos controladorListarGastos) {
        this.telaCadastrarGasto = telaCadastrarGasto;
        this.gastoRet = gasto;
        this.controladorListarGastos = controladorListarGastos;
        inicializarAcao();    
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
        if(validarGastoVazio()){
            gasto = new Gasto(telaCadastrarGasto.getNome(),Float.parseFloat(telaCadastrarGasto.getLimite()), 0f, getDateTime(), this.gastoRet.getIdUsuario());
            if(GastoDAO.salvarGasto(gasto)){
                telaCadastrarGasto.exibirMensagem("Categoria de gasto salvo com sucesso. " + gasto.getNome());
                telaCadastrarGasto.limparTela();
                telaCadastrarGasto.fecharTela();
                controladorListarGastos.atualizarDados();
            }else{
                System.out.println("Erro ao inserir categoria de gasto no banco");
            }
            
        }
        else {
            telaCadastrarGasto.exibirMensagem("Nome/CPF vazio");
        }
    }
    
    public boolean validarGastoVazio(){
        if (this.telaCadastrarGasto.getNome().equals(""))
            return false;
        if (this.telaCadastrarGasto.getLimite().equals(""))
            return false;
        return true;
    }
    
    public Gasto retornaGasto(){
        return gasto;
    }
    
    public TelaCadastrarGasto getTelaCadastrarGasto() {
        return telaCadastrarGasto;
    }
    
    private String getDateTime() {
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
	return dateFormat.format(date);
    }
}