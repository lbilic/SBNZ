package com.siem.siem.facts;

import java.util.Date;

public class Log {
	private String ipAddress;
    private Date timestamp;
    
    public Log() {}
  
    public Log(String ip, Date timestamp ) {
		this.ipAddress = ip;
		this.timestamp = timestamp;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
    
    

}
