package com.minegocio.service;

import java.util.List;

import com.minegocio.dto.GambleOrder;
import com.minegocio.dto.GambleResponse;
import com.minegocio.dto.TransactionResume;

public interface GamblingService {
    
    /**
     * this method will take a gambling order with the customer data 
     * and it will place a bit by calling the casino microservice 
     * @param order is the Data Transfer Object DAO that will hold all customer's requierd information and the bet information
     * @return A GambleResponse object (DAO) which holds the result of the customer gambling
     */
    GambleResponse placeABit (GambleOrder order);

    /**
     * Find all the transactions (bets) made by the customer with the provided id
     * @param id is the id of the customer
     * @return a list of TransacionResume objects (DAO) which holds all the information about
     * the customer gambling history in the casino
     */
    List<TransactionResume> findCustomerTransactions(int id);

}
