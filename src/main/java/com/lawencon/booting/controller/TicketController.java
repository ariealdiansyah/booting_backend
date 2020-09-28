package com.lawencon.booting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.TicketCharts;
import com.lawencon.booting.model.TicketHeader;
import com.lawencon.booting.model.TicketStatus;
import com.lawencon.booting.model.Tickets;
import com.lawencon.booting.model.Users;
import com.lawencon.booting.service.TicketsService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketsService ticketsService;

	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		List<Tickets> listData = new ArrayList<>();
		try {
			listData = ticketsService.getListTickets();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody String data) {
		Tickets ticket = new Tickets();
		try {
			ticket = new ObjectMapper().readValue(data, Tickets.class);
			ticket = ticketsService.update(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> insert(@RequestBody String data) {
		Tickets ticket = new Tickets();
		try {
			ticket = new ObjectMapper().readValue(data, Tickets.class);
			ticket = ticketsService.insert(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		if (ticket == null) {
			String respon = "";
			try {
				respon = new ObjectMapper().writeValueAsString("you have no tickets left");
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>(respon, HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(ticket, HttpStatus.CREATED);
	}

	@GetMapping("/{code}")
	public ResponseEntity<?> getTicketByCode(@PathVariable("code") String code) {
		Tickets ticket = new Tickets();
		ticket.setCode(code);
		TicketHeader ticketHeader = new TicketHeader();
		try {
			ticketHeader = ticketsService.getTicket(ticket);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ticketHeader, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticketHeader, HttpStatus.OK);
	}

	@GetMapping("/status")
	public ResponseEntity<?> getAllStatus() {
		TicketStatus ticketStatus = new TicketStatus();
		try {
			ticketStatus = ticketsService.selectStatus();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ticketStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticketStatus, HttpStatus.OK);
	}

	@GetMapping("/status/agent/{nip}")
	public ResponseEntity<?> getStatusAgent(@PathVariable("nip") String nip) {
		TicketStatus ticketStatus = new TicketStatus();
		Users us = new Users();
		us.setNip(nip);
		try {
			ticketStatus = ticketsService.statusAgent(us);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ticketStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticketStatus, HttpStatus.OK);
	}

	@GetMapping("/status/client/{code}")
	public ResponseEntity<?> getStatusClient(@PathVariable("code") String code) {
		TicketStatus ticketStatus = new TicketStatus();
		Companies comp = new Companies();
		comp.setCode(code);
		try {
			ticketStatus = ticketsService.statusClient(comp);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ticketStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticketStatus, HttpStatus.OK);
	}

	@GetMapping("/status/customer/{nip}")
	public ResponseEntity<?> getStatusCustomer(@PathVariable("nip") String nip) {
		TicketStatus ticketStatus = new TicketStatus();
		Users us = new Users();
		us.setNip(nip);
		try {
			ticketStatus = ticketsService.statusCustomer(us);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(ticketStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticketStatus, HttpStatus.OK);
	}

	@GetMapping("/charts/{year}")
	public ResponseEntity<?> getCharts(@PathVariable("year") String year) {
		List<TicketCharts> listData = new ArrayList<>();
		Long tc = Long.parseLong(year);
		try {
			listData = ticketsService.getListTicketCharts(tc);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/charts/client/{code}")
	public ResponseEntity<?> getChartsByClient(@PathVariable("code") String code) {
		List<TicketCharts> listData = new ArrayList<>();
		Companies com = new Companies();
		com.setCode(code);
		try {
			listData = ticketsService.getChartsByClient(com);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/charts/agent/{nip}")
	public ResponseEntity<?> getChartsByAgent(@PathVariable("nip") String nip) {
		List<TicketCharts> listData = new ArrayList<>();
		Users user = new Users();
		user.setNip(nip);
		try {
			listData = ticketsService.getChartsByAgent(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/agent/{nip}")
	public ResponseEntity<?> getAllByAgent(@PathVariable("nip") String nip) {
		List<Tickets> listData = new ArrayList<>();
		Users us = new Users();
		us.setNip(nip);
		try {
			listData = ticketsService.getListByIdAgent(us);
			System.out.println(listData.size());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/client/{code}")
	public ResponseEntity<?> getAllByCompany(@PathVariable("code") String code) {
		List<Tickets> listData = new ArrayList<>();
		Companies comp = new Companies();
		comp.setCode(code);
		try {
			listData = ticketsService.getListByIdCompany(comp);
			System.out.println(listData.size());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/customer/{nip}")
	public ResponseEntity<?> getAllByCustomer(@PathVariable("nip") String nip) {
		List<Tickets> listData = new ArrayList<>();
		Users us = new Users();
		us.setNip(nip);
		try {
			listData = ticketsService.getListByIdUser(us);
			System.out.println(listData.size());
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/relation/{idCompany}")
	public ResponseEntity<?> getListAgent(@PathVariable("idCompany") String idCompany) {
		List<?> listData = new ArrayList<>();
		try {
			listData = ticketsService.getListRelations(idCompany);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

}
