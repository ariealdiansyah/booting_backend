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
import com.lawencon.booting.model.Users;
import com.lawencon.booting.service.ReportService;
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
	private UsersService userService;
	
	@GetMapping("/listClientAll")
	public ResponseEntity<?> listClientAll() {
		List<ReportAllListClient> listData = new ArrayList<>();
		try {
			listData = reportService.getReportListAllAgentRelations();
			jasperService.allListClient(listData);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}
	
	@GetMapping("/totalTicketAgent/{nip}")
	public ResponseEntity<?> totalTicketAgent(@PathVariable("nip") String nip, HttpServletResponse res) {
		Users users = new Users();
		List<ReportTotalTicketAgent> listTotalTicket = new ArrayList<>();
		users.setNip(nip);
		try {
			users = userService.getUserByNip(users);
			listTotalTicket = reportService.getReportTotalTicketAgent(users);
			jasperService.totalTicketAgent(listTotalTicket, users.getName(), users.getNip(), res);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listTotalTicket, HttpStatus.OK);
	}
}
