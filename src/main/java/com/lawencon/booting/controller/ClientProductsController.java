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
import com.lawencon.booting.model.ClientProducts;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.service.ClientProductsService;

@RestController
@RequestMapping("/client-products")
public class ClientProductsController {

	@Autowired
	private ClientProductsService clientProductsService;

	@GetMapping("/")
	public ResponseEntity<?> getAll() {
		List<ClientProducts> listData = new ArrayList<>();
		try {
			listData = clientProductsService.getListClientProducts();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(listData, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> insert(@RequestBody String data) throws JsonProcessingException {
		ClientProducts clientProducts = new ClientProducts();
		String respon = "";
		try {
			clientProducts = new ObjectMapper().readValue(data, ClientProducts.class);
			clientProducts = clientProductsService.insert(clientProducts);
		}catch (DataIntegrityViolationException e) {
			respon = new ObjectMapper().writeValueAsString("duplicate key value violates unique constraint");
			return new ResponseEntity<>(respon, HttpStatus.BAD_GATEWAY);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(clientProducts, HttpStatus.CREATED);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<?> getClientProducts(@PathVariable("name") String name) {
		ClientProducts clientProducts = new ClientProducts();
		Companies comp = new Companies();
		comp.setName(name);
		clientProducts.setIdCompany(comp);
		List<ClientProducts> listData = new ArrayList<>();
		try {
			listData = clientProductsService.getListByCompany(clientProducts);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(listData, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody String data) {
		ClientProducts clientProducts = new ClientProducts();
		try {
			clientProducts = new ObjectMapper().readValue(data, ClientProducts.class);
			clientProducts = clientProductsService.update(clientProducts);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(clientProducts, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id){
		String result = "";
		try {
			clientProductsService.delete(id);
			result = new ObjectMapper().writeValueAsString("Delete Success");
		} catch (PersistenceException e) {
			try {
				clientProductsService.deletePath(id);
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
