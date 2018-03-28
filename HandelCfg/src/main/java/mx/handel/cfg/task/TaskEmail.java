package mx.handel.cfg.task;

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

import mx.handel.cfg.pojo.Task;


/**
 * Con esta clase se envian correos electronicos
 * @author Marco Antonio
 *
 */
@Stateless
public class TaskEmail extends AbstractTask{
	// https://developer.jboss.org/wiki/MailConfigurationInWildFly10
	@Resource(name = "java:jboss/mail/gmail")
	private javax.mail.Session session;
	public TaskEmail(){
		super();
	}
	public void send(String json) throws MessagingException{
		Gson gson = new GsonBuilder().create();
		Map<String,Object> data = gson.fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());
		
		if (!data.containsKey("to")){
			throw new MessagingException("No hay destinatario para enviar correo electronico"); 
		}
		if (!data.containsKey("subject")){
			throw new MessagingException("No se ha especificado el asunto del correo electronico");
		}
		if (!data.containsKey("message")){
			throw new MessagingException("No se ha especificado el mensaje del correo electronico");
		}
		//"text/html","text/plain"
		String contentType = data.containsKey("contentType") ? data.get("contentType").toString() : "text/plain";

		Message msg = new MimeMessage(session);
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(data.get("to").toString()));
		msg.setSubject(data.get("subject").toString());
		msg.setContent(data.get("message"), contentType);
		//msg.setText(data.get("text"));
		Transport.send(msg);
	}
	public void send(String to, String subject, String message) throws MessagingException{
		Message msg = new MimeMessage(session);
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		msg.setSubject(subject);
		msg.setText(message);
		Transport.send(msg);

	}
	@Override
	public void doTask(Task task) {

		if (task == null || task.getData() == null){
			logger.info("No se envia correo electronico: la tarea que se envio es nula");
			return;
		}
		if (!(task.getData() instanceof Map)){
			logger.info(String.format("No se envia correo electronico: los datos que se envian no de tipo Map<String,String> en su lugar %s",task.getData().getClass().toString()));
			return;			
		}
		
		try {
			@SuppressWarnings("unchecked")
			Map<String,Object> data = (Map<String,Object>) task.getData();
			if (!data.containsKey("to")){
				throw new MessagingException("No hay destinatario para enviar correo electronico");
			}
			if (!data.containsKey("subject")){
				throw new MessagingException("No se ha especificado el asunto del correo electronico");
			}
			if (!data.containsKey("message")){
				throw new MessagingException("No se ha especificado el mensaje del correo electronico");
			}
			
			String contentType = data.containsKey("contentType") ? data.get("contentType").toString() : "text/plain";
			Message msg = new MimeMessage(session);
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(data.get("to").toString()));
			msg.setSubject(data.get("subject").toString());
			msg.setContent(data.get("message"), contentType);
			//msg.setText(data.get("message"));
			Transport.send(msg);
		} catch (Exception e) {
			logger.error("No se pudo enviar correo electronico", e);
		} 

	}
}
