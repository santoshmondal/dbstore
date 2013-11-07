package common.util.collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {

	public static void main(String args[]) {
		queueDemo();
	}

	public static void queueDemo() {
		Queue<String> qObj = new LinkedList<String>();
		qObj.add("santsoh");
		qObj.add("Amit");
		qObj.add("sunny");

		System.out.println(qObj);

		System.out.println("HEAD ELEMENT::" + qObj.element());
		qObj.remove();
		System.out.println("HEAD ELEMENT::" + qObj.element());
	}

}
