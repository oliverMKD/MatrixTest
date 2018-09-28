package com.oli.matrixtest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.oli.matrixtest.fragmenti.CurrentWeather;
import com.oli.matrixtest.fragmenti.WeatherForecast;
import com.oli.matrixtest.helpers.CustomViewPager;
import com.oli.matrixtest.helpers.VPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    public VPagerAdapter adapter;
    public @BindView(R.id.vp)
    CustomViewPager vPage;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
        ButterKnife.bind(this);
        adapter = new VPagerAdapter(getSupportFragmentManager());
        adapter.dodadiFragment(new CurrentWeather());
        adapter.dodadiFragment(new WeatherForecast());
        vPage.setAdapter(adapter);
        vPage.setPagingEnabled(false);
        vPage.setCurrentItem(0);

    }

    @Override
    public void onBackPressed() {

    }
}
