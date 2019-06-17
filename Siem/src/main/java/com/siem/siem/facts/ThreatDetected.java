package com.siem.siem.facts;

import java.util.Date;

public class ThreatDetected extends Log {
	private Long threatId;
	   
    public ThreatDetected() {
    	super.setLogType("ThreatDetected");
    }
    
    public ThreatDetected(Long threatId, String ip, Date timestemp) {
		super(ip, timestemp);
		this.threatId = threatId;
		super.setLogType("ThreatDetected");
	}

	public Long getThreatId() {
		return threatId;
	}

	public void setThreatId(Long threatId) {
		this.threatId = threatId;
	}

	@Override
	public String toString() {
		return "ThreatDetected [threatId=" + threatId + ", toString()=" + super.toString() + "]";
	}
	
	
}
