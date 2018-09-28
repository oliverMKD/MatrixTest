package com.oli.matrixtest.fragmenti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oli.matrixtest.R;
import com.oli.matrixtest.api.RestApi;
import com.oli.matrixtest.helpers.RecyclerAdapter;
import com.oli.matrixtest.helpers.SharedPref;
import com.oli.matrixtest.klasi.Model;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherForecast extends Fragment {

    public static Fragment secondInstance() {
        Fragment eFrag = new WeatherForecast();
        return eFrag;
    }

    @BindView(R.id.MyRV)
    RecyclerView rv;
    RecyclerAdapter adapter;
    RestApi api;
    Model model;

    private Unbinder mUnbinder;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_forecast, null);

        mUnbinder = ButterKnife.bind(this, view);
        adapter = new RecyclerAdapter(getActivity());


        WeatherForcast();
        return view;
    }
    public void WeatherForcast(){
        api = new RestApi(getActivity());
        String lat = SharedPref.getLat(getActivity());
        String lon = SharedPref.getLng(getActivity());
        String units = "metric";
        final int i = 0;

        Call<Model> call = api.getForecast(units,lat,lon);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful()){
                    model = response.body();

                    Toast.makeText(getActivity(), "Response successful", Toast.LENGTH_SHORT).show();
                } else if (!response.isSuccessful()){


                }
                adapter = new RecyclerAdapter(getActivity());
                rv.setHasFixedSize(true);
                rv.setLayoutManager(new GridLayoutManager(getActivity(),1));
                rv.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
