package edu.lecture.android.practice.application;

import java.util.HashMap;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class GlobalApplication extends Application {
    public static final String PREF_APP = "prefApp";
    public static final String PROPERTY_ON_SCREEN = "propertyOnScreen";

    private static GlobalApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public void onTerminate() {
        instance = null;
        super.onTerminate();
    }

    public static GlobalApplication getInstance() {
        return instance;
    }

    public static int getAppVersion() {
        try {
            PackageInfo packageInfo = instance.getPackageManager().getPackageInfo(instance.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            // should never happen
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    private SharedPreferences getPref() {
        return getSharedPreferences(PREF_APP, MODE_PRIVATE);
    }

}
