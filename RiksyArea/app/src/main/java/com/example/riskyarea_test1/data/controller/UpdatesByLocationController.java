package com.example.riskyarea_test1.data.controller;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.riskyarea_test1.data.dto.DeviceDto;
import com.example.riskyarea_test1.utils.APIConfig;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdatesByLocationController extends BaseController {
    private static final String TAG = "UpdatesByLocation";
    private static UpdatesByLocationController updatesByLocationController;

    private Retrofit retrofit = getBuilder().build();
    private APIConfig apiConfig = retrofit.create(APIConfig.class);

    private MutableLiveData<ResponseBody> _update = new MutableLiveData<>();
    private LiveData<ResponseBody> update = _update;

    private UpdatesByLocationController() {
    }

    public static UpdatesByLocationController getController() {
        if (updatesByLocationController == null) {
            updatesByLocationController = new UpdatesByLocationController();
        }
        return updatesByLocationController;
    }


    public LiveData<ResponseBody> fetchUpdatesByLocation(String city) {
        apiConfig.getUpdatesByLocation(city).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                _update.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
        return update;
    }

    public void registerDevice(DeviceDto deviceDto) {
        apiConfig.registerDevice(deviceDto).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "onResponse: Device Register successful");
                } else {
                    Log.e(TAG, "onResponse: Device Register failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onResponse: Device Register error" + t.toString());
            }
        });

    }

}
