package com.citi.assignment.controller;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.citi.assignment.service.CitiBankATMService;

@RestController
@RequestMapping("/citiatm")
public class CitiBankATMController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(CitiBankATMController.class);
	
	@Autowired
    private CitiBankATMService atmService;

    @PostMapping("/withdraw")
    public CompletableFuture<String> withdrawCitiCash(@RequestParam int amount) {
    	
    	LOGGER.info("Inside withdrawCitiCash()method of CitiBankATMController!");
    	
        return atmService.withdrawCitiCash(amount);
    }

}
