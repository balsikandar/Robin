package com.balsikandar.android.robinsample;

import android.app.Application;

import com.balsikandar.android.robin.callbacks.LifeCycleCallbacks;
import com.balsikandar.android.robin.callbacks.ScreenViewCallback;

/**
 * Created by bali on 27/05/18.
 */

public class RobinSampleApplication extends Application implements ScreenViewCallback, LifeCycleCallbacks {

    private String screenView = "RobinSampleHomePage";

    @Override
    public void onScreenShown(String className, String customScreenView) {

    }

    @Override
    public void breadCrumps(String name, String callback) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
