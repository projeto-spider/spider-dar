package view.TomadaDeDecisao;

import controller.ControllerAlternativa;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import util.Internal;
import util.MyCellRenderer;
import util.MyDefaultTableModel;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewAlternativaDeSolucao extends javax.swing.JInternalFrame {

    private MyDefaultTableModel myDefaultTableModel;
    private final ControllerAlternativa controllerAlternativa = new ControllerAlternativa();

    public ViewAlternativaDeSolucao() {
        initComponents();

        Internal.retiraBorda(this);
    }

    public void showViewAlternativaDeSolucao() {
        listAlternativasInTable(controllerAlternativa.listAlternativasByProblema());
    }

    private void listAlternativasInTable(List<Request> requestList) {
        String columns[] = {"id", "Alternativa", "Descrição", "Custo", "Tempo", "Criada em"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false);

        for (Request request : requestList) {
            String line[] = {
                request.getData("Alternativa.id"),
                request.getData("Alternativa.nome"),
                request.getData("Alternativa.descricao"),
                request.getData("Alternativa.estimativaCusto"),
                request.getData("Alternativa.estimativaTempo"),
                request.getData("Alternativa.created")
            };

            myDefaultTableModel.addRow(line);
        }

        jTableAlternativas.setModel(myDefaultTableModel);
        jTableAlternativas.removeColumn(jTableAlternativas.getColumnModel().getColumn(0));
        // transformar as celulas da tabela em textArea.
        jTableAlternativas.setDefaultRenderer(Object.class, new MyCellRenderer());

        jTableAlternativas.getColumnModel().getColumn(0).setPreferredWidth(230);
        jTableAlternativas.getColumnModel().getColumn(1).setPreferredWidth(430);
        jTableAlternativas.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTableAlternativas.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTableAlternativas.getColumnModel().getColumn(4).setPreferredWidth(140);
        
        jTableAlternativas.setGridColor(new Color(229,229,229)); 

    }

    private boolean rowIsSelected() {
        if (jTableAlternativas.getSelectedRow() > -1) {
            return true;
        } else {
            return false;
        }
    }

    private void removeAlternativa() {
        int Result = JOptionPane.showConfirmDialog(null, "Deseja Excluir esta Alternativa?", "EXCLUIR", JOptionPane.YES_NO_OPTION);

        if (Result == JOptionPane.YES_OPTION) {
            try {
                int idAltenativa = Integer.parseInt(jTableAlternativas.getModel()
                        .getValueAt(jTableAlternativas.getSelectedRow(), 0).toString());

                controllerAlternativa.deletAlternativa(idAltenativa);

                JOptionPane.showMessageDialog(null, "\"Alternativa\" foi excluída com sucesso.");
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, error.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlternativas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPesquisa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(246, 179, 111));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setText(" Alternativa de Solução");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTableAlternativas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Alternativa", "Estimativa de Custo", "Estimativa de tempo", "Criada em"
            }
        ));
        jTableAlternativas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTableAlternativas);

        jLabel1.setText("Pesquisar:");

        jTextFieldPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesquisaActionPerformed(evt);
            }
        });

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
                        .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new ViewAlternativaDeSolucaoNovo(null, true).setVisible(true);
        listAlternativasInTable(controllerAlternativa.listAlternativasByProblema());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!rowIsSelected()) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
            return;
        }
        int idAltenativa = Integer.parseInt(jTableAlternativas.getModel()
                .getValueAt(jTableAlternativas.getSelectedRow(), 0).toString());

        new ViewAlternativaDeSolucaoNovo(null, true, idAltenativa).setVisible(true);
        listAlternativasInTable(controllerAlternativa.listAlternativasByProblema());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!rowIsSelected()) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
            return;
        }

        removeAlternativa();
        listAlternativasInTable(controllerAlternativa.listAlternativasByProblema());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextFieldPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaActionPerformed
        String name = jTextFieldPesquisa.getText();
        listAlternativasInTable(controllerAlternativa.listAlternativaByNome(name)); 
    }//GEN-LAST:event_jTextFieldPesquisaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAlternativas;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldPesquisa;
    // End of variables declaration//GEN-END:variables
}
