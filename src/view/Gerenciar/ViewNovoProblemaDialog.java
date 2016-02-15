package view.Gerenciar;

import controller.ControllerProblema;
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
public class ViewNovoProblemaDialog extends javax.swing.JDialog
{
    private final ControllerProblema controllerProblema = new ControllerProblema();
    private int type;
    private Request request = new Request();

    public ViewNovoProblemaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.type = Constant.CREATE;

        this.setLocationRelativeTo(null);
    }
    
    public ViewNovoProblemaDialog(java.awt.Frame parent, boolean modal, String idProblema)
    {
        super(parent, modal);
        initComponents();
        
        this.type = Constant.UPDATE;
        this.fillFieldsProblemaById(idProblema);
        
        this.setLocationRelativeTo(null);
    }
    
    private void fillFieldsProblemaById(String idProblema)
    {
        request.setData("Problema.id", idProblema);
        request.setData("Problema.idOrganizacao", KeepData.getData("Organizacao.id"));
        
        Request problema = controllerProblema.getProblemaById(request);
        
        request.setData("Problema.id",problema.getData("Problema.id"));
        
        jTextFieldProblemaNome.setText(problema.getData("Problema.nome"));
        jTextAreaProblemaProposito.setText(problema.getData("Problema.proposito"));
        jTextAreaProblemaPlanejamento.setText(problema.getData("Problema.planejamento"));
        jTextAreaProblemaContexto.setText(problema.getData("Problema.contexto"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jTextFieldProblemaNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaProblemaProposito = new javax.swing.JTextArea();
        jButtonCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaProblemaPlanejamento = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaProblemaContexto = new javax.swing.JTextArea();
        jButtonSalvar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelKeyword = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableKeywords = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Tomada de Decisão");
        setResizable(false);

        jTextFieldProblemaNome.setNextFocusableComponent(jTextAreaProblemaProposito);

        jTextAreaProblemaProposito.setColumns(20);
        jTextAreaProblemaProposito.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextAreaProblemaProposito.setLineWrap(true);
        jTextAreaProblemaProposito.setRows(2);
        jTextAreaProblemaProposito.setNextFocusableComponent(jTextAreaProblemaPlanejamento);
        jScrollPane1.setViewportView(jTextAreaProblemaProposito);

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jTextAreaProblemaPlanejamento.setColumns(20);
        jTextAreaProblemaPlanejamento.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextAreaProblemaPlanejamento.setLineWrap(true);
        jTextAreaProblemaPlanejamento.setRows(2);
        jTextAreaProblemaPlanejamento.setNextFocusableComponent(jTextAreaProblemaContexto);
        jScrollPane2.setViewportView(jTextAreaProblemaPlanejamento);

        jTextAreaProblemaContexto.setColumns(20);
        jTextAreaProblemaContexto.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jTextAreaProblemaContexto.setLineWrap(true);
        jTextAreaProblemaContexto.setRows(2);
        jScrollPane3.setViewportView(jTextAreaProblemaContexto);

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome do Problema:");

        jLabel2.setText("Propósito:");

        jLabel3.setText("Planejamento:");

        jLabel4.setText("Contexto/Cenário:");

        jLabelKeyword.setText("Palavras-Chave (Até 3 palavras):");

        jTableKeywords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String []
            {
                "Nº", "Palavra", "Nova", "Editar", "Excluirl"
            }
        ));
        jTableKeywords.setNextFocusableComponent(jButtonSalvar);
        jScrollPane4.setViewportView(jTableKeywords);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldProblemaNome)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabelKeyword)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 184, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jTextFieldProblemaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelKeyword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean isValidData(Request request)
    {
        HashMap<String, Input> fields = request.getAllHashMapDataInputs();
     
        int fieldsSize = fields.size();
        
        for (int index = 1; index <= fieldsSize; index++)
        {
            for (Map.Entry<String, Input> entry: fields.entrySet())
            {
                Input inputToValidate = entry.getValue();
                
                boolean isNotEqualsToHidden = !(inputToValidate.getTipo().equals("hidden"));
                
                if (index == inputToValidate.getOrdem() && isNotEqualsToHidden)
                {
                    if (inputToValidate.getValor().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Campo Obigatório: " + inputToValidate.getNome(),"ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void save()
    {
        request.setHashMapValueToInput();
        
        request.setDataInput("Problema.nome", new Input(1, "text", "Nome do Problema", jTextFieldProblemaNome.getText()));
        request.setDataInput("Problema.proposito", new Input(3, "textarea", "Propósito", jTextAreaProblemaProposito.getText()));
        request.setDataInput("Problema.planejamento", new Input(4, "textarea", "Planejamento", jTextAreaProblemaPlanejamento.getText()));
        request.setDataInput("Problema.contexto", new Input(5, "textarea", "Contexto/Cenário", jTextAreaProblemaContexto.getText()));
        request.setDataInput("Problema.idOrganizacao", new Input(6, "hidden", "idOrganizacao", KeepData.getData("Organizacao.id")));
        
        if (!this.isValidData(request))
            return;
        
        try
        {
            if (type == Constant.CREATE) 
                this.controllerProblema.addProblema(request);
            else
            {
                String idProblema = request.getData("Problema.id");
                
                request.setDataInput("Problema.id", new Input(0,"hidden", "Id", idProblema));
                request.setData("Problema.idOrganizacao",KeepData.getData("Organizacao.id"));
                
                controllerProblema.editProblema(request);
            }
            
            JOptionPane.showMessageDialog(null, "o item \"Problema\" foi salvo com sucesso.");
            this.dispose();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"ERRO AO SALVAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        this.save();// TODO add your handling code here:
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelKeyword;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableKeywords;
    private javax.swing.JTextArea jTextAreaProblemaContexto;
    private javax.swing.JTextArea jTextAreaProblemaPlanejamento;
    private javax.swing.JTextArea jTextAreaProblemaProposito;
    private javax.swing.JTextField jTextFieldProblemaNome;
    // End of variables declaration//GEN-END:variables
}
