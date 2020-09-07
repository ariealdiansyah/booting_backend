package com.lawencon.booting.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.booting.model.Accounts;
import com.lawencon.booting.service.AccountsService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountsService accountService;
	
	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody String data) {
		Accounts acc = new Accounts();
		try {
			acc = new ObjectMapper().readValue(data, Accounts.class);
			acc = accountService.insert(acc);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
//		String respon = "";
//		try {
//			respon = new ObjectMapper().writeValueAsString("Admin berhasil ditambah");
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
		return new ResponseEntity<>(acc, HttpStatus.CREATED);
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
	
	@GetMapping("/all-active")
	public ResponseEntity<?> getAllActive(){
		List<Accounts> listData = new ArrayList<>();
		try {
			listData = accountService.getListAccountsActive();
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}
	
	@GetMapping("/get-accounts/{code}")
	public ResponseEntity<?> getAccountsByCode(@PathVariable("data") String code) {
		Accounts account = new Accounts();
		account.setEmail(code);
		try {
//			account = new ObjectMapper().readValue(code, Accounts.class);
			account = accountService.findByEmail(account);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(account, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody String data) {
		Accounts acoount = new Accounts();
		try {
			acoount = new ObjectMapper().readValue(data, Accounts.class);
			acoount = accountService.update(acoount);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(acoount, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePath(@PathVariable("id") String id) {
		String result = "";
		try {
			accountService.deleteAccounts(id);
			result = new ObjectMapper().writeValueAsString("Delete Success");
		} catch (PersistenceException e) {
			try {
				accountService.deletePath(id);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping("/forgot")
	public ResponseEntity<?> forgotPass(@RequestBody String data){
		Accounts acc = new Accounts();
		try {
			acc = new ObjectMapper().readValue(data, Accounts.class);
			acc = accountService.forgotPass(acc);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(acc, HttpStatus.OK);
	}
}
