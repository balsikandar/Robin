package com.balsikandar.android.robinsample;

import android.app.Application;
import android.util.Log;

import com.balsikandar.android.robin.Robin;
import com.balsikandar.android.robin.callbacks.LifeCycleCallbacks;
import com.balsikandar.android.robin.callbacks.ScreenViewCallback;

/**
 * Created by bali on 27/05/18.
 */

public class RobinSampleApplication extends Application implements ScreenViewCallback, LifeCycleCallbacks {

    @Override
    public void onScreenShown(String className, String customScreenName) {
        Log.e("Screen", className + " | " + customScreenName);
    }

    @Override
    public void breadCrumps(String className, String callback) {
        Log.e("Screen", className + " | " + callback);
        //Crashlytics.log(name + " | " + callback);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Robin.start(this);
    }
}
