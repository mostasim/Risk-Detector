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
            double lati = 25.638751;  //25.638751, 88.645633
            double longi = 88.645633;
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
