package com.adsfb.testadxvideo.XAds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;

import com.adsfb.testadxvideo.R;
import com.adsfb.testadxvideo.SplashActivity;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdsImplements {
    static String FbBLoaded = null;
    static String FbNBannerLoaded = null;
    static String FbNBannerLoaded01 = null;
    static String FbNLoaded = null;
    static String FbNLoaded01 = null;
    static String TAG = "FbNative";
    static NativeAdView adView;
    static NativeAdView adView01;
    static AdView adView12;
    static NativeAdView adViewbanner;
    static NativeAdView adViewbanner01;
    static String admobBLoaded = null;
    static String admobNBannerLoaded = null;
    static String admobNBannerLoaded01 = null;
    static String admobNLoaded = null;
    static String admobNLoaded01 = null;
    static CountDownTimer cTimer = null;
    static com.facebook.ads.AdView fbAdView;
    static boolean idtimer = true;
    static InterstitialAd interstitialAd;
    static boolean isFirstBannerNative = true;
    static boolean isFirstNative = true;
    static com.google.android.gms.ads.interstitial.InterstitialAd mInterstitialAd;
    static NativeAd nativeAd;
    static NativeAd nativeAd01;
    static com.facebook.ads.NativeAd nativeAdFb = null;
    static com.facebook.ads.NativeAd nativeAdFb01 = null;
    static com.facebook.ads.NativeAd nativeAdFbBanner = null;
    static com.facebook.ads.NativeAd nativeAdFbBanner01 = null;
    static NativeAdLayout nativeAdLayout;
    static NativeAdLayout nativeAdLayout01;
    static NativeAd nativeAdbanner;
    static NativeAd nativeAdbanner01;
    static NativeAdLayout nativeBannerAdLayout;
    static NativeAdLayout nativeBannerAdLayout01;
    static OnAdListner onAdListner;
    public static int totaltime = DefaultControlDispatcher.DEFAULT_FAST_FORWARD_MS;

    public interface OnAdListner {
        void OnClose();
    }

    public static void startTimer() {
        idtimer = false;
        CountDownTimer r0 = new CountDownTimer((long) totaltime, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                AdsImplements.idtimer = true;
            }
        };
        cTimer = r0;
        r0.start();
    }

    public static void BanerLoad(Activity activity, RelativeLayout Adlayout) {
        com.facebook.ads.AdView adView2 = fbAdView;
        if (!(adView2 == null || adView2.getParent() == null)) {
            ((ViewGroup) fbAdView.getParent()).removeView(fbAdView);
        }
        AdView adView3 = adView12;
        if (!(adView3 == null || adView3.getParent() == null)) {
            ((ViewGroup) adView12.getParent()).removeView(adView12);
        }
        if (SplashActivity.AdsFlag.matches(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS) && SplashActivity.isAdmob) {
            String str = admobBLoaded;
            if (str == null) {
                String str2 = FbBLoaded;
                if (str2 == null) {
                    LoadQurekaBanner(activity, Adlayout);
                } else if (!str2.matches("true")) {
                    LoadFbBanner();
                    LoadQurekaBanner(activity, Adlayout);
                } else if (SplashActivity.isFacebook) {
                    Adlayout.addView(fbAdView);
                } else {
                    LoadQurekaBanner(activity, Adlayout);
                }
            } else if (str.matches("true")) {
                Adlayout.addView(adView12);
            } else {
                String str3 = FbBLoaded;
                if (str3 == null) {
                    LoadAdBanner();
                    LoadQurekaBanner(activity, Adlayout);
                } else if (!str3.matches("true")) {
                    LoadAdBanner();
                    LoadFbBanner();
                    LoadQurekaBanner(activity, Adlayout);
                } else if (SplashActivity.isFacebook) {
                    LoadAdBanner();
                    Adlayout.addView(fbAdView);
                } else {
                    LoadAdBanner();
                    LoadQurekaBanner(activity, Adlayout);
                }
            }
        } else if (SplashActivity.isFacebook) {
            String str4 = FbBLoaded;
            if (str4 == null) {
                String str5 = admobBLoaded;
                if (str5 == null) {
                    LoadQurekaBanner(activity, Adlayout);
                } else if (!str5.matches("true")) {
                    LoadAdBanner();
                    LoadQurekaBanner(activity, Adlayout);
                } else if (SplashActivity.isAdmob) {
                    Adlayout.addView(adView12);
                } else {
                    LoadQurekaBanner(activity, Adlayout);
                }
            } else if (str4.matches("true")) {
                Adlayout.addView(fbAdView);
            } else {
                String str6 = admobBLoaded;
                if (str6 == null) {
                    LoadFbBanner();
                    LoadQurekaBanner(activity, Adlayout);
                } else if (!str6.matches("true")) {
                    LoadAdBanner();
                    LoadFbBanner();
                    LoadQurekaBanner(activity, Adlayout);
                } else if (SplashActivity.isAdmob) {
                    LoadFbBanner();
                    Adlayout.addView(adView12);
                } else {
                    LoadFbBanner();
                    LoadQurekaBanner(activity, Adlayout);
                }
            }
        }
    }

    public static void LoadAdBanner() {
        AdView adView2 = new AdView(AppApplicationClass.getInstance());
        adView12 = adView2;
        adView2.setAdSize(AdSize.SMART_BANNER);
        adView12.setAdUnitId(SplashActivity.adBanner);
        adView12.loadAd(new AdRequest.Builder().build());
        adView12.setAdListener(new AdListener() {
            public void onAdLoaded() {
                Log.w("Banner", "Load Admob Banner");
                AdsImplements.admobBLoaded = "true";
            }

            public void onAdFailedToLoad(LoadAdError adError) {
                Log.e("ADMOB", "onAdFailedToLoad:" + adError.toString());
                AdsImplements.admobBLoaded = "false";
            }

            public void onAdOpened() {
            }

            public void onAdClicked() {
            }

            public void onAdClosed() {
            }
        });
    }

    public static void LoadFbBanner() {
        fbAdView = new com.facebook.ads.AdView((Context) AppApplicationClass.getInstance(), SplashActivity.fbBanner, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        com.facebook.ads.AdListener adListener = new com.facebook.ads.AdListener() {
            public void onError(Ad ad, AdError adError) {
                Log.e("FBADMOB", "onAdFailedToLoad:" + adError.getErrorMessage());
                AdsImplements.FbBLoaded = "false";
            }

            public void onAdLoaded(Ad ad) {
                Log.w("FbBanner", "Load Admob Banner");
                AdsImplements.FbBLoaded = "true";
            }

            public void onAdClicked(Ad ad) {
            }

            public void onLoggingImpression(Ad ad) {
            }
        };
        com.facebook.ads.AdView adView2 = fbAdView;
        adView2.loadAd(adView2.buildLoadAdConfig().withAdListener(adListener).build());
    }

    public static void InterLoadTimer(Activity activity, OnAdListner onAdListner1) {
        onAdListner = onAdListner1;
        if (idtimer) {
            InterLoad(activity, onAdListner1);
        } else {
            onAdListner1.OnClose();
        }
    }

    public static void InterLoad(Activity activity, OnAdListner onAdListner1) {
        onAdListner = onAdListner1;
        SplashActivity.AdsCOUNTER++;
        if (SplashActivity.AdsCOUNTER % SplashActivity.AdsMAX != 0) {
            onAdListner.OnClose();
        } else if (SplashActivity.AdsFlag.matches(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)) {
            com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd2 = mInterstitialAd;
            if (interstitialAd2 != null) {
                interstitialAd2.show(activity);
                return;
            }
            InterstitialAd interstitialAd3 = interstitialAd;
            if (interstitialAd3 != null) {
                if (interstitialAd3.isAdLoaded()) {
                    LoadAdInter();
                    interstitialAd.show();
                } else if (SplashActivity.isQureka) {
                    LoadAdInter();
                    LoadFbInter();
                    activity.startActivityForResult(new Intent(activity, QurekaInterActivity.class), 523);
                } else {
                    LoadAdInter();
                    LoadFbInter();
                    onAdListner.OnClose();
                }
            } else if (SplashActivity.isQureka) {
                LoadAdInter();
                LoadFbInter();
                activity.startActivityForResult(new Intent(activity, QurekaInterActivity.class), 523);
            } else {
                LoadAdInter();
                LoadFbInter();
                onAdListner.OnClose();
            }
        } else {
            InterstitialAd interstitialAd4 = interstitialAd;
            if (interstitialAd4 != null) {
                if (interstitialAd4.isAdLoaded()) {
                    interstitialAd.show();
                } else if (mInterstitialAd != null) {
                    LoadFbInter();
                    mInterstitialAd.show(activity);
                } else if (SplashActivity.isQureka) {
                    LoadAdInter();
                    LoadFbInter();
                    activity.startActivityForResult(new Intent(activity, QurekaInterActivity.class), 523);
                } else {
                    LoadAdInter();
                    LoadFbInter();
                    onAdListner.OnClose();
                }
            } else if (mInterstitialAd != null) {
                LoadFbInter();
                mInterstitialAd.show(activity);
            } else if (SplashActivity.isQureka) {
                LoadAdInter();
                LoadFbInter();
                activity.startActivityForResult(new Intent(activity, QurekaInterActivity.class), 523);
            } else {
                LoadAdInter();
                LoadFbInter();
                onAdListner.OnClose();
            }
        }
    }

    public static void InterLoadWithoutCount(Activity activity, OnAdListner onAdListner1) {
        onAdListner = onAdListner1;
        if (SplashActivity.AdsFlag.matches(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)) {
            com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd2 = mInterstitialAd;
            if (interstitialAd2 != null) {
                interstitialAd2.show(activity);
                return;
            }
            InterstitialAd interstitialAd3 = interstitialAd;
            if (interstitialAd3 != null) {
                if (interstitialAd3.isAdLoaded()) {
                    LoadAdInter();
                    interstitialAd.show();
                } else if (SplashActivity.isQureka) {
                    LoadAdInter();
                    LoadFbInter();
                    activity.startActivityForResult(new Intent(activity, QurekaInterActivity.class), 523);
                } else {
                    LoadAdInter();
                    LoadFbInter();
                    onAdListner.OnClose();
                }
            } else if (SplashActivity.isQureka) {
                LoadAdInter();
                LoadFbInter();
                activity.startActivityForResult(new Intent(activity, QurekaInterActivity.class), 523);
            } else {
                LoadAdInter();
                LoadFbInter();
                onAdListner.OnClose();
            }
        } else {
            InterstitialAd interstitialAd4 = interstitialAd;
            if (interstitialAd4 != null) {
                if (interstitialAd4.isAdLoaded()) {
                    interstitialAd.show();
                } else if (mInterstitialAd != null) {
                    LoadFbInter();
                    mInterstitialAd.show(activity);
                } else if (SplashActivity.isQureka) {
                    LoadAdInter();
                    LoadFbInter();
                    activity.startActivityForResult(new Intent(activity, QurekaInterActivity.class), 523);
                } else {
                    LoadAdInter();
                    LoadFbInter();
                    onAdListner.OnClose();
                }
            } else if (mInterstitialAd != null) {
                LoadFbInter();
                mInterstitialAd.show(activity);
            } else if (SplashActivity.isQureka) {
                LoadAdInter();
                LoadFbInter();
                activity.startActivityForResult(new Intent(activity, QurekaInterActivity.class), 523);
            } else {
                LoadAdInter();
                LoadFbInter();
                onAdListner.OnClose();
            }
        }
    }

    public void onInterstitialReady() {
    }

    public static void LoadAdInter() {
        if (SplashActivity.isAdmob) {
            com.google.android.gms.ads.interstitial.InterstitialAd.load(AppApplicationClass.getInstance(), SplashActivity.adInter, new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {
                public void onAdLoaded(com.google.android.gms.ads.interstitial.InterstitialAd interstitialAd) {
                    AdsImplements.mInterstitialAd = interstitialAd;
                    Log.i(AdsImplements.TAG, "onAdLoaded");
                    interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        public void onAdDismissedFullScreenContent() {
                            if (AdsImplements.cTimer != null) {
                                AdsImplements.cTimer.cancel();
                            }
                            AdsImplements.startTimer();
                            AdsImplements.LoadAdInter();
                            AdsImplements.onAdListner.OnClose();
                            AdsImplements.mInterstitialAd = null;
                            Log.d("TAG", "The ad was dismissed.");
                        }

                        public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                            AdsImplements.mInterstitialAd = null;
                            if (AdsImplements.cTimer != null) {
                                AdsImplements.cTimer.cancel();
                            }
                            AdsImplements.startTimer();
                            AdsImplements.LoadAdInter();
                            AdsImplements.onAdListner.OnClose();
                            Log.d("TAG", "The ad failed to show.");
                        }

                        public void onAdShowedFullScreenContent() {
                            Log.d("TAG", "The ad was shown.");
                        }
                    });
                }

                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    Log.i(AdsImplements.TAG, loadAdError.getMessage());
                    AdsImplements.mInterstitialAd = null;
                }
            });
        }
    }

    public static void LoadFbInter() {
        if (SplashActivity.isFacebook) {
            interstitialAd = new InterstitialAd(AppApplicationClass.getInstance(), SplashActivity.fbInter);
            InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                public void onInterstitialDisplayed(Ad ad) {
                    Log.e("FbInter", "Interstitial ad displayed.");
                }

                public void onInterstitialDismissed(Ad ad) {
                    Log.e("FbInter", "Interstitial ad dismissed.");
                    if (AdsImplements.cTimer != null) {
                        AdsImplements.cTimer.cancel();
                    }
                    AdsImplements.startTimer();
                    AdsImplements.LoadFbInter();
                    AdsImplements.onAdListner.OnClose();
                }

                public void onError(Ad ad, AdError adError) {
                    Log.e("FbInter", "Interstitial ad failed to load: " + adError.getErrorMessage());
                }

                public void onAdLoaded(Ad ad) {
                    Log.d("FbInter", "Interstitial ad is loaded and ready to be displayed!");
                }

                public void onAdClicked(Ad ad) {
                    Log.d("FbInter", "Interstitial ad clicked!");
                }

                public void onLoggingImpression(Ad ad) {
                    Log.d("FbInter", "Interstitial ad impression logged!");
                }
            };
            InterstitialAd interstitialAd2 = interstitialAd;
            interstitialAd2.loadAd(interstitialAd2.buildLoadAdConfig().withAdListener(interstitialAdListener).build());
        }
    }

    public static void NativeFinalLoad(Activity activity, FrameLayout Adlayout) {
        Adlayout.removeAllViews();
        if (isFirstNative) {
            NativeLoad(activity, Adlayout);
            isFirstNative = false;
            return;
        }
        NativeLoad01(activity, Adlayout);
        isFirstNative = true;
    }

    public static void NativeFinalBannerLoad(Activity activity, FrameLayout Adlayout) {
        Adlayout.removeAllViews();
        if (isFirstBannerNative) {
            NativeBannerLoad(activity, Adlayout);
            isFirstBannerNative = false;
            return;
        }
        NativeBannerLoad01(activity, Adlayout);
        isFirstBannerNative = true;
    }

    public static void NativeLoad(Activity activity, FrameLayout Adlayout) {
        NativeAd nativeAd2 = nativeAd01;
        if (nativeAd2 != null) {
            nativeAd2.destroy();
            LoadAdNative01();
        }
        com.facebook.ads.NativeAd nativeAd3 = nativeAdFb01;
        if (nativeAd3 != null) {
            nativeAd3.destroy();
            LoadFbNative01();
        }
        NativeAdLayout nativeAdLayout2 = nativeAdLayout;
        if (!(nativeAdLayout2 == null || nativeAdLayout2.getParent() == null)) {
            ((ViewGroup) nativeAdLayout.getParent()).removeView(nativeAdLayout);
        }
        NativeAdView nativeAdView = adView;
        if (!(nativeAdView == null || nativeAdView.getParent() == null)) {
            ((ViewGroup) adView.getParent()).removeView(adView);
        }
        if (SplashActivity.AdsFlag.matches(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS) && SplashActivity.isAdmob) {
            String str = admobNLoaded;
            if (str == null) {
                String str2 = FbNLoaded;
                if (str2 == null) {
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str2.matches("true")) {
                    LoadFbNative();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isFacebook) {
                    Adlayout.addView(nativeAdLayout);
                } else {
                    LoadQrekaNative(activity, Adlayout);
                }
            } else if (str.matches("true")) {
                Adlayout.addView(adView);
            } else {
                String str3 = FbNLoaded;
                if (str3 == null) {
                    LoadAdNative();
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str3.matches("true")) {
                    LoadAdNative();
                    LoadFbNative();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isFacebook) {
                    LoadAdNative();
                    Adlayout.addView(nativeAdLayout);
                } else {
                    LoadAdNative();
                    LoadQrekaNative(activity, Adlayout);
                }
            }
        } else if (SplashActivity.isFacebook) {
            String str4 = FbNLoaded;
            if (str4 == null) {
                String str5 = admobNLoaded;
                if (str5 == null) {
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str5.matches("true")) {
                    LoadAdNative();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isAdmob) {
                    Adlayout.addView(adView);
                } else {
                    LoadQrekaNative(activity, Adlayout);
                }
            } else if (str4.matches("true")) {
                Adlayout.addView(nativeAdLayout);
            } else {
                String str6 = admobNLoaded;
                if (str6 == null) {
                    LoadFbNative();
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str6.matches("true")) {
                    LoadAdNative();
                    LoadFbNative();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isAdmob) {
                    LoadFbNative();
                    Adlayout.addView(adView);
                } else {
                    LoadFbNative();
                    LoadQrekaNative(activity, Adlayout);
                }
            }
        }
    }

    public static void NativeLoad01(Activity activity, FrameLayout Adlayout) {
        NativeAd nativeAd2 = nativeAd;
        if (nativeAd2 != null) {
            nativeAd2.destroy();
            LoadAdNative();
        }
        com.facebook.ads.NativeAd nativeAd3 = nativeAdFb;
        if (nativeAd3 != null) {
            nativeAd3.destroy();
            LoadFbNative();
        }
        NativeAdLayout nativeAdLayout2 = nativeAdLayout01;
        if (!(nativeAdLayout2 == null || nativeAdLayout2.getParent() == null)) {
            ((ViewGroup) nativeAdLayout01.getParent()).removeView(nativeAdLayout01);
        }
        NativeAdView nativeAdView = adView01;
        if (!(nativeAdView == null || nativeAdView.getParent() == null)) {
            ((ViewGroup) adView01.getParent()).removeView(adView01);
        }
        if (SplashActivity.AdsFlag.matches(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS) && SplashActivity.isAdmob) {
            String str = admobNLoaded01;
            if (str == null) {
                String str2 = FbNLoaded01;
                if (str2 == null) {
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str2.matches("true")) {
                    LoadFbNative01();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isFacebook) {
                    Adlayout.addView(nativeAdLayout01);
                } else {
                    LoadQrekaNative(activity, Adlayout);
                }
            } else if (str.matches("true")) {
                Adlayout.addView(adView01);
            } else {
                String str3 = FbNLoaded01;
                if (str3 == null) {
                    LoadAdNative01();
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str3.matches("true")) {
                    LoadAdNative01();
                    LoadFbNative01();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isFacebook) {
                    LoadAdNative01();
                    Adlayout.addView(nativeAdLayout01);
                } else {
                    LoadAdNative01();
                    LoadQrekaNative(activity, Adlayout);
                }
            }
        } else if (SplashActivity.isFacebook) {
            String str4 = FbNLoaded01;
            if (str4 == null) {
                String str5 = admobNLoaded01;
                if (str5 == null) {
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str5.matches("true")) {
                    LoadAdNative01();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isAdmob) {
                    Adlayout.addView(adView01);
                } else {
                    LoadQrekaNative(activity, Adlayout);
                }
            } else if (str4.matches("true")) {
                Adlayout.addView(nativeAdLayout01);
            } else {
                String str6 = admobNLoaded01;
                if (str6 == null) {
                    LoadFbNative01();
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str6.matches("true")) {
                    LoadAdNative01();
                    LoadFbNative01();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isAdmob) {
                    LoadFbNative01();
                    Adlayout.addView(adView01);
                } else {
                    LoadFbNative01();
                    LoadQrekaNative(activity, Adlayout);
                }
            }
        }
    }

    public static void NativeBannerLoad(Activity activity, FrameLayout Adlayout) {
        NativeAd nativeAd2 = nativeAdbanner01;
        if (nativeAd2 != null) {
            nativeAd2.destroy();
            LoadAdBannerNative01();
        }
        com.facebook.ads.NativeAd nativeAd3 = nativeAdFbBanner01;
        if (nativeAd3 != null) {
            nativeAd3.destroy();
            LoadFbBannerNative01();
        }
        NativeAdLayout nativeAdLayout2 = nativeBannerAdLayout;
        if (!(nativeAdLayout2 == null || nativeAdLayout2.getParent() == null)) {
            ((ViewGroup) nativeBannerAdLayout.getParent()).removeView(nativeBannerAdLayout);
        }
        NativeAdView nativeAdView = adViewbanner;
        if (!(nativeAdView == null || nativeAdView.getParent() == null)) {
            ((ViewGroup) adViewbanner.getParent()).removeView(adViewbanner);
        }
        if (SplashActivity.AdsFlag.matches(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS) && SplashActivity.isAdmob) {
            String str = admobNBannerLoaded;
            if (str == null) {
                String str2 = FbNBannerLoaded;
                if (str2 == null) {
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str2.matches("true")) {
                    LoadFbBannerNative();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isFacebook) {
                    Adlayout.addView(nativeBannerAdLayout);
                } else {
                    LoadQrekaNative(activity, Adlayout);
                }
            } else if (str.matches("true")) {
                Adlayout.addView(adViewbanner);
            } else {
                String str3 = FbNBannerLoaded;
                if (str3 == null) {
                    LoadAdBannerNative();
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str3.matches("true")) {
                    LoadAdBannerNative();
                    LoadFbBannerNative();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isFacebook) {
                    LoadAdBannerNative();
                    Adlayout.addView(nativeBannerAdLayout);
                } else {
                    LoadAdBannerNative();
                    LoadQrekaNative(activity, Adlayout);
                }
            }
        } else if (SplashActivity.isFacebook) {
            String str4 = FbNBannerLoaded;
            if (str4 == null) {
                String str5 = admobNBannerLoaded;
                if (str5 == null) {
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str5.matches("true")) {
                    LoadAdBannerNative();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isAdmob) {
                    Adlayout.addView(adViewbanner);
                } else {
                    LoadQrekaNative(activity, Adlayout);
                }
            } else if (str4.matches("true")) {
                Adlayout.addView(nativeBannerAdLayout);
            } else {
                String str6 = admobNBannerLoaded;
                if (str6 == null) {
                    LoadFbBannerNative();
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str6.matches("true")) {
                    LoadAdBannerNative();
                    LoadFbBannerNative();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isAdmob) {
                    LoadFbBannerNative();
                    Adlayout.addView(adViewbanner);
                } else {
                    LoadFbBannerNative();
                    LoadQrekaNative(activity, Adlayout);
                }
            }
        }
    }

    public static void NativeBannerLoad01(Activity activity, FrameLayout Adlayout) {
        NativeAd nativeAd2 = nativeAdbanner;
        if (nativeAd2 != null) {
            nativeAd2.destroy();
            LoadAdBannerNative();
        }
        com.facebook.ads.NativeAd nativeAd3 = nativeAdFbBanner;
        if (nativeAd3 != null) {
            nativeAd3.destroy();
            LoadFbBannerNative();
        }
        NativeAdLayout nativeAdLayout2 = nativeBannerAdLayout01;
        if (!(nativeAdLayout2 == null || nativeAdLayout2.getParent() == null)) {
            ((ViewGroup) nativeBannerAdLayout01.getParent()).removeView(nativeBannerAdLayout01);
        }
        NativeAdView nativeAdView = adViewbanner01;
        if (!(nativeAdView == null || nativeAdView.getParent() == null)) {
            ((ViewGroup) adViewbanner01.getParent()).removeView(adViewbanner01);
        }
        if (SplashActivity.AdsFlag.matches(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS) && SplashActivity.isAdmob) {
            String str = admobNBannerLoaded01;
            if (str == null) {
                String str2 = FbNBannerLoaded01;
                if (str2 == null) {
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str2.matches("true")) {
                    LoadFbBannerNative01();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isFacebook) {
                    Adlayout.addView(nativeBannerAdLayout01);
                } else {
                    LoadQrekaNative(activity, Adlayout);
                }
            } else if (str.matches("true")) {
                Adlayout.addView(adViewbanner01);
            } else {
                String str3 = FbNBannerLoaded01;
                if (str3 == null) {
                    LoadAdBannerNative01();
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str3.matches("true")) {
                    LoadAdBannerNative01();
                    LoadFbBannerNative01();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isFacebook) {
                    LoadAdBannerNative01();
                    Adlayout.addView(nativeBannerAdLayout01);
                } else {
                    LoadAdBannerNative01();
                    LoadQrekaNative(activity, Adlayout);
                }
            }
        } else if (SplashActivity.isFacebook) {
            String str4 = FbNBannerLoaded01;
            if (str4 == null) {
                String str5 = admobNBannerLoaded01;
                if (str5 == null) {
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str5.matches("true")) {
                    LoadAdBannerNative01();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isAdmob) {
                    Adlayout.addView(adViewbanner01);
                } else {
                    LoadQrekaNative(activity, Adlayout);
                }
            } else if (str4.matches("true")) {
                Adlayout.addView(nativeBannerAdLayout01);
            } else {
                String str6 = admobNBannerLoaded01;
                if (str6 == null) {
                    LoadFbBannerNative01();
                    LoadQrekaNative(activity, Adlayout);
                } else if (!str6.matches("true")) {
                    LoadAdBannerNative01();
                    LoadFbBannerNative01();
                    LoadQrekaNative(activity, Adlayout);
                } else if (SplashActivity.isAdmob) {
                    LoadFbBannerNative01();
                    Adlayout.addView(adViewbanner01);
                } else {
                    LoadFbBannerNative01();
                    LoadQrekaNative(activity, Adlayout);
                }
            }
        }
    }

    public static void LoadFbNative() {
        final com.facebook.ads.NativeAd nativeAd2 = new com.facebook.ads.NativeAd((Context) AppApplicationClass.getInstance(), SplashActivity.fbNative);
        nativeAd2.loadAd(nativeAd2.buildLoadAdConfig().withAdListener(new NativeAdListener() {
            public void onMediaDownloaded(Ad ad) {
                Log.e(AdsImplements.TAG, "Native ad finished downloading all assets.");
            }

            public void onError(Ad ad, AdError adError) {
                String str = AdsImplements.TAG;
                Log.e(str, "Native ad failed to load: " + adError.getErrorMessage());
                AdsImplements.FbNLoaded = "false";
            }

            public void onAdLoaded(Ad ad) {
                Log.d(AdsImplements.TAG, "Native ad is loaded and ready to be displayed!");
                if (nativeAd != null && nativeAd == ad) {
                    AdsImplements.FbNLoaded = "true";
                    AdsImplements.nativeAdFb = nativeAd2;
                    AdsImplements.inflateAd(nativeAd2);
                }
            }

            public void onAdClicked(Ad ad) {
                Log.d(AdsImplements.TAG, "Native ad clicked!");
            }

            public void onLoggingImpression(Ad ad) {
                Log.d(AdsImplements.TAG, "Native ad impression logged!");
            }
        }).build());
    }

    public static void LoadFbNative01() {
        final com.facebook.ads.NativeAd nativeAd2 = new com.facebook.ads.NativeAd((Context) AppApplicationClass.getInstance(), SplashActivity.fbNative);
        nativeAd2.loadAd(nativeAd2.buildLoadAdConfig().withAdListener(new NativeAdListener() {
            public void onMediaDownloaded(Ad ad) {
                Log.e(AdsImplements.TAG, "Native ad finished downloading all assets.");
            }

            public void onError(Ad ad, AdError adError) {
                String str = AdsImplements.TAG;
                Log.e(str, "Native ad failed to load: " + adError.getErrorMessage());
                AdsImplements.FbNLoaded01 = "false";
            }

            public void onAdLoaded(Ad ad) {
                Log.d(AdsImplements.TAG, "Native ad is loaded and ready to be displayed!");
                if (nativeAd != null && nativeAd == ad) {
                    AdsImplements.FbNLoaded01 = "true";
                    AdsImplements.nativeAdFb01 = nativeAd2;
                    AdsImplements.inflateAd01(nativeAd2);
                }
            }

            public void onAdClicked(Ad ad) {
                Log.d(AdsImplements.TAG, "Native ad clicked!");
            }

            public void onLoggingImpression(Ad ad) {
                Log.d(AdsImplements.TAG, "Native ad impression logged!");
            }
        }).build());
    }

    public static void LoadFbBannerNative() {
        final com.facebook.ads.NativeAd nativeAd2 = new com.facebook.ads.NativeAd((Context) AppApplicationClass.getInstance(), SplashActivity.fbNative);
        nativeAd2.loadAd(nativeAd2.buildLoadAdConfig().withAdListener(new NativeAdListener() {
            public void onMediaDownloaded(Ad ad) {
                Log.e(AdsImplements.TAG, "Native ad finished downloading all assets.");
            }

            public void onError(Ad ad, AdError adError) {
                String str = AdsImplements.TAG;
                Log.e(str, "Native ad failed to load: " + adError.getErrorMessage());
                AdsImplements.FbNBannerLoaded = "false";
            }

            public void onAdLoaded(Ad ad) {
                Log.d(AdsImplements.TAG, "Native ad is loaded and ready to be displayed!");


                if (nativeAd != null && nativeAd == ad) {
                    AdsImplements.FbNBannerLoaded = "true";
                    AdsImplements.nativeAdFbBanner = nativeAd2;
                    AdsImplements.inflateBannerAd(nativeAd2);
                }
            }

            public void onAdClicked(Ad ad) {
                Log.d(AdsImplements.TAG, "Native ad clicked!");
            }

            public void onLoggingImpression(Ad ad) {
                Log.d(AdsImplements.TAG, "Native ad impression logged!");
            }
        }).build());
    }

    public static void LoadFbBannerNative01() {
        final com.facebook.ads.NativeAd nativeAd2 = new com.facebook.ads.NativeAd((Context) AppApplicationClass.getInstance(), SplashActivity.fbNative);
        nativeAd2.loadAd(nativeAd2.buildLoadAdConfig().withAdListener(new NativeAdListener() {
            public void onMediaDownloaded(Ad ad) {
                Log.e(AdsImplements.TAG, "Native ad finished downloading all assets.");
            }

            public void onError(Ad ad, AdError adError) {
                String str = AdsImplements.TAG;
                Log.e(str, "Native ad failed to load: " + adError.getErrorMessage());
                AdsImplements.FbNBannerLoaded01 = "false";
            }

            public void onAdLoaded(Ad ad) {
                Log.d(AdsImplements.TAG, "Native ad is loaded and ready to be displayed!");

                if (nativeAd != null && nativeAd == ad) {
                    AdsImplements.FbNBannerLoaded01 = "true";
                    AdsImplements.nativeAdFbBanner01 = nativeAd2;
                    AdsImplements.inflateBannerAd01(nativeAd2);
                }
            }

            public void onAdClicked(Ad ad) {
                Log.d(AdsImplements.TAG, "Native ad clicked!");
            }

            public void onLoggingImpression(Ad ad) {
                Log.d(AdsImplements.TAG, "Native ad impression logged!");
            }
        }).build());
    }

    /* access modifiers changed from: private */
    public static void inflateAd(com.facebook.ads.NativeAd nativeAd2) {
        nativeAd2.unregisterView();
        nativeAdLayout = new NativeAdLayout(AppApplicationClass.getInstance());
        int i = 0;
        LinearLayout adView2 = (LinearLayout) LayoutInflater.from(AppApplicationClass.getInstance()).inflate(R.layout.ad_fb_native_container, nativeAdLayout, false);
        nativeAdLayout.addView(adView2);
        LinearLayout adChoicesContainer = (LinearLayout) adView2.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(AppApplicationClass.getInstance(), nativeAd2, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);
        MediaView nativeAdIcon = (MediaView) adView2.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = (TextView) adView2.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = (MediaView) adView2.findViewById(R.id.native_ad_media);
        TextView sponsoredLabel = (TextView) adView2.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = (Button) adView2.findViewById(R.id.native_ad_call_to_action);
        nativeAdTitle.setText(nativeAd2.getAdvertiserName());
        ((TextView) adView2.findViewById(R.id.native_ad_body)).setText(nativeAd2.getAdBodyText());
        ((TextView) adView2.findViewById(R.id.native_ad_social_context)).setText(nativeAd2.getAdSocialContext());
        if (!nativeAd2.hasCallToAction()) {
            i = 4;
        }
        nativeAdCallToAction.setVisibility(i);
        nativeAdCallToAction.setText(nativeAd2.getAdCallToAction());
        sponsoredLabel.setText(nativeAd2.getSponsoredTranslation());
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeAd2.registerViewForInteraction((View) adView2, nativeAdMedia, nativeAdIcon, clickableViews);
    }

    /* access modifiers changed from: private */
    public static void inflateAd01(com.facebook.ads.NativeAd nativeAd2) {
        nativeAd2.unregisterView();
        nativeAdLayout01 = new NativeAdLayout(AppApplicationClass.getInstance());
        int i = 0;
        LinearLayout adView2 = (LinearLayout) LayoutInflater.from(AppApplicationClass.getInstance()).inflate(R.layout.ad_fb_native_container, nativeAdLayout01, false);
        nativeAdLayout01.addView(adView2);
        LinearLayout adChoicesContainer = (LinearLayout) adView2.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(AppApplicationClass.getInstance(), nativeAd2, nativeAdLayout01);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);
        MediaView nativeAdIcon = (MediaView) adView2.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = (TextView) adView2.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = (MediaView) adView2.findViewById(R.id.native_ad_media);
        TextView sponsoredLabel = (TextView) adView2.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = (Button) adView2.findViewById(R.id.native_ad_call_to_action);
        nativeAdTitle.setText(nativeAd2.getAdvertiserName());
        ((TextView) adView2.findViewById(R.id.native_ad_body)).setText(nativeAd2.getAdBodyText());
        ((TextView) adView2.findViewById(R.id.native_ad_social_context)).setText(nativeAd2.getAdSocialContext());
        if (!nativeAd2.hasCallToAction()) {
            i = 4;
        }
        nativeAdCallToAction.setVisibility(i);
        nativeAdCallToAction.setText(nativeAd2.getAdCallToAction());
        sponsoredLabel.setText(nativeAd2.getSponsoredTranslation());
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeAd2.registerViewForInteraction((View) adView2, nativeAdMedia, nativeAdIcon, clickableViews);
    }

    /* access modifiers changed from: private */
    public static void inflateBannerAd(com.facebook.ads.NativeAd nativeAd2) {
        nativeAd2.unregisterView();
        nativeBannerAdLayout = new NativeAdLayout(AppApplicationClass.getInstance());
        int i = 0;
        LinearLayout adView2 = (LinearLayout) LayoutInflater.from(AppApplicationClass.getInstance()).inflate(R.layout.ad_fb_banner_native, nativeBannerAdLayout, false);
        nativeBannerAdLayout.addView(adView2);
        LinearLayout adChoicesContainer = (LinearLayout) adView2.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(AppApplicationClass.getInstance(), nativeAd2, nativeBannerAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);
        MediaView nativeAdIcon = (MediaView) adView2.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = (TextView) adView2.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = (MediaView) adView2.findViewById(R.id.native_ad_media);
        TextView sponsoredLabel = (TextView) adView2.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = (Button) adView2.findViewById(R.id.native_ad_call_to_action);
        nativeAdTitle.setText(nativeAd2.getAdvertiserName());
        ((TextView) adView2.findViewById(R.id.native_ad_body)).setText(nativeAd2.getAdBodyText());
        ((TextView) adView2.findViewById(R.id.native_ad_social_context)).setText(nativeAd2.getAdSocialContext());
        if (!nativeAd2.hasCallToAction()) {
            i = 4;
        }
        nativeAdCallToAction.setVisibility(i);
        nativeAdCallToAction.setText(nativeAd2.getAdCallToAction());
        sponsoredLabel.setText(nativeAd2.getSponsoredTranslation());
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeAd2.registerViewForInteraction((View) adView2, nativeAdMedia, nativeAdIcon, clickableViews);
    }

    /* access modifiers changed from: private */
    public static void inflateBannerAd01(com.facebook.ads.NativeAd nativeAd2) {
        nativeAd2.unregisterView();
        nativeBannerAdLayout01 = new NativeAdLayout(AppApplicationClass.getInstance());
        int i = 0;
        LinearLayout adView2 = (LinearLayout) LayoutInflater.from(AppApplicationClass.getInstance()).inflate(R.layout.ad_fb_banner_native, nativeBannerAdLayout01, false);
        nativeBannerAdLayout01.addView(adView2);
        LinearLayout adChoicesContainer = (LinearLayout) adView2.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(AppApplicationClass.getInstance(), nativeAd2, nativeBannerAdLayout01);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);
        MediaView nativeAdIcon = (MediaView) adView2.findViewById(R.id.native_ad_icon);
        TextView nativeAdTitle = (TextView) adView2.findViewById(R.id.native_ad_title);
        MediaView nativeAdMedia = (MediaView) adView2.findViewById(R.id.native_ad_media);
        TextView sponsoredLabel = (TextView) adView2.findViewById(R.id.native_ad_sponsored_label);
        Button nativeAdCallToAction = (Button) adView2.findViewById(R.id.native_ad_call_to_action);
        nativeAdTitle.setText(nativeAd2.getAdvertiserName());
        ((TextView) adView2.findViewById(R.id.native_ad_body)).setText(nativeAd2.getAdBodyText());
        ((TextView) adView2.findViewById(R.id.native_ad_social_context)).setText(nativeAd2.getAdSocialContext());
        if (!nativeAd2.hasCallToAction()) {
            i = 4;
        }
        nativeAdCallToAction.setVisibility(i);
        nativeAdCallToAction.setText(nativeAd2.getAdCallToAction());
        sponsoredLabel.setText(nativeAd2.getSponsoredTranslation());
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeAd2.registerViewForInteraction((View) adView2, nativeAdMedia, nativeAdIcon, clickableViews);
    }

    public static void LoadAdNative() {
        AdLoader.Builder builder = new AdLoader.Builder(AppApplicationClass.getInstance(), SplashActivity.adNative);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(NativeAd unifiedNativeAd) {
                if (AdsImplements.nativeAd != null) {
                    AdsImplements.nativeAd.destroy();
                }
                AdsImplements.nativeAd = unifiedNativeAd;
                AdsImplements.adView = (NativeAdView) LayoutInflater.from(AppApplicationClass.getContext()).inflate(R.layout.ads_nativ_admob, (ViewGroup) null);
                AdsImplements.populateUnifiedNativeAdView(unifiedNativeAd, AdsImplements.adView);
                AdsImplements.admobNLoaded = "true";
            }
        });
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                AdsImplements.admobNLoaded = "false";
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    public static void LoadAdNative01() {
        AdLoader.Builder builder = new AdLoader.Builder(AppApplicationClass.getInstance(), SplashActivity.adNative);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(NativeAd unifiedNativeAd) {
                if (AdsImplements.nativeAd01 != null) {
                    AdsImplements.nativeAd01.destroy();
                }
                AdsImplements.nativeAd01 = unifiedNativeAd;
                AdsImplements.adView01 = (NativeAdView) LayoutInflater.from(AppApplicationClass.getContext()).inflate(R.layout.ads_nativ_admob, (ViewGroup) null);
                AdsImplements.populateUnifiedNativeAdView(unifiedNativeAd, AdsImplements.adView01);
                AdsImplements.admobNLoaded01 = "true";
            }
        });
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                AdsImplements.admobNLoaded01 = "false";
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    public static void LoadAdBannerNative() {
        AdLoader.Builder builder = new AdLoader.Builder(AppApplicationClass.getInstance(), SplashActivity.adNative);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(NativeAd unifiedNativeAd) {
                if (AdsImplements.nativeAdbanner != null) {
                    AdsImplements.nativeAdbanner.destroy();
                }
                AdsImplements.nativeAdbanner = unifiedNativeAd;
                AdsImplements.adViewbanner = (NativeAdView) LayoutInflater.from(AppApplicationClass.getContext()).inflate(R.layout.ads_nativ_banner_admob, (ViewGroup) null);
                AdsImplements.populateUnifiedNativeAdView(unifiedNativeAd, AdsImplements.adViewbanner);
                AdsImplements.admobNBannerLoaded = "true";
            }
        });
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                AdsImplements.admobNBannerLoaded = "false";
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    public static void LoadAdBannerNative01() {
        AdLoader.Builder builder = new AdLoader.Builder(AppApplicationClass.getInstance(), SplashActivity.adNative);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            public void onNativeAdLoaded(NativeAd unifiedNativeAd) {
                if (AdsImplements.nativeAdbanner01 != null) {
                    AdsImplements.nativeAdbanner01.destroy();
                }
                AdsImplements.nativeAdbanner01 = unifiedNativeAd;
                AdsImplements.adViewbanner01 = (NativeAdView) LayoutInflater.from(AppApplicationClass.getContext()).inflate(R.layout.ads_nativ_banner_admob, (ViewGroup) null);
                AdsImplements.populateUnifiedNativeAdView(unifiedNativeAd, AdsImplements.adViewbanner01);
                AdsImplements.admobNBannerLoaded01 = "true";
            }
        });
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                AdsImplements.admobNBannerLoaded01 = "false";
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    public static void setUpFadeAnimation(final TextView textView) {
        final Animation fadeIn = new AlphaAnimation(0.2f, 1.0f);
        fadeIn.setDuration(700);
        fadeIn.setStartOffset(100);
        final Animation fadeOut = new AlphaAnimation(1.0f, 0.2f);
        fadeOut.setDuration(700);
        fadeOut.setStartOffset(100);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation arg0) {
                textView.startAnimation(fadeOut);
            }

            public void onAnimationRepeat(Animation arg0) {
            }

            public void onAnimationStart(Animation arg0) {
            }
        });
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation arg0) {
                textView.startAnimation(fadeIn);
            }

            public void onAnimationRepeat(Animation arg0) {
            }

            public void onAnimationStart(Animation arg0) {
            }
        });
        textView.startAnimation(fadeOut);
    }

    public static void LoadQrekaNative(final Activity activity, FrameLayout dialog) {
        int drawablebanner;
        if (SplashActivity.isQureka) {
            int random = new Random().nextInt(5) + 1;
            if (random == 1) {
                drawablebanner = R.drawable.qnative1;
            } else if (random == 2) {
                drawablebanner = R.drawable.qnative2;
            } else if (random == 3) {
                drawablebanner = R.drawable.qnative3;
            } else if (random == 4) {
                drawablebanner = R.drawable.qnative4;
            } else {
                drawablebanner = R.drawable.qnative5;
            }
            ImageView imageView = new ImageView(activity);
            imageView.setImageResource(drawablebanner);
            imageView.setAdjustViewBounds(true);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AdsImplements.stratQureka(activity);
                }
            });
            dialog.addView(imageView);
        }
    }

    public static void stratQureka(Activity activity) {
        Uri uri = Uri.parse(SplashActivity.qurekaLink);
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setDefaultColorSchemeParams(new CustomTabColorSchemeParams.Builder().setNavigationBarColor(ContextCompat.getColor(activity, R.color.black)).setToolbarColor(ContextCompat.getColor(activity, R.color.white)).setSecondaryToolbarColor(ContextCompat.getColor(activity, R.color.white)).build());
        intentBuilder.build().launchUrl(activity, uri);
    }

    public static void LoadQurekaBanner(final Activity activity, RelativeLayout Adlayout) {
        int drawablebanner;
        if (SplashActivity.isQureka) {
            int random = new Random().nextInt(5) + 1;
            if (random == 1) {
                drawablebanner = R.drawable.qrek1;
            } else if (random == 2) {
                drawablebanner = R.drawable.qrek2;
            } else if (random == 3) {
                drawablebanner = R.drawable.qrek3;
            } else if (random == 4) {
                drawablebanner = R.drawable.qrek4;
            } else {
                drawablebanner = R.drawable.qrek5;
            }
            ImageView imageView = new ImageView(activity);
            imageView.setImageResource(drawablebanner);
            imageView.setAdjustViewBounds(true);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AdsImplements.stratQureka(activity);
                }
            });
            Adlayout.addView(imageView);
        }
    }

    /* access modifiers changed from: private */
    public static void populateUnifiedNativeAdView(NativeAd nativeAd2, NativeAdView adView2) {
        adView2.setMediaView((com.google.android.gms.ads.nativead.MediaView) adView2.findViewById(R.id.ad_media));
        adView2.setHeadlineView(adView2.findViewById(R.id.ad_headline));
        adView2.setBodyView(adView2.findViewById(R.id.ad_body));
        adView2.setCallToActionView(adView2.findViewById(R.id.ad_call_to_action));
        setUpFadeAnimation((TextView) adView2.findViewById(R.id.ad_call_to_action));
        adView2.setIconView(adView2.findViewById(R.id.ad_app_icon));
        adView2.setPriceView(adView2.findViewById(R.id.ad_price));
        adView2.setStarRatingView(adView2.findViewById(R.id.ad_stars));
        adView2.setStoreView(adView2.findViewById(R.id.ad_store));
        adView2.setAdvertiserView(adView2.findViewById(R.id.ad_advertiser));
        ((TextView) adView2.getHeadlineView()).setText(nativeAd2.getHeadline());
        adView2.getMediaView().setMediaContent(nativeAd2.getMediaContent());
        if (nativeAd2.getBody() == null) {
            adView2.getBodyView().setVisibility(4);
        } else {
            adView2.getBodyView().setVisibility(0);
            ((TextView) adView2.getBodyView()).setText(nativeAd2.getBody());
        }
        if (nativeAd2.getCallToAction() == null) {
            adView2.getCallToActionView().setVisibility(4);
        } else {
            adView2.getCallToActionView().setVisibility(0);
            ((TextView) adView2.getCallToActionView()).setText(nativeAd2.getCallToAction());
        }
        if (nativeAd2.getIcon() == null) {
            adView2.getIconView().setVisibility(8);
        } else {
            ((ImageView) adView2.getIconView()).setImageDrawable(nativeAd2.getIcon().getDrawable());
            adView2.getIconView().setVisibility(0);
        }
        if (nativeAd2.getPrice() == null) {
            adView2.getPriceView().setVisibility(4);
        } else {
            adView2.getPriceView().setVisibility(0);
            ((TextView) adView2.getPriceView()).setText(nativeAd2.getPrice());
        }
        if (nativeAd2.getStore() == null) {
            adView2.getStoreView().setVisibility(4);
        } else {
            adView2.getStoreView().setVisibility(0);
            ((TextView) adView2.getStoreView()).setText(nativeAd2.getStore());
        }
        if (nativeAd2.getStarRating() == null) {
            adView2.getStarRatingView().setVisibility(4);
        } else {
            ((RatingBar) adView2.getStarRatingView()).setRating(nativeAd2.getStarRating().floatValue());
            adView2.getStarRatingView().setVisibility(0);
        }
        if (nativeAd2.getAdvertiser() == null) {
            adView2.getAdvertiserView().setVisibility(4);
        } else {
            ((TextView) adView2.getAdvertiserView()).setText(nativeAd2.getAdvertiser());
            adView2.getAdvertiserView().setVisibility(0);
        }
        adView2.setNativeAd(nativeAd2);
    }
}
