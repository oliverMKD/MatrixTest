package com.oli.matrixtest;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.oli.matrixtest.fragmenti.CurrentWeather;
import com.oli.matrixtest.fragmenti.WeatherForecast;
import com.oli.matrixtest.helpers.CustomViewPager;
import com.oli.matrixtest.helpers.SharedPref;
import com.oli.matrixtest.helpers.VPagerAdapter;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    LocationManager locationManager;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    Marker marker;
    LocationListener locationListener;

    public VPagerAdapter adapter;
    public @BindView(R.id.vp)
    CustomViewPager vPage;
    @BindView(R.id.tablayout)
    public TabLayout tabLayout;
    @BindView(R.id.editSearch)
    EditText searchText;
    @BindView(R.id.kopce_search)
    Button kopceSearch;
    Context context;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        ButterKnife.bind(this);



        tabLayout.setupWithViewPager(vPage);
        adapter = new VPagerAdapter(getSupportFragmentManager());
        adapter.dodadiFragment(new CurrentWeather(),"Current Weather");
        adapter.dodadiFragment(new WeatherForecast(), "Weather Forecast");
        vPage.setAdapter(adapter);
        vPage.setPagingEnabled(false);
        vPage.setCurrentItem(0);
        SearchText();
    }
    public void SearchText(){
        searchText.addTextChangedListener(new TextWatcher() {

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override

            public void onTextChanged(final CharSequence s, int start, int before, int count) {

                handler = new Handler();

                handler.postDelayed(new Runnable() {

                    @Override

                    public void run() {

                        Log.e("handler", s + "");

                        if (s.toString().isEmpty()) {


                        } else {

                            String movie = searchText.getText().toString();

                            performSearch(movie);
                        }
                    }
                }, 1000);

            }

            @Override

            public void afterTextChanged(Editable s) {

            }

        });

    }

    private void performSearch(final String aaa) {

        kopceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                intent.putExtra("search", aaa);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {

    }




    public void GetLocation(){
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                SharedPref.addLat(String.valueOf(latitude),getApplicationContext());
                SharedPref.addLng(String.valueOf(longitude),getApplicationContext());
                //get the location name from latitude and longitude
                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    List<Address> addresses =
                            geocoder.getFromLocation(latitude, longitude, 1);
                    String result = addresses.get(0).getLocality()+":";
                    result += addresses.get(0).getCountryName();
                    LatLng latLng = new LatLng(latitude, longitude);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }
}
