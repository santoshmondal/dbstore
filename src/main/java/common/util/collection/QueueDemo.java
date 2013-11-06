package common.util.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class QueueDemo {

	public static void main(String args[]) {
		//queueDemo();
		//stackDemo();
		// dqueueDemo();
		priorityQueue();

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

	public static void stackDemo() {
		Stack<String> sObj = new Stack<String>();
		sObj.push("santosh");
		sObj.push("raj");
		sObj.push("amit");

		System.out.println(sObj);

		System.out.println("Peek Element::" + sObj.peek());
		sObj.pop();
		System.out.println("Peek Element::" + sObj.peek());
	}

	public static void listToStackNQueue() {
		List<String> list = new ArrayList<String>();
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

	public static void priorityQueue() {
		Queue<String> pqObj = new PriorityQueue<String>(10, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() > o2.length()) {
					return 1;
				}

				if (o1.length() < o2.length()) {
					return -1;
				} else {
					return 0;
				}
			}
		});

		pqObj.add("jaoi");
		pqObj.add("shree");
		pqObj.add("ra");

		System.out.println(pqObj.element());
		pqObj.remove();
		System.out.println(pqObj.element());

		// PRIORITY QUEUE USING UDF OBJECT
		Queue<User> pqUserQueue = new PriorityQueue<User>(10, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				if (o1.getId() > o2.getId()) {
					return 1;
				}

				if (o1.getId() < o2.getId()) {
					return -1;
				}

				return 0;
			}
		});

		pqUserQueue.add(new User(12, "amit"));
		pqUserQueue.add(new User(111, "amit"));
		pqUserQueue.add(new User(11, "amit"));
		pqUserQueue.add(new User(19, "amit"));

		System.out.println(pqUserQueue.element().getId());
	}
}

class User {
	int id;
	String name;

	User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}