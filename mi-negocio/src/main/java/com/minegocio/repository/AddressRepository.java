package com.minegocio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.minegocio.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	@Query("FROM Address a WHERE a.customer.id= :id")
	List<Address> gettAddressByUserIdNumber(@Param("id") int id);
	
	

}
