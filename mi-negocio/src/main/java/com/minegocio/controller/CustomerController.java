package com.minegocio.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "customer-controller",description = "Add, delete and edit customers ")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@Autowired
	private JsonConverter jsonConverter;

	@ApiResponse(description = "the list of customers",responseCode = "200",content = @Content( array=@ArraySchema(schema = @Schema(implementation = Customer.class))))
	@Operation(description = "get all customers",summary = "calling this endpoint will give you all customers")
	@GetMapping( produces = "application/json")
	public ResponseEntity getAllCustomers() {
		List<Customer> customers = service.findAll();
		String json = jsonConverter.convertToJson(customers);
		return new ResponseEntity<>(json, HttpStatus.OK);

	}
	@ApiResponse(description = "The customer that holds the id",responseCode = "200",content = @Content( schema = @Schema(implementation = Customer.class)))
	@Operation(description = "get the specific customer",summary = "calling this endpoint will give you the customer who has the given id")
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity getById(@PathVariable int id) {
		Customer c = service.findById(id);
		if (c != null)
			return ResponseEntity.ok(c);
		else
			return ResponseEntity.notFound().build();
	}
	
	@ApiResponse(description = "The customer that has the given email",responseCode = "200",content = @Content( schema = @Schema(implementation = Customer.class)))
	@Operation(description = "get the specific customer",summary = "calling this endpoint will give you the customer who has the given email")
	@GetMapping(value="/byEmail/{email}", produces = "application/json")
	public ResponseEntity getByEmail (@PathVariable String email) {
		Customer c = service.findByEmail(email);
		if (c != null)
			return ResponseEntity.ok(c);
		else
			return  new ResponseEntity("Please make sure the email is correct!!",HttpStatus.BAD_REQUEST);
		
	}
	
	@ApiResponse(description = "The customer that has the given identification number",responseCode = "200",content = @Content( schema = @Schema(implementation = Customer.class)))
	@Operation(description = "get the specific customer",summary = "calling this endpoint will give you the customer who has the given identification number")
	@GetMapping(value="/byIdNumber/{idNumber}", produces = "application/json")
	public ResponseEntity getByIdNumber (@PathVariable String idNumber) {
		Customer c = service.findByIdNumber(idNumber);
		if (c != null)
			return ResponseEntity.ok(c);
		else
		return new ResponseEntity("Please make sure the Identification number is correct!!",HttpStatus.BAD_REQUEST);
		
	}
	
	@ApiResponse(description = "The customer that has the given name",responseCode = "200",content = @Content( schema = @Schema(implementation = Customer.class)))
	@Operation(description = "get the specific customer",summary = "calling this endpoint will give you the customer who has the given identification name")
	@GetMapping(value="/byName/{name}", produces = "application/json")
	public ResponseEntity getByName (@PathVariable String name) {
		List<Customer> c = service.findByName(name.toLowerCase());
		if (c != null && c.size()>0)
			return ResponseEntity.ok(c);
		else
			return new ResponseEntity(String.format("Could not find customers with the name %s",name)
			,HttpStatus.BAD_REQUEST);
		
	}
	
	@ApiResponse(description = "Add a new customer to the database",responseCode = "200",content = @Content( schema = @Schema(implementation = Customer.class)))
	@Operation(description = "Add a new customer",summary = "calling this endpoint will allow you to create a new customer by passing the json object through the request body")
	@PostMapping( produces = "application/json")
	public ResponseEntity saveCustomer(@RequestBody(required = false) Customer customer) {
		
		return customer!=null ?  ResponseEntity.ok(service.save(customer)) :
		 new ResponseEntity<>("Please make sure you set a correct Customer object as json in the body request!",HttpStatus.BAD_REQUEST);
	}

	@ApiResponse(description = "Delete all the customers from the database",responseCode = "200")
	@Operation(description = "Delete all the customers",summary = "calling this endpoint will empty the database by deleting all customers")
	@DeleteMapping(value="/all", produces = "application/json")
	public ResponseEntity deleteAll() {
		service.deleteAll();
		return ResponseEntity.ok().build();
	}

	@ApiResponse(description = "Delete a specific customer that holds the given id",responseCode = "200")
	@Operation(description = "Delete the specific customer",summary = "calling this endpoint will allow you to delete th customer that holds the given id, the id will be passed as a path variable")
	@DeleteMapping(value="/{id}", produces = "application/json")
	public ResponseEntity deleteById(@PathVariable int id) {
		return service.deleteById(id) ? ResponseEntity.ok().build() : 
		new ResponseEntity("Please make sure of the customer id!!",HttpStatus.BAD_REQUEST);
		//return ResponseEntity.ok().build();
	}
	
	@ApiResponse(description = "Update te information of the customer that holds the given id",responseCode = "200",content = @Content( schema = @Schema(implementation = Customer.class)))
	@Operation(description = "update the specific customer",summary = "calling this endpoint will allow you to update a specific customer that holds the given id, the id must be passed as a path variable")
	@PutMapping(value="/{id}", produces = "application/json")
	public ResponseEntity updateCustomer(@RequestBody Customer customer,@PathVariable int id) {
		Customer updated=service.updateCustomerInfoById(id, customer);
		if(updated!=null) {
			return ResponseEntity.ok(updated);
		}else {
			return new ResponseEntity("Please make sure the ID is correct!!",HttpStatus.BAD_REQUEST);
		}
		
	}

}
