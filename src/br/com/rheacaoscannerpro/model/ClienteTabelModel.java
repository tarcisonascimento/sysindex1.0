package br.com.rheacaoscannerpro.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tarciso Nascimento
 */
public class ClienteTabelModel extends AbstractTableModel {

    private List<ClienteModel> dados = new ArrayList<>();
    private String[] colunas = {"ID", "Razão Social", "Telefone"};

    
    
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return dados.size();//responsavel pelo tamanho da lista     
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {

        switch (coluna) {
            case 0:
                return dados.get(linha).getId();
            case 1:
                return dados.get(linha).getNome();
            case 2:
                return dados.get(linha).getTelefone();
        }

        return null;
    }
    
    
    
    public void addRow(ClienteModel c) {//adiciona uma unica linha na tabela
        this.dados.add(c);
        this.fireTableDataChanged();

    }

    public void removeAll() {
        this.dados.removeAll(dados);
        this.fireTableDataChanged();
    }

}
