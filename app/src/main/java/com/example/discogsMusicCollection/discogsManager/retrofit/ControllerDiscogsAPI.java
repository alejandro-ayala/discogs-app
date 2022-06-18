package com.example.discogsMusicCollection.discogsManager.retrofit;


import android.util.Log;

import com.example.discogsMusicCollection.discogsManager.DiscogsViewModel;
import com.example.discogsMusicCollection.discogsManager.model.Discogs;
import com.example.discogsMusicCollection.discogsManager.model.remote.DiscogsAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControllerDiscogsAPI implements Callback<Discogs> {
    private static final String TAG = "Controller";
    DiscogsAPI discogsAPI;
    static final String BASE_URL = "https://api.discogs.com/database/";

    public void startRetrofitService(){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        discogsAPI = retrofit.create(DiscogsAPI.class);

    }
    @Override
    public void onResponse(Call<Discogs> call, Response<Discogs> response){
        Log.d(TAG, "onResponse");

        if(response.isSuccessful()) {
            Log.d(TAG, "onResponse isSuccessful");

            Discogs discogsResponse = response.body();
            List<Discogs.Result> results = discogsResponse.getResults();


            RetrofitObservable.getInstance(). notifyObserverWithResponse(results);

        } else {
            Log.d(TAG, "onResponse errorBody");
            System.out.println(response.errorBody());
            System.out.println("ERROR "+response.raw().body());
        }
    }
    @Override
    public void onFailure(Call<Discogs> call, Throwable t){
        System.out.println("onFailure");
        Log.d(TAG, "onFailure");

        Log.d("Call request", call.request().toString());
        Log.d("Call request header", call.request().headers().toString());

        t.printStackTrace();
    }

    public void requestDiscogsSearch(DiscogsSearchParameter searchRequest){
        Log.d(TAG, "requestDiscogsSearch");
        List<DiscogsViewModel> searchResponse = new ArrayList<DiscogsViewModel>();

        String token = "CJksBcZlIjbFjXTOjtlAJHmdhZcBmMIMgKLwBWeF";
        String title = searchRequest.getTitle();
        String artistName = searchRequest.getArtistName();
        String year = searchRequest.getYear();
        String format = searchRequest.getFormat();
        String genre = searchRequest.getGenre();
        String page = "1";
        String perPage = "10";
        Call<Discogs> call = discogsAPI.getResponse(title,artistName,perPage,page,year,format,genre,token);
        call.enqueue(this);

    }

}



