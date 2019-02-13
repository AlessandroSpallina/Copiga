import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Email {
	public static void invia(String indirizzo, String testo) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.email.it");
		props.put("mail.smtp.socketFactory.port", "25");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "25");
		
		new Thread() {
            public void run() {
            	Session session = Session.getDefaultInstance(props,
            			new javax.mail.Authenticator() {
            				protected PasswordAuthentication getPasswordAuthentication() {
            					return new PasswordAuthentication("ingsw_lm32","bfCDDnwQ");
            				}
            			});
             
            		try {
             
            			Message message = new MimeMessage(session);
            			message.setFrom(new InternetAddress("ingsw_lm32@email.it"));
            			message.setRecipients(Message.RecipientType.TO,
            					InternetAddress.parse(indirizzo));
            			message.setSubject("Libro disponibile");
            			message.setText(testo);
             
            			Transport.send(message);
             
            			System.out.println("Done");
             
            		} catch (MessagingException e) {
            			throw new RuntimeException(e);
            		}
            }
        }.start();
	}
}