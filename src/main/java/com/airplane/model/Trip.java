package com.airplane.model;

public class Trip {
	
	private int id;
	private int companyId;
	private String aircraftModel;
	private String townFrom;
	private String townTo;
	private String timeOut;
	private String timeIn;
	
	/**
	 * Default constructor Trip
	 */
	public Trip() {
		
	}
	
	/**
	 * @param id
	 * @param companyId
	 * @param aircraftModel
	 * @param townFrom
	 * @param townTo
	 * @param timeOut
	 * @param timeIn
	 */
	public Trip(int id,int companyId, String aircraftModel, String townFrom, String townTo, String timeOut, String timeIn) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.aircraftModel = aircraftModel;
		this.townFrom = townFrom;
		this.townTo = townTo;
		this.timeOut = timeOut;
		this.timeIn = timeIn;
	}

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the aircraftModel
	 */
	public String getAircraftModel() {
		return aircraftModel;
	}

	/**
	 * @param aircraftModel the aircraftModel to set
	 */
	public void setAircraftModel(String aircraftModel) {
		this.aircraftModel = aircraftModel;
	}

	/**
	 * @return the townFrom
	 */
	public String getTownFrom() {
		return townFrom;
	}

	/**
	 * @param townFrom the townFrom to set
	 */
	public void setTownFrom(String townFrom) {
		this.townFrom = townFrom;
	}

	/**
	 * @return the townTo
	 */
	public String getTownTo() {
		return townTo;
	}

	/**
	 * @param townTo the townTo to set
	 */
	public void setTownTo(String townTo) {
		this.townTo = townTo;
	}

	/**
	 * @return the timeOut
	 */
	public String getTimeOut() {
		return timeOut;
	}

	/**
	 * @param timeOut the timeOut to set
	 */
	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	/**
	 * @return the timeIn
	 */
	public String getTimeIn() {
		return timeIn;
	}

	/**
	 * @param timeIn the timeIn to set
	 */
	public void setTimeIn(String timeIn) {
		this.timeIn = timeIn;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
}
