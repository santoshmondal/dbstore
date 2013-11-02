package common.util.reflection;

import java.io.Serializable;

public class HelloReflection3 implements Serializable {

	private static final long serialVersionUID = 1L;
	public int param1;
	private String param2;
	private float param3;

	public int getParam1() {
		return param1;
	}

	public void setParam1(int param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public float getParam3() {
		return param3;
	}

	public void setParam3(float param3) {
		this.param3 = param3;
	}

}
