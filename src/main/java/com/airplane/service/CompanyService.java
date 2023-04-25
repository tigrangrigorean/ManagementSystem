package com.airplane.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
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
		try (Connection connection = Connections.getInstance().getConnection();
			Statement statement = connection.createStatement()){
			
			String sql = "SELECT * FROM company WHERE id = " + id;
			ResultSet resultSet = statement.executeQuery(sql);
			company = new Company();
			
			if(resultSet.next()) {
				company.setId(resultSet.getInt("id"));
				company.setCompanyName(resultSet.getString("company_name"));
				company.setFoundingDate(resultSet.getString("founding_date"));
			}else {
				throw new NoSuchElementException();
			}
			
		} catch (SQLException a) {
			System.out.println("SQL Command Exception");
		} catch(NoSuchElementException b) {
			throw new NoSuchElementException("Can't find element in database");
		} catch(NullPointerException c) {
			throw new NullPointerException("Result Set,statement, or connection is Null");
		}
		return company;
	}
	
	/**
	 * Method gets all companies from Table
	 * @return
	 */
	public Set<Company> getAll() {
		 Set<Company> allUsers = new LinkedHashSet<Company>();
			try(Connection connection = Connections.getInstance().getConnection();
			Statement statement = connection.createStatement()) {
				String sql = "SELECT * FROM company";
				ResultSet resultSet = statement.executeQuery(sql);
				while(resultSet.next()) {
					Company company = new Company();
					company.setId(resultSet.getInt("id"));
					company.setCompanyName(resultSet.getString("company_name"));
					company.setFoundingDate(resultSet.getString("founding_date"));
					allUsers.add(company);
				}
			}  catch (SQLException e) {
				System.out.println("SQL Command Exception");
			} catch(NullPointerException c) {
				throw new NullPointerException("Result Set,statement, or connection is Null");
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
		
		
		try(Connection connection = Connections.getInstance().getConnection();
			     PreparedStatement statement = connection.prepareStatement("SELECT * FROM company WHERE id >= ? ORDER BY " + sort + " LIMIT ?")) {
			
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
			System.out.println("SQL Command Exception");
		} catch(NullPointerException c) {
			throw new NullPointerException("Result Set,statement, or connection is Null");
		}
			
		return companyList;
	}
	 
	/**
	 * Method saves new Company
	 * @param company
	 * @return
	 */
	public Company save(Company company) {
		 
		 try(Connection connection = Connections.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO company(company_name,founding_date) VALUES(?,?)")) {
			
			statement.setString(1, company.getCompanyName());
			statement.setString(2, company.getFoundingDate());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL Command Exception");
		} catch(NullPointerException c) {
			throw new NullPointerException("Result Set,statement, or connection is Null");
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
		 		 
		 try (Connection connection = Connections.getInstance().getConnection();
					PreparedStatement statement = connection.prepareStatement("UPDATE company SET company_name = ?,founding_date = ? WHERE id = " + id)) {
			 
			Statement stResult =  connection.createStatement();
			ResultSet rs = stResult.executeQuery("SELECT * FROM company WHERE id = " + id);
			if(rs.next()) {
			statement.setString(1, company.getCompanyName());
			statement.setString(2, company.getFoundingDate());
			statement.executeUpdate();
			} else {
				throw new NoSuchElementException();
			}
		} catch (SQLException e) {
			System.out.println("SQL command exception");
		} catch(NoSuchElementException b) {
			throw new NoSuchElementException("Can't find element in database");
		} catch(NullPointerException c) {
			throw new NullPointerException("Result Set,statement, or connection is Null");
		}
		 return company;
	 }
	
	 /**
	  * Method removes company by id
	  * @param companyId
	  */
	 public void delete(int companyId) {
		 try (Connection connection = Connections.getInstance().getConnection();
				Statement statement = connection.createStatement()){
				
				ResultSet rs = statement.executeQuery("SELECT * FROM company WHERE id = " + companyId);
				String sql = "DELETE FROM company WHERE id = " + companyId;
				if(rs.next()) {
					statement.executeUpdate(sql);
				} else {
					throw new NoSuchElementException();
				}
				
			} catch (SQLException e) {
				System.out.println("SQL command exception");
			} catch(NoSuchElementException b) {
				throw new NoSuchElementException("Can't find element in database");
			} catch(NullPointerException c) {
				throw new NullPointerException("Result Set,statement, or connection is Null");
			}
	 }
	
	
}
