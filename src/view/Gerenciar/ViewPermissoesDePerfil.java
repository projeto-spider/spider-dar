package view.Gerenciar;

import controller.ControllerPerfil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import util.Internal;
import util.MyCellRenderer;
import util.MyDefaultTableModel;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewPermissoesDePerfil extends javax.swing.JInternalFrame {

    private MyDefaultTableModel myDefaultTableModel;
    private DefaultListModel listModelInPerfil;
    private DefaultListModel listModelOutPerfil;
    private final ControllerPerfil controllerPerfil = new ControllerPerfil();

    public ViewPermissoesDePerfil() {
        initComponents();

        Internal.retiraBorda(this);
    }

    /**
     * Preenche a tabela com os Perfis cadastradas anteriormente.
     *
     * @param requestList
     */
    public void fillTable(List<Request> requestList) {
        String columns[] = {"Perfil", "Criada em", "Última modificação"};
        myDefaultTableModel = new MyDefaultTableModel(columns, 0, false);

        for (Request request : requestList) {
            String line[] = {
                request.getData("Perfil.nome"),
                request.getData("Perfil.created"),
                request.getData("Perfil.modified")
            };
            myDefaultTableModel.addRow(line);
        }
        jTablePerfis.setModel(myDefaultTableModel);
        jTablePerfis.setDefaultRenderer(Object.class, new MyCellRenderer());
    }

    private String getPerfilSelected() {
        int row = jTablePerfis.getSelectedRow();
        return jTablePerfis.getValueAt(row, 0).toString();
    }

    public void clearLists() {
        listModelInPerfil = new DefaultListModel();
        listModelOutPerfil = new DefaultListModel();
        jListFuncionalidades.setModel(listModelOutPerfil);
        jListFuncionalidadesDoPerfil.setModel(listModelInPerfil);
    }

    private void fillLists() {
        listModelInPerfil = new DefaultListModel();
        listModelOutPerfil = new DefaultListModel();

        List<Request> list = controllerPerfil.findFuncionalidadesByPerfil(getPerfilSelected());

        for (Request request : list) {
            if (request.getData("Funcionalidade.dentro.nome") != null) {
                listModelInPerfil.addElement(request.getData("Funcionalidade.dentro.nome"));
            } else if (request.getData("Funcionalidade.fora.nome") != null) {
                listModelOutPerfil.addElement(request.getData("Funcionalidade.fora.nome"));
            }

        }

        jListFuncionalidades.setModel(listModelOutPerfil);
        jListFuncionalidadesDoPerfil.setModel(listModelInPerfil);
    }

    /**
     * Chamado quando o botão editar e pressionado.
     */
    private void editarButtonIsPressed() {
        int index = jTablePerfis.getSelectedRow();
        if (index > -1) {
            new ViewNovoPerfil(null, true, jTablePerfis.getValueAt(index, 0).toString()).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
        }
    }

    private void removePerfil() {
        int Result = JOptionPane.showConfirmDialog(null, "Deseja Excluir este perfil?", "EXCLUIR", JOptionPane.YES_NO_OPTION);

        if (Result == JOptionPane.YES_OPTION) {
            boolean isDone = false;
            int index = jTablePerfis.getSelectedRow();
            isDone = controllerPerfil.deletePerfil(jTablePerfis.getValueAt(index, 0).toString());

            if (isDone) {
                JOptionPane.showMessageDialog(null, "Excluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null,
                        "Há usuários que possuem este perfil."
                        + "\nRetire este perfil desses \"Usuários\" antes de excluí-lo.");
            }
        }
    }

    private void includeFuncionalidade() {
        int index = jListFuncionalidades.getSelectedIndex();
        if (index != -1) {
            listModelInPerfil.addElement(listModelOutPerfil.getElementAt(index).toString());
            listModelOutPerfil.remove(index);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma Funcionalidade.");
        }
    }

    private void removeFuncionalidade() {
        int index = jListFuncionalidadesDoPerfil.getSelectedIndex();
        if (index != -1) {
            listModelOutPerfil.addElement(listModelInPerfil.getElementAt(index).toString());
            listModelInPerfil.remove(index);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma Funcionalidade do Perfil.");
        }
    }

    private void save() {
        Map<String, String> data = new HashMap<>();
        data.put("Funcionalidade.quantidade", String.valueOf(listModelInPerfil.getSize()));
        for (int i = 0; i < listModelInPerfil.getSize(); i++) {
            data.put("Funcionalidade.nome" + i, listModelInPerfil.getElementAt(i).toString());
        }

        boolean isDone = controllerPerfil.saveFuncionalidades(getPerfilSelected(), new Request(data));

        if (isDone) {
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListFuncionalidades = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListFuncionalidadesDoPerfil = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePerfis = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jScrollPane3.setViewportView(jListFuncionalidades);

        jLabel3.setText("Funcionalidades:");

        jButton1.setText("Incluir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Retirar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Funcionalidades do Perfil:");

        jScrollPane4.setViewportView(jListFuncionalidadesDoPerfil);

        jButton3.setText("Salvar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                .addGap(294, 294, 294)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(172, 172, 172)))))
                .addGap(36, 36, 36))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
        );

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(246, 179, 111));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setText(" Permissôes de Perfil");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jTablePerfis.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTablePerfis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Participante ", "24/10/2015"},
                {"Decisor", "24/10/2015"}
            },
            new String [] {
                "Perfil", "Criado em"
            }
        ));
        jTablePerfis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePerfisMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTablePerfis);

        jButton5.setText("Novo Perfil");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText("Excluir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
            .addComponent(jTextField1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButtonEditar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new ViewNovoPerfil(null, true).setVisible(true);
        fillTable(controllerPerfil.findPerfis());
        clearLists();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTablePerfisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePerfisMouseClicked
        if (jTablePerfis.getSelectedRow() != -1) {
            fillLists();
        }
    }//GEN-LAST:event_jTablePerfisMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        includeFuncionalidade();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        removeFuncionalidade();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        save();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        editarButtonIsPressed();
        fillTable(controllerPerfil.findPerfis());
    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (jTablePerfis.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Selecione uma linha na tabela.");
            return;
        }
        removePerfil();
        fillTable(controllerPerfil.findPerfis());
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jListFuncionalidades;
    private javax.swing.JList jListFuncionalidadesDoPerfil;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTablePerfis;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
