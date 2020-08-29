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
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.service.CompaniesService;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

	@Autowired
	private CompaniesService companiesService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAll(){
		List<Companies> listData = new ArrayList<>();
		try {
			listData = companiesService.getListCompanies();
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}
	
	@PostMapping("/insert")
	public ResponseEntity<?> insert(@RequestBody String data) {
		Companies companies = new Companies();
		try {
			companies = new ObjectMapper().readValue(data, Companies.class);
			companies = companiesService.insert(companies);
		} catch (Exception e) {
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(companies, HttpStatus.CREATED);
	}

	@GetMapping("/get-companies")
	public ResponseEntity<?> getCompaniesByCode(@RequestBody String code) {
		Companies companies = new Companies();
		try {
			companies = new ObjectMapper().readValue(code, Companies.class);
			companies = companiesService.getCompanyByName(companies);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(companies, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody String data) {
		Companies companies = new Companies();
		try {
			companies = new ObjectMapper().readValue(data, Companies.class);
			companies = companiesService.update(companies);
		} catch (Exception e) {
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(companies, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody String data) {
		Companies companies = new Companies();
		String result ="";
		try {
			companies = new ObjectMapper().readValue(data, Companies.class);
			companies = companiesService.getCompanyByName(companies);
			companiesService.delete(companies.getId());
			result = new ObjectMapper().writeValueAsString("Delete Success");
		} catch (Exception e) {
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
