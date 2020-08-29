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
import com.lawencon.booting.model.Accounts;
import com.lawencon.booting.service.AccountsService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountsService accountService;
	
	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody String data) {
		try {
			Accounts acc = new ObjectMapper().readValue(data, Accounts.class);
			accountService.insert(acc);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		String respon = "";
		try {
			respon = new ObjectMapper().writeValueAsString("Admin berhasil ditambah");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(respon, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		List<Accounts> listData = new ArrayList<>();
		try {
			listData = accountService.getListAccounts();
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}
}
