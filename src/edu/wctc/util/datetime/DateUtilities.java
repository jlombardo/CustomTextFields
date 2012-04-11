/*
 * (C) 2007 - James G. Lombardo dba The Byteshop.Net
 * 
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this program; if not, write to the Free 
 * Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, 
 * MA  02111-1307, USA.
 */

package edu.wctc.util.datetime;

import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Utility class to simplify managing and using dates and times. Fields are 
 * provided for choice of units to be returned from date/time difference calculations.
 * <P>
 * Change History:
 * <UL>
 * 		<LI>2007-03-02 - initial version.</LI>
 * 		<LI>2007-03-09 - fixed bug in dateDiff method that under certain circumstances
 *                       produced incorrect values.</LI>
 *      <LI>2007-03-30 - refactored various method names and added toDate() method.
 * </UL>
 * 
 * (C) 2007 - James G. Lombardo dba The Byteshop.Net
 * 
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public 
 * License as published by the Free Software Foundation; either 
 * version 2.1 of the License, or (at your option) any later version.
 * <P>
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * <P>
 * You should have received a copy of the GNU Lesser General Public 
 * License along with this program; if not, write to the Free 
 * Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, 
 * MA  02111-1307, USA.
 * 
 * @author James G. Lombardo (<a href="mailto:james.g.lombardo@gmail.com">james.g.lombardo@gmail.com</a>)
 * @version 1.01.02
 */
public class DateUtilities {
	public static final long DAY_UNITS = 1000L * 60L * 60L * 24L;
	public static final long HOUR_UNITS = 1000L * 60L * 60L;
	public static final long MINUTE_UNITS = 1000L * 60L;
	public static final long SECOND_UNITS = 1000L;
	
	// Prohibit instantiation
	private DateUtilities() {}

	/** Returns the current date and time. */
	public static Date now() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * Format a <code>Date</code> according to the default date pattern for the current locale
	 * @param date - a <code>Date</code> object
	 * @return a date formatted according to the default date pattern for the current locale
	 * @throws IllegalArgumentException if date is null
	 */
	public static String toString(Date date) throws IllegalArgumentException {
		if(date == null) throw new IllegalArgumentException("Error: date argument cannot be null");
		DateFormat df = DateFormat.getDateInstance();
		return df.format(date);
	}
	
	/**
	 * Format a <code>Calendar</code> according to the default date pattern for the current locale
	 * @param date - a <code>Calendar</code> object
	 * @return a date formatted according to the default date pattern for the current locale
	 * @throws IllegalArgumentException if date is null
	 */
	public static String toString(Calendar date) throws IllegalArgumentException {
		DateFormat df = DateFormat.getDateInstance();
		return df.format(date.getTime());
	}
	
	/**
	 * Format a <code>Calendar</code> according to a specified pattern
	 * @param date - a <code>Calendar</code> object
	 * @param pattern - a <code>SimpleDateFormat</code> date/time pattern
	 * @return a date and or time formatted according to the specified pattern
	 * @throws IllegalArgumentException if pattern is not recognized
	 */
	public static String toString(Calendar date, String pattern) throws IllegalArgumentException {
		String strDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		strDate = sdf.format(date.getTime());
			
		return strDate;
	}
	
	/**
	 * Format a <code>Calendar</code> according to a specified style for a specified Locale
	 * @param date - a <code>Calendar</code> object
	 * @param dateFormatStyle - a <code>DateFormat</code> style field
	 * @param aLocale - a <code>Locale</code> field
	 * @return a date and/or time formatted according to the specified style for the specified Locale
	 * @throws IllegalArgumentException if style or locale is not recognized
	 */
	public static String toString(Calendar date, int dateFormatStyle, Locale aLocale) 
	throws IllegalArgumentException {
		String strDate = null;
		DateFormat df = DateFormat.getDateInstance(dateFormatStyle, aLocale);
		strDate = df.format(date.getTime());
			
		return strDate;
	}
	
	/**
	 * Format a <code>Calendar</code> according to a specified date and time style for a specified Locale
	 * @param date - a <code>Calendar</code> object
	 * @param dateFormatStyle - a <code>DateFormat</code> style field
	 * @param timeFormatStyle - a <code>DateFormat</code> style field
	 * @param aLocale - a <code>Locale</code> field
	 * @return a date and/or time formatted according to the specified style for the specified Locale
	 * @throws IllegalArgumentException if any style or locale is not recognized
	 */
	public static String toString(Calendar date, int dateFormatStyle, int timeFormatStyle, Locale aLocale) 
	throws IllegalArgumentException {
		String strDate = null;
		DateFormat df = DateFormat.getDateTimeInstance(dateFormatStyle, timeFormatStyle, aLocale);
		strDate = df.format(date.getTime());
			
		return strDate;
	}
	
