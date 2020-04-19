package com.example.riskyarea_test1.data.model.response;

import com.google.gson.annotations.SerializedName;

public class MarkedPlace{

	@SerializedName("marked_as")
	private String markedAs;

	@SerializedName("latitude")
	private double latitude;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("radius")
	private int radius;

	@SerializedName("modified_at")
	private String modifiedAt;

	@SerializedName("longitude")
	private double longitude;

	public void setMarkedAs(String markedAs){
		this.markedAs = markedAs;
	}

	public String getMarkedAs(){
		return markedAs;
	}

	public void setLatitude(double latitude){
		this.latitude = latitude;
	}

	public double getLatitude(){
		return latitude;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setRadius(int radius){
		this.radius = radius;
	}

	public int getRadius(){
		return radius;
	}

	public void setModifiedAt(String modifiedAt){
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedAt(){
		return modifiedAt;
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
			"MarkedPlace{" + 
			"marked_as = '" + markedAs + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",radius = '" + radius + '\'' + 
			",modified_at = '" + modifiedAt + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}