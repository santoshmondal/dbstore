package common.util.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DemoJoin {

	public static void main(String args[]) {
		demoJoinDemo();
	}

	private static void demoJoinDemo() {

		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				new DemoJoin().test();
				System.out.println("Hello!!");
			}
		}, 0, 5, TimeUnit.SECONDS);
	}

	public void test() {
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread() {
				@Override
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			};

			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
