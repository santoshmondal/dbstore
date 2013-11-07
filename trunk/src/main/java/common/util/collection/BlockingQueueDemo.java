package common.util.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import common.util.datetime.DateTimeUtil;
import common.util.datetime.DateTimeUtil.DATE_TIME_FORMAT;

public class BlockingQueueDemo {

	private static final BlockingQueue<String> bq = new ArrayBlockingQueue<String>(10);

	public static void main(String[] args) {
		startConsumer();
		startProducer();
	}

	public static void startConsumer() {
		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					bq.take();
					System.out.println("TAKE::" + DateTimeUtil.formatCurrentDate(DATE_TIME_FORMAT.dd_MM_yyyy_HH_mm_ss) + ":: " + bq.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 0, 5, TimeUnit.SECONDS);
	}

	public static void startProducer() {
		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					bq.put(DateTimeUtil.formatCurrentDate(DATE_TIME_FORMAT.dd_MM_yyyy_HH_mm_ss));
					System.out.println("PUT::" + DateTimeUtil.formatCurrentDate(DATE_TIME_FORMAT.dd_MM_yyyy_HH_mm_ss) + ":: " + bq.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
}
