package com.example.riskyarea_test1.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.riskyarea_test1.R;
import com.example.riskyarea_test1.data.model.SettingsValues;
import com.example.riskyarea_test1.data.model.response.MarkedPlace;
import com.example.riskyarea_test1.helper.SendNotification;
import com.example.riskyarea_test1.ui.activity.LoadOverBridges;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
public class OverBridgesMapFragment extends Fragment implements LocationListener {

    final static int REQUEST_CODE = 1;
    double alarm_location_latitude = 0;
    double alarm_location_longitutde = 0;
    Circle circle;
    boolean state = false;
    LocationManager locationManager;
    Criteria criteria;
    String bestProvider;
    private GoogleMap mMap;
    private LatLng location;
    private double current_location_latitude = 0;
    private double current_location_longitutde = 0;
    private LocationManager lm;
    private int delay;
    private int radius;

    Handler handler;
    Runnable runnable;
    public OverBridgesMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MapViewModel mViewModel = ViewModelProviders.of(this).get(MapViewModel.class);

        mViewModel.getMarkedAreaList().observe(this, new Observer<ArrayList<MarkedPlace>>() {
            @Override
            public void onChanged(ArrayList<MarkedPlace> markedPlaces) {
                Log.e("RESPONSE", Arrays.toString(markedPlaces.toArray()));
                drawMarkedArea(markedPlaces);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.maps_fragment_custom, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.customMap);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers
                mMap.setMyLocationEnabled(true);
                lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                getMyLocation();
                location = new LatLng(current_location_latitude, current_location_longitutde);
                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(current_location_latitude, current_location_longitutde))
                        .zoom(18)
                        .bearing(0)
                        .tilt(45)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(current_location_latitude, current_location_longitutde))
                        .title("Your Location"));
