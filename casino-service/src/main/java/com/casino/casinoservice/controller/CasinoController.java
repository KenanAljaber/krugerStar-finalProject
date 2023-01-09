package com.casino.casinoservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casino.casinoservice.dto.GambleOrder;
import com.casino.casinoservice.dto.GambleResponse;
import com.casino.casinoservice.dto.TransactionResume;
import com.casino.casinoservice.entity.BetTransaction;
import com.casino.casinoservice.service.CasinoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/casino")
public class CasinoController {

    @Autowired
    private CasinoService service;

    @PostMapping(produces = "application/json")
    public ResponseEntity placeABet(@RequestBody GambleOrder order) {
        if (order != null) {
            GambleResponse result = service.bet(order);
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity("Please make sure the order is correct", HttpStatus.BAD_REQUEST);

    }

    @GetMapping(value="/all")
    public ResponseEntity getAllTransactions() {
        List<BetTransaction> list=service.getAllTransaction();
        if(list.size()>0){
            return new ResponseEntity(list,HttpStatus.OK);
        }
        return new ResponseEntity(new ArrayList<>(),HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/customer-transactions/{id}")
    public ResponseEntity getCustomersTransactions (@PathVariable int id){
        List<BetTransaction> transactions=service.getAllTransactionsByCustomerId(id);
        return transactions.size()>0 ? new ResponseEntity<>(transactions,HttpStatus.OK) : 
        new ResponseEntity<>("Could not find transacitons with thtis id!",HttpStatus.NOT_FOUND);

    }

    @GetMapping("/customer-transactions/resume/{id}")
    public ResponseEntity getCustomersTransactionsResume (@PathVariable int id){
        List<TransactionResume> transactions=service.getResumeOfAllCustomerTransactionById(id);
        return transactions.size()>0 ? new ResponseEntity<>(transactions,HttpStatus.OK) : 
        new ResponseEntity<>("Could not find transacitons with thtis id!",HttpStatus.NOT_FOUND);

    }

}
