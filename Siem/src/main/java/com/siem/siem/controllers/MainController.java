package com.siem.siem.controllers;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.siem.siem.facts.SuccessfulLogin;
import com.siem.siem.services.TestService;

@RestController
public class MainController {
	
	private static Logger log = LoggerFactory.getLogger(MainController.class);

	private final TestService testService;
	
	@Autowired
	public MainController(TestService testService) {
		this.testService = testService;
	}

	@RequestMapping(value = "/test_inactive")
    public SuccessfulLogin test_inactive() {
    	SuccessfulLogin newItem = new SuccessfulLogin(null, "meh", "sadsa", new GregorianCalendar(2019, Calendar.MARCH, 11).getTime());

		log.debug("Item request received for: " + newItem);

		SuccessfulLogin i2 = testService.testInactive(newItem);

		return i2;
    }
    
	@RequestMapping(value = "/test_multiple_failed_ip")
    public void testMultipleFailedLogin() {
    	testService.testMultipleFailedIp();
    }
	
	@RequestMapping(value = "/test_multiple_failed_username")
	public void testMultipleFailedUsername() {
    	testService.testMultipleFailedUsername();
	}
	
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
    	testService.testIt();
        return "Uspeh!!!!";
    }

    @RequestMapping(value = "/test_dos", method = RequestMethod.GET)
    public String testDos() {
    	testService.testDOS();
        return "Dos test done";
    }
}
