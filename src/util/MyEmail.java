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

    public void sendEmail(String receiver, String subject, String message) {
        HtmlEmail htmlEmail = new HtmlEmail();

        try {
            htmlEmail.setHostName(host);
            htmlEmail.setSmtpPort(port);

            htmlEmail.setAuthenticator(new DefaultAuthenticator(sender, password));
            htmlEmail.setSSLOnConnect(true);
            
            htmlEmail.setFrom(sender);
            htmlEmail.setSubject(subject);
            htmlEmail.setHtmlMsg(message);
            htmlEmail.addTo(receiver);
            
            htmlEmail.send();
        } catch (EmailException error) {
            System.out.println(">> não Deu :(");
            error.printStackTrace();
        }

    }
}
