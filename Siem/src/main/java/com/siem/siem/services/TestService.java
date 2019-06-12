package com.siem.siem.services;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.internal.utils.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siem.siem.SiemApplication;
import com.siem.siem.facts.ErrorLog;
import com.siem.siem.facts.FailedLogin;
import com.siem.siem.facts.ProfileChange;
import com.siem.siem.facts.SuccessfulLogin;
import com.siem.siem.facts.SystemTypes;
import com.siem.siem.facts.Test;
import com.siem.siem.facts.ThreatDetected;
import com.siem.siem.facts.ThreatEliminated;
import com.siem.siem.model.Account;
import com.siem.siem.utils.SessionHandler;

@Service
public class TestService {
	private static Logger log = LoggerFactory.getLogger(TestService.class);

	private final KieContainer kieContainer;

	@Autowired
	SessionHandler sessionHandler;
	
	@Autowired
	public TestService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}
	
	public void testDrl() {
		InputStream template = TestService.class.getResourceAsStream("/com/siem/successful-login.drt");
        
        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{"18"},
            new String[]{"22"},
            new String[]{"31"},
            new String[]{"41"}
        });
        
        System.out.println(template);
        System.out.println(dataProvider);
        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);
        
        System.out.println(drl);
        KieSession kieSession = createKieSessionFromDRL(drl);
	}
	public void testIt() {
		sessionHandler.createNewSession();
		KieSession kieSession = SiemApplication.allSessions.get("SiemSession");
		System.out.println("Started with rules");
		kieSession.insert(new Test(1L, "Ime"));
		sessionHandler.insertLogIntoSession(new ThreatDetected(1l, "ip123", new Date()));
		sessionHandler.insertLogIntoSession(new ThreatDetected(2l, "ip123", new Date()));
		sessionHandler.insertLogIntoSession(new ThreatDetected(3l, "ip123", new Date()));
		sessionHandler.insertLogIntoSession(new ThreatDetected(4l, "ip123", new Date()));
		sessionHandler.insertLogIntoSession(new ThreatDetected(5l, "ip123", new Date()));
		sessionHandler.insertLogIntoSession(new ThreatDetected(6l, "ip123", new Date()));
		sessionHandler.insertLogIntoSession(new ThreatDetected(7l, "ip123", new Date()));
		sessionHandler.insertLogIntoSession(new ThreatDetected(8l, "ip123", new Date()));
		sessionHandler.insertLogIntoSession(new ThreatDetected(9l, "ip123", new Date()));
		sessionHandler.insertLogIntoSession(new ThreatDetected(10l, "ip123", new Date()));
		sessionHandler.insertAccountIntoSession(new Account(1l, "username", "ip123"));
		sessionHandler.insertLogIntoSession(new SuccessfulLogin(SystemTypes.OS, "lemur", "ip123", new Date()));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sessionHandler.insertLogIntoSession(new SuccessfulLogin(SystemTypes.IS1, "lemur", "ip1234", new Date()));
		sessionHandler.insertLogIntoSession(new SuccessfulLogin(SystemTypes.IS1, "lemur", "ip1234", new Date()));

//		ThreatEliminated te = new ThreatEliminated(1l, "ip123", new Date());
//		te.setTimestamp(DateUtils.addHours(te.getTimestamp(), 3));
		sessionHandler.insertLogIntoSession(new ErrorLog("Some message", "ip12304109", new Date()));
//		kieSession.insert(te);
		sessionHandler.insertLogIntoSession(new SuccessfulLogin(SystemTypes.IS2, "lemur1", "ip1", new Date()));
		sessionHandler.insertLogIntoSession(new FailedLogin(SystemTypes.IS2, "lemur12", "ip2", new Date()));
		sessionHandler.insertLogIntoSession(new ErrorLog("message", "ip1", new Date()));

		kieSession.fireAllRules();

		QueryResults queryResults = sessionHandler.getQueryResultsForQuery("account with 10 antivirus alarms in 10 days");
		System.out.println(queryResults.size());
		for (QueryResultsRow row : queryResults) {
			System.out.println(((Account) row.get("account")).getIpAddress());
		}
		queryResults = sessionHandler.getQueryResultsForQuery("account with 10 antivirus alarms in 10 days");
		
		
		for(Object o : kieSession.getObjects()) {
			System.out.println(o.toString());
		}
		System.out.println("Done with rules");

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
		for (int i = 0; i < 15; i++) {
			kieSession.insert(new FailedLogin(null, null, "localhost",
					new GregorianCalendar(2019, Calendar.MARCH, 1 + i).getTime()));
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

	public void testProfileChange() {
		KieSession kieSession = kieContainer.newKieSession("SiemSession");
		for (int i = 0; i < 6; i++) {
			kieSession.insert(new FailedLogin(SystemTypes.IS2, "username", "localhost", new Date()));
		}
		kieSession.insert(new SuccessfulLogin(SystemTypes.IS2, "username", "localhost", new Date()));
		kieSession.insert(new ProfileChange(SystemTypes.IS2, "username", "localhost", new Date()));
		kieSession.fireAllRules();
		kieSession.dispose();
	}

	public void testAntivirusSamePC() {
		KieSession kieSession = kieContainer.newKieSession("SiemSession");
		for (int i = 0; i < 6; i++) {
			kieSession.insert(new ThreatDetected(Integer.toUnsignedLong(i), "localhost" + i % 2, new Date()));
		}
		kieSession.fireAllRules();
		kieSession.dispose();
	}

	public void testDOS() {
		KieSession kieSession = kieContainer.newKieSession("SiemSession");
		for (int i = 0; i < 11; i++) {
			kieSession.insert(new FailedLogin(SystemTypes.IS1, "random_user" + i, "random_ip" + i, new Date()));
		}
		/*
		 * try { Thread.sleep(11000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } kieSession.insert(new
		 * FailedLogin(SystemTypes.IS1,"random_user11","random_ip11",new Date()));
		 */
		kieSession.fireAllRules();
		kieSession.dispose();
	}
	
	private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        
        Results results = kieHelper.verify();
        
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }
            
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        
        return kieHelper.build().newKieSession();
    }
}
