package com.minegocio.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int address_id;
	
	private String province;
	
	private String city;
	
	@Column(name = "address_in_detail",columnDefinition = "varchar(255)")
	private String addressInDetail="not defiend";
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private Customer customer;
	
	

	public Address() {
	}

	public Address(String province, String city, String addressInDetail) {
		this.province = province;
		this.city = city;
		this.addressInDetail = addressInDetail;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressInDetail() {
		return addressInDetail;
	}

	public void setAddressInDetail(String addressInDetail) {
		this.addressInDetail = addressInDetail;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Address [address_id=" + address_id + ", province=" + province + ", city=" + city + ", addressInDetail="
				+ addressInDetail + "]";
	}
	
	


	

	
	

}
