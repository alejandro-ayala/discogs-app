package com.example.myapplication;


import android.util.Log;

import com.example.myapplication.data.model.Discogs;
import com.example.myapplication.data.model.remote.DiscogsAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String title = "nevermind";
        String artist = "nirvana";
        String token = "CJksBcZlIjbFjXTOjtlAJHmdhZcBmMIMgKLwBWeF";

        DiscogsAPI discogsAPI = retrofit.create(DiscogsAPI.class);
        System.out.println("getResponse");
        //Call<Discogs> call = discogsAPI.getResponse("nirvana");
        Call<Discogs> call = discogsAPI.getResponse();
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
            assert discogsResponse != null;
            System.out.println("response: " + discogsResponse.toString());
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



