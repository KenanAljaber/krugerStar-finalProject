package com.minegocio.service;

import java.util.List;

import com.minegocio.model.Address;

public interface AddressService {
	
	List<Address> getAddressesByCustomerId(int CustomerId);
	
	Address saveAddressByCustomerId (int customerId, Address address);

}
