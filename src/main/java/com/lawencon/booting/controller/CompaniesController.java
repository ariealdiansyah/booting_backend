package com.lawencon.booting.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

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
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.service.CompaniesService;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

	@Autowired
	private CompaniesService companiesService;

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		List<Companies> listData = new ArrayList<>();
		try {
			listData = companiesService.getListCompanies();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllActive() {
		List<Companies> listData = new ArrayList<>();
		try {
			listData = companiesService.getListCompaniesActive();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> insert(@RequestBody String data) throws JsonProcessingException {
		Companies companies = new Companies();
		String respon = "";
		try {
			companies = new ObjectMapper().readValue(data, Companies.class);
			companies = companiesService.insert(companies);
		}catch (DataIntegrityViolationException e) {
			respon = new ObjectMapper().writeValueAsString("duplicate key value violates unique constraint");
			return new ResponseEntity<>(respon, HttpStatus.BAD_GATEWAY);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(companies, HttpStatus.CREATED);
	}

	@GetMapping("/{name}")
	public ResponseEntity<?> getCompaniesByName(@PathVariable("name") String name) {
		Companies companies = new Companies();
		companies.setName(name);
		try {
			companies = companiesService.getCompanyByName(companies);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(companies, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody String data) {
		Companies companies = new Companies();
		try {
			companies = new ObjectMapper().readValue(data, Companies.class);
			companies = companiesService.update(companies);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(companies, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePath(@PathVariable("id") String id) {
		String result = "";
		try {
			companiesService.delete(id);
			result = new ObjectMapper().writeValueAsString("Delete Success");
		} catch (PersistenceException e) {
			try {
				companiesService.deletePath(id);
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
