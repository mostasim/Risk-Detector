package com.example.riskyarea_test1.data.model.response;

import com.google.gson.annotations.SerializedName;

public class Doctor {

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("degree")
	private String degree;

	@SerializedName("specialized_in")
	private String specializedIn;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("modified_at")
	private String modifiedAt;

	@SerializedName("email")
	private String email;

	@SerializedName("working_place")
	private String workingPlace;

	@SerializedName("location_want_to_serve_in")
	private String locationWantToServeIn;

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDegree(String degree){
		this.degree = degree;
	}

	public String getDegree(){
		return degree;
	}

	public void setSpecializedIn(String specializedIn){
		this.specializedIn = specializedIn;
	}

	public String getSpecializedIn(){
		return specializedIn;
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

	public void setModifiedAt(String modifiedAt){
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedAt(){
		return modifiedAt;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setWorkingPlace(String workingPlace){
		this.workingPlace = workingPlace;
	}

	public String getWorkingPlace(){
		return workingPlace;
	}

	public void setLocationWantToServeIn(String locationWantToServeIn){
		this.locationWantToServeIn = locationWantToServeIn;
	}

	public String getLocationWantToServeIn(){
		return locationWantToServeIn;
	}

	@Override
 	public String toString(){
		return 
			"PassportListResponse{" + 
			"phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",degree = '" + degree + '\'' + 
			",specialized_in = '" + specializedIn + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",modified_at = '" + modifiedAt + '\'' + 
			",email = '" + email + '\'' + 
			",working_place = '" + workingPlace + '\'' + 
			",location_want_to_serve_in = '" + locationWantToServeIn + '\'' + 
			"}";
		}
}