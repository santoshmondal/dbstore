package common.util.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "root", namespace = "http://www.example.com/FOO")
@XmlRootElement(name = "root", namespace = "http://www.test.com")
// @XmlRootElement(name = "root")
public class Root {

	private String a;
	private String b;
	private String c;

	// @XmlElement(namespace = "http://www.example.com/OTHER")
	@XmlElement(namespace = "http://www.test11.com")
	// @XmlElement
	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	// @XmlElement(namespace = "http://www.example.com/OTHER")
	@XmlElement(namespace = "http://www.test.com")
	// @XmlElement
	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	// @XmlElement(namespace = "http://www.example.com/OTHER")
	@XmlElement
	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

}