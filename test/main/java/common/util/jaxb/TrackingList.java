package common.util.jaxb;

import javax.xml.bind.annotation.XmlElement;

public class TrackingList {
	private String executionDate;
	private String executionTime;
	private String noOfPieces;
	private String serviceEvent;
	private String statusCode;
	private String trackingCode;

	@XmlElement(name = "ExecutionDate")
	public String getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}

	@XmlElement(name = "ExecutionTime")
	public String getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}

	@XmlElement(name = "NoOfPieces")
	public String getNoOfPieces() {
		return noOfPieces;
	}

	public void setNoOfPieces(String noOfPieces) {
		this.noOfPieces = noOfPieces;
	}

	@XmlElement(name = "ServiceEvent")
	public String getServiceEvent() {
		return serviceEvent;
	}

	public void setServiceEvent(String serviceEvent) {
		this.serviceEvent = serviceEvent;
	}

	@XmlElement(name = "StatusCode")
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@XmlElement(name = "TrackingCode")
	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}

}
