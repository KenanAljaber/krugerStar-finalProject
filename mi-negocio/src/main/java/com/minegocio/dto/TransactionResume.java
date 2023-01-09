package com.minegocio.dto;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResume{
    private int transactionId;

    private State state;

  
    private int randomLuckyNumber;


    private double stateAmount;

 
    private Date date;
}