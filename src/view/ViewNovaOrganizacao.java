package view;

import controller.ControllerOrganizacao;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import settings.Constant;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewNovaOrganizacao extends javax.swing.JDialog {

    private final ControllerOrganizacao controllerOrganizacao = new ControllerOrganizacao();
    private Request request;
    private int type;

    /**
     * Contrutor usado no cadastro de uma nova Organização.
     */
    public ViewNovaOrganizacao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        type = Constant.SAVE;
        this.setLocationRelativeTo(null);
    }

    /**
     * Construtor usado para edição das informações de uma Organização.
     *
     * @param name nome da organização a ser editada.
     */
    public ViewNovaOrganizacao(java.awt.Frame parent, boolean modal, String name) {
        super(parent, modal);
        initComponents();

        type = Constant.UPDATE;
        fillFields(name);
        this.setLocationRelativeTo(null);
    }

    /**
     * Método de validação dos campos que devem ser preenchidos na tela.
     *
     * @return
     */
    private boolean fieldValidation() {
        if (jTextFieldNomeOrganizacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo nome da Organização é obrigatório.", "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (jTextAreaDescricao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo Descrição é obrigatório.", "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void fillFields(String name) {
        request = controllerOrganizacao.findOrganizacaoSelected(name);
        jTextFieldNomeOrganizacao.setText(request.getData("Organizacao.nome"));
        jTextAreaDescricao.setText(request.getData("Organizacao.descricao"));
    }

    /**
     * Método responsavel pelo cadastro ou edição de uma nova Organização.
     */
    private void save() {
        if (!fieldValidation()) {
            return;
        }

        Map<String, String> data = new HashMap<>();
        data.put("Organizacao.nome", jTextFieldNomeOrganizacao.getText());
        data.put("Organizacao.descricao", jTextAreaDescricao.getText());

        boolean isDone = false;
        if (type == Constant.SAVE) {
            request = new Request(data);
            isDone = controllerOrganizacao.createNewOrganizacao(request);
        } else {
            data.put("Organizacao.id", request.getData("Organizacao.id"));
            request = new Request(data);
            isDone = controllerOrganizacao.updateOrganizacao(request);
        }

        if (isDone) {
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso.");
            this.setVisible(false);
        } else {
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNomeOrganizacao = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nova Organização");
        setResizable(false);

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescricao);

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

        jLabel1.setText("Nome da Organização:");

        jLabel2.setText("Descrição:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar))
                    .addComponent(jTextFieldNomeOrganizacao, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jTextFieldNomeOrganizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        this.setVisible(false);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextField jTextFieldNomeOrganizacao;
    // End of variables declaration//GEN-END:variables
}
