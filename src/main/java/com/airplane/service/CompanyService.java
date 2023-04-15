package com.airplane.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.Set;

import com.airplane.connections.Connections;
import com.airplane.model.Company;

public class CompanyService {
	
	/**
	 * Method gets by id Company
	 * @param id
	 * @return
	 */
	public Company getById(int id) {
		Company company = null;
		try {
			Connection connection = Connections.getConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM company WHERE id = " + id;
			ResultSet resultSet = statement.executeQuery(sql);
			statement.close();
			company = new Company();
			
			if(resultSet.next()) {
				company.setId(resultSet.getInt("id"));
				company.setCompanyName(resultSet.getString("company_name"));
				company.setFoundingDate(resultSet.getString("founding_date"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connections.closeConnection();
			
		}
		return company;
	}
	
	/**
	 * Method gets all companies from Table
	 * @return
	 */
	public Set<Company> getAll() {
		 Set<Company> allUsers = new LinkedHashSet<Company>();
			try {
				Connection connection = Connections.getConnection();
				Statement statement = connection.createStatement();
				String sql = "SELECT * FROM company";
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					Company company = new Company();
					company.setId(resultSet.getInt("id"));
					company.setCompanyName(resultSet.getString("company_name"));
					company.setFoundingDate(resultSet.getString("founding_date"));
					allUsers.add(company);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				Connections.closeConnection();
				
			}
			return allUsers;
	 }
	
	/**
	 * Method returns a Set starting from a certain id
	 * @param offset
	 * @param perPage
	 * @param sort
	 * @return
	 */
	public Set<Company> get(int offset, int perPage, String sort) {
		Set<Company> companyList = new LinkedHashSet<Company>();
		
		
		try {
			Connection connection = Connections.getConnection();
			String sql = "SELECT * FROM company WHERE id >= ? ORDER BY " + sort + " LIMIT ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, offset);
			statement.setInt(2, perPage);
			
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Company company = new Company();
				company.setId(resultSet.getInt("id"));
				company.setCompanyName(resultSet.getString("company_name"));
				company.setFoundingDate(resultSet.getString("founding_date"));
				companyList.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Connections.closeConnection();
			
		}
			
		return companyList;
	}
	 
	/**
	 * Method saves new Company
	 * @param company
	 * @return
	 */
	public Company save(Company company) {
		 
		 try {
			Connection connection = Connections.getConnection();
			String sql = "INSERT INTO company(company_name,founding_date) VALUES(?,?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, company.getCompanyName());
			statement.setString(2, company.getFoundingDate());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Connections.closeConnection();
			
		}
		  return company;
	 }
	
	/**
	 * Method updates company by ID
	 * @param id
	 * @param company
	 * @return
	 */
	 public Company update(int id,Company company) {
		 		 
		 try {
			Connection connection = Connections.getConnection();
			String sql = "UPDATE company SET company_name = ?,founding_date = ? WHERE id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, company.getCompanyName());
			statement.setString(2, company.getFoundingDate());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Connections.closeConnection();
			
		}
		 return company;
	 }
	
	 /**
	  * Method removes company by id
	  * @param companyId
	  */
	 public void delete(int companyId) {
		 try {
				Connection connection = Connections.getConnection();
				Statement statement = connection.createStatement();
				String sql = "DELETE FROM company WHERE id = " + companyId;
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				Connections.closeConnection();
				
			}
	 }
	
	
}
