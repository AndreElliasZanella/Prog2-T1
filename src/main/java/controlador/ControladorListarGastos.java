/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.GastoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import modelo.Gasto;
import modelo.GastoTableModel;
import modelo.Usuario;
import visao.TelaCadastrarGasto;
import visao.TelaInserirValor;
import visao.TelaListar;

/**
 *
 * @author André
 */
public class ControladorListarGastos {
    private TelaListar telaListar;
    private TelaInserirValor telaInserirValor;
    private GastoTableModel gastoTableModel;
    private ControladorCadastrarGasto controladorCadastrarGasto;
    private ControladorInserirValor controladorInserirValor;
    private ControladorLogin controladorLogin;
    private Usuario usuario;
    private int idUsuario;

    List<Gasto> gastos;
    
    public ControladorListarGastos(TelaListar telaListar, GastoTableModel gastoTableModel, int idUsuario, ControladorLogin controladorLogin) {
        this.telaListar = telaListar;
        this.gastoTableModel = gastoTableModel;
        this.gastos = new ArrayList<>();
        this.idUsuario = idUsuario;
        this.controladorLogin = controladorLogin;
        setTableModel();
        adicionarEventos();
        inicializarTelaCadastrarGasto();
        inicializarAcao();
    }
    private void setTableModel(){
        telaListar.setTableModel(this.gastoTableModel);
    }
    
    public void exibir(){
        telaListar.exibirTela();
    }
    public void fechar(){
        telaListar.fechar();
    }
    
    public void atualizarDados(){
        gastoTableModel.fireTableDataChanged();
        gastoTableModel.setGastos(GastoDAO.getTodosGastoDoUsuario(idUsuario));
        System.out.print("Atualizando dados...");
    }

    public List<Gasto> getGastos() {
        return gastos;
    }
    
    public void inicializarTelaCadastrarGasto(){
        controladorCadastrarGasto = new ControladorCadastrarGasto(new TelaCadastrarGasto(), new Gasto("",0f,0f,"",this.idUsuario), this);
        controladorInserirValor = new ControladorInserirValor(this,new TelaInserirValor());
    }
    
    public void inicializarAcao(){
        this.telaInserirValor = new TelaInserirValor();
        
        telaListar.adicionarAcaoCriarCategoria(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorCadastrarGasto.exibir();
            }
        });
        
        telaListar.adicionarAcaoExcluir(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirPaciente();
            }
        });
        
        telaListar.adicionarAcaoInserirValor(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorInserirValor.exibir();
            }
        });
        telaListar.adicionarAcaoSair(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLogin.exibirLogin();
                fechar();
            }
        });
    }
    
    public String getNomeLinha(){
        return telaListar.getNomeLinhaSelecionada();
    }
    
    public void excluirPaciente(){
        String nome = telaListar.getNomeLinhaSelecionada();
        if(GastoDAO.excluirGasto(nome)){
            telaListar.exibirMensagem("Categoria de gasto excluida com sucesso");
            atualizarDados();
        }
        else {
            telaListar.exibirMensagem("Não foi possível excluir a categoria de gasto");
        }
    }
    
    public void adicionarEventos(){
        telaListar.adicionarEventoAlteracaoTabela(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (TableModelEvent.UPDATE == e.getType()) {
                   int row = e.getFirstRow();
                   int column = e.getColumn();
                   if(row >=0 && column >=0){
                        GastoTableModel model = (GastoTableModel)e.getSource();
                        String Nome = (String)model.getValueAt(row, 0);
                        Gasto gasto = gastoTableModel.getGastos().get(row);
                        System.out.println(gasto);
                        GastoDAO.atualizarGasto(gasto);
                        atualizarDados();
                   }
                 }
            }
        });
    }
}
