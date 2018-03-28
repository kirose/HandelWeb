package mx.handel.cfg.email;

import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


@Stateless
public class Email{
	// https://developer.jboss.org/wiki/MailConfigurationInWildFly10
	@Resource(name = "java:jboss/mail/gmail")
	private javax.mail.Session session;
	public Email(){
		super();
	}
	public void send(String json) throws MessagingException{
		Gson gson = new GsonBuilder().create();
		Map<String,String> data = gson.fromJson(json, new TypeToken<Map<String, String>>(){}.getType());
		
		if (!data.containsKey("to")){
			throw new MessagingException("No hay destinatario para enviar correo electronico"); 
		}
		if (!data.containsKey("subject")){
			throw new MessagingException("No se ha especificado el asunto del correo electronico");
		}
		if (!data.containsKey("text")){
			throw new MessagingException("No se ha especificado el mensaje del correo electronico");
		}
		Message msg = new MimeMessage(session);
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(data.get("to")));
		msg.setSubject(data.get("subject"));
		msg.setText(data.get("text"));
		Transport.send(msg);
	}
	public void send(String to, String subject, String message) throws MessagingException{
		Message msg = new MimeMessage(session);
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		msg.setSubject(subject);
		msg.setText(message);
		Transport.send(msg);

	}
}
