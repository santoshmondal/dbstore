package common.util.scheduler;

import java.util.Timer;
import java.util.TimerTask;

import common.util.datetime.DateTimeUtil;
import common.util.datetime.DateTimeUtil.DATE_TIME_FORMAT;

/*
 * Not adviseable because of chance of execution of same task twice
 * in case of delay because of anything. 
 */
public class TimerScheduleDemo {
	public static void main(String[] args) {
		scheduleJob();
	}

	public static void scheduleJob() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println(DateTimeUtil.formatCurrentDate(DATE_TIME_FORMAT.dd_MM_yyyy_HH_mm_ss));
			}
		}, 0, 5 * 1000);
	}
}
