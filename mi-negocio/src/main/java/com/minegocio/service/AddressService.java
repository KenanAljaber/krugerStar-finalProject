package com.minegocio.service;

import java.util.List;

import com.minegocio.model.Address;

public interface AddressService {

	/**
	 * get all additional addresses of a specific customer
	 * @param CustomerId is the id of the customer we want to look for
	 * @return a list of address objects
	 */
	List<Address> getAddressesByCustomerId(int CustomerId);
	
	/**
	 * Add a new additional address to the customer with the given id
	 * @param customerId is the id of the customer we want to add a new address
	 * @param address is the new address we want to add
	 * @return the new address we just added
	 */
	Address saveAddressByCustomerId (int customerId, Address address);

}
