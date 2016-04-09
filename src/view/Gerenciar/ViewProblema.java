package view.Gerenciar;

import controller.ControllerProblema;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import settings.KeepData;
import util.Internal;
import util.MyCellRenderer;
import util.MyDefaultTableModel;
import util.Request;
import static javax.swing.JOptionPane.*;
import settings.Constant;

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
        String columns[] = {"id","Status"," ", "Nome do Problema", "Criado em", "Modificado em"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false,true,2);
        
        ImageIcon iconAtivo = new ImageIcon(getClass().getResource("/resources/image/problema.png"));
        ImageIcon iconInativo = new ImageIcon(getClass().getResource("/resources/image/problema_inativo.png"));
        
        for (Request request: requestList)
        {
            String statusProblema = request.getData("Problema.status");
            boolean isProblemaAtivo = (Integer.parseInt(statusProblema) == Constant.PROBLEMA_ATIVO);
            
            Object line[] = {request.getData("Problema.id"),
                                statusProblema,
                                (isProblemaAtivo) ? iconAtivo : iconInativo,
                                (isProblemaAtivo) ? request.getData("Problema.nome"): "(Inativo) " + request.getData("Problema.nome"),
                                request.getData("Problema.created"),
                                request.getData("Problema.modified")};
            
            myDefaultTableModel.addRow(line);
        }
        jTableProblemas.setModel(myDefaultTableModel);
        jTableProblemas.getTableHeader().setReorderingAllowed(false);
        jTableProblemas.removeColumn(jTableProblemas.getColumnModel().getColumn(0));
        jTableProblemas.removeColumn(jTableProblemas.getColumnModel().getColumn(0));
        jTableProblemas.setDefaultRenderer(Object.class, new MyCellRenderer());
        
        jTableProblemas.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTableProblemas.getColumnModel().getColumn(1).setPreferredWidth(430);
        jTableProblemas.getColumnModel().getColumn(2).setPreferredWidth(140);
        jTableProblemas.getColumnModel().getColumn(3).setPreferredWidth(140);
        jTableProblemas.setRowHeight(25);
    }

    private void editarButtonIsPressed()
    {
        int index = jTableProblemas.getSelectedRow();
        
        if (index > -1)
        {
            String idProblema = getIdProblemaFromTable(index);
            int statusProblema = controllerProblema.getStatusProblema(idProblema);
            
            if (statusProblema == Constant.PROBLEMA_INATIVO)
            {
                showMessageDialog(null, "<html>Não é possível editar o problema, pois está <b>inativo</b>.</html>");
            }
            else if (statusProblema == Constant.PROBLEMA_FINALIZADO)
            {
                showMessageDialog(null, "<html>Não é possível editar o problema, pois foi <b>Finalizado</b>.</html>");
            }
            else
                new ViewNovoProblema(null, true, idProblema).setVisible(true);
            
        }
        else
            showMessageDialog(null, "Selecione uma linha na tabela.");
    }
    
    private void inativarProblemaPressed()
    {
        int index = jTableProblemas.getSelectedRow();
        
        if (index > -1)
        {
            try
            {
                String buttonActivation = jButtonInativarProblema.getText();
                String idProblema = this.getIdProblemaFromTable(index);
                String idOrganizacao = KeepData.getData("Organizacao.id");
                String message = "";
                if (buttonActivation.equals("Inativar"))
                {
                    controllerProblema.inativarProblemaById(idProblema);
                    message = "<html>Problema <b>Inativado</b> com sucesso.</html>";
                }
                else if (buttonActivation.equals("Ativar"))
                {
                    controllerProblema.ativarProblemaById(idProblema);
                    message = "<html>Problema <b>Reativado</b> com sucesso.</html>";
                }
                
                showMessageDialog(null, message);
                listProblemasInTable(new ControllerProblema().listProblemasByIdOrganizacao(idOrganizacao));
            }
            catch (Exception e)
            {
                showMessageDialog(null, e.getMessage(),"ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
    }
    
    private String getIdProblemaFromTable(int index)
    {
        return jTableProblemas.getModel().getValueAt(index, 0).toString();
    }
    
    private void changeButtonAction()
    {
        int index = jTableProblemas.getSelectedRow();
        
        if (index > -1)
        {
            int status = Integer.parseInt(jTableProblemas.getModel().getValueAt(index, 1).toString());
            
            if (status == Constant.PROBLEMA_INATIVO)
                jButtonInativarProblema.setText("Ativar");
            else if(status == Constant.PROBLEMA_ATIVO)
                jButtonInativarProblema.setText("Inativar");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProblemas = new javax.swing.JTable();
        jTextFieldPesquisarProblema = new javax.swing.JTextField();
        jButtonEditarProblema = new javax.swing.JButton();
        jButtonCadastrarProblema = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButtonInativarProblema = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(797, 539));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(31, 109, 165));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
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
        jTableProblemas.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                jTableProblemasMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableProblemas);

        jTextFieldPesquisarProblema.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextFieldPesquisarProblemaActionPerformed(evt);
            }
        });

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/spyglass.png"))); // NOI18N

        jButtonInativarProblema.setText("Inativar");
        jButtonInativarProblema.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonInativarProblemaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonCadastrarProblema, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEditarProblema, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonInativarProblema))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPesquisarProblema, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 458, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPesquisarProblema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEditarProblema)
                    .addComponent(jButtonCadastrarProblema)
                    .addComponent(jButtonInativarProblema))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButtonCadastrarProblemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarProblemaActionPerformed
        new ViewNovoProblema(null, true).setVisible(true);
        listProblemasInTable(new ControllerProblema().listProblemasByIdOrganizacao(KeepData.getData("Organizacao.id")));
    }//GEN-LAST:event_jButtonCadastrarProblemaActionPerformed

    private void jButtonEditarProblemaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonEditarProblemaActionPerformed
    {//GEN-HEADEREND:event_jButtonEditarProblemaActionPerformed
        editarButtonIsPressed();
        listProblemasInTable(new ControllerProblema().listProblemasByIdOrganizacao(KeepData.getData("Organizacao.id")));
    }//GEN-LAST:event_jButtonEditarProblemaActionPerformed

    private void jTextFieldPesquisarProblemaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextFieldPesquisarProblemaActionPerformed
    {//GEN-HEADEREND:event_jTextFieldPesquisarProblemaActionPerformed
        String busca = jTextFieldPesquisarProblema.getText();
        
        Request request = new Request();
        request.setData("Problema.busca", busca);
        request.setData("Problema.idOrganizacao", KeepData.getData("Organizacao.id"));
        
        listProblemasInTable(new ControllerProblema().listProblemasByPesquisa(request));
    }//GEN-LAST:event_jTextFieldPesquisarProblemaActionPerformed

    private void jButtonInativarProblemaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonInativarProblemaActionPerformed
    {//GEN-HEADEREND:event_jButtonInativarProblemaActionPerformed
        inativarProblemaPressed();
    }//GEN-LAST:event_jButtonInativarProblemaActionPerformed

    private void jTableProblemasMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jTableProblemasMouseReleased
    {//GEN-HEADEREND:event_jTableProblemasMouseReleased
        changeButtonAction();
    }//GEN-LAST:event_jTableProblemasMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCadastrarProblema;
    private javax.swing.JButton jButtonEditarProblema;
    private javax.swing.JButton jButtonInativarProblema;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProblemas;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldPesquisarProblema;
    // End of variables declaration//GEN-END:variables
}
