package com.minegocio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.minegocio.dto.GambleOrder;
import com.minegocio.dto.GambleResponse;
import com.minegocio.dto.TransactionResume;
import com.minegocio.model.Customer;

@Service
public class GamblingServiceImpl implements GamblingService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomerService customerService;

    @Override
    public GambleResponse placeABit(GambleOrder order) {
        Customer c = customerService.findById(order.getCustomerId());
        order.setCustomerBalance(c.getBalance());
        order.setCustomerName(c.getName());

        // calling the casino microservice endpoint
        ResponseEntity<GambleResponse> resp = restTemplate.postForEntity("http://GATEWAY-SERVICE/api/casino", order,
                GambleResponse.class);
        System.out.println("calling casino service ====> response is ==> " + resp.getBody());
        GambleResponse gambleResponse = resp.getBody();
        customerService.updateCustomerBalance(order.getCustomerId(), gambleResponse.getNewCustomerBalance());

        return gambleResponse;
    }

    @Override
    public List<TransactionResume> findCustomerTransactions(int id) {
        ResponseEntity resp = null;
        try {
            //calling the casino microservice endpoint
            resp = restTemplate.getForEntity("http://GATEWAY-SERVICE/api/casino/customer-transactions/resume/" + id,
                    List.class);
            System.out.println("Calling casino service to get the transactions of customer with id "
                    + id + " and the response is \n" + resp.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        if (resp.getStatusCode().value() == HttpStatus.OK.value()) {
            List<TransactionResume> transactions = (List<TransactionResume>) resp.getBody();
            return transactions;
        }
        return new ArrayList<>();

    }

}
