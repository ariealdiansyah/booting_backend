package com.lawencon.booting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.booting.model.Notifications;
import com.lawencon.booting.service.NotificationsService;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

	@Autowired
	private NotificationsService notificationsService;

	@PostMapping("/")
	public ResponseEntity<?> insert(@RequestBody String data) {
		try {
			Notifications notif = new ObjectMapper().readValue(data, Notifications.class);
			notificationsService.insert(notif);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		String respon = "";
		try {
			respon = new ObjectMapper().writeValueAsString("Notifikasi berhasil ditambah");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(respon, HttpStatus.CREATED);
	}
}
