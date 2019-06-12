package com.siem.siem.utils;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siem.siem.SiemApplication;
import com.siem.siem.facts.Log;
import com.siem.siem.model.Account;
import com.siem.siem.repositories.AccountRepository;

@Service
public class SessionHandler {
	@Autowired
	private KieContainer kieContainer;
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	public void createNewSession() {
		if(SiemApplication.allSessions.get("SiemSession") != null) {
			return;
		}
		KieSession kieSession = kieContainer.newKieSession("SiemSession");
		addUsersToSession(kieSession);
		addDatesToSession(kieSession);
		SiemApplication.allSessions.put("SiemSession", kieSession);
	}
	
	public void addUsersToSession(KieSession ks) {
		List<Account> accounts = accountRepository.findAll();
		for (Account account : accounts) {
			ks.insert(account);
		}
	}
	
	public void addDatesToSession(KieSession ks) {
		ks.insert(CustomDate.sixMothsBeforeDate());
		ks.insert(CustomDate.twelveHoursBeforeDate());
		ks.insert(CustomDate.oneDayBeforeDate());
		ks.insert(CustomDate.tenDaysBeforeDate());
	}
	
	public void disposeSesion(String username) {
		System.out.println(SiemApplication.allSessions.size());
		SiemApplication.allSessions.get("SiemSession").dispose();
		SiemApplication.allSessions.remove("SiemSession");
	}
	
	public FactHandle insertLogIntoSession(Log object) {
		return SiemApplication.allSessions.get("SiemSession").insert(object);
	}
	
	public FactHandle insertAccountIntoSession(Account object) {
		return SiemApplication.allSessions.get("SiemSession").insert(object);
	}
	
	public QueryResults getQueryResultsForQueryParameter(String string, String parameter) {
		return SiemApplication.allSessions.get("SiemSession").getQueryResults(string, parameter);
	}
	
	public QueryResults getQueryResultsForQuery(String string) {
		return SiemApplication.allSessions.get("SiemSession").getQueryResults(string);
	}

}
