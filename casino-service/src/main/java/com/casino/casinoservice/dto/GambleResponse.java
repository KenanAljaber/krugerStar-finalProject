package com.casino.casinoservice.dto;

import com.casino.casinoservice.entity.State;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GambleResponse {
    
private int customerId;

private State state;

private double newCustomerBalance;

private int customerLuckyNumber;

private int winnerNumber;

}
