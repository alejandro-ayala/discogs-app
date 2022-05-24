package com.example.myapplication.data.model.remote;

import com.example.myapplication.data.model.Discogs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DiscogsAPI {
        @GET("search")

        Call<Discogs> getResponse(@Query("release_title") String release_title, @Query("artist") String artist, @Query("per_page") String page, @Query("page") String per_page, @Query("token") String token);
//?release_title=nevermind&artist={?artist}&per_page=3&page=1&token=CJksBcZlIjbFjXTOjtlAJHmdhZcBmMIMgKLwBWeF
}
