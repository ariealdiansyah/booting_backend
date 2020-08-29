package com.lawencon.booting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.booting.model.AgentRelations;
import com.lawencon.booting.service.AgentRelationsService;

@RestController
@RequestMapping("/agent-relations")
public class AgentRelationsController {

	@Autowired
	private AgentRelationsService agentRelationsService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		List<AgentRelations> listData = new ArrayList<>();
		try {
			listData = agentRelationsService.getListAgentRelations();
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody String data) {
		AgentRelations agentRelations = new AgentRelations();
		try {
			agentRelations = new ObjectMapper().readValue(data, AgentRelations.class);
			agentRelations = agentRelationsService.insert(agentRelations);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(agentRelations, HttpStatus.CREATED);
	}

	@GetMapping("/get-agent-relations")
	public ResponseEntity<?> getAgentRelationsByCode(@RequestBody String code) {
		AgentRelations agentRelations = new AgentRelations();
		List<AgentRelations> listData = new ArrayList<>();
		try {
			agentRelations = new ObjectMapper().readValue(code, AgentRelations.class);
			listData = agentRelationsService.getListByIdUser(agentRelations);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@PutMapping("/update")
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

//	@DeleteMapping("/delete")
//	public ResponseEntity<?> delete(@RequestBody String data) {
//		AgentRelations agentRelations = new AgentRelations();
//		String result ="";
//		try {
//			agentRelations = new ObjectMapper().readValue(data, AgentRelations.class);
//			agentRelations = agentRelationsService.getCompanyByCode(agentRelations);
//			agentRelationsService.delete(agentRelations.getId());
//			result = new ObjectMapper().writeValueAsString("Delete Success");
//		} catch (Exception e) {
//			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
//		}
//
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}
}
