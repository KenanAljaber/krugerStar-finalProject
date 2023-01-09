package com.casino.casinoservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.casino.casinoservice.entity.BetTransaction;

public interface CasinoRepository extends JpaRepository<BetTransaction,Integer> {
    @Query("FROM BetTransaction b WHERE b.customerId= :id")
    List<BetTransaction> getAllTransactionsByCustomerId(@Param("id") int id);
    
}
