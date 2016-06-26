package view.Gerenciar;

import controller.ControllerPerfil;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import settings.Constant;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewNovoPerfil extends javax.swing.JDialog {

    private final ControllerPerfil controllerPerfil = new ControllerPerfil();
    private Request request;
    private int type;

    /**
     * Contrutor usado no cadastro de um novo Perfil.
     */
    public ViewNovoPerfil(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        type = Constant.CREATE;
        this.setLocationRelativeTo(null);
    }
    
     /**
     * Construtor usado para edição das informações de um Perfil.
     *
     * @param name nome do perfil a ser editado.
     */
     public ViewNovoPerfil(java.awt.Frame parent, boolean modal, String name) {
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
        if (jTextFieldNomePerfil.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Campo nome do Perfil é obrigatório.",
                    "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (jTextAreaHabilidades.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Campo Habilidades é obrigatório.",
                    "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (jTextAreaCompetencias.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Campo Competencias é obrigatório.",
                    "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void fillFields(String name) {
        request = controllerPerfil.findPerfilSelected(name);
        jTextFieldNomePerfil.setText(request.getData("Perfil.nome"));
        jTextAreaHabilidades.setText(request.getData("Perfil.habilidades"));
        jTextAreaCompetencias.setText(request.getData("Perfil.competencias"));
    }
    
    /**
     * Método responsavel pelo cadastro ou edição de um novo Perfil.
     */
    private void save() {
        if (!fieldValidation()) {
            return;
        }

        Map<String, String> data = new HashMap<>();
        data.put("Perfil.nome", jTextFieldNomePerfil.getText());
        data.put("Perfil.habilidades", jTextAreaHabilidades.getText());
        data.put("Perfil.competencias", jTextAreaCompetencias.getText());

        boolean isDone = false;
        if (type == Constant.CREATE) {
            request = new Request(data);
            isDone = controllerPerfil.createPerfil(request);
        } else {
            data.put("Perfil.id", request.getData("Perfil.id"));
            request = new Request(data);
            isDone = controllerPerfil.updatePerfil(request);
        }
        
        if (isDone) {
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Por favor, verifique se este Perfil já não foi cadastrada.",
                    "ERRO AO SALVAR", type);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNomePerfil = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaHabilidades = new javax.swing.JTextArea();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaCompetencias = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Perfil");
        setResizable(false);

        jTextAreaHabilidades.setColumns(20);
        jTextAreaHabilidades.setRows(3);
        jScrollPane1.setViewportView(jTextAreaHabilidades);

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome do Perfil:");

        jLabel2.setText("Habilidades:");

        jLabel3.setText("Competencias:");

        jTextAreaCompetencias.setColumns(20);
        jTextAreaCompetencias.setRows(2);
        jScrollPane2.setViewportView(jTextAreaCompetencias);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                    .addComponent(jTextFieldNomePerfil, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(310, Short.MAX_VALUE)
                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldNomePerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonCancelar))
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaCompetencias;
    private javax.swing.JTextArea jTextAreaHabilidades;
    private javax.swing.JTextField jTextFieldNomePerfil;
    // End of variables declaration//GEN-END:variables
}
