package br.com.rheacaoscannerpro.telas;

import br.com.rheacaoscannerpro.dal.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Tarciso Nascimento
 */
public class Login extends javax.swing.JFrame {
    
    
    

    /**
     * Creates new form Login
     */
    Connection conexao = null;//usando o metodo de conexao e atribuindo a conexao limpa para iniciar
    PreparedStatement pst = null; //usado para preparar a conexao com o banco de dados
    ResultSet rs = null;//exibe o resultado das instruçoes sql que sera usado no java

    //aqui sera a tela de logar
    public void logar() {

        String sql = "select * from usuarios where usousuario=? and usosenha=?";//essa variavel vai pesquisar no banco de dados usuario e senha; essa variavel usa comando sql
        try {
            //as linhas abaixo preparam a consulta no banco de dados em funçao do que foi digitado no txt.
            //o ? é substituido pelo conteudo das variaveis.
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText());
            pst.setString(2, txtSenha.getText());

            //a linha abaixo executa a consulta ao banco de dados (query)
            rs = pst.executeQuery();
            //se existir usuario e senha correspondente
            if (rs.next()) {//aqui ele libera a tela se estiver tudo ok
                //a linha abaixo obtem o contedudo do campo perfil da tabela usuario
                String perfil = rs.getString("usoperfil");
                String usonome = rs.getString("usonome");
                // System.out.println(perfil);
                //a estrutura de decisao abaixo classifica o usuario de acordo com o perfil

                if (perfil.equals("Administrador")) {

                    TelaPrincipal principal = new TelaPrincipal();//instanciando a tela
                    //principal.setExtendedState(JFrame.MAXIMIZED_BOTH);//iniciando a tela maximizado
                    principal.setVisible(true);
                    //TelaPrincipal.//ativando os botoes desativados pelo perfil
                    TelaPrincipal.MenCadastroUsu.setEnabled(true);
                    TelaPrincipal.MenEditEmp.setEnabled(true);
                    TelaPrincipal.lblUsuario.setText("Usuário: " + usonome);
                    TelaPrincipal.lblPerfil.setText("Perfil: " + rs.getString("usoperfil"));//aqui passa para o lbl o valor do perfil em banco de dados

                    this.dispose(); //fecha a tela de login
                    conexao.close();// fecha a conexao criada anteriormente

                } else {

                    TelaPrincipal principal = new TelaPrincipal();//instanciando a tela
                    //principal.setExtendedState(JFrame.MAXIMIZED_BOTH);//iniciando a tela maximizado
                    principal.setVisible(true);
                    TelaPrincipal.lblUsuario.setText(usonome);
                    TelaPrincipal.lblPerfil.setText("Perfil: " + rs.getString("usoperfil"));//aqui passa para o lbl o valor do perfil em banco de dados
                    this.dispose(); //fecha a tela de login
                    conexao.close();// fecha a conexao criada anteriormente

                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario e/ou senha inválido(s)");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public Login() {
        initComponents();
        conexao = ModuloConexao.conector();

        if (conexao != null) {
            lblStatusConexao.setText("Conexão com banco de dados foi bem sucedida!");
            lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/dbok.png")));
        } else {
            lblStatusConexao.setText("A conexão com banco de dados falhou");
            lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/dberror.png")));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnEntrarLogin = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        lblStatusConexao = new javax.swing.JLabel();
        lblIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rheação - Scanner Pro 1.0 | LOGIN");
        setName("Login - Rheação Scanner Pro 1.0"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/rheacaoscannerpro/icons/logo (2).png"))); // NOI18N

        btnEntrarLogin.setText("ENTRAR");
        btnEntrarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarLoginActionPerformed(evt);
            }
        });

        jLabel2.setText("Usuário");

        jLabel3.setText("Senha");

        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtSenha.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));
        jPanel1.setForeground(new java.awt.Color(51, 102, 255));

        lblStatusConexao.setForeground(new java.awt.Color(255, 255, 255));
        lblStatusConexao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStatusConexao.setText("Status de Conexão");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(lblStatusConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblStatusConexao))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtSenha, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnEntrarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2)
                        .addGap(0, 0, 0)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(0, 0, 0)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEntrarLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarLoginActionPerformed
        if (conexao != null) {
            logar();
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao conectar o Banco de dados Contate o Suporte!", "Erro de Conexão", 0);
        }
    }//GEN-LAST:event_btnEntrarLoginActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        getRootPane().setDefaultButton(btnEntrarLogin);//deixando o btnLogin como defalt        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrarLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblStatusConexao;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
