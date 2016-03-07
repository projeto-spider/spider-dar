package view.Gerenciar;

import controller.ControllerKeywords;
import controller.ControllerProblema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import model.Keyword;
import settings.Constant;
import settings.KeepData;
import util.Input;
import util.Request;
import util.swing.ComboItem;

/**
 *
 * @author Bleno Vale
 */
public class ViewNovoProblema extends javax.swing.JDialog
{
    private final ControllerProblema controllerProblema = new ControllerProblema();
    private final ControllerKeywords controllerKeywords = new ControllerKeywords();
    private int type;
    private Request request = new Request();
    private DefaultListModel myJListModel = new DefaultListModel<>();

    public ViewNovoProblema(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.type = Constant.CREATE;

        this.setLocationRelativeTo(null);
    }
    
    public ViewNovoProblema(java.awt.Frame parent, boolean modal, String idProblema)
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
        List<Keyword> keywordList = controllerKeywords.listKeywordsByIdProblema(request);
        
        request.setData("Problema.id",problema.getData("Problema.id"));
        
        jTextFieldProblemaNome.setText(problema.getData("Problema.nome"));
        jTextAreaProblemaProposito.setText(problema.getData("Problema.proposito"));
        jTextAreaProblemaPlanejamento.setText(problema.getData("Problema.planejamento"));
        jTextAreaProblemaContexto.setText(problema.getData("Problema.contexto"));
        
        int keywordsSize = keywordList.size();
        
        for (int indexList = 0; indexList < keywordsSize; indexList++)
        {
            String idKeyword = String.valueOf(keywordList.get(indexList).getKeywordPK());
            String nomeKeyword = keywordList.get(indexList).getNome();
        
            myJListModel.addElement(new ComboItem(idKeyword, nomeKeyword));
        }
        
        jListKeywords.setModel(myJListModel);
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
        jScrollPane5 = new javax.swing.JScrollPane();
        jListKeywords = new javax.swing.JList<>();
        jButtonNovaKeyword = new javax.swing.JButton();
        jButtonExcluirKeyword = new javax.swing.JButton();

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

        jListKeywords.setToolTipText("");
        jScrollPane5.setViewportView(jListKeywords);

