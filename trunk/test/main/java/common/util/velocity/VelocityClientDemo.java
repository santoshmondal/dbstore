package common.util.velocity;

import common.util.network.SampleObject;

public class VelocityClientDemo {

	public static void main(String args[]) {
		SampleObject obj = new SampleObject();
		obj.setUid(11);
		//obj.setUname("velocity");

		String response = VelocityUtil.objectToTemplate(obj, "sample.vm");
		System.out.println(response);
	}

}
