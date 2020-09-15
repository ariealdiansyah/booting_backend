package com.lawencon.booting.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.lawencon.booting.model.Priorities;
import com.lawencon.booting.service.PrioritiesService;

@RestController
@RequestMapping("/priorities")
public class PrioritiesController {

	@Autowired
	private PrioritiesService prioritiesService;

	@GetMapping("/all")
	public ResponseEntity<?> getPriorities() {
		List<Priorities> listPriorities = new ArrayList<>();
		try {
			listPriorities = prioritiesService.getListPriorities();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(listPriorities, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<?> getPrioritiesActive() {
		List<Priorities> listPriorities = new ArrayList<>();
		try {
			listPriorities = prioritiesService.getListPrioritiesActive();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(listPriorities, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> insert(@RequestBody String data) {
		Priorities Priorities = new Priorities();
		try {
			Priorities = new ObjectMapper().readValue(data, Priorities.class);
			Priorities = prioritiesService.insert(Priorities);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(Priorities, HttpStatus.CREATED);
	}

	@GetMapping("/{code}")
	public ResponseEntity<?> getPrioritiesByCode(@PathVariable("code") String code) {
		Priorities priorities = new Priorities();
		priorities.setCode(code);
		try {
			priorities = prioritiesService.getPrioritiesByCode(priorities);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(priorities, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody String data) {
		Priorities priorities = new Priorities();
		try {

			priorities = new ObjectMapper().readValue(data, Priorities.class);
			priorities = prioritiesService.update(priorities);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(priorities, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		String result = "";
		try {
			prioritiesService.deletePriorities(id);
			result = new ObjectMapper().writeValueAsString("Delete Success");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
