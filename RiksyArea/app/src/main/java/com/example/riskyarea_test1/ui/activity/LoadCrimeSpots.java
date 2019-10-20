package com.example.riskyarea_test1.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.riskyarea_test1.R;

/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
public class LoadCrimeSpots extends AppCompatActivity {

    boolean check = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_crime_spots);
    }
    @Override
    public void finish() {
        if(check==true) {
            double lati = 23.7738637;
            double longi = 90.3660227;
            // Prepare data intent
            Intent i = new Intent();
            i.putExtra("a_latitude", lati);
            i.putExtra("a_longitude", longi);
            // Activity finished ok, return the data
            setResult(RESULT_OK, i);
        }
        super.finish();
    }

    public void loadCrimeMaps(View v){
        check = true;
        finish();
    }
}
