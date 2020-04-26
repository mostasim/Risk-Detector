package com.example.riskyarea_test1.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.ui.activity.healthcheckup.HealthCheckUpFragment;

public class HealthCheckUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_check_up_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, HealthCheckUpFragment.newInstance())
                    .commitNow();
        }
    }
}
