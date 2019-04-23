package com.siem.siem.services;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siem.siem.facts.ErrorLog;
import com.siem.siem.facts.FailedLogin;
import com.siem.siem.facts.ProfileChange;
import com.siem.siem.facts.SuccessfulLogin;
import com.siem.siem.facts.SystemTypes;
import com.siem.siem.facts.Test;
import com.siem.siem.facts.ThreatDetected;
import com.siem.siem.facts.ThreatEliminated;

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
		kieSession.insert(new SuccessfulLogin(SystemTypes.OS, "lemur", "ip123" , new Date()));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		kieSession.insert(new SuccessfulLogin(SystemTypes.IS1, "lemur", "ip1234" , new Date()));
		kieSession.insert(new SuccessfulLogin(SystemTypes.IS2, "lemur", "ip12345" , new Date()));
		kieSession.insert(new ThreatDetected(1l,"ip123",new Date()));
		ThreatEliminated te = new ThreatEliminated(1l,"ip123", new Date());
		te.setTimestamp(DateUtils.addHours(te.getTimestamp(), 3));
		kieSession.insert(new ErrorLog("Some message","ip12304109",new Date()));
		kieSession.insert(te);
		kieSession.insert(new SuccessfulLogin(SystemTypes.IS2, "lemur1", "ip1" , new Date()));
		kieSession.insert(new FailedLogin(SystemTypes.IS2, "lemur12", "ip2" , new Date()));
		kieSession.insert(new ErrorLog("message", "ip1" , new Date()));
		kieSession.fireAllRules();
		kieSession.dispose();
		
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
	
	public void testProfileChange() {
		KieSession kieSession = kieContainer.newKieSession("SiemSession");
		for(int i=0;i<6;i++) {
			kieSession.insert(new FailedLogin(SystemTypes.IS2, "username", "localhost", new Date()));
		}
		kieSession.insert(new SuccessfulLogin(SystemTypes.IS2, "username", "localhost", new Date()));
		kieSession.insert(new ProfileChange(SystemTypes.IS2, "username", "localhost", new Date()));
		kieSession.fireAllRules();
		kieSession.dispose();
	}

	public void testAntivirusSamePC() {
		KieSession kieSession = kieContainer.newKieSession("SiemSession");
		for(int i=0;i<6;i++) {
			kieSession.insert(new ThreatDetected(Integer.toUnsignedLong(i),"localhost" + i%2, new Date()));
		}
		kieSession.fireAllRules();
		kieSession.dispose();
	}
	
	public void testDOS() {
		KieSession kieSession = kieContainer.newKieSession("SiemSession");
		for(int i = 0; i < 11; i++) {
			kieSession.insert(new FailedLogin(SystemTypes.IS1,"random_user" + i,"random_ip" + i,new Date()));
		}
		/*try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		kieSession.insert(new FailedLogin(SystemTypes.IS1,"random_user11","random_ip11",new Date()));*/
		kieSession.fireAllRules();
		kieSession.dispose();
	}
}
