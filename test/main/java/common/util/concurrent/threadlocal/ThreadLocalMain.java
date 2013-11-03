package common.util.concurrent.threadlocal;

public class ThreadLocalMain {

	public static void main(String args[]) {
		DContext dContext = DContext.get();
		dContext.setId("skm645");

		// Now print context value
		DPrint.dPrint();
		new DPrint().doPrint();
	}

}

class DPrint {
	public static void dPrint() {
		DContext dContext = DContext.get();
		System.out.println("Using Static Method::" + dContext.getId());
	}

	public void doPrint() {
		DContext dContext = DContext.get();
		System.out.println("Using Object Method::" + dContext.getId());
	}
}

class DContext {
	String id;

	private static ThreadLocal<DContext> threadLocal = new ThreadLocal<DContext>() {
		@Override
		protected DContext initialValue() {
			return new DContext();
		}
	};
	public static DContext get() {
		return threadLocal.get();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
