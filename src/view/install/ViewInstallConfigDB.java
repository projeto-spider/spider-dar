package view.install;

import controller.ControllerInstallation;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;
import org.ini4j.Ini;
import util.Input;
import util.Request;
import util.Validate;
import static javax.swing.JOptionPane.*;
import settings.Constant;

public class ViewInstallConfigDB extends javax.swing.JFrame
{
    private ControllerInstallation controllerInstalation = new ControllerInstallation();
    private int installationType = 0;
    public ViewInstallConfigDB()
    {
        initComponents();
    }
    
    public void setInstallationType(int installationType)
    {
        this.installationType = installationType;
    }
    
    private void checkPreviousStep()
    {
        this.dispose();
        controllerInstalation.goToStep(Constant.INSTALL_SELECT_INSTALLATION);
    }
    
    public void checkDatabaseStatus()
    {
        String urlIP = jTextFieldEnderecoIP.getText();
        String portaIP = jTextFieldPortaIP.getText();
        String nomeDB = jTextFieldNomeBD.getText();
        
//        String url = "jdbc:mysql://" + urlIP + ":" + portaIP + "/" + nomeDB;
        
        String usuarioDB = jTextFieldUsuarioBD.getText();
        String passDB = jPasswordFieldSenhaBD.getText();
        String passDBConfirmar = jPasswordFieldSenhaBDConfirmar.getText();
        
//        Request request = new Request();
        
//        request.setDataInput("Persistence.ip", new Input(1, "ip", "Nome ou IP do BD:", urlIP));
//        request.setDataInput("Persistence.porta", new Input(2, "number", nomeDB, url));
//        request.setDataInput("Persistence.url", new Input(1, "text", "Nome do Banco de Dados", url));
//        request.setDataInput("Persistence.usuario", new Input(2, "Usuário do Banco de Dados", nomeDB, url));
//        request.setDataInput("Persistence.password", new Input(3, url, nomeDB, url));
//        request.setDataInput("Persistence.passwordConfirm", new Input(3, url, nomeDB, url));
//        
//        Validate validate = new Validate(request);
//        
//        if (validate.isValidData())
//            return false;
        try
        {
            
            Map properties = new HashMap();

            String urlBD = "jdbc:mysql://" + urlIP + ":" + portaIP + "/" + nomeDB;
//            userBD = user;
//            passBD = pass;

            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            properties.put("javax.persistence.jdbc.url", urlBD);
            properties.put("javax.persistence.jdbc.user", usuarioDB);
            properties.put("javax.persistence.jdbc.password", passDB);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Spider-DARPU", properties);
            EntityManager em = (EntityManager) emf.createEntityManager();

            Object object = em.createNativeQuery("SELECT COUNT(*) FROM usuario").getSingleResult();
            
            if (Integer.parseInt(object.toString()) >= 0)
                showMessageDialog(null, "Conexão com o Banco de Dados bem sucedida!", "Conexão com Banco de Dados",INFORMATION_MESSAGE);
            else
                throw new Exception();
        }
        catch (Exception e)
        {
//            TODO: STATUS DE DEBUG DA APLICAÇÃO em algum INI
//            showMessageDialog(null, e.toString(),"Conexão com o Banco de Dados", ERROR_MESSAGE);
            showMessageDialog(null, "Ocorreu um Problema na conexão com Banco de Dados","Conexão com o Banco de Dados", ERROR_MESSAGE);
        }
    }
    
