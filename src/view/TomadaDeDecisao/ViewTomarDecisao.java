package view.TomadaDeDecisao;

import controller.ControllerAlternativa;
import controller.ControllerDecisao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import settings.Constant;
import util.Request;
import util.swing.ComboItem;

/**
 *
 * @author Bleno Vale
 */
public class ViewTomarDecisao extends javax.swing.JDialog {

    private int type;
    private int idDecisao;
    private final ControllerAlternativa controllerAlternativa = new ControllerAlternativa();
    private final ControllerDecisao controllerDecisao = new ControllerDecisao();

    public ViewTomarDecisao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        fillCombobox();
        this.type = Constant.CREATE;
        this.setLocationRelativeTo(null);
    }

    public ViewTomarDecisao(java.awt.Frame parent, boolean modal, int idDecisao) {
        super(parent, modal);
        initComponents();

        fillCombobox();
        fillFields();
        this.type = Constant.UPDATE;
        this.idDecisao = idDecisao;
        this.setLocationRelativeTo(null);
    }

    private void fillCombobox() {
        jComboBoxalternativas.removeAllItems();
        jComboBoxalternativas.addItem(new ComboItem("", "--Selecione uma Alternativa--"));

        List<Request> requestList = controllerAlternativa.listAlternativasByProblema();

        for (Request request : requestList) {
            String id = request.getData("Alternativa.id");
            String nome = request.getData("Alternativa.nome");
            jComboBoxalternativas.addItem(new ComboItem(id, nome));
        }
    }

    private void fillFields() {
        Request request = controllerDecisao.findDecisao();

        String id = request.getData("Decisao.alternativa.id");
        String nome = request.getData("Decisao.alternativa");
        jComboBoxalternativas.setSelectedItem(new ComboItem(id, nome)); 
        jTextAreaJustificativa.setText(request.getData("Decisao.justificativa"));
    }

    private boolean validateFields() {
        ComboItem selectedAlternativa = (ComboItem) jComboBoxalternativas.getSelectedItem();
        if (selectedAlternativa.getLabel().equals("--Selecione uma Alternativa--")) {
            JOptionPane.showMessageDialog(null, "É necessario escolher uma \"Alternativa\".");
            return false;
        } else if (jTextAreaJustificativa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"justificativa\" não pode ser vazio.");
            return false;
        } else {
            return true;
        }

    }

    private void save() {
        if (!validateFields()) {
            return;
        }

        Map<String, String> data = new HashMap<>();
        ComboItem selectedAlternativa = (ComboItem) jComboBoxalternativas.getSelectedItem();
        data.put("Alternativa.id", selectedAlternativa.getValue());
        data.put("Decisao.justificativa", jTextAreaJustificativa.getText());

        try {
            if (type == Constant.CREATE) {
                controllerDecisao.saveDecisao(new Request(data));
            } else {
                data.put("Decisao.id", String.valueOf(idDecisao));
                controllerDecisao.updateDecisao(new Request(data));
            }

            JOptionPane.showMessageDialog(null, "\"Decisão\" foi salva com sucesso.");
            this.dispose();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "ERRO AO SALVAR", JOptionPane.ERROR_MESSAGE);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaJustificativa = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBoxalternativas = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tomada de Dcisão");
        setResizable(false);

        jLabel1.setText("Alternativa escolhida:");

        jLabel2.setText("Justificativa:");

        jTextAreaJustificativa.setColumns(20);
        jTextAreaJustificativa.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextAreaJustificativa.setLineWrap(true);
        jTextAreaJustificativa.setRows(5);
        jTextAreaJustificativa.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextAreaJustificativa);

        jButton1.setText("Cancelar");

        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBoxalternativas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBoxalternativas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jComboBoxalternativas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxalternativas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        save();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBoxalternativas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaJustificativa;
    // End of variables declaration//GEN-END:variables
}
