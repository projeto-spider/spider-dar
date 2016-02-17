package util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author Gessica and Bleno
 */
public class MyEmail {

    private String sender;
    private String password;
    private String host;
    private int port;

    public MyEmail() {
//        sender = "iuri.raiol@icen.ufpa.br";
//        password = "iuri23";
//        host = "smtp.ufpa.br";
//        port = 25;
        
        sender = "luiz-odl@hotmail.com";
        password = "motorola1986";
        host = "smtp.live.com";
        port = 587;
        
//        sender = "projetospiderdar@gmail.com";
//        password = "5p1d3rd4r";
//        host = "smtp.gmail.com";
//        port = 465;
    }

    public void SendNewPassword(String address, String name, String newPassword) {
        String subject = "Nova senha Spider-DAR";
        String message = "<html>"
                + "Olá, " + name + "<br><br>"
                + "Sua nova senha é: " + newPassword
                + "</html>";
        sendEmail(address, subject, message, 3 );
    }
        
    private void sendEmail(String address, String subject, String message, int operation) {
        
        switch(operation) {
            case (1) :
                sendEmailWithoutCript(address, subject, message);
                break;
            case (2) :
                sendEmailSSL(address, subject, message);
                break;
            case (3) :
                sendEmailTLS(address, subject, message);
                break; 
        }
    }
    
    private void sendEmailWithoutCript(String address, String subject, String message) {
        try {
            HtmlEmail email = new HtmlEmail();
            
            email.setDebug(true); 
            email.setHostName(host);
            email.setSmtpPort(port);
            
            email.setAuthenticator(new DefaultAuthenticator(sender, password));  
            
            email.setFrom(sender);
            email.setSubject(subject);
            email.addTo(address);
            email.setMsg(message);
            email.send();
        } catch (EmailException error) {
            error.printStackTrace();
        }
    }
        
    private void sendEmailSSL(String address, String subject, String message) {
        try {
            HtmlEmail email = new HtmlEmail();
            
            email.setDebug(true); 
            email.setHostName(host);
            email.setSmtpPort(port);
            
            email.setAuthenticator(new DefaultAuthenticator(sender, password));  
            
//            Google
            email.setSSLOnConnect(true);
            email.setSSLCheckServerIdentity(true);
            
            email.setFrom(sender);
            email.setSubject(subject);
            email.addTo(address);
            email.setMsg(message);
            email.send();
        } catch (EmailException error) {
            error.printStackTrace();
        }
    }
        
    private void sendEmailTLS(String address, String subject, String message) {
        try {
            HtmlEmail email = new HtmlEmail();
            
            email.setDebug(true); 
            email.setHostName(host);
            email.setSmtpPort(port);
            
            email.setAuthenticator(new DefaultAuthenticator(sender, password));  
            
//            OutLook
            email.setStartTLSEnabled(true); 
            email.setStartTLSRequired(true);
            
            email.setFrom(sender);
            email.setSubject(subject);
            email.addTo(address);
            email.setMsg(message);
            email.send();
        } catch (EmailException error) {
            error.printStackTrace();
        }
    }

}
