package com.siem.siem.facts;

import java.util.Date;

public class ErrorLog extends Log {
	private String message;
	   
    public ErrorLog() {
    	super.setLogType("ErrorLog");
    }
    
    public ErrorLog(String message, String ip, Date timestemp) {
		super(ip, timestemp);
		this.message = message;
		super.setLogType("ErrorLog");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
