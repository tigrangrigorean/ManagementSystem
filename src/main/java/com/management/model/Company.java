package com.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "founding_data")
	private String foundingData;
	
	/**
	 * Default Constructor
	 */
	public Company() {
		
	}

	/**
	 * @param companyName
	 * @param foundingData
	 */
	public Company(String companyName, String foundingData) {
		super();
		this.companyName = companyName;
		this.foundingData = foundingData;
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
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the foundingData
	 */
	public String getFoundingData() {
		return foundingData;
	}

	/**
	 * @param foundingData the foundingData to set
	 */
	public void setFoundingData(String foundingData) {
		this.foundingData = foundingData;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", foundingData=" + foundingData + "]";
	}
}
