package com.minegocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minegocio.model.Address;
import com.minegocio.model.Customer;
import com.minegocio.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository repo;
	
	@Autowired
	private CustomerService customerService;

	@Override
	public List<Address> getAddressesByCustomerId(int CustomerId) {
		List<Address> addresses= repo.gettAddressByUserIdNumber(CustomerId);
		return addresses;
	}

	@Override
	public Address saveAddressByCustomerId(int customerId, Address address) {
		Customer c= customerService.findById(customerId);
		if(c!=null) {
			address.setCustomer(c);
			return repo.save(address);
			/*c.add(address);
			customerService.updateCustomerOtherAddressesById(customerId, c);*/
		}
		return null;

	}

}
