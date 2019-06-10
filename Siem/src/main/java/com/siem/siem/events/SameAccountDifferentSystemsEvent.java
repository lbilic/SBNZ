package com.siem.siem.events;

import java.io.Serializable;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class SameAccountDifferentSystemsEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String username;
	
	public SameAccountDifferentSystemsEvent() {
		super();
	}
	
	public SameAccountDifferentSystemsEvent(String message, String username) {
		super();
		this.message = message;
		this.username = username;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}

