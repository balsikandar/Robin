package com.balsikandar.android.robinsample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.balsikandar.android.robinsample.util.AppConstants;

public class SplashActivity extends AppCompatActivity {

    private String screenView = "RobinSampleSplashScreen";      //this'll be returned in onScreenShows

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);

        //Add extras data
        intent.putExtra(AppConstants.FROM_SPLASH, true);
        intent.putExtra(AppConstants.CLASS_NAME, SplashActivity.class.getSimpleName());
        intent.putExtra(AppConstants.FAV_NUMBER, 7);
        startActivity(intent);
        finish();
    }
}
