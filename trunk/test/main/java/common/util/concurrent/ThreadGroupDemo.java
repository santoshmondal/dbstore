package common.util.concurrent;

public class ThreadGroupDemo {

	public static void main(String args[]) throws Exception {

		ThreadGroup feedThreadGroup = new ThreadGroup("FeedThreadGroup");
		ThreadGroup publishThreadGroup = new ThreadGroup("PublishThreadGroup");

		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		};

		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		};

		Runnable r3 = new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		};

		Thread t1 = new Thread(feedThreadGroup, r1);
		Thread t2 = new Thread(publishThreadGroup, r2);
		Thread t3 = new Thread(feedThreadGroup, r3);
		t1.start();
		t2.start();
		t3.start();

		System.out.println("Thrad Groupu Main::"
				+ Thread.currentThread().getThreadGroup().activeGroupCount());
		System.out
				.println("GROUP COUNT::" + feedThreadGroup.activeGroupCount());
		System.out.println("publishThreadGroup::"
				+ publishThreadGroup.activeCount());
		System.out.println("FeedTrheadGroup::" + feedThreadGroup.activeCount());

		t1.join();
		t2.join();
		t3.join();

		System.out.println("MAIN THREAD::" + Thread.currentThread().getName());
	}
}
