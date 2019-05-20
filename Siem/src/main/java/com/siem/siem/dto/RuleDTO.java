package com.siem.siem.dto;

public class RuleDTO {

	public String rule;
	public String field;
	public String value;
	public String interval;
	
	public RuleDTO() {}
	
	public RuleDTO(String rule, String field, String value, String interval) {
		super();
		this.rule = rule;
		this.field = field;
		this.value = value;
		this.interval = interval;
	}
	
	
}
