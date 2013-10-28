package common.util.jaxb;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import common.util.file.FileUtil;

@XmlRootElement(name = "BulkTrackingResponse")
public class ResponseRedexrpress implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TrackingResponse> trackingResponseList;

	@XmlElementWrapper(name = "BulkResponse")
	@XmlElement(name = "TrackingResponse")
	public List<TrackingResponse> getTrackingResponseList() {
		return trackingResponseList;
	}

	public void setTrackingResponseList(List<TrackingResponse> trackingResponseList) {
		this.trackingResponseList = trackingResponseList;
	}

	public static void main(String args[]) throws Exception {
		String xml = FileUtil.readFile("redexpress_res.xml", null, true);
		xml = xml.replace("xmlns=\"http://schemas.datacontract.org/2004/07/GetSetRed\"", "");
		ResponseRedexrpress obj = (ResponseRedexrpress) JaxbUtil.convertXmlToObject(ResponseRedexrpress.class.getName(), xml, null);
		System.out.println(obj);
	}

}
