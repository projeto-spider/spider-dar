package view.Gerenciar;

import controller.ControllerUsuario;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import util.Internal;
import util.MyDefaultTableModel;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewUsuarios extends javax.swing.JInternalFrame {

    private MyDefaultTableModel myDefaultTableModel;
    private final ControllerUsuario controllerUsuario = new ControllerUsuario();

    public ViewUsuarios() {
        initComponents();

        Internal.retiraBorda(this);
    }

    public void fillTable(List<Request> requestList) {
        String columns[] = {"Nome", "Login", "Qauntidades de problemas", "Criado em"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false);

        for (Request request : requestList) {
            String line[] = {
                request.getData("Usuario.nome"),
                request.getData("Usuario.login"),
                request.getData("Usuario.quantidadeProblemas"),
                request.getData("Usuario.created")
            };
            myDefaultTableModel.addRow(line);
        }
        jTableUsuarios.setModel(myDefaultTableModel);
    }

    private void removeUsuario(String name) {
        int Result = JOptionPane.showConfirmDialog(null, "Deseja Excluir este usuário?", "EXCLUIR", JOptionPane.YES_NO_OPTION);

        if (Result == JOptionPane.YES_OPTION) {
            boolean isDone = false;
            isDone = controllerUsuario.removeUsuario(name);
            if (isDone) {
                JOptionPane.showMessageDialog(null, "Excluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro inesperado, por favor tente novamente.", "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsuarios = new javax.swing.JTable();
        jTextFieldPesquica = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(246, 179, 111));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setText(" Usuários Cadastrados");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"ADM", "ADM", "0", "0"},
                {"Bleno", "bleno", "1", null}
            },
            new String [] {
                "Nome", "Login", "Quantidade  de projetos", "Criado em"
            }
        ));
        jScrollPane1.setViewportView(jTableUsuarios);

        jTextFieldPesquica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesquicaActionPerformed(evt);
            }
        });

        jLabel1.setText("Pesquisar:");

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Novo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Excluir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPesquica, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPesquica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new ViewNovoUsuario(null, true).setVisible(true);
        fillTable(controllerUsuario.findUsuarios());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldPesquicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesquicaActionPerformed
        String name = jTextFieldPesquica.getText();
        fillTable(controllerUsuario.findUsuarioBySearch(name));
    }//GEN-LAST:event_jTextFieldPesquicaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTableUsuarios.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
            return;
        }

        new ViewNovoUsuario(null, true, jTableUsuarios.getValueAt(jTableUsuarios.getSelectedRow(), 0).toString()).setVisible(true);
        fillTable(controllerUsuario.findUsuarios());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTableUsuarios.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
            return;
        }

        if (controllerUsuario.isADM(jTableUsuarios.getValueAt(jTableUsuarios.getSelectedRow(), 0).toString())) {
            JOptionPane.showMessageDialog(null, "O usuário Administrador não pode ser removido.");
            return;
        }
        String name = jTableUsuarios.getValueAt(jTableUsuarios.getSelectedRow(), 0).toString();
        removeUsuario(name);
        fillTable(controllerUsuario.findUsuarios());
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUsuarios;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldPesquica;
    // End of variables declaration//GEN-END:variables
}
