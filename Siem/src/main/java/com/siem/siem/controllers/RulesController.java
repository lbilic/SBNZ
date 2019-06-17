package com.siem.siem.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siem.siem.dto.RuleDTO;
import com.siem.siem.facts.Log;
import com.siem.siem.facts.MaliciousIps;
import com.siem.siem.model.Account;
import com.siem.siem.services.LogsService;
import com.siem.siem.services.TestService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class RulesController {

	private static Logger log = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private TestService testService;

	@Autowired
	private LogsService logsService;

	@RequestMapping(method = RequestMethod.POST, value = "/generate-rule", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void generateRule(@RequestBody RuleDTO rule) {
		System.out.println(rule.rule);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/generate-custom-rule/{ruleName}", consumes = MediaType.TEXT_PLAIN_VALUE)
	public void generateCustomRule(@RequestBody String rule, @PathVariable String ruleName) {
		try (PrintWriter out = new PrintWriter("../siem-rules/src/main/resources/com/siem/" + ruleName + ".drl")) {
			out.println(rule);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try{
			String path = new File("../siem-rules/install.bat").getCanonicalPath();
			String pathToFolder = path.substring(0, path.length()-11);
			path = "start " + path;
			path = String.format("cmd /c cd \"%s\" && ", pathToFolder) + path;
		    Process p = Runtime.getRuntime().exec(path);
		    //p.waitFor();

		}catch( IOException ex ){
		    ex.printStackTrace();
		}/*catch( InterruptedException ex ){
			ex.printStackTrace();
		}*/
	}

	@RequestMapping(method = RequestMethod.POST, value = "/insert-log/{logType}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertLog(@RequestBody Object log, @PathVariable String logType)
			throws IllegalArgumentException, ClassNotFoundException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (!logType.endsWith("MaliciousIps")) {
			System.out.println((mapper.convertValue(log, Class.forName(logType))));
			logsService.insertLog((Log) mapper.convertValue(log, Class.forName(logType)));
		}
		else {
			JSONObject jsonObj = new JSONObject(mapper.writeValueAsString(log));
			JSONArray arrJson = jsonObj.getJSONArray("maliciousIps");
			for(int i = 0; i < arrJson.length(); i++)
			    MaliciousIps.maliciousIps.add(arrJson.getString(i));
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/insert-account", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertAccount(@RequestBody Account account) {
		logsService.insertAccount(account);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/logs")
	public Collection<? extends Object> getLogs() {
		return logsService.getLogs();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/alarms")
	public Collection<? extends Object> getAlarms() {
		return logsService.getAlarms();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/accounts-with-antivirus-alarms")
	public ArrayList<Account> getAccountsWithAlarms() {
		return logsService.getAccountWithTenVirusAlarms();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/accounts-with-failed-logins")
	public Collection<? extends Object> getAccountWithNFailedLogins(@RequestParam int number) {
		return logsService.getAccountWithNFailedLogins(number);
	}

//	@RequestMapping(method= RequestMethod.GET, value = "/accounts-with-failed-logins")
//    public Collection<? extends Object> getAccountWithNFailedLogins(@RequestParam int number) {
//		return logsService.getAccountWithNFailedLogins(number);
//    }

}
