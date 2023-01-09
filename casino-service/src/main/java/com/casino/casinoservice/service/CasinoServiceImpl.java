package com.casino.casinoservice.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casino.casinoservice.dto.GambleOrder;
import com.casino.casinoservice.dto.GambleResponse;
import com.casino.casinoservice.dto.TransactionResume;
import com.casino.casinoservice.entity.BetTransaction;
import com.casino.casinoservice.entity.State;
import com.casino.casinoservice.repository.CasinoRepository;

@Service
public class CasinoServiceImpl implements CasinoService {

    @Autowired
    private CasinoRepository repo;

    @Override
    public GambleResponse bet(GambleOrder order) {
        int winnerNumber = generateLuckyNumber();
        GambleResponse response = processOrder(order, winnerNumber);
        return response;
    }

    private int generateLuckyNumber() {
        return new Random().nextInt(5);
    }

    private GambleResponse processOrder(GambleOrder order, int winnerNumber) {
        GambleResponse resp = new GambleResponse();
        if (!sufficientBalance(order)) {
            resp = creatResponse(order, 0, order.getCustomerBalance(), State.INSUFFICIENT_BALANCE);
            createBetTransaction(resp, 0, order.getCustomerName());
            return resp;
        }
        if (Math.abs(winnerNumber - order.getLuckyNumber()) == 1) {
            double winAmount = order.getBetAmount() * 0.5;
            double newBalance = order.getCustomerBalance() + winAmount;
            resp = creatResponse(order, winnerNumber, newBalance, State.HALF_WIN);
            createBetTransaction(resp, winAmount, order.getCustomerName());
            return resp;
        }
        if (winnerNumber == order.getLuckyNumber()) {
            double winAmount = order.getBetAmount() * 2;
            double newBalance = order.getCustomerBalance() + winAmount;
            resp = creatResponse(order, winnerNumber, newBalance, State.DOUBLE_WIN);
            createBetTransaction(resp, winAmount, order.getCustomerName());
            return resp;
        }

        double newBalance = order.getCustomerBalance() - order.getBetAmount();
        resp = creatResponse(order, winnerNumber, newBalance, State.LOSE);
        createBetTransaction(resp, (order.getBetAmount() * -1), order.getCustomerName());
        return resp;

    }

    private GambleResponse creatResponse(GambleOrder order, int winnerNumber, double newBalance, State state) {
        return GambleResponse.builder().customerId(order.getCustomerId()).customerLuckyNumber(order.getLuckyNumber())
                .newCustomerBalance(newBalance).state(state).winnerNumber(winnerNumber).build();

    }

    private void createBetTransaction(GambleResponse resp, double winAmount, String customerName) {
        BetTransaction transaction = new BetTransaction();
        transaction.setCustomerId(resp.getCustomerId());
        transaction.setDate(new Date());
        transaction.setRandomLuckyNumber(resp.getWinnerNumber());
        transaction.setState(resp.getState());
        transaction.setStateAmount(winAmount);
        transaction.setCustomerName(customerName);
        save(transaction);
    }

    private boolean sufficientBalance(GambleOrder order) {
        return order.getCustomerBalance() >= order.getBetAmount() ? true : false;
    }

    @Override
    public BetTransaction save(BetTransaction bet) {

        return repo.save(bet);
    }

    @Override
    public List<BetTransaction> getAllTransaction() {

        return repo.findAll();
    }

    @Override
    public List<BetTransaction> getAllTransactionsByCustomerId(int id) {

        return repo.getAllTransactionsByCustomerId(id);
    }

    @Override
    public List<TransactionResume> getResumeOfAllCustomerTransactionById(int id) {
        List<BetTransaction> detailedTransactions= repo.getAllTransactionsByCustomerId(id);
        
        return getResumeTransactionsList(detailedTransactions);
    }

    private List<TransactionResume> getResumeTransactionsList(List<BetTransaction> detailedTransactions) {
     
        List<TransactionResume> resume= detailedTransactions.stream()
       .map(t-> new TransactionResume(t.getId(),t.getState(),t.getRandomLuckyNumber(),t.getStateAmount(),t.getDate())).toList();
        return resume;
    }

}
