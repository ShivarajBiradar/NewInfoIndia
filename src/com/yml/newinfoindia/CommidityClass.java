package com.yml.newinfoindia;

public class CommidityClass {

	String recharge_value;
	String recharge_talktime;
	String recharge_validity;
	String recharge_short_description;
	String recharge_description;
	//String circle_master;
	//String operator_master;
	
	public CommidityClass(String value,String talktime,String validity,String SDecription, String recharge_description)
	
	{
		
		recharge_value=value;
		recharge_talktime=talktime;
		recharge_validity=validity;
		recharge_short_description=SDecription;
		this.recharge_description=recharge_description;
		//circle_master=cMaster;
		///operator_master=Omaster;
		
	}

}

 class CarClass{
	String model;
	String fuel_type;
	String price;
	String state;
	String crawl_date;
	
	public CarClass(String model,String fuel_type,String price,String state,String crawl_date) {
		// TODO Auto-generated constructor stub
		this.model=model;
		this.fuel_type=fuel_type;
		this.price=price;
		this.state=state;
		this.crawl_date=crawl_date;
	}

	
}
 class WeatherClass
 {
	 String minimum;
	 String date;
	 String maximum;
	 String moonset;
	 String sunset;
	 //String sunrise;
	 
	public WeatherClass(String minimum,String date,String maximum,String moonset,String sunset) {
		// TODO Auto-generated constructor stub
		this.minimum=minimum;
		this.date=date;
		this.maximum=maximum;
		this.moonset=moonset;
		this.sunset=sunset;
		//this.sunrise=sunrise;
	}
	 
 }
