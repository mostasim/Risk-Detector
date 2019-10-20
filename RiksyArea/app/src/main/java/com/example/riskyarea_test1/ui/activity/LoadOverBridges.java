package com.example.riskyarea_test1.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.riskyarea_test1.R;

public class LoadOverBridges extends AppCompatActivity {

    boolean check = false ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_over_bridges);
    }

    @Override
    public void finish() {
        if(check==true) {
            double lati = 23.7527583;
            double longi = 90.37771;
            // Prepare data intent
            Intent i = new Intent();
            i.putExtra("a_latitude", lati);
            i.putExtra("a_longitude", longi);
            // Activity finished ok, return the data
            setResult(RESULT_OK, i);
        }
        super.finish();
    }

    public void loadOverBridges(View v){
        check = true;
        finish();
    }
}
