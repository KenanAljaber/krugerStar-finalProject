package com.minegocio.service;

import java.util.List;

import com.minegocio.model.Customer;

public interface CustomerService {
	
	/**
	 * find all the customers in the databse
	 * @return a list of all customers in the DB
	 */
	List<Customer> findAll();
	
	/**
	 * find the user that has the given id
	 * @param id is the id of the user we are looking for
	 * @return the user that holds the same given id 
	 */
	Customer findById(int id);

	/**
	 * find the all customers with the given name note:(customer name can be repeated in the db)
	 * @param name is the name of the customer we are looling for
	 * @return list of all customers with the same name 
	 */
	List<Customer> findByName (String name);
	
	/**
	 * find the customer that has the given email
	 * @param email the email of the customer we are looking for
	 * @return the customer that holds the email
	 */
	Customer findByEmail(String email);
	
	/**
	 * find the customer that holds the given identification number
	 * @param idNumber is the identification number (CEDULA,RUC) of the customer we are looking for
	 * @return the customer with the same identification number
	 */
	Customer findByIdNumber(String idNumber);
	
	/**
	 * save a new customer to the database 
	 * @param customer the customer we want to add
	 * @return the customer we just added
	 */
	Customer save(Customer customer);
	
	// delete all customers in database
	void deleteAll ();
	
	/**
	 * delete the customer that holds the given id
	 * @param id the id of the customer we want to delete
	 * @return true if we found the customer and has been successfully deleted and false if not
	 */
	boolean deleteById(int id);
	
	/**
	 * update the customer perosnal info 
	 * @param id the id of the customer we want to update
	 * @param customer the customer that holds the updated values
	 * @return the updated customer
	 */
	Customer updateCustomerInfoById(int id,Customer customer);
	
	/**
	 * update the additional addresses list
	 * @param id of the cusotmer we want to proccess
	 * @param updatedCustomer the customer that holds the updated values
	 * @return the updated customer
	 */
	Customer updateCustomerOtherAddressesById (int id,Customer updatedCustomer);

	/**
	 * only update the customer balance
	 * @param customerId is the id of the customer we are looking for
	 * @param newCustomerBalance is the new balance we will set to the customer
	 */

    void updateCustomerBalance(int customerId, double newCustomerBalance);

}
