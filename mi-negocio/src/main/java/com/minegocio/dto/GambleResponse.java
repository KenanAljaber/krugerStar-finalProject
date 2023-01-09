package com.minegocio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GambleResponse {
    
private int customerId;

private State state;

private double newCustomerBalance;

private int customerLuckyNumber;

private int winnerNumber;

}
