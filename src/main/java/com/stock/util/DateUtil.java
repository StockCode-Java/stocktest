/**
 * 
 */
package com.stock.util;

import java.util.Calendar;
import java.util.Date;


public class DateUtil {

	public static Date getCurrentDateTime(){
		Calendar currentDateTime = Calendar.getInstance();
		return currentDateTime.getTime();
	}
	
	public static Date getNowMovedMinutes(int minutes){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, minutes);
		return now.getTime();
	}
}
