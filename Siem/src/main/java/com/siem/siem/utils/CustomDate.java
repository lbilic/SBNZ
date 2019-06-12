package com.siem.siem.utils;

import java.util.Date;

public class CustomDate {
	private String name;
	private Date timestamp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public CustomDate() {
		super();
	}

	public CustomDate(String name, Date timestamp) {
		super();
		this.name = name;
		this.timestamp = timestamp;
	}

	public static CustomDate sixMothsBeforeDate() {
		Date sixMonthsEarlier = new Date();
		sixMonthsEarlier.setTime(sixMonthsEarlier.getTime() - 15552000000L);
		CustomDate md = new CustomDate("sixMonthsEarlyer", sixMonthsEarlier);
		return md;
	}

	public static CustomDate tenDaysBeforeDate() {
		Date tenDaysBeforeDate = new Date();
		tenDaysBeforeDate.setTime(tenDaysBeforeDate.getTime() - 8640000000L);
		CustomDate md = new CustomDate("tenDaysBeforeDate", tenDaysBeforeDate);
		return md;

	}

	public static CustomDate oneDayBeforeDate() {
		Date oneDayBeforeDate = new Date();
		oneDayBeforeDate.setTime(oneDayBeforeDate.getTime() - 864000000L);
		CustomDate md = new CustomDate("oneDayBeforeDate", oneDayBeforeDate);
		return md;

	}

	public static CustomDate twelveHoursBeforeDate() {
		Date twelveHoursBeforeDate = new Date();
		twelveHoursBeforeDate.setTime(twelveHoursBeforeDate.getTime() - 43200000);
		CustomDate md = new CustomDate("twentyOneDaysEarlier", twelveHoursBeforeDate);
		return md;

	}

	@Override
	public String toString() {
		return "MyDate [name=" + name + ", timestamp=" + timestamp + "]";
	}

}
