package com.example.riskyarea_test1.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.riskyarea_test1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
/**
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 2019-10-14
 */
public class CrimeMapFragment extends Fragment {


    private LatLng location;
    double current_location_latitude = 0;
    double current_location_longitutde = 0;
    public LocationManager lm ;
    final static int REQUEST_CODE = 1 ;
    public CrimeMapFragment() {
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
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

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
                        .position(new LatLng(current_location_latitude, current_location_longitutde)) //23.752574, 90.377375
                        .title("Your Location Overbridge")
                        .icon(bitmapDescriptorFromVector(getActivity(),R.drawable.rsz_crime_image))
                                .snippet("Crime Type : Murder"));

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

}
