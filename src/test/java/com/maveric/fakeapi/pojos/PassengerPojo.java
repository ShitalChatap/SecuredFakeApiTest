package com.maveric.fakeapi.pojos;
 
public class PassengerPojo 
{
	private String name;
    private int trips;
    private int airline;
    
    
	public PassengerPojo() 
	{
		super();
	}
	public PassengerPojo(String name, int trips, int airline)
	{
		super();
		this.name = name;
		this.trips = trips;
		this.airline = airline;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public int getTrips() 
	{
		return trips;
	}
	public void setTrips(int trips) 
	{
		this.trips = trips;
	}
	public int getAirline() 
	{
		return airline;
	}
	public void setAirline(int airline)
	{
		this.airline = airline;
	}
    
    
}