package com.example.riskyarea_test1.data.model.response;

import com.google.gson.annotations.SerializedName;

public class UpdateResponse{

	@SerializedName("date")
	private String date;

	@SerializedName("total_infected_count")
	private int totalInfectedCount;

	@SerializedName("new_infected_count")
	private int newInfectedCount;

	@SerializedName("new_test_count")
	private int newTestCount;

	@SerializedName("total_test_count")
	private int totalTestCount;

	@SerializedName("total_death_count")
	private int totalDeathCount;

	@SerializedName("new_death_count")
	private int newDeathCount;

	@SerializedName("total_recover_count")
	private int totalRecoverCount;

	@SerializedName("new_recover_count")
	private int newRecoverCount;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setTotalInfectedCount(int totalInfectedCount){
		this.totalInfectedCount = totalInfectedCount;
	}

	public int getTotalInfectedCount(){
		return totalInfectedCount;
	}

	public void setNewInfectedCount(int newInfectedCount){
		this.newInfectedCount = newInfectedCount;
	}

	public int getNewInfectedCount(){
		return newInfectedCount;
	}

	public void setNewTestCount(int newTestCount){
		this.newTestCount = newTestCount;
	}

	public int getNewTestCount(){
		return newTestCount;
	}

	public void setTotalTestCount(int totalTestCount){
		this.totalTestCount = totalTestCount;
	}

	public int getTotalTestCount(){
		return totalTestCount;
	}

	public void setTotalDeathCount(int totalDeathCount){
		this.totalDeathCount = totalDeathCount;
	}

	public int getTotalDeathCount(){
		return totalDeathCount;
	}

	public void setNewDeathCount(int newDeathCount){
		this.newDeathCount = newDeathCount;
	}

	public int getNewDeathCount(){
		return newDeathCount;
	}

	public void setTotalRecoverCount(int totalRecoverCount){
		this.totalRecoverCount = totalRecoverCount;
	}

	public int getTotalRecoverCount(){
		return totalRecoverCount;
	}

	public void setNewRecoverCount(int newRecoverCount){
		this.newRecoverCount = newRecoverCount;
	}

	public int getNewRecoverCount(){
		return newRecoverCount;
	}

	@Override
 	public String toString(){
		return 
			"UpdateResponse{" + 
			"date = '" + date + '\'' + 
			",total_infected_count = '" + totalInfectedCount + '\'' + 
			",new_infected_count = '" + newInfectedCount + '\'' + 
			",new_test_count = '" + newTestCount + '\'' + 
			",total_test_count = '" + totalTestCount + '\'' + 
			",total_death_count = '" + totalDeathCount + '\'' + 
			",new_death_count = '" + newDeathCount + '\'' + 
			",total_recover_count = '" + totalRecoverCount + '\'' + 
			",new_recover_count = '" + newRecoverCount + '\'' + 
			"}";
		}
}