package common.util.collection;

import java.util.Deque;
import java.util.LinkedList;

public class DequeDemo {

	public static void main(String[] args) {
		dqueueDemo();
	}

	public static void dqueueDemo() {
		Deque<String> dObj = new LinkedList<String>();
		dObj.push("jai");
		dObj.push("shree");
		dObj.push("Ram");

		System.out.println(dObj.peek());
		dObj.pop();
		System.out.println(dObj.peek());

		dObj.clear();

		dObj.add("joya");
		dObj.add("jai");
		dObj.add("shree");
		dObj.add("ram");
		System.out.println(dObj.element());
		dObj.remove();
		System.out.println(dObj.element());
	}
}
