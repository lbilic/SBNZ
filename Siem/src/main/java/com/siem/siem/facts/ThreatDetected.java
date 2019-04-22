package com.siem.siem.facts;

import java.util.Date;

public class ThreatDetected extends Log {
	private Long threatId;
	   
    public ThreatDetected() {}
    
    public ThreatDetected(Long threatId, String ip, Date timestemp) {
		super(ip, timestemp);
		this.threatId = threatId;
	}

	public Long getThreatId() {
		return threatId;
	}

	public void setThreatId(Long threatId) {
		this.threatId = threatId;
	}
}
