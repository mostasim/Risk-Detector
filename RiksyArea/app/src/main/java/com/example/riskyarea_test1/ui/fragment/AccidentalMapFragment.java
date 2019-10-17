package com.example.riskyarea_test1.ui.fragment;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.ui.activity.Alarm;
import com.example.riskyarea_test1.ui.activity.LoginActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
public class AccidentalMapFragment extends Fragment {

    private GoogleMap mMap;
    private LatLng location;
    double alarm_location_latitude = 0;
    double alarm_location_longitutde = 0;
    private double current_location_latitude = 0;
    private double current_location_longitutde = 0;
    private LocationManager lm ;

    final static int REQUEST_CODE = 1 ;
    Circle circle ;
    boolean state = false ;
    public AccidentalMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.maps_fragment_accidental, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.accidentalMap);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap=googleMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers
                mMap.setMyLocationEnabled(true);
                lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
                getMyLocation();
                location=new LatLng(current_location_latitude, current_location_longitutde);
                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(current_location_latitude,current_location_longitutde))
                        .zoom(18)
                        .bearing(0)
                        .tilt(45)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(current_location_latitude, current_location_longitutde))
                        .title("Shukrabaad Overbridge")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.rsz_crime_image))
                        .snippet("Crime Type : Murder"));
                addAlaram();

            }
        });

        return rootView;
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    // update the current location of user
    public  void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else {

            Location loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            current_location_latitude = loc.getLatitude();
            current_location_longitutde = loc.getLongitude();
            // Toast.makeText(getApplicationContext(),current_location_latitude+" , "+ current_location_longitutde , Toast.LENGTH_SHORT).show();
        }
    }

    public void addAlaram(){
        //getMyLocation();
        Intent i = new Intent(getActivity(), Alarm.class);
        i.putExtra("longitude" ,current_location_longitutde );
        i.putExtra("latitude" ,current_location_latitude );
        startActivityForResult(i, REQUEST_CODE);
    }

    // Checks whether user is inside of circle or not
    public boolean IsInCircle(){
        float distance[] ={0,0,0};
        Location.distanceBetween( current_location_latitude,current_location_longitutde,
                circle.getCenter().latitude, circle.getCenter().longitude, distance);
        if( distance[0] > circle.getRadius())
            return false;
        else
            return true;
    }
    //-----------After Alarm Set ---------------------
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE)
        {
            if (data.hasExtra("alarm_location_latitude") && data.hasExtra("alarm_location_longitude")) {
                state = true;
                alarm_location_latitude = data.getExtras().getDouble("alarm_location_latitude");
                alarm_location_longitutde = data.getExtras().getDouble("alarm_location_longitude");

                location = new LatLng(alarm_location_latitude, alarm_location_longitutde);
                mMap.addMarker(new MarkerOptions().position(location).title("Alarm Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                // Add a circle of radius 50 meter
                circle = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(alarm_location_latitude, alarm_location_longitutde))
                        .radius(50).strokeColor(Color.RED).fillColor(Color.BLUE));

                //--------------- Check user is in Range or Not after 5 Seconds --------
                final Handler handler = new Handler();
                final int delay = 5000; //milliseconds
                handler.postDelayed(new Runnable(){
                    public void run(){
                        //do something
                        getMyLocation();
                        if(IsInCircle()){
                            if(state==true)
                            {
                                Toast.makeText(getActivity(),"At Location",Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getActivity().getApplicationContext(), MyBroadcastReceiver.class);
//                                PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                                        getActivity().getApplicationContext(), 234324243, intent, 0);
//                                AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
//                                alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
//                                        + ( 100), pendingIntent);
//                                state = false;


                            }
                        }
                        handler.postDelayed(this, delay);
                    }
                }, delay);


            }
        }

    }

}
