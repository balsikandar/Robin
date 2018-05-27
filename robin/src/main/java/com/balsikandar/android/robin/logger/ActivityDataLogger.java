package com.balsikandar.android.robin.logger;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.balsikandar.android.robin.callbacks.ActivityLifeCycleListener;
import com.balsikandar.android.robin.callbacks.ApplicationEventCallbacks;
import com.balsikandar.android.robin.callbacks.ScreenViewsCallbacks;

/**
 * Created by bali on 27/05/18.
 */

public class ActivityDataLogger extends ActivityLifeCycleListener {

    private ScreenViewsCallbacks viewsCallback;
    private ApplicationEventCallbacks eventCallbacks;
    private static FragmentDataLogger fragmentDataLogger;

    public ActivityDataLogger() {
        fragmentDataLogger = new FragmentDataLogger();
    }

    public ActivityDataLogger(Application application) {
        initialiseCallbacks(application);
        fragmentDataLogger = new FragmentDataLogger(application);
    }

    private void initialiseCallbacks(Context context) {
        this.viewsCallback = (ScreenViewsCallbacks) context;
        this.eventCallbacks = (ApplicationEventCallbacks) context;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        super.onActivityCreated(activity, savedInstanceState);

        if (activity instanceof FragmentActivity && fragmentDataLogger != null) {
            ((FragmentActivity) activity)
                    .getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(fragmentDataLogger, true);
        }

        sendScreenViews(activity);

        LoggerUtil.logBundleDataOfActivity(activity);

        sendActivityCallbacks(activity, "onCreate");
    }

    private void sendScreenViews(Activity activity) {
        if (viewsCallback != null) {
            String customScreenView = LoggerUtil.getUserProviedScreenName(activity);
            viewsCallback.screenShown(activity.getClass().getSimpleName(), customScreenView);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        super.onActivityStarted(activity);

        sendActivityCallbacks(activity, "onStart");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        super.onActivityResumed(activity);

        sendActivityCallbacks(activity, "onResume");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        super.onActivityPaused(activity);

        sendActivityCallbacks(activity, "onPause");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        super.onActivityStopped(activity);

        sendActivityCallbacks(activity, "onStop");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        super.onActivitySaveInstanceState(activity, outState);

        sendActivityCallbacks(activity, "onSavedInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        super.onActivityDestroyed(activity);

        if (activity instanceof FragmentActivity && fragmentDataLogger != null) {
            ((FragmentActivity) activity)
                    .getSupportFragmentManager()
                    .unregisterFragmentLifecycleCallbacks(fragmentDataLogger);
        }

        sendActivityCallbacks(activity, "onDestroy");
    }

    private void sendActivityCallbacks(Activity activity, String callBackName) {

        if (eventCallbacks != null) {
            eventCallbacks.breadCrumps(activity.getClass().getSimpleName(), callBackName);
        }
    }
}
