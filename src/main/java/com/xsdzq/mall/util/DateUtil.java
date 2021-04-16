package com.xsdzq.mall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getStandardDate(Date date) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		String standardString = sFormat.format(date);
		return standardString;
	}

	public static String getStandardMonthDate(Date date) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM");
		String standardString = sFormat.format(date);
		return standardString;
	}

	public static int getIntegerTime(Date date) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
		String fullString = sFormat.format(date);
		int fullTime = Integer.parseInt(fullString);
		System.out.println(fullTime);
		return fullTime;
	}

}
