package com.minegocio.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minegocio.model.Address;
import com.minegocio.model.Customer;
import com.minegocio.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	

	public CustomerServiceImpl(CustomerRepository repo) {
		this.customerRepo = repo;
	}

	@Override
	public List<Customer> findAll() {
		return customerRepo.findAll();
	}
	
	@Override
	public List<Customer> findByName(String name) {
		return Optional.of(customerRepo.findByName(name)).orElse(null);
	}

	@Override
	public Customer findByEmail(String email) {
		return customerRepo.findByEmail(email.toLowerCase()) !=null? customerRepo.findByEmail(email.toLowerCase()) : null;
	}

	@Override
	public Customer findByIdNumber(String idNumber) {
		return customerRepo.findByIdNumber(idNumber) !=null? customerRepo.findByIdNumber(idNumber) : null;
	}

	@Override
	public Customer findById(int id) {
		return customerRepo.findById(id).orElse(null);
	}

	@Override
	public Customer save(Customer c) {
		if(userExist(c))
		{
			return c;
		}
		Set<Address> addresses=c.getOtherAddresses();
		c.setOtherAddresses(null);
		for(Address a: addresses) {
			c.add(a);
		}
		return customerRepo.save(c);
	}

	private boolean userExist(Customer c) {
		System.out.println(c);
		Customer byEmail=findByEmail(c.getEmail().toLowerCase());
		Customer byIdNumber=this.findByIdNumber(c.getIdNumber());
		return   byEmail!=null || byIdNumber!=null;
	}

	@Override
	public void deleteAll() {
		customerRepo.deleteAll();

	}

	@Override
	public boolean deleteById(int id) {
		Customer c=this.findById(id);
		if (c!=null){
			customerRepo.deleteById(id);
			return true;
		}
		return false;
		
	}

	@Override
	public Customer updateCustomerInfoById(int id, Customer updatedCustomer) {
		Customer customer = this.findById(id);
		if (customer == null) {
			return null;
		}
		customer.setName(updatedCustomer.getName());
		customer.setEmail(updatedCustomer.getEmail());
		customer.setIdNumber(updatedCustomer.getIdNumber());
		customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
		customer.setMainAddress(updatedCustomer.getMainAddress());
		customer.setType(updatedCustomer.getType());

		return customerRepo.save(customer);
	}

	@Override
	public Customer updateCustomerOtherAddressesById(int id, Customer updatedCustomer) {
		Customer customer=this.findById(id);
		if(customer==null) {
			return null;
		}
		customer.setOtherAddresses(updatedCustomer.getOtherAddresses());
		return null;
	}

	@Override
	public void updateCustomerBalance(int customerId, double newCustomerBalance) {
		Customer c= findById(customerId);

		c.setBalance(newCustomerBalance);
		customerRepo.save(c);
		
	}
	
	



	

}
