package com.example.riskyarea_test1.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.data.model.SettingsValues;
import com.example.riskyarea_test1.ui.fragment.AccidentalMapFragment;

/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
public class SettingsActivity extends AppCompatActivity {

    int alertMeter;
    int refreshSec;
    EditText alertMeterText;
    EditText refreshSecText;
    String a;
    String b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        alertMeterText = findViewById(R.id.min_alert_meter);
        refreshSecText = findViewById(R.id.refresh_rate);



        Button changeBtn = findViewById(R.id.done);


        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=alertMeterText.getText().toString();
                b=refreshSecText.getText().toString();
                alertMeter = Integer.parseInt(a);
                refreshSec = Integer.parseInt(b);
                SettingsValues settingsValues=new SettingsValues(a,b);

                startActivity(new Intent(SettingsActivity.this,BottomNavActivity.class));
            }
        });


    }
}
