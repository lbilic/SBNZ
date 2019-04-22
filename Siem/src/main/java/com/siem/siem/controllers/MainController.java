package com.siem.siem.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siem.siem.facts.Test;
import com.siem.siem.services.TestService;

@RestController
public class MainController {
	
	private static Logger log = LoggerFactory.getLogger(MainController.class);

	private final TestService testService;
	
	@Autowired
	public MainController(TestService testService) {
		this.testService = testService;
	}

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
    	testService.testIt();
        return "Uspeh!!!!";
    }
}
