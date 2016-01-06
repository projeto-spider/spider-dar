package view.Gerenciar;

import controller.ControllerPerfil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewAlocarUsuario extends javax.swing.JDialog {

    private Request request = null;
    private final ControllerPerfil controllerPerfil = new ControllerPerfil();
    private DefaultComboBoxModel comboboxModel;

    public ViewAlocarUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        fillComboboxProblema();
        fillComboboxPerfil();
        this.setLocationRelativeTo(null);
    }

    private void fillComboboxProblema() {
        comboboxModel = new DefaultComboBoxModel();
        comboboxModel.addElement("--Selecione um Problema--");
        comboboxModel.addElement("Default");

        jComboBoxProblema.setModel(comboboxModel);
    }

    private void fillComboboxPerfil() {
        comboboxModel = new DefaultComboBoxModel();
        comboboxModel.addElement("--Selecione um Perfil--");

        List<Request> requestList = controllerPerfil.findPerfis();
        for (Request request : requestList) {
            comboboxModel.addElement(request.getData("Perfil.nome"));
        }

        jComboBoxPerfil.setModel(comboboxModel);
    }

    private void Allocate() {
        if (!jComboBoxPerfil.getSelectedItem().toString().equals("--Selecione um Perfil--")
                && !jComboBoxProblema.getSelectedItem().toString().equals("--Selecione um Problema--")) {
            Map<String, String> data = new HashMap<>();
            data.put("Problema.nome", jComboBoxProblema.getSelectedItem().toString());
            data.put("Perfil.nome", jComboBoxPerfil.getSelectedItem().toString());
            request = new Request(data);
        }
    }

    public Request getRequest() {
        return request;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxProblema = new javax.swing.JComboBox();
        jComboBoxPerfil = new javax.swing.JComboBox();
        jButtonAlocar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alocar usuário a um problema");
        setResizable(false);

        jComboBoxProblema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxPerfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonAlocar.setText("Alocar");
        jButtonAlocar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlocarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAlocar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxProblema, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxProblema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButtonAlocar)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAlocarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlocarActionPerformed
        Allocate();
        this.dispose();
    }//GEN-LAST:event_jButtonAlocarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlocar;
    private javax.swing.JComboBox jComboBoxPerfil;
    private javax.swing.JComboBox jComboBoxProblema;
    // End of variables declaration//GEN-END:variables
}
