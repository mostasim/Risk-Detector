package com.example.riskyarea_test1.data.model.response;

import com.google.gson.annotations.SerializedName;

public class Announcement {

	@SerializedName("details")
	private String details;

	@SerializedName("time")
	private String time;

	@SerializedName("title")
	private String title;

	public void setDetails(String details){
		this.details = details;
	}

	public String getDetails(){
		return details;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"AnnouncementsItem{" + 
			"details = '" + details + '\'' + 
			",time = '" + time + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}