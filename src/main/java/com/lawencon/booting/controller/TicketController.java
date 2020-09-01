package com.lawencon.booting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.booting.model.TicketDetails;
import com.lawencon.booting.model.TicketStatus;
import com.lawencon.booting.model.Tickets;
import com.lawencon.booting.model.Users;
import com.lawencon.booting.service.TicketsService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketsService ticketsService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		List<Tickets> listData = new ArrayList<>();
		try {
			listData = ticketsService.getListTickets();
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody String data) {
		try {
			TicketDetails pojo = new ObjectMapper().readValue(data, TicketDetails.class);
			ticketsService.insert(pojo);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		String respon = "";
		try {
			respon = new ObjectMapper().writeValueAsString("success");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(respon, HttpStatus.CREATED);
	}
	
	@GetMapping("/all-status")
	public ResponseEntity<?> getAllStatus(){
		TicketStatus ticketStatus = new TicketStatus();
		try {
			ticketStatus = ticketsService.selectStatus();
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ticketStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticketStatus, HttpStatus.OK);
	}
	
	@GetMapping("/all-agent")
	public ResponseEntity<?> getAllAgent(@RequestBody String data){
		List<Tickets> listData = new ArrayList<>();
		try {
			Users us = new ObjectMapper().readValue(data, Users.class);
			listData = ticketsService.getListByIdAgent(us);
			System.out.println(listData);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData.size(), HttpStatus.OK);
	}
	
}
