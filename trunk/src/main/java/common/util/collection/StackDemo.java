package common.util.collection;

import java.util.Stack;

public class StackDemo {

	public static void main(String[] args) {
		stackDemo();
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
}
