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

    @RequestMapping(value = "/test")
    public String test() {
        return "Uspeh!!!!";
    }
    
    @RequestMapping(value = "/test2", method = RequestMethod.GET, produces = "application/json")
	public Test getQuestions(@RequestParam(required = true) String id, @RequestParam(required = true) String name) {

		Test newItem = new Test(Long.parseLong(id), name);

		log.debug("Item request received for: " + newItem);

		Test i2 = testService.getClassifiedItem(newItem);

		return i2;
	}
}
