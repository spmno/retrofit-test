package com.example.retrofittest;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class GithubUserViewModel extends ViewModel {
    public MutableLiveData<GithubUserResponse> getGithubUserResponseMutableLiveData() {
        return githubUserResponseMutableLiveData;
    }

    public void setGithubUserResponseMutableLiveData(MutableLiveData<GithubUserResponse> githubUserResponseMutableLiveData) {
        this.githubUserResponseMutableLiveData = githubUserResponseMutableLiveData;
    }

    MutableLiveData<GithubUserResponse> githubUserResponseMutableLiveData;

    public void getUserInfo() {
        GithubApi githubApi = RetrofitService.createGithubService(GithubApi.class);
        githubApi.getUserInfo("spmno")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    userInfo -> {githubUserResponseMutableLiveData.setValue(userInfo);},
                        throwable -> {
                            Log.e("GithubApi", throwable.toString());}
                );
    }

    public void init() {
        GithubUserResponse githubUserResponse = new GithubUserResponse();
        githubUserResponseMutableLiveData = new MutableLiveData<>(githubUserResponse);
    }

}
