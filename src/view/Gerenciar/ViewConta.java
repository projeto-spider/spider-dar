package view.Gerenciar;

import controller.ControllerUsuario;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import settings.KeepData;
import util.Criptografia;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewConta extends javax.swing.JDialog {

    private Request request;
    private final ControllerUsuario controllerUsuario = new ControllerUsuario();

    public ViewConta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        enableAlterarSenha();
        fillfields();
        this.setLocationRelativeTo(null);
    }

    private void enableAlterarSenha() {
        if (jCheckBoxAlterarSenha.isSelected()) {
            jPasswordFieldNovaSenha.setEnabled(true);
            jPasswordFieldConfirmaSenha.setEnabled(true);
        } else {
            jPasswordFieldNovaSenha.setEnabled(false);
            jPasswordFieldConfirmaSenha.setEnabled(false);
        }
    }

    private void fillfields() {
        request = controllerUsuario.findUsuarioById(Integer.parseInt(KeepData.getData("Usuario.id")));
        jTextFieldNome.setText(request.getData("Usuario.nome"));
        jTextFieldLogin.setText(request.getData("Usuario.login"));
        jTextFieldEmail.setText(request.getData("Usuario.email"));
    }

    private boolean fieldValidate() {
        if (jTextFieldNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Campo \"Nome Completo\" é obrigatório.");
            return false;

        } else if (jTextFieldLogin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Campo \"Login de Acesso\" é obrigatório.");
            return false;

        } else if (!controllerUsuario.validateEmail(jTextFieldEmail.getText())) {
            JOptionPane.showMessageDialog(null,
                    "Campo \"E-mail\" não é um endereço válido.");
            return false;

        } else if (!controllerUsuario.CompareSenhaTypedWithBD(
                request.getData("Usuario.senha"), new String(jPasswordFieldSenha.getPassword()))) {
            return false;

        } else if (jCheckBoxAlterarSenha.isSelected()) {
            if (jPasswordFieldNovaSenha.getPassword().length < 6) {
                JOptionPane.showMessageDialog(null,
                        "Campo \"Nova senha\" deve ter pelo menos 6 caracteres.");
                return false;

            } else if (!Arrays.equals(jPasswordFieldNovaSenha.getPassword(), jPasswordFieldConfirmaSenha.getPassword())) {
                JOptionPane.showMessageDialog(null,
                        "Campos \"Nova senha\"  e \"Confirma senha\" não correspondem.");
                return false;

            }
        }
        return true;
    }

    private void save() {
        Map<String, String> data = new HashMap<>();

        data.put("Usuario.nome", jTextFieldNome.getText());
        data.put("Usuario.login", jTextFieldLogin.getText());
        data.put("Usuario.email", jTextFieldEmail.getText());
        data.put("Usuario.id", KeepData.getData("Usuario.id")); 
        if (jCheckBoxAlterarSenha.isSelected()) {
            String newSenha = new String(jPasswordFieldNovaSenha.getPassword());
            data.put("Usuario.senha", new Criptografia().encryptMessage(newSenha));
        } else {
            data.put("Usuario.senha", "");
        }

        request = new Request(data);
        boolean isDone = controllerUsuario.updateUsuario(request);
        if (isDone) {
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Erro ao salvar.",
                    "ERRO AO SALVAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldLogin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jCheckBoxAlterarSenha = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPasswordFieldSenha = new javax.swing.JPasswordField();
        jPasswordFieldNovaSenha = new javax.swing.JPasswordField();
        jPasswordFieldConfirmaSenha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conta");
        setResizable(false);

        jLabel1.setText("Nome completo:");

        jTextFieldNome.setEditable(false);

        jLabel2.setText("Login:");

        jLabel3.setText("E-mail:");

        jLabel4.setText("Senha:");

        jCheckBoxAlterarSenha.setText("Alterar senha");
        jCheckBoxAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAlterarSenhaActionPerformed(evt);
            }
        });

        jLabel5.setText("Nova senha:");

        jLabel6.setText("Confirmar senha:");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldLogin)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPasswordFieldNovaSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPasswordFieldConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jCheckBoxAlterarSenha)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jPasswordFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxAlterarSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordFieldNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jPasswordFieldConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAlterarSenhaActionPerformed
        enableAlterarSenha();
    }//GEN-LAST:event_jCheckBoxAlterarSenhaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!fieldValidate()) {
            return;
        }

        save();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBoxAlterarSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordFieldConfirmaSenha;
    private javax.swing.JPasswordField jPasswordFieldNovaSenha;
    private javax.swing.JPasswordField jPasswordFieldSenha;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