        jButtonNovaKeyword.setText("Nova Palavra");
        jButtonNovaKeyword.setToolTipText("");
        jButtonNovaKeyword.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonNovaKeywordActionPerformed(evt);
            }
        });

        jButtonExcluirKeyword.setText("Excluir Palavra");
        jButtonExcluirKeyword.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonExcluirKeywordActionPerformed(evt);
            }
        });

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelKeyword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonExcluirKeyword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonNovaKeyword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 2, Short.MAX_VALUE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNovaKeyword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonExcluirKeyword))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    static class fieldTypeValue
    {
        public static final int TEXT = 1;
        public static final int TEXTAREA = 2;
        public static final int HIDDEN = 3;
        public static final int LIST = 4;
    }
    
    private boolean isValidData(Request request)
    {
        HashMap<String, Input> fields = request.getAllHashMapDataInputs();
        int fieldsSize = fields.size();

        for (int index = 1; index <= fieldsSize; index++)
        {
            for (Map.Entry<String, Input> entry: fields.entrySet())
            {
                Input inputToValidate = entry.getValue();

                if (index == inputToValidate.getOrdem())
                {
                    HashMap<String,Integer> caseHash = new HashMap<>();
                    caseHash.put("text", 1);
                    caseHash.put("textarea", 2);
                    caseHash.put("hidden", 3);
                    caseHash.put("list", 4);
                    
                    String fieldType = inputToValidate.getTipo();
                    
                    int caseThisValue = caseHash.containsKey(fieldType) ? caseHash.get(fieldType) : -1;
                    
                    switch (caseThisValue)
                    {
                        case fieldTypeValue.TEXT:
                            if(!this.isValidText(inputToValidate))
                                return false;
                            break;
                        case fieldTypeValue.TEXTAREA:
                            if(!this.isValidTextarea(inputToValidate))
                                return false;
                            break;
                        case fieldTypeValue.HIDDEN:
                            break;
                        case fieldTypeValue.LIST:
                            if (!this.isValidListField(inputToValidate))
                                return false;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Campo Obigatório: " + inputToValidate.getNome(),"ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
                            return false;
                    }
                }
            }
        }
        return true;
    }
    
    private boolean isValidText(Input inputToValidate)
    {
        if (inputToValidate.getValor().trim().length() <= 0)
        {
            JOptionPane.showMessageDialog(null, "Campo Obigatório: " + inputToValidate.getNome(),"ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean isValidTextarea(Input inputToValidate)
    {
        String newline = System.getProperty("line.separator");
        
        boolean hasNewline = inputToValidate.getValor().contains(newline);
        
        if (inputToValidate.getValor().trim().length() <= 0 || hasNewline)
        {
            JOptionPane.showMessageDialog(null, "Campo Obigatório: " + inputToValidate.getNome(),"ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean isValidListField(Input inputToValidate)
    {
        if (inputToValidate.getValor().trim().length() <= 0)
        {
            JOptionPane.showMessageDialog(null, "Campo Obigatório: " + inputToValidate.getNome(),"ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void save()
    {
        request.setHashMapValueToInput();
        
        request.setDataInput("Problema.nome", new Input(1, "text", "Nome do Problema", jTextFieldProblemaNome.getText()));
        request.setDataInput("Problema.proposito", new Input(2, "textarea", "Propósito", jTextAreaProblemaProposito.getText()));
        request.setDataInput("Problema.planejamento", new Input(3, "textarea", "Planejamento", jTextAreaProblemaPlanejamento.getText()));
        request.setDataInput("Problema.contexto", new Input(4, "textarea", "Contexto/Cenário", jTextAreaProblemaContexto.getText()));
        request.setDataInput("Problema.idOrganizacao", new Input(5, "hidden", "idOrganizacao", KeepData.getData("Organizacao.id")));
        
        ListModel listModel = jListKeywords.getModel();
        int keywordIndex;
        boolean isEmptyList = false;
        
        if (listModel.getSize() > 0)
        {
            for (keywordIndex = 0; keywordIndex < listModel.getSize(); keywordIndex++)
                request.setDataInput("Keyword."+ keywordIndex + ".nome", new Input(6, "list", "Palavras-Chave", listModel.getElementAt(keywordIndex).toString()));
        }
        else
            isEmptyList = true;
        
        if (!this.isValidData(request))
            return;
        
        if (isEmptyList)
        {
            JOptionPane.showMessageDialog(null, "É necessário cadastrar pelo menos 01 (uma) Palavra-chave.");
            return;
        }
        
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
    
    private void addKeywords()
    {
        int numberElements = jListKeywords.getModel().getSize();
        
        if (!(numberElements > 2))
        {
            String value = JOptionPane.showInputDialog("Insira uma Palavra-Chave");
            
            if (value != null)
            {
                if (value.trim().length() > 0)
                    myJListModel.addElement(new ComboItem(String.valueOf(numberElements), value));
                else
                   JOptionPane.showMessageDialog(null,"A Palavra-chave não pode ser vazia ou somente espaços em branco");
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Somente 3 palavras chaves no máximo!");
        
        jListKeywords.setModel(myJListModel);
    }
    
    private void excluirKeyword()
    {
        int indexItemSelected = jListKeywords.getSelectedIndex();

        if (indexItemSelected < 0)
            JOptionPane.showMessageDialog(null, "Selecione uma das palavras para excluir"); 
        else
        {
            myJListModel.remove(indexItemSelected);
        }
    }

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        this.save();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonNovaKeywordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonNovaKeywordActionPerformed
    {//GEN-HEADEREND:event_jButtonNovaKeywordActionPerformed
        this.addKeywords();
    }//GEN-LAST:event_jButtonNovaKeywordActionPerformed

    private void jButtonExcluirKeywordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonExcluirKeywordActionPerformed
    {//GEN-HEADEREND:event_jButtonExcluirKeywordActionPerformed
        this.excluirKeyword();
    }//GEN-LAST:event_jButtonExcluirKeywordActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluirKeyword;
    private javax.swing.JButton jButtonNovaKeyword;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelKeyword;
    private javax.swing.JList<String> jListKeywords;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextArea jTextAreaProblemaContexto;
    private javax.swing.JTextArea jTextAreaProblemaPlanejamento;
    private javax.swing.JTextArea jTextAreaProblemaProposito;
    private javax.swing.JTextField jTextFieldProblemaNome;
    // End of variables declaration//GEN-END:variables
}
