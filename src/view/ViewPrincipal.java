package view;

import view.Gerenciar.ViewNovoUsuario;
import view.Gerenciar.ViewNovaOrganizacao;
import view.Gerenciar.ViewNovoProblemaDialog;
import controller.ControllerOrganizacao;
import controller.ControllerPerfil;
import controller.ControllerProblema;
import controller.ControllerUsuario;
import java.awt.Color;
import java.beans.PropertyVetoException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import settings.KeepData;
import settings.Reminder;
import util.MyButton;
import view.Gerenciar.ViewConta;
import view.Gerenciar.ViewOrganizacoes;
import view.Gerenciar.ViewProblema;
import view.Gerenciar.ViewUsuarios;
import view.Organizacional.ViewGuiaGestaoDeDecisao;
import view.Gerenciar.ViewPermissoesDePerfil;
import view.TomadaDeDecisao.ViewAlternativaDeSolucao;
import view.TomadaDeDecisao.ViewTarefas;
import view.TomadaDeDecisao.ViewCriteriosDeAvaliacao;
import view.TomadaDeDecisao.ViewAvaliacao;
import view.TomadaDeDecisao.ViewHistorico;
import view.TomadaDeDecisao.ViewMotivacaoEObjetivos;

/**
 *
 * @author Bleno Vale
 */
public class ViewPrincipal extends javax.swing.JFrame {

    //View
    private final ViewHome viewHome = new ViewHome();

    //View.Organizacional
    private final ViewPermissoesDePerfil viewPermissoesDePerfil = new ViewPermissoesDePerfil();
    private final ViewGuiaGestaoDeDecisao viewGuiaGestãoDeDecisao = new ViewGuiaGestaoDeDecisao();

    //View.Gerenciar
    private final ViewOrganizacoes viewOrganizacoes = new ViewOrganizacoes();
    private final ViewProblema viewTomadaDeDecisoes = new ViewProblema();
    private final ViewUsuarios viewUsuarios = new ViewUsuarios();

    //View.TomadaDeDecisao
    private final ViewMotivacaoEObjetivos viewMotivacaoEObjetivos = new ViewMotivacaoEObjetivos();
    private final ViewTarefas viewTarefas = new ViewTarefas();
    private final ViewAlternativaDeSolucao viewAlternativaDeSolucao = new ViewAlternativaDeSolucao();
    private final ViewCriteriosDeAvaliacao viewCriteriosDeAvaliacao = new ViewCriteriosDeAvaliacao();
    private final ViewAvaliacao viewDecisao = new ViewAvaliacao();
    private final ViewHistorico viewHistorico = new ViewHistorico();

    private Reminder reminder;

    public ViewPrincipal() {
        initComponents();

        startViews();
        showInformation();

        reminder = new Reminder(this);
        reminder.reload();

        this.setLocationRelativeTo(null);
    }

    public void showInformation() {
        String nomeOrganizacao = KeepData.getData("Organizacao.nome");
        String nomeProblema = KeepData.getData("Problema.nome");

//        String nomeOrganizacaoLabel = (nomeOrganizacao.isEmpty() || nomeOrganizacao == null) ? "-" : nomeOrganizacao;
//        String nomeProblemaLabel = (nomeProblema.isEmpty() || nomeProblema == null) ? "-" : nomeProblema;
        jLabelBemvindo.setText("Bem-Vindo, " + KeepData.getData("Usuario.nome"));
        jLabelOrganizacao.setText("<html>" + nomeOrganizacao + "</html>");
        jLabelProblema.setText("<html>" + nomeProblema + "</html>");

        changeViews(viewHome);
    }

