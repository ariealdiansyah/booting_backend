package com.lawencon.booting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import com.lawencon.booting.model.AgentRelations;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.Users;
import com.lawencon.booting.service.AgentRelationsService;

@RestController
@RequestMapping("/agent-relations")
public class AgentRelationsController {

	@Autowired
	private AgentRelationsService agentRelationsService;

	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		List<AgentRelations> listData = new ArrayList<>();
		try {
			listData = agentRelationsService.getListAgentRelations();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> insert(@RequestBody String data) throws JsonProcessingException {
		AgentRelations agentRelations = new AgentRelations();
		String respon = "";
		try {
			agentRelations = new ObjectMapper().readValue(data, AgentRelations.class);
			agentRelations = agentRelationsService.insert(agentRelations);
		}catch (DataIntegrityViolationException e) {
			respon = new ObjectMapper().writeValueAsString("duplicate key value violates unique constraint");
			return new ResponseEntity<>(respon, HttpStatus.BAD_GATEWAY);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(agentRelations, HttpStatus.CREATED);
	}

	@GetMapping("/{nip}")
	public ResponseEntity<?> getAgentRelationsByCode(@PathVariable("nip") String nip) {
		AgentRelations agentRelations = new AgentRelations();
		Users us = new Users();
		us.setNip(nip);
		agentRelations.setIdAgent(us);
		List<AgentRelations> listData = new ArrayList<>();
		try {
			listData = agentRelationsService.getListByIdUser(agentRelations);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/company/{name}")
	public ResponseEntity<?> getAgentRelationsByCompany(@PathVariable("name") String name) {
		Companies comp = new Companies();
		Users agent = new Users();
		comp.setName(name);
		try {
			agent = agentRelationsService.getAgentByCompany(comp);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(agent, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody String data) {
		AgentRelations agentRelations = new AgentRelations();
		try {
			agentRelations = new ObjectMapper().readValue(data, AgentRelations.class);
			agentRelations = agentRelationsService.update(agentRelations);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(agentRelations, HttpStatus.OK);
	}

}
