package br.com.rheacaoscannerpro.telas;

import br.com.rheacaoscannerpro.dal.ModuloConexao;
import br.com.rheacaoscannerpro.model.ClienteModel;
import br.com.rheacaoscannerpro.model.UsuarioModel;
import br.com.rheacaoscannerpro.model.UsuarioTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Tarciso Nascimento
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;//usando o metodo de conexao e atribuindo a conexao limpa para iniciar
    PreparedStatement pst = null; //usado para preparar a conexao com o banco de dados
    ResultSet rs = null;//exibe o resultado das instruçoes sql que sera usado no java
    UsuarioTableModel tableModel = new UsuarioTableModel();

    //o metodo abaixo é utilizado para abrir apenas uma janela no Jdesktop
    public static TelaUsuario telaUsuario;

    public static TelaUsuario getInstancia() {
        if (telaUsuario == null) {

            telaUsuario = new TelaUsuario();

        }
        return telaUsuario;
    }

    //metodo para adicionar usuarios
    private void adicionarUso() {
        //instrução sql para inserção de dados na tabela mysql
        String sql = "insert into usuarios (usonome,usoendereco,usocidade,usocelular,usomatricula,usousuario,usosenha,usofuncao,usoperfil,usocpf,usoemail,usoativo,idcli,usoclinome) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {

            pst = conexao.prepareStatement(sql); //preparando a conexão
            pst.setString(1, txtNomeUso.getText());
            pst.setString(2, txtEnduso.getText());
            pst.setString(3, txtCidadeUso.getText());
            pst.setString(4, txtCeluso.getText());
            pst.setString(5, txtMatUso.getText());
            pst.setString(6, txtloginUso.getText());
            pst.setString(7, txtSenhaUso.getText());
            pst.setString(8, txtFuncUso.getText());
            pst.setString(9, cmbPerfilUso.getSelectedItem().toString());
            pst.setString(10, txtCpfUso.getText());
            pst.setString(11, txtEmailUso.getText());
            pst.setBoolean(12, chkAtivaUso.isSelected());
            ClienteModel cliente = (ClienteModel) cmbClientes.getSelectedItem();//pega o item do combobox
            //JOptionPane.showMessageDialog(null, "ID: " + cliente.getId());       
            pst.setInt(13, cliente.getId());
            pst.setString(14, cliente.getNome());

            // pst.setInt(11, cmbClientes.getIt);
            //a linha abaixo atualiza a tabela usuarios com as informaçoes novas do formulario
            int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 01" + e);
        }

    }

    //o metodo abaixo realiza a consulta para setat a combobox do cliente 
    public void preenchecombobox(String tarc) {
        
        
        cmbClientes.removeAllItems();
        String sql = "select * from cliente where idcli=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tarc);
            rs = pst.executeQuery();

            while (rs.next()) { //enquanto o RS result set for inteiro existe um laço de repetiçao.

                ClienteModel b = new ClienteModel();

                b.setNome(rs.getString("clirazaosocial"));
                b.setTelefone(rs.getString("clitelefone"));
                b.setId(rs.getInt("idcli"));
                cmbClientes.addItem(b);
                consultarClientes();

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 01" + e);
        }

    }

    public void setarCampos() {
        String sql = "select * from usuarios where iduso=?";
        int linha = tblUsuarios.getSelectedRow();
        String tarc = tblUsuarios.getValueAt(linha, 0).toString();//linha é alinha selecionada e 0 é a coluna
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tarc);
            rs = pst.executeQuery();

            if (rs.next()) {

                txtIdUso.setText(rs.getString("iduso"));
                txtIdUsoCli.setText(rs.getString("idcli"));
                txtNomeUso.setText(rs.getString("usonome"));
                txtEnduso.setText(rs.getString("usoendereco"));
                txtCidadeUso.setText(rs.getString("usocidade"));
                txtCeluso.setText(rs.getString("usocelular"));
                txtMatUso.setText(rs.getString("usomatricula"));
                txtloginUso.setText(rs.getString("usousuario"));
                txtEmailUso.setText(rs.getString("usoemail"));
                txtSenhaUso.setText(rs.getString("usosenha"));
                txtFuncUso.setText(rs.getString("usofuncao"));
                cmbPerfilUso.setSelectedItem(rs.getString("usoperfil"));
                chkAtivaUso.setSelected(rs.getBoolean("usoativo"));
                txtCpfUso.setText(rs.getString("usocpf"));
                preenchecombobox(rs.getString("idcli"));

                btnAtualizUso.setEnabled(true);
                btnExluiUso.setEnabled(true);

            } else {

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 01" + e);
        }

    }

    private void alterar() {

        String sql = "update usuarios set usonome=?,usoendereco=?,usocidade=?,usocelular=?,usomatricula=?,usousuario=?,usosenha=?,usofuncao=?,usoperfil=?,usocpf=?,usoemail=?,usoativo=?,idcli=?,usoclinome=? where iduso=?";

        try {

            ClienteModel cliente = (ClienteModel) cmbClientes.getSelectedItem();
            pst = conexao.prepareStatement(sql); //preparando a conexão
            pst.setString(1, txtNomeUso.getText());
            pst.setString(2, txtEnduso.getText());
            pst.setString(3, txtCidadeUso.getText());
            pst.setString(4, txtCeluso.getText());
            pst.setString(5, txtMatUso.getText());
            pst.setString(6, txtloginUso.getText());
            pst.setString(7, txtSenhaUso.getText());
            pst.setString(8, txtFuncUso.getText());
            pst.setString(9, cmbPerfilUso.getSelectedItem().toString());
            pst.setString(10, txtCpfUso.getText());
            pst.setString(11, txtEmailUso.getText());
            pst.setBoolean(12, chkAtivaUso.isSelected());
            pst.setInt(13, cliente.getId());
            pst.setString(14, cliente.getNome());
            pst.setInt(15, Integer.parseInt(txtIdUso.getText()));

            // pst.setInt(11, cmbClientes.getIt);
            //a linha abaixo atualiza a tabela usuarios com as informaçoes novas do formulario
            int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 01" + e);
        }

    }

    private void alterarUsuario() {
        if (txtNomeUso.getText().isEmpty() || txtCpfUso.getText().equals("   .   .   -  ") || txtEmailUso.getText().isEmpty() || txtFuncUso.getText().isEmpty() || txtloginUso.getText().isEmpty() || txtSenhaUso.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Os campos com (*) são obrigatórios!", "Atenção!", 2);

        } else {
            alterar();
            limpacampos();
            desativacampos();
        }

    }

    public void consultar() {

        String sql = "select * from usuarios where usonome like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtBuscaUso.getText() + "%");
            rs = pst.executeQuery();

            while (rs.next()) { //enquanto o RS result set for inteiro existe um laço de repetiçao.

                UsuarioModel b = new UsuarioModel();
                b.setNome(rs.getString("usonome"));
                b.setFuncao(rs.getString("usofuncao"));
                b.setCpf(rs.getString("usocpf"));
                b.setTelefone(rs.getString("usocelular"));
                b.setId(rs.getInt("iduso"));
                tableModel.addRow(b);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 01" + e);
        }

    }

    public void incluirUso() {

        if (txtNomeUso.getText().isEmpty() || txtCpfUso.getText().equals("   .   .   -  ") || txtEmailUso.getText().isEmpty() || txtFuncUso.getText().isEmpty() || txtloginUso.getText().isEmpty() || txtSenhaUso.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Os campos com (*) são obrigatórios!", "Atenção!", 2);

        } else {
            adicionarUso();
            limpacampos();
            desativacampos();
        }

    }

    public void desativacampos() {
        //abaixo são os campos
        chkAtivaUso.setEnabled(false);
        cmbClientes.setEnabled(false);
        txtNomeUso.setEnabled(false);
        txtCpfUso.setEnabled(false);
        txtMatUso.setEnabled(false);
        txtCeluso.setEnabled(false);
        txtEmailUso.setEnabled(false);
        cmbPerfilUso.setEnabled(false);
        txtFuncUso.setEnabled(false);
        txtEnduso.setEnabled(false);
        txtCidadeUso.setEnabled(false);
        txtloginUso.setEnabled(false);
        txtSenhaUso.setEnabled(false);
        //abaixo saão os botoes
        btnAtualizUso.setEnabled(false);
        btnExluiUso.setEnabled(false);
        btnLimpaUso.setEnabled(false);
        btnSalvaUso.setEnabled(false);
        btnCancelUso.setEnabled(false);
        btnAdicUso.setEnabled(true);

    }

    public void ativacamposeditauso() {

        chkAtivaUso.setEnabled(true);
        cmbClientes.setEnabled(true);
        txtNomeUso.setEnabled(true);
        txtCpfUso.setEnabled(true);
        txtMatUso.setEnabled(true);
        txtCeluso.setEnabled(true);
        txtEmailUso.setEnabled(true);
        cmbPerfilUso.setEnabled(true);
        txtFuncUso.setEnabled(true);
        txtEnduso.setEnabled(true);
        txtCidadeUso.setEnabled(true);
        txtloginUso.setEnabled(true);
        txtSenhaUso.setEnabled(true);
        //abaixo saão os botoes
        btnAtualizUso.setEnabled(false);
        btnExluiUso.setEnabled(false);
        btnLimpaUso.setEnabled(false);
        btnSalvaUso.setEnabled(true);
        btnCancelUso.setEnabled(true);
        btnAdicUso.setEnabled(false);

    }

    public void ativacamposnovouso() {

        chkAtivaUso.setEnabled(true);
        cmbClientes.setEnabled(true);
        txtNomeUso.setEnabled(true);
        txtCpfUso.setEnabled(true);
        txtMatUso.setEnabled(true);
        txtCeluso.setEnabled(true);
        txtEmailUso.setEnabled(true);
        cmbPerfilUso.setEnabled(true);
        txtFuncUso.setEnabled(true);
        txtEnduso.setEnabled(true);
        txtCidadeUso.setEnabled(true);
        txtloginUso.setEnabled(true);
        txtSenhaUso.setEnabled(true);
        //abaixo saão os botoes
        btnAtualizUso.setEnabled(false);
        btnExluiUso.setEnabled(false);
        btnLimpaUso.setEnabled(true);
        btnSalvaUso.setEnabled(true);
        btnCancelUso.setEnabled(true);
        btnAdicUso.setEnabled(false);
        cmbClientes.removeAllItems();
        consultarClientes();

    }

    public void limpacampos() {

        txtIdUso.setText("");
        txtIdUsoCli.setText("");
        chkAtivaUso.setSelected(true);
        txtNomeUso.setText("");
        txtCpfUso.setText("");
        txtMatUso.setText("");
        txtCeluso.setText("");
        txtEmailUso.setText("");
        cmbPerfilUso.setSelectedItem("Administrador");
        txtFuncUso.setText("");
        txtEnduso.setText("");
        txtCidadeUso.setText("");
        txtloginUso.setText("");
        txtSenhaUso.setText("");
        //abaixo saão os botoes
        btnAtualizUso.setEnabled(false);
        btnExluiUso.setEnabled(false);
        btnLimpaUso.setEnabled(false);
        btnSalvaUso.setEnabled(false);
        btnCancelUso.setEnabled(false);


    }

    private void removerUsuario() {

        String sql = "delete from usuarios where iduso=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdUso.getText());
            int removido = pst.executeUpdate();//caso a adição for concluida cai no if
            if (removido > 0) {
                JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro 01" + e);
        }

    }

    //O METODO ABAIXO PREENCHE A COMBOBOX PARA QUE ELA POSSA EXIBIR OS ITENS.
    public void consultarClientes() {

        String sql = "select * from cliente";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) { //enquanto o RS result set for inteiro existe um laço de repetiçao.

                ClienteModel b = new ClienteModel();

                b.setNome(rs.getString("clirazaosocial"));
                b.setTelefone(rs.getString("clitelefone"));
                b.setId(rs.getInt("idcli"));
                cmbClientes.addItem(b);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 01" + e);
        }

    }

    public void consultarUsuarios() {
        
        tableModel.removeAll();
        String sql = "select * from usuarios";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) { //enquanto o RS result set for inteiro existe um laço de repetiçao.

                UsuarioModel b = new UsuarioModel();
                b.setNome(rs.getString("usonome"));
                b.setFuncao(rs.getString("usofuncao"));
                b.setCpf(rs.getString("usocpf"));
                b.setTelefone(rs.getString("usocelular"));
                b.setId(rs.getInt("iduso"));
                tableModel.addRow(b);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 01" + e);
        }

    }

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
        tblUsuarios.setModel(tableModel);//inicia e seta o modelo da tabela     

        tblUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(170);
        tblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(125);
        tblUsuarios.getColumnModel().getColumn(4).setPreferredWidth(120);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        txtBuscaUso = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtIdUsoCli = new javax.swing.JTextField();
        txtNomeUso = new javax.swing.JTextField();
        txtCpfUso = new javax.swing.JFormattedTextField();
        txtMatUso = new javax.swing.JTextField();
        txtCeluso = new javax.swing.JFormattedTextField();
        cmbPerfilUso = new javax.swing.JComboBox<>();
        txtEnduso = new javax.swing.JTextField();
        txtCidadeUso = new javax.swing.JTextField();
        txtloginUso = new javax.swing.JTextField();
        txtSenhaUso = new javax.swing.JPasswordField();
        chkAtivaUso = new javax.swing.JCheckBox();
        btnBuscaUso = new javax.swing.JButton();
        btnAdicUso = new javax.swing.JButton();
        btnAtualizUso = new javax.swing.JButton();
        btnExluiUso = new javax.swing.JButton();
        btnSairUso = new javax.swing.JButton();
        btnSalvaUso = new javax.swing.JButton();
        txtFuncUso = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmailUso = new javax.swing.JTextField();
        btnCancelUso = new javax.swing.JButton();
        btnLimpaUso = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtIdUso = new javax.swing.JTextField();
        cmbClientes = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setMaximumSize(new java.awt.Dimension(1359, 730));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ID USO:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("* NOME:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("* CPF:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("MATRÍCULA:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("CELULAR:");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("* PERFIL:");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("ENDEREÇO:");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("CIDADE:");

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("* LOGIN:");

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("* SENHA:");

        txtIdUsoCli.setEnabled(false);

        txtNomeUso.setEnabled(false);

        try {
            txtCpfUso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpfUso.setEnabled(false);
        txtCpfUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfUsoActionPerformed(evt);
            }
        });

        txtMatUso.setEnabled(false);

        try {
            txtCeluso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) # ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCeluso.setEnabled(false);

        cmbPerfilUso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Cadastro", "Suporte", "Relatórios" }));
        cmbPerfilUso.setEnabled(false);

        txtEnduso.setEnabled(false);

        txtCidadeUso.setEnabled(false);

        txtloginUso.setEnabled(false);

        txtSenhaUso.setEnabled(false);

        chkAtivaUso.setSelected(true);
        chkAtivaUso.setText("Usuário Ativo");
        chkAtivaUso.setEnabled(false);

        btnBuscaUso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/lupa15x15.png"))); // NOI18N
        btnBuscaUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaUsoActionPerformed(evt);
            }
        });

        btnAdicUso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/create30x30.png"))); // NOI18N
        btnAdicUso.setText("Adicionar");
        btnAdicUso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAdicUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicUsoActionPerformed(evt);
            }
        });

        btnAtualizUso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/update30x30.png"))); // NOI18N
        btnAtualizUso.setText("Atualizar");
        btnAtualizUso.setEnabled(false);
        btnAtualizUso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAtualizUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizUsoActionPerformed(evt);
            }
        });

        btnExluiUso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/delete30x30.png"))); // NOI18N
        btnExluiUso.setText("Excluir");
        btnExluiUso.setToolTipText("");
        btnExluiUso.setEnabled(false);
        btnExluiUso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExluiUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExluiUsoActionPerformed(evt);
            }
        });

        btnSairUso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/sair30x30.png"))); // NOI18N
        btnSairUso.setText("Sair");
        btnSairUso.setToolTipText("");
        btnSairUso.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSairUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairUsoActionPerformed(evt);
            }
        });

        btnSalvaUso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/save30x30.png"))); // NOI18N
        btnSalvaUso.setText("Salva");
        btnSalvaUso.setToolTipText("");
        btnSalvaUso.setEnabled(false);
        btnSalvaUso.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSalvaUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvaUsoActionPerformed(evt);
            }
        });

        txtFuncUso.setEnabled(false);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("* FUNÇÃO:");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("* E-MAIL:");

        txtEmailUso.setEnabled(false);

        btnCancelUso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/cancel30x30.png"))); // NOI18N
        btnCancelUso.setText("Cancela");
        btnCancelUso.setToolTipText("");
        btnCancelUso.setEnabled(false);
        btnCancelUso.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCancelUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelUsoActionPerformed(evt);
            }
        });

        btnLimpaUso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/limpa30x30.png"))); // NOI18N
        btnLimpaUso.setToolTipText("Limpar Campos");
        btnLimpaUso.setEnabled(false);
        btnLimpaUso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLimpaUso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpaUsoActionPerformed(evt);
            }
        });

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("ID CLIENTE:");

        txtIdUso.setEnabled(false);

        cmbClientes.setEnabled(false);
        cmbClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClientesActionPerformed(evt);
            }
        });

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("* VINCULO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnAdicUso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAtualizUso, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addComponent(btnExluiUso, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                    .addComponent(btnLimpaUso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeUso))
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCidadeUso, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtloginUso))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEnduso))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFuncUso, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbPerfilUso, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmailUso, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCeluso)
                                .addGap(153, 153, 153))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtIdUso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdUsoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(chkAtivaUso))
                            .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSenhaUso, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSalvaUso, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCancelUso, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCpfUso))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMatUso, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtBuscaUso, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscaUso, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(187, 187, 187))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSairUso, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel10, jLabel11, jLabel12, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicUso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtualizUso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExluiUso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpaUso)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscaUso)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBuscaUso)
                                .addComponent(chkAtivaUso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdUsoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(jLabel1)
                                .addComponent(txtIdUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtNomeUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtCpfUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtMatUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtCeluso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtEmailUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(cmbPerfilUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtFuncUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtEnduso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtCidadeUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtloginUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtSenhaUso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSalvaUso)
                            .addComponent(btnCancelUso)
                            .addComponent(btnSairUso))
                        .addGap(18, 18, 18))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        int adicionar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja fechar esta janela?", "Fechando a Tela de Usuários", JOptionPane.YES_NO_OPTION, 2);

        if (adicionar == JOptionPane.YES_OPTION) {
            telaUsuario = null;
            TelaUsuario.this.dispose(); //metodo para fechar uma unica janela

        } else {

        }
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnSairUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairUsoActionPerformed
        int adicionar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja fechar esta janela?", "Fechando a Tela de Usuários", JOptionPane.YES_NO_OPTION, 2);

        if (adicionar == JOptionPane.YES_OPTION) {
            telaUsuario = null;
            TelaUsuario.this.dispose(); //metodo para fechar uma unica janela

        } else {

        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnSairUsoActionPerformed

    private void btnCancelUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelUsoActionPerformed
        desativacampos();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelUsoActionPerformed

    private void btnAtualizUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizUsoActionPerformed
        ativacamposeditauso();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtualizUsoActionPerformed

    private void btnAdicUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicUsoActionPerformed
        limpacampos();
        ativacamposnovouso(); 
    }//GEN-LAST:event_btnAdicUsoActionPerformed

    private void btnLimpaUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpaUsoActionPerformed
        limpacampos();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpaUsoActionPerformed

    private void txtCpfUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfUsoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfUsoActionPerformed

    private void btnSalvaUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaUsoActionPerformed
        if (txtIdUso.getText().isEmpty()) {

            incluirUso();
            consultarUsuarios();

        } else {

            alterarUsuario();
            consultarUsuarios();

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvaUsoActionPerformed

    private void btnBuscaUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaUsoActionPerformed
        tableModel.removeAll();
        consultar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscaUsoActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        tableModel.removeAll();
        consultarUsuarios();
        consultarClientes();
// TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameOpened

    private void cmbClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbClientesActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        setarCampos();        // TODO add your handling code here:
        desativacampos();
        btnAtualizUso.setEnabled(true);
        btnExluiUso.setEnabled(true);
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnExluiUsoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExluiUsoActionPerformed
        int adicionar = JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja excluir este usuário?\nEsta Operação não poderá ser desfeita!\n\n -Não será possível reverter esta ação.\n-Use a opção desativar usuário se preferir.\n\nDeseja Prosseguir?", "ATENÇÃO!", JOptionPane.YES_NO_OPTION, 0);

        if (adicionar == JOptionPane.YES_OPTION) {
            removerUsuario();
            tableModel.removeAll();
            consultar();

        } else {

        }              // TODO add your handling code here:
    }//GEN-LAST:event_btnExluiUsoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicUso;
    private javax.swing.JButton btnAtualizUso;
    private javax.swing.JButton btnBuscaUso;
    private javax.swing.JButton btnCancelUso;
    private javax.swing.JButton btnExluiUso;
    private javax.swing.JButton btnLimpaUso;
    private javax.swing.JButton btnSairUso;
    private javax.swing.JButton btnSalvaUso;
    private javax.swing.JCheckBox chkAtivaUso;
    private javax.swing.JComboBox<Object> cmbClientes;
    private javax.swing.JComboBox<String> cmbPerfilUso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtBuscaUso;
    private javax.swing.JFormattedTextField txtCeluso;
    private javax.swing.JTextField txtCidadeUso;
    private javax.swing.JFormattedTextField txtCpfUso;
    private javax.swing.JTextField txtEmailUso;
    private javax.swing.JTextField txtEnduso;
    private javax.swing.JTextField txtFuncUso;
    private javax.swing.JTextField txtIdUso;
    private javax.swing.JTextField txtIdUsoCli;
    private javax.swing.JTextField txtMatUso;
    private javax.swing.JTextField txtNomeUso;
    private javax.swing.JPasswordField txtSenhaUso;
    private javax.swing.JTextField txtloginUso;
    // End of variables declaration//GEN-END:variables
}
