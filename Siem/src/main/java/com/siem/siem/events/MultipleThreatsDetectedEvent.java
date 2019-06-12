package com.siem.siem.events;

import java.io.Serializable;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class MultipleThreatsDetectedEvent implements Serializable{
	private static final long serialVersionUID = 1L;
	private String ipAddress;
	private String message;
	
	public MultipleThreatsDetectedEvent(String ipAddress, String message) {
		super();
		this.ipAddress = ipAddress;
		this.message = message;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
