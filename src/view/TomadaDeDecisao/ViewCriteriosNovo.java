package view.TomadaDeDecisao;

import controller.ControllerCriterios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import settings.Constant;
import settings.KeepData;
import util.Input;
import util.MyDefaultTableModel;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewCriteriosNovo extends javax.swing.JDialog {

    private final int type;
    private Request request;
    private int idCriterio;
    private final ControllerCriterios controllerCriterios = new ControllerCriterios();
    private MyDefaultTableModel myDefaultTableModel;

    public ViewCriteriosNovo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        initialiazeTable();
        this.type = Constant.CREATE;
        this.setLocationRelativeTo(null);
    }

    public ViewCriteriosNovo(java.awt.Frame parent, boolean modal, int idCriterio) {
        super(parent, modal);
        initComponents();

        this.type = Constant.UPDATE;
        this.idCriterio = idCriterio;
        fillFields();
        this.setLocationRelativeTo(null);
    }

    private void initialiazeTable() {
        String columns[] = {"Nota", "Valor"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false);

        jTableNotas.setModel(myDefaultTableModel);
    }

    private void fillTable() {
        initialiazeTable();

        List<Request> list = controllerCriterios.listNotasByCriterio(idCriterio);
        for (Request requestNota : list) {
            String line[] = {
                requestNota.getData("Nota.nome"),
                requestNota.getData("Nota.valor")
            };

            myDefaultTableModel.addRow(line);
            jTableNotas.setModel(myDefaultTableModel);
        }
    }

    private void fillFields() {
        request = new Request();
        request = controllerCriterios.getCriterioSelected(idCriterio);

        jTextFieldNome.setText(request.getData("Criterio.nome"));
        jTextFieldPeso.setText(request.getData("Criterio.peso"));
        jTextAreaJustificativa.setText(request.getData("Criterio.justificativa"));

        fillTable();
    }

    private boolean isValidData(Request request) {
        HashMap<String, Input> fields = request.getAllHashMapDataInputs();

        int fieldsSize = fields.size();

        for (int index = 1; index <= fieldsSize; index++) {
            for (Map.Entry<String, Input> entry : fields.entrySet()) {
                Input inputToValidate = entry.getValue();

                boolean isNotEqualsToHidden = !(inputToValidate.getTipo().equals("hidden"));

                if (index == inputToValidate.getOrdem() && isNotEqualsToHidden) {
                    if (inputToValidate.getValor().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo Obigatório: " + inputToValidate.getNome(), "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void putNotaInTable() {
        if (jTextFieldNota.getText().isEmpty()) {
            return;
        } else if (jTextFieldValor.getText().isEmpty()) {
            return;
        }

        String line[] = {
            jTextFieldNota.getText(),
            jTextFieldValor.getText()
        };

        myDefaultTableModel.addRow(line);
        jTableNotas.setModel(myDefaultTableModel);

    }

    private boolean rowIsSelected() {
        return jTableNotas.getSelectedRow() > -1;
    }

    private void removeNotaInTable() {
        if (!rowIsSelected()) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
            return;
        }
        int row = jTableNotas.getSelectedRow();
        myDefaultTableModel.removeRow(row);
        jTableNotas.setModel(myDefaultTableModel);

    }

    private boolean theresRowInTable() {
        return jTableNotas.getRowCount() != 0;
    }

    public void jTextFieldOnlyNumbers(java.awt.event.KeyEvent evt) {
        String caracteres = "9876543210";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }

    private void save() {
        request = new Request();
        request.setHashMapValueToInput();

        request.setDataInput("Criterio.nome", new Input(1, "text", "Nome do Critério", jTextFieldNome.getText()));
        request.setDataInput("Criterio.peso", new Input(1, "text", "Peso do Critério", jTextFieldPeso.getText()));
        request.setDataInput("Criterio.justificativa", new Input(1, "text", "Justificativa do peso", jTextAreaJustificativa.getText()));

        if (!isValidData(request)) {
            return;
        }

        if (!theresRowInTable()) {
            JOptionPane.showMessageDialog(null, "É necessário cadastrar notas para o Critério");
            return;
        }

        List<Request> list = new ArrayList<>();
        for (int i = 0; i < jTableNotas.getRowCount(); i++) {
            Map<String, String> data = new HashMap<>();

            String nome = jTableNotas.getValueAt(i, 0).toString();
            String valor = jTableNotas.getValueAt(i, 1).toString();

            data.put("Nota.nome", nome);
            data.put("Nota.valor", valor);
            list.add(new Request(data));

        }

        request.setDataInput("Problema.id", new Input(4, "text", "id do Problema", KeepData.getData("Problema.id")));

        try {
            if (type == Constant.CREATE) {
                controllerCriterios.addCriterio(request, list);
            } else {
                request.setDataInput("Criterio.id", new Input(5, "text", "id Critério", String.valueOf(idCriterio)));
                controllerCriterios.updateCriterio(request, list);
            }

            JOptionPane.showMessageDialog(null, "\"Critério\" foi salva com sucesso.");
            this.dispose();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "ERRO AO SALVAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaJustificativa = new javax.swing.JTextArea();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldNota = new javax.swing.JTextField();
        jTextFieldValor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonCriarNota = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableNotas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextFieldPeso = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Critério de Avaliação");
        setResizable(false);

        jLabel1.setText("Nome do Critério:");

        jLabel4.setText("Justificativa para o Peso:");

        jTextAreaJustificativa.setColumns(20);
        jTextAreaJustificativa.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextAreaJustificativa.setRows(3);
        jScrollPane1.setViewportView(jTextAreaJustificativa);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jTextFieldValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldValorActionPerformed(evt);
            }
        });
        jTextFieldValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldValorKeyTyped(evt);
            }
        });

        jLabel5.setText("Nota:");

        jLabel6.setText("Valor:");

        jButtonCriarNota.setText("Criar ");
        jButtonCriarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCriarNotaActionPerformed(evt);
            }
        });

        jTableNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Nota", "Valor"
            }
        ));
        jScrollPane3.setViewportView(jTableNotas);

        jButton1.setText("Remover");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNota))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonCriarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(3, 3, 3)
                            .addComponent(jTextFieldValor))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCriarNota)
                    .addComponent(jButton1))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("PESO:");

        jTextFieldPeso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldPeso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPeso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPesoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jTextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                            .addComponent(jTextFieldNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldValorActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        save();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonCriarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCriarNotaActionPerformed
        putNotaInTable();
    }//GEN-LAST:event_jButtonCriarNotaActionPerformed

    private void jTextFieldPesoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesoKeyTyped
        jTextFieldOnlyNumbers(evt);
    }//GEN-LAST:event_jTextFieldPesoKeyTyped

    private void jTextFieldValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorKeyTyped
        jTextFieldOnlyNumbers(evt);
    }//GEN-LAST:event_jTextFieldValorKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        removeNotaInTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCriarNota;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableNotas;
    private javax.swing.JTextArea jTextAreaJustificativa;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNota;
    private javax.swing.JTextField jTextFieldPeso;
    private javax.swing.JTextField jTextFieldValor;
    // End of variables declaration//GEN-END:variables
}
