package common.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class HelloReflection {
	private static final Logger LOG = Logger.getLogger(HelloReflection.class);

	private String fname;
	public String uname;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public static void main(String args[]) {
		getInstance();
		methodInvocation();
		fieldInvocation();
	}

	public static void getInstance() {
		try {
			// Class<?> forName = Class.forName("common.util.reflection.HelloReflection");
			// Class<?> forName = Class.forName(HelloReflection.class.getName());
			// Object instance = forName.newInstance();
			Object instance = HelloReflection.class.newInstance();
			LOG.info(instance);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public static void methodInvocation() {
		try {
			Class<?> forName = Class.forName("common.util.reflection.HelloReflection");
			Object instance = forName.newInstance();

			// setmethod has been invoked
			Method method = forName.getDeclaredMethod("setFname", String.class);
			method.invoke(instance, "raj");

			// getmethod has been invoked
			method = forName.getDeclaredMethod("getFname");
			Object res = method.invoke(instance);
			LOG.info(res);
		} catch (Exception e) {
			LOG.error(e);
		}

	}

	public static void fieldInvocation() {
		try {
			Class<?> forName = Class.forName("common.util.reflection.HelloReflection");
			Object instance = forName.newInstance();

			// Field 
			Field field = forName.getDeclaredField("uname");
			field.set(instance, "vivek");

			Object res = field.get(instance);
			LOG.info(res);
		} catch (Exception e) {
			LOG.error(e);
		}

	}
}
