package com.example.riskyarea_test1.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.riskyarea_test1.R;

public class Alarm extends AppCompatActivity {
    boolean check = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
    }

    @Override
    public void finish() {
        if(check==true) {
            double lati =23.752574;
            double longi = 90.377375;
            // Prepare data intent
            Intent i = new Intent();
            i.putExtra("alarm_location_latitude", lati);
            i.putExtra("alarm_location_longitude", longi);
            // Activity finished ok, return the data
            setResult(RESULT_OK, i);
        }
        super.finish();
    }

    public void SetAlram(View v){
        check = true;
        finish();
    }
}
