package view.TomadaDeDecisao;

import controller.ControllerAlternativa;
import controller.ControllerAvaliacao;
import controller.ControllerCriterios;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import settings.KeepData;
import util.MyCellRenderer;
import util.MyDefaultTableModel;
import util.Request;
import util.swing.ComboItem;

/**
 *
 * @author Bleno Vale
 */
public class ViewAvaliar extends javax.swing.JDialog {

    private MyDefaultTableModel myDefaultTableModel;
    private final int idAlternativa;
    private final ControllerAlternativa controllerAlternativa = new ControllerAlternativa();
    private final ControllerCriterios controllerCriterios = new ControllerCriterios();
    private final ControllerAvaliacao controllerAvaliacao = new ControllerAvaliacao();

    public ViewAvaliar(java.awt.Frame parent, boolean modal, int idAlternativa) {
        super(parent, modal);
        initComponents();

        this.idAlternativa = idAlternativa;
        showInfos();
        this.setLocationRelativeTo(null);
    }

    private void showInfos() {
        String name = controllerAlternativa.getAlternativaSelected(idAlternativa).getData("Alternativa.nome");
        jLabelAlternativa.setText("<html>" + name + "</html>");
        initializeTable();
    }

    private void initializeTable() {
        myDefaultTableModel = new MyDefaultTableModel(new Object[]{"id", "Critério", "Nota"}, 0, false, false, 2);

        int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));
        List<Request> listCriterios = controllerCriterios.listCriteirosByProjeto(idProblema);

        for (Request request : listCriterios) {
            int idCriterio = Integer.parseInt(request.getData("Criterio.id"));
            Request avRequest = controllerAvaliacao.getAvaliar(idAlternativa, idCriterio);

            if (avRequest.getData("Criterio.id") != null) {
                Object[] line = {
                    avRequest.getData("Criterio.id"),
                    avRequest.getData("Criterio.nome"),
                    avRequest.getData("Avaliacao.nota")};
                myDefaultTableModel.addRow(line);
            } else {
                Object[] line = {
                    request.getData("Criterio.id"),
                    request.getData("Criterio.nome"),
                    "--Selecione uma nota--"};
                myDefaultTableModel.addRow(line);
            }

        }

        List<TableCellEditor> editors = new ArrayList<>();
        for (Request request : listCriterios) {
            int idCriterio = Integer.parseInt(request.getData("Criterio.id"));
            List<Request> listNotas = controllerCriterios.listNotasByCriterio(idCriterio);

            JComboBox comboBox = new JComboBox();
            comboBox.addItem(new ComboItem("", "--Selecione uma nota--"));
            for (Request nota : listNotas) {
                String nome = nota.getData("Nota.nome");
                String valor = nota.getData("Nota.valor");

                comboBox.addItem(new ComboItem(valor, nome));
            }

            DefaultCellEditor defaultCellEditor = new DefaultCellEditor(comboBox);
            editors.add(defaultCellEditor);

            jTable1 = new JTable(myDefaultTableModel) {
                @Override
                public TableCellEditor getCellEditor(int row, int column) {
                    int modelColumn = convertColumnIndexToModel(column);
                    if (modelColumn == 2) {
                        return editors.get(row);
                    } else {
                        return super.getCellEditor(row, column);
                    }
                }
            };

        }

        jTable1.removeColumn(jTable1.getColumnModel().getColumn(0));
        jTable1.setDefaultRenderer(Object.class, new MyCellRenderer(1, new Color(240, 240, 240)));
        jTable1.setRowHeight(25);
        jTable1.setTableHeader(null);
        jScrollPane1.setViewportView(jTable1);
    }

    private boolean validateCombobox() {
        boolean isOK = true;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (jTable1.getValueAt(i, 1).toString().equals("--Selecione uma nota--")) {
                isOK = false;
                break;
            }
        }

        return isOK;
    }

    private void save() {
        if (!validateCombobox()) {
            JOptionPane.showMessageDialog(null, "E necesário escolher uma nota para cada Critério.");
            return;
        }

        List<Request> saveList = new ArrayList<>();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            Map<String, String> data = new HashMap<>();

            data.put("Alternativa.id", String.valueOf(idAlternativa));

            String idCriterio = jTable1.getModel().getValueAt(i, 0).toString();
            data.put("Criterio.id", idCriterio);
            data.put("Criterio.nome", jTable1.getValueAt(i, 0).toString());

            data.put("Avaliar.nome", jTable1.getValueAt(i, 1).toString());

            saveList.add(new Request(data));
        }

        try {
            controllerAvaliacao.saveAvaliar(saveList);
            JOptionPane.showMessageDialog(null, "\"Avaliação\" foi salva com sucesso.");
            this.dispose();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "ERRO AO SALVAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelAlternativa = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Critérios", "Notas"
            }
        ));
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(31, 109, 165));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/alternatives.png"))); // NOI18N
        jLabel1.setText("Alternativa:");

        jLabelAlternativa.setBackground(new java.awt.Color(31, 109, 165));
        jLabelAlternativa.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAlternativa.setText("Nome Aqui");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAlternativa, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelAlternativa)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Critérios para a avaliação:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        save();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelAlternativa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