    private void changeButtonColor(JButton jButton) {
        MyButton myButton = new MyButton(jButton);
        myButton.setBackgroundColor(new Color(65, 65, 65));
        myButton.setHoverBackgroundColor(new Color(50, 49, 49));
        myButton.setPressedBackgroundColor(new Color(50, 49, 49));

        myButton.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelBemvindo = new javax.swing.JLabel();
        jDesktopPane = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButtonMotivacaoEObj = new javax.swing.JButton();
        jButtonCalendario = new javax.swing.JButton();
        jButtonAlternativas = new javax.swing.JButton();
        jButtonCriterios = new javax.swing.JButton();
        jButtonAvaliacao = new javax.swing.JButton();
        jButtonHistorico = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelOrganizacao = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelProblema = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArquivo = new javax.swing.JMenu();
        jMenuItemNovaOrganizacao = new javax.swing.JMenuItem();
        jMenuItemNovaDecisao = new javax.swing.JMenuItem();
        jMenuItemNovoUsuario = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemDesconectar = new javax.swing.JMenuItem();
        jMenuGerenciar = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemDecisoes = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItemUsuarios = new javax.swing.JMenuItem();
        jMenuItemPermissoes = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemConta = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuOrganizacional = new javax.swing.JMenu();
        jMenuItemGuiadaGestao = new javax.swing.JMenuItem();
        jMenuSobre = new javax.swing.JMenu();
        jMenuItemSpiderGDE = new javax.swing.JMenuItem();
        jMenuItemAjuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SPIDER-DAR");

        jPanel1.setBackground(new java.awt.Color(65, 65, 65));

        jLabelBemvindo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelBemvindo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBemvindo.setText("Bem-vindo(a), ADM");

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );

        jButton1.setText("<html>Selecionar<br>Organização</html>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(683, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jButton1)
                .addGap(8, 8, 8))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Projeto SPIDER");

