package com.oli.matrixtest;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.oli.matrixtest.fragmenti.CurrentWeather;
import com.oli.matrixtest.fragmenti.SearchForecastFragment;
import com.oli.matrixtest.fragmenti.SearchWeatherFragment;
import com.oli.matrixtest.fragmenti.WeatherForecast;
import com.oli.matrixtest.helpers.CustomViewPager;
import com.oli.matrixtest.helpers.SharedPref;
import com.oli.matrixtest.helpers.VPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    VPagerAdapter adapter;
    @BindView(R.id.vp1)
    CustomViewPager vp1;
    @BindView(R.id.tablayout1)
    TabLayout tab1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("search")){
            String extra = intent.getStringExtra("search");
            SharedPref.addCity(extra,this);
        }


        tab1.setupWithViewPager(vp1);
        adapter = new VPagerAdapter(getSupportFragmentManager());
        adapter.dodadiFragment(new SearchWeatherFragment(),"Current Weather");
        adapter.dodadiFragment(new SearchForecastFragment(), "Weather Forecast");
        vp1.setAdapter(adapter);
        vp1.setPagingEnabled(false);
        vp1.setCurrentItem(0);


    }








}
