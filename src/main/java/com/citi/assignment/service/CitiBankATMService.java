package com.citi.assignment.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CitiBankATMService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CitiBankATMService.class);
	
	// Denominations available in the ATM
    private final int[] denominations = {1000, 500, 100, 10};

    // ATM Cash Storage (for simplicity, we assume the ATM has this much cash)
    private final int[] availableCash = {100, 200, 500, 1000}; // This can be adjusted as needed.

    /**
     * Withdraws the given amount and returns the result asynchronously.
     */
    @Async
    public CompletableFuture<String> cashWithdrawl(int amount) {
    	
    	LOGGER.info("Inside cashWithdrawl()method of CitiBankATMService!");
    	
    	// Handle potential exception in async operation
        return CompletableFuture.supplyAsync(() -> {
            try {
                return processCashWithdrawl(amount);
            } catch (IllegalArgumentException e) {
                LOGGER.error("Invalid argument: ", e);
                return "Error: " + e.getMessage();
            } catch (Exception e) {
                LOGGER.error("Unexpected error: ", e);
                return "An unexpected error occurred during the withdrawal process.";
            }
        });
    }

    /**
     * Process the withdrawal, ensuring minimum notes are dispensed.
     */
    private String processCashWithdrawl(int amount) {
    	
    	LOGGER.info("Inside processCashWithdrawl()method of CitiBankATMService!");
    	
        StringBuilder response = new StringBuilder("Withdrawal Successful: ");
        int[] notesToDispense = new int[denominations.length];

        // Check if the amount is a multiple of 10 (required by ATM)
        if (amount % 10 != 0) {
        	throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        try {
            for (int i = 0; i < denominations.length; i++) {
                if (amount == 0) break;

                // Calculate the maximum number of notes of this denomination
                int maxNotes = Math.min(amount / denominations[i], availableCash[i]);

                // If we can dispense this denomination, do so
                if (maxNotes > 0) {
                    notesToDispense[i] = maxNotes;
                    amount -= maxNotes * denominations[i];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.error("Array index out of bounds during withdrawal processing: ", e);
            return "Error during withdrawal process. Please try again.";
        }

        // Check if amount is zero after the process, if not, insufficient funds
        if (amount != 0) {
            return "Insufficient funds or unable to provide the requested amount with the available denominations!";
        }

        // Update the availableCash after dispensing the notes
        for (int i = 0; i < denominations.length; i++) {
            availableCash[i] -= notesToDispense[i];
        }

        // Prepare the response with the notes dispensed
        for (int i = 0; i < denominations.length; i++) {
            if (notesToDispense[i] > 0) {
                response.append(denominations[i]).append(" x ").append(notesToDispense[i]).append(" ");
            }
        }

        return response.toString();
    }

}
