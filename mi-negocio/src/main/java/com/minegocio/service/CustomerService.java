package com.minegocio.service;

import java.util.List;

import com.minegocio.model.Customer;

public interface CustomerService {
	
	List<Customer> findAll();
	
	Customer findById(int id);
	
	List<Customer> findByName (String name);
	
	Customer findByEmail(String email);
	
	Customer findByIdNumber(String idNumber);
	
	Customer save(Customer customer);
	
	void deleteAll ();
	
	void deleteById(int id);
	
	Customer updateCustomerInfoById(int id,Customer customer);
	
	Customer updateCustomerOtherAddressesById (int id,Customer updatedCustomer);

}
