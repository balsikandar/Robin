package com.balsikandar.android.robin.logger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.balsikandar.android.robin.callbacks.FragmentLifeCycleListener;
import com.balsikandar.android.robin.callbacks.LifeCycleCallbacks;
import com.balsikandar.android.robin.callbacks.ScreenViewCallback;

/**
 * Created by bali on 27/05/18.
 */

public class FragmentDataLogger extends FragmentLifeCycleListener {

    private ScreenViewCallback viewsCallback;
    private LifeCycleCallbacks eventCallbacks;

    FragmentDataLogger() {
        //default constructor
    }

    FragmentDataLogger(Context context) {
        initialiseCallbacks(context);
    }

    private void initialiseCallbacks(Context context) {
        if (context instanceof ScreenViewCallback) {
            this.viewsCallback = (ScreenViewCallback) context;
        }
        if (context instanceof LifeCycleCallbacks) {
            this.eventCallbacks = (LifeCycleCallbacks) context;
        }
    }

    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentCreated(fm, f, savedInstanceState);

        sendScreenViews(f);

        LoggerUtil.logBundleDataOfFragment(f);

        sendFragmentCallbacks(f, "onFragmentCreated");
    }

    private void sendScreenViews(Fragment fragment) {
        if (viewsCallback != null) {
            String customScreenView = LoggerUtil.getUserProviedScreenName(fragment);
            viewsCallback.onScreenShown(fragment.getClass().getSimpleName(), customScreenView);
        }
    }


    @Override
    public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
        super.onFragmentViewCreated(fm, f, v, savedInstanceState);

        sendFragmentCallbacks(f, "onFragmentViewCreated");
    }

    @Override
    public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
        super.onFragmentAttached(fm, f, context);

        sendFragmentCallbacks(f, "onFragmentAttached");
    }

    @Override
    public void onFragmentPaused(FragmentManager fm, Fragment f) {
        super.onFragmentPaused(fm, f);

        sendFragmentCallbacks(f, "onFragmentPaused");
    }

    @Override
    public void onFragmentDetached(FragmentManager fm, Fragment f) {
        super.onFragmentDetached(fm, f);

        sendFragmentCallbacks(f, "onFragmentDetached");
    }

    @Override
    public void onFragmentResumed(FragmentManager fm, Fragment f) {
        super.onFragmentResumed(fm, f);

        sendFragmentCallbacks(f, "onFragmentResumed");
    }

    @Override
    public void onFragmentStarted(FragmentManager fm, Fragment f) {
        super.onFragmentStarted(fm, f);

        sendFragmentCallbacks(f, "onFragmentStarted");
    }

    @Override
    public void onFragmentStopped(FragmentManager fm, Fragment f) {
        super.onFragmentStopped(fm, f);

        sendFragmentCallbacks(f, "onFragmentStopped");
    }

    @Override
    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
        super.onFragmentViewDestroyed(fm, f);

        sendFragmentCallbacks(f, "onFragmentViewDestroyed");
    }

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
        super.onFragmentDestroyed(fm, f);

        sendFragmentCallbacks(f, "onFragmentDestroyed");
    }

    private void sendFragmentCallbacks(Fragment fragment, String callBackName) {

        if (eventCallbacks != null) {
            eventCallbacks.breadCrumps(fragment.getClass().getSimpleName(), callBackName);
        }
    }
}
