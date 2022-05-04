package br.com.rheacaoscannerpro.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel {

    private List<UsuarioModel> dados = new ArrayList<>();
    private String[] colunas = {"ID", "Nome", "Função", "CPF", "Telefone"};

    @Override
    public String getColumnName(int column) {
        return colunas[column]; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRowCount() {
        return dados.size();
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
                return dados.get(linha).getFuncao();
            case 3:
                return dados.get(linha).getCpf();
            case 4:
                return dados.get(linha).getTelefone();

        }

        return null;

    }

    public void addRow(UsuarioModel c) {//adiciona uma unica linha na tabela
        this.dados.add(c);
        this.fireTableDataChanged();

    }

    public void removeAll() {
        this.dados.removeAll(dados);
        this.fireTableDataChanged();
    }

}
