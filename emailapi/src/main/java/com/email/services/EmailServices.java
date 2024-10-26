package com.email.services;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServices {

	public boolean sendEmail(String msg, String subject, String to) {
			boolean f = false;
			String from = "SenderMail@gmail.com";
		
			// get the system properties
			Properties properties = System.getProperties();
			// Mail properties set karo
			properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP host address
			properties.put("mail.smtp.port", "587"); // SMTP port
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			// Gmail credentials
			final String username = "SenderMail@gmail.com"; // Gmail account
			final String password = "Password"; // Gmail password or App Password

			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			session.setDebug(true);

			// compose the msg
			try {
				// Create the message
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				message.setSubject(subject);
				message.setText(msg);

				// Send the email
				Transport.send(message);

				System.out.println("Email successfully sent!");
				
				f=true;
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return f;
		}
	}
	
	