//                addAlaram1();

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
    public void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else {
            locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
            criteria = new Criteria();
            bestProvider = String.valueOf(lm.getBestProvider(criteria, true)).toString();

            Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (loc != null) {
                current_location_latitude = loc.getLatitude();
                current_location_longitutde = loc.getLongitude();
                // Toast.makeText(getApplicationContext(),current_location_latitude+" , "+ current_location_longitutde , Toast.LENGTH_SHORT).show();
            } else {
                locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
            }

        }
    }

    public void addAlaram1() {
        //getMyLocation();
        Intent i = new Intent(getActivity(), LoadOverBridges.class);
        i.putExtra("longitude", current_location_longitutde);
        i.putExtra("latitude", current_location_latitude);
        startActivityForResult(i, REQUEST_CODE);
    }

    // Checks whether user is inside of circle or not
    public boolean IsInCircle() {
        float distance[] = {0, 0, 0};
        Location.distanceBetween(current_location_latitude, current_location_longitutde,
                circle.getCenter().latitude, circle.getCenter().longitude, distance);
        Log.e("RESULT",""+Arrays.toString(distance)+" radius "+circle.getRadius() );
        if (distance[0] > circle.getRadius())
            return false;
        else
            return true;
    }

    private void createMarker(double latitude, double longitude, String title, String snippet) {

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
//                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet("Type : "+snippet)
                .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.over_bridge_red)));
    }

    public void drawMarkedArea(ArrayList<MarkedPlace> markedPlaces) {

        for (int i = 0; i < markedPlaces.size(); i++) {

            createMarker(markedPlaces.get(i).getLatitude(), markedPlaces.get(i).getLongitude(), markedPlaces.get(i).getTitle(), markedPlaces.get(i).getMarkedAs());
        }
        for (int i = 0; i < markedPlaces.size(); i++) {

           circle =  mMap.addCircle(new CircleOptions().center(new LatLng(markedPlaces.get(i).getLatitude(), markedPlaces.get(i).getLongitude())).radius(markedPlaces.get(i).getRadius()).strokeColor(Color.GREEN).fillColor(Color.RED));
        }
        radius = Integer.parseInt(SettingsValues.getRadius());
        delay = Integer.parseInt(SettingsValues.getRefresh());
        delay = delay * 1000;
        state = true;
//        alarm_location_latitude = 25.63875;
//        alarm_location_longitutde =  88.645633;
//
//        location = new LatLng(alarm_location_latitude, alarm_location_longitutde);
//        mMap.addMarker(new MarkerOptions().position(location).title("DIU-SWE Over Bridge")
//                .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.over_bridge_red))
//                .snippet("Location : Dhanmondi 32"));
//
////                25.637558, 88.645393
//        LatLng second_location = new LatLng(25.637558,88.645393);
//        mMap.addMarker(new MarkerOptions().position(second_location).title("Another")
//                .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.over_bridge_red))
//                .snippet("Location : Fakirpara"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        // Add a circle of radius 50 meter
//        circle = mMap.addCircle(new CircleOptions()
//                .center(new LatLng(alarm_location_latitude, alarm_location_longitutde))
//                .radius(radius).strokeColor(Color.RED).fillColor(Color.GREEN));
//        mMap.addCircle(new CircleOptions().center(second_location).radius(radius).strokeColor(Color.GREEN).fillColor(Color.RED));
        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(current_location_latitude, current_location_longitutde))
                .zoom(18)
                .bearing(0)
                .tilt(45)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

        //--------------- Check user is in Range or Not after 5 Seconds --------
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                //do something
                getMyLocation();
                if (IsInCircle()) {
                    if (state) {
                        SendNotification sendNotification = new SendNotification(getActivity());
                        sendNotification.execute("Over-Bridge");
                        Toast.makeText(getActivity(), "You are near to a over bridge", Toast.LENGTH_SHORT).show();
                        try {
                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                            Ringtone r = RingtoneManager.getRingtone(getActivity().getApplicationContext(), notification);
                            r.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                } else {

                }
                handler.postDelayed(this, delay);
            }
        };
        handler.postDelayed(runnable, delay);


    }
    //-----------After LoadAccidentalPlaces Set ---------------------
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        radius = Integer.parseInt(SettingsValues.getRadius());
//        delay = Integer.parseInt(SettingsValues.getRefresh());
//        delay=delay*1000;
//        if (requestCode == REQUEST_CODE)
//        {
//            if (data.hasExtra("a_latitude") && data.hasExtra("a_longitude")) {
//                state = true;
//                alarm_location_latitude = data.getExtras().getDouble("a_latitude");
//                alarm_location_longitutde = data.getExtras().getDouble("a_longitude");
//
//                location = new LatLng(alarm_location_latitude, alarm_location_longitutde);
//                mMap.addMarker(new MarkerOptions().position(location).title("DIU-SWE Over Bridge")
//                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.over_bridge_red))
//                        .snippet("Location : Dhanmondi 32"));
//
////                25.637558, 88.645393
//                LatLng second_location = new LatLng(25.637558,88.645393);
//                mMap.addMarker(new MarkerOptions().position(second_location).title("Another")
//                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.over_bridge_red))
//                        .snippet("Location : Fakirpara"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
//                // Add a circle of radius 50 meter
//                circle = mMap.addCircle(new CircleOptions()
//                        .center(new LatLng(alarm_location_latitude, alarm_location_longitutde))
//                        .radius(radius).strokeColor(Color.RED).fillColor(Color.GREEN));
//                mMap.addCircle(new CircleOptions().center(second_location).radius(radius).strokeColor(Color.GREEN).fillColor(Color.RED));
//                CameraPosition googlePlex = CameraPosition.builder()
//                        .target(new LatLng(current_location_latitude,current_location_longitutde))
//                        .zoom(18)
//                        .bearing(0)
//                        .tilt(45)
//                        .build();
//                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);
//
//                //--------------- Check user is in Range or Not after 5 Seconds --------
//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable(){
//                    public void run(){
//                        //do something
//                        getMyLocation();
//                        if(IsInCircle()){
//                            if(state==true)
//                            {
//                                SendNotification sendNotification = new SendNotification(getActivity());
//                                sendNotification.execute("Over-Bridge");
//                                Toast.makeText(getActivity(),"You are near to a over bridge",Toast.LENGTH_SHORT).show();
//                                try {
//                                    Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//                                    Ringtone r = RingtoneManager.getRingtone(getActivity().getApplicationContext(), notification);
//                                    r.play();
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//
//
//                            }
//                        }
//                        else
//                        {
//
//                        }
//                        handler.postDelayed(this, delay);
//                    }
//                }, delay);
//
//
//            }
//        }
//
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onLocationChanged(Location location) {
        locationManager.removeUpdates(this);

        //open the map:
        current_location_latitude = location.getLatitude();
        current_location_longitutde = location.getLongitude();
//        Toast.makeText(MainActivity.this, "latitude:" + latitude + " longitude:" + longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
