package com.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pass_in_trip")
public class PassInTrip {
	
	@Id
	@Column(name = "trip_id")
	private int tripId;
	@Column(name = "passenger_id")
	private int passengerId;
	@Column(name = "date")
	private String date;
	@Column(name = "place")
	private String place;
	
	/**
	 * Default Constructor
	 */
	public PassInTrip() {
		
	}

	/**
	 * @param tripId
	 * @param passengerId
	 * @param date
	 * @param place
	 */
	public PassInTrip(int tripId, int passengerId, String date, String place) {
		super();
		this.tripId = tripId;
		this.passengerId = passengerId;
		this.date = date;
		this.place = place;
	}

	/**
	 * @return the tripId
	 */
	public int getTripId() {
		return tripId;
	}

	/**
	 * @param tripId the tripId to set
	 */
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	/**
	 * @return the passengerId
	 */
	public int getPassengerId() {
		return passengerId;
	}

	/**
	 * @param passengerId the passengerId to set
	 */
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public String toString() {
		return "PassInTrip [tripId=" + tripId + ", passengerId=" + passengerId + ", date=" + date + ", place=" + place
				+ "]";
	}
	
}
