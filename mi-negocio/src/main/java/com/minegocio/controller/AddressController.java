package com.minegocio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minegocio.model.Address;

import com.minegocio.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/customers/address")
@Tag(name = "address-controller", description = "Add, delete and edit addresses ")
public class AddressController {

	@Autowired
	private AddressService service;

	@ApiResponse(description = "the list of sub-addresses of a customer", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Address.class))))
	@Operation(description = "get all addresses of a customer", summary = "calling this endpoint will give you a list of address of the customer that holds the given id")
	@GetMapping("/{id}")
	public ResponseEntity getAddressesOfCustomer(@PathVariable int id) {
		List<Address> a = service.getAddressesByCustomerId(id);
		System.out.println(a);
		return ResponseEntity.ok(a);
	}

	@ApiResponse(description = "Add a new subAddress ", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Address.class))))
	@Operation(description = "Add a new subAddress to the customer with the given id", summary = "This endpoint will allow you to add a new address to the customer that has the given id")
	@PostMapping("/{id}")
	public ResponseEntity saveAddressOfCustomer(@PathVariable int id, @RequestBody Address address) {
		Address a = service.saveAddressByCustomerId(id, address);
		if (a != null) {
			return ResponseEntity.ok(a);
		}
		return new ResponseEntity("Pleas make sure of the ID!", HttpStatus.BAD_REQUEST);

	}

}
