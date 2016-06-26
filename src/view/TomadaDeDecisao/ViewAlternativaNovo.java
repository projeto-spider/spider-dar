package view.TomadaDeDecisao;

import controller.ControllerAlternativa;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import settings.Constant;
import util.Input;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewAlternativaNovo extends javax.swing.JDialog {

    private final int type;
    private Request request;
    private int idAlternativa;
    private final ControllerAlternativa controllerAlternativa = new ControllerAlternativa();

    public ViewAlternativaNovo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.type = Constant.CREATE;

        this.setLocationRelativeTo(null);
    }

    public ViewAlternativaNovo(java.awt.Frame parent, boolean modal, int idAlternativa) {
        super(parent, modal);
        initComponents();

        this.type = Constant.UPDATE;
        this.idAlternativa = idAlternativa;
        fillFields(this.idAlternativa);

        this.setLocationRelativeTo(null);
    }

    private void fillFields(int IdAlternativa) {
        request = new Request();
        request = controllerAlternativa.getAlternativaSelected(IdAlternativa);

        jTextFieldNome.setText(request.getData("Alternativa.nome"));
        jTextAreaDescricao.setText(request.getData("Alternativa.descricao"));
        jTextFieldEstimativaCusto.setText(request.getData("Alternativa.estimativaCusto"));
        jTextFieldEstimativaTempo.setText(request.getData("Alternativa.estimativaTempo"));
        jTextAreaMetodos.setText(request.getData("Alternativa.metodos"));

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

    private void save() {
        request =  new Request();
        request.setHashMapValueToInput();

        request.setDataInput("Alternativa.nome", new Input(1, "text", "Nome da Alternativa", jTextFieldNome.getText()));
        request.setDataInput("Alternativa.descricao", new Input(1, "text", "Descrição", jTextAreaDescricao.getText()));
        request.setDataInput("Alternativa.estimativaCusto", new Input(1, "text", "Estimativa de Custo", jTextFieldEstimativaCusto.getText()));
        request.setDataInput("Alternativa.estimativaTempo", new Input(1, "text", "Estimativa de Tempo", jTextFieldEstimativaTempo.getText()));
         request.setDataInput("Alternativa.metodos", new Input(1, "text", "Metodos", jTextAreaMetodos.getText()));

        if (!isValidData(request)) {
            return;
        }

        try {
            if (type == Constant.CREATE) {
                controllerAlternativa.addAlternativa(request);
            } else {
                controllerAlternativa.upDatelternativa(request, idAlternativa); 
            }

            JOptionPane.showMessageDialog(null, "\"Alternativa\" foi salva com sucesso.");
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
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldEstimativaCusto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldEstimativaTempo = new javax.swing.JTextField();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaMetodos = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Alternativa de Solução");
        setResizable(false);

        jLabel1.setText("Nome da Alternativa:");

        jLabel3.setText("Descrição:");

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setLineWrap(true);
        jTextAreaDescricao.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescricao);

        jLabel4.setText("Estimativa de Custo:");

        jLabel5.setText("Estimativa de Tempo:");

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

        jLabel2.setText("Método de Avaliação:");

        jTextAreaMetodos.setColumns(20);
        jTextAreaMetodos.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jTextAreaMetodos.setLineWrap(true);
        jTextAreaMetodos.setRows(5);
        jTextAreaMetodos.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextAreaMetodos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldEstimativaCusto)
                            .addComponent(jTextFieldEstimativaTempo)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEstimativaCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldEstimativaTempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        save();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextArea jTextAreaMetodos;
    private javax.swing.JTextField jTextFieldEstimativaCusto;
    private javax.swing.JTextField jTextFieldEstimativaTempo;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
