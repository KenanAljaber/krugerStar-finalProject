package com.minegocio.controller;

import static org.mockito.Mockito.description;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minegocio.dto.GambleOrder;
import com.minegocio.dto.GambleResponse;
import com.minegocio.dto.TransactionResume;
import com.minegocio.service.GamblingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/customers/gamble")
@Tag(name = "gambling-controller", description = "Place bets and get the history of your bets ")
public class GamblingController {

    @Autowired
    private GamblingService service;

    /**
     * represents the endpoint that will allow customeres to do thier bets
     * 
     * @param order is the object that holds the gambling and customer informaiton
     * @return a response entity with a http status code
     */
    @ApiResponse(description = "Place a new bet order", responseCode = "200", content = @Content(schema = @Schema(implementation = GambleResponse.class)))
	@Operation(description = "Place a new bet order ", summary = "calling this endpoint will allow you to place a new bet and make money")
    @PostMapping(produces = "application/json")
    public ResponseEntity placeABit(@RequestBody(required = false) GambleOrder order) {
        if (order == null) {
            return new ResponseEntity("Plase make sure the order is correct!", HttpStatus.BAD_REQUEST);
        }
        GambleResponse resp = service.placeABit(order);
        return ResponseEntity.ok(resp);

    }

    /**
     * represents the endpoint that will show a specific customer betting history
     * @param id is the id of the customer we will search for
     * @return a response entity that holds the data we requested or an error message 
     */
    @ApiResponse(description = "A record of all bets that the customer with the given id has made", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransactionResume.class))))
	@Operation(description = "get all transactions made by the customer with the given id", summary = "calling this endpoint will give you a list of transactions records made by the customer")
    @GetMapping(value="/transactions/{id}",produces="application/json")
    public ResponseEntity getCustomerTransactions(@PathVariable int id) {
        return new ResponseEntity<>(service.findCustomerTransactions(id), HttpStatus.OK);

    }

}
