package com.example.retrofittest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("top-headlines")
    Observable<NewsResponse> getNewsList(@Query("sources") String newsSource, @Query("apiKey") String apiKey);
}
