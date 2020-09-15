package com.lawencon.booting.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.booting.model.ReportAllListClient;
import com.lawencon.booting.model.ReportTotalTicketAgent;
import com.lawencon.booting.model.TicketStatus;
import com.lawencon.booting.model.Users;
import com.lawencon.booting.service.ReportService;
import com.lawencon.booting.service.TicketsService;
import com.lawencon.booting.service.UsersService;
import com.lawencon.booting.utility.JasperReportService;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private JasperReportService jasperService;

	@Autowired
	private ReportService reportService;
	
	@Autowired
	private TicketsService ticketService;

	@Autowired
	private UsersService userService;

	@GetMapping("/listClient")
	public ResponseEntity<?> listClientAll(HttpServletResponse res) {
		List<ReportAllListClient> listData = new ArrayList<>();
		byte[] baos;
		try {
			listData = reportService.getReportListAllAgentRelations();
			baos = jasperService.allListClient(listData, res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(baos, HttpStatus.OK);
	}

	@GetMapping("/totalTicketAgent/{nip}")
	public ResponseEntity<?> totalTicketAgent(@PathVariable("nip") String nip, HttpServletResponse res) {
		Users users = new Users();
		List<ReportTotalTicketAgent> listTotalTicket = new ArrayList<>();
		users.setNip(nip);
		byte[] baot;
		try {
			users = userService.getUserByNip(users);
			TicketStatus ticketStatus = ticketService.statusAgent(users);
			listTotalTicket = reportService.getReportTotalTicketAgent(users);
			baot = jasperService.totalTicketAgents(listTotalTicket, users.getName(), users.getNip(), ticketStatus, res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(baot, HttpStatus.OK);
	}
}
