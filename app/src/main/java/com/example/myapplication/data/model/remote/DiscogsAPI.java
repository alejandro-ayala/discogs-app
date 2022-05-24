package com.example.myapplication.data.model.remote;

import com.example.myapplication.data.model.Discogs;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DiscogsAPI {
        @GET("search?release_title=nevermind&artist=nirvana&per_page=3&page=1&token=CJksBcZlIjbFjXTOjtlAJHmdhZcBmMIMgKLwBWeF")
        Call<Discogs> getResponse();

}
