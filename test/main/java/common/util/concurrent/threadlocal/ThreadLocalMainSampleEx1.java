package common.util.concurrent.threadlocal;

public class ThreadLocalMainSampleEx1 {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				ThreadContext threadContext = new ThreadContext();
				threadContext.setTransactionId(1l);
				threadContext.setUserId("User 1");
				CustomThreadLocal.add(threadContext);
				// here we call a method where the thread context is not passed
				// as parameter
				PrintThreadContextValues.printThreadContextValues();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				ThreadContext threadContext = new ThreadContext();
				threadContext.setTransactionId(2l);
				threadContext.setUserId("User 2");
				CustomThreadLocal.add(threadContext);
				// here we call a method where the thread context is not passed
				// as parameter
				PrintThreadContextValues.printThreadContextValues();
			}
		}).start();
	}
}
