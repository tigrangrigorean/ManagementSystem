package com.airplane.model;

public class Company {
	private int id;
	private String companyName;
	private String foundingDate;
	
	/**
	 * Default constructor
	 */
	public Company() {
		
	}
	
	/**
	 * @param companyName
	 * @param foundingDate
	 */
	public Company(String companyName, String foundingDate) {
		super();
		this.companyName = companyName;
		this.foundingDate = foundingDate;
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
	 * @return the foundingDate
	 */
	public String getFoundingDate() {
		return foundingDate;
	}
	/**
	 * @param foundingDate the foundingDate to set
	 */
	public void setFoundingDate(String foundingDate) {
		this.foundingDate = foundingDate;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", foundingDate=" + foundingDate + "]";
	}
}
