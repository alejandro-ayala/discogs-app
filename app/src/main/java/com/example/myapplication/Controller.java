package com.example.myapplication;


import android.util.Log;

import com.example.myapplication.data.model.Discogs;
import com.example.myapplication.data.model.Result;
import com.example.myapplication.data.model.remote.DiscogsAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<Discogs> {
    static final String BASE_URL = "https://api.discogs.com/database/";
    public void start(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        DiscogsAPI discogsAPI = retrofit.create(DiscogsAPI.class);
        System.out.println("getResponse");
        String artist = "nirvana";
        String title = "nevermind";
        String token = "CJksBcZlIjbFjXTOjtlAJHmdhZcBmMIMgKLwBWeF";
        String per_page = "3";
        String page = "1";


        Call<Discogs> call = discogsAPI.getResponse(title,artist,per_page,page,token);
        //Call<Discogs> call = discogsAPI.getResponse();
        System.out.println("getResponse done!");
        call.enqueue(this);

    }
    @Override
    public void onResponse(Call<Discogs> call, Response<Discogs> response){
        System.out.println("onResponse");
        Log.d("Call request", call.request().toString());
        Log.d("Call request header", call.request().headers().toString());
        if(response.isSuccessful()) {
            System.out.println("onResponse isSuccessful");
            Discogs discogsResponse = response.body();
            List<Discogs.Result> results = discogsResponse.getResults();

            results.forEach(result -> System.out.println(result.getCountry()));

        } else {
            System.out.println("onResponse errorBody");
            System.out.println(response.errorBody());
            System.out.println("ERROR "+response.raw().body());
        }
    }
    @Override
    public void onFailure(Call<Discogs> call, Throwable t){
        System.out.println("onFailure");
        Log.d("Call request", call.request().toString());
        Log.d("Call request header", call.request().headers().toString());

        t.printStackTrace();
    }
}



