package com.siem.siem.facts;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class FailedLogin extends Log implements Serializable {
	private SystemTypes systemType;
    private String username;
    
    private static final long serialVersionUID = 1L;
    
    public FailedLogin() {}
    
    public FailedLogin(SystemTypes systemTypes, String name, String ip, Date timestemp) {
		super(ip, timestemp);
		this.systemType = systemTypes;
		this.username = name;
	}

	public SystemTypes getSystemType() {
		return systemType;
	}

	public void setSystemType(SystemTypes systemType) {
		this.systemType = systemType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.username);
        hash = 59 * hash + Objects.hashCode(this.getIpAddress());
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FailedLogin other = (FailedLogin) obj;
        if (!Objects.equals(this.getIpAddress(), other.getIpAddress())) {
            return false;
        }
        if (!Objects.equals(this.username, other.getUsername())) {
            return false;
        }
        return true;
    }
}
