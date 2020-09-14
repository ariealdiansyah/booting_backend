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
import com.lawencon.booting.model.Classifications;
import com.lawencon.booting.service.ClassificationsService;

@RestController
@RequestMapping("/classifications")
public class ClassificationsController {

	@Autowired
	private ClassificationsService ClassificationsService;

	@GetMapping("/all")
	public ResponseEntity<?> getClassifications() {
		List<Classifications> listClassifications = new ArrayList<>();
		try {
			listClassifications = ClassificationsService.getListClassifications();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(listClassifications, HttpStatus.OK);
	}

	@GetMapping("/all-active")
	public ResponseEntity<?> getClassificationsActive() {
		List<Classifications> listClassifications = new ArrayList<>();
		try {
			listClassifications = ClassificationsService.getListClassificationsActive();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(listClassifications, HttpStatus.OK);
	}

	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody String data) {
		Classifications classifications = new Classifications();
		try {
			classifications = new ObjectMapper().readValue(data, Classifications.class);
			classifications = ClassificationsService.insert(classifications);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(classifications, HttpStatus.CREATED);
	}

	@GetMapping("/getclassifications")
	public ResponseEntity<?> getClassificationsByCode(@RequestBody String code) {
		Classifications classifications = new Classifications();
		try {
			classifications = new ObjectMapper().readValue(code, Classifications.class);
			classifications = ClassificationsService.getClassificationsByCode(classifications);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(classifications, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody String data) {
		Classifications classifications = new Classifications();
		try {
			classifications = new ObjectMapper().readValue(data, Classifications.class);
			classifications = ClassificationsService.update(classifications);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(classifications, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody String data) {
		Classifications classifications = new Classifications();
		String result = "";
		try {
			classifications = new ObjectMapper().readValue(data, Classifications.class);
			classifications = ClassificationsService.getClassificationsByCode(classifications);
			ClassificationsService.deleteClassifications(classifications.getId());
			result = new ObjectMapper().writeValueAsString("Delete Success");
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
