package com.lawencon.booting.utility;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class Mail {

	private String subject;
	private String body;
	private String address;

	@Autowired
	private JavaMailSender javaMailSender;

	public Mail init(String address, String subject, String body) {
		this.body = body;
		this.subject = subject;
		this.address = address;

		return this;
	}

	public void sendMail() {
		// without attachment file
		SimpleMailMessage email = new SimpleMailMessage();

		// with attachment file
		MimeMessage msg = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setTo(address);

			helper.setSubject(subject);

			// default = text/plain
			// helper.setText("Check attachment for image!");

			// true = text/html
			// helper.setText("<h1> Ini Text berupa HTML </h1>");
			helper.setText(body, true);
			try {
				helper.setFrom(address, "Mochammad Arie Aldiansyah");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		email.setTo(address);
		email.setFrom(address);
		email.setSubject(body);
		email.setText(body);

		//javaMailSender.send(email);
		javaMailSender.send(msg);
	}

}
