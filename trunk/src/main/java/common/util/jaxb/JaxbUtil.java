package common.util.jaxb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

public class JaxbUtil {
	private static final Logger LOG = Logger.getLogger("EXTERNAL_SERVICE");

	private static final Map<String, Class<?>> clazzMap = new ConcurrentHashMap<String, Class<?>>();
	private static final Map<String, JAXBContext> jaxbContextMap = new ConcurrentHashMap<String, JAXBContext>();

	public static JAXBContext getFromContextMap(String sFullyQualifiedClassName) {
		JAXBContext jaxBContext = jaxbContextMap.get(sFullyQualifiedClassName);
		if (null == jaxBContext) {
			try {
				Class<?> classObj = getFromClazzMap(sFullyQualifiedClassName);
				jaxBContext = JAXBContext.newInstance(classObj);
				jaxbContextMap.put(sFullyQualifiedClassName, jaxBContext);
			} catch (JAXBException e) {
				LOG.error("", e);
			}
		}

		return jaxBContext;
	}

	public static Class<?> getFromClazzMap(String sFullyQualifiedClassName) {
		Class<?> classObj = clazzMap.get(sFullyQualifiedClassName);
		if (null == classObj) {
			try {
				classObj = Class.forName(sFullyQualifiedClassName);
				clazzMap.put(sFullyQualifiedClassName, classObj);
			} catch (ClassNotFoundException e) {
				LOG.error("", e);
			}
		}

		return classObj;
	}

	public static Object convertXmlToObject(String sFullyQualifiedClassName, String inputXml, String xsdFilePath) {
		Object newInstance = null;
		try {
			JAXBContext context = getFromContextMap(sFullyQualifiedClassName);
			Unmarshaller um = context.createUnmarshaller();

			newInstance = um.unmarshal(new ByteArrayInputStream(inputXml.getBytes()));
		} catch (JAXBException e) {
			LOG.error("inputXml ::: \n" + inputXml + "\n", e);
		}
		return newInstance;
	}

	public static String convertObjectToXML(String sFullyQualifiedClassName, Object newInstance) {
		String data = "";
		if (null != newInstance) {
			try {

				JAXBContext jaxbContext = getFromContextMap(sFullyQualifiedClassName);
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				marshaller.setProperty("com.sun.xml.internal.bind.characterEscapeHandler", new CharacterEscapeHandler() {
					@Override
					public void escape(char[] ch, int start, int length, boolean isAttVal, Writer writer) throws IOException {
						writer.write(ch, start, length);
					}
				});

				StringWriter xml = new StringWriter();
				marshaller.marshal(newInstance, xml);
				data = xml.toString();
				if (data.contains("?xml")) {
					data = data.substring(data.indexOf("?>") + 2, data.length());
				}
			} catch (JAXBException e) {
				LOG.error("", e);
			}
		}
		return data;
	}

	public static void main(String[] args) {
		LOG.info("JAXB Util.");
	}
}
