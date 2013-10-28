package common.util.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * DD		=> Day in year
 * dd		=> two digit date in month
 *  
 * MM		=> two digit month (01)
 * MMM		=> three char short month (Jan)
 * MMMM 	=> Full month name (January)
 * yyyy		=> four digit year
 * 
 * H		=> Hour
 * m		=> minute
 * s		=> second
 * S		=> miliseconds
 * 
 * a		=> AM/PM marker
 */
public class DateTimeUtil {

	public enum DATE_TIME_FORMAT {
		// @formatter:off
		dd,
		MM,
		MMM,
		MMMM,
		yy,
		yyyy,
		dd_MM_yyyy,
		dd_MMM_yyyy,
		MM_dd_yyyy,
		dd_MM_yyyy_HH_mm_ss
		// @formatter:on
	}

	public static Map<DATE_TIME_FORMAT, String> map = new HashMap<DATE_TIME_FORMAT, String>();

	static {
		map.put(DATE_TIME_FORMAT.dd, "dd");
		map.put(DATE_TIME_FORMAT.MM, "MM");
		map.put(DATE_TIME_FORMAT.MMM, "MMM");
		map.put(DATE_TIME_FORMAT.MMMM, "MMMM");
		map.put(DATE_TIME_FORMAT.yy, "yy");
		map.put(DATE_TIME_FORMAT.yyyy, "yyyy");
		map.put(DATE_TIME_FORMAT.dd_MM_yyyy, "dd-MM-yyyy");
		map.put(DATE_TIME_FORMAT.dd_MMM_yyyy, "dd-MMM-yyyy");
		map.put(DATE_TIME_FORMAT.MM_dd_yyyy, "MM-dd-yyyy");
		map.put(DATE_TIME_FORMAT.dd_MM_yyyy_HH_mm_ss, "dd-MM-yyyy:HH:mm:ss");
	}

	public static long getTimeinMilliSecond() {
		return Calendar.getInstance().getTimeInMillis();
	}

	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		String format = map.get(DATE_TIME_FORMAT.dd);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		String format = map.get(DATE_TIME_FORMAT.MM);

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		String format = map.get(DATE_TIME_FORMAT.yyyy);

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date getCurrentDateObject() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static String formatCurrentDate(DATE_TIME_FORMAT dtFormat) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		String format = map.get(dtFormat);

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String formatCurrentDate(String format) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String formatDate(Date date, DATE_TIME_FORMAT dtFormat) {
		String format = map.get(dtFormat);

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String formatDate(Date date, String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String formatDate(Long timeInMS, DATE_TIME_FORMAT dtFormat) {
		Date date = millisecondToDate(timeInMS);
		String format = map.get(dtFormat);

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String formatDate(Long timeInMS, String format) {
		Date date = millisecondToDate(timeInMS);

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Date millisecondToDate(long timeInMS) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timeInMS);
		return cal.getTime();
	}

	public static long dateToMillSecond(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getTimeInMillis();
	}

	public static Date parseStringToDate(String source, DATE_TIME_FORMAT dfFormat) {
		String format = map.get(dfFormat);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static Date parseStringToDate(String source, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String args[]) throws Exception {
		System.out.println(formatCurrentDate("dd/MMM/yy"));
		System.out.println(getCurrentDate());
	}
}
