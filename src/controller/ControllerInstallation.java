package controller;

import javax.xml.bind.annotation.XmlElement;
import settings.Constant;
import util.Request;
import view.install.ViewInstallConfigAppEmail;
import view.install.ViewInstallConfigDB;
import view.install.ViewInstallCreateAdmin;
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
        
        return true;
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
            case Constant.INSTALL_CREATE_ADMIN: 
            {
                ViewInstallCreateAdmin viewInstallCreateAdmin = new ViewInstallCreateAdmin();
                viewInstallCreateAdmin.setVisible(true);
                viewInstallCreateAdmin.setLocationRelativeTo(null);
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