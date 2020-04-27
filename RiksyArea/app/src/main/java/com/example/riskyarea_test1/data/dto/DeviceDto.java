package com.example.riskyarea_test1.data.dto;

import com.google.gson.annotations.SerializedName;

public class DeviceDto{

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("imei")
	private int imei;

	@SerializedName("place")
	private String place;

	@SerializedName("longitude")
	private double longitude;

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setImei(int imei){
		this.imei = imei;
	}

	public int getImei(){
		return imei;
	}

	public void setPlace(String place){
		this.place = place;
	}

	public String getPlace(){
		return place;
	}

	public void setLongitude(double longitude){
		this.longitude = longitude;
	}

	public double getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"DeviceDto{" + 
			"latitude = '" + latitude + '\'' + 
			",imei = '" + imei + '\'' + 
			",place = '" + place + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}