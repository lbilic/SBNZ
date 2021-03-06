package com.siem.siem.services;

import java.util.ArrayList;
import java.util.Collection;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siem.siem.SiemApplication;
import com.siem.siem.facts.Log;
import com.siem.siem.model.Account;
import com.siem.siem.utils.SessionHandler;

@Service
public class LogsService {

	@Autowired
	SessionHandler sessionHandler;
	
	@Autowired
	TestService testService;
	
	public void insertLog(Log l) {
		l.setFired(false);
		sessionHandler.createNewSession();
		sessionHandler.insertLogIntoSession(l);
		KieSession kieSession = SiemApplication.allSessions.get("SiemSession");
		kieSession.fireAllRules();
		testService.testDrl();
	}
	
	public void insertAccount(Account l) {
		sessionHandler.createNewSession();
		sessionHandler.insertAccountIntoSession(l);
	}
	
	public Collection<? extends Object> getLogs() {
		sessionHandler.createNewSession();
		return sessionHandler.getAllLogs();
	}
	
	public Collection<? extends Object> getAlarms() {
		sessionHandler.createNewSession();
		return sessionHandler.getAllAlarms();
	}
	
	public ArrayList<Account> getAccountWithTenVirusAlarms() {
		sessionHandler.createNewSession();
		QueryResults queryResults = sessionHandler.getQueryResultsForQuery("account with 10 antivirus alarms in 10 days");
		ArrayList<Account> accounts = new ArrayList<Account>();
		for (QueryResultsRow row : queryResults) {
			accounts.add(((Account) row.get("account")));
		}
		return accounts;
	}
	
	public ArrayList<Account> getAccountWithNFailedLogins(int number) {
		sessionHandler.createNewSession();
		QueryResults queryResults = sessionHandler.getQueryResultsForQueryParameter("account with N failed logins", String.valueOf(number));
		ArrayList<Account> accounts = new ArrayList<Account>();
		for (QueryResultsRow row : queryResults) {
			accounts.add(((Account) row.get("account")));
		}
		return accounts;
	}
	
}
