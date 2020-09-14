package com.lawencon.booting.controller;

import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.Users;
import com.lawencon.booting.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		List<Users> listData = new ArrayList<>();
		try {
			listData = usersService.getListUsers();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/all-customer/{data}")
	public ResponseEntity<?> getAllCustomer(@PathVariable("data") String data) {
		Users us = new Users();
		Companies com = new Companies();
		com.setName(data);
		us.setIdCompany(com);
		List<Users> listData = new ArrayList<>();
		try {
			listData = usersService.getListUsersByClient(us);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/all-agent")
	public ResponseEntity<?> getAllAgent() {
		List<Users> listData = new ArrayList<>();
		try {
			listData = usersService.getListAgents();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/all-clients")
	public ResponseEntity<?> getAllClients() {
		List<Users> listData = new ArrayList<>();
		try {
			listData = usersService.getListClients();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/all-active")
	public ResponseEntity<?> getAllActive() {
		List<Users> listData = new ArrayList<>();
		try {
			listData = usersService.getListUsersActive();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody String data) {
		Users users = new Users();
		try {
			users = new ObjectMapper().readValue(data, Users.class);
			users = usersService.insert(users);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(users, HttpStatus.CREATED);
	}

	@GetMapping("/get-users/{code}")
	public ResponseEntity<?> getUserByCode(@PathVariable("code") String code) {
		Users users = new Users();
		users.setNip(code);
		try {
			users = usersService.getUserByNip(users);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> update(@RequestPart(value = "users") String data,
			@RequestPart(value = "file", required = false) MultipartFile file) throws URISyntaxException {
		Users users = new Users();
		try {
			users = new ObjectMapper().readValue(data, Users.class);
			users = usersService.update(users, file);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletePath(@PathVariable("id") String id) {
		String result = "";
		try {
			usersService.delete(id);
			result = new ObjectMapper().writeValueAsString("Delete Success");
		} catch (PersistenceException e) {
			try {
				usersService.deletePath(id);
				result = new ObjectMapper().writeValueAsString("Delete Success");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
