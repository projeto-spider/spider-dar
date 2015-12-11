package view.Organizacional;

import controller.ControllerGuia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import settings.Constant;
import util.Internal;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewGuiaGestaoDeDecisao extends javax.swing.JInternalFrame {

    private int type;
    private Request request;
    private ControllerGuia controllerGuia = new ControllerGuia();

    public ViewGuiaGestaoDeDecisao() {
        initComponents();
        
        buttonGruop();
        Internal.retiraBorda(this);
    }

    private void buttonGruop() {
        buttonGroup.add(jRadioButtonImportacaoArquivo);
        buttonGroup.add(jRadioButtonInsercaoManual);
    }

    private void disableInsercaoManual() {
        jTextArea.setEnabled(false);
        jButtonSalvar.setEnabled(false);

        jButtonImportarArquivo.setEnabled(true);
        jButtonAbrirArquivo.setEnabled(true);
    }

    private void disableImportacaoArquivo() {
        jButtonImportarArquivo.setEnabled(false);
        jButtonAbrirArquivo.setEnabled(false);

        jTextArea.setEnabled(true);
        jButtonSalvar.setEnabled(true);
    }

    private boolean fieldValidation() {
        if (jTextAreaProposito.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Propósito\" na aba \"Informações gerais\" é obrigatório.");
            return false;
        } else if (jTextAreaDiretizDefinicao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Definição\" na aba \"Diretrizes\" é obrigatório.");
            return false;
        } else if (jTextAreaDiretrizAplicacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Aplicação\" na aba \"Diretrizes\" é obrigatório.");
            return false;
        } else if (jTextAreaProblemaDefinicao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Definição\" na aba \"Problema\" é obrigatório.");
            return false;
        } else if (jTextAreaProblemaAplicacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Aplicação\" na aba \"Problema\" é obrigatório.");
            return false;
        } else if (jTextAreaCriteriosDefinicao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Definição\" na aba \"Critérios de avaliação\" é obrigatório.");
            return false;
        } else if (jTextAreaCriteriosAplicacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Aplicação\" na aba \"Critérios de avaliação\" é obrigatório.");
            return false;
        } else if (jTextAreaSolucaoDefinicao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Definição\" na aba \"Soluções alternativas\" é obrigatório.");
            return false;
        } else if (jTextAreaSolucaoAplicacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Aplicação\" na aba \"Soluções alternativas\" é obrigatório.");
            return false;
        } else if (jTextAreaMetodoDefinicao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Definição\" na aba \"Método de avaliação\" é obrigatório.");
            return false;
        } else if (jTextAreaMetodoAplicacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Aplicação\" na aba \"Método de avaliação\" é obrigatório.");
            return false;
        } else if (jTextAreaAvaliarDefinicao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Definição\" na aba \"Avaliar alternativas\" é obrigatório.");
            return false;
        } else if (jTextAreaAvaliarAplicacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Aplicação\" na aba \"Avaliar alternativas\" é obrigatório.");
            return false;
        } else if (jTextAreaSelecionarDeficao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Definição\" na aba \"Selecionar soluções\" é obrigatório.");
            return false;
        } else if (jTextAreaSelecionarAplicacao.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Aplicação\" na aba \"Selecionar soluções\" é obrigatório.");
            return false;
        } else {
            return true;
        }
    }

    private void saveManual() {
        if (!fieldValidation()) {
            return;
        }

        Map<String, String> data = new HashMap<>();
        data.put("Guia.caminhoguia", "Manual");
        data.put("Guia.descricao", jTextAreaProposito.getText());

        boolean isDone = false;
        if (type == Constant.CREATE) {
            request = new Request(data);
            isDone = controllerGuia.createGuia(request);
        }

        if (isDone) {
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Erro ao salvar.", "ERRO AO SALVAR", type);
        }

        List<Request> list = new ArrayList<>();
        data = new HashMap<>();
        data.put("Itemguia.tipo", "Diretriz");
        data.put("Itemguia.definicao", jTextAreaDiretizDefinicao.getText());
        data.put("Itemguia.aplicacao", jTextAreaDiretrizAplicacao.getText());
        list.add(new Request(data));

        //data = new HashMap<>();
        data.put("Itemguia.tipo", "Problema");
        data.put("Itemguia.definicao", jTextAreaProblemaDefinicao.getText());
        data.put("Itemguia.aplicacao", jTextAreaProblemaAplicacao.getText());
        list.add(new Request(data));

        //data = new HashMap<>();
        data.put("Itemguia.tipo", "Criterio");
        data.put("Itemguia.definicao", jTextAreaCriteriosDefinicao.getText());
        data.put("Itemguia.aplicacao", jTextAreaCriteriosAplicacao.getText());
        list.add(new Request(data));

        //data = new HashMap<>();
        data.put("Itemguia.tipo", "Solucoes");
        data.put("Itemguia.definicao", jTextAreaSolucaoDefinicao.getText());
        data.put("Itemguia.aplicacao", jTextAreaSolucaoAplicacao.getText());
        list.add(new Request(data));

        //data = new HashMap<>();
        data.put("Itemguia.tipo", "Metodos");
        data.put("Itemguia.definicao", jTextAreaMetodoDefinicao.getText());
        data.put("Itemguia.aplicacao", jTextAreaMetodoAplicacao.getText());
        list.add(new Request(data));

        //data = new HashMap<>();
        data.put("Itemguia.tipo", "Avaliar");
        data.put("Itemguia.definicao", jTextAreaAvaliarDefinicao.getText());
        data.put("Itemguia.aplicacao", jTextAreaAvaliarAplicacao.getText());
        list.add(new Request(data));

        //data = new HashMap<>();
        data.put("Itemguia.tipo", "Selecionar");
        data.put("Itemguia.definicao", jTextAreaSelecionarDeficao.getText());
        data.put("Itemguia.aplicacao", jTextAreaSelecionarAplicacao.getText());
        list.add(new Request(data));

        controllerGuia.createItemGuia(list);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButtonImportacaoArquivo = new javax.swing.JRadioButton();
        jRadioButtonInsercaoManual = new javax.swing.JRadioButton();
        jPanelImportarArquivo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButtonImportarArquivo = new javax.swing.JButton();
        jButtonAbrirArquivo = new javax.swing.JButton();
        jPanelInsecaoManual = new javax.swing.JPanel();
        jButtonSalvar = new javax.swing.JButton();
        jTextArea = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaProposito = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDiretizDefinicao = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaDiretrizAplicacao = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaProblemaDefinicao = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaProblemaAplicacao = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaCriteriosDefinicao = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaCriteriosAplicacao = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextAreaSolucaoDefinicao = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextAreaSolucaoAplicacao = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextAreaMetodoDefinicao = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextAreaMetodoAplicacao = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextAreaAvaliarDefinicao = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        jScrollPaneAvaliarAplicacao = new javax.swing.JScrollPane();
        jTextAreaAvaliarAplicacao = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextAreaSelecionarDeficao = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextAreaSelecionarAplicacao = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Organização: Desenvolvimento ");

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

        jPanelImportarArquivo.setBorder(javax.swing.BorderFactory.createTitledBorder("Importar Arquivo"));

        jLabel3.setText("Não há arquivo importado.");

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
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelImportarArquivoLayout.setVerticalGroup(
            jPanelImportarArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImportarArquivoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanelImportarArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelImportarArquivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonAbrirArquivo)
                        .addComponent(jLabel3))
                    .addComponent(jButtonImportarArquivo))
                .addGap(5, 5, 5))
        );

        jPanelInsecaoManual.setBorder(javax.swing.BorderFactory.createTitledBorder("Inserção Manual"));

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel4.setText(" propósito do processo Gerência de Decisões na Organização:");

        jTextAreaProposito.setColumns(20);
        jTextAreaProposito.setRows(5);
        jScrollPane2.setViewportView(jTextAreaProposito);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 408, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTextArea.addTab("<html>Informações<br>Gerais</html>", jPanel3);

        jLabel5.setText(" Diretrizes para Análise e Decisão Informação ");

        jLabel6.setText("Definição:");

        jTextAreaDiretizDefinicao.setColumns(20);
        jTextAreaDiretizDefinicao.setRows(2);
        jScrollPane3.setViewportView(jTextAreaDiretizDefinicao);

        jLabel7.setText("Aplicação:");

        jTextAreaDiretrizAplicacao.setColumns(20);
        jTextAreaDiretrizAplicacao.setRows(2);
        jScrollPane4.setViewportView(jTextAreaDiretrizAplicacao);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 484, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextArea.addTab("<html>Diretrizes</html>", jPanel4);

        jLabel8.setText("O problema ou questão a ser objeto de um processo formal de tomada de decisão é definido ");

        jLabel9.setText("Definição:");

        jTextAreaProblemaDefinicao.setColumns(20);
        jTextAreaProblemaDefinicao.setRows(2);
        jScrollPane5.setViewportView(jTextAreaProblemaDefinicao);

        jLabel10.setText("Aplicação:");

        jTextAreaProblemaAplicacao.setColumns(20);
        jTextAreaProblemaAplicacao.setRows(2);
        jScrollPane6.setViewportView(jTextAreaProblemaAplicacao);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(0, 259, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextArea.addTab("<html>Problema</html>", jPanel5);

        jLabel11.setText("Estabelecer Critérios de Avaliação ");

        jLabel12.setText("Definição:");

        jTextAreaCriteriosDefinicao.setColumns(20);
        jTextAreaCriteriosDefinicao.setRows(2);
        jScrollPane7.setViewportView(jTextAreaCriteriosDefinicao);

        jLabel13.setText("Aplicação:");

        jTextAreaCriteriosAplicacao.setColumns(20);
        jTextAreaCriteriosAplicacao.setRows(2);
        jScrollPane8.setViewportView(jTextAreaCriteriosAplicacao);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(0, 538, Short.MAX_VALUE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextArea.addTab("<html>Critérios de<br>Avaliação </html>", jPanel6);

        jLabel14.setText("Identificar Soluções Alternativas");

        jLabel15.setText("Definição:");

        jTextAreaSolucaoDefinicao.setColumns(20);
        jTextAreaSolucaoDefinicao.setRows(2);
        jScrollPane9.setViewportView(jTextAreaSolucaoDefinicao);

        jLabel16.setText("Aplicação:");

        jTextAreaSolucaoAplicacao.setColumns(20);
        jTextAreaSolucaoAplicacao.setRows(2);
        jScrollPane10.setViewportView(jTextAreaSolucaoAplicacao);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane9)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addGap(0, 549, Short.MAX_VALUE))
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextArea.addTab("<html>Soluções<br>Alternativas</html>", jPanel7);

        jLabel17.setText("Selecionar Métodos de Avaliação");

        jLabel18.setText("Definição:");

        jTextAreaMetodoDefinicao.setColumns(20);
        jTextAreaMetodoDefinicao.setRows(2);
        jScrollPane11.setViewportView(jTextAreaMetodoDefinicao);

        jLabel19.setText("Aplicação:");

        jTextAreaMetodoAplicacao.setColumns(20);
        jTextAreaMetodoAplicacao.setRows(2);
        jScrollPane12.setViewportView(jTextAreaMetodoAplicacao);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane11)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(0, 547, Short.MAX_VALUE))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextArea.addTab("<html>Métodos de<br>Avaliação</html>", jPanel8);

        jLabel20.setText("Avaliar Alternativas ");

        jLabel21.setText("Definição:");

        jTextAreaAvaliarDefinicao.setColumns(20);
        jTextAreaAvaliarDefinicao.setRows(2);
        jScrollPane13.setViewportView(jTextAreaAvaliarDefinicao);

        jLabel22.setText("Aplicação:");

        jTextAreaAvaliarAplicacao.setColumns(20);
        jTextAreaAvaliarAplicacao.setRows(2);
        jScrollPaneAvaliarAplicacao.setViewportView(jTextAreaAvaliarAplicacao);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane13)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(0, 607, Short.MAX_VALUE))
                    .addComponent(jScrollPaneAvaliarAplicacao, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addGap(0, 0, 0)
                .addComponent(jScrollPaneAvaliarAplicacao, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextArea.addTab("<html>Avaliar<br>Alternativas </html>", jPanel9);

        jLabel23.setText("Selecionar Soluções");

        jLabel24.setText("Definição:");

        jTextAreaSelecionarDeficao.setColumns(20);
        jTextAreaSelecionarDeficao.setRows(2);
        jScrollPane15.setViewportView(jTextAreaSelecionarDeficao);

        jLabel25.setText("Aplicação:");

        jTextAreaSelecionarAplicacao.setColumns(20);
        jTextAreaSelecionarAplicacao.setRows(2);
        jScrollPane16.setViewportView(jTextAreaSelecionarAplicacao);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane15)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addGap(0, 610, Short.MAX_VALUE))
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextArea.addTab("<html>Selecionar<br>Soluções</html>", jPanel10);

        javax.swing.GroupLayout jPanelInsecaoManualLayout = new javax.swing.GroupLayout(jPanelInsecaoManual);
        jPanelInsecaoManual.setLayout(jPanelInsecaoManualLayout);
        jPanelInsecaoManualLayout.setHorizontalGroup(
            jPanelInsecaoManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInsecaoManualLayout.createSequentialGroup()
                .addGroup(jPanelInsecaoManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelInsecaoManualLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextArea))
                    .addGroup(jPanelInsecaoManualLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelInsecaoManualLayout.setVerticalGroup(
            jPanelInsecaoManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInsecaoManualLayout.createSequentialGroup()
                .addComponent(jTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSalvar))
        );

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonImportacaoArquivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButtonInsercaoManual)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanelInsecaoManual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelImportarArquivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jRadioButtonImportacaoArquivo)
                    .addComponent(jRadioButtonInsercaoManual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelImportarArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelInsecaoManual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonImportacaoArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonImportacaoArquivoActionPerformed
        disableInsercaoManual();
    }//GEN-LAST:event_jRadioButtonImportacaoArquivoActionPerformed

    private void jRadioButtonInsercaoManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonInsercaoManualActionPerformed
        disableImportacaoArquivo();
    }//GEN-LAST:event_jRadioButtonInsercaoManualActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        saveManual();
    }//GEN-LAST:event_jButtonSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton jButtonAbrirArquivo;
    private javax.swing.JButton jButtonImportarArquivo;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelImportarArquivo;
    private javax.swing.JPanel jPanelInsecaoManual;
    private javax.swing.JRadioButton jRadioButtonImportacaoArquivo;
    private javax.swing.JRadioButton jRadioButtonInsercaoManual;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JScrollPane jScrollPaneAvaliarAplicacao;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTextArea;
    private javax.swing.JTextArea jTextAreaAvaliarAplicacao;
    private javax.swing.JTextArea jTextAreaAvaliarDefinicao;
    private javax.swing.JTextArea jTextAreaCriteriosAplicacao;
    private javax.swing.JTextArea jTextAreaCriteriosDefinicao;
    private javax.swing.JTextArea jTextAreaDiretizDefinicao;
    private javax.swing.JTextArea jTextAreaDiretrizAplicacao;
    private javax.swing.JTextArea jTextAreaMetodoAplicacao;
    private javax.swing.JTextArea jTextAreaMetodoDefinicao;
    private javax.swing.JTextArea jTextAreaProblemaAplicacao;
    private javax.swing.JTextArea jTextAreaProblemaDefinicao;
    private javax.swing.JTextArea jTextAreaProposito;
    private javax.swing.JTextArea jTextAreaSelecionarAplicacao;
    private javax.swing.JTextArea jTextAreaSelecionarDeficao;
    private javax.swing.JTextArea jTextAreaSolucaoAplicacao;
    private javax.swing.JTextArea jTextAreaSolucaoDefinicao;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
