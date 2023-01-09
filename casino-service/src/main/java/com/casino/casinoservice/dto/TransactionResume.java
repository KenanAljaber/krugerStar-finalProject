package com.casino.casinoservice.dto;

import java.util.Date;

import com.casino.casinoservice.entity.State;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResume {
    private int transactionId;

    private State state;

  
    private int randomLuckyNumber;


    private double stateAmount;

 
    private Date date;
}
