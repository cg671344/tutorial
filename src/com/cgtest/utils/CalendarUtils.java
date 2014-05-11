package com.cgtest.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarUtils {
	/** 
	 * ȡ��ָ�����������ܵĵ�һ�� 
	 * 
	 * @param date 
	 * @return 
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.FRIDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday 
		return c.getTime();
	}

	/** 
	 * �õ�ĳ��ĳ�ܵĵ�һ�� 
	 * 
	 * @param year 
	 * @param week 
	 * @return 
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, Calendar.JANUARY);
		c.set(Calendar.DATE, 1);
		Calendar cal = (GregorianCalendar) c.clone();
		cal.add(Calendar.DATE, week * 7);
		return getFirstDayOfWeek(cal.getTime());
	}
}