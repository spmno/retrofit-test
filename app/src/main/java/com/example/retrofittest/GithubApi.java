package com.example.retrofittest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {
    @GET("users/{name}")
    Observable<GithubUserResponse> getUserInfo(@Path("name") String name);
}
