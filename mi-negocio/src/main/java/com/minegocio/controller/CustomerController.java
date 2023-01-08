package com.minegocio.controller;

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

import com.minegocio.model.Customer;
import com.minegocio.service.CustomerService;
import com.minegocio.service.CustomerServiceImpl;
import com.minegocio.utils.JsonConverter;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@Autowired
	private JsonConverter jsonConverter;

	@GetMapping( produces = "application/json")
	public ResponseEntity getAllCustomers() {
		List<Customer> customers = service.findAll();
		String json = jsonConverter.convertToJson(customers);
		return new ResponseEntity<>(json, HttpStatus.OK);

	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity getById(@PathVariable int id) {
		Customer c = service.findById(id);
		if (c != null)
			return ResponseEntity.ok(c);
		else
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping(value="/byEmail/{email}", produces = "application/json")
	public ResponseEntity getByEmail (@PathVariable String email) {
		Customer c = service.findByEmail(email);
		if (c != null)
			return ResponseEntity.ok(c);
		else
			return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping(value="/byIdNumber/{idNumber}", produces = "application/json")
	public ResponseEntity getByIdNumber (@PathVariable String idNumber) {
		Customer c = service.findByIdNumber(idNumber);
		if (c != null)
			return ResponseEntity.ok(c);
		else
			return ResponseEntity.notFound().build();
		
	}
	
	@GetMapping(value="/byName/{name}", produces = "application/json")
	public ResponseEntity getByName (@PathVariable String name) {
		List<Customer> c = service.findByName(name.toLowerCase());
		if (c != null)
			return ResponseEntity.ok(c);
		else
			return ResponseEntity.notFound().build();
		
	}
	

	@PostMapping( produces = "application/json")
	public ResponseEntity saveCustomer(@RequestBody Customer customer) {
		
		return ResponseEntity.ok(service.save(customer));
	}

	@DeleteMapping(value="/all", produces = "application/json")
	public ResponseEntity deleteAll() {
		service.deleteAll();
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value="/{id}", produces = "application/json")
	public ResponseEntity deleteById(@PathVariable int id) {
		service.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value="/{id}", produces = "application/json")
	public ResponseEntity updateCustomer(@RequestBody Customer customer,@PathVariable int id) {
		Customer updated=service.updateCustomerInfoById(id, customer);
		if(updated!=null) {
			return ResponseEntity.ok(updated);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}

}
