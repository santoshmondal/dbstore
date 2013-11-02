package common.util.reflection;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import common.util.file.FileUtil;
import common.util.jaxb.JaxbUtil;

@XmlRootElement(name = "reflection")
public class JaxbReflection implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<DynamicClass> list;

	@XmlElement(name = "dynamic-class")
	public List<DynamicClass> getList() {
		return list;
	}

	public void setList(List<DynamicClass> list) {
		this.list = list;
	}

	public static void main(String args[]) throws Exception {
		String inputXml = FileUtil.readFile("reflection-util.xml");
		JaxbReflection jaxbRefobj = (JaxbReflection) JaxbUtil.convertXmlToObject(JaxbReflection.class.getName(), inputXml, null);
		System.out.println(jaxbRefobj);
	}
}
