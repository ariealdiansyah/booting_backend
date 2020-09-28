package com.lawencon.booting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.booting.model.Accounts;
import com.lawencon.booting.model.ForgotPass;
import com.lawencon.booting.service.AccountsService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountsService accountService;

	@PostMapping("/")
	public ResponseEntity<?> insert(@RequestBody String data) throws JsonProcessingException {
		Accounts acc = new Accounts();
		String respon = "";
		try {
			acc = new ObjectMapper().readValue(data, Accounts.class);
			acc = accountService.insert(acc);
		}catch (DataIntegrityViolationException e) {
			respon = new ObjectMapper().writeValueAsString("duplicate key value violates unique constraint");
			return new ResponseEntity<>(respon, HttpStatus.BAD_GATEWAY);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(acc, HttpStatus.CREATED);
	}

	@GetMapping("/{email}")
	public ResponseEntity<?> getAccountsByEmail(@PathVariable("email") String email) {
		Accounts account = new Accounts();
		account.setEmail(email);
		try {
			account = accountService.findByEmail(account);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody String data) {
		ForgotPass accHelper = new ForgotPass();
		Accounts acoount = new Accounts();
		try {
			accHelper = new ObjectMapper().readValue(data, ForgotPass.class);
			acoount = accountService.update(accHelper);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		acoount.setPass(null);
		return new ResponseEntity<>(acoount, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		String result = "";
		try {
			accountService.deleteAccounts(id);
			result = new ObjectMapper().writeValueAsString("Delete Success");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/forgot")
	public ResponseEntity<?> forgotPass(@RequestBody String data) {
		Accounts acc = new Accounts();
		try {
			acc = new ObjectMapper().readValue(data, Accounts.class);
			acc = accountService.forgotPass(acc);
			acc.setPass(null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(acc, HttpStatus.OK);
	}
}
