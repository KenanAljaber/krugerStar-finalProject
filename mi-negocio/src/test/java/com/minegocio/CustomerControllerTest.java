package com.minegocio;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minegocio.model.Customer;
import com.minegocio.service.CustomerServiceImpl;


@WebMvcTest
public class CustomerControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	
	@MockBean
	private CustomerServiceImpl service;
	
	
	@Test
	public void getAllCustomers() throws Exception {
		List<Customer> customers=new ArrayList<>();
		customers.add(new Customer());
		customers.add(new Customer());
	
		when(service.findAll()).thenReturn(customers);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/customers")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$",hasSize(2) )).andDo(print());
	}
	
	
	@Test
	public void testSaveCustomerControllrt () {
		
	}
	
	
	
	
	


}
