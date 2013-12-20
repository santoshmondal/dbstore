package common.util.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "web-app", namespace = "http://java.sun.com/xml/ns/javaee")
public class JaxbWebXml {

	private String displayName;

	@XmlElement(name = "display-name", namespace = "http://helloworld.com")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
