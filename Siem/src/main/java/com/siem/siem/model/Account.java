package com.siem.siem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Long id;

	@Column(name = "username", unique = true)
	protected String username;
	
	@Column(name = "ipAddress")
	protected String ipAddress;
	
	@Column(name = "riskLevel")
	protected AccountRiskLevel riskLevel;
	
	public Account(Long id, String username, String ipAddress) {
		super();
		this.id = id;
		this.username = username;
		this.ipAddress = ipAddress;
		this.riskLevel = riskLevel.LOW;
	}
	
	public Account(Long id, String username, String ipAddress, AccountRiskLevel riskLevel) {
		super();
		this.id = id;
		this.username = username;
		this.ipAddress = ipAddress;
		this.riskLevel = riskLevel;
	}

	public Account() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public AccountRiskLevel getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(AccountRiskLevel riskLevel) {
		this.riskLevel = riskLevel;
	}
}
