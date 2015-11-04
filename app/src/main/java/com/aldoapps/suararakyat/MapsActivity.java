package com.aldoapps.suararakyat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final float ZOOM_LEVEL = 13f;
    private GoogleMap mMap;
    private BitmapDescriptor mIconOne;
    private BitmapDescriptor mIconTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng surabaya = new LatLng(-7.2653, 112.7425);
//        Marker thisMarker = mMap.addMarker(new MarkerOptions().position(surabaya).title("Pemilih di Surabaya"));

        mIconOne = BitmapDescriptorFactory.fromResource(R.drawable.ic_action_one);

        mIconTwo = BitmapDescriptorFactory.fromResource(R.drawable.ic_action_two);

        generateRandomMarker();

        CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(surabaya, ZOOM_LEVEL);
        // when googlemap is connected, move to current position quickly
        googleMap.animateCamera(cameraPosition,
                200, // Duration (1 millisecond), as fast as possible. 0 is not acceptable
                new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onCancel() {
                    }
                });
    }

    private List<Marker> generateRandomMarker() {
        List<Marker> markerList = new ArrayList<>();
        Random random = new Random(123);

        for(int i = 0; i < 115; i++){
            float randomLat = random.nextFloat() / 100f;
            float randomLan = random.nextFloat() / 100f;
            LatLng surabaya = new LatLng(-7.2653 + randomLat, 112.7425 + randomLan);
            if(random.nextBoolean()){
                Marker newMarker = mMap.addMarker(new MarkerOptions().position(surabaya).icon(mIconOne));
                markerList.add(newMarker);
            }else{
                Marker newMarker = mMap.addMarker(new MarkerOptions().position(surabaya).icon(mIconTwo));
                markerList.add(newMarker);
            }
        }

        return markerList;
    }
}
