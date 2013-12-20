package common.util.jaxb;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "jaxb-root")
@XmlType(propOrder = { "id", "simpleValue", "uname", "xDemoList", "certificateList" })
public class JaxbDemoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String simpleValue;
	private String uname;
	private String password;

	private List<String> certificateList;
	private List<String> xDemoList;

	@XmlAttribute(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	// @XmlValue
	@XmlElement(name = "simple_value")
	public String getSimpleValue() {
		return simpleValue;
	}

	public void setSimpleValue(String simpleValue) {
		this.simpleValue = simpleValue;
	}

	@XmlElement(name = "uname")
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@XmlTransient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElementWrapper(name = "certificate-list")
	@XmlElement(name = "certificate")
	public List<String> getCertificateList() {
		return certificateList;
	}

	public void setCertificateList(List<String> certificateList) {
		this.certificateList = certificateList;
	}

	@XmlList
	@XmlElement(name = "xml-list")
	public List<String> getxDemoList() {
		return xDemoList;
	}

	public void setxDemoList(List<String> xDemoList) {
		this.xDemoList = xDemoList;
	}

}
