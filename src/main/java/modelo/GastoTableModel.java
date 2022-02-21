/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author André
 */
public class GastoTableModel extends AbstractTableModel{
    
    private List<Gasto> gastos;
    
    private final String[] nomeColunas = {"Nome", "Limite", "Saldo", "Data"};
    private final int COLUNA_NOME = 0;
    private final int COLUNA_LIMITE = 1;
    private final int COLUNA_SALDO = 2;
    private final int COLUNA_DATA = 3;
    
    public GastoTableModel(List<Gasto> gastos) {
        this.gastos = gastos;
    }

    
    @Override
    public int getRowCount() {
        return gastos.size();
    }

    @Override
    public int getColumnCount() {
        return nomeColunas.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return nomeColunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Gasto gasto = this.gastos.get(rowIndex);
        String valor = null;
        switch(columnIndex){
            case COLUNA_NOME:
                valor = gasto.getNome();
                break;
            case COLUNA_LIMITE:
                valor = Float.toString(gasto.getLimite());
                break;
            case COLUNA_SALDO:
                valor = Float.toString(gasto.getSaldo());
                break;
            case COLUNA_DATA:
                valor = gasto.getData();
                break;
        }
        return valor;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == COLUNA_LIMITE)
            return false;
        return true;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    	Gasto gasto = this.gastos.get(rowIndex);
        switch (columnIndex) {
            case COLUNA_NOME:
                gasto.setNome((String) aValue);
                break;
            case COLUNA_LIMITE:
                gasto.setLimite((Float) aValue);
                break;
        }
        fireTableDataChanged();
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos.clear();
        this.gastos.addAll(gastos);
    }

    public List<Gasto> getGastos() {
        return gastos;
    }    
}
