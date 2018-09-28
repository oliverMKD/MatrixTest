package com.oli.matrixtest.api;

import android.content.Context;

import com.oli.matrixtest.BuildConfig;
import com.oli.matrixtest.helpers.LoggingInterceptor;
import com.oli.matrixtest.klasi.Model;
import com.oli.matrixtest.klasi.OpenWeatherMap;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApi {
    public static final int request_max_time_in_secconds = 20;
    private Context activity;

    public RestApi(Context context) {
        this.activity = activity;
    }

    public Retrofit getRetrofitInstance() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .writeTimeout(request_max_time_in_secconds, TimeUnit.SECONDS)
                .readTimeout(request_max_time_in_secconds, TimeUnit.SECONDS)
                .connectTimeout(request_max_time_in_secconds, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public ApiService request() {
        return getRetrofitInstance().create(ApiService.class);
    }

    public Call<OpenWeatherMap> getWeather(String units,String lat, String lon){
        return request().getCurrentWeather(units,lat,lon);
    }
    public Call<Model>getForecast(String units,String lat, String lon) {
        return request().getForecast(units, lat, lon);
    }


//    public Call<User> postAuthentication(String email,int register_type,String appkey, String device) {
//        return request().getToken(email,register_type,appkey,device);
//    }
//    public Call<Rewards> getNagradi(String token, String appkey) {
//        return request().getNagradi(token,appkey);
//    }
}
