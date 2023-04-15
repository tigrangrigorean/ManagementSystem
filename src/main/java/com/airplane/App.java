package com.airplane;

import com.airplane.model.Passenger;
import com.airplane.model.Trip;
import com.airplane.service.CompanyService;
import com.airplane.service.PassengerService;

public class App 
{
    public static void main(String[] args)
    {
    	CompanyService cs = new CompanyService();
    	
    	System.out.println(cs.getById(1));
    	
    	
    }
}
