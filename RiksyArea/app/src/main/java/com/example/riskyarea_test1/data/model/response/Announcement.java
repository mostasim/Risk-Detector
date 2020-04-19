package com.example.riskyarea_test1.data.model.response;

import com.google.gson.annotations.SerializedName;

public class Announcement{

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("details")
	private String details;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("modified_at")
	private String modifiedAt;

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setDetails(String details){
		this.details = details;
	}

	public String getDetails(){
		return details;
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

	public void setModifiedAt(String modifiedAt){
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedAt(){
		return modifiedAt;
	}

	@Override
 	public String toString(){
		return 
			"Announcement{" + 
			"created_at = '" + createdAt + '\'' + 
			",details = '" + details + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",modified_at = '" + modifiedAt + '\'' + 
			"}";
		}
}