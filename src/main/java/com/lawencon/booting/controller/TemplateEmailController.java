package com.lawencon.booting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.booting.model.TemplateEmail;
import com.lawencon.booting.service.TemplateEmailService;

@RestController
@RequestMapping("/template")
@CrossOrigin("http://127.0.0.1:5500")
public class TemplateEmailController {

	@Autowired
	private TemplateEmailService templateEmailService;

	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody String data) {
		TemplateEmail template = new TemplateEmail();
		try {
			template = new ObjectMapper().readValue(data, TemplateEmail.class);
			template = templateEmailService.insert(template);
		} catch (Exception e) {
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(template, HttpStatus.CREATED);
	}

}
