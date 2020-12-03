package com.example.intergratedapplicationhub.application_activities;

import android.app.Application;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.List;

public class BackendlessApplication extends Application {
    public static final String APPLICATION_ID = "D6F24EF3-973C-6767-FF0A-1A3AA25B9300";
    public static final String API_KEY = "9EA3FB76-0A1F-4370-FF7D-F5AF0F2BA600";
    public static final String SERVER_URL = "https://api.backendless.com";

    public static BackendlessUser user;
    public static List<com.example.intergratedapplicationhub.entities.Application> applications;
    public static List<com.example.intergratedapplicationhub.entities.Marks> marks;
    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );


    }
}
