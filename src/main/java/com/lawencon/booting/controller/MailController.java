package com.lawencon.booting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.booting.utility.Mail;

@RestController
@RequestMapping("/mail")
@CrossOrigin("http://127.0.0.1:5500")
public class MailController {
	
	@Autowired
	private Mail mail;	
	
	@PostMapping("/kirim/{address}/{subject}/{body}")
	public ResponseEntity<?> sendMail(@PathVariable(name = "address") String address, @PathVariable(name = "subject") String subject, @PathVariable(name = "body") String body) {
		
		mail
		.init(address, subject, body)
		.sendMail();
		
		return new ResponseEntity<>("Kirim Berhasil Ok", HttpStatus.OK);
	}

}
