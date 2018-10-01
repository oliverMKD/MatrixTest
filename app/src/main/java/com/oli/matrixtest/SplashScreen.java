package com.oli.matrixtest;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.oli.matrixtest.helpers.SharedPref;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class SplashScreen extends AppCompatActivity implements LocationListener {

    @BindView(R.id.splash_slika)
    ImageView slika;
    private static final String TAG = "SplashScreenActivity";
    private ProgressDialog pd = null;
    private Object data = null;
    Location newLocation = null;
    Context context;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proba_za_splash);
        context = this;
        setupLocation();
    }

    private void setupLocation() {
        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        boolean network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location location;

        if (network_enabled) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]
                                {Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_PERMISSION);
            }
            location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(location!=null){
              double  longitude = location.getLongitude();
               double latitude = location.getLatitude();
//                Picasso.with(SplashScreen.this).load(R.drawable.za_pane).fit().into(slika);
                SharedPref.addLng(String.valueOf(longitude),context);
                SharedPref.addLat(String.valueOf(latitude),context);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreen.this,HomeActivity.class);
                        startActivity(intent);
                    }
                },3000);
                Toast.makeText(SplashScreen.this, "Lat"+latitude+"long"+longitude, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {

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
}
