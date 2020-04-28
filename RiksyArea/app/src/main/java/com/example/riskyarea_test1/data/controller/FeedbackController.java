package com.example.riskyarea_test1.data.controller;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.riskyarea_test1.data.dto.FeedbackDto;
import com.example.riskyarea_test1.utils.APIConfig;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FeedbackController extends BaseController {
    private static final String TAG = "FeedbackController";
    private static FeedbackController feedbackController;
    MutableLiveData<ResponseBody> _response = new MutableLiveData<>();
    private Retrofit retrofit = getBuilder().build();
    private APIConfig apiConfig = retrofit.create(APIConfig.class);

    private FeedbackController() {
    }

    public static FeedbackController getFeedbackController() {
        if (feedbackController == null) {
            feedbackController = new FeedbackController();
        }
        return feedbackController;
    }

    public void sendFeedback(FeedbackDto feedbackDto) {
        Log.e(TAG, "sendFeedback: Called" );
        apiConfig.dailyFeedback(feedbackDto).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    _response.postValue(response.body());
                    Log.e(TAG, "onResponse: Success" + response.code());
                } else {
                    Log.e(TAG, "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
    }
}
