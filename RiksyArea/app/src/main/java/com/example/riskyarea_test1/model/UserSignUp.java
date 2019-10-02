package com.example.riskyarea_test1.model;
import com.google.gson.annotations.SerializedName;


public class UserSignUp {

	@SerializedName("password")
	private String password;

	@SerializedName("phoneNumber")
	private String phoneNumber;

	@SerializedName("gender")
	private String gender;

	@SerializedName("name")
	private String name;

	@SerializedName("email")
	private String email;

	@SerializedName("age")
	private int age;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAge(int age){
		this.age = age;
	}

	public int getAge(){
		return age;
	}
}