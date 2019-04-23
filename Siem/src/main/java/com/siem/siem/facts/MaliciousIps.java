package com.siem.siem.facts;

import java.util.ArrayList;

public class MaliciousIps {

	public static ArrayList<String> maliciousIps = new ArrayList<String>(){{
	    add("ip1");
	    add("ip2");
	    add("ip3");
	    add("ip4");
	    add("ip5");
	    add("ip6");
	}};
	
	public static int calcModificator(Class objClass) {
		/*if(objClass == FailedLogin.class) {
			return 5;
		}else if(objClass == )*/
		return 0;
	}
	
}
