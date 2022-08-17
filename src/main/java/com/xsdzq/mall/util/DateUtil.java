package com.xsdzq.mall.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static void main(String[] args) {
		getReduceDate("20200101");
	}

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
		// System.out.println(fullTime);
		return fullTime;
	}

	public static int getReduceDate(String source) {

		int year = Integer.parseInt(source.substring(0, 4));
		int month = Integer.parseInt(source.substring(4, 6));
		int day = Integer.parseInt(source.substring(6, 8));

		Date date = new Date(year - 1900, month - 1, day);
		// System.out.println(getIntegerTime(date));
		// System.out.println(date.getTime());

		long mi = date.getTime() - 24 * 1000 * 60 * 60;
		Date reduceDate = new Date(mi);
		// System.out.println(getIntegerTime(reduceDate));
		// int = reduceDate.get

		return getIntegerTime(reduceDate);

	}

	public static Date strToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}
}
