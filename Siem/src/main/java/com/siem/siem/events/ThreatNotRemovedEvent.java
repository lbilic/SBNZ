package com.siem.siem.events;

import java.io.Serializable;

import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
public class ThreatNotRemovedEvent implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long threatId;
	private String message;
	
	public ThreatNotRemovedEvent(Long threatId, String message) {
		super();
		this.threatId = threatId;
		this.message = message;
	}
	
	
	
	public Long getThreatId() {
		return threatId;
	}

	public void setThreatId(Long threatId) {
		this.threatId = threatId;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
