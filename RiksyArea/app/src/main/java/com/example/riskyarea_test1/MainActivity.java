package com.example.riskyarea_test1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.riskyarea_test1.data.APIConfig;
import com.example.riskyarea_test1.model.User;

import org.w3c.dom.Text;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView) findViewById(R.id.tvTest);

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://192.168.1.213:8087/").
                addConverterFactory(GsonConverterFactory.create());


        Retrofit retrofit=builder.build();

        APIConfig apiConfig = retrofit.create(APIConfig.class);

        Call<List<User>> call= (Call<List<User>>) apiConfig.getAllUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.e("__TAG__",response.body().get(0).getName());
                textView.setText("Name : "+response.body().get(0).getName()+"\nAge  : "+response.body().get(0).getAge());


            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("__TAG__",t.getMessage());

            }
        });
    }
}
