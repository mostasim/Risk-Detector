package com.example.riskyarea_test1.data.model.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SectionAnnouncement{

	@SerializedName("date")
	private String date;

	@SerializedName("total")
	private int total;

	@SerializedName("announcements")
	private List<Announcement> announcements;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setAnnouncements(List<Announcement> announcements){
		this.announcements = announcements;
	}

	public List<Announcement> getAnnouncements(){
		return announcements;
	}

	@Override
 	public String toString(){
		return 
			"SectionAnnouncement{" + 
			"date = '" + date + '\'' + 
			",total = '" + total + '\'' + 
			",announcements = '" + announcements + '\'' + 
			"}";
		}
}