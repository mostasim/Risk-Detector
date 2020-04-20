package com.example.riskyarea_test1.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.data.model.SettingsValues;
import com.example.riskyarea_test1.ui.fragment.DoctorListFragment;
import com.example.riskyarea_test1.ui.fragment.NotificationsListFragment;
import com.example.riskyarea_test1.ui.fragment.OverBridgesMapFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
public class BottomNavActivity extends AppCompatActivity {

    private TextView txtViewTitle;

    final Fragment fragment1 = new OverBridgesMapFragment();
    final Fragment fragment2 = new DoctorListFragment();
    final Fragment fragment3 = new NotificationsListFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    txtViewTitle.setText(getResources().getString(R.string.risky_area));
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;

                case R.id.navigation_dashboard:
                    txtViewTitle.setText(getResources().getString(R.string.doctor_list));
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;

                case R.id.navigation_notifications:
                    txtViewTitle.setText(getResources().getString(R.string.announcement));
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtViewTitle = findViewById(R.id.toolbar_title);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setItemIconTintList(null);

//        Dexter.withActivity(this)
//                .withPermissions(
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
//                        Manifest.permission.CALL_PHONE
//                ).withListener(new MultiplePermissionsListener() {
//            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
//                if (  report.areAllPermissionsGranted()){
//                    fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
//                    fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
//                    fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();
//                }
//            }
//            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                token.continuePermissionRequest();
//
//            }
//        }).check();
        loadFragments();

    }

    private void loadFragments() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Dexter.withActivity(this)
                    .withPermissions(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                            Manifest.permission.CALL_PHONE
                    ).withListener(new MultiplePermissionsListener() {
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {
                    if (report.areAllPermissionsGranted()) {
                        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
                        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
                        fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();
                    }
                }

                @Override
                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                    token.continuePermissionRequest();
                }
            }).check();
        } else {
            fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
            fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
            fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            showSettingsDialog();
            //startActivity(new Intent(BottomNavActivity.this,SettingsActivity.class));
            return true;
        } else if (id == R.id.action_register_doctor) {
            startActivity(new Intent(this, RegistrationActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    public void searchArea(View view) {
        int PLACE_PICKER_REQUEST = 1;
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    public void stopAlarm(View view) {
        SettingsValues.setRing(true);
    }

    public void showSettingsDialog() {
        final View layout;
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(layout = getLayoutInflater().inflate(R.layout.activity_settings, null));
        final AlertDialog alertDialog = builder.create();
        /*alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transperantWhite)));
        alertDialog.setTitle("Settings");*/

        layout.findViewById(R.id.done).setOnClickListener(view -> {
            final EditText delay = layout.findViewById(R.id.refresh_rate);
            final EditText radius = layout.findViewById(R.id.min_alert_meter);
            String delayString = delay.getText().toString();
            String radiusString = radius.getText().toString();
            SettingsValues.radius = radiusString;
            SettingsValues.refresh = delayString;
            alertDialog.dismiss();

            startActivity(new Intent(BottomNavActivity.this, BottomNavActivity.class));
        });

        layout.findViewById(R.id.btn_cancel).setOnClickListener(view -> {
            if (alertDialog != null && alertDialog.isShowing())
                alertDialog.dismiss();
        });

        alertDialog.show();
    }
}
