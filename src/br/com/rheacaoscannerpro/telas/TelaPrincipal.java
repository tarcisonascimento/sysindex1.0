package br.com.rheacaoscannerpro.telas;

import br.com.rheacaoscannerpro.classes.GerenciadorDeJanelas;
import br.com.rheacaoscannerpro.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


/**
 *
 * @author Tarciso Nascimento
 */
public class TelaPrincipal extends javax.swing.JFrame {
    Connection conexao = null;//usando o metodo de conexao e atribuindo a conexao limpa para iniciar
    PreparedStatement pst = null; //usado para preparar a conexao com o banco de dados
    ResultSet rs = null;//exibe o resultado das instruçoes sql que sera usado no java
    GerenciadorDeJanelas gerenciadorDeJanela; //variavel para trabalhar com o gerenciador de janelas
    
    


    
    
    
    public TelaPrincipal() {
        initComponents();
        conexao = ModuloConexao.conector();
        this.gerenciadorDeJanela = new GerenciadorDeJanelas(Desktop);//passar o desktop para dentro da variavel 

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblPerfil = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        lblCliente = new javax.swing.JLabel();
        lblIdCli = new javax.swing.JLabel();
        lblIdUso = new javax.swing.JLabel();
        Desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenArq = new javax.swing.JMenu();
        MenArqRel = new javax.swing.JMenuItem();
        MenArqSair = new javax.swing.JMenuItem();
        MenCadastro = new javax.swing.JMenu();
        MenCadastroUsu = new javax.swing.JMenuItem();
        MenCadastroCli = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        MenEditEmp = new javax.swing.JMenuItem();
        MenSobre = new javax.swing.JMenu();
        MenSobreVer = new javax.swing.JMenuItem();
        MenAjuda = new javax.swing.JMenu();
        MenAjudaSup = new javax.swing.JMenuItem();
        MenAjudaBkp = new javax.swing.JMenuItem();
        MenAjudaResBkp = new javax.swing.JMenuItem();
        MenEditLic = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Rheação Scanner Pro 1.0");
        setName("Rheação Scanner Pro 1.0"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        lblPerfil.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblPerfil.setForeground(new java.awt.Color(255, 255, 255));
        lblPerfil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPerfil.setText("Perfil: Perfil de Acesso ");

        lblUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lblUsuario.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUsuario.setText("Usuário: Nome do Usuário");

        lblData.setBackground(new java.awt.Color(255, 255, 255));
        lblData.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblData.setForeground(new java.awt.Color(255, 255, 255));
        lblData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblData.setText("Acesso: dd/mm/aaaa | 00:00");

        lblCliente.setBackground(new java.awt.Color(255, 255, 255));
        lblCliente.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCliente.setText("Cliente: Nome Cliente");

        lblIdCli.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblIdCli.setForeground(new java.awt.Color(255, 255, 255));
        lblIdCli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblIdCli.setText("IDCLI: 00");

        lblIdUso.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblIdUso.setForeground(new java.awt.Color(255, 255, 255));
        lblIdUso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblIdUso.setText("IDUSO: 00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIdUso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(lblPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblIdUso, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblIdCli, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1360, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );

        MenArq.setText("Arquivo");

        MenArqRel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenArqRel.setText("Processos");
        MenArqRel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenArqRelActionPerformed(evt);
            }
        });
        MenArq.add(MenArqRel);

        MenArqSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenArqSair.setText("Sair");
        MenArqSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenArqSairActionPerformed(evt);
            }
        });
        MenArq.add(MenArqSair);

        jMenuBar1.add(MenArq);

        MenCadastro.setText("Cadastro");

        MenCadastroUsu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenCadastroUsu.setText("Cadastro de Usuarios");
        MenCadastroUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenCadastroUsuActionPerformed(evt);
            }
        });
        MenCadastro.add(MenCadastroUsu);

        MenCadastroCli.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenCadastroCli.setText("Cadastro de Clientes");
        MenCadastroCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenCadastroCliActionPerformed(evt);
            }
        });
        MenCadastro.add(MenCadastroCli);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem1.setText("Cadastros Auxiliares");
        MenCadastro.add(jMenuItem1);

        MenEditEmp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenEditEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/ddd.png"))); // NOI18N
        MenEditEmp.setText("Informações da Empresa");
        MenCadastro.add(MenEditEmp);

        jMenuBar1.add(MenCadastro);

        MenSobre.setText("Sobre");

        MenSobreVer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenSobreVer.setText("Rheação Scanner Pro Versão 1.0");
        MenSobreVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenSobreVerActionPerformed(evt);
            }
        });
        MenSobre.add(MenSobreVer);

        jMenuBar1.add(MenSobre);

        MenAjuda.setText("Ajuda");

        MenAjudaSup.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenAjudaSup.setText("Suporte");
        MenAjuda.add(MenAjudaSup);

        MenAjudaBkp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenAjudaBkp.setText("Backup");
        MenAjuda.add(MenAjudaBkp);

        MenAjudaResBkp.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenAjudaResBkp.setText("Restaurar Backup");
        MenAjudaResBkp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenAjudaResBkpActionPerformed(evt);
            }
        });
        MenAjuda.add(MenAjudaResBkp);

        MenEditLic.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, java.awt.event.InputEvent.ALT_DOWN_MASK));
        MenEditLic.setText("Licença");
        MenAjuda.add(MenEditLic);

        jMenuBar1.add(MenAjuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Desktop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenAjudaResBkpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenAjudaResBkpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenAjudaResBkpActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
    
        Date data = new Date();
        DateFormat formato = DateFormat.getDateInstance(DateFormat.FULL);
        lblData.setText("Data: "+formato.format(data));
      
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
     int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?","Encerrando o sistema" ,JOptionPane.YES_NO_OPTION,2);
        
        if (sair == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        
        } else {
        }
    }//GEN-LAST:event_formWindowClosing

    private void MenSobreVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenSobreVerActionPerformed
        TelaSobre sobre = new TelaSobre();
        sobre.setVisible(true); // TODO add your handling code here:
    }//GEN-LAST:event_MenSobreVerActionPerformed

    private void MenArqSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenArqSairActionPerformed
      //exibe uma caixa de dialogo
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?","Encerrando o sistema" ,JOptionPane.YES_NO_OPTION,2);
        
        if (sair == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        
        } else {
        }        // TODO add your handling code here:
    }//GEN-LAST:event_MenArqSairActionPerformed

    private void MenCadastroUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenCadastroUsuActionPerformed
    gerenciadorDeJanela.abrirJanelas(TelaUsuario.getInstancia());//instanciando a tela pela classe gerenciador de janelas        // TODO add your handling code here:
    }//GEN-LAST:event_MenCadastroUsuActionPerformed

    private void MenCadastroCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenCadastroCliActionPerformed
    gerenciadorDeJanela.abrirJanelas(TelaCliente.getInstancia());//instanciando a tela pela classe gerenciador de janelas              // TODO add your handling code here:
    }//GEN-LAST:event_MenCadastroCliActionPerformed

    private void MenArqRelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenArqRelActionPerformed
    gerenciadorDeJanela.abrirJanelas(TelaProcessos.getInstancia());//instanciando a tela pela classe gerenciador de janelas              // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_MenArqRelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenu MenAjuda;
    private javax.swing.JMenuItem MenAjudaBkp;
    private javax.swing.JMenuItem MenAjudaResBkp;
    private javax.swing.JMenuItem MenAjudaSup;
    private javax.swing.JMenu MenArq;
    private javax.swing.JMenuItem MenArqRel;
    private javax.swing.JMenuItem MenArqSair;
    private javax.swing.JMenu MenCadastro;
    private javax.swing.JMenuItem MenCadastroCli;
    public static javax.swing.JMenuItem MenCadastroUsu;
    public static javax.swing.JMenuItem MenEditEmp;
    private javax.swing.JMenuItem MenEditLic;
    private javax.swing.JMenu MenSobre;
    private javax.swing.JMenuItem MenSobreVer;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    public static javax.swing.JLabel lblCliente;
    public static javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblIdCli;
    public static javax.swing.JLabel lblIdUso;
    public static javax.swing.JLabel lblPerfil;
    public static javax.swing.JLabel lblUsuario;
    // End of variables declaration//GEN-END:variables

}
