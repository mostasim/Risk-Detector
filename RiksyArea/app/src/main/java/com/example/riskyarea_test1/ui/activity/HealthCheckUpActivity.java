package com.example.riskyarea_test1.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.ui.activity.healthcheckup.HealthCheckUpFragment;
import com.example.riskyarea_test1.utils.InfoHubApplication;

public class HealthCheckUpActivity extends AppCompatActivity {
    private static final String TAG = "HealthCheckUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_check_up_activity);

        InfoHubApplication.getInstance().setInHealthCheckUpActivityIsOn(true);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, HealthCheckUpFragment.newInstance())
                    .commitNow();
        }
    }

    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment)
                .commitNow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
        InfoHubApplication.getInstance().setInHealthCheckUpActivityIsOn(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
