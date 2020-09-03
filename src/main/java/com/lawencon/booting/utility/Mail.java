package com.lawencon.booting.utility;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import com.lawencon.booting.model.Accounts;
import com.lawencon.booting.model.Tickets;

@Component
public class Mail {

	private String subject;
	private String body;
	private String address;
	final Context ctx = new Context();

	@Autowired
	private JavaMailSender javaMailSender;

	public Mail init(String address, String subject, String body) {
		this.body = body;
		this.subject = subject;
		this.address = address;

		return this;
	}
	
	public Mail init(String address, String subject, String body,Accounts accounts, String pwd) {
		this.body = body;
		this.subject = subject;
		this.address = address;
		ctx.setVariable("nama", accounts.getIdUser().getName());
		ctx.setVariable("email", "Email : " + accounts.getEmail() );
		ctx.setVariable("pass", "Password : " + pwd );
		return this;
	}
	
	public Mail init(String address, String subject, String body,String name) {
		this.body = body;
		this.subject = subject;
		this.address = address;
		ctx.setVariable("nama", name );
		return this;
	}
	
	public Mail init(String address, String subject, String body,String name,Tickets ticket) {
		this.body = body;
		this.subject = subject;
		this.address = address;
		ctx.setVariable("subject", this.subject);
		ctx.setVariable("nama", name );
		ctx.setVariable("product", ticket.getIdProduct().getName());
		ctx.setVariable("priority", ticket.getIdPriority().getName());
		ctx.setVariable("classification", ticket.getIdClassification().getName());
		ctx.setVariable("desc", ticket.getSubject());
		return this;
	}

	public void sendMail() {
		// without attachment file
		SimpleMailMessage email = new SimpleMailMessage();
//		final Context context = new Context();
//		context.setVariable("nama", "iqbal");
		// with attachment file
		MimeMessage msg = javaMailSender.createMimeMessage();
		
	
		
		final TemplateEngine templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(new StringTemplateResolver());
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			
//		    final String result = templateEngine.process(htmlMsg, ctx);
			final String result = templateEngine.process(body, ctx);
			helper.setTo(address);

			helper.setSubject(subject);

			// default = text/plain
			// helper.setText("Check attachment for image!");

			// true = text/html
//			 helper.setText(body,true);
//			helper.setText(htmlMsg, true);
			helper.setText(result, true);
			try {
				helper.setFrom(address, "Booting");
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

		// javaMailSender.send(email);
		javaMailSender.send(msg);
	}

}
