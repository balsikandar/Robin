package com.balsikandar.android.robinsample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.balsikandar.android.robinsample.R;

public class MainActivity extends AppCompatActivity {

    private String screenView = "RobinSampleHomePage";      //this'll be returned in onScreenShows

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
