package com.siem.siem.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siem.siem.facts.FailedLogin;
import com.siem.siem.facts.SuccessfulLogin;
import com.siem.siem.facts.Test;

@Service
public class TestService {
	private static Logger log = LoggerFactory.getLogger(TestService.class);

	private final KieContainer kieContainer;

	@Autowired
	public TestService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}

	public void testIt() {
		KieSession kieSession = kieContainer.newKieSession("SiemSession");
		kieSession.insert(new Test(1L, "Ime"));
		kieSession.fireAllRules();
		kieSession.dispose();
		return;
	}
	
	public SuccessfulLogin testInactive(SuccessfulLogin sl) {
		KieSession kieSession = kieContainer.newKieSession("SiemSession");
		kieSession.insert(sl);
		kieSession.fireAllRules();
		kieSession.dispose();
		return sl;
	}
	
	public void testMultipleFailedIp() {
		KieSession kieSession = kieContainer.newKieSession("SiemSession");
		for(int i = 0; i < 15; i++) {
			kieSession.insert(new FailedLogin(null, null, "localhost", new GregorianCalendar(2019, Calendar.MARCH, 1+i).getTime()));
		}
		kieSession.fireAllRules();
		kieSession.dispose();
	}
	
	public void testMultipleFailedUsername() {
		KieSession kieSession = kieContainer.newKieSession("SiemSession");
		kieSession.insert(new FailedLogin(null, "username", "localhost", new Date()));
		kieSession.insert(new FailedLogin(null, "username", "localhost", new Date()));
		kieSession.fireAllRules();
		kieSession.dispose();
	}
}
