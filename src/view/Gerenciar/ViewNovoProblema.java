package view.Gerenciar;

import controller.ControllerKeywords;
import controller.ControllerProblema;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import model.Keyword;
import settings.Constant;
import settings.KeepData;
import util.Input;
import util.Request;
import util.Validate;
import util.swing.ComboItem;
import static javax.swing.JOptionPane.*;

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
    private void initComponents() {

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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jListKeywords = new javax.swing.JList<String>();
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
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
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
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome do Problema:");

        jLabel2.setText("Propósito:");

        jLabel3.setText("Planejamento:");

        jLabel4.setText("Contexto/Cenário:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Palavras-Chave (Até 3 palavras)"));

        jListKeywords.setToolTipText("");
        jScrollPane5.setViewportView(jListKeywords);

        jButtonNovaKeyword.setText("Nova Palavra");
        jButtonNovaKeyword.setToolTipText("");
        jButtonNovaKeyword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaKeywordActionPerformed(evt);
            }
        });

        jButtonExcluirKeyword.setText("Excluir Palavra");
        jButtonExcluirKeyword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirKeywordActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonExcluirKeyword)
                    .addComponent(jButtonNovaKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonNovaKeyword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonExcluirKeyword))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(380, Short.MAX_VALUE)
                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldProblemaNome)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldProblemaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        
        Validate validate = new Validate(request);
        
        if (!validate.isValidData())
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
            showMessageDialog(null, "Selecione uma das palavras para excluir"); 
        else
        {   
            String ObjectButtons[] = {"Sim","Não"};

            int PromptResult = showOptionDialog(null, "Deseja excluir a Palavra-chave?", 
                    "EXCLUIR", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
                    null, ObjectButtons, ObjectButtons[1]);
            
            if (PromptResult == 0)
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
    private javax.swing.JList<String> jListKeywords;
    private javax.swing.JPanel jPanel1;
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
