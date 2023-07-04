package com.adsfb.testadxvideo.XAds;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.adsfb.testadxvideo.SplashActivity;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import java.util.Date;

public class AppOpenManager implements Application.ActivityLifecycleCallbacks, LifecycleObserver {
    private static final String AD_UNIT_ID = SplashActivity.adOpenAd;
    private static final String LOG_TAG = "AppOpenManager";
    /* access modifiers changed from: private */
    public static boolean isShowingAd = false;
    boolean adsLoadnow = true;
    /* access modifiers changed from: private */
    public AppOpenAd appOpenAd = null;
    private Activity currentActivity;
    private AppOpenAd.AppOpenAdLoadCallback loadCallback;
    /* access modifiers changed from: private */
    public long loadTime = 0;
    private final AppApplicationClass myApplication;
    int myids1;
    OnAppOpenClose onAppOpenClose;

    public AppOpenManager(AppApplicationClass myApplication2, OnAppOpenClose onAppOpenClose2) {
        this.onAppOpenClose = onAppOpenClose2;
        this.myApplication = myApplication2;
        myApplication2.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        this.myids1 = 1;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        showAdIfAvailable();
        Log.d(LOG_TAG, "onStart");
    }

    public void DestroyAllAds() {
        if (SplashActivity.isAdmob) {
            this.adsLoadnow = false;
            this.myApplication.unregisterActivityLifecycleCallbacks(this);
            ProcessLifecycleOwner.get().getLifecycle().removeObserver(this);
            this.appOpenAd = null;
            isShowingAd = false;
            this.currentActivity = null;
        }
    }

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public boolean isAdAvailable() {
        return this.appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
    }

    private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
        return new Date().getTime() - this.loadTime < 3600000 * numHours;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
        this.currentActivity = activity;
    }

    public void onActivityResumed(Activity activity) {
        this.currentActivity = activity;
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
        this.currentActivity = null;
    }

    public void fetchAd() {
        if (!isAdAvailable()) {
            this.loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
                public void onAdLoaded(AppOpenAd ad) {
                    AppOpenAd unused = AppOpenManager.this.appOpenAd = ad;
                    long unused2 = AppOpenManager.this.loadTime = new Date().getTime();
                    if (AppOpenManager.this.myids1 == 1) {
                        AppOpenManager.this.myids1 = 0;
                        AppOpenManager.this.showAdIfAvailable();
                    }
                }

                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    AppOpenManager.this.onAppOpenClose.OnAppOpenFailToLoad();
                }
            };
            AdRequest request = getAdRequest();
            if (this.adsLoadnow) {
                AppOpenAd.load((Context) this.myApplication, AD_UNIT_ID, request, 1, this.loadCallback);
            }
        }
    }

    public void showAdIfAvailable() {
        if (isShowingAd || !isAdAvailable()) {
            Log.d(LOG_TAG, "Can not show ad.");
            fetchAd();
            return;
        }
        Log.d(LOG_TAG, "Will show ad.");
        this.appOpenAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            public void onAdDismissedFullScreenContent() {
                AppOpenAd unused = AppOpenManager.this.appOpenAd = null;
                boolean unused2 = AppOpenManager.isShowingAd = false;
                AppOpenManager.this.fetchAd();
                AppOpenManager.this.onAppOpenClose.OnAppOpenClose();
            }

            public void onAdFailedToShowFullScreenContent(AdError adError) {
            }

            public void onAdShowedFullScreenContent() {
                boolean unused = AppOpenManager.isShowingAd = true;
            }
        });
        this.appOpenAd.show(this.currentActivity);
    }
}
