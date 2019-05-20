package com.siem.siem.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siem.siem.dto.RuleDTO;
import com.siem.siem.services.TestService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class RulesController {
	
	private static Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(method= RequestMethod.POST, value = "/generate-rule", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void generateRule(@RequestBody RuleDTO rule) {
    	System.out.println(rule.field);
    }

}
