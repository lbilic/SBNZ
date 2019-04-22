package com.siem.siem.facts;

import java.util.Date;

public class FailedLogin extends Log {
	private SystemTypes systemType;
    private String username;
    
    public FailedLogin() {}
    
    public FailedLogin(SystemTypes systemTypes, String name, String ip, Date timestemp) {
		super(ip, timestemp);
		this.systemType = systemTypes;
		this.username = name;
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
