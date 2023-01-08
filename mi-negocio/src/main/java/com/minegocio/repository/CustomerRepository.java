package com.minegocio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.minegocio.model.Customer;
@Repository("customerRepo")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	@Query("FROM Customer c WHERE lower(c.email)= :email")
	Customer findByEmail(@Param("email") String email);
	
	@Query("FROM Customer c WHERE lower(c.name)= :name")
	List<Customer> findByName(@Param("name") String name);
	
	@Query("From Customer c WHERE c.idNumber= :idNumber")
	Customer findByIdNumber(@Param("idNumber") String idNumber);

}
