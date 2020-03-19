package com.example.retrofittest;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {

    public MutableLiveData<NewsResponse> getNewsResponseMutableLiveData() {
        return newsResponseMutableLiveData;
    }

    public void setNewsResponseMutableLiveData(MutableLiveData<NewsResponse> newsResponseMutableLiveData) {
        this.newsResponseMutableLiveData = newsResponseMutableLiveData;
    }

    private MutableLiveData<NewsResponse> newsResponseMutableLiveData;

    public void init() {
        NewsResponse newsResponse = new NewsResponse();
        newsResponseMutableLiveData = new MutableLiveData<>(newsResponse);
    }

    public void getNews() {
        NewsApi newsApi = RetrofitService.createNewsService(NewsApi.class);
        newsApi.getNewsList("techcrunch", "7d60a0fa1dec462eb682c0e256f709a8")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    newsData -> { newsResponseMutableLiveData.setValue(newsData);}
                    , throwable->{
                            Log.e("new", throwable.toString());
                    }
                );

    }
}
