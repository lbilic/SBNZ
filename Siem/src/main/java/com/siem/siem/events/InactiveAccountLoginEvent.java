package com.siem.siem.events;


import java.io.Serializable;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class InactiveAccountLoginEvent implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String message;
	
	public InactiveAccountLoginEvent(String username, String message) {
		super();
		this.username = username;
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
