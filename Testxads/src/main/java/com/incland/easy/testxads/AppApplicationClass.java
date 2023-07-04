package com.incland.easy.testxads;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class AppApplicationClass extends Application {
    public static Context mActivity;
    public static AppApplicationClass mInstance;
    public static Resources resources;
    private Boolean testMode = true;
    private String unityGameID = "4381313";

    static {
        System.loadLibrary("testadxvideo");
    }

    public void onCreate() {
        super.onCreate();
        AudienceNetworkAds.initialize(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mInstance = this;
        mActivity = this;
        resources = getResources();
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static synchronized AppApplicationClass getInstance() {
        AppApplicationClass appApplicationClass;
        synchronized (AppApplicationClass.class) {
            appApplicationClass = mInstance;
        }
        return appApplicationClass;
    }

    public static synchronized Context getContext() {
        Context applicationContext;
        synchronized (AppApplicationClass.class) {
            applicationContext = getInstance().getApplicationContext();
        }
        return applicationContext;
    }
}
