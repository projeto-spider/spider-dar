package view.Gerenciar;

import controller.ControllerProblema;
import java.util.List;
import javax.swing.JOptionPane;
import settings.KeepData;
import util.Internal;
import util.MyDefaultTableModel;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewProblema extends javax.swing.JInternalFrame {

    private MyDefaultTableModel myDefaultTableModel;
    private final ControllerProblema controllerProblema = new ControllerProblema();
    
    
    public ViewProblema() {
        initComponents();

        Internal.retiraBorda(this);
    }
    
    public void listProblemasInTable(List<Request> requestList)
    {
        String columns[] = {"Código", "Nome do Problema", "Criado em", "Modificado em"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false);
        
        for (Request request: requestList)
        {
            String line[] = {request.getData("Problema.codigo"),
                                request.getData("Problema.nome"),
                                request.getData("Problema.created"),
                                request.getData("Problema.modified")};
            
            myDefaultTableModel.addRow(line);
        }
        jTableProblemas.setModel(myDefaultTableModel);
        jTableProblemas.setAutoCreateRowSorter(true);
        jTableProblemas.getRowSorter().toggleSortOrder(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProblemas = new javax.swing.JTable();
        jTextFieldProblemaPesquisa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButtonEditarProblema = new javax.swing.JButton();
        jButtonCadastrarProblema = new javax.swing.JButton();

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(246, 179, 111));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setText(" Problemas Cadastrados");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTableProblemas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Código", "Nome do Problema", "Criado em", "Modificado em"
            }
        ));
        jScrollPane1.setViewportView(jTableProblemas);

        jTextFieldProblemaPesquisa.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextFieldProblemaPesquisaActionPerformed(evt);
            }
        });

        jLabel1.setText("Pesquisar:");

        jButtonEditarProblema.setText("Editar");
        jButtonEditarProblema.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonEditarProblemaActionPerformed(evt);
            }
        });

        jButtonCadastrarProblema.setText("Novo");
        jButtonCadastrarProblema.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCadastrarProblemaActionPerformed(evt);
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
                        .addComponent(jTextFieldProblemaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonCadastrarProblema, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEditarProblema, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldProblemaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEditarProblema)
                    .addComponent(jButtonCadastrarProblema))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editarButtonIsPressed()
    {
        int index = jTableProblemas.getSelectedRow();
        if (index > -1) {
            new ViewNovoProblemaDialog(null, true, jTableProblemas.getValueAt(index, 0).toString()).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
        }
    }
    
    private void jButtonCadastrarProblemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarProblemaActionPerformed
        new ViewNovoProblemaDialog(null, true).setVisible(true);
        listProblemasInTable(new ControllerProblema().listProblemasByIdOrganizacao(KeepData.getData("Organizacao.id")));
    }//GEN-LAST:event_jButtonCadastrarProblemaActionPerformed

    private void jButtonEditarProblemaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEditarProblemaActionPerformed
    {//GEN-HEADEREND:event_jButtonEditarProblemaActionPerformed
        editarButtonIsPressed();
        listProblemasInTable(new ControllerProblema().listProblemasByIdOrganizacao(KeepData.getData("Organizacao.id")));
    }//GEN-LAST:event_jButtonEditarProblemaActionPerformed

    private void jTextFieldProblemaPesquisaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextFieldProblemaPesquisaActionPerformed
    {//GEN-HEADEREND:event_jTextFieldProblemaPesquisaActionPerformed
        String busca = jTextFieldProblemaPesquisa.getText();
        
        Request request = new Request();
        request.setData("Problema.busca", busca);
        request.setData("Problema.idOrganizacao", KeepData.getData("Organizacao.id"));
        
        listProblemasInTable(new ControllerProblema().listProblemasByPesquisa(request));
    }//GEN-LAST:event_jTextFieldProblemaPesquisaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadastrarProblema;
    private javax.swing.JButton jButtonEditarProblema;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProblemas;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldProblemaPesquisa;
    // End of variables declaration//GEN-END:variables
}
