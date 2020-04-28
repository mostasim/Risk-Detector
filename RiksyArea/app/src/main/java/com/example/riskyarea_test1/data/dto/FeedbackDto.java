package com.example.riskyarea_test1.data.dto;

import com.google.gson.annotations.SerializedName;

public class FeedbackDto{

	@SerializedName("result")
	private String result;

	@SerializedName("taking_treatment_for_other_disease")
	private boolean takingTreatmentForOtherDisease;

	@SerializedName("in_contact_with_covid19_positive_people_recently")
	private boolean inContactWithCovid19PositivePeopleRecently;

	@SerializedName("cough_throat_pain")
	private boolean coughThroatPain;

	@SerializedName("recent_foreign_return")
	private boolean recentForeignReturn;

	@SerializedName("imei")
	private String imei;

	@SerializedName("has_fever")
	private boolean hasFever;

	@SerializedName("has_breathing_problems")
	private boolean hasBreathingProblems;

	@SerializedName("in_contact_with_infected_people_recently")
	private boolean inContactWithInfectedPeopleRecently;

	@SerializedName("age")
	private String age;

	public void setResult(String result){
		this.result = result;
	}

	public String getResult(){
		return result;
	}

	public void setTakingTreatmentForOtherDisease(boolean takingTreatmentForOtherDisease){
		this.takingTreatmentForOtherDisease = takingTreatmentForOtherDisease;
	}

	public boolean isTakingTreatmentForOtherDisease(){
		return takingTreatmentForOtherDisease;
	}

	public void setInContactWithCovid19PositivePeopleRecently(boolean inContactWithCovid19PositivePeopleRecently){
		this.inContactWithCovid19PositivePeopleRecently = inContactWithCovid19PositivePeopleRecently;
	}

	public boolean isInContactWithCovid19PositivePeopleRecently(){
		return inContactWithCovid19PositivePeopleRecently;
	}

	public void setCoughThroatPain(boolean coughThroatPain){
		this.coughThroatPain = coughThroatPain;
	}

	public boolean isCoughThroatPain(){
		return coughThroatPain;
	}

	public void setRecentForeignReturn(boolean recentForeignReturn){
		this.recentForeignReturn = recentForeignReturn;
	}

	public boolean isRecentForeignReturn(){
		return recentForeignReturn;
	}

	public void setImei(String imei){
		this.imei = imei;
	}

	public String getImei(){
		return imei;
	}

	public void setHasFever(boolean hasFever){
		this.hasFever = hasFever;
	}

	public boolean isHasFever(){
		return hasFever;
	}

	public void setHasBreathingProblems(boolean hasBreathingProblems){
		this.hasBreathingProblems = hasBreathingProblems;
	}

	public boolean isHasBreathingProblems(){
		return hasBreathingProblems;
	}

	public void setInContactWithInfectedPeopleRecently(boolean inContactWithInfectedPeopleRecently){
		this.inContactWithInfectedPeopleRecently = inContactWithInfectedPeopleRecently;
	}

	public boolean isInContactWithInfectedPeopleRecently(){
		return inContactWithInfectedPeopleRecently;
	}

	public void setAge(String age){
		this.age = age;
	}

	public String getAge(){
		return age;
	}

	@Override
 	public String toString(){
		return 
			"FeedbackDto{" + 
			"result = '" + result + '\'' + 
			",taking_treatment_for_other_disease = '" + takingTreatmentForOtherDisease + '\'' + 
			",in_contact_with_covid19_positive_people_recently = '" + inContactWithCovid19PositivePeopleRecently + '\'' + 
			",cough_throat_pain = '" + coughThroatPain + '\'' + 
			",recent_foreign_return = '" + recentForeignReturn + '\'' + 
			",imei = '" + imei + '\'' + 
			",has_fever = '" + hasFever + '\'' + 
			",has_breathing_problems = '" + hasBreathingProblems + '\'' + 
			",in_contact_with_infected_people_recently = '" + inContactWithInfectedPeopleRecently + '\'' + 
			",age = '" + age + '\'' + 
			"}";
		}
}