package com.minegocio.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Table(name="customer")
@Entity
@DynamicInsert
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name = "name",unique = false,columnDefinition = "varchar(255)",nullable = false)
	private String name="consumidor final";
	
	@Column(name="id_type",nullable = false)
	private IdType type=IdType.UNDEFINED;
	

	@Column(name = "id_number",unique = true,columnDefinition = "varchar(20)",nullable = false)
	private String idNumber="0000000000";
	

	@Column(name = "email",unique = true,columnDefinition = "varchar(255)",nullable = false)
	private String email="consumidorfinal@demo.com";
	

	@Column(name = "phone_number",unique = true,columnDefinition = "varchar(20)",nullable = false)
	private String phoneNumber="0000000000";
	
	@Column(name="main_address")
	private String mainAddress="No se ha definido una direccion";
	
	
	@OneToMany(mappedBy = "customer",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Address> otherAddresses=new HashSet<>();
	
	


	public Customer() {

	}


	public Customer(String name, IdType type, String idNumber, String email, String phoneNumber, String mainAddress) {
		this.name = name;
		this.type = type;
		this.idNumber = idNumber;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.mainAddress = mainAddress;
	}
	
	
	public void add(Address address) {
		if(otherAddresses==null) {
			otherAddresses= new HashSet<>();
		}
		if(!otherAddresses.contains(address)) {
			otherAddresses.add(address);
			address.setCustomer(this);
		}
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public IdType getType() {
		return type;
	}


	public void setType(IdType type) {
		this.type = type;
	}


	public String getIdNumber() {
		return idNumber;
	}


	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getMainAddress() {
		return mainAddress;
	}


	public void setMainAddress(String mainAddress) {
		this.mainAddress = mainAddress;
	}


	public Set<Address> getOtherAddresses() {
		return otherAddresses;
	}


	public void setOtherAddresses(Set<Address> otherAddresses) {
		this.otherAddresses = otherAddresses;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", type=" + type + ", idNumber=" + idNumber + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", mainAddress=" + mainAddress + "]";
	}









	


	

}
