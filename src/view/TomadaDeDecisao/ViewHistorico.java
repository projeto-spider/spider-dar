package view.TomadaDeDecisao;

import controller.ControllerHistorico;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import settings.Constant;
import settings.KeepData;
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
        jRadioButtonTodos.setSelected(true);
        Internal.retiraBorda(this);

        Date date = controllerHistorico.getStartProblema(Integer.parseInt(KeepData.getData("Problema.id")));
        dateFieldDe.setValue(date); 
    }

    public void reloadViewHistorico() {
        buttonGruop();
        String search = jTextFieldPesquisa.getText();
        Date date1 = (Date) dateFieldDe.getValue();
        Date date2 = (Date) dateFieldAte.getValue();
        listAlternativasInTable(controllerHistorico.listHistoricoBySearch(search, date1, date2, chosenTipo()));
    }

    private void buttonGruop() {
        buttonGroup.add(jRadioButtonTodos);
        buttonGroup.add(jRadioButtonTarefas);
        buttonGroup.add(jRadioButtonAlternativas);
        buttonGroup.add(jRadioButtonCriterios);
        buttonGroup.add(jRadioButtonAvaliacao);
        buttonGroup.add(jRadioButtonRelatorio);
        buttonGroup.add(jRadioButtonProblema);
    }

    private int chosenTipo() {
        if (jRadioButtonTodos.isSelected()) {
            return -1;
        } else if (jRadioButtonTarefas.isSelected()) {
            return Constant.FUC_TAREFAS;
        } else if (jRadioButtonAlternativas.isSelected()) {
            return Constant.FUC_ALTERNATIVAS;
        } else if (jRadioButtonCriterios.isSelected()) {
            return Constant.FUC_CRITERIOS;
        } else if (jRadioButtonAvaliacao.isSelected()) {
            return Constant.FUC_AVALIACAO;
        } else if (jRadioButtonRelatorio.isSelected()) {
            return Constant.FUC_RELATORIO;
        } else if (jRadioButtonProblema.isSelected()) {
            return Constant.FUC_PROBLEMA;
        } else {
            return -1;
        }
    }

    private ImageIcon getIcon(int type) {
        ImageIcon icon = null;
        switch (type) {
            case 0:
                icon = new ImageIcon(getClass().getResource("/resources/image/problema.png"));
                break;
            case 1:
                icon = new ImageIcon(getClass().getResource("/resources/image/goal.png"));
                break;
            case 2:
                icon = new ImageIcon(getClass().getResource("/resources/image/task.png"));
                break;
            case 3:
                icon = new ImageIcon(getClass().getResource("/resources/image/alternatives.png"));
                break;
            case 4:
                icon = new ImageIcon(getClass().getResource("/resources/image/criteria.png"));
                break;
            case 5:
                icon = new ImageIcon(getClass().getResource("/resources/image/evaluation.png"));
                break;
            case 7:
                icon = new ImageIcon(getClass().getResource("/resources/image/report.png"));
                break;
        }
        return icon;
    }

    private void listAlternativasInTable(List<Request> requestList) {
        String columns[] = {" ", "Alteração", "Feita por", "Data"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false, true, 2);

        for (Request request : requestList) {
            int type = Integer.parseInt(request.getData("Historico.tipo"));
            Object line[] = {
                getIcon(type),
                request.getData("Historico.descricao"),
                request.getData("Historico.usuarioNome"),
                request.getData("Historico.created")
            };

            myDefaultTableModel.addRow(line);
        }

        jTableHistorico.setModel(myDefaultTableModel);
        jTableHistorico.setDefaultRenderer(Object.class, new MyCellRenderer());
        jTableHistorico.getColumnModel().getColumn(0).setPreferredWidth(18);
        jTableHistorico.getColumnModel().getColumn(1).setPreferredWidth(410);
        jTableHistorico.getColumnModel().getColumn(2).setPreferredWidth(120);
        jTableHistorico.getColumnModel().getColumn(3).setPreferredWidth(95);

        jTableHistorico.setRowHeight(25);
        jTableHistorico.setGridColor(new Color(182, 182, 182));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistorico = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPesquisa = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jRadioButtonTodos = new javax.swing.JRadioButton();
        jRadioButtonTarefas = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jRadioButtonAlternativas = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jRadioButtonCriterios = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jRadioButtonAvaliacao = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jRadioButtonRelatorio = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jRadioButtonProblema = new javax.swing.JRadioButton();
        jLabelIconProblema = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dateFieldDe = new net.sf.nachocalendar.components.DateField();
        jLabel3 = new javax.swing.JLabel();
        dateFieldAte = new net.sf.nachocalendar.components.DateField();

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(31, 109, 165));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/spyglass.png"))); // NOI18N

        jTextFieldPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesquisaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jRadioButtonTodos.setText("Todos");
        jRadioButtonTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTodosActionPerformed(evt);
            }
        });

        jRadioButtonTarefas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTarefasActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/task.png"))); // NOI18N

        jRadioButtonAlternativas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAlternativasActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/alternatives.png"))); // NOI18N

        jRadioButtonCriterios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCriteriosActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/criteria.png"))); // NOI18N

        jRadioButtonAvaliacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAvaliacaoActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/evaluation.png"))); // NOI18N

        jRadioButtonRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonRelatorioActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/report.png"))); // NOI18N

        jRadioButtonProblema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonProblemaActionPerformed(evt);
            }
        });

        jLabelIconProblema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/problema.png"))); // NOI18N
        jLabelIconProblema.setLabelFor(jRadioButtonProblema);
        jLabelIconProblema.setToolTipText("Problema");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jRadioButtonTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonProblema)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelIconProblema)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonTarefas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonAlternativas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonCriterios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonAvaliacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonRelatorio)
                .addGap(0, 0, 0)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4)
                        .addComponent(jRadioButtonTarefas))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(jRadioButtonAlternativas))
                    .addComponent(jRadioButtonTodos)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jRadioButtonCriterios)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jRadioButtonAvaliacao)
                            .addComponent(jLabel6))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jRadioButtonRelatorio)
                            .addComponent(jLabel7)))
                    .addComponent(jRadioButtonProblema)
                    .addComponent(jLabelIconProblema))
                .addGap(6, 6, 6))
        );

        jLabel2.setText("De:");

        dateFieldDe.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dateFieldDeStateChanged(evt);
            }
        });

        jLabel3.setText("até:");

        dateFieldAte.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dateFieldAteStateChanged(evt);
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
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFieldDe, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateFieldAte, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jTextFieldPesquisa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateFieldDe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(dateFieldAte, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesquisaActionPerformed
        reloadViewHistorico();
    }//GEN-LAST:event_jTextFieldPesquisaActionPerformed

    private void dateFieldAteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateFieldAteStateChanged
        reloadViewHistorico();
    }//GEN-LAST:event_dateFieldAteStateChanged

    private void dateFieldDeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateFieldDeStateChanged
        reloadViewHistorico();
    }//GEN-LAST:event_dateFieldDeStateChanged

    private void jRadioButtonTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTodosActionPerformed
        reloadViewHistorico();
    }//GEN-LAST:event_jRadioButtonTodosActionPerformed

    private void jRadioButtonTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTarefasActionPerformed
        reloadViewHistorico();
    }//GEN-LAST:event_jRadioButtonTarefasActionPerformed

    private void jRadioButtonAlternativasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAlternativasActionPerformed
        reloadViewHistorico();
    }//GEN-LAST:event_jRadioButtonAlternativasActionPerformed

    private void jRadioButtonCriteriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCriteriosActionPerformed
        reloadViewHistorico();
    }//GEN-LAST:event_jRadioButtonCriteriosActionPerformed

    private void jRadioButtonAvaliacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAvaliacaoActionPerformed
        reloadViewHistorico();
    }//GEN-LAST:event_jRadioButtonAvaliacaoActionPerformed

    private void jRadioButtonRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonRelatorioActionPerformed
        reloadViewHistorico();
    }//GEN-LAST:event_jRadioButtonRelatorioActionPerformed

    private void jRadioButtonProblemaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButtonProblemaActionPerformed
    {//GEN-HEADEREND:event_jRadioButtonProblemaActionPerformed
        reloadViewHistorico();
    }//GEN-LAST:event_jRadioButtonProblemaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private net.sf.nachocalendar.components.DateField dateFieldAte;
    private net.sf.nachocalendar.components.DateField dateFieldDe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelIconProblema;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonAlternativas;
    private javax.swing.JRadioButton jRadioButtonAvaliacao;
    private javax.swing.JRadioButton jRadioButtonCriterios;
    private javax.swing.JRadioButton jRadioButtonProblema;
    private javax.swing.JRadioButton jRadioButtonRelatorio;
    private javax.swing.JRadioButton jRadioButtonTarefas;
    private javax.swing.JRadioButton jRadioButtonTodos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableHistorico;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldPesquisa;
    // End of variables declaration//GEN-END:variables
}