    private void checkNextStep()
    {
        String urlIP = jTextFieldEnderecoIP.getText();
        String portaIP = jTextFieldPortaIP.getText();
        String nomeDB = jTextFieldNomeBD.getText();
        
        String usuarioDB = jTextFieldUsuarioBD.getText();
        String passDB = jPasswordFieldSenhaBD.getText();
        String passDBConfirmar = jPasswordFieldSenhaBDConfirmar.getText();
        
        try
        {
            String url = "src/resources/configs/configBD.ini";
//            String url = "config/configBD.ini";

            Ini ini = new Ini(new File(url));

            String iniFileName = "configdb";
            ini.put(iniFileName, "ip", urlIP);
            ini.put(iniFileName, "port", portaIP);
            ini.put(iniFileName, "namedb", nomeDB);
            ini.put(iniFileName, "user", usuarioDB);
            ini.put(iniFileName, "pass", passDB);
            ini.store();
            
            boolean isApplicationDatabaseScriptReady = controllerInstalation.isApplicationDatabaseScriptReady();
            
            if (isApplicationDatabaseScriptReady)
                throw new Exception();
            
            
            controllerInstalation.goToStep(Constant.INSTALL_CREATE_ADMIN);
            this.dispose();
        }
        catch(IOException ioe)
        {
//            showMessageDialog(null, ioe.toString());
            showMessageDialog(null, "Ocorreu um Problema ao ler o arquivo de configuração", "Configuração",ERROR_MESSAGE);
        }
        catch(Exception e)
        {
            showMessageDialog(null, "Ocorreu um problema ao criar o Banco de Dados","Configuração",ERROR_MESSAGE);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jButtonTestarConexaoBD = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        jLabelTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldEnderecoIP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPortaIP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNomeBD = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldUsuarioBD = new javax.swing.JTextField();
        jButtonProximoConfigDB = new javax.swing.JButton();
        jButtonCancelarInstall = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jButtonAnteriorConfigBD = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jPasswordFieldSenhaBD = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jPasswordFieldSenhaBDConfirmar = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonTestarConexaoBD.setText("Testar conexão com Banco de dados");
        jButtonTestarConexaoBD.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonTestarConexaoBDActionPerformed(evt);
            }
        });

        jLabel1.setText("Endereço ou IP do BD:");

        panel1.setBackground(new java.awt.Color(31, 109, 165));
        panel1.setPreferredSize(new java.awt.Dimension(420, 40));

        jLabelTitulo.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setText("Instalação: Configuração do Banco de Dados");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTextFieldEnderecoIP.setText("localhost");

        jLabel2.setText("Porta:");

        jTextFieldPortaIP.setText("3306");
        jTextFieldPortaIP.setToolTipText("Porta Padrão:3306");

        jLabel3.setText("Nome do BD:");

        jTextFieldNomeBD.setText("spiderdar");

        jLabel4.setText("Usuário do BD:");

        jTextFieldUsuarioBD.setText("spiderdar");

        jButtonProximoConfigDB.setText("Próximo >");
        jButtonProximoConfigDB.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonProximoConfigDBActionPerformed(evt);
            }
        });

        jButtonCancelarInstall.setText("Cancelar");

        jButtonAnteriorConfigBD.setText("< Anterior");
        jButtonAnteriorConfigBD.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonAnteriorConfigBDActionPerformed(evt);
            }
        });

        jLabel7.setText("Senha do BD:");

        jPasswordFieldSenhaBD.setText("spider");

        jLabel5.setText("Confirmar Senha do BD:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonTestarConexaoBD)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPasswordFieldSenhaBDConfirmar, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPasswordFieldSenhaBD, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldUsuarioBD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                .addComponent(jTextFieldNomeBD, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldEnderecoIP, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jTextFieldPortaIP, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAnteriorConfigBD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonProximoConfigDB)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCancelarInstall)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEnderecoIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPortaIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNomeBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUsuarioBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jPasswordFieldSenhaBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jPasswordFieldSenhaBDConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonTestarConexaoBD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonProximoConfigDB)
                    .addComponent(jButtonCancelarInstall)
                    .addComponent(jButtonAnteriorConfigBD))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTestarConexaoBDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonTestarConexaoBDActionPerformed
    {//GEN-HEADEREND:event_jButtonTestarConexaoBDActionPerformed
        this.checkDatabaseStatus();
    }//GEN-LAST:event_jButtonTestarConexaoBDActionPerformed

    private void jButtonProximoConfigDBActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonProximoConfigDBActionPerformed
    {//GEN-HEADEREND:event_jButtonProximoConfigDBActionPerformed
        checkNextStep();
    }//GEN-LAST:event_jButtonProximoConfigDBActionPerformed

    private void jButtonAnteriorConfigBDActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonAnteriorConfigBDActionPerformed
    {//GEN-HEADEREND:event_jButtonAnteriorConfigBDActionPerformed
        checkPreviousStep();
    }//GEN-LAST:event_jButtonAnteriorConfigBDActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(ViewInstallConfigDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ViewInstallConfigDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ViewInstallConfigDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ViewInstallConfigDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new ViewInstallConfigDB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnteriorConfigBD;
    private javax.swing.JButton jButtonCancelarInstall;
    private javax.swing.JButton jButtonProximoConfigDB;
    private javax.swing.JButton jButtonTestarConexaoBD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPasswordField jPasswordFieldSenhaBD;
    private javax.swing.JPasswordField jPasswordFieldSenhaBDConfirmar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldEnderecoIP;
    private javax.swing.JTextField jTextFieldNomeBD;
    private javax.swing.JTextField jTextFieldPortaIP;
    private javax.swing.JTextField jTextFieldUsuarioBD;
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables
}
