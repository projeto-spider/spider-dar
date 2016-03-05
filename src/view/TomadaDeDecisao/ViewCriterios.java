package view.TomadaDeDecisao;

import controller.ControllerCriterios;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import settings.CustomTableTarefa;
import settings.KeepData;
import util.Internal;
import util.MyCellRenderer;
import util.MyDefaultTableModel;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewCriterios extends javax.swing.JInternalFrame {

    private Request request;
    private MyDefaultTableModel myDefaultTableModel;
    private final ControllerCriterios controllerCriterios = new ControllerCriterios();

    public ViewCriterios() {
        initComponents();

        Internal.retiraBorda(this);
    }

    public void reloadViewTarefas() {
        String partName = jTextFieldPesquisa.getText();
        int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));
        fillTable(controllerCriterios.listCriteirosBySearch(partName, idProblema));
    }

    public void fillTable(List<Request> requestList) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/image/criteria.png"));

        String columns[] = {"id", " ", "Critério", "Peso", "Porcentagem", "Criada em"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false, true, 2);

        for (Request request : requestList) {
            Object line[] = {
                request.getData("Criterio.id"),
                icon,
                request.getData("Criterio.nome"),
                request.getData("Criterio.peso"),
                request.getData("Criterio.porcentagem") + "%",
                request.getData("Criterio.created")
            };
            myDefaultTableModel.addRow(line);
        }

        jTableCriterio.setModel(myDefaultTableModel);

        jTableCriterio.removeColumn(jTableCriterio.getColumnModel().getColumn(0));
        jTableCriterio.setDefaultRenderer(Object.class, new MyCellRenderer(3));

        jTableCriterio.getColumnModel().getColumn(0).setPreferredWidth(1);
        jTableCriterio.getColumnModel().getColumn(1).setPreferredWidth(350);
        jTableCriterio.getColumnModel().getColumn(2).setPreferredWidth(95);
        jTableCriterio.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTableCriterio.getColumnModel().getColumn(4).setPreferredWidth(80);

        jTableCriterio.setRowHeight(25);
        jTableCriterio.setGridColor(new Color(182, 182, 182));
    }

    private boolean rowIsSelected() {
        return jTableCriterio.getSelectedRow() > -1;
    }

    private void removeCriterio(){
        int Result = JOptionPane.showConfirmDialog(null, "Deseja Excluir este Critério?", "EXCLUIR", JOptionPane.YES_NO_OPTION);
        if (Result == JOptionPane.YES_OPTION) {
            try {
                int idCriterio = Integer.parseInt(jTableCriterio.getModel()
                        .getValueAt(jTableCriterio.getSelectedRow(), 0).toString()); 

                controllerCriterios.removeCriterio(idCriterio);

                JOptionPane.showMessageDialog(null, "\"Critério\" foi excluída com sucesso.");
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, error.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCriterio = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(31, 109, 165));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText(" Critérios de avaliação");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/spyglass.png"))); // NOI18N

        jTextFieldPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesquisaActionPerformed(evt);
            }
        });

        jTableCriterio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "#", "Critério", "Criado em", "Pesos"
            }
        ));
        jScrollPane1.setViewportView(jTableCriterio);

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
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new ViewCriteriosNovo(null, true).setVisible(true);
        reloadViewTarefas();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!rowIsSelected()) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
            return;
        }
        
        int idACriterio = Integer.parseInt(jTableCriterio.getModel()
                .getValueAt(jTableCriterio.getSelectedRow(), 0).toString());
        
        new ViewCriteriosNovo(null, true, idACriterio).setVisible(true);
        reloadViewTarefas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaActionPerformed
        reloadViewTarefas();
    }//GEN-LAST:event_jTextFieldPesquisaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!rowIsSelected()) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
            return;
        }
        
        removeCriterio();
        reloadViewTarefas();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCriterio;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldPesquisa;
    // End of variables declaration//GEN-END:variables
}
