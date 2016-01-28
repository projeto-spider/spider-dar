package util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

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
        sender = "iuri.raiol@icen.ufpa.br";
        password = "iuri23";
        host = "smtp.ufpa.br";
        port = 25;
        
//        sender = "luiz-odl@hotmail.com";
//        password = "motorola1986";
//        host = "smtp.live.com";
//        port = 587;
        
//        sender = "projetospiderdar@gmail.com";
//        password = "5p1d3rd4r";
//        host = "smtp.gmail.com";
//        port = 465;
    }

    public void SendNewPassword(String address, String name, String newPassword) {
        String subject = "Nova senha Spider-DAR";
//        String message = "<html>"
//                + "Olá " + name + "<br><br>"
//                + "Sua nova senha é: " + newPassword
//                + "</html>";
        sendEmail(address, subject, "Teste");
    }

    private void sendEmail(String address, String subject, String message) {
        try {
            SimpleEmail email = new SimpleEmail();
            
            email.setDebug(true); 
            email.setHostName(host);
            email.setSmtpPort(port);
            
            email.setAuthenticator(new DefaultAuthenticator(sender, password));  
//            Google
//            email.setSSLOnConnect(true);
//            email.setSSLCheckServerIdentity(true);

//            OutLook
//            email.setStartTLSEnabled(true); 
//            email.setStartTLSRequired(true);
            
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
