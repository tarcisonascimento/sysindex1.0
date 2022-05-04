package br.com.rheacaoscannerpro.telas;

import br.com.rheacaoscannerpro.dal.ModuloConexao;
import br.com.rheacaoscannerpro.model.ClienteModel;
import br.com.rheacaoscannerpro.model.ClienteTabelModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Tarciso Nascimento
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;//usando o metodo de conexao e atribuindo a conexao limpa para iniciar
    PreparedStatement pst = null; //usado para preparar a conexao com o banco de dados
    ResultSet rs = null;//exibe o resultado das instruçoes sql que sera usado no java
    ClienteTabelModel tableModel = new ClienteTabelModel(); //cria o modelo da tabela

    //o metodo abaixo é utilizado para abrir apenas uma janela no Jdesktop
    public static TelaCliente telaCliente;

    public static TelaCliente getInstancia() {
        if (telaCliente == null) {

            telaCliente = new TelaCliente();

        }
        return telaCliente;
    }

    public void consultar() {

        String sql = "select * from cliente where clirazaosocial like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtBuscaCli.getText() + "%");
            rs = pst.executeQuery();

            while (rs.next()) { //enquanto o RS result set for inteiro existe um laço de repetiçao.

                ClienteModel b = new ClienteModel();
                b.setNome(rs.getString("clirazaosocial"));
                b.setTelefone(rs.getString("clitelefone"));
                b.setId(rs.getInt("idcli"));
                tableModel.addRow(b);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 01" + e);
        }

    }

    //metodo para adicionar usuarios
    private void adicionarCli() {
        //instrução sql para inserção de dados na tabela mysql
        String sql = "insert into cliente (clirazaosocial,clicnpj,clitelefone,cliramal,clicelular,cliemail,clinomerespo,cliendereco,clicidade,cliativo) values (?,?,?,?,?,?,?,?,?,?)";

        try {

            pst = conexao.prepareStatement(sql); //preparando a conexão
            pst.setString(1, txtRazaoCli.getText());
            pst.setString(2, txtCnpjCli.getText());
            pst.setString(3, txtTelCli.getText());
            pst.setString(4, txtRamalCli.getText());
            pst.setString(5, txtCelCli.getText());

            pst.setString(6, txtEmailCli.getText());
            pst.setString(7, txtNomeRespCli.getText());
            pst.setString(8, txtEndCli.getText());
            pst.setString(9, txtCidadeCli.getText());
            pst.setBoolean(10, chkAtivaUso.isSelected());

            //a linha abaixo atualiza a tabela usuarios com as informaçoes novas do formulario
            int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 01" + e);
        }

    }

    private void alterarCliente() {

        if (txtRazaoCli.getText().isEmpty() || txtCnpjCli.getText().equals("   .   .   -  ") || txtEmailCli.getText().isEmpty() || txtNomeRespCli.getText().isEmpty() || txtTelCli.getText().equals("(  )     -    ") || txtCelCli.getText().equals("(  )       -    ") || txtEndCli.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Os campos com (*) são obrigatórios!", "Atenção!", 2);

        } else {

            String sql = "update cliente set clirazaosocial=?,clicnpj=?,clitelefone=?,cliramal=?,clicelular=?,cliemail=?,clinomerespo=?,cliendereco=?,clicidade=?,cliativo=? where idcli=?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtRazaoCli.getText());
                pst.setString(2, txtCnpjCli.getText());
                pst.setString(3, txtTelCli.getText());
                pst.setString(4, txtRamalCli.getText());
                pst.setString(5, txtCelCli.getText());
                pst.setString(6, txtEmailCli.getText());
                pst.setString(7, txtNomeRespCli.getText());
                pst.setString(8, txtEndCli.getText());
                pst.setString(9, txtCidadeCli.getText());
                pst.setBoolean(10, chkAtivaUso.isSelected());
                pst.setString(11, txtIdCli.getText());

                int adicionado = pst.executeUpdate();//caso a adição for concluida cai no if
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro 01" + e);
            }

        }
    }

    private void removerCliente() {

        String sql = "delete from cliente where idcli=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtIdCli.getText());
            int removido = pst.executeUpdate();//caso a adição for concluida cai no if
            if (removido > 0) {
                JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro 01" + e);
        }

    }

    public void setarCampos() {
        String sql = "select * from cliente where idcli=?";
        int linha = jTableCliente.getSelectedRow();
        String tarc = jTableCliente.getValueAt(linha, 0).toString();//linha é alinha selecionada e 0 é a coluna
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tarc);
            rs = pst.executeQuery();

            if (rs.next()) {

                txtIdCli.setText(rs.getString("idcli"));
                txtRazaoCli.setText(rs.getString("clirazaosocial"));
                txtCnpjCli.setText(rs.getString("clicnpj"));
                txtTelCli.setText(rs.getString("clitelefone"));
                txtRamalCli.setText(rs.getString("cliramal"));
                txtCelCli.setText(rs.getString("clicelular"));
                txtEmailCli.setText(rs.getString("cliemail"));
                txtNomeRespCli.setText(rs.getString("clinomerespo"));
                txtEndCli.setText(rs.getString("cliendereco"));
                txtCidadeCli.setText(rs.getString("clicidade"));
                chkAtivaUso.setSelected(rs.getBoolean("cliativo"));
                btnAtualizCli.setEnabled(true);
                btnExluiCli.setEnabled(true);

            } else {

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro 01" + e);
        }

    }

    public void incluirCli() {

        if (txtRazaoCli.getText().isEmpty() || txtCnpjCli.getText().equals("   .   .   -  ") || txtEmailCli.getText().isEmpty() || txtNomeRespCli.getText().isEmpty() || txtTelCli.getText().equals("(  )     -    ") || txtCelCli.getText().equals("(  )       -    ") || txtEndCli.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Os campos com (*) são obrigatórios!", "Atenção!", 2);

        } else {
            adicionarCli();
            limpacampos();
            desativacampos();
        }

    }

    public void desativacampos() {

        txtCelCli.setEnabled(false);
        txtTelCli.setEnabled(false);
        txtRamalCli.setEnabled(false);
        txtCidadeCli.setEnabled(false);
        txtCnpjCli.setEnabled(false);
        txtEmailCli.setEnabled(false);
        txtEndCli.setEnabled(false);
        txtNomeRespCli.setEnabled(false);
        txtRazaoCli.setEnabled(false);
        btnAtualizCli.setEnabled(false);
        btnCancelCli.setEnabled(false);
        btnExluiCli.setEnabled(false);
        btnSalvaCli.setEnabled(false);
        btnLimpaCli.setEnabled(false);
        btnAdicCli.setEnabled(true);
        chkAtivaUso.setEnabled(false);

    }

    public void ativacamposeditauso() {

        txtCelCli.setEnabled(true);
        txtTelCli.setEnabled(true);
        txtRamalCli.setEnabled(true);
        txtCidadeCli.setEnabled(true);
        txtCnpjCli.setEnabled(true);
        txtEmailCli.setEnabled(true);
        txtEndCli.setEnabled(true);
        txtNomeRespCli.setEnabled(true);
        txtRazaoCli.setEnabled(true);
        btnAtualizCli.setEnabled(false);
        btnCancelCli.setEnabled(true);
        btnSalvaCli.setEnabled(true);
        btnExluiCli.setEnabled(false);
        btnAdicCli.setEnabled(false);
        chkAtivaUso.setEnabled(true);

    }

    public void desativacampostable() {

        txtCelCli.setEnabled(false);
        txtTelCli.setEnabled(false);
        txtRamalCli.setEnabled(false);
        txtCidadeCli.setEnabled(false);
        txtCnpjCli.setEnabled(false);
        txtEmailCli.setEnabled(false);
        txtEndCli.setEnabled(false);
        txtNomeRespCli.setEnabled(false);
        txtRazaoCli.setEnabled(false);
        btnAtualizCli.setEnabled(true);
        btnCancelCli.setEnabled(false);
        btnSalvaCli.setEnabled(false);
        btnExluiCli.setEnabled(true);
        btnAdicCli.setEnabled(true);
        chkAtivaUso.setEnabled(false);

    }

    public void ativacamposnovouso() {

        txtCelCli.setEnabled(true);
        txtTelCli.setEnabled(true);
        txtRamalCli.setEnabled(true);
        txtCidadeCli.setEnabled(true);
        txtCnpjCli.setEnabled(true);
        txtEmailCli.setEnabled(true);
        txtEndCli.setEnabled(true);
        txtNomeRespCli.setEnabled(true);
        txtRazaoCli.setEnabled(true);
        btnCancelCli.setEnabled(true);
        btnSalvaCli.setEnabled(true);
        btnLimpaCli.setEnabled(true);
        btnExluiCli.setEnabled(false);
        btnAtualizCli.setEnabled(false);
        btnAdicCli.setEnabled(false);
        chkAtivaUso.setEnabled(true);
        chkAtivaUso.setSelected(true);

    }

    public void limpacampos() {

        txtCelCli.setText("");
        txtTelCli.setText("");
        txtRamalCli.setText("");
        txtCidadeCli.setText("");
        txtCnpjCli.setText("");
        txtEmailCli.setText("");
        txtEndCli.setText("");
        txtNomeRespCli.setText("");
        txtRazaoCli.setText("");
        txtIdCli.setText("");

    }

    /**
     * Creates new form TelaUsuario
     */
    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
        jTableCliente.setModel(tableModel);//inicia e seta o modelo da tabela

        jTableCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableCliente.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTableCliente.getColumnModel().getColumn(1).setPreferredWidth(276);
        jTableCliente.getColumnModel().getColumn(2).setPreferredWidth(106);

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
        jTableCliente = new javax.swing.JTable();
        txtBuscaCli = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtRazaoCli = new javax.swing.JTextField();
        txtCnpjCli = new javax.swing.JFormattedTextField();
        txtCelCli = new javax.swing.JFormattedTextField();
        txtEndCli = new javax.swing.JTextField();
        txtCidadeCli = new javax.swing.JTextField();
        chkAtivaUso = new javax.swing.JCheckBox();
        btnBuscaCli = new javax.swing.JButton();
        btnAdicCli = new javax.swing.JButton();
        btnAtualizCli = new javax.swing.JButton();
        btnExluiCli = new javax.swing.JButton();
        btnSairCli = new javax.swing.JButton();
        btnSalvaCli = new javax.swing.JButton();
        txtNomeRespCli = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmailCli = new javax.swing.JTextField();
        btnCancelCli = new javax.swing.JButton();
        btnLimpaCli = new javax.swing.JButton();
        txtIdCli = new javax.swing.JTextField();
        txtTelCli = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        txtRamalCli = new javax.swing.JTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cliente");
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

        jTableCliente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCliente);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ID:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("* RAZÃO SOCIAL:");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("* CNPJ:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("* TELEFONE:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("* CELULAR RESPONSÁVEL:");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("* ENDEREÇO:");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("CIDADE:");

        txtRazaoCli.setEnabled(false);

        try {
            txtCnpjCli.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCnpjCli.setEnabled(false);
        txtCnpjCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCnpjCliActionPerformed(evt);
            }
        });

        try {
            txtCelCli.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) # ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCelCli.setEnabled(false);

        txtEndCli.setEnabled(false);

        txtCidadeCli.setEnabled(false);

        chkAtivaUso.setSelected(true);
        chkAtivaUso.setText("CLIENTE ATIVO");
        chkAtivaUso.setEnabled(false);

        btnBuscaCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/lupa15x15.png"))); // NOI18N
        btnBuscaCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaCliActionPerformed(evt);
            }
        });

        btnAdicCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/create30x30.png"))); // NOI18N
        btnAdicCli.setText("Adicionar");
        btnAdicCli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAdicCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicCliActionPerformed(evt);
            }
        });

        btnAtualizCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/update30x30.png"))); // NOI18N
        btnAtualizCli.setText("Atualizar");
        btnAtualizCli.setEnabled(false);
        btnAtualizCli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAtualizCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizCliActionPerformed(evt);
            }
        });

        btnExluiCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/delete30x30.png"))); // NOI18N
        btnExluiCli.setText("Excluir");
        btnExluiCli.setToolTipText("");
        btnExluiCli.setEnabled(false);
        btnExluiCli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExluiCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExluiCliActionPerformed(evt);
            }
        });

        btnSairCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/sair30x30.png"))); // NOI18N
        btnSairCli.setText("Sair");
        btnSairCli.setToolTipText("");
        btnSairCli.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnSairCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairCliActionPerformed(evt);
            }
        });

        btnSalvaCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/save30x30.png"))); // NOI18N
        btnSalvaCli.setText("Salva");
        btnSalvaCli.setToolTipText("");
        btnSalvaCli.setEnabled(false);
        btnSalvaCli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSalvaCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvaCliActionPerformed(evt);
            }
        });

        txtNomeRespCli.setEnabled(false);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("* NOME RESPONSÁVEL");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("* E-MAIL CORPORATIVO:");

        txtEmailCli.setEnabled(false);

        btnCancelCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/cancel30x30.png"))); // NOI18N
        btnCancelCli.setText("Cancela");
        btnCancelCli.setToolTipText("");
        btnCancelCli.setEnabled(false);
        btnCancelCli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCancelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelCliActionPerformed(evt);
            }
        });

        btnLimpaCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/limpa30x30.png"))); // NOI18N
        btnLimpaCli.setToolTipText("Limpar Campos");
        btnLimpaCli.setEnabled(false);
        btnLimpaCli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLimpaCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpaCliActionPerformed(evt);
            }
        });

        txtIdCli.setEnabled(false);

        try {
            txtTelCli.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelCli.setEnabled(false);
        txtTelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelCliActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("RAMAL:");

        txtRamalCli.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdicCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAtualizCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExluiCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(167, 167, 167)
                                        .addComponent(chkAtivaUso))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtRazaoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnSalvaCli, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                                            .addComponent(btnCancelCli, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtCidadeCli, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtCnpjCli))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtTelCli, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtRamalCli, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(106, 106, 106))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel5))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCelCli, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtEmailCli)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtEndCli))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtNomeRespCli))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtBuscaCli, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscaCli, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLimpaCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSairCli, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel12, jLabel2, jLabel3, jLabel4, jLabel5, jLabel7, jLabel8, jLabel9});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtualizCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExluiCli)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkAtivaUso, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(txtIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnBuscaCli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtBuscaCli, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtRazaoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtCnpjCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTelCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtRamalCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtCelCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtEmailCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtNomeRespCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtEndCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtCidadeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelCli)
                            .addComponent(btnSalvaCli))
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimpaCli)
                            .addComponent(btnSairCli))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        int adicionar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja fechar esta janela?", "Fechando a Tela de Usuários", JOptionPane.YES_NO_OPTION, 2);

        if (adicionar == JOptionPane.YES_OPTION) {
            telaCliente = null;
            TelaCliente.this.dispose(); //metodo para fechar uma unica janela

        } else {

        }
    }//GEN-LAST:event_formInternalFrameClosing

    private void btnSairCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairCliActionPerformed
        int adicionar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja fechar esta janela?", "Fechando a Tela de Usuários", JOptionPane.YES_NO_OPTION, 2);

        if (adicionar == JOptionPane.YES_OPTION) {
            telaCliente = null;
            TelaCliente.this.dispose(); //metodo para fechar uma unica janela

        } else {

        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnSairCliActionPerformed

    private void btnCancelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelCliActionPerformed
        desativacampos();        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelCliActionPerformed

    private void btnAtualizCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizCliActionPerformed
        ativacamposeditauso();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtualizCliActionPerformed

    private void btnAdicCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicCliActionPerformed
        limpacampos();
        ativacamposnovouso();        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdicCliActionPerformed

    private void btnLimpaCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpaCliActionPerformed
        limpacampos();        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimpaCliActionPerformed

    private void txtCnpjCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCnpjCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCnpjCliActionPerformed

    private void btnSalvaCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvaCliActionPerformed
        if (txtIdCli.getText().isEmpty()) {
            incluirCli();
            desativacampos();
            tableModel.removeAll();
            consultar();
        } else {
            alterarCliente();
            desativacampos();
            tableModel.removeAll();
            consultar();
        }      // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvaCliActionPerformed

    private void txtTelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelCliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelCliActionPerformed

    private void btnBuscaCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaCliActionPerformed
        tableModel.removeAll();
        consultar();        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscaCliActionPerformed

    private void jTableClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClienteMouseClicked
        setarCampos();    // TODO add your handling code here:
        desativacampostable();
    }//GEN-LAST:event_jTableClienteMouseClicked

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        tableModel.removeAll();
        consultar();             // TODO add your handling code here:
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnExluiCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExluiCliActionPerformed
        int adicionar = JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja excluir este cliente?\nEsta Operação não poderá ser desfeita!\n\n -Todos os usuários deste cliente perderão seu acesso.\n -Não será possível reverter esta ação.\n-Use a opção desativar cliente\n\nDeseja Prosseguir?", "ATENÇÃO!", JOptionPane.YES_NO_OPTION, 0);

        if (adicionar == JOptionPane.YES_OPTION) {
            removerCliente();
            tableModel.removeAll();
            consultar();

        } else {

        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnExluiCliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicCli;
    private javax.swing.JButton btnAtualizCli;
    private javax.swing.JButton btnBuscaCli;
    private javax.swing.JButton btnCancelCli;
    private javax.swing.JButton btnExluiCli;
    private javax.swing.JButton btnLimpaCli;
    private javax.swing.JButton btnSairCli;
    private javax.swing.JButton btnSalvaCli;
    private javax.swing.JCheckBox chkAtivaUso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCliente;
    private javax.swing.JTextField txtBuscaCli;
    private javax.swing.JFormattedTextField txtCelCli;
    private javax.swing.JTextField txtCidadeCli;
    private javax.swing.JFormattedTextField txtCnpjCli;
    private javax.swing.JTextField txtEmailCli;
    private javax.swing.JTextField txtEndCli;
    private javax.swing.JTextField txtIdCli;
    private javax.swing.JTextField txtNomeRespCli;
    private javax.swing.JTextField txtRamalCli;
    private javax.swing.JTextField txtRazaoCli;
    private javax.swing.JFormattedTextField txtTelCli;
    // End of variables declaration//GEN-END:variables
}
