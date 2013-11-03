package common.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DemoFuture {

	public static void main(String args[]) {
		futurecall();
	}

	private static void futurecall() {

		List<Future<String>> list = new ArrayList<Future<String>>();

		Future<String> fs1 = Executors.newCachedThreadPool().submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "Async Call 1.";
			}
		});
		list.add(fs1);

		Future<String> fs2 = Executors.newCachedThreadPool().submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "Async Call 2.";
			}
		});
		list.add(fs2);

		Future<String> fs3 = Executors.newCachedThreadPool().submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "Async Call 3.";
			}
		});
		list.add(fs3);

		for (Future<String> fs : list) {
			try {
				String res = fs.get();
				System.out.println(res);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
