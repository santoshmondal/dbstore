package common.util.scheduler;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import common.util.datetime.DateTimeUtil;
import common.util.datetime.DateTimeUtil.DATE_TIME_FORMAT;

public class QuartzBasics implements Job {

	public static void main(String[] args) {
		basics2();
	}

	public static void basics1() {
		try {
			// Grab the Scheduler instance from the Factory 
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// and start it off
			scheduler.start();
			scheduler.shutdown();
		} catch (SchedulerException se) {
			se.printStackTrace();
		}
	}

	public static void basics2() {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();

			// define the job and tie it to our HelloJob class
			JobDetail job = JobBuilder.newJob(QuartzBasics.class).withIdentity("job1", "group1").build();

			// Trigger the job to run now, and then repeat every 40 seconds
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();

			// Tell quartz to schedule the job using our trigger
			scheduler.scheduleJob(job, trigger);

		} catch (SchedulerException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(DateTimeUtil.formatCurrentDate(DATE_TIME_FORMAT.dd_MM_yyyy_HH_mm_ss));
	}
}
