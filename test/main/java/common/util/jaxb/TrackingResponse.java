package common.util.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class TrackingResponse {
	private String actionRemarks;
	private String chargeableWeight;
	private String commodity;
	private String consigneeAddress;
	private String consigneeCity;
	private String consigneeLocality;
	private String consigneeName;
	private String consigneePincode;
	private String destinationBranch;
	private String grossWeight;
	private String messageReference;
	private String noOfPieces;
	private String originBranch;
	private String referenceNo;
	private String shipperAddress;
	private String shipperCity;
	private String shipperLocality;
	private String shipperName;
	private String shipperPincode;
	private String tempWaybillNo;
	private String trackingID;
	private String trackingLevel;
	private List<TrackingList> rackingList;
	private String userID;
	private String volumeWeight;
	private String waybillNo;

	@XmlElement(name = "ActionRemarks")
	public String getActionRemarks() {
		return actionRemarks;
	}

	public void setActionRemarks(String actionRemarks) {
		this.actionRemarks = actionRemarks;
	}

	@XmlElement(name = "ChargeableWeight")
	public String getChargeableWeight() {
		return chargeableWeight;
	}

	public void setChargeableWeight(String chargeableWeight) {
		this.chargeableWeight = chargeableWeight;
	}

	@XmlElement(name = "Commodity")
	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	@XmlElement(name = "ConsigneeAddress")
	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	@XmlElement(name = "ConsigneeCity")
	public String getConsigneeCity() {
		return consigneeCity;
	}

	public void setConsigneeCity(String consigneeCity) {
		this.consigneeCity = consigneeCity;
	}

	@XmlElement(name = "ConsigneeLocality")
	public String getConsigneeLocality() {
		return consigneeLocality;
	}

	public void setConsigneeLocality(String consigneeLocality) {
		this.consigneeLocality = consigneeLocality;
	}

	@XmlElement(name = "ConsigneeName")
	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	@XmlElement(name = "ConsigneePincode")
	public String getConsigneePincode() {
		return consigneePincode;
	}

	public void setConsigneePincode(String consigneePincode) {
		this.consigneePincode = consigneePincode;
	}

	@XmlElement(name = "DestinationBranch")
	public String getDestinationBranch() {
		return destinationBranch;
	}

	public void setDestinationBranch(String destinationBranch) {
		this.destinationBranch = destinationBranch;
	}

	@XmlElement(name = "GrossWeight")
	public String getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(String grossWeight) {
		this.grossWeight = grossWeight;
	}

	@XmlElement(name = "MessageReference")
	public String getMessageReference() {
		return messageReference;
	}

	public void setMessageReference(String messageReference) {
		this.messageReference = messageReference;
	}

	@XmlElement(name = "NoOfPieces")
	public String getNoOfPieces() {
		return noOfPieces;
	}

	public void setNoOfPieces(String noOfPieces) {
		this.noOfPieces = noOfPieces;
	}

	@XmlElement(name = "OriginBranch")
	public String getOriginBranch() {
		return originBranch;
	}

	public void setOriginBranch(String originBranch) {
		this.originBranch = originBranch;
	}

	@XmlElement(name = "ReferenceNo")
	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	@XmlElement(name = "ShipperAddress")
	public String getShipperAddress() {
		return shipperAddress;
	}

	public void setShipperAddress(String shipperAddress) {
		this.shipperAddress = shipperAddress;
	}

	@XmlElement(name = "ShipperCity")
	public String getShipperCity() {
		return shipperCity;
	}

	public void setShipperCity(String shipperCity) {
		this.shipperCity = shipperCity;
	}

	@XmlElement(name = "ShipperLocality")
	public String getShipperLocality() {
		return shipperLocality;
	}

	public void setShipperLocality(String shipperLocality) {
		this.shipperLocality = shipperLocality;
	}

	@XmlElement(name = "ShipperName")
	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	@XmlElement(name = "ShipperPincode")
	public String getShipperPincode() {
		return shipperPincode;
	}

	public void setShipperPincode(String shipperPincode) {
		this.shipperPincode = shipperPincode;
	}

	@XmlElement(name = "TempWaybillNo")
	public String getTempWaybillNo() {
		return tempWaybillNo;
	}

	public void setTempWaybillNo(String tempWaybillNo) {
		this.tempWaybillNo = tempWaybillNo;
	}

	@XmlElement(name = "TrackingID")
	public String getTrackingID() {
		return trackingID;
	}

	public void setTrackingID(String trackingID) {
		this.trackingID = trackingID;
	}

	@XmlElement(name = "TrackingLevel")
	public String getTrackingLevel() {
		return trackingLevel;
	}

	public void setTrackingLevel(String trackingLevel) {
		this.trackingLevel = trackingLevel;
	}

	@XmlElementWrapper(name = "TrackingList")
	@XmlElement(name = "TrackingList")
	public List<TrackingList> getRackingList() {
		return rackingList;
	}

	public void setRackingList(List<TrackingList> rackingList) {
		this.rackingList = rackingList;
	}

	@XmlElement(name = "UserID")
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@XmlElement(name = "VolumeWeight")
	public String getVolumeWeight() {
		return volumeWeight;
	}

	public void setVolumeWeight(String volumeWeight) {
		this.volumeWeight = volumeWeight;
	}

	@XmlElement(name = "WaybillNo")
	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}
}
