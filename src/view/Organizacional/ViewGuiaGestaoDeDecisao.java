package view.Organizacional;

import controller.ControllerGuia;
import settings.KeepData;
import util.Internal;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewGuiaGestaoDeDecisao extends javax.swing.JInternalFrame {

    private int type;
    private Request request;
    private final ControllerGuia controllerGuia = new ControllerGuia();

    public ViewGuiaGestaoDeDecisao() {
        initComponents();

        buttonGruop();
        guiaHasBeenRegisterd();
        EnableRadiobuttoms();
        Internal.retiraBorda(this);
    }

    private void buttonGruop() {
        buttonGroup.add(jRadioButtonImportacaoArquivo);
        buttonGroup.add(jRadioButtonInsercaoManual);
    }

    private void EnableRadiobuttoms() {
        if (jRadioButtonImportacaoArquivo.isSelected()) {
            jButtonCadastrar.setEnabled(false);
            jButtonImportarArquivo.setEnabled(true);
            jButtonAbrirArquivo.setEnabled(true);

        } else if (jRadioButtonInsercaoManual.isSelected()) {
            jButtonImportarArquivo.setEnabled(false);
            jButtonAbrirArquivo.setEnabled(false);
            jButtonCadastrar.setEnabled(true);
        }
    }

    private void guiaHasBeenRegisterd() {
        int idOrg = Integer.parseInt(KeepData.getData("Organizacao.id"));
        request = controllerGuia.findGuia(idOrg);

        if (request == null) {
            jRadioButtonImportacaoArquivo.setSelected(true);
            jLabelInsercaoManual.setText("Não há guia cadastrado");
            jLabelImportacaoDeArquivo.setText("Não há arquivo importado.");

        } else if (request.getData("Guia.caminhoguia").equals("Manual")) {
            jRadioButtonInsercaoManual.setSelected(true);
            jLabelInsercaoManual.setText("Guia cadastrado em,  " + request.getData("Guia.created"));
            jLabelImportacaoDeArquivo.setText("Não há arquivo importado.");

        } else if (request.getData("Guia.caminhoguia").equals("Arquivo")) {
            jRadioButtonImportacaoArquivo.setSelected(true);
            jLabelInsercaoManual.setText("Não há guia cadastrado");
            jLabelImportacaoDeArquivo.setText("Guia cadastrado em,  " + request.getData("Guia.created"));
        }
    }

    private void isSelectedButtonCadastrar() {
        if (request == null) {
            new ViewGuiaDeGestaoDeDecisaoCadastro(null, true).setVisible(true);
        } else {
            new ViewGuiaDeGestaoDeDecisaoCadastro(null, true, request).setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButtonImportacaoArquivo = new javax.swing.JRadioButton();
        jRadioButtonInsercaoManual = new javax.swing.JRadioButton();
        jPanelImportarArquivo = new javax.swing.JPanel();
        jLabelImportacaoDeArquivo = new javax.swing.JLabel();
        jButtonImportarArquivo = new javax.swing.JButton();
        jButtonAbrirArquivo = new javax.swing.JButton();
        jPanelInsecaoManual = new javax.swing.JPanel();
        jButtonCadastrar = new javax.swing.JButton();
        jLabelInsercaoManual = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Opção para inserir Guia de Gestão de Decisão: ");

        jRadioButtonImportacaoArquivo.setText("Importação de Arquivo");
        jRadioButtonImportacaoArquivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonImportacaoArquivoActionPerformed(evt);
            }
        });

        jRadioButtonInsercaoManual.setText("Inserção Manual na Ferramenta");
        jRadioButtonInsercaoManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonInsercaoManualActionPerformed(evt);
            }
        });

        jPanelImportarArquivo.setBorder(javax.swing.BorderFactory.createTitledBorder("Importação Arquivo"));

        jLabelImportacaoDeArquivo.setText("Não há arquivo importado.");

        jButtonImportarArquivo.setText("Importar Arquivo");

        jButtonAbrirArquivo.setText("Abrir arquivo");

        javax.swing.GroupLayout jPanelImportarArquivoLayout = new javax.swing.GroupLayout(jPanelImportarArquivo);
        jPanelImportarArquivo.setLayout(jPanelImportarArquivoLayout);
        jPanelImportarArquivoLayout.setHorizontalGroup(
            jPanelImportarArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImportarArquivoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonImportarArquivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAbrirArquivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelImportacaoDeArquivo)
                .addContainerGap(385, Short.MAX_VALUE))
        );
        jPanelImportarArquivoLayout.setVerticalGroup(
            jPanelImportarArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImportarArquivoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelImportarArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonImportarArquivo)
                    .addComponent(jButtonAbrirArquivo)
                    .addComponent(jLabelImportacaoDeArquivo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelInsecaoManual.setBorder(javax.swing.BorderFactory.createTitledBorder("Inserção Manual"));

        jButtonCadastrar.setText("Cadastrar/ Editar Guia");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jLabelInsercaoManual.setText("Não há guia cadastrado");

        javax.swing.GroupLayout jPanelInsecaoManualLayout = new javax.swing.GroupLayout(jPanelInsecaoManual);
        jPanelInsecaoManual.setLayout(jPanelInsecaoManualLayout);
        jPanelInsecaoManualLayout.setHorizontalGroup(
            jPanelInsecaoManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInsecaoManualLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonCadastrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelInsercaoManual)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelInsecaoManualLayout.setVerticalGroup(
            jPanelInsecaoManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInsecaoManualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInsecaoManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCadastrar)
                    .addComponent(jLabelInsercaoManual))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelImportarArquivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelInsecaoManual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonImportacaoArquivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonInsercaoManual)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jRadioButtonImportacaoArquivo)
                    .addComponent(jRadioButtonInsercaoManual))
                .addGap(19, 19, 19)
                .addComponent(jPanelImportarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelInsecaoManual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(246, 179, 111));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setText(" Guia de Gestão de Decisão");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jTextField1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonImportacaoArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonImportacaoArquivoActionPerformed
        EnableRadiobuttoms();
    }//GEN-LAST:event_jRadioButtonImportacaoArquivoActionPerformed

    private void jRadioButtonInsercaoManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonInsercaoManualActionPerformed
        EnableRadiobuttoms();
    }//GEN-LAST:event_jRadioButtonInsercaoManualActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
        isSelectedButtonCadastrar();
    }//GEN-LAST:event_jButtonCadastrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton jButtonAbrirArquivo;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonImportarArquivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelImportacaoDeArquivo;
    private javax.swing.JLabel jLabelInsercaoManual;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanelImportarArquivo;
    private javax.swing.JPanel jPanelInsecaoManual;
    private javax.swing.JRadioButton jRadioButtonImportacaoArquivo;
    private javax.swing.JRadioButton jRadioButtonInsercaoManual;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
