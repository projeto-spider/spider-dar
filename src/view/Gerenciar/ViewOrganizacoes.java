package view.Gerenciar;

import controller.ControllerOrganizacao;
import java.util.List;
import javax.swing.JOptionPane;
import util.Internal;
import util.MyDefaultTableModel;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewOrganizacoes extends javax.swing.JInternalFrame {

    private MyDefaultTableModel myDefaultTableModel;
    private final ControllerOrganizacao controllerOrganizacao = new ControllerOrganizacao();

    public ViewOrganizacoes() {
        initComponents();

        Internal.retiraBorda(this);
    }

    /**
     * Preenche a tabela com as Organizações cadastradas anteriormente.
     *
     * @param requestList
     */
    public void fillTable(List<Request> requestList) {
        String columns[] = {"teste", "Organização", "Criada em", "Última modificação"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false);

        for (Request request : requestList) {
            String line[] = {
                "teste",
                request.getData("Organizacao.nome"),
                request.getData("Organizacao.created"),
                request.getData("Organizacao.modified")
            };
            myDefaultTableModel.addRow(line);
        }
        jTableOrganizacoes.setModel(myDefaultTableModel);
        jTableOrganizacoes.removeColumn(jTableOrganizacoes.getColumnModel().getColumn(0));
    }

    /**
     * Chamado quando o botão editar e pressionado.
     */
    private void editarButtonIsPressed() {
        int index = jTableOrganizacoes.getSelectedRow();
        if (index > -1) {
            new ViewNovaOrganizacao(null, true, jTableOrganizacoes.getValueAt(index, 0).toString()).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
        }
    }

    private void removeOrganizacao() {
        int Result = JOptionPane.showConfirmDialog(null, "Deseja Excluir esta organização?", "EXCLUIR", JOptionPane.YES_NO_OPTION);

        if (Result == JOptionPane.YES_OPTION) {
            boolean isDone = false;
            int index = jTableOrganizacoes.getSelectedRow();
            isDone = controllerOrganizacao.deleteOrganizacao(jTableOrganizacoes.getValueAt(index, 0).toString());
            if (isDone) {
                JOptionPane.showMessageDialog(null, "Excluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Para deletar uma organização é necessário"
                        + "\nque esta não esteja vinculada a \"Problemas\" e \"Perfis\".");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOrganizacoes = new javax.swing.JTable();
        jTextFieldPesquisa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(246, 179, 111));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setText(" Organizações Cadastradas");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTableOrganizacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Desenvolvedores", "24/10/2015"},
                {"Alta ADM", "24/10/2015"}
            },
            new String [] {
                "Organização", "Criada em"
            }
        ));
        jTableOrganizacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableOrganizacoesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableOrganizacoes);

        jTextFieldPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesquisaActionPerformed(evt);
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
                        .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        new ViewNovaOrganizacao(null, true).setVisible(true);
        fillTable(controllerOrganizacao.findOrganizacoes());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        editarButtonIsPressed();
        fillTable(controllerOrganizacao.findOrganizacoes());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTableOrganizacoes.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
            return;
        }
        removeOrganizacao();
        fillTable(controllerOrganizacao.findOrganizacoes());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextFieldPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaActionPerformed
        String name = jTextFieldPesquisa.getText();
        fillTable(controllerOrganizacao.findOrganizacoesBySearch(name));
    }//GEN-LAST:event_jTextFieldPesquisaActionPerformed

    private void jTableOrganizacoesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOrganizacoesMousePressed
        System.out.println(">>Valor da coluna escontida: "
                + jTableOrganizacoes.getModel().getValueAt(jTableOrganizacoes.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_jTableOrganizacoesMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableOrganizacoes;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldPesquisa;
    // End of variables declaration//GEN-END:variables
}
