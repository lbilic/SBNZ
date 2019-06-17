package com.siem.siem.facts;

import java.util.Date;

public class SuccessfulLogin extends Log {
	private SystemTypes systemType;
    private String username;
    
    public SuccessfulLogin() {
    	super.setLogType("SuccessfulLogin");
    }
    
    public SuccessfulLogin(SystemTypes systemTypes, String name, String ip, Date timestemp) {
		super(ip, timestemp);
		this.systemType = systemTypes;
		this.username = name;
		super.setLogType("SuccessfulLogin");
	}

	public SystemTypes getSystemType() {
		return systemType;
	}

	public void setSystemType(SystemTypes systemType) {
		this.systemType = systemType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    
    
}
