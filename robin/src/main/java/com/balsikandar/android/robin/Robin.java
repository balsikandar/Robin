package com.balsikandar.android.robin;

/**
 * Created by bali on 27/05/18.
 */

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.balsikandar.android.robin.logger.ActivityDataLogger;
import com.balsikandar.android.robin.logger.LoggerUtil;

import java.util.HashMap;

/**
 * A collection of helper methods to assist you in debugging bundle data
 * <p>
 * The easiest way to use this class is to call {@link #start(Application)} in your app's
 * {@link Application#onCreate()} method.
 */
public class Robin {

    /**
     * pass an intent to print it's bundle data. If it doesn't carries any data
     * it'll print nothing
     *
     * @param intent  Intent carrying bundle data
     * @param tagName Field to search for printed Log in LogCat, If passed nothing
     *                default {@param tagName "Robin"} will be used
     */
    public static void logData(Intent intent, String tagName) {
        LoggerUtil.logData(intent, tagName);
    }

    /**
     * pass a bundle to print it's data if it doesn't carries any data
     * it'll print nothing
     *
     * @param bundle  Bundle carrying data
     * @param tagName Field to search for printed Log in LogCat, If passed nothing
     *                default {@param tagName "Robin"} will be used
     */
    public static void logData(Bundle bundle, String tagName) {
        LoggerUtil.logData(bundle, tagName);
    }

    /**
     * This API takes Intent as input and returns it's bundle data as Key-value
     * pair in HashMap
     *
     * @param intent Intent to get bundle data from
     */
    public static HashMap<String, String> getDataAsMap(Intent intent) {
        return LoggerUtil.getDataAsMap(intent);
    }

    /**
     * This API takes Intent as input and returns it's bundle data as Key-value
     * pair in HashMap
     *
     * @param bundle Intent to get bundle data from
     */
    public static HashMap<String, String> getDataAsMap(Bundle bundle) {
        return LoggerUtil.getDataAsMap(bundle);
    }

    /**
     * pass application context to intialise this library as
     * {@code Robin.start(this)}
     *
     * @param application application context
     */
    public static void start(Application application) {
        application.registerActivityLifecycleCallbacks(new ActivityDataLogger(application));
    }

    /**
     * pass application context to intialise this library as
     * {@code Robin.start(this, true)}. On passing false for {@param enableCallbacks} will not
     * trigger any screenViews and activity lifecycle callbacks.
     *
     * @param application      application context
     * @param enableCallbacks use this to enable and disable ScreenViews and Activity Callbacks
     */
    public static void start(Application application, boolean enableCallbacks) {
        ActivityDataLogger logger = enableCallbacks ? new ActivityDataLogger(application) : new ActivityDataLogger();
        application.registerActivityLifecycleCallbacks(logger);
    }

}
