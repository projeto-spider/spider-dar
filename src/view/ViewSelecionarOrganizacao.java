package view;

import controller.ControllerOrganizacao;
import controller.ControllerProblema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import settings.KeepData;
import util.Request;

/**
 *
 * @author Bleno Vale
 */
public class ViewSelecionarOrganizacao extends javax.swing.JDialog {

    private DefaultComboBoxModel comboboxModel;
    private final ControllerOrganizacao controllerOrganizacao = new ControllerOrganizacao();
    private ViewPrincipal viewPrincipal;

    public ViewSelecionarOrganizacao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        fillComboboxOrganizacaoAndProblema();
        this.setLocationRelativeTo(null);
    }

    public ViewSelecionarOrganizacao(java.awt.Frame parent, boolean modal, ViewPrincipal viewPrincipal) {
        super(parent, modal);
        initComponents();

        this.viewPrincipal = viewPrincipal;
        fillComboboxOrganizacaoAndProblema();
        
        this.setLocationRelativeTo(null);
    }

    private void fillComboboxOrganizacaoAndProblema() {
        comboboxModel = new DefaultComboBoxModel();
        comboboxModel.addElement("--Selecione uma Organização--");
        
        List<Request> requestList = controllerOrganizacao.findOrganizacoesByUsuario();
        for (Request request : requestList) {
            comboboxModel.addElement(request.getData("Organizacao.nome"));
        }

        jComboBoxOrganizacao.setModel(comboboxModel);
        
        jComboBoxOrganizacao.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                DefaultComboBoxModel comboBoxModelProblema = new DefaultComboBoxModel();
                String comboboxProblemaTextDefault = "--Selecione um Problema--";
                comboBoxModelProblema.addElement(comboboxProblemaTextDefault);
                
                ControllerProblema controllerProblema = new ControllerProblema();
                
                String nomeOrganizacao = (String) comboboxModel.getSelectedItem();
                
                if (!nomeOrganizacao.equals("--Selecione uma Organização--"))
                {
                    Request requestOrganizacao = controllerOrganizacao.findOrganizacaoSelected(nomeOrganizacao);

                    String idOrganizacao = requestOrganizacao.getData("Organizacao.id");

                    List<Request> requestList = controllerProblema.listProblemasByIdOrganizacao(idOrganizacao);

                    for (Request request : requestList)
                    {
                        comboBoxModelProblema.addElement(request.getData("Problema.codigo") + " - " + request.getData("Problema.nome"));
                    }
                }
                
               jComboBoxProblema.setModel(comboBoxModelProblema);
            }
        });
    }
    
    private void keepData() {
        
        if (!"--Selecione uma Organização--".equals(jComboBoxOrganizacao.getSelectedItem().toString())) 
        {
            Request request = controllerOrganizacao.findOrganizacaoSelected(jComboBoxOrganizacao.getSelectedItem().toString());
            
            KeepData.setData("Organizacao.id", request.getData("Organizacao.id"));
            KeepData.setData("Organizacao.nome", request.getData("Organizacao.nome"));
            
            String comboBoxValue = jComboBoxProblema.getSelectedItem().toString();
            
            if (!("--Selecione um Problema--".equals(comboBoxValue)))
            {
                String arrayStringCodigoProblema[] = comboBoxValue.split(" - ");
                
                String codigoProblema = arrayStringCodigoProblema[0];
                
                ControllerProblema controllerProblema = new ControllerProblema();
                Request requestProblemaBusca = new Request();
                
                requestProblemaBusca.setData("Problema.codigo", codigoProblema);
                requestProblemaBusca.setData("Problema.idOrganizacao", request.getData("Organizacao.id"));
                
                Request requestProblema = controllerProblema.getProblemaByCodigo(requestProblemaBusca);
                
                KeepData.setData("Problema.id",requestProblema.getData("Problema.id"));
                KeepData.setData("Problema.codigo",requestProblema.getData("Problema.codigo"));
                KeepData.setData("Problema.nome",requestProblema.getData("Problema.nome"));
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        jComboBoxOrganizacao = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxProblema = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Organização/Tomada");

        jLabel1.setText("Organizações:");

        jComboBoxOrganizacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Selecione uma Organização--" }));

        jLabel2.setText("Problemas:");

        jComboBoxProblema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Selecione um Problema--" }));

        jButton1.setText("acessar");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxOrganizacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jComboBoxProblema, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 311, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxOrganizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxProblema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (viewPrincipal == null) {
            this.dispose();
            keepData();
            new ViewPrincipal().setVisible(true);
        } else {
            this.dispose();
            KeepData.clearKeepData();
            keepData();
            viewPrincipal.showInformation();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBoxOrganizacao;
    private javax.swing.JComboBox jComboBoxProblema;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

      public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ViewSelecionarOrganizacao viewSelecionarOrganizacao = new ViewSelecionarOrganizacao(new javax.swing.JFrame(), true);
                viewSelecionarOrganizacao.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                viewSelecionarOrganizacao.setVisible(true);
            }
        });
    }
}
