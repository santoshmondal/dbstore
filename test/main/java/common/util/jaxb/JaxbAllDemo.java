package common.util.jaxb;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;

import org.apache.log4j.Logger;

import common.util.file.FileUtil;

public class JaxbAllDemo {
	private static final Logger log = Logger.getLogger(JaxbAllDemo.class);

	public static String jaxbCustomMarshaller(String fullyQualifiedClassName, Object instance) throws JAXBException, ClassNotFoundException {

		Class<?> forName = Class.forName(fullyQualifiedClassName);

		JAXBContext jaxbInstance = JAXBContext.newInstance(forName);
		Marshaller jaxbMarshaler = jaxbInstance.createMarshaller();
		jaxbMarshaler.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		StringWriter xml = new StringWriter();
		jaxbMarshaler.marshal(instance, xml);

		return xml.toString();
	}

	public static Object jaxbCustomUnMarshaller(String fullyQualifiedClassName, String xml) throws JAXBException, ClassNotFoundException,
			FileNotFoundException, XMLStreamException {

		Class<?> forName = Class.forName(fullyQualifiedClassName);

		JAXBContext jaxbInstance = JAXBContext.newInstance(forName);
		Unmarshaller jaxbUnmarshaller = jaxbInstance.createUnmarshaller();

		StringReader xmlReader = new StringReader(xml);
		XMLInputFactory xif = XMLInputFactory.newInstance();
		XMLStreamReader xsr = xif.createXMLStreamReader(xmlReader);
		xsr = new MyStreamReaderDelegate(xsr);

		return jaxbUnmarshaller.unmarshal(xsr);
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

	public static void main(String[] args) throws Exception {
		unmarshalFromXmlFile();
	}

	public static void simplestOnlyRoot() throws Exception {
		JaxbDemoBean instance = new JaxbDemoBean();
		instance.setId(1);
		instance.setPassword("raj");
		instance.setUname("raju");
		instance.setSimpleValue("coool");
		List<String> list = new ArrayList<String>();
		list.add("Java");
		list.add("jsp");
		instance.setCertificateList(list);
		instance.setxDemoList(list);

		String xml = JaxbAllDemo.jaxbCustomMarshaller(JaxbDemoBean.class.getName(), instance);
		log.info(xml);

		instance = (JaxbDemoBean) JaxbAllDemo.jaxbCustomUnMarshaller(JaxbDemoBean.class.getName(), xml);
		log.info(instance);
	}

	public static void unmarshalFromXmlFile() throws Exception {
		String xml = FileUtil.readFile("jaxb_demo_all.xml");

		JaxbDemoBean instance = (JaxbDemoBean) JaxbAllDemo.jaxbCustomUnMarshaller(JaxbDemoBean.class.getName(), xml);
		log.info(instance);
	}

}
