package view.TomadaDeDecisao;

import controller.ControllerHistorico;
import java.util.List;
import util.Internal;
import util.MyCellRenderer;
import util.MyDefaultTableModel;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewHistorico extends javax.swing.JInternalFrame {

    private MyDefaultTableModel myDefaultTableModel;
    private final ControllerHistorico controllerHistorico = new ControllerHistorico();

    public ViewHistorico() {
        initComponents();

        Internal.retiraBorda(this);
    }

    public void showViewHistorico() {
        listAlternativasInTable(controllerHistorico.listHistoricoByProblema());
    }

    private void listAlternativasInTable(List<Request> requestList) {
        String columns[] = {"Alteração", "Feita por", "Data"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false);

        for (Request request : requestList) {
            String line[] = {
                request.getData("Historico.descricao"),
                request.getData("Historico.usuarioNome"),
                request.getData("Historico.created")
            };

            myDefaultTableModel.addRow(line);
        }

        jTableHistorico.setModel(myDefaultTableModel);
        jTableHistorico.setDefaultRenderer(Object.class, new MyCellRenderer(jTableHistorico));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistorico = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPesquisa = new javax.swing.JTextField();

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(246, 179, 111));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setText(" Histórico");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTableHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Alteração", "Feita por", "Data "
            }
        ));
        jScrollPane1.setViewportView(jTableHistorico);

        jLabel1.setText("Pesquisa:");

        jTextFieldPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesquisaActionPerformed(evt);
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
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaActionPerformed
        String descricao = jTextFieldPesquisa.getText();
        listAlternativasInTable(controllerHistorico.listHistoricoByPartDescricao(descricao));
    }//GEN-LAST:event_jTextFieldPesquisaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableHistorico;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldPesquisa;
    // End of variables declaration//GEN-END:variables
}
