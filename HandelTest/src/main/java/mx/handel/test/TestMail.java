package mx.handel.test;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestMail {
	public static void main(String[] args) {
		Properties props = System.getProperties(); 
		//Properties props = new Properties();
		props.put("mail.smtps.host", "smtp.gmail.com");
		//props.put("mail.smtp.socketFactory.port", "465");
		//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtps.auth", "true");
		//props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props);
		session.setDebug(true);
		/*Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("masdark99@gmail.com","Fokeuler27182818$");
				}
			});*/

		try {

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("Perenganito"));
			msg.setRecipients(Message.RecipientType.TO,	InternetAddress.parse("marcoasb99@ciencias.unam.mx"));
			msg.setContent("Dear Mail Crawler, \n\n No spam to my email, please!","text/plain");
			msg.setSubject("Testing Subject");
			//msg.setText("Dear Mail Crawler, \n\n No spam to my email, please!");
			msg.saveChanges();
			
            Transport tr = session.getTransport("smtps");
            tr.connect("smtp.gmail.com","masdark99@gmail.com", "Fokeuler27182818$");
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
			//Transport.send(msg);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
