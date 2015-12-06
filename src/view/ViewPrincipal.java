package view;

import controller.ControllerOrganizacao;
import java.beans.PropertyVetoException;
import javax.swing.JInternalFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import view.Gerenciar.ViewOrganizacoes;
import view.Gerenciar.ViewTomadaDeDecisoes;
import view.Gerenciar.ViewUsuarios;
import view.Organizacional.ViewGuiaGestaoDeDecisao;
import view.Organizacional.ViewPermissoesDePerfil;
import view.TomadaDeDecisao.ViewAlternativaDeSolucao;
import view.TomadaDeDecisao.ViewCalendario;
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
    private final ViewTomadaDeDecisoes viewTomadaDeDecisoes = new ViewTomadaDeDecisoes();
    private final ViewUsuarios viewUsuarios = new ViewUsuarios();

    //View.TomadaDeDecisao
    private final ViewMotivacaoEObjetivos viewMotivacaoEObjetivos = new ViewMotivacaoEObjetivos();
    private final ViewCalendario viewCalendario = new ViewCalendario();
    private final ViewAlternativaDeSolucao viewAlternativaDeSolucao = new ViewAlternativaDeSolucao();
    private final ViewCriteriosDeAvaliacao viewCriteriosDeAvaliacao = new ViewCriteriosDeAvaliacao();
    private final ViewAvaliacao viewDecisao = new ViewAvaliacao();
    private final ViewHistorico viewHistorico = new ViewHistorico();

    public ViewPrincipal() {
        initComponents();

        iniciaTelas();
        trocaTela(viewHome);
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree = new javax.swing.JTree();
        jDesktopPane = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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
        jMenuOrganizacional = new javax.swing.JMenu();
        jMenuItemGuiadaGestao = new javax.swing.JMenuItem();
        jMenuSobre = new javax.swing.JMenu();
        jMenuItemSpiderGDE = new javax.swing.JMenuItem();
        jMenuItemAjuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SPIDER-GDE");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bem-vindo(a), ADM");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Funcionalidades");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Organização");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Guia de Gestão de Decisão");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Tomada de Decisão");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Motivação e Objetivos");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Calendário");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Alternativas de Solução");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Critérios de Avaliação");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Avaliação");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Histórico");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTree);

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

        jLabel3.setText("Organização: Desenvolvimento");

        jLabel4.setText("Tomada de decisão: nome Tomada");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 497, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton1)))
                .addGap(8, 8, 8))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Projeto SPIDER");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(56, 56, 56)))
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

        jMenuItemNovaDecisao.setText("Nova Tomada de Decisão");
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

        jMenuItemDecisoes.setText("Tomadas de Decisões");
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
        jMenuGerenciar.add(jMenuItemConta);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemNovaDecisaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovaDecisaoActionPerformed
        new ViewNovaTomadaDialog(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemNovaDecisaoActionPerformed

    private void jMenuItemNovoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovoUsuarioActionPerformed
        new ViewNovoUsuario(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemNovoUsuarioActionPerformed

    private void jMenuItemPermissoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPermissoesActionPerformed
        trocaTela(viewPermissoesDePerfil);
    }//GEN-LAST:event_jMenuItemPermissoesActionPerformed

    private void jMenuItemDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDesconectarActionPerformed
        new ViewLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItemDesconectarActionPerformed

    private void jMenuItemGuiadaGestaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuiadaGestaoActionPerformed
        trocaTela(viewGuiaGestãoDeDecisao);
    }//GEN-LAST:event_jMenuItemGuiadaGestaoActionPerformed

    private void jMenuItemNovaOrganizacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovaOrganizacaoActionPerformed
        new ViewNovaOrganizacao(null, true).setVisible(true);
        viewOrganizacoes.fillTable(new ControllerOrganizacao().findOrganizacoes());
    }//GEN-LAST:event_jMenuItemNovaOrganizacaoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        trocaTela(viewOrganizacoes);
        viewOrganizacoes.fillTable(new ControllerOrganizacao().findOrganizacoes());
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItemDecisoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDecisoesActionPerformed
        trocaTela(viewTomadaDeDecisoes);
    }//GEN-LAST:event_jMenuItemDecisoesActionPerformed

    private void jMenuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUsuariosActionPerformed
        trocaTela(viewUsuarios);
    }//GEN-LAST:event_jMenuItemUsuariosActionPerformed

    private void jTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeMouseClicked
        eventosDaArvore();
    }//GEN-LAST:event_jTreeMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        new ViewSelecionarOrganizacao(null, true).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new ViewSelecionarOrganizacao(null, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void iniciaTelas() {
        jDesktopPane.add(viewHome);
        jDesktopPane.add(viewPermissoesDePerfil);
        jDesktopPane.add(viewGuiaGestãoDeDecisao);
        jDesktopPane.add(viewOrganizacoes);
        jDesktopPane.add(viewTomadaDeDecisoes);
        jDesktopPane.add(viewUsuarios);
        jDesktopPane.add(viewMotivacaoEObjetivos);
        jDesktopPane.add(viewCalendario);
        jDesktopPane.add(viewAlternativaDeSolucao);
        jDesktopPane.add(viewCriteriosDeAvaliacao);
        jDesktopPane.add(viewDecisao);
        jDesktopPane.add(viewHistorico);
    }

    private void trocaTela(JInternalFrame tela) {
        viewHome.setVisible(false);
        viewPermissoesDePerfil.setVisible(false);
        viewGuiaGestãoDeDecisao.setVisible(false);
        viewOrganizacoes.setVisible(false);
        viewTomadaDeDecisoes.setVisible(false);
        viewUsuarios.setVisible(false);
        viewMotivacaoEObjetivos.setVisible(false);
        viewCalendario.setVisible(false);
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

    private void eventosDaArvore() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();

        if (node == null) {
            return;
        }
        if (!node.isLeaf()) {
            return;
        }

        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        String nodeFilho = node.toString();
        String nodePai = parent.toString();

        if (nodePai.endsWith("Organização")) {
            if (nodeFilho.equals("Guia de Gestão de Decisão")) {
                trocaTela(viewGuiaGestãoDeDecisao);
            }
        } else if (nodePai.endsWith("Tomada de Decisão")) {
            if (nodeFilho.equals("Motivação e Objetivos")) {
                trocaTela(viewMotivacaoEObjetivos);
            } else if (nodeFilho.equals("Calendário")) {
                trocaTela(viewCalendario);
            } else if (nodeFilho.equals("Alternativas de Solução")) {
                trocaTela(viewAlternativaDeSolucao);
            } else if (nodeFilho.equals("Critérios de Avaliação")) {
                trocaTela(viewCriteriosDeAvaliacao);
            } else if (nodeFilho.equals("Avaliação")) {
                trocaTela(viewDecisao);
            } else if (nodeFilho.equals("Histórico")) {
                trocaTela(viewHistorico);
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
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenuArquivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuGerenciar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTree jTree;
    // End of variables declaration//GEN-END:variables
}
