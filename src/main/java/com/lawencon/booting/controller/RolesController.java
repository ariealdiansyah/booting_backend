package com.lawencon.booting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.booting.model.Roles;
import com.lawencon.booting.service.RolesService;

@RestController
@RequestMapping("/roles")
public class RolesController {

	@Autowired
	private RolesService rolesService;

	@GetMapping("/all")
	public ResponseEntity<?> getRoles() {
		List<Roles> listRoles = new ArrayList<>();
		try {
			listRoles = rolesService.getListRoles();
		} catch (Exception e) {
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(listRoles, HttpStatus.OK);
	}

	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody String data) {
		Roles roles = new Roles();
		try {
			roles = new ObjectMapper().readValue(data, Roles.class);
			roles = rolesService.insert(roles);
		} catch (Exception e) {
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(roles, HttpStatus.CREATED);
	}

	@GetMapping("/getrole")
	public ResponseEntity<?> getRolesByCode(@RequestBody String code) {
		Roles roles = new Roles();
		try {
			roles = new ObjectMapper().readValue(code, Roles.class);
			roles = rolesService.getRolesByCode(roles);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody String data) {
		Roles roles = new Roles();
		try {
			roles = new ObjectMapper().readValue(data, Roles.class);
			roles = rolesService.update(roles);
		} catch (Exception e) {
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(roles, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody String data) {
		Roles roles = new Roles();
		String result ="";
		try {
			roles = new ObjectMapper().readValue(data, Roles.class);
			roles = rolesService.getRolesByCode(roles);
			rolesService.deleteRoles(roles.getId());
			result = new ObjectMapper().writeValueAsString("Delete Success");
		} catch (Exception e) {
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
