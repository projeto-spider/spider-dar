package view;

import com.itextpdf.text.DocumentException;
import controller.ControllerAcessar;
import view.Gerenciar.ViewNovoUsuario;
import view.Gerenciar.ViewNovaOrganizacao;
import view.Gerenciar.ViewNovoProblema;
import controller.ControllerOrganizacao;
import controller.ControllerPerfil;
import controller.ControllerProblema;
import controller.ControllerUsuario;
import controller.ControllerRelatorio;
import java.awt.Color;
import java.beans.PropertyVetoException;
import java.io.FileFilter;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import settings.KeepData;
import settings.Reminder;
import util.MyButton;
import util.Request;
import view.Gerenciar.ViewConta;
import view.Gerenciar.ViewOrganizacoes;
import view.Gerenciar.ViewProblema;
import view.Gerenciar.ViewUsuarios;
import view.Organizacional.ViewGuiaGestaoDeDecisao;
import view.Gerenciar.ViewPermissoesDePerfil;
import view.TomadaDeDecisao.ViewAlternativas;
import view.TomadaDeDecisao.ViewTarefas;
import view.TomadaDeDecisao.ViewCriterios;
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
    private final ViewAlternativas viewAlternativaDeSolucao = new ViewAlternativas();
    private final ViewCriterios viewCriterios = new ViewCriterios();
    private final ViewAvaliacao viewDecisao = new ViewAvaliacao();
    private final ViewHistorico viewHistorico = new ViewHistorico();

    private Reminder reminder;
    private Request requestPerfil;

    public ViewPrincipal() {
        initComponents();

        startViews();
        showInformation();

        reminder = new Reminder(this);
        reminder.reload();

        this.setIconImage(new ImageIcon(getClass().getResource("/resources/image/spiderGrey.png")).getImage());
        this.setLocationRelativeTo(null);
    }

    public void showInformation() {
        String nomeOrganizacao = KeepData.getData("Organizacao.nome");
        String nomeProblema = KeepData.getData("Problema.nome");
        jLabelBemvindo.setText("Bem-Vindo, " + KeepData.getData("Usuario.nome"));
        jLabelOrganizacao.setText("<html>" + nomeOrganizacao + "</html>");
        jLabelProblema.setText("<html>" + nomeProblema + "</html>");

        int id = Integer.parseInt(KeepData.getData("Usuario.id"));
        requestPerfil = new ControllerAcessar().findPerfilByUser(id);

        enabledPermitions();
//        enabledAdmin();

        changeViews(viewHome);
    }

    private void enabledAdmin() {
        if ("Administrador-spiderDAR".equals(requestPerfil.getData("Perfil.nome"))) {
            jMenuItemNovaOrganizacao.setEnabled(true);
            jMenuItemOrganizacoes.setEnabled(true);
            jMenuItemOrgEProb.setEnabled(true);
            jMenuItemConfiguracoes.setEnabled(true);
            jMenuItemNovoUsuario.setEnabled(true);
            jMenuItemUsuarios.setEnabled(true);
            jMenuItemNovoProblema.setEnabled(true);
            jMenuItemProblemas.setEnabled(true);
            jMenuItemPermissoes.setEnabled(true);
        } else {
            jMenuItemNovaOrganizacao.setEnabled(false);
            jMenuItemOrganizacoes.setEnabled(false);
            jMenuItemOrgEProb.setEnabled(false);
            jMenuItemConfiguracoes.setEnabled(false);
            jMenuItemNovoUsuario.setEnabled(false);
            jMenuItemUsuarios.setEnabled(false);
            jMenuItemNovoProblema.setEnabled(false);
            jMenuItemProblemas.setEnabled(false);
            jMenuItemPermissoes.setEnabled(false);
        }
    }

    private void enabledPermitions() {
        jMenuItemNovoProblema.setEnabled(false);
        jMenuItemProblemas.setEnabled(false);
        jMenuItemNovaOrganizacao.setEnabled(false);
        jMenuItemOrganizacoes.setEnabled(false);
        jMenuItemNovoUsuario.setEnabled(false);
        jMenuItemUsuarios.setEnabled(false);
        jMenuItemPermissoes.setEnabled(false);

        jButtonMotivacaoEObj.setEnabled(false);
        jButtonCalendario.setEnabled(false);
        jButtonAlternativas.setEnabled(false);
        jButtonCriterios.setEnabled(false);
        jButtonAvaliacao.setEnabled(false);
        jButtonHistorico.setEnabled(false);
        jButtonRelatorio.setEnabled(false);

        if (requestPerfil.getData("Perfil.nome").equals("Administrador-spiderDAR")) {
            jMenuItemNovaOrganizacao.setEnabled(true);
            jMenuItemOrganizacoes.setEnabled(true);
            jMenuItemOrgEProb.setEnabled(true);
            jMenuItemConfiguracoes.setEnabled(true);
            jMenuItemNovoUsuario.setEnabled(true);
            jMenuItemUsuarios.setEnabled(true);
            jMenuItemNovoProblema.setEnabled(true);
            jMenuItemProblemas.setEnabled(true);
            jMenuItemPermissoes.setEnabled(true);
        } else {
            List<Request> list = new ControllerPerfil().findFuncionalidadesInPerfil(requestPerfil.getData("Perfil.nome"));

            for (Request funcao : list) {
                System.out.println(">>" + funcao.getData("Funcionalidade.nome"));
                switch (funcao.getData("Funcionalidade.nome")) {
                    case "Gerenciar - Problemas":
                        jMenuItemNovoProblema.setEnabled(true);
                        jMenuItemProblemas.setEnabled(true);
                        break;
                    case "Gerenciar - Usuários":
                        jMenuItemNovoUsuario.setEnabled(true);
                        jMenuItemUsuarios.setEnabled(true);
                        break;
                    case "Gerenciar - Permissões de Perfil":
                        jMenuItemPermissoes.setEnabled(true);
                        break;
                    case "Problema - Motivação e Objetivos":
                        jButtonMotivacaoEObj.setEnabled(true);
                        break;
                    case "Problema - Tarefas":
                        jButtonCalendario.setEnabled(true);
                        break;
                    case "Problema - Alternativas de Solução":
                        jButtonAlternativas.setEnabled(true);
                        break;
                    case "Problema - Critérios de Avaliação":
                        jButtonCriterios.setEnabled(true);
                        break;
                    case "Problema - Avaliação":
                        jButtonAvaliacao.setEnabled(true);
                        break;
                    case "Problema - Histórico":
                        jButtonHistorico.setEnabled(true);
                        break;
                    case "Problema - Relatório":
                        jButtonRelatorio.setEnabled(true);
                        break;
                }
            }
        }
    }

    private void changeButtonColor(JButton jButton) {
        MyButton myButton = new MyButton(jButton);
        myButton.setBackgroundColor(new Color(65, 65, 65));
        myButton.setHoverBackgroundColor(new Color(50, 49, 49));
        myButton.setPressedBackgroundColor(new Color(50, 49, 49));

        myButton.repaint();
    }

    //Abrir o PDF na tela usando o Runtime.exec para chamar o executável do AcrobatReader
    public final void openPDF() {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler  " + "Relatório.pdf");
        } catch (Exception e) {
            System.out.println("Failed to open file ");
        }
    }

    public void desconectar() {
        reminder.killTimer();
        new ViewLogin().setVisible(true);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jDesktopPane = new javax.swing.JDesktopPane();
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
        jButtonRelatorio = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabelBemvindo = new javax.swing.JLabel();
        jButtonSelecionarOrganizacao = new javax.swing.JButton();
        jButtonConta = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArquivo = new javax.swing.JMenu();
        jMenuItemNovaOrganizacao = new javax.swing.JMenuItem();
        jMenuItemNovoProblema = new javax.swing.JMenuItem();
        jMenuItemNovoUsuario = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemDesconectar = new javax.swing.JMenuItem();
        jMenuGerenciar = new javax.swing.JMenu();
        jMenuItemOrgEProb = new javax.swing.JMenuItem();
        jMenuItemOrganizacoes = new javax.swing.JMenuItem();
        jMenuItemProblemas = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItemUsuarios = new javax.swing.JMenuItem();
        jMenuItemPermissoes = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemConta = new javax.swing.JMenuItem();
        jMenuItemConfiguracoes = new javax.swing.JMenuItem();
        jMenuOrganizacional = new javax.swing.JMenu();
        jMenuItemGuiadaGestao = new javax.swing.JMenuItem();
        jMenuSobre = new javax.swing.JMenu();
        jMenuItemSpiderGDE = new javax.swing.JMenuItem();
        jMenuItemAjuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SPIDER-DAR");

        jPanel1.setBackground(new java.awt.Color(65, 65, 65));

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 462, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonRelatorio.setBackground(new java.awt.Color(65, 65, 65));
        jButtonRelatorio.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButtonRelatorio.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/report.png"))); // NOI18N
        jButtonRelatorio.setText("RELATÓRIO");
        jButtonRelatorio.setContentAreaFilled(false);
        jButtonRelatorio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonRelatorio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButtonRelatorioStateChanged(evt);
            }
        });
        jButtonRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRelatorioActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/spiderDAR4.png"))); // NOI18N

        jLabelBemvindo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelBemvindo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBemvindo.setText("Bem-vindo(a), ADM");

        jButtonSelecionarOrganizacao.setBackground(new java.awt.Color(65, 65, 65));
        jButtonSelecionarOrganizacao.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButtonSelecionarOrganizacao.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSelecionarOrganizacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/org.png"))); // NOI18N
        jButtonSelecionarOrganizacao.setToolTipText("Selecionar Organização");
        jButtonSelecionarOrganizacao.setContentAreaFilled(false);
        jButtonSelecionarOrganizacao.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButtonSelecionarOrganizacaoStateChanged(evt);
            }
        });
        jButtonSelecionarOrganizacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelecionarOrganizacaoActionPerformed(evt);
            }
        });

        jButtonConta.setBackground(new java.awt.Color(65, 65, 65));
        jButtonConta.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButtonConta.setForeground(new java.awt.Color(255, 255, 255));
        jButtonConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/Account.png"))); // NOI18N
        jButtonConta.setToolTipText("Conta");
        jButtonConta.setContentAreaFilled(false);
        jButtonConta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jButtonContaStateChanged(evt);
            }
        });
        jButtonConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContaActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/image/spiderWhite.png"))); // NOI18N

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
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRelatorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonSelecionarOrganizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jButtonConta, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 533, Short.MAX_VALUE)
                        .addComponent(jLabelBemvindo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(8, 8, 8))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonSelecionarOrganizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jButtonConta, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelBemvindo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addGap(0, 0, 0)
                        .addComponent(jButtonRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jDesktopPane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
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

        jMenuItemNovoProblema.setText("Novo Problema");
        jMenuItemNovoProblema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovoProblemaActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemNovoProblema);

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

        jMenuItemOrgEProb.setText("Organização/Problema");
        jMenuItemOrgEProb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOrgEProbActionPerformed(evt);
            }
        });
        jMenuGerenciar.add(jMenuItemOrgEProb);

        jMenuItemOrganizacoes.setText("Organizações");
        jMenuItemOrganizacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOrganizacoesActionPerformed(evt);
            }
        });
        jMenuGerenciar.add(jMenuItemOrganizacoes);

        jMenuItemProblemas.setText("Problemas");
        jMenuItemProblemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProblemasActionPerformed(evt);
            }
        });
        jMenuGerenciar.add(jMenuItemProblemas);
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

        jMenuItemConfiguracoes.setText("Configurações");
        jMenuItemConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConfiguracoesActionPerformed(evt);
            }
        });
        jMenuGerenciar.add(jMenuItemConfiguracoes);

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

    private void jMenuItemNovoProblemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovoProblemaActionPerformed
        new ViewNovoProblema(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemNovoProblemaActionPerformed

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
        this.desconectar();
    }//GEN-LAST:event_jMenuItemDesconectarActionPerformed

    private void jMenuItemGuiadaGestaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuiadaGestaoActionPerformed
        viewGuiaGestãoDeDecisao.showViewGestaoDeDecisao();
        changeViews(viewGuiaGestãoDeDecisao);
    }//GEN-LAST:event_jMenuItemGuiadaGestaoActionPerformed

    private void jMenuItemNovaOrganizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovaOrganizacaoActionPerformed
        viewOrganizacoes.fillTable(new ControllerOrganizacao().findOrganizacoesByUsuario());
        new ViewNovaOrganizacao(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemNovaOrganizacaoActionPerformed

    private void jMenuItemOrganizacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOrganizacoesActionPerformed
        changeViews(viewOrganizacoes);
        viewOrganizacoes.fillTable(new ControllerOrganizacao().findOrganizacoes());
    }//GEN-LAST:event_jMenuItemOrganizacoesActionPerformed

    private void jMenuItemProblemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProblemasActionPerformed
        changeViews(viewTomadaDeDecisoes);
        viewTomadaDeDecisoes.listProblemasInTable(new ControllerProblema().listProblemasByIdOrganizacao(KeepData.getData("Organizacao.id")));
    }//GEN-LAST:event_jMenuItemProblemasActionPerformed

    private void jMenuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUsuariosActionPerformed
        changeViews(viewUsuarios);
        viewUsuarios.fillTable(new ControllerUsuario().findUsuarios());
    }//GEN-LAST:event_jMenuItemUsuariosActionPerformed

    private void jMenuItemOrgEProbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOrgEProbActionPerformed
        new ViewSelecionarOrganizacao(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemOrgEProbActionPerformed

    private void jMenuItemContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContaActionPerformed
        new ViewConta(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemContaActionPerformed

    private void jButtonMotivacaoEObjStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonMotivacaoEObjStateChanged
        changeButtonColor(jButtonMotivacaoEObj);
    }//GEN-LAST:event_jButtonMotivacaoEObjStateChanged

    private void jButtonMotivacaoEObjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMotivacaoEObjActionPerformed
        changeViews(viewMotivacaoEObjetivos);
        viewMotivacaoEObjetivos.showInformation();
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
        changeViews(viewCriterios);
        viewCriterios.reloadViewTarefas();
    }//GEN-LAST:event_jButtonCriteriosActionPerformed

    private void jButtonAvaliacaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonAvaliacaoStateChanged
        changeButtonColor(jButtonAvaliacao);
    }//GEN-LAST:event_jButtonAvaliacaoStateChanged

    private void jButtonAvaliacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAvaliacaoActionPerformed
        changeViews(viewDecisao);
        viewDecisao.showAvaliacao();
    }//GEN-LAST:event_jButtonAvaliacaoActionPerformed

    private void jButtonHistoricoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonHistoricoStateChanged
        changeButtonColor(jButtonHistorico);
    }//GEN-LAST:event_jButtonHistoricoStateChanged

    private void jButtonHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistoricoActionPerformed
        changeViews(viewHistorico);
        viewHistorico.reloadViewHistorico();
    }//GEN-LAST:event_jButtonHistoricoActionPerformed

    private void jMenuItemConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConfiguracoesActionPerformed
        new ViewConfiguracoes(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemConfiguracoesActionPerformed

    private void jButtonRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRelatorioActionPerformed
        ControllerRelatorio relatorio = new ControllerRelatorio();
//        try {
//            relatorio.gerarRelatorio();
//
//            JFileChooser jFileChooser = new JFileChooser();
//
//            //seta para selecionar apenas arquivos
//            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//
//            //mostra janela para salvar
//            int acao = jFileChooser.showSaveDialog(null);
//
//            //executa acao conforme opcao selecionada
//            if (acao == JFileChooser.APPROVE_OPTION) {
//                //escolheu arquivo
//                System.out.println(jFileChooser.getSelectedFile().getAbsolutePath());
//            } else if (acao == JFileChooser.CANCEL_OPTION) {
//                //apertou botao cancelar
//            } else if (acao == JFileChooser.ERROR_OPTION) {
//                //outra opcao
//            }
//
//        } catch (IOException ex) {
//        } catch (DocumentException ex) {
//        }

        openPDF();
    }//GEN-LAST:event_jButtonRelatorioActionPerformed

    private void jButtonRelatorioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonRelatorioStateChanged
        changeButtonColor(jButtonRelatorio);
    }//GEN-LAST:event_jButtonRelatorioStateChanged

    private void jButtonSelecionarOrganizacaoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonSelecionarOrganizacaoStateChanged
        changeButtonColor(jButtonSelecionarOrganizacao);
    }//GEN-LAST:event_jButtonSelecionarOrganizacaoStateChanged

    private void jButtonSelecionarOrganizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelecionarOrganizacaoActionPerformed
        new ViewSelecionarOrganizacao(null, true, this).setVisible(true);
    }//GEN-LAST:event_jButtonSelecionarOrganizacaoActionPerformed

    private void jButtonContaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jButtonContaStateChanged
        changeButtonColor(jButtonConta);
    }//GEN-LAST:event_jButtonContaStateChanged

    private void jButtonContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContaActionPerformed
        new ViewConta(null, true).setVisible(true);
    }//GEN-LAST:event_jButtonContaActionPerformed

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
        jDesktopPane.add(viewCriterios);
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
        viewCriterios.setVisible(false);
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
    private javax.swing.JButton jButtonAlternativas;
    private javax.swing.JButton jButtonAvaliacao;
    private javax.swing.JButton jButtonCalendario;
    private javax.swing.JButton jButtonConta;
    private javax.swing.JButton jButtonCriterios;
    private javax.swing.JButton jButtonHistorico;
    private javax.swing.JButton jButtonMotivacaoEObj;
    private javax.swing.JButton jButtonRelatorio;
    private javax.swing.JButton jButtonSelecionarOrganizacao;
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelBemvindo;
    private javax.swing.JLabel jLabelOrganizacao;
    private javax.swing.JLabel jLabelProblema;
    private javax.swing.JMenu jMenuArquivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuGerenciar;
    private javax.swing.JMenuItem jMenuItemAjuda;
    private javax.swing.JMenuItem jMenuItemConfiguracoes;
    private javax.swing.JMenuItem jMenuItemConta;
    private javax.swing.JMenuItem jMenuItemDesconectar;
    private javax.swing.JMenuItem jMenuItemGuiadaGestao;
    private javax.swing.JMenuItem jMenuItemNovaOrganizacao;
    private javax.swing.JMenuItem jMenuItemNovoProblema;
    private javax.swing.JMenuItem jMenuItemNovoUsuario;
    private javax.swing.JMenuItem jMenuItemOrgEProb;
    private javax.swing.JMenuItem jMenuItemOrganizacoes;
    private javax.swing.JMenuItem jMenuItemPermissoes;
    private javax.swing.JMenuItem jMenuItemProblemas;
    private javax.swing.JMenuItem jMenuItemSpiderGDE;
    private javax.swing.JMenuItem jMenuItemUsuarios;
    private javax.swing.JMenu jMenuOrganizacional;
    private javax.swing.JMenu jMenuSobre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    // End of variables declaration//GEN-END:variables
}
