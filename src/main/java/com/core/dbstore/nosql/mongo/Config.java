package com.core.dbstore.nosql.mongo;

import java.util.ResourceBundle;

public class Config {

	public static void main(String args[]) {
		readUsingResouceBundle();
	}

	public static void readUsingResouceBundle() {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("config");
			System.out.println("ResourceBundle::" + rb.keySet());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
