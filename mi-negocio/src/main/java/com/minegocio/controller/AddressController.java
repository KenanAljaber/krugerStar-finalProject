package com.minegocio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minegocio.model.Address;
import com.minegocio.service.AddressService;

@RestController
@RequestMapping("api/customers/address")
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	
	@GetMapping("/{id}")
	public ResponseEntity getAddressesOfCustomer(@PathVariable int id) {
		return ResponseEntity.ok(service.getAddressesByCustomerId(id));
	}
	
	@PostMapping("/{id}")
	public ResponseEntity saveAddressOfCustomer (@PathVariable int id, @RequestBody Address address) {
		return ResponseEntity.ok(service.saveAddressByCustomerId(id, address));
	}

}
