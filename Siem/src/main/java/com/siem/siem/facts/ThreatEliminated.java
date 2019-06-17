package com.siem.siem.facts;

import java.util.Date;

public class ThreatEliminated extends Log {
	private Long threatId;
   
    public ThreatEliminated() {
    	super.setLogType("ThreatEliminated");
    }
    
    public ThreatEliminated(Long threatId, String ip, Date timestemp) {
		super(ip, timestemp);
		this.threatId = threatId;
		super.setLogType("ThreatEliminated");
	}

	public Long getThreatId() {
		return threatId;
	}

	public void setThreatId(Long threatId) {
		this.threatId = threatId;
	}
}
