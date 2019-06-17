package com.siem.siem.facts;

import java.util.Date;

public class Log {
	private String ipAddress;
    private Date timestamp;
    private String logType;
    
    public Log() {
    	this.logType = "Log";
    }
  
    public Log(String ip, Date timestamp ) {
		this.ipAddress = ip;
		this.timestamp = timestamp;
		this.logType = "Log";
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

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}
	
	/*@Override
	public String toString() {
		return "Log [ipAddress=" + ipAddress + ", timestamp=" + timestamp + "]";
	}*/
    
    

}
