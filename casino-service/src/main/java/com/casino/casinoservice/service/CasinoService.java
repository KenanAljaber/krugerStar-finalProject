package com.casino.casinoservice.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casino.casinoservice.dto.GambleOrder;
import com.casino.casinoservice.dto.GambleResponse;
import com.casino.casinoservice.dto.TransactionResume;
import com.casino.casinoservice.entity.BetTransaction;

public interface CasinoService {
    
    GambleResponse bet (GambleOrder order);

    BetTransaction save (BetTransaction bet);

    List<BetTransaction> getAllTransaction();

    
    List<BetTransaction> getAllTransactionsByCustomerId(int id);

    List<TransactionResume> getResumeOfAllCustomerTransactionById(int id);


}
