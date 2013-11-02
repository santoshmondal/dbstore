package common.util.reflection;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;

import common.util.file.FileUtil;
import common.util.jaxb.JaxbUtil;

public class ReflectionBasics {
	private static final Logger LOG = Logger.getLogger(ReflectionBasics.class);

	private static String REFLECTION_CONFIG_XML;
	static {
		try {
			REFLECTION_CONFIG_XML = FileUtil.readFile("reflection-util.xml");
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	public enum DATA_TYPE {
		INT, FLOAT, DOUBLE, STRING
	}

	public static Object getIntance(String name) {
		try {
			JaxbReflection jaxbRefobj = (JaxbReflection) JaxbUtil.convertXmlToObject(JaxbReflection.class.getName(), REFLECTION_CONFIG_XML, null);

			List<DynamicClass> list = jaxbRefobj.getList();
			for (DynamicClass dClass : list) {
				String instanceClass = dClass.getInstanceClass();
				String instanceName = dClass.getInstanceName();

				if (name.equalsIgnoreCase(instanceName)) {
					Class<?> forName = Class.forName(instanceClass);
					Object instance = forName.newInstance();

					List<Field> fieldList = dClass.getField();
					for (Field field : fieldList) {
						String fieldName = field.getName();
						String fieldValue = field.getValue();
						String fieldType = field.getType();

						String methodName = camelCaseCovertor(fieldName);

						Method method;
						if (fieldType.equalsIgnoreCase(DATA_TYPE.INT.toString())) {
							method = forName.getDeclaredMethod(methodName, int.class);
							int value = Integer.parseInt(fieldValue);
							method.invoke(instance, value);
						} else if (fieldType.equalsIgnoreCase(DATA_TYPE.FLOAT.toString())) {
							method = forName.getDeclaredMethod(methodName, float.class);
							float value = Float.parseFloat(fieldValue);
							method.invoke(instance, value);
						} else {
							method = forName.getDeclaredMethod(methodName, String.class);
							method.invoke(instance, fieldValue);
						}
					}
					return instance;
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		}

		return null;
	}

	public static String camelCaseCovertor(String paramName) {
		String name = paramName.substring(1);
		return "set" + ("" + paramName.charAt(0)).toUpperCase() + name;
	}

	public static void main(String... args) throws Exception {
		HelloReflection2 obj = (HelloReflection2) ReflectionBasics.getIntance("second");
		System.out.println(obj.getParam1());
	}

}
