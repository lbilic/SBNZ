package com.siem.siem.services;

import org.kie.api.runtime.KieSession;
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
}
