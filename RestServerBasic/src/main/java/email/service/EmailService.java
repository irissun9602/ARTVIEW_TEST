package email.service;

import javax.mail.MessagingException;

import email.model.TestEmail;

public interface EmailService {
	void sendMail(TestEmail testEmail) throws MessagingException;
}
