/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import modelo.Gasto;
import modelo.GastoTableModel;
import visao.TelaCadastrarGasto;
import visao.TelaListar;

/**
 *
 * @author André
 */
public class ControladorListarGastos {
    private TelaListar telaListar;
    private GastoTableModel gastoTableModel;
    private ControladorCadastrarGasto controladorCadastrarGasto;

    List<Gasto> gastos;
    
    public ControladorListarGastos(TelaListar telaListar, GastoTableModel gastoTableModel) {
        this.telaListar = telaListar;
        this.gastoTableModel = gastoTableModel;
        this.gastos = new ArrayList<>();
        inicializarTelaCadastrarGasto();
        setTableModel();
        adicionarEventos();
    }
    private void setTableModel(){
        telaListar.setTableModel(this.gastoTableModel);
    }
    
    public void exibir(){
        telaListar.exibirTela();
    }
    
    public void atualizarDados(){
        gastoTableModel.fireTableDataChanged();
        gastoTableModel.setGastos(gastos);
        System.out.print("Atualizando dados..");
    }

    public List<Gasto> getGastos() {
        return gastos;
    }
    
    public void inicializarTelaCadastrarGasto(){
        controladorCadastrarGasto = new ControladorCadastrarGasto(new TelaCadastrarGasto(), new Gasto("",0f,0f,""));
    }
    
//    public void adicionarAcaoBotaoExcluir(){;
//        telaListarPacientes.adicionarAcaoExcluir(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                excluirPaciente();
//            }
//        });
//    }
    
//    public void excluirPaciente(){
//        String CPF = telaListarPacientes.getCPFLinhaSelecionada();
//        if(PacienteDAO.excluirPaciente(CPF)){
//            telaListarPacientes.exibirMensagem("Paciente excluido com sucesso");
//            atualizarDados();
//        }
//        else {
//            telaListarPacientes.exibirMensagem("Não foi possível excluir o paciente");
//        }
//    }
    
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
//                        PacienteDAO.atualizarPaciente(paciente);
                        atualizarDados();
                   }
                 }
            }
        });
    }
}
