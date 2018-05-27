package com.balsikandar.android.robin.callbacks;

/**
 * Created by bali on 27/05/18.
 */

/**
 * An interface to get the {@param className} & {@param customScreenView} of newly created
 * activity or fragment.
 * <p>
 * Declare field {@code screenView} in your activity or fragment.
 * {@code customScreenView} returns "screenView" value set in Activity/Fragment.
 * <p>
 * {@code className} returns className of newly created Activity/Fragment
 * <p>
 * You can use these params to log screen view of your app that user visited
 * using your analytics client
 */
public interface ScreenViewCallback {

    void onScreenShown(String className, String customScreenName);

}
