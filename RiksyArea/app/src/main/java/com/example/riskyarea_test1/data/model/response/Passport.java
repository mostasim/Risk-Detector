package com.example.riskyarea_test1.data.model.response;

import com.google.gson.annotations.SerializedName;

public class Passport{

	@SerializedName("address")
	private String address;

	@SerializedName("gender")
	private int gender;

	@SerializedName("emergency_contact_phone_number")
	private String emergencyContactPhoneNumber;

	@SerializedName("emergency_contact_address")
	private String emergencyContactAddress;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("emergency_contact_name")
	private String emergencyContactName;

	@SerializedName("mothers_name")
	private String mothersName;

	@SerializedName("passport_number")
	private String passportNumber;

	@SerializedName("name")
	private String name;

	@SerializedName("phone_number")
	private String phoneNumber;

	@SerializedName("id")
	private int id;

	@SerializedName("modified_at")
	private String modifiedAt;

	@SerializedName("fathers_name")
	private String fathersName;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setGender(int gender){
		this.gender = gender;
	}

	public int getGender(){
		return gender;
	}

	public void setEmergencyContactPhoneNumber(String emergencyContactPhoneNumber){
		this.emergencyContactPhoneNumber = emergencyContactPhoneNumber;
	}

	public String getEmergencyContactPhoneNumber(){
		return emergencyContactPhoneNumber;
	}

	public void setEmergencyContactAddress(String emergencyContactAddress){
		this.emergencyContactAddress = emergencyContactAddress;
	}

	public String getEmergencyContactAddress(){
		return emergencyContactAddress;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setEmergencyContactName(String emergencyContactName){
		this.emergencyContactName = emergencyContactName;
	}

	public String getEmergencyContactName(){
		return emergencyContactName;
	}

	public void setMothersName(String mothersName){
		this.mothersName = mothersName;
	}

	public String getMothersName(){
		return mothersName;
	}

	public void setPassportNumber(String passportNumber){
		this.passportNumber = passportNumber;
	}

	public String getPassportNumber(){
		return passportNumber;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
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

	public void setFathersName(String fathersName){
		this.fathersName = fathersName;
	}

	public String getFathersName(){
		return fathersName;
	}

	@Override
 	public String toString(){
		return 
			"Passport{" + 
			"address = '" + address + '\'' + 
			",gender = '" + gender + '\'' + 
			",emergency_contact_phone_number = '" + emergencyContactPhoneNumber + '\'' + 
			",emergency_contact_address = '" + emergencyContactAddress + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",emergency_contact_name = '" + emergencyContactName + '\'' + 
			",mothers_name = '" + mothersName + '\'' + 
			",passport_number = '" + passportNumber + '\'' + 
			",name = '" + name + '\'' + 
			",phone_number = '" + phoneNumber + '\'' + 
			",id = '" + id + '\'' + 
			",modified_at = '" + modifiedAt + '\'' + 
			",fathers_name = '" + fathersName + '\'' + 
			"}";
		}
}