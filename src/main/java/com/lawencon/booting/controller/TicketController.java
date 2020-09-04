package com.lawencon.booting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.TicketCharts;
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
		Tickets ticket = new Tickets();
		try {
			ticket = new ObjectMapper().readValue(data, Tickets.class);
			ticket = ticketsService.insert(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
//		String respon = "";
//		try {
//			respon = new ObjectMapper().writeValueAsString("success");
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
		return new ResponseEntity<>(ticket, HttpStatus.CREATED);
	}
	
	@GetMapping("/get-ticket/{data}")
	public ResponseEntity<?> getTicket(@PathVariable("data") String data){
		Tickets ticket = new Tickets();
		ticket.setCode(data);
		try {
			ticket = ticketsService.getTicket(ticket);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ticket, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticket, HttpStatus.OK);
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
	
	@GetMapping("/status-agent/{data}")
	public ResponseEntity<?> getStatusAgent(@PathVariable("data") String data){
		TicketStatus ticketStatus = new TicketStatus();
		Users us = new Users();
		us.setNip(data);
		try {
			ticketStatus = ticketsService.statusAgent(us);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ticketStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticketStatus, HttpStatus.OK);
	}
	
	@GetMapping("/status-client/{data}")
	public ResponseEntity<?> getStatusClient(@PathVariable("data") String data){
		TicketStatus ticketStatus = new TicketStatus();
		Companies comp = new Companies();
		comp.setName(data);
		try {
			ticketStatus = ticketsService.statusClient(comp);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ticketStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticketStatus, HttpStatus.OK);
	}
	
	@GetMapping("/status-customer/{data}")
	public ResponseEntity<?> getStatusCustomer(@PathVariable("data") String data){
		TicketStatus ticketStatus = new TicketStatus();
		Users us = new Users();
		us.setNip(data);
		try {
			ticketStatus = ticketsService.statusCustomer(us);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ticketStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticketStatus, HttpStatus.OK);
	}
	
	@GetMapping("/all-agent/{data}")
	public ResponseEntity<?> getAllByAgent(@PathVariable("data") String data){
		List<Tickets> listData = new ArrayList<>();
		Users us = new Users();
		us.setNip(data);
		try {
//			Users us = new ObjectMapper().readValue(data, Users.class);
			listData = ticketsService.getListByIdAgent(us);
			System.out.println(listData.size());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}
	
	@GetMapping("/charts/{data}")
	public ResponseEntity<?> getCharts(@PathVariable("data") String data){
		List<TicketCharts> listData = new ArrayList<>();
//		TicketCharts tc = new TicketCharts();
		Long tc = Long.parseLong(data);
		try {
//			TicketCharts tc = new ObjectMapper().readValue(data, TicketCharts.class);
			listData = ticketsService.getListTicketCharts(tc);
//			System.out.println(listData);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}
	
	@GetMapping("/charts-client/{data}")
	public ResponseEntity<?> getChartsByClient(@PathVariable("data") String data){
		List<TicketCharts> listData = new ArrayList<>();
		Long tc = Long.parseLong(data);
		try {
			listData = ticketsService.getChartsByClient(tc);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}
	
	@GetMapping("/all-company/{data}")
	public ResponseEntity<?> getAllByCompany(@PathVariable("data") String data){
		List<Tickets> listData = new ArrayList<>();
		Companies comp = new Companies();
		comp.setName(data);
		try {
//			Companies us = new ObjectMapper().readValue(data, Companies.class);
			listData = ticketsService.getListByIdCompany(comp);
			System.out.println(listData.size());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}
	
	@GetMapping("/all-customer/{data}")
	public ResponseEntity<?> getAllByCustomer(@PathVariable("data") String data){
		List<Tickets> listData = new ArrayList<>();
		Users us = new Users();
		us.setNip(data);
		try {
//			Users us = new ObjectMapper().readValue(data, Users.class);
			listData = ticketsService.getListByIdUser(us);
			System.out.println(listData.size());
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}
	
}
