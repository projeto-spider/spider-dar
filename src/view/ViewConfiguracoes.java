package view;

import controller.ControllerConfiguracoes;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import model.Configuracoes;
import settings.Constant;
import util.Request;

/**
 *
 * @author Géssica
 */
public class ViewConfiguracoes extends javax.swing.JDialog {

    private int type;
    private Configuracoes configuracoes;
    private Request request;
    private final ControllerConfiguracoes controllerConfiguracoes = new ControllerConfiguracoes();
    private int idConfiguracoes;
    
    public ViewConfiguracoes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        buttonGruop();
        fillFields(this.idConfiguracoes);
        jRadioButtonSSL.setSelected(true);
        this.setLocationRelativeTo(null);
    }
    
    private void buttonGruop() {
        buttonGroup.add(jRadioButtonSSL);
        buttonGroup.add(jRadioButtonTLS);
        buttonGroup.add(jRadioButtonNenhum);
    }
    
     private void selecionarCheckBox() {
        if (configuracoes.getTipoCript().equals("SSL"))
            jRadioButtonSSL.setSelected(true);
        else if (configuracoes.getTipoCript().equals("TLS")) {
            jRadioButtonTLS.setSelected(true);
        } else 
            jRadioButtonNenhum.setSelected(true);
    }
     
    private String getSelectedTipoCript() {
        if (jRadioButtonSSL.isSelected())
            return "SSL";
        else if (jRadioButtonTLS.isSelected()) {
            return "TLS";
        } else
            return "Nenhum";
    }
    

    public boolean fieldValidation() {
        if (jTextFieldEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo \"E-mail\" é obrigatório.");
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
        } else
            return true;
    }
    
     private void fillFields(int idConfiguracoes) {

        request = new Request();
        request = controllerConfiguracoes.findConfiguracoesById(idConfiguracoes);


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
        data.put("Configuracoes.tipoCript", getSelectedTipoCript());
        
        boolean isDone = false;
        if (type == Constant.CREATE) {
            request = new Request(data);
            isDone = controllerConfiguracoes.createConfiguracao(request);
        } else {
            data.put("Configuracoes.id", request.getData("Configuracoes.id"));
            request = new Request(data);
            isDone = controllerConfiguracoes.updateConfiguracao(request);
        }
        
        if (isDone) {
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso.");
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
                .addGap(0, 175, Short.MAX_VALUE))
        );
        jPanelTipoCriptLayout.setVerticalGroup(
            jPanelTipoCriptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTipoCriptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRadioButtonSSL)
                .addComponent(jRadioButtonTLS)
                .addComponent(jRadioButtonNenhum))
        );

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
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jButtonCancelar)))
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanelTipoCript;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JRadioButton jRadioButtonNenhum;
    private javax.swing.JRadioButton jRadioButtonSSL;
    private javax.swing.JRadioButton jRadioButtonTLS;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldPorta;
    private javax.swing.JTextField jTextFieldServidor;
    // End of variables declaration//GEN-END:variables
}
