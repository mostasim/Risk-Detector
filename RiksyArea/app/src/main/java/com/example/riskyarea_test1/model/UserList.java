package com.example.riskyarea_test1.model;


import com.google.gson.annotations.SerializedName;
public class UserList {

	@SerializedName("password")
	private String password;

	@SerializedName("phoneNumber")
	private String phoneNumber;

	@SerializedName("address")
	private Object address;

	@SerializedName("gender")
	private Object gender;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("age")
	private String age;

	@SerializedName("email")
	private String email;

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

	public void setAddress(Object address){
		this.address = address;
	}

	public Object getAddress(){
		return address;
	}

	public void setGender(Object gender){
		this.gender = gender;
	}

	public Object getGender(){
		return gender;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAge(String age){
		this.age = age;
	}

	public String getAge(){
		return age;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}