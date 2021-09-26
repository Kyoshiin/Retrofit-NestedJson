package com.roy.demoretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GoogleBooksAPI {

    @GET("volumes?q=donald")
    Call<SearchResult> getPosts();

}
