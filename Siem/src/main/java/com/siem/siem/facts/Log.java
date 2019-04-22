package com.siem.siem.facts;

import java.util.Date;

public class Log {
	private String ipAddress;
    private Date timestemp;
    
    public Log() {}
  
    public Log(String ip, Date timestemp ) {
		this.ipAddress = ip;
		this.timestemp = timestemp;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getTimestemp() {
		return timestemp;
	}

	public void setTimestemp(Date timestemp) {
		this.timestemp = timestemp;
	}
    
    

}
