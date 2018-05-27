package com.balsikandar.android.robin.logger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by bali on 27/05/18.
 */

public class LoggerUtil {

    private static final String TAG = "Robin/ ";

    private static HashMap<String, String> getDataFromBundle(Bundle bundle, String tagName, boolean returnKeyValueData) {
        try {

            Method method = bundle.getClass().getMethod("keySet");

            Set<String> keySet;

            if (!(method.invoke(bundle) instanceof Set<?>)) return null;

            method.setAccessible(true);

            keySet = (Set<String>) method.invoke(bundle);

            Gson gson = new Gson();
            HashMap<String, String> keyValueData = null;

            if (returnKeyValueData) {
                keyValueData = new HashMap<>();
            }

            for (String key : keySet) {

                Object value = bundle.getSerializable(key);
                if (exists(value)) {
                    logBundleKeyValuePair(tagName, gson, key, value, keyValueData);
                    continue;
                }

                value = bundle.getParcelable(key);
                if (exists(value)) {
                    logBundleKeyValuePair(tagName, gson, key, value, keyValueData);
                    continue;
                }

                value = bundle.get(key);
                if (exists(value)) {
                    logBundleKeyValuePair(tagName, gson, key, value, keyValueData);
                }
            }

            return keyValueData;
        } catch (Exception ignore) {
            ignore.printStackTrace();
            Log.e(TAG, "exception: " + ignore);
        }
        return null;
    }

    private static void logBundleKeyValuePair(String activityName, Gson gson, String key, Object value,
                                              HashMap<String, String> keyValueData) {
        if (exists(keyValueData)) {
            keyValueData.put(key, (String) value);
        }
        Log.e(TAG + activityName, "Key: " + key + " value : " + gson.toJson(value));
    }

    static String getUserProviedScreenName(Activity activity) {
        Field field;
        try {
            field = activity.getClass().getDeclaredField("screenView");

            field.setAccessible(true);

            Object value = field.get(activity);

            return (String) value;

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    static String getUserProviedScreenName(Fragment fragment) {
        Field field = null;
        try {
            field = fragment.getClass().getDeclaredField("screenView");

            field.setAccessible(true);

            Object value = field.get(fragment);

            return (String) value;

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static boolean exists(Object value) {
        return value != null;
    }

    static void logBundleDataOfActivity(Activity activity) {

        Intent intent = activity.getIntent();
        Bundle bundle;

        if (intent == null || (bundle = intent.getExtras()) == null) return;

        getDataFromBundle(bundle, activity.getClass().getSimpleName(), false);
    }

    static void logBundleDataOfFragment(Fragment fragment) {

        Bundle bundle = fragment.getArguments();

        if (bundle == null) return;

        getDataFromBundle(bundle, fragment.getClass().getSimpleName(), false);
    }

    public static void logData(Intent intent, String tagName) {
        Bundle bundle;
        if (intent == null || (bundle = intent.getExtras()) == null) return;

        getDataFromBundle(bundle, !TextUtils.isEmpty(tagName) ? tagName : TAG, false);
    }

    public static void logData(Bundle bundle, String tagName) {

        if (bundle == null) return;

        getDataFromBundle(bundle, !TextUtils.isEmpty(tagName) ? tagName : TAG, false);
    }

    public static HashMap<String, String> getDataAsMap(Intent intent) {

        Bundle bundle;
        if (intent == null || (bundle = intent.getExtras()) == null) return null;

        return getDataFromBundle(bundle, TAG, true);
    }

    public static HashMap<String, String> getDataAsMap(Bundle bundle) {

        if (bundle == null) return null;

        return getDataFromBundle(bundle, TAG, true);
    }

}
