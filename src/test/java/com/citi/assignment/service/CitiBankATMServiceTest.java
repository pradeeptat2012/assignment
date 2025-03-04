package com.citi.assignment.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CitiBankATMServiceTest {

	private CitiBankATMService atmService;

    @BeforeEach
    public void setUp() {
        atmService = new CitiBankATMService();
    }

    @Test
    public void testWithdrawCash_Successful() throws ExecutionException, InterruptedException {
        // Test withdrawal of 2000 which should be successful
        CompletableFuture<String> result = atmService.withdrawCitiCash(2000);
        String response = result.join();
        
        // Assert that the response is correct
        assertEquals("Withdrawal Successful: 1000 x 2 ", response);
    }

    @Test
    public void testWithdrawCash_AmountNotMultipleOf10() throws ExecutionException, InterruptedException {
        // Test withdrawal of amount that is not multiple of 10
        CompletableFuture<String> result = atmService.withdrawCitiCash(250);
        String response = result.join();
        
        // Assert that the response is correct
        assertEquals("Amount should be a multiple of 10!", response);
    }

    @Test
    public void testWithdrawCash_InsufficientFunds() throws ExecutionException, InterruptedException {
        // Test withdrawal amount higher than the available funds
        CompletableFuture<String> result = atmService.withdrawCitiCash(10000);  // Requesting too much
        String response = result.join();
        
        // Assert that the response indicates insufficient funds
        assertEquals("Insufficient funds or unable to provide the requested amount with the available denominations!", response);
    }

    @Test
    public void testWithdrawCash_UnableToDispenseExactAmount() throws ExecutionException, InterruptedException {
        // Test a case where the ATM cannot dispense the exact requested amount
        CompletableFuture<String> result = atmService.withdrawCitiCash(1500);  // Cannot dispense exactly
        String response = result.join();
        
        // Assert that the response is correct
        assertEquals("Insufficient funds or unable to provide the requested amount with the available denominations!", response);
    }

    @Test
    public void testWithdrawCash_PartialWithdrawal() throws ExecutionException, InterruptedException {
        // Test withdrawal of an amount that requires partial denominations to be dispensed
        CompletableFuture<String> result = atmService.withdrawCitiCash(1600);  // It should give 1000 x 1, 500 x 1, 100 x 1
        String response = result.join();
        
        // Assert that the response is correct
        assertEquals("Withdrawal Successful: 1000 x 1 500 x 1 100 x 1 ", response);
    }

}
