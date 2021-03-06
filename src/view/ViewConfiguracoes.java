package view;

import controller.ControllerConfiguracoes;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import util.Request;

/**
 *
 * @author Géssica
 */
public class ViewConfiguracoes extends javax.swing.JDialog {

    private Request request;
    private final ControllerConfiguracoes controllerConfiguracoes = new ControllerConfiguracoes();

    public ViewConfiguracoes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        buttonGruop();
        fillFields();
        this.setLocationRelativeTo(null);
    }

    private void buttonGruop() {
        buttonGroup.add(jRadioButtonSSL);
        buttonGroup.add(jRadioButtonTLS);
        buttonGroup.add(jRadioButtonNenhum);
    }

    private String getSelectedTipoCript() {
        if (jRadioButtonSSL.isSelected()) {
            return "SSL";
        } else if (jRadioButtonTLS.isSelected()) {
            return "TLS";
        } else {
            return "Nenhum";
        }
    }

    public boolean fieldValidation() {
        if (!controllerConfiguracoes.validateEmail(jTextFieldEmail.getText())) {
            JOptionPane.showMessageDialog(this,"Campo \"E-mail\" não é um endereço válido.");
            return false;
        } else if (jPasswordFieldSenha.getPassword().length < 1) {
            JOptionPane.showMessageDialog(this, "O campo \"Senha\" é obrigatório.");
            return false;
        } else if (jTextFieldPorta.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo \"Porta\" é obrigatório.");
            return false;
        } else if (jTextFieldServidor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo \"Servidor\" é obrigatório.");
            return false;
        } else if (!Arrays.equals(jPasswordFieldSenha.getPassword(), jPasswordFieldConfirmaSenha.getPassword())) {
            JOptionPane.showMessageDialog(this,"Campos \"Senha\"  e \"Confirmar senha\" não correspondem.");
            return false;
        } else {
            return true;
        }
    }
    
    public void jTextFieldSomenteNumeros(java.awt.event.KeyEvent evt) {
        String caracteres = "9876543210";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }

    private void selecionarCheckBox() {
        if (request.getData("Configuracoes.crip").equals("SSL")) {
            jRadioButtonSSL.setSelected(true);
        } else if (request.getData("Configuracoes.crip").equals("TLS")) {
            jRadioButtonTLS.setSelected(true);
        } else {
            jRadioButtonNenhum.setSelected(true);
        }
    }

    private void fillFields() {

        request = controllerConfiguracoes.findConfiguracao();

        jTextFieldEmail.setText(request.getData("Configuracoes.email"));
        jPasswordFieldSenha.setText(request.getData("Configuracoes.senha"));
        jTextFieldServidor.setText(request.getData("Configuracoes.servidor"));
        jTextFieldPorta.setText(request.getData("Configuracoes.porta"));
        selecionarCheckBox();
    }

    private void save() {
        if (!fieldValidation()) {
            return;
        }

        Map<String, String> data = new HashMap<>();
        data.put("Configuracoes.email", jTextFieldEmail.getText());
        String senha = new String(jPasswordFieldSenha.getPassword());
        data.put("Configuracoes.senha", senha);
        data.put("Configuracoes.porta", jTextFieldPorta.getText());
        data.put("Configuracoes.servidor", jTextFieldServidor.getText());
        data.put("Configuracoes.crip", getSelectedTipoCript());

        data.put("Configuracoes.id", request.getData("Configuracoes.id"));
        request = new Request(data);
        boolean isDone = controllerConfiguracoes.updateConfiguracao(request);

        if (isDone) {
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Erro ao salvar.", "ERRO AO SALVAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldServidor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jTextFieldPorta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanelTipoCript = new javax.swing.JPanel();
        jRadioButtonSSL = new javax.swing.JRadioButton();
        jRadioButtonTLS = new javax.swing.JRadioButton();
        jRadioButtonNenhum = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jPasswordFieldConfirmaSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configurações");

        jLabel1.setText("E-mail de envio:");

        jLabel2.setText("Servidor SMTP:");

        jLabel4.setText("Senha:");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jTextFieldPorta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPortaKeyTyped(evt);
            }
        });

        jLabel7.setText("Porta SMTP:");

        jPanelTipoCript.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Criptografia:"));

        jRadioButtonSSL.setText("SSL");

        jRadioButtonTLS.setText("TLS");

        jRadioButtonNenhum.setText("Nenhuma");

        javax.swing.GroupLayout jPanelTipoCriptLayout = new javax.swing.GroupLayout(jPanelTipoCript);
        jPanelTipoCript.setLayout(jPanelTipoCriptLayout);
        jPanelTipoCriptLayout.setHorizontalGroup(
            jPanelTipoCriptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTipoCriptLayout.createSequentialGroup()
                .addComponent(jRadioButtonSSL)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonTLS)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonNenhum)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelTipoCriptLayout.setVerticalGroup(
            jPanelTipoCriptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTipoCriptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButtonSSL)
                .addComponent(jRadioButtonTLS)
                .addComponent(jRadioButtonNenhum))
        );

        jLabel6.setText("Confirmar senha:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelTipoCript, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldPorta)
                            .addComponent(jTextFieldServidor)
                            .addComponent(jTextFieldEmail)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(58, 58, 58)
                        .addComponent(jPasswordFieldSenha))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jButtonCancelar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPasswordFieldConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jPasswordFieldConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldServidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelTipoCript, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        save();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTextFieldPortaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPortaKeyTyped
        jTextFieldSomenteNumeros(evt);
    }//GEN-LAST:event_jTextFieldPortaKeyTyped

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
            java.util.logging.Logger.getLogger(ViewConfiguracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewConfiguracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewConfiguracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewConfiguracoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ViewConfiguracoes dialog = new ViewConfiguracoes(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanelTipoCript;
    private javax.swing.JPasswordField jPasswordFieldConfirmaSenha;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JRadioButton jRadioButtonNenhum;
    private javax.swing.JRadioButton jRadioButtonSSL;
    private javax.swing.JRadioButton jRadioButtonTLS;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldPorta;
    private javax.swing.JTextField jTextFieldServidor;
    // End of variables declaration//GEN-END:variables
}
