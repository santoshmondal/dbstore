package common.util.reflection;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class DynamicClass {

	private String instanceClass;
	private String instanceName;
	private List<Field> field;

	@XmlElement(name = "instance-class")
	public String getInstanceClass() {
		return instanceClass;
	}

	public void setInstanceClass(String instanceClass) {
		this.instanceClass = instanceClass;
	}

	@XmlElement(name = "instance-name")
	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	@XmlElement(name = "field")
	public List<Field> getField() {
		return field;
	}

	public void setField(List<Field> field) {
		this.field = field;
	}

}
