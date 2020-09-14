package com.lawencon.booting.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.Products;
import com.lawencon.booting.service.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductsService productsService;

	@GetMapping("/all")
	public ResponseEntity<?> getAll() {
		List<Products> listProducts = new ArrayList<>();
		try {
			listProducts = productsService.getListProducts();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(listProducts, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllActive() {
		List<Products> listProducts = new ArrayList<>();
		try {
			listProducts = productsService.getListProductsActive();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(listProducts, HttpStatus.OK);
	}

	@GetMapping("/company/{data}")
	public ResponseEntity<?> getByCompany(@PathVariable("data") String data) {
		List<Products> listProducts = new ArrayList<>();
		Companies comp = new Companies();
		comp.setName(data);
		try {
			listProducts = productsService.getListByCompany(comp);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(listProducts, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<?> insert(@RequestBody String data) {
		Products products = new Products();
		try {
			products = new ObjectMapper().readValue(data, Products.class);
			products = productsService.insert(products);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
		return new ResponseEntity<>(products, HttpStatus.CREATED);
	}

	@GetMapping("/{code}")
	public ResponseEntity<?> getProductsByCode(@PathVariable("code") String code) {
		Products products = new Products();
		products.setCode(code);
		try {
			products = productsService.getProductsByCode(products);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody String data) {
		Products roles = new Products();
		try {
			roles = new ObjectMapper().readValue(data, Products.class);
			roles = productsService.update(roles);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
		}

		return new ResponseEntity<>(roles, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePath(@PathVariable("id") String id) {
		String result = "";
		try {
			productsService.deleteProducts(id);
			result = new ObjectMapper().writeValueAsString("Delete Success");
		} catch (PersistenceException e) {
			try {
				productsService.deletePath(id);
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
