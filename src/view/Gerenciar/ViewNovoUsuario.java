package view.Gerenciar;

import controller.ControllerUsuario;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import settings.Constant;
import util.MyDefaultTableModel;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewNovoUsuario extends javax.swing.JDialog {

    private int type;
    private Request request;
    private final ControllerUsuario controllerUsuario = new ControllerUsuario();
    private MyDefaultTableModel myDefaultTableModel;

    public ViewNovoUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        type = Constant.CREATE;
        initialiazeTable();
        this.setLocationRelativeTo(null);
    }

    public ViewNovoUsuario(java.awt.Frame parent, boolean modal, String name) {
        super(parent, modal);
        initComponents();

        type = Constant.UPDATE;
        fillFields(name);
        this.setLocationRelativeTo(null);
    }

    private boolean fieldValidate() {
        if (jTextFieldNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Campo \"Nome Completo\" é obrigatório.",
                    "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (jTextFieldLogin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Campo \"Login de Acesso\" é obrigatório.",
                    "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (getTableData().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "O Usuário precisa estar alocado em pelo menos um problema.",
                    "ERRO AO CADASTRAR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void initialiazeTable() {
        String columns[] = {"Problema", "Perfil"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false);
    }

    private void putNewRowOnTheTable(Request requestAllocate) {
        if (requestAllocate == null) {
            return;
        }

        if (controllerUsuario.hasAcessoDuplicate(getTableData(), requestAllocate)) {
            JOptionPane.showMessageDialog(null, "Usuário já foi alocado para este Problema.");
            return;
        }

        String line[] = {
            requestAllocate.getData("Problema.nome"),
            requestAllocate.getData("Perfil.nome")
        };

        myDefaultTableModel.addRow(line);
        jTableAlocacao.setModel(myDefaultTableModel);
    }

    private void fillFields(String name) {
        request = controllerUsuario.findUsuarioSelected(name);
        jTextFieldNome.setText(request.getData("Usuario.nome"));
        jTextFieldLogin.setText(request.getData("Usuario.login"));

        fillTable(name);
    }

    private void fillTable(String name) {
        initialiazeTable();

        List<Request> list = controllerUsuario.findUsuarioAcesso(name);

        for (Request request : list) {
            String line[] = {
                request.getData("Problema.nome"),
                request.getData("Perfil.nome")
            };

            myDefaultTableModel.addRow(line);
        }
        jTableAlocacao.setModel(myDefaultTableModel);
    }

    private List<Request> getTableData() {
        List<Request> requestList = new ArrayList<>();
        for (int i = 0; i < jTableAlocacao.getRowCount(); i++) {
            Map<String, String> data = new HashMap<>();
            data.put("Problema.nome", jTableAlocacao.getValueAt(i, 0).toString());
            data.put("Perfil.nome", jTableAlocacao.getValueAt(i, 1).toString());
            requestList.add(new Request(data));
        }
        return requestList;
    }

    private void openPopupMenuTable(MouseEvent evt) {
        jMenuItem.setText("remover linha");
        jPopupMenu.add(jMenuItem);
        if (SwingUtilities.isRightMouseButton(evt)) {
            if (jTableAlocacao.getSelectedRow() != -1) {
                jPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    }

    private void removeRowTable(int row) {
        myDefaultTableModel.removeRow(row);
        jTableAlocacao.setModel(myDefaultTableModel);
    }

    private void save() {
        if (!fieldValidate()) {
            return;
        }

        Map<String, String> data = new HashMap<>();
        data.put("Usuario.nome", jTextFieldNome.getText());
        data.put("Usuario.login", jTextFieldLogin.getText());

        boolean isDone = false;
        if (type == Constant.CREATE) {
            request = new Request(data);
            isDone = controllerUsuario.createUsuario(request, getTableData(), jTextFieldNome.getText());
        } else {
            data.put("Usuario.id", request.getData("Usuario.id"));
            request = new Request(data);
            isDone = controllerUsuario.updateUsuario(request, getTableData());
        }

        if (isDone) {
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso.");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Por favor, verifique se este Usuário já não foi cadastrada.",
                    "ERRO AO SALVAR", type);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu = new javax.swing.JPopupMenu();
        jMenuItem = new javax.swing.JMenuItem();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldLogin = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAlocacao = new javax.swing.JTable();
        jButtonAlocar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jMenuItem.setText("jMenuItem1");
        jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemActionPerformed(evt);
            }
        });
        jPopupMenu.add(jMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Usuário");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Alocação"));

        jTableAlocacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Problema", "Perfil"
            }
        ));
        jTableAlocacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableAlocacaoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAlocacao);

        jButtonAlocar.setText("Alocar este Usuário a um Problema");
        jButtonAlocar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlocarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAlocar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAlocar)
                .addGap(11, 11, 11))
        );

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

        jLabel1.setText("Nome Completo:");

        jLabel2.setText("Login de Acesso:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLogin)
                            .addComponent(jTextFieldNome)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAlocarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlocarActionPerformed
        ViewAlocarUsuario viewAlocarUsuario = new ViewAlocarUsuario(null, true);
        viewAlocarUsuario.setVisible(true);
        putNewRowOnTheTable(viewAlocarUsuario.getRequest());
    }//GEN-LAST:event_jButtonAlocarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        save();
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTableAlocacaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAlocacaoMousePressed
        openPopupMenuTable(evt);
    }//GEN-LAST:event_jTableAlocacaoMousePressed

    private void jMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemActionPerformed
        removeRowTable(jTableAlocacao.getSelectedRow());
    }//GEN-LAST:event_jMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlocar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAlocacao;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
