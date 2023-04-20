package com.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trip")
public class Trip {
	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "company_id")
	private int companyId;
	@Column(name = "aircraft_model")
	private String aircraftModel;
	@Column(name = "town_from")
	private String townFrom;
	@Column(name = "town_to")
	private String townTo;
	@Column(name = "time_out")
	private String timeOut;
	@Column(name = "time_to")
	private String timeIn;
	
	/**
	 * Default Constructor
	 */
	public Trip() {
		
	}
	
	/**
	 * @param companyId
	 * @param aircraftModel
	 * @param townFrom
	 * @param townTo
	 * @param timeOut
	 * @param timeIn
	 */
	public Trip(int companyId, String aircraftModel, String townFrom, String townTo, String timeOut, String timeIn) {
		super();
		this.companyId = companyId;
		this.aircraftModel = aircraftModel;
		this.townFrom = townFrom;
		this.townTo = townTo;
		this.timeOut = timeOut;
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

	@Override
	public String toString() {
		return "Trip [id=" + id + ", companyId=" + companyId + ", aircraftModel=" + aircraftModel + ", townFrom="
				+ townFrom + ", townTo=" + townTo + ", timeOut=" + timeOut + ", timeIn=" + timeIn + "]\n";
	}
}