        jButtonMotivacaoEObj.setBackground(new java.awt.Color(65, 65, 65));
        jButtonMotivacaoEObj.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButtonMotivacaoEObj.setForeground(new java.awt.Color(255, 255, 255));
        jButtonMotivacaoEObj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/goal.png"))); // NOI18N
        jButtonMotivacaoEObj.setText("MOTIVAÇÃO E OBJETIVOS");
        jButtonMotivacaoEObj.setContentAreaFilled(false);
        jButtonMotivacaoEObj.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonMotivacaoEObj.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButtonMotivacaoEObjStateChanged(evt);
            }
        });
        jButtonMotivacaoEObj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMotivacaoEObjActionPerformed(evt);
            }
        });

        jButtonCalendario.setBackground(new java.awt.Color(65, 65, 65));
        jButtonCalendario.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButtonCalendario.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/task.png"))); // NOI18N
        jButtonCalendario.setText("TAREFAS");
        jButtonCalendario.setContentAreaFilled(false);
        jButtonCalendario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonCalendario.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButtonCalendarioStateChanged(evt);
            }
        });
        jButtonCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalendarioActionPerformed(evt);
            }
        });

        jButtonAlternativas.setBackground(new java.awt.Color(65, 65, 65));
        jButtonAlternativas.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButtonAlternativas.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAlternativas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/alternatives.png"))); // NOI18N
        jButtonAlternativas.setText("ALTERNATIVAS DE SOLUÇÃO");
        jButtonAlternativas.setContentAreaFilled(false);
        jButtonAlternativas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAlternativas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButtonAlternativasStateChanged(evt);
            }
        });
        jButtonAlternativas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlternativasActionPerformed(evt);
            }
        });

        jButtonCriterios.setBackground(new java.awt.Color(65, 65, 65));
        jButtonCriterios.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButtonCriterios.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCriterios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/criteria.png"))); // NOI18N
        jButtonCriterios.setText("CRITÉRIOS DE AVALIAÇÃO");
        jButtonCriterios.setContentAreaFilled(false);
        jButtonCriterios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonCriterios.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButtonCriteriosStateChanged(evt);
            }
        });
        jButtonCriterios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCriteriosActionPerformed(evt);
            }
        });

        jButtonAvaliacao.setBackground(new java.awt.Color(65, 65, 65));
        jButtonAvaliacao.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButtonAvaliacao.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAvaliacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/evaluation.png"))); // NOI18N
        jButtonAvaliacao.setText("AVALIAÇÃO");
        jButtonAvaliacao.setContentAreaFilled(false);
        jButtonAvaliacao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAvaliacao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButtonAvaliacaoStateChanged(evt);
            }
        });
        jButtonAvaliacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAvaliacaoActionPerformed(evt);
            }
        });

        jButtonHistorico.setBackground(new java.awt.Color(65, 65, 65));
        jButtonHistorico.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButtonHistorico.setForeground(new java.awt.Color(255, 255, 255));
        jButtonHistorico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/historic.png"))); // NOI18N
        jButtonHistorico.setText("HISTÓRICO");
        jButtonHistorico.setContentAreaFilled(false);
        jButtonHistorico.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonHistorico.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButtonHistoricoStateChanged(evt);
            }
        });
        jButtonHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistoricoActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(54, 54, 54));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Organização:");

        jLabelOrganizacao.setForeground(new java.awt.Color(255, 255, 255));
        jLabelOrganizacao.setText("Desenvolvimento");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Problema:");

        jLabelProblema.setForeground(new java.awt.Color(255, 255, 255));
        jLabelProblema.setText("nome Problema");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelOrganizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabelProblema, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelOrganizacao)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelProblema)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonMotivacaoEObj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCalendario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCriterios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAlternativas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAvaliacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabelBemvindo))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBemvindo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jDesktopPane)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButtonMotivacaoEObj, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButtonCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButtonAlternativas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButtonCriterios, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButtonAvaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButtonHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenuBar1.setBorderPainted(false);

        jMenuArquivo.setText("Inicio");

        jMenuItemNovaOrganizacao.setText("Nova Organização");
        jMenuItemNovaOrganizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovaOrganizacaoActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemNovaOrganizacao);

        jMenuItemNovaDecisao.setText("Novo Problema");
        jMenuItemNovaDecisao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovaDecisaoActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemNovaDecisao);

        jMenuItemNovoUsuario.setText("Novo Usuário");
        jMenuItemNovoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovoUsuarioActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemNovoUsuario);
        jMenuArquivo.add(jSeparator1);

        jMenuItemDesconectar.setText("Desconectar");
        jMenuItemDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDesconectarActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemDesconectar);

        jMenuBar1.add(jMenuArquivo);

        jMenuGerenciar.setText("Gerenciar");

        jMenuItem2.setText("Organização/Tomada");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuGerenciar.add(jMenuItem2);

        jMenuItem1.setText("Organizações");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuGerenciar.add(jMenuItem1);

        jMenuItemDecisoes.setText("Problemas");
        jMenuItemDecisoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDecisoesActionPerformed(evt);
            }
        });
        jMenuGerenciar.add(jMenuItemDecisoes);
        jMenuGerenciar.add(jSeparator3);

        jMenuItemUsuarios.setText("Usuários");
        jMenuItemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUsuariosActionPerformed(evt);
            }
        });
        jMenuGerenciar.add(jMenuItemUsuarios);

        jMenuItemPermissoes.setText("Permissões de Perfil");
        jMenuItemPermissoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPermissoesActionPerformed(evt);
            }
        });
        jMenuGerenciar.add(jMenuItemPermissoes);
        jMenuGerenciar.add(jSeparator2);

        jMenuItemConta.setText("Conta");
        jMenuItemConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContaActionPerformed(evt);
            }
        });
        jMenuGerenciar.add(jMenuItemConta);

        jMenuItem3.setText("Configurações");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuGerenciar.add(jMenuItem3);

        jMenuBar1.add(jMenuGerenciar);

        jMenuOrganizacional.setText("Organizacional");

        jMenuItemGuiadaGestao.setText("Guia da Gestão de Decisão");
        jMenuItemGuiadaGestao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGuiadaGestaoActionPerformed(evt);
            }
        });
        jMenuOrganizacional.add(jMenuItemGuiadaGestao);

        jMenuBar1.add(jMenuOrganizacional);

        jMenuSobre.setText("Sobre");

        jMenuItemSpiderGDE.setText("SPIDER - GDE");
        jMenuSobre.add(jMenuItemSpiderGDE);

        jMenuItemAjuda.setText("Ajuda");
        jMenuSobre.add(jMenuItemAjuda);

        jMenuBar1.add(jMenuSobre);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemNovaDecisaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovaDecisaoActionPerformed
        new ViewNovoProblemaDialog(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemNovaDecisaoActionPerformed

    private void jMenuItemNovoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovoUsuarioActionPerformed
        new ViewNovoUsuario(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemNovoUsuarioActionPerformed

    private void jMenuItemPermissoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPermissoesActionPerformed
        int idOrg = Integer.parseInt(KeepData.getData("Organizacao.id"));
        viewPermissoesDePerfil.fillTable(new ControllerPerfil().getPerfisByIdOrganizacao(idOrg));
        viewPermissoesDePerfil.clearLists();
        changeViews(viewPermissoesDePerfil);
    }//GEN-LAST:event_jMenuItemPermissoesActionPerformed

    private void jMenuItemDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDesconectarActionPerformed
        reminder.killTimer();
        new ViewLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemDesconectarActionPerformed

    private void jMenuItemGuiadaGestaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuiadaGestaoActionPerformed
        viewGuiaGestãoDeDecisao.showViewGestaoDeDecisao();
        changeViews(viewGuiaGestãoDeDecisao);
    }//GEN-LAST:event_jMenuItemGuiadaGestaoActionPerformed

    private void jMenuItemNovaOrganizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovaOrganizacaoActionPerformed
        viewOrganizacoes.fillTable(new ControllerOrganizacao().findOrganizacoesByUsuario());
        new ViewNovaOrganizacao(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemNovaOrganizacaoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        changeViews(viewOrganizacoes);
        viewOrganizacoes.fillTable(new ControllerOrganizacao().findOrganizacoes());
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemDecisoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDecisoesActionPerformed
        changeViews(viewTomadaDeDecisoes);
        viewTomadaDeDecisoes.listProblemasInTable(new ControllerProblema().listProblemasByIdOrganizacao(KeepData.getData("Organizacao.id")));
    }//GEN-LAST:event_jMenuItemDecisoesActionPerformed

    private void jMenuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUsuariosActionPerformed
        changeViews(viewUsuarios);
        viewUsuarios.fillTable(new ControllerUsuario().findUsuarios());
    }//GEN-LAST:event_jMenuItemUsuariosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new ViewSelecionarOrganizacao(null, true, this).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new ViewSelecionarOrganizacao(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItemContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContaActionPerformed
        new ViewConta(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemContaActionPerformed

    private void jButtonMotivacaoEObjStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonMotivacaoEObjStateChanged
        changeButtonColor(jButtonMotivacaoEObj);
    }//GEN-LAST:event_jButtonMotivacaoEObjStateChanged

    private void jButtonMotivacaoEObjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMotivacaoEObjActionPerformed
        changeViews(viewMotivacaoEObjetivos);
    }//GEN-LAST:event_jButtonMotivacaoEObjActionPerformed

    private void jButtonCalendarioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonCalendarioStateChanged
        changeButtonColor(jButtonCalendario);
    }//GEN-LAST:event_jButtonCalendarioStateChanged

    private void jButtonCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalendarioActionPerformed
        changeViews(viewTarefas);
        viewTarefas.reloadViewTarefas();
    }//GEN-LAST:event_jButtonCalendarioActionPerformed

    private void jButtonAlternativasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonAlternativasStateChanged
        changeButtonColor(jButtonAlternativas);
    }//GEN-LAST:event_jButtonAlternativasStateChanged

    private void jButtonAlternativasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlternativasActionPerformed
        changeViews(viewAlternativaDeSolucao);
        viewAlternativaDeSolucao.showViewAlternativaDeSolucao();
    }//GEN-LAST:event_jButtonAlternativasActionPerformed

    private void jButtonCriteriosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonCriteriosStateChanged
        changeButtonColor(jButtonCriterios);
    }//GEN-LAST:event_jButtonCriteriosStateChanged

    private void jButtonCriteriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCriteriosActionPerformed
        changeViews(viewCriteriosDeAvaliacao);
    }//GEN-LAST:event_jButtonCriteriosActionPerformed

    private void jButtonAvaliacaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonAvaliacaoStateChanged
        changeButtonColor(jButtonAvaliacao);
    }//GEN-LAST:event_jButtonAvaliacaoStateChanged

    private void jButtonAvaliacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAvaliacaoActionPerformed
        changeViews(viewDecisao);
    }//GEN-LAST:event_jButtonAvaliacaoActionPerformed

    private void jButtonHistoricoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonHistoricoStateChanged
        changeButtonColor(jButtonHistorico);
    }//GEN-LAST:event_jButtonHistoricoStateChanged

    private void jButtonHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistoricoActionPerformed
        changeViews(viewHistorico);
        viewHistorico.showViewHistorico();
    }//GEN-LAST:event_jButtonHistoricoActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new ViewConfiguracoes(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    public void reload() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (viewUsuarios.isVisible()) {
                    viewUsuarios.fillTable(new ControllerUsuario().findUsuarios());
                    System.out.println(">>>>Funfando!!");
                } else {
                    timer.cancel();
                }
            }
        }, 0, 10 * 1000);
    }

    private void startViews() {
        jDesktopPane.add(viewHome);
        jDesktopPane.add(viewPermissoesDePerfil);
        jDesktopPane.add(viewGuiaGestãoDeDecisao);
        jDesktopPane.add(viewOrganizacoes);
        jDesktopPane.add(viewTomadaDeDecisoes);
        jDesktopPane.add(viewUsuarios);
        jDesktopPane.add(viewMotivacaoEObjetivos);
        jDesktopPane.add(viewTarefas);
        jDesktopPane.add(viewAlternativaDeSolucao);
        jDesktopPane.add(viewCriteriosDeAvaliacao);
        jDesktopPane.add(viewDecisao);
        jDesktopPane.add(viewHistorico);
    }

    private void changeViews(JInternalFrame tela) {
        viewHome.setVisible(false);
        viewPermissoesDePerfil.setVisible(false);
        viewGuiaGestãoDeDecisao.setVisible(false);
        viewOrganizacoes.setVisible(false);
        viewTomadaDeDecisoes.setVisible(false);
        viewUsuarios.setVisible(false);
        viewMotivacaoEObjetivos.setVisible(false);
        viewTarefas.setVisible(false);
        viewAlternativaDeSolucao.setVisible(false);
        viewCriteriosDeAvaliacao.setVisible(false);
        viewDecisao.setVisible(false);
        viewHistorico.setVisible(false);

        if (tela != null) {
            tela.setVisible(true);
            try {
                tela.setMaximum(true);
            } catch (PropertyVetoException ex) {
                System.err.println(" Exception maximizar internal\n " + ex);
            }
        }
    }

