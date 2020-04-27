package com.example.riskyarea_test1.data.controller;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.riskyarea_test1.data.dto.DeviceDto;
import com.example.riskyarea_test1.data.model.response.UpdateResponse;
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

    private MutableLiveData<UpdateResponse> _update = new MutableLiveData<>();
    private LiveData<UpdateResponse> update = _update;

    private UpdatesByLocationController() {
    }

    public static UpdatesByLocationController getController() {
        if (updatesByLocationController == null) {
            updatesByLocationController = new UpdatesByLocationController();
        }
        return updatesByLocationController;
    }


    public LiveData<UpdateResponse> fetchUpdatesByLocation(String city) {
        apiConfig.getUpdatesByLocation(city).enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                if (response.isSuccessful()) {
                    _update.postValue(response.body());
                } else {
                    Log.e(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
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
