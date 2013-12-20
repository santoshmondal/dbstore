package common.util.jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;

import org.apache.log4j.Logger;

public class JaxbUtil {
	private static final Logger log = Logger.getLogger(JaxbUtil.class);

	public static Object convertXmlToObject(String sFullyQualifiedClassName, String xml, String xsdFilePath) throws Exception {
		Class<?> forName = Class.forName(sFullyQualifiedClassName);

		JAXBContext jaxbInstance = JAXBContext.newInstance(forName);
		Unmarshaller jaxbUnmarshaller = jaxbInstance.createUnmarshaller();

		StringReader xmlReader = new StringReader(xml);
		XMLInputFactory xif = XMLInputFactory.newInstance();
		XMLStreamReader xsr = xif.createXMLStreamReader(xmlReader);
		xsr = new MyStreamReaderDelegate(xsr);

		return jaxbUnmarshaller.unmarshal(xsr);
	}

	public static String convertObjectToXML(String sFullyQualifiedClassName, Object newInstance) throws Exception {
		Class<?> forName = Class.forName(sFullyQualifiedClassName);

		JAXBContext jaxbInstance = JAXBContext.newInstance(forName);
		Marshaller jaxbMarshaler = jaxbInstance.createMarshaller();
		jaxbMarshaler.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		jaxbMarshaler.setProperty(Marshaller.JAXB_FRAGMENT, true);

		StringWriter xml = new StringWriter();
		jaxbMarshaler.marshal(newInstance, xml);

		return xml.toString();
	}

	private static class MyStreamReaderDelegate extends StreamReaderDelegate {

		public MyStreamReaderDelegate(XMLStreamReader xsr) {
			super(xsr);
		}

		@Override
		public String getAttributeLocalName(int index) {
			return super.getAttributeLocalName(index).toLowerCase();
		}

		@Override
		public String getLocalName() {
			return super.getLocalName().toLowerCase();
		}

	}
}
