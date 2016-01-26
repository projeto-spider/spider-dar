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
//        port = 587;
        
        sender = "projetospiderdar@gmail.com";
        password = "5p1d3rd4r";
        host = "smtp.gmail.com";
        port = 465;
    }

    public void SendNewPassword(String address, String name, String newPassword) {
        String subject = "Nova senha Spider-DAR";
        String message = "<html>"
                + "Olá " + name + "<br><br>"
                + "Sua nova senha é: " + newPassword
                + "</html>";
        sendEmail(address, subject, message);
    }

    private void sendEmail(String address, String subject, String message) {
        try {
            HtmlEmail email = new HtmlEmail();
            
            email.setDebug(true); 
            email.setHostName(host);
            email.setSmtpPort(port);
            
            email.setAuthenticator(new DefaultAuthenticator(sender, password));
            email.setSSLOnConnect(true);
            email.setStartTLSEnabled(true); 
            email.setStartTLSRequired(true);
            
            email.setFrom(sender);
            email.setSubject(subject);
            email.addTo(address);
            email.setHtmlMsg(message);
            email.send();
        } catch (EmailException error) {
            error.printStackTrace();
        }
    }

    private void setAuthenticator(DefaultAuthenticator defaultAuthenticator) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
