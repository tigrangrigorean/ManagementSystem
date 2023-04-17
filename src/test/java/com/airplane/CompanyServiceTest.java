package com.airplane;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

import com.airplane.model.Company;
import com.airplane.service.CompanyService;

public class CompanyServiceTest {

	/**
	 * Method tests whether the method works getById
	 */
	@Test
	public void getByIdTest() {
	    Company companyToFind = new Company();
	    companyToFind.setId(1009);
	    companyToFind.setCompanyName("Test Company");
	    companyToFind.setFoundingDate("2022-01-01");

	    CompanyService companyService = new CompanyService();
	    companyService.save(companyToFind);

	    Company foundCompany = companyService.getById(1009);

	    assertEquals(companyToFind, foundCompany);
	}
	
	/**
	 * Method tests whether it throws an exception
	 */
	@Test(expected = NoSuchElementException.class)
	public void GetByIdNoSuchElementExceptionTest() {
	    CompanyService companyService = new CompanyService();
	    companyService.getById(-1);
	}
	
	/**
	 * The method tests whether it is saved correctly
	 */
	@Test
	public void saveCompanySuccessTest() {
	    CompanyService companyService = new CompanyService();
	    Company company = new Company("Test Company", "2022-01-01");
	    Company savedCompany = companyService.save(company);
	    assertNotNull(savedCompany.getId());
	}
	
	/**
	 * Method tests to catch the exception NullPointerException
	 */
	@Test(expected = NullPointerException.class)
	public void saveCompanyNullTest() {
	    CompanyService companyService = new CompanyService();
	    Company company = null;
	    companyService.save(company);
	}

}
