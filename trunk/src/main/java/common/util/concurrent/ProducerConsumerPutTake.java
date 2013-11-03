package common.util.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import common.util.datetime.DateTimeUtil;
import common.util.datetime.DateTimeUtil.DATE_TIME_FORMAT;

/**
 * Waiting Queue
 * @author santoshm
 *
 */
public class ProducerConsumerPutTake {
	private static final Logger LOG = Logger.getLogger(ProducerConsumerPutTake.class);
	private static final BlockingQueue<String> bQueue = new ArrayBlockingQueue<String>(200);

	public static void startConsumer() {
		ScheduledExecutorService newSingleThreadExecutor = Executors.newSingleThreadScheduledExecutor();
		newSingleThreadExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					String consume = bQueue.take();
					String output = DateTimeUtil.formatDate(Long.valueOf(consume), DATE_TIME_FORMAT.dd_MM_yyyy_HH_mm_ss);
					LOG.info(output);
				} catch (InterruptedException e) {
					LOG.error(e);
				}
			}
		}, 0, 1, TimeUnit.SECONDS);
	}

	public static void startProducer() {
		ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
		newSingleThreadScheduledExecutor.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				long timeInMS = DateTimeUtil.getTimeinMilliSecond();
				try {
					bQueue.put(String.valueOf(timeInMS));
				} catch (InterruptedException e) {
					LOG.error(e);
				}
			}

		}, 0, 1, TimeUnit.SECONDS);
	}

	public static void main(String args[]) {
		startConsumer();
		startProducer();
	}

}
