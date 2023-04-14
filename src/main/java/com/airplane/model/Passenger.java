package com.airplane.model;

public class Passenger implements Comparable{
	
	private int id;
	private String name;
	private String phone;
	private String country;
	private String city;
	
	/**
	 * Default constructor;
	 */
	public Passenger() {
		
	}

	/**
	 * @param id
	 * @param phone
	 * @param country
	 * @param city
	 */
	public Passenger(String name, String phone, String country, String city) {
		super();
		this.name = name;
		this.phone = phone;
		this.country = country;
		this.city = city;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", name=" + name + ", phone=" + phone + ", country=" + country + ", city=" + city
				+ "]\n";
	}
	

	@Override
	public int compareTo(Object o) {
		 if (this.id > ((Passenger) o).getId()){
		        return 1;
		    } else if (this.id < ((Passenger) o).getId()){
		        return -1;
		    }
		    return 0;
	}

}
