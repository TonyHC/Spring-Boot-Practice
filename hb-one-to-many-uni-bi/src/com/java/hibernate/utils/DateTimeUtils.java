package com.java.hibernate.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
	// Formats Date to [Month/Day/Year Hour:Minute:Second]
	private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	// Read a Date String and parse/convert to a Date
	public static Date parseDateTime(String dateTime) throws ParseException {
		Date date = formatter.parse(dateTime);
		return date;
	}
	
	// Read a Date and format/convert to String
	public static String formatDateTime(Date date) {
		String result = null;
		
		if (date != null) {
			result = formatter.format(date);
		}
		
		return result;
	}
}