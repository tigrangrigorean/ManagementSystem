package com.airplane;

import java.util.Arrays;

import com.airplane.model.Passenger;
import com.airplane.service.PassengerService;

public class App 
{
    public static void main(String[] args)
    {
    	PassengerService ps = new PassengerService();
    	
    	Passenger p = new Passenger("Stephen Ammel", "614-347-1707","Canada","Torbay");
    	
    	System.out.println(ps.getPassengersOfTrip(1181));
    	
    	
    }
}