//    public static void main(String args[]) {
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ViewPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ViewPrincipal().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAlternativas;
    private javax.swing.JButton jButtonAvaliacao;
    private javax.swing.JButton jButtonCalendario;
    private javax.swing.JButton jButtonCriterios;
    private javax.swing.JButton jButtonHistorico;
    private javax.swing.JButton jButtonMotivacaoEObj;
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelBemvindo;
    private javax.swing.JLabel jLabelOrganizacao;
    private javax.swing.JLabel jLabelProblema;
    private javax.swing.JMenu jMenuArquivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuGerenciar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemAjuda;
    private javax.swing.JMenuItem jMenuItemConta;
    private javax.swing.JMenuItem jMenuItemDecisoes;
    private javax.swing.JMenuItem jMenuItemDesconectar;
    private javax.swing.JMenuItem jMenuItemGuiadaGestao;
    private javax.swing.JMenuItem jMenuItemNovaDecisao;
    private javax.swing.JMenuItem jMenuItemNovaOrganizacao;
    private javax.swing.JMenuItem jMenuItemNovoUsuario;
    private javax.swing.JMenuItem jMenuItemPermissoes;
    private javax.swing.JMenuItem jMenuItemSpiderGDE;
    private javax.swing.JMenuItem jMenuItemUsuarios;
    private javax.swing.JMenu jMenuOrganizacional;
    private javax.swing.JMenu jMenuSobre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
