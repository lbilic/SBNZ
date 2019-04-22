package com.siem.siem.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Test getClassifiedItem(Test t) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(t);
		kieSession.fireAllRules();
		kieSession.dispose();
		return t;
	}
}
