package com.example.retrofittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.retrofittest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private GithubUserViewModel githubUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(activityMainBinding.getRoot());
        githubUserViewModel = new ViewModelProvider(this).get(GithubUserViewModel.class);
        githubUserViewModel.init();
        githubUserViewModel.getGithubUserResponseMutableLiveData().observe(this,
                userInfo -> {
                    Log.i("Main", userInfo.toString());
                    activityMainBinding.editText.setText(userInfo.toString());
                }
        );
        githubUserViewModel.getUserInfo();
    }
}
