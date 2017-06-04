package com.mail;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ManagedBean(name = "mailSender")
public class MailSender implements Serializable {
	
	private static final long serialVersionUID = -8780269596203245866L;

	@Resource(mappedName = "java:jboss/mail/Gmail")
	private Session session;
	
	private String correoDestino;
	private String asunto;
	private String mensaje;

	public String sendMailTest() throws MessagingException {
		System.out.println("Eviando... ");
		try {
			sendMail("jorge.gonzalez1086@gmail.com", correoDestino, asunto, mensaje);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void sendMail(String mailFrom, String sMailTo, String sSubject,
			String sMailText) throws MessagingException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(mailFrom));
		message.setReplyTo(new Address[] { new InternetAddress(mailFrom) });
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				sMailTo));
		message.setHeader("Content-Type", "Contenido BODY del mail");
		message.setSubject(sSubject);
		message.setText(sMailText);
		Transport.send(message);
	}

	public String getCorreoDestino() {
		return correoDestino;
	}

	public void setCorreoDestino(String correoDestino) {
		this.correoDestino = correoDestino;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}