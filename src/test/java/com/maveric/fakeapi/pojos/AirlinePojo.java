package com.maveric.fakeapi.pojos;
 
public class AirlinePojo 
{
 
    private String _id;
    private String name;
    private String country;
    private String logo;
    private String slogan;
    private String head_quaters;
    private String website;
    private String established;
    
	public AirlinePojo() 
	{
		super();
		
	}
	public AirlinePojo(String _id, String name, String country, String logo, String slogan, String head_quaters,
			String website, String established) 
	{
		super();
		this._id = _id;
		this.name = name;
		this.country = country;
		this.logo = logo;
		this.slogan = slogan;
		this.head_quaters = head_quaters;
		this.website = website;
		this.established = established;
	}
	public String get_id() 
	{
		return _id;
	}
	public void set_id(String _id)
	{
		this._id = _id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getCountry()
	{
		return country;
	}
	public void setCountry(String country) 
	{
		this.country = country;
	}
	public String getLogo()
	{
		return logo;
	}
	public void setLogo(String logo)
	{
		this.logo = logo;
	}
	public String getSlogan() 
	{
		return slogan;
	}
	public void setSlogan(String slogan)
	{
		this.slogan = slogan;
	}
	public String getHead_quaters() 
	{
		return head_quaters;
	}
	public void setHead_quaters(String head_quaters) 
	{
		this.head_quaters = head_quaters;
	}
	public String getWebsite() 
	{
		return website;
	}
	public void setWebsite(String website) 
	{
		this.website = website;
	}
	public String getEstablished() 
	{
		return established;
	}
	public void setEstablished(String established)
	{
		this.established = established;
	}
	@Override
	public String toString() {
		return "AirlinePojo [_id=" + _id + ", name=" + name + ", country=" + country + ", logo=" + logo + ", slogan="
				+ slogan + ", head_quaters=" + head_quaters + ", website=" + website + ", established=" + established
				+ "]";
	}
    
    
    
    
	
    
    
}