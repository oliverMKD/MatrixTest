package com.oli.matrixtest.fragmenti;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.oli.matrixtest.MapsActivity;
import com.oli.matrixtest.R;
import com.oli.matrixtest.api.RestApi;
import com.oli.matrixtest.common.Common;
import com.oli.matrixtest.helpers.SharedPref;
import com.oli.matrixtest.klasi.OpenWeatherMap;
import com.oli.matrixtest.klasi.Weather;
import com.oli.matrixtest.model.WeatherModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentWeather extends Fragment {

    @BindView(R.id.txtCity)
    TextView city;
    @BindView(R.id.txtLastUpdate)
    TextView lastUpdate;
    @BindView(R.id.txtDescription)
    TextView description;
    @BindView(R.id.txtHumidity)
    TextView humidity;
    @BindView(R.id.txtTime)
    TextView time;
    @BindView(R.id.txtCelsius)
    TextView celsius;
    @BindView(R.id.imageview)
    ImageView slika;
    @BindView(R.id.Maps)
    Button mapi;
    @BindView(R.id.ForecastButton)
    Button prognoza;
    @BindView(R.id.segasnoVreme)
    LinearLayout segasnovreme;
    RestApi api;
    public WeatherModel weatherModel;
    Weather weather;
    OpenWeatherMap openWeatherMap;
    SharedPref sharedPref;

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_currentweather, null);

        mUnbinder = ButterKnife.bind(this, view);
        GetCurrentW();
        prognoza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpedFragment();
            }
        });
        mapi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MapsActivity.class));
            }
        });

        return view;
    }



    public void GetCurrentW(){
        api = new RestApi(getActivity());

        String lat = SharedPref.getLat(getActivity());
        String lon = SharedPref.getLng(getActivity());
        String units = "metric";
       final int i = 0;
        Call<OpenWeatherMap> call = api.getWeather(units,lat,lon);
        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {
                if (response.isSuccessful()){
                    openWeatherMap = response.body();
                    description.setText(openWeatherMap.weather.get(i).getDescription());
                    city.setText(openWeatherMap.getName()+","+openWeatherMap.getSys().getCountry());
                    humidity.setText(String.format("%d%%",openWeatherMap.getMain().getHumidity()));
                    celsius.setText(String.format("%.2f °C",openWeatherMap.getMain().getTemp()));
                    lastUpdate.setText(Common.getDateNow());
                    time.setText(String.format("%s/%s",Common.UnixTimeStamp(openWeatherMap.getSys().getSunrise()),Common.UnixTimeStamp(openWeatherMap.getSys().getSunset())));
                    Picasso.with(getActivity()).load(Common.getImage(openWeatherMap.weather.get(i).getIcon())).into(slika);
                } else if (!response.isSuccessful()){
                    Toast.makeText(getActivity(), "NE PROAGJA", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<OpenWeatherMap> call, Throwable t) {

            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
    private void addExpedFragment(){
        Fragment exped = WeatherForecast.secondInstance();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.segasnoVreme, exped).commit();
    }



}
