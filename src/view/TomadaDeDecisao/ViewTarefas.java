package view.TomadaDeDecisao;

import controller.ControllerTarefas;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import settings.Constant;
import settings.CustomTableTarefa;
import settings.KeepData;
import util.Internal;
import util.MyDefaultTableModel;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewTarefas extends javax.swing.JInternalFrame {

    private final int type;
    private Request request;
    private MyDefaultTableModel myDefaultTableModel;
    private final ControllerTarefas controllerTarefas = new ControllerTarefas();

    public ViewTarefas() {
        initComponents();

        this.type = Constant.CREATE;
        Internal.retiraBorda(this);
    }

    public void showViewTarefas() {
        int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));
        fillTable(controllerTarefas.listTarefasByProjeto(idProblema));
    }

    private boolean isDone(String done) {
        return done.equals("true");
    }

    private String getMarcador(int valor) {
        String marcador = null;
        switch (valor) {
            case 1:
                marcador = "TRIVIAL";
                break;
            case 2:
                marcador = "PEQUENO";
                break;
            case 3:
                marcador = "MÉDIO";
                break;
            case 4:
                marcador = "GRANDE";
                break;
        }
        return marcador;
    }

    public void fillTable(List<Request> requestList) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/image/task.png"));

        String columns[] = {"id", " ", "Feito", "Tarefa", "Marcador", "Prazo"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false, true, 2);

        for (Request request : requestList) {
            Object line[] = {
                request.getData("Tarefa.id"),
                icon,
                isDone(request.getData("Tarefa.feito")),
                request.getData("Tarefa.nome"),
                getMarcador(Integer.parseInt(request.getData("Tarefa.marcador"))),
                request.getData("Tarefa.data")
            };
            myDefaultTableModel.addRow(line);
        }

        jTableTarefas.setModel(myDefaultTableModel);

        jTableTarefas.removeColumn(jTableTarefas.getColumnModel().getColumn(0));
        jTableTarefas.setDefaultRenderer(Object.class, new CustomTableTarefa());

        jTableTarefas.getColumnModel().getColumn(0).setPreferredWidth(1);
        jTableTarefas.getColumnModel().getColumn(1).setPreferredWidth(1);
        jTableTarefas.getColumnModel().getColumn(2).setPreferredWidth(460);
        jTableTarefas.getColumnModel().getColumn(3).setPreferredWidth(50);
        jTableTarefas.getColumnModel().getColumn(4).setPreferredWidth(95);

        jTableTarefas.setRowHeight(25);
        jTableTarefas.setGridColor(new Color(229, 229, 229));
    }

    private boolean rowIsSelected() {
        return jTableTarefas.getSelectedRow() > -1;
    }

    private void removeTarefa() {
        int Result = JOptionPane.showConfirmDialog(null, "Deseja Excluir esta Tarefa?", "EXCLUIR", JOptionPane.YES_NO_OPTION);

        if (Result == JOptionPane.YES_OPTION) {
            try {
                int idTarefa = Integer.parseInt(jTableTarefas.getModel()
                        .getValueAt(jTableTarefas.getSelectedRow(), 0).toString());

                controllerTarefas.removeTarefa(idTarefa);

                JOptionPane.showMessageDialog(null, "\"Tarefa\" foi excluída com sucesso.");
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, error.getMessage(), "ERRO AO EXCLUIR", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        taskDataModel = new net.sf.nachocalendar.tasks.TaskDataModel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTarefas = new javax.swing.JTable();
        jButtonNovo = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(246, 179, 111));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setText(" Tarefas");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTableTarefas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tarefa", "Data"
            }
        ));
        jScrollPane1.setViewportView(jTableTarefas);

        jButtonNovo.setText("Nova Tarefa");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jButton2.setText("Salvar alterações na tabela");

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jLabel1.setText("Pesquisa:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovo)
                    .addComponent(jButton2)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonEditar))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        new ViewTarefaNovo(null, true).setVisible(true);
        showViewTarefas();
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        if (!rowIsSelected()) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
            return;
        }

        int idATarefa = Integer.parseInt(jTableTarefas.getModel()
                .getValueAt(jTableTarefas.getSelectedRow(), 0).toString());

        new ViewTarefaNovo(null, true, idATarefa).setVisible(true);
        showViewTarefas();
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        if (!rowIsSelected()) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
            return;
        }

        removeTarefa();
        showViewTarefas();
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTarefas;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private net.sf.nachocalendar.tasks.TaskDataModel taskDataModel;
    // End of variables declaration//GEN-END:variables
}
