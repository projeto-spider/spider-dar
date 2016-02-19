package view.TomadaDeDecisao;

import java.awt.Color;
import javax.swing.ImageIcon;
import settings.Constant;
import util.Internal;
import util.MyCellRenderer;
import util.MyDefaultTableModel;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewTarefas extends javax.swing.JInternalFrame {

    private MyDefaultTableModel myDefaultTableModel;
    private final int type;
    private Request request;
    private int idTarefa;

    public ViewTarefas() {
        initComponents();

        this.type = Constant.CREATE;
        fillTable();
        Internal.retiraBorda(this);
    }

    public void fillTable() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/image/task.png"));
        String columns[] = {" -", "Feito", "Tarefa", "Data"};
        Object[] line = {icon, false, "teste", "teste"};

        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false, true, 1);
        myDefaultTableModel.addRow(line);
        jTableTarefas.setModel(myDefaultTableModel);
        jTableTarefas.setDefaultRenderer(Object.class, new MyCellRenderer());
        jTableTarefas.setRowHeight(25);
        
        jTableTarefas.setGridColor(new Color(229,229,229)); 

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        taskDataModel = new net.sf.nachocalendar.tasks.TaskDataModel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTarefas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(246, 179, 111));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setText(" Calendário");
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

        jButton1.setText("Nova Tarefa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salvar alterações na tabela");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ViewTarefaNovo(null, true).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTarefas;
    private javax.swing.JTextField jTextField1;
    private net.sf.nachocalendar.tasks.TaskDataModel taskDataModel;
    // End of variables declaration//GEN-END:variables
}
