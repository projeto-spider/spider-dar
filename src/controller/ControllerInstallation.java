package controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.ini4j.Ini;
import settings.Constant;
import util.Request;
import view.install.ViewInstallConfigAppEmail;
import view.install.ViewInstallConfigDB;
import view.install.ViewInstallFinish;
import view.install.ViewInstallSelectInstallation;

public class ControllerInstallation
{
    public boolean checkDatabaseStatus(Request request)
    {
        return false;
    }
    
    public boolean isInitialInstall()
    {
        checkConfigurationFile();
        return true;
    }
    
    private boolean checkConfigurationFile()
    {
        boolean configurationStatus = false;
        
        try
        {
            String url = "/config/config.ini";

            Ini ini = new Ini(getClass().getResource(url));
            
            String installationStatus = ini.get("install","status", String.class);
            
            if (installationStatus.equals("notInstalled"))
                configurationStatus = true;
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(null, "lol");
        }
        
        return configurationStatus;
    } 
    
    public boolean isApplicationDatabaseScriptReady()
    {
        return false;
    }
    
    public void goToStep(int step)
    {
        switch(step)
        {
            case Constant.INSTALL_SELECT_INSTALLATION:
            {
                ViewInstallSelectInstallation viewInstallSelectInstallation = new ViewInstallSelectInstallation();
                viewInstallSelectInstallation.setVisible(true);
                viewInstallSelectInstallation.setLocationRelativeTo(null);
                break;
            }
            case Constant.INSTALL_CONFIGDB:
            {
                ViewInstallConfigDB viewInstallConfigDB = new ViewInstallConfigDB();
                viewInstallConfigDB.setVisible(true);
                viewInstallConfigDB.setLocationRelativeTo(null);
                break;
            }
            case Constant.INSTALL_CONFIG_EMAIL:
            {
                ViewInstallConfigAppEmail viewInstallConfigAppEmail = new ViewInstallConfigAppEmail();
                viewInstallConfigAppEmail.setVisible(true);
                viewInstallConfigAppEmail.setLocationRelativeTo(null);
                break;
            }
            case Constant.INSTALL_FINISH:
            {
                ViewInstallFinish viewInstallFinish = new ViewInstallFinish();
                viewInstallFinish.setVisible(true);
                viewInstallFinish.setLocationRelativeTo(null);
                break;
            }
            default:
            {
                ViewInstallFinish viewInstallFinish = new ViewInstallFinish();
                viewInstallFinish.setVisible(true);
                viewInstallFinish.setLocationRelativeTo(null);
                break;
            }
        }
    }
}