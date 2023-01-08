package com.minegocio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.minegocio.model.Address;
import com.minegocio.model.Customer;
import com.minegocio.model.IdType;
import com.minegocio.repository.CustomerRepository;
import com.minegocio.service.CustomerServiceImpl;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerServiceTest {
	
	
	@Autowired
	private CustomerRepository customerRepo;
	

	@Test
	@Order(1)
	void saveCustomerWithData() {

		Customer c=new Customer( "demo",IdType.RUC, "096999999", "demo@hotmail.com", "099999999","");
		Customer customer=customerRepo.save(c);
		System.out.println(customer);
		CustomerServiceImpl service=new CustomerServiceImpl(customerRepo);
		List<Customer> customers=service.findAll();
		Customer lastCustomer=customers.get(customers.size()-1);
		
		assertEquals(c.getName(),lastCustomer.getName());
		assertEquals(c.getIdNumber(),lastCustomer.getIdNumber());
	}
	
	@Test
	@Order(2)
	void saveCustomerAsFinalCustomer() {
		Customer c=new Customer();
		customerRepo.save(c);
		
		CustomerServiceImpl service =new CustomerServiceImpl (customerRepo);
		List<Customer> customers=service.findAll();
		Customer lastAdded = customers.get(customers.size()-1);
		
		assertEquals("consumidor final",lastAdded.getName());
		assertEquals("consumidorfinal@demo.com",lastAdded.getEmail());
		
		
	}
	
	@Test
	@Order(100)
	void deleteAllUsers() {
		customerRepo.deleteAll();
		CustomerServiceImpl service=new CustomerServiceImpl(customerRepo);
		
		List<Customer> customers=service.findAll();
		
		assertEquals(0, customers.size());
	}
	
	
	@Test
	@Order(3)
	void saveUserWithAddress() {
		Customer c=new Customer("kenan", IdType.CEDULA, "asdasd","asdasd","asdasd", "asdasd");
		c.add(new Address("guayas", "guayaquil",""));
		Customer customer=customerRepo.save(c);
		System.out.println(customer);
		CustomerServiceImpl service=new CustomerServiceImpl(customerRepo);
		List<Customer> customers=service.findAll();
		Customer lastCustomer=customers.get(customers.size()-1);
		
		assertEquals(c.getName(),lastCustomer.getName());
		assertEquals(c.getOtherAddresses().size(),1);
	}

}
