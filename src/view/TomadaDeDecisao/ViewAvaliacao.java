package view.TomadaDeDecisao;

import controller.ControllerAlternativa;
import controller.ControllerAvaliacao;
import controller.ControllerCriterios;
import controller.ControllerDecisao;
import controller.ControllerProblema;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import settings.KeepData;
import util.Internal;
import util.MyCellRenderer;
import util.MyDefaultTableModel;
import util.Request;
import util.swing.MyTable;

/**
 *
 * @author Bleno Vale
 */
public class ViewAvaliacao extends javax.swing.JInternalFrame {

    private MyDefaultTableModel myDefaultTableModel;
    private final ControllerAlternativa controllerAlternativa = new ControllerAlternativa();
    private final ControllerAvaliacao controllerAvaliacao = new ControllerAvaliacao();
    private final ControllerDecisao controllerDecisao = new ControllerDecisao();
    private int idDecisao = -1;

    public ViewAvaliacao() {
        initComponents();

        Internal.retiraBorda(this);
    }

    public void showAvaliacao() {
        initializeTable();
        fillFields();
    }

    private void initializeTable() {
        myDefaultTableModel = new MyDefaultTableModel(new Object[]{"id", "Alternativas", "Satisfação", "Ranking", "Avaliar"}, 0, false, false, 3);
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/image/task.png"));

        List<Request> list = controllerAvaliacao.getRequestListFromAlternativa();

        for (Request request : list) {
            Object[] line = {
                request.getData("Avaliacao.alternativa.id"),
                request.getData("Avaliacao.alternativa.nome"),
                request.getData("Avaliacao.satisfacao"),
                request.getData("Avaliacao.posicao")};

            myDefaultTableModel.addRow(line);
        }

        jTableAvaliacao.setModel(myDefaultTableModel);

        new MyTable(jTableAvaliacao).putButton();

        jTableAvaliacao.removeColumn(jTableAvaliacao.getColumnModel().getColumn(0));
        jTableAvaliacao.getColumnModel().getColumn(0).setPreferredWidth(430);
        jTableAvaliacao.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableAvaliacao.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTableAvaliacao.getColumnModel().getColumn(3).setPreferredWidth(40);
        jTableAvaliacao.setDefaultRenderer(Object.class, new MyCellRenderer(2, new Color(189, 189, 240)));

        jTableAvaliacao.setRowHeight(25);
        jTableAvaliacao.setGridColor(new Color(182, 182, 182));

    }

    private void fillFields() {
        Request request = controllerDecisao.findDecisao();
        if (request.getData("Decisao.id") != null) {
            idDecisao = Integer.parseInt(request.getData("Decisao.id"));
            jLabelNome.setText(request.getData("Decisao.alternativa"));
            jLabelJustificativa.setText("<html>" + request.getData("Decisao.justificativa") + "</html>");

            if ("1".equals(request.getData("Decisao.definitivo"))) {
                jCheckBox1.setEnabled(false);
                jCheckBox1.setSelected(true);
                jButton2.setEnabled(false);
            }

        } else {
            jLabelNome.setText("");
            jLabelJustificativa.setText("");
            jButton2.setEnabled(true);
            jCheckBox1.setEnabled(true);
        }
    }

    private void saveDecisaoDefinitiva() {

        if (idDecisao == -1) {
            JOptionPane.showMessageDialog(null, "A decisão ainda não foi tomada.");
            jCheckBox1.setSelected(false);
        } else {
            try {
                controllerDecisao.SaveDecisaoDefinitiva(idDecisao);
                ControllerProblema controllerProblema = new ControllerProblema();
                controllerProblema.saveStatusConcluido();
                showAvaliacao();
            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, error.getMessage(), "ERRO AO SALVAR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAvaliacao = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelNome = new javax.swing.JLabel();
        jLabelJustificativa = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(31, 109, 165));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("  Avaliação");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTableAvaliacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Alternativas", "Satifação (%)", "Raking", "Avaliar"
            }
        ));
        jTableAvaliacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAvaliacaoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableAvaliacaoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAvaliacao);

        jButton2.setText("Tomar Decisão");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(31, 109, 165));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Decisão");

        jLabelNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNome.setForeground(new java.awt.Color(255, 255, 255));

        jLabelJustificativa.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelJustificativa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelJustificativa, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jCheckBox1.setText("Decisão definitiva");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (idDecisao == -1) {
            new ViewTomarDecisao(null, true).setVisible(true);
        } else {
            new ViewTomarDecisao(null, true, idDecisao).setVisible(true);
        }

        showAvaliacao();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTableAvaliacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAvaliacaoMouseClicked

    }//GEN-LAST:event_jTableAvaliacaoMouseClicked

    private void jTableAvaliacaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAvaliacaoMousePressed
        int column = jTableAvaliacao.columnAtPoint(evt.getPoint());

        if (column == 3) {
            int id = Integer.parseInt(KeepData.getData("Problema.id"));
            if (new ControllerCriterios().listCriteirosByProjeto(id).isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há Critérios cadastrados para realizar a avaliação.");
            } else {
                int idAltenativa = Integer.parseInt(jTableAvaliacao.getModel()
                        .getValueAt(jTableAvaliacao.getSelectedRow(), 0).toString());
                new ViewAvaliar(null, true, idAltenativa).setVisible(true);
                showAvaliacao();
            }
        }
    }//GEN-LAST:event_jTableAvaliacaoMousePressed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        saveDecisaoDefinitiva();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelJustificativa;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableAvaliacao;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

//    private void initializeTable() {
//        model = new MyDefaultTableModel(new Object[]{"Alternativas", "Critério A", "Critério B", "Critério C", 
//            "<html>Porcentagem<br>  (%)</html>", "Ranking"}, 0, false);
//
//        jTableAvaliacao = new JTable(model) {
//            @Override
//            protected JTableHeader createDefaultTableHeader() {
//                return new GroupableTableHeader(columnModel);
//            }
//        };
//        
//        TableColumnModel cm = jTableAvaliacao.getColumnModel();       
//        ColumnGroup subColumn = new ColumnGroup("Critérios");
//        subColumn.add(cm.getColumn(1));
//        subColumn.add(cm.getColumn(2));
//        subColumn.add(cm.getColumn(3)); 
//
//        GroupableTableHeader header = (GroupableTableHeader) jTableAvaliacao.getTableHeader();
//        header.addColumnGroup(subColumn);
//
//        jScrollPane1.setViewportView(jTableAvaliacao);
//        jTableAvaliacao.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//    }
//
//    private void putRow(){
//        Object[] line =  {" 1111111111111111111111111111 "," 1 "," 1 "," 1 "," 1 "," 1 "," 1 "," 1 "," 1 "," 1 "," 1 "," 1 ",};
//        model.addRow(line);
//        jTableAvaliacao.setModel(model); 
//    }
//    
}
