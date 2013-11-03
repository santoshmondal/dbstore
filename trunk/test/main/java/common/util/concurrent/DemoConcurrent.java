package common.util.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DemoConcurrent {

	private static final int NO_OF_THREAD = 5;
	final static BlockingQueue<String> bq = new LinkedBlockingQueue<String>();

	public static void main(String args[]) {
		// demoNewFixedThreadPool();
		// demoNewScheduledThreadPool();
		// demoBlockingQueue();
		requirementBackyard();
		// dummySchedule();
	}

	public static void dummySchedule() {
		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				System.out.println("HI SARFARAz!!!");
			}
		}, 0, 10, TimeUnit.SECONDS);
	}

	public static void requirementBackyard() {

		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setName("DEMO_CONUSMER_BACKYARD");
				while (true) {
					String data;
					try {
						data = bq.take();
						System.out.println("CONSUMER::" + data + "::" + Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		});

		// START PRODUCER
		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setName("DEMO_PRODUCER_BACKYARD");
				try {
					throw new RuntimeException();
					//bq.add("Hiiii...::");
				} catch (Exception e) {
					method();
				}

				System.out.println("SPAWN::" + Thread.currentThread().getName());
			}
		}, 0, 30, TimeUnit.SECONDS);

	}

	public static void method() {

		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				boolean bool = true;
				while (bool) {
					try {
						Thread.sleep(2000);
						Thread.currentThread().setName("DEMO_PRODUCER_BACKYARD");
						bq.add("Hiiii...::");
						bool = false;
					} catch (Exception e) {
						bool = true;
					}

					System.out.println("SPAWN::" + Thread.currentThread().getName());

				}
			}
		});
	}

	public static void demoBlockingQueue() {
		final BlockingQueue<String> bq = new LinkedBlockingQueue<String>();

		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setName("DEMO_CONUSMER");
				while (true) {
					String data;
					try {
						data = bq.take();
						System.out.println("CONSUMER::" + data + "::" + Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		});

		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				Thread.currentThread().setName("DEMO_PRODUCER");
				bq.add("Hiiii...::");
				System.out.println("SPAWN::" + Thread.currentThread().getName());
			}
		}, 0, 10, TimeUnit.SECONDS);
	}

	public static void demoNewScheduledThreadPool() {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(NO_OF_THREAD);

		scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		}, 0, 10, TimeUnit.SECONDS);
	}

	public static void demoNewFixedThreadPool() {
		ExecutorService executorService = Executors.newFixedThreadPool(NO_OF_THREAD);

		for (int i = 0; i < 100; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "::" + (NO_OF_THREAD + 0));
				}
			});
		}

	}
}