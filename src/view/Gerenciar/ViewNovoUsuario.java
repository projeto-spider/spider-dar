package view.Gerenciar;

import controller.ControllerOrganizacao;
import controller.ControllerUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import settings.Constant;
import settings.KeepData;
import util.MyDefaultTableModel;
import util.Request;
import util.swing.ComboItem;

/**
 *
 * @author Bleno Vale
 */
public class ViewNovoUsuario extends javax.swing.JDialog {

    private int type;
    private Request request;
    private String userName;
    private final ControllerUsuario controllerUsuario = new ControllerUsuario();
    private MyDefaultTableModel myDefaultTableModel;

    public ViewNovoUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        type = Constant.CREATE;
        initialiazeTable();
        fillComboboxOrganizacoes();
        this.setLocationRelativeTo(null);
    }

    public ViewNovoUsuario(java.awt.Frame parent, boolean modal, String userName) {
        super(parent, modal);
        initComponents();

        type = Constant.UPDATE;
        this.userName = userName;
        fillComboboxOrganizacoes();
        fillFields(this.userName);
        this.setLocationRelativeTo(null);
    }

    private boolean fieldValidate() {
        if (jTextFieldNome.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Campo \"Nome Completo\" é obrigatório.");
            return false;
        } else if (jTextFieldLogin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Campo \"Login de Acesso\" é obrigatório.");
            return false;
        } else if (getTableData().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "O Usuário precisa estar alocado em pelo menos um problema.");
            return false;
        } 
        
        return true;
    }

    private void initialiazeTable() {
        String columns[] = {"Problema", "Perfil"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false);
    }

    private void putNewRowInTheTable(Request requestAllocate) { 
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
        request = controllerUsuario.findUsuarioByNome(name);
        jTextFieldNome.setText(request.getData("Usuario.nome"));
        jTextFieldLogin.setText(request.getData("Usuario.login"));
    }

    private void fillComboboxOrganizacoes() {
        int userId = Integer.parseInt(KeepData.getData("Usuario.id"));
        if (userId == Constant.ID_ADMIN) {
            jComboBoxOrganizações.removeAllItems();
            jComboBoxOrganizações.addItem(new ComboItem("", "--Selecione uma Organização--"));

            List<Request> requestList = new ControllerOrganizacao().findOrganizacoesByUsuario();
            for (Request request : requestList) {
                String idOrganizacao = request.getData("Organizacao.id");
                String nomeOrganizacao = request.getData("Organizacao.nome");
                jComboBoxOrganizações.addItem(new ComboItem(idOrganizacao, nomeOrganizacao));
            }

            if (type == Constant.UPDATE) {
                int idOrg = Integer.parseInt(KeepData.getData("Organizacao.id"));
                jComboBoxOrganizações.setSelectedIndex(idOrg);
            }
        } else {
            jComboBoxOrganizações.removeAllItems();
            jComboBoxOrganizações.addItem(new ComboItem(KeepData.getData("Organizacao.id"), KeepData.getData("Organizacao.nome")));
            jComboBoxOrganizações.setEnabled(false);
        }
    }

    private void fillTable(String name, int idOrg) {
        initialiazeTable();

        List<Request> list = controllerUsuario.findAcessoByUsuario(name, idOrg);

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

        ComboItem selectedOrganizacao = (ComboItem) jComboBoxOrganizações.getSelectedItem();
        String idOrganizacao = selectedOrganizacao.getValue();
        data.put("Organizacao.id", idOrganizacao);

        boolean isDone = false;
        if (type == Constant.CREATE) {
            request = new Request(data);
            isDone = controllerUsuario.createUsuario(request, getTableData(), jTextFieldNome.getText());
        } else {
            data.put("Usuario.id", request.getData("Usuario.id"));
            request = new Request(data);
            isDone = controllerUsuario.updateUsuarioByADM(request, getTableData());
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
        jLabel3 = new javax.swing.JLabel();
        jComboBoxOrganizações = new javax.swing.JComboBox();

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

        jLabel3.setText("Organização:");

        jComboBoxOrganizações.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxOrganizações.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxOrganizaçõesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLogin)
                            .addComponent(jTextFieldNome)
                            .addComponent(jComboBoxOrganizações, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxOrganizações, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonSalvar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAlocarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlocarActionPerformed
        ComboItem selectedOrganizacao = (ComboItem) jComboBoxOrganizações.getSelectedItem();
        if (selectedOrganizacao.getLabel().equals("--Selecione uma Organização--")) {
            JOptionPane.showMessageDialog(null, "É necessário Escolher uma Organização no Combobox");
            return;
        }
        int idOrganizacao = Integer.parseInt(selectedOrganizacao.getValue());

        ViewAlocarUsuario viewAlocarUsuario = new ViewAlocarUsuario(null, true, idOrganizacao);
        viewAlocarUsuario.setVisible(true);
        putNewRowInTheTable(viewAlocarUsuario.getRequest());
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

    private void jComboBoxOrganizaçõesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxOrganizaçõesActionPerformed
        jComboBoxOrganizações.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComboItem selectedOrganizacao = (ComboItem) jComboBoxOrganizações.getSelectedItem();
                if (!selectedOrganizacao.getLabel().equals("--Selecione uma Organização--")) {
                    int idOrganizacao = Integer.parseInt(selectedOrganizacao.getValue());
                    fillTable(userName, idOrganizacao);
                }
            }
        });
    }//GEN-LAST:event_jComboBoxOrganizaçõesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlocar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox jComboBoxOrganizações;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAlocacao;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
