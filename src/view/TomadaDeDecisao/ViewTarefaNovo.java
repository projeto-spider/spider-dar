package view.TomadaDeDecisao;

import controller.ControllerTarefas;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import settings.Constant;
import settings.KeepData;
import util.Input;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewTarefaNovo extends javax.swing.JDialog {

    private final int type;
    private Request request;
    private int idTarefa;
    private final ControllerTarefas controllerTarefas = new ControllerTarefas();

    public ViewTarefaNovo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        buttonGruop();
        jRadioButton1.setSelected(true); 
        this.type = Constant.CREATE;
        this.setLocationRelativeTo(null);
    }

    public ViewTarefaNovo(java.awt.Frame parent, boolean modal, int idTarefa) {
        super(parent, modal);
        initComponents();
        
        buttonGruop();
        this.type = Constant.UPDATE;
        this.idTarefa = idTarefa;
        fillFields();
        this.setLocationRelativeTo(null);
    }
    
    private void buttonGruop() {
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        buttonGroup.add(jRadioButton3);
        buttonGroup.add(jRadioButton4);
    }
    
    private void fillFields(){
        request = new Request();
        request = controllerTarefas.getTarefasSelected(idTarefa);
        
        jTextFieldNome.setText(request.getData("Tarefa.nome")); 
        jTextAreaDescricao.setText(request.getData("Tarefa.descricao")); 
        selectedJRadioButton(Integer.parseInt(request.getData("Tarefa.marcador"))); 
        dateFieldData.setValue(controllerTarefas.getPrazoTarefaSelected(idTarefa)); 
    }
    
    private void selectedJRadioButton(int mardador){
        if (mardador ==Constant.TRIVIAL){
            jRadioButton1.setSelected(true); 
        } else if (mardador ==Constant.PEQUENO){
            jRadioButton2.setSelected(true); 
        } else if (mardador == Constant.MEDIO){
            jRadioButton3.setSelected(true); 
        }  else if (mardador == Constant.GRANDE){
            jRadioButton4.setSelected(true); 
        }
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

    private boolean isValidDate() {
        Date chosenDate = (Date) dateFieldData.getValue();
        return controllerTarefas.isValidDate(chosenDate);
    }
    
    private int getRadioButtonSelected(){
        int marcador = 1;
        if (jRadioButton1.isSelected()){
            marcador = Constant.TRIVIAL;
        } else if (jRadioButton2.isSelected()){
            marcador = Constant.PEQUENO;
        } else if (jRadioButton3.isSelected()){
            marcador = Constant.MEDIO;
        }  else if (jRadioButton4.isSelected()){
            marcador = Constant.GRANDE;
        }
        return marcador;
    }

    private void save() {
        request = new Request();
        request.setHashMapValueToInput();

        request.setDataInput("Tarefa.nome", new Input(1, "text", "Nome da Tarefa", jTextFieldNome.getText()));
        request.setDataInput("Tarefa.descricao", new Input(2, "text", "Descrição", jTextAreaDescricao.getText()));
        request.setDataInput("Tarefa.marcador", new Input(3, "text", "Marcador", String.valueOf(getRadioButtonSelected())));

        if (!isValidData(request)) {
            return;
        }

        if (isValidDate()) {
            JOptionPane.showMessageDialog(null,
                    "Data não pode ser menor que a data atual.", null, JOptionPane.ERROR_MESSAGE);
            return;
        }

        request.setDataInput("Problema.id", new Input(4, "text", "id do Problema", KeepData.getData("Problema.id")));
        Date chosenDate = (Date) dateFieldData.getValue();

        try {
            if (type == Constant.CREATE) {
                controllerTarefas.addTarefa(request, chosenDate);
            } else {
               request.setDataInput("Tarefa.id", new Input(5, "text", "id Tarefa", String.valueOf(idTarefa))); 
               controllerTarefas.upDateTarefa(request, chosenDate); 
            }

            JOptionPane.showMessageDialog(null, "\"Tarefa\" foi salva com sucesso.");
            this.dispose();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "ERRO AO SALVAR", JOptionPane.ERROR_MESSAGE);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        dateFieldData = new net.sf.nachocalendar.components.DateField();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Tarefa");
        setResizable(false);

        jLabel1.setText("Nome: ");

        jTextFieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeActionPerformed(evt);
            }
        });

        jLabel3.setText("Descrição:");

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescricao);

        dateFieldData.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dateFieldDataStateChanged(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), " Marcadores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 51, 51))); // NOI18N

        jRadioButton1.setBackground(new java.awt.Color(153, 255, 153));
        jRadioButton1.setText("Trivial");

        jRadioButton2.setBackground(new java.awt.Color(202, 224, 251));
        jRadioButton2.setText("Pequeno");

        jRadioButton3.setBackground(new java.awt.Color(255, 255, 153));
        jRadioButton3.setText("Médio");

        jRadioButton4.setBackground(new java.awt.Color(255, 153, 153));
        jRadioButton4.setText("Grande");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Prazo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(441, 441, 441))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(311, 311, 311)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(dateFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void jTextFieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeActionPerformed

    private void dateFieldDataStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dateFieldDataStateChanged

    }//GEN-LAST:event_dateFieldDataStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private net.sf.nachocalendar.components.DateField dateFieldData;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
