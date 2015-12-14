package view.Organizacional;

import controller.ControllerGuia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import settings.Constant;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewGuiaDeGestaoDeDecisaoCadastro extends javax.swing.JDialog {

    private int type;
    private Request request;
    private final ControllerGuia controllerGuia = new ControllerGuia();

    public ViewGuiaDeGestaoDeDecisaoCadastro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        type = Constant.CREATE;
        this.setLocationRelativeTo(null);
    }

    /**
     * Contrutor usado para edição.
     *
     * @param request
     */
    public ViewGuiaDeGestaoDeDecisaoCadastro(java.awt.Frame parent, boolean modal, Request request) {
        super(parent, modal);
        initComponents();

        type = Constant.UPDATE;
        this.request = request;
        fillFields();
        this.setLocationRelativeTo(null);
    }

    private boolean fieldValidation() {
        if (jTextAreaProposito.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo \"Propósito\" na aba \"Informações gerais\" é obrigatório.");
            return false;
        } else if (jTextAreaDiretrizDefinicao.getText().isEmpty()) {
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

    private void fillFields() {
        jTextAreaProposito.setText(request.getData("Guia.descricao"));

        int idGuia = Integer.parseInt(request.getData("Guia.id"));
        List<Request> list = controllerGuia.findListItemGuia(idGuia);

        jTextAreaDiretrizDefinicao.setText(list.get(Constant.DIRETRIZES).getData("Itemguia.definicao"));
        jTextAreaDiretrizAplicacao.setText(list.get(Constant.DIRETRIZES).getData("Itemguia.aplicacao"));

        jTextAreaProblemaDefinicao.setText(list.get(Constant.PROBLEMA).getData("Itemguia.definicao"));
        jTextAreaProblemaAplicacao.setText(list.get(Constant.PROBLEMA).getData("Itemguia.aplicacao"));

        jTextAreaCriteriosDefinicao.setText(list.get(Constant.CRITERIOS).getData("Itemguia.definicao"));
        jTextAreaCriteriosAplicacao.setText(list.get(Constant.CRITERIOS).getData("Itemguia.aplicacao"));

        jTextAreaSolucaoDefinicao.setText(list.get(Constant.ALTERNATIVAS).getData("Itemguia.definicao"));
        jTextAreaSolucaoAplicacao.setText(list.get(Constant.ALTERNATIVAS).getData("Itemguia.aplicacao"));

        jTextAreaMetodoDefinicao.setText(list.get(Constant.METODOS).getData("Itemguia.definicao"));
        jTextAreaMetodoAplicacao.setText(list.get(Constant.METODOS).getData("Itemguia.aplicacao"));

        jTextAreaAvaliarDefinicao.setText(list.get(Constant.AVALIAR).getData("Itemguia.definicao"));
        jTextAreaAvaliarAplicacao.setText(list.get(Constant.AVALIAR).getData("Itemguia.aplicacao"));

        jTextAreaSelecionarDeficao.setText(list.get(Constant.SOLUCOES).getData("Itemguia.definicao"));
        jTextAreaSelecionarAplicacao.setText(list.get(Constant.SOLUCOES).getData("Itemguia.aplicacao"));
    }

    private void save() {
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
        } else {
            data.put("Guia.id", request.getData("Guia.id"));
            request = new Request(data);
            isDone = controllerGuia.updateGuia(request);
        }

        List<Request> list = new ArrayList<>();
        data = new HashMap<>();
        data.put("Itemguia.tipo", "Diretriz");
        data.put("Itemguia.definicao", jTextAreaDiretrizDefinicao.getText());
        data.put("Itemguia.aplicacao", jTextAreaDiretrizAplicacao.getText());
        list.add(new Request(data));

        data = new HashMap<>();
        data.put("Itemguia.tipo", "Problema");
        data.put("Itemguia.definicao", jTextAreaProblemaDefinicao.getText());
        data.put("Itemguia.aplicacao", jTextAreaProblemaAplicacao.getText());
        list.add(new Request(data));

        data = new HashMap<>();
        data.put("Itemguia.tipo", "Criterio");
        data.put("Itemguia.definicao", jTextAreaCriteriosDefinicao.getText());
        data.put("Itemguia.aplicacao", jTextAreaCriteriosAplicacao.getText());
        list.add(new Request(data));

        data = new HashMap<>();
        data.put("Itemguia.tipo", "Solucoes");
        data.put("Itemguia.definicao", jTextAreaSolucaoDefinicao.getText());
        data.put("Itemguia.aplicacao", jTextAreaSolucaoAplicacao.getText());
        list.add(new Request(data));

        data = new HashMap<>();
        data.put("Itemguia.tipo", "Metodos");
        data.put("Itemguia.definicao", jTextAreaMetodoDefinicao.getText());
        data.put("Itemguia.aplicacao", jTextAreaMetodoAplicacao.getText());
        list.add(new Request(data));

        data = new HashMap<>();
        data.put("Itemguia.tipo", "Avaliar");
        data.put("Itemguia.definicao", jTextAreaAvaliarDefinicao.getText());
        data.put("Itemguia.aplicacao", jTextAreaAvaliarAplicacao.getText());
        list.add(new Request(data));

        data = new HashMap<>();
        data.put("Itemguia.tipo", "Selecionar");
        data.put("Itemguia.definicao", jTextAreaSelecionarDeficao.getText());
        data.put("Itemguia.aplicacao", jTextAreaSelecionarAplicacao.getText());
        list.add(new Request(data));

        if (type == Constant.CREATE) {
            controllerGuia.createItemGuia(list);
        } else {
            controllerGuia.updateItemGuia(list);
        }

        if (isDone) {
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Erro ao salvar.", "ERRO AO SALVAR", type);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelInformacoesGerais = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaProposito = new javax.swing.JTextArea();
        jPanelDiretrizes = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDiretrizDefinicao = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDiretrizAplicacao = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jPanelProblema = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaProblemaDefinicao = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextAreaProblemaAplicacao = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jPanelCriterios = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaCriteriosDefinicao = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaCriteriosAplicacao = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jPanelSolucoes = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextAreaSolucaoDefinicao = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextAreaSolucaoAplicacao = new javax.swing.JTextArea();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jPanelMetodos = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextAreaMetodoDefinicao = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextAreaMetodoAplicacao = new javax.swing.JTextArea();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jPanelAvaliar = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextAreaAvaliarDefinicao = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextAreaAvaliarAplicacao = new javax.swing.JTextArea();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jPanelSelecionar = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextAreaSelecionarDeficao = new javax.swing.JTextArea();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextAreaSelecionarAplicacao = new javax.swing.JTextArea();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Guai de Gestão de Decisão - Cadastro Manual");
        setResizable(false);

        jLabel4.setText(" Propósito do processo Gerência de Decisões na Organização:");

        jTextAreaProposito.setColumns(20);
        jTextAreaProposito.setLineWrap(true);
        jTextAreaProposito.setRows(5);
        jScrollPane1.setViewportView(jTextAreaProposito);

        javax.swing.GroupLayout jPanelInformacoesGeraisLayout = new javax.swing.GroupLayout(jPanelInformacoesGerais);
        jPanelInformacoesGerais.setLayout(jPanelInformacoesGeraisLayout);
        jPanelInformacoesGeraisLayout.setHorizontalGroup(
            jPanelInformacoesGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformacoesGeraisLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelInformacoesGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelInformacoesGeraisLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 265, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelInformacoesGeraisLayout.setVerticalGroup(
            jPanelInformacoesGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInformacoesGeraisLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("<html>Inormações<br>Gerais</html>", jPanelInformacoesGerais);

        jLabel5.setText(" Diretrizes para Análise e Decisão Informação ");

        jLabel6.setText("Definição:");

        jTextAreaDiretrizDefinicao.setColumns(20);
        jTextAreaDiretrizDefinicao.setLineWrap(true);
        jTextAreaDiretrizDefinicao.setRows(5);
        jScrollPane2.setViewportView(jTextAreaDiretrizDefinicao);

        jLabel7.setText("Aplicação:");

        jTextAreaDiretrizAplicacao.setColumns(20);
        jTextAreaDiretrizAplicacao.setLineWrap(true);
        jTextAreaDiretrizAplicacao.setRows(5);
        jScrollPane3.setViewportView(jTextAreaDiretrizAplicacao);

        javax.swing.GroupLayout jPanelDiretrizesLayout = new javax.swing.GroupLayout(jPanelDiretrizes);
        jPanelDiretrizes.setLayout(jPanelDiretrizesLayout);
        jPanelDiretrizesLayout.setHorizontalGroup(
            jPanelDiretrizesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDiretrizesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDiretrizesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDiretrizesLayout.createSequentialGroup()
                        .addGroup(jPanelDiretrizesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 341, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        jPanelDiretrizesLayout.setVerticalGroup(
            jPanelDiretrizesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDiretrizesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(4, 4, 4)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Diretrizes", jPanelDiretrizes);

        jLabel9.setText("Definição:");

        jTextAreaProblemaDefinicao.setColumns(20);
        jTextAreaProblemaDefinicao.setLineWrap(true);
        jTextAreaProblemaDefinicao.setRows(5);
        jScrollPane4.setViewportView(jTextAreaProblemaDefinicao);

        jLabel10.setText("Aplicação:");

        jTextAreaProblemaAplicacao.setColumns(20);
        jTextAreaProblemaAplicacao.setLineWrap(true);
        jTextAreaProblemaAplicacao.setRows(5);
        jScrollPane5.setViewportView(jTextAreaProblemaAplicacao);

        jLabel8.setText("Problema ou questão a ser objeto de um processo formal de tomada de decisão é definido ");

        javax.swing.GroupLayout jPanelProblemaLayout = new javax.swing.GroupLayout(jPanelProblema);
        jPanelProblema.setLayout(jPanelProblemaLayout);
        jPanelProblemaLayout.setHorizontalGroup(
            jPanelProblemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProblemaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProblemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanelProblemaLayout.createSequentialGroup()
                        .addGroup(jPanelProblemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8))
                        .addGap(0, 127, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelProblemaLayout.setVerticalGroup(
            jPanelProblemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProblemaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Problema", jPanelProblema);

        jLabel11.setText("Definição:");

        jTextAreaCriteriosDefinicao.setColumns(20);
        jTextAreaCriteriosDefinicao.setLineWrap(true);
        jTextAreaCriteriosDefinicao.setRows(5);
        jScrollPane6.setViewportView(jTextAreaCriteriosDefinicao);

        jLabel12.setText("Aplicação:");

        jTextAreaCriteriosAplicacao.setColumns(20);
        jTextAreaCriteriosAplicacao.setLineWrap(true);
        jTextAreaCriteriosAplicacao.setRows(5);
        jScrollPane7.setViewportView(jTextAreaCriteriosAplicacao);

        jLabel13.setText("Estabelecer Critérios de Avaliação ");

        javax.swing.GroupLayout jPanelCriteriosLayout = new javax.swing.GroupLayout(jPanelCriterios);
        jPanelCriterios.setLayout(jPanelCriteriosLayout);
        jPanelCriteriosLayout.setHorizontalGroup(
            jPanelCriteriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCriteriosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCriteriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                    .addComponent(jScrollPane7)
                    .addGroup(jPanelCriteriosLayout.createSequentialGroup()
                        .addGroup(jPanelCriteriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelCriteriosLayout.setVerticalGroup(
            jPanelCriteriosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCriteriosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(4, 4, 4)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("<html>Critérios de<br>Avaliação</html>", jPanelCriterios);

        jLabel14.setText("Definição:");

        jTextAreaSolucaoDefinicao.setColumns(20);
        jTextAreaSolucaoDefinicao.setLineWrap(true);
        jTextAreaSolucaoDefinicao.setRows(5);
        jScrollPane8.setViewportView(jTextAreaSolucaoDefinicao);

        jLabel15.setText("Aplicação:");

        jTextAreaSolucaoAplicacao.setColumns(20);
        jTextAreaSolucaoAplicacao.setLineWrap(true);
        jTextAreaSolucaoAplicacao.setRows(5);
        jScrollPane9.setViewportView(jTextAreaSolucaoAplicacao);

        jLabel17.setText("Identificar Soluções Alternativas");

        javax.swing.GroupLayout jPanelSolucoesLayout = new javax.swing.GroupLayout(jPanelSolucoes);
        jPanelSolucoes.setLayout(jPanelSolucoesLayout);
        jPanelSolucoesLayout.setHorizontalGroup(
            jPanelSolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSolucoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                    .addComponent(jScrollPane9)
                    .addGroup(jPanelSolucoesLayout.createSequentialGroup()
                        .addGroup(jPanelSolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSolucoesLayout.setVerticalGroup(
            jPanelSolucoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSolucoesLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("<html>Soluções<br>Alternativas</html>", jPanelSolucoes);

        jLabel16.setText("Definição:");

        jTextAreaMetodoDefinicao.setColumns(20);
        jTextAreaMetodoDefinicao.setLineWrap(true);
        jTextAreaMetodoDefinicao.setRows(5);
        jScrollPane10.setViewportView(jTextAreaMetodoDefinicao);

        jLabel18.setText("Aplicação:");

        jTextAreaMetodoAplicacao.setColumns(20);
        jTextAreaMetodoAplicacao.setLineWrap(true);
        jTextAreaMetodoAplicacao.setRows(5);
        jScrollPane11.setViewportView(jTextAreaMetodoAplicacao);

        jLabel19.setText("Selecionar Métodos de Avaliação");

        javax.swing.GroupLayout jPanelMetodosLayout = new javax.swing.GroupLayout(jPanelMetodos);
        jPanelMetodos.setLayout(jPanelMetodosLayout);
        jPanelMetodosLayout.setHorizontalGroup(
            jPanelMetodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMetodosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMetodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                    .addComponent(jScrollPane11)
                    .addGroup(jPanelMetodosLayout.createSequentialGroup()
                        .addGroup(jPanelMetodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelMetodosLayout.setVerticalGroup(
            jPanelMetodosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMetodosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(4, 4, 4)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("<html>Métodos de<br>Avaliação</html>", jPanelMetodos);

        jLabel20.setText("Definição:");

        jTextAreaAvaliarDefinicao.setColumns(20);
        jTextAreaAvaliarDefinicao.setLineWrap(true);
        jTextAreaAvaliarDefinicao.setRows(5);
        jScrollPane12.setViewportView(jTextAreaAvaliarDefinicao);

        jLabel21.setText("Aplicação:");

        jTextAreaAvaliarAplicacao.setColumns(20);
        jTextAreaAvaliarAplicacao.setLineWrap(true);
        jTextAreaAvaliarAplicacao.setRows(5);
        jScrollPane13.setViewportView(jTextAreaAvaliarAplicacao);

        jLabel23.setText("Avaliar Alternativas ");

        javax.swing.GroupLayout jPanelAvaliarLayout = new javax.swing.GroupLayout(jPanelAvaliar);
        jPanelAvaliar.setLayout(jPanelAvaliarLayout);
        jPanelAvaliarLayout.setHorizontalGroup(
            jPanelAvaliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAvaliarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAvaliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                    .addComponent(jScrollPane13)
                    .addGroup(jPanelAvaliarLayout.createSequentialGroup()
                        .addGroup(jPanelAvaliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelAvaliarLayout.setVerticalGroup(
            jPanelAvaliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAvaliarLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel21)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("<html>Avaliar<br>Alternativas</html>", jPanelAvaliar);

        jLabel24.setText("Definição:");

        jTextAreaSelecionarDeficao.setColumns(20);
        jTextAreaSelecionarDeficao.setLineWrap(true);
        jTextAreaSelecionarDeficao.setRows(5);
        jScrollPane14.setViewportView(jTextAreaSelecionarDeficao);

        jLabel25.setText("Aplicação:");

        jTextAreaSelecionarAplicacao.setColumns(20);
        jTextAreaSelecionarAplicacao.setLineWrap(true);
        jTextAreaSelecionarAplicacao.setRows(5);
        jScrollPane15.setViewportView(jTextAreaSelecionarAplicacao);

        jLabel1.setText("Selecionar Soluções");

        javax.swing.GroupLayout jPanelSelecionarLayout = new javax.swing.GroupLayout(jPanelSelecionar);
        jPanelSelecionar.setLayout(jPanelSelecionarLayout);
        jPanelSelecionarLayout.setHorizontalGroup(
            jPanelSelecionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSelecionarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelSelecionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                    .addComponent(jScrollPane15)
                    .addGroup(jPanelSelecionarLayout.createSequentialGroup()
                        .addGroup(jPanelSelecionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSelecionarLayout.setVerticalGroup(
            jPanelSelecionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSelecionarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("<html>Selecionar<br>Soluções</html>", jPanelSelecionar);

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        save();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelAvaliar;
    private javax.swing.JPanel jPanelCriterios;
    private javax.swing.JPanel jPanelDiretrizes;
    private javax.swing.JPanel jPanelInformacoesGerais;
    private javax.swing.JPanel jPanelMetodos;
    private javax.swing.JPanel jPanelProblema;
    private javax.swing.JPanel jPanelSelecionar;
    private javax.swing.JPanel jPanelSolucoes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaAvaliarAplicacao;
    private javax.swing.JTextArea jTextAreaAvaliarDefinicao;
    private javax.swing.JTextArea jTextAreaCriteriosAplicacao;
    private javax.swing.JTextArea jTextAreaCriteriosDefinicao;
    private javax.swing.JTextArea jTextAreaDiretrizAplicacao;
    private javax.swing.JTextArea jTextAreaDiretrizDefinicao;
    private javax.swing.JTextArea jTextAreaMetodoAplicacao;
    private javax.swing.JTextArea jTextAreaMetodoDefinicao;
    private javax.swing.JTextArea jTextAreaProblemaAplicacao;
    private javax.swing.JTextArea jTextAreaProblemaDefinicao;
    private javax.swing.JTextArea jTextAreaProposito;
    private javax.swing.JTextArea jTextAreaSelecionarAplicacao;
    private javax.swing.JTextArea jTextAreaSelecionarDeficao;
    private javax.swing.JTextArea jTextAreaSolucaoAplicacao;
    private javax.swing.JTextArea jTextAreaSolucaoDefinicao;
    // End of variables declaration//GEN-END:variables
}
