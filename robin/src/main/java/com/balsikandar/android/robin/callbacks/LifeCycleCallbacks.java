package com.balsikandar.android.robin.callbacks;

/**
 * Created by bali on 27/05/18.
 */

/**
 * This interface provides Activity/Fragment Lifecycle callbacks
 * which can be logged to Crashlytics using
 * <p>
 * {@code Crashlytics.log()} to track user interaction if a crash occurs
 * <p>
 * It'll be highly useful for crash logs where we can't determine point of origin
 */
public interface LifeCycleCallbacks {

    void breadCrumps(String className, String callback);

}
