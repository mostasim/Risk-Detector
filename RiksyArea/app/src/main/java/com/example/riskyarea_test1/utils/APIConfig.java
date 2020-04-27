package com.example.riskyarea_test1.utils;

import com.example.riskyarea_test1.data.dto.DeviceDto;
import com.example.riskyarea_test1.data.dto.DoctorDto;
import com.example.riskyarea_test1.data.model.UserList;
import com.example.riskyarea_test1.data.model.UserLogIn;
import com.example.riskyarea_test1.data.model.UserSignUp;
import com.example.riskyarea_test1.data.model.response.Doctor;
import com.example.riskyarea_test1.data.model.response.MarkedPlace;
import com.example.riskyarea_test1.data.model.response.Passport;
import com.example.riskyarea_test1.data.model.response.SectionAnnouncement;
import com.example.riskyarea_test1.data.model.response.UpdateResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIConfig {

    @GET("rest/user/getAll")
    public Call<List<UserList>> getAllUser();

    @POST("rest/user/signUp")
    public Call<ResponseBody> signUp(@Body UserSignUp user);

    @POST("/rest/user/logIn")
    public Call<ResponseBody> logIn(@Body UserLogIn user);

    @GET("doctor/")
    public Call<ArrayList<Doctor>> getDoctorList();


    @POST("doctor/")
    public Call<ResponseBody> registerDoctor(@Body DoctorDto doctor);

    @GET("passport_info/")
    public Call<ArrayList<Passport>> getPassportList();

    @GET("marked_place/")
    public Call<ArrayList<MarkedPlace>> getMarkedPlace();

    @GET("announcements/")
    public Call<ArrayList<SectionAnnouncement>> getAnnouncement();

    @GET("updates/{city}")
    public Call<UpdateResponse> getUpdatesByLocation(@Path("city") String city);

    @POST("device_register/")
    public Call<ResponseBody> registerDevice(@Body DeviceDto deviceDto);


}
