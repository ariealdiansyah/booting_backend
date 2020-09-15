package com.lawencon.booting.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.lawencon.booting.model.Status;
import com.lawencon.booting.service.StatusService;

@RestController
@RequestMapping("/status")
public class StatusController {

	@Autowired
	private StatusService statusService;

	@GetMapping("/all")
	public ResponseEntity<?> getStatus() {
		List<Status> listStatus = new ArrayList<>();
		try {
			listStatus = statusService.getListStatuses();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(listStatus, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<?> getStatusActive() {
		List<Status> listStatus = new ArrayList<>();
		try {
			listStatus = statusService.getListStatusActive();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(listStatus, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> insert(@RequestBody String data) throws JsonProcessingException {
		Status status = new Status();
		String respon = "";
		try {
			status = new ObjectMapper().readValue(data, Status.class);
			status = statusService.insert(status);
		}catch (DataIntegrityViolationException e) {
			respon = new ObjectMapper().writeValueAsString("duplicate key value violates unique constraint");
			return new ResponseEntity<>(respon, HttpStatus.BAD_GATEWAY);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}

	@GetMapping("/{code}")
	public ResponseEntity<?> getStatusByCode(@PathVariable("code") String code) {
		Status status = new Status();
		status.setCode(code);
		try {
			status = statusService.getStatusesByCode(status);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody String data) {
		Status priorities = new Status();
		try {
			priorities = new ObjectMapper().readValue(data, Status.class);
			priorities = statusService.update(priorities);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(priorities, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		Status status = new Status();
		String result = "";
		try {
			status = new ObjectMapper().readValue(id, Status.class);
			status = statusService.getStatusesByCode(status);
			statusService.deleteStatuses(status.getId());
			result = new ObjectMapper().writeValueAsString("Delete Success");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
