package common.util.generics;

import java.util.ArrayList;
import java.util.List;

public class HelloGenerics {

	public static void main(String args[]) {
		List<? extends Employee> eLists = helloLists();

		// the above can be typecasted
		List<TechEmployee> tLists = (List<TechEmployee>) helloLists();
	}

	/*
	 * Returning generic list.
	 * It can return a generic list of type employee.
	 */
	public static List<? extends Employee> helloLists() {
		List<TechEmployee> tObj = new ArrayList<TechEmployee>();

		TechEmployee tEmp = new TechEmployee();
		tObj.add(tEmp);

		return tObj;
	}

	/*
	 * This method accepts a genric param list.
	 * The param can accept list of type Employee.
	 * NOTE: the above list is only read only. 
	 * it can not be modified.
	 */
	public static void genericListAsParam(List<? extends Employee> eList) {
		TechEmployee tEmp = new TechEmployee();
		Employee emp = new Employee();
		// eList.add(tEmp);	// wont let u compile
		// eList.add(emp); // wont let u compile
	}

}