	/**
	 * Format a <code>Date</code> according to a specified pattern
	 * @param date - a <code>Date</code> object
	 * @param pattern - a <code>SimpleDateFormat</code> date/time pattern
	 * @return a date and/or time formatted according to the specified pattern
	 * @throws IllegalArgumentException if pattern is not recognized
	 */
	public static String toString(Date date, String pattern) throws IllegalArgumentException {
		String strDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		strDate = sdf.format(date);
			
		return strDate;
	}
	
	/**
	 * Format the current date/time according to the specified pattern.
	 * @param pattern - a <code>SimpleDateFormat</code> date/time pattern
	 * @return a date and or time formatted according to the specified pattern
	 * @throws IllegalArgumentException if pattern is not recognized
	 */
	public static String format(String pattern) throws IllegalArgumentException {
		String strDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		strDate = sdf.format(new Date());
		return strDate;
	}
	
	/**
	 * Format a date and/or time string according to the specified pattern.
	 * @param dateString - a <code>String</code> representation of a date and/or time
	 * @param pattern - a <code>SimpleDateFormat</code> date/time pattern
	 * @return a date and/or time formatted according to the specified pattern
	 * @throws IllegalArgumentException if pattern is not recognized
	 */
	public static String format(String dateString, String pattern) 
	throws ParseException, IllegalArgumentException {
		String strDate = null;
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		date = sdf.parse(dateString);
		strDate = sdf.format(date);
			
		return strDate;
	}
	
	/**
	 * Attempts to convert a String representation of a date to a java.util.Date 
	 * object. Conversion rules are based on parse definitions in the 
	 * java.text.DateFormat class.
	 * @param dateString
	 * @return a java.util.Date object
	 * @throws IllegalArgumentException if the date string cannot be parsed.
	 */
	public static Date toDate(String dateString) 
	throws IllegalArgumentException {
		Date date = null;
		DateFormat df = DateFormat.getDateInstance();
		try {
			df = DateFormat.getDateInstance(DateFormat.SHORT);
			date = df.parse(dateString);
		} catch( Exception e) {
			try {
				df = DateFormat.getDateInstance(DateFormat.MEDIUM);
				date = df.parse(dateString);
			} catch(Exception e2) {
				try {
					df = DateFormat.getDateInstance(DateFormat.LONG);
					date = df.parse(dateString);
				} catch(Exception e3) {
					try {
						df = DateFormat.getDateInstance(DateFormat.FULL);
						date = df.parse(dateString);
					} catch(Exception e4) {
						throw new IllegalArgumentException(e4.getMessage());
					}
				}
			}
		}
		return date;
	}
	
	/**
	 * Calculate the difference, in DateUtilitities field units, for any two <code>Calendar</code> objects
	 * @param dateUtilitiesUnitField - the unit of measure (e.g., DateUtilities.DAY_UNITS, DateUtilities.HOUR_UNITS, etc.)
	 * @param firstDate - a <code>Calendar</code> object
	 * @param secondDate - a <code>Calendar</code> object
	 * @return the difference in DateUtilities units as a positive whole number
	 * @throws IllegalArgumentException if any argument is invalid
	 */
	public static int dateDiff(long dateUtilitiesUnitField, Calendar firstDate, Calendar secondDate) 
	throws IllegalArgumentException {
		long diff = Math.abs(firstDate.getTimeInMillis() - secondDate.getTimeInMillis());
		double diffAmt = (double)diff / dateUtilitiesUnitField;
		
		return (int)Math.round(diffAmt);
	}
	
	/*
	 * Test harness.
	 * @param args - not used
	 */
//	public static void main(String[] args) throws ParseException, IllegalArgumentException {
//		
////		Calendar d1 = Calendar.getInstance();
////		Calendar d2 = Calendar.getInstance();
////		d2.add(Calendar.DATE, 4);
////		int difference = DateUtilities.dateDiff(DateUtilities.DAY_UNITS, d1,d2);
////		System.out.println(difference);
//		
//		
//		
//		Date date = new Date();
//		Calendar firstDate = Calendar.getInstance();
//		Calendar secondDate = Calendar.getInstance();
//		secondDate.add(Calendar.DAY_OF_MONTH, 500);
//		
//		System.out.println(format(date));
//		System.out.println(format(secondDate.getTime()));
//		System.out.println(dateDiff(DateUtilities.DAY_UNITS, firstDate, secondDate));
//
//	}

}
