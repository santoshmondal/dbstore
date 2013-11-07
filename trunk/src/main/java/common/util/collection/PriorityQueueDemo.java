package common.util.collection;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueDemo {

	public static void main(String args[]) {
		priorityQueue();
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