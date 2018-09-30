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
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchWeatherFragment extends Fragment {


    @BindView(R.id.txtCity)
    TextView txtcity;
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


    RestApi api;
    public WeatherModel weatherModel;
    Weather weather;
    OpenWeatherMap openWeatherMap;
    SharedPref sharedPref;


    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.search_weather_fragment, null);

        mUnbinder = ButterKnife.bind(this, view);

povik();
        return view;
    }

    public void povik ( ) {

        api = new RestApi(getActivity());

        final String city= "London";
        String units = "metric";

        Call<OpenWeatherMap> call = api.SearchWeather(units,city);

        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {

                if(response.isSuccessful()) {
                    openWeatherMap = response.body();


                    txtcity.setText(openWeatherMap.getName() + " , " + openWeatherMap.sys.getCountry()  );

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


}
