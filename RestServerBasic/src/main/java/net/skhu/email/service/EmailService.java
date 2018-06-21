package net.skhu.email.service;

import net.skhu.email.model.TestEmail;
import net.skhu.email.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;


@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(TestEmail email) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject(email.getSubject());
		message.setSender(new InternetAddress(email.getSender()));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getRecipient()));
		message.setText(email.getContent());
		message.setSentDate(new Date());

		javaMailSender.send(message);
	}
}
