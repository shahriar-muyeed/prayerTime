package com.example.prayer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.prayer.mapLocator.name;

public class mapFragment  extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapView;
    View mView;
    Float lat;
    Float lon;
    Button bt;
    EditText et;
    String name;
    public static String s;

    public mapFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.map_frag,container,false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView=(MapView)mView.findViewById(R.id.map);
        if(mMapView!=null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

        mapLocator process2= new mapLocator();
        process2.execute();

        bt=mMapView.findViewById(R.id.btmap);






        //Log.d("map", String.valueOf(mapLocator.lat));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        googleMap.addMarker(new MarkerOptions().position(new LatLng(23.780733, 90.409166)).title("Gausul Azam Jame Masjid"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(23.779317, 90.409166)).title("Mohakhali TB Gate Jame Mosjid"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(23.782302, 90.413779)).title("Sapra Masjid"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(23.780837, 90.407747)).title("bracU"));
        CameraPosition basha2= CameraPosition.builder().target(new LatLng(23.780837, 90.407747)).zoom(16).bearing(0).tilt(0).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(basha2));


    }
}
