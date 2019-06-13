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
	
	public void insertLog(Log l) {
		sessionHandler.createNewSession();
		sessionHandler.insertLogIntoSession(l);
	}
	
	public void insertAccount(Account l) {
		sessionHandler.createNewSession();
		sessionHandler.insertAccountIntoSession(l);
	}
	
	public Collection<? extends Object> getLogs() {
		sessionHandler.createNewSession();
		return sessionHandler.getAllLogs();
	}
	
	public ArrayList<Account> getAccountWithTenVirusAlarms() {
		QueryResults queryResults = sessionHandler.getQueryResultsForQuery("account with 10 antivirus alarms in 10 days");
		ArrayList<Account> accounts = new ArrayList<Account>();
		for (QueryResultsRow row : queryResults) {
			accounts.add(((Account) row.get("account")));
		}
		return accounts;
	}
	
	public ArrayList<Account> getAccountWithNFailedLogins(int number) {
		QueryResults queryResults = sessionHandler.getQueryResultsForQueryParameter("account with N failed logins", String.valueOf(number));
		ArrayList<Account> accounts = new ArrayList<Account>();
		for (QueryResultsRow row : queryResults) {
			accounts.add(((Account) row.get("account")));
		}
		return accounts;
	}
	
}
