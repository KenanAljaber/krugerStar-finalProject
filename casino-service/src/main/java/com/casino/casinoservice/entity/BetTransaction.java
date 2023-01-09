package com.casino.casinoservice.entity;

import java.util.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "transaction_id")
@AllArgsConstructor
@NoArgsConstructor
public class BetTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "customer_name")
    private String customerName;

    private State state;

    @Column(name = "random_lucky_number")
    private int randomLuckyNumber;

    @Column(name = "state_amount")
    private double stateAmount;

    @Temporal(TemporalType.DATE)
    @Column(name="created")
    private Date date;



}
