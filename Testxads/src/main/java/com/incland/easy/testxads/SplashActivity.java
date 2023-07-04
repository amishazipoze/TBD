package com.incland.easy.testxads;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity implements InstallReferrerStateListener {
    public static int AdsCOUNTER = 0;
    public static String AdsFlag;
    public static int AdsMAX = 0;
    public static String adBanner;
    public static String adInter;
    public static String adNative;
    public static String adOpenAd;
    public static String adReward;
    public static int adsTimer;
    public static AppOpenManager appOpenManager;
    public static String fbBanner;
    public static String fbInter;
    public static String fbNative;
    public static boolean isAdShow = true;
    public static boolean isAdmob;
    public static boolean isFacebook;
    public static boolean isQureka;
    public static String mReferral = "NA";
    public static boolean directQureka=false;
    public static String qurekaLink;
    int adshowed = 0;
    Dialog dialog1;
    Button download;
    private InstallReferrerClient mReferrerClient;
    OnAppOpenClose onAppOpenClose;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_splash);
        InstallReferrerClient build = InstallReferrerClient.newBuilder(this).build();
        this.mReferrerClient = build;
        build.startConnection(this);
        LoginAPp();
    }

    private void LoginAPp() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        Volley.newRequestQueue(this).add(new StringRequest(0, "http://159.89.162.204/Test/test.php", new Response.Listener<String>() {
            public void onResponse(String response) {
                try {
                    Log.e("response",response+"");
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONArray(response).getJSONObject(0);
                    SplashActivity.AdsFlag = jsonObject.getString("AdsFlag");
                    SplashActivity.adsTimer = jsonObject.getInt("adsTimer");
                    AdsImplements.totaltime = SplashActivity.adsTimer * 1000;
                    SplashActivity.AdsMAX = jsonObject.getInt("adsCount");
                    SplashActivity.isAdmob = jsonObject.getBoolean("isAdmob");
                    SplashActivity.isFacebook = jsonObject.getBoolean("isFacebook");
                    SplashActivity.isQureka = jsonObject.getBoolean("isQureka");
                    SplashActivity.qurekaLink = jsonObject.getString("qurekaLink");
                    SplashActivity.adBanner = jsonObject.getString("adBanner");
                    SplashActivity.adInter = jsonObject.getString("adInter");
                    SplashActivity.adNative = jsonObject.getString("adNative");
                    SplashActivity.adReward = jsonObject.getString("adReward");
                    SplashActivity.adOpenAd = jsonObject.getString("adOpenAd");
                    SplashActivity.fbBanner = jsonObject.getString("fbBanner");
                    SplashActivity.fbNative = jsonObject.getString("fbNative");
                    SplashActivity.fbInter = jsonObject.getString("fbInter");
                    SplashActivity.this.onAppOpenClose = new OnAppOpenClose() {
                        public void OnAppOpenClose() {
                            if (SplashActivity.this.adshowed == 0) {
                                SplashActivity.this.adshowed = 1;
                                SplashActivity.this.startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                                SplashActivity.this.finish();
                            }
                        }

                        public void OnAppOpenFailToLoad() {
                            if (SplashActivity.this.adshowed == 0) {
                                SplashActivity.this.adshowed = 1;
                                SplashActivity.this.startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                                SplashActivity.this.finish();
                            }
                        }
                    };
                    AdsImplements.LoadAdInter();
                    AdsImplements.LoadFbInter();
                    AdsImplements.LoadFbBanner();
                    AdsImplements.LoadAdBanner();
                    AdsImplements.LoadAdNative();
                    AdsImplements.LoadAdBannerNative();
                    AdsImplements.LoadFbNative();
                    AdsImplements.LoadAdNative01();
                    AdsImplements.LoadAdBannerNative01();
                    AdsImplements.LoadFbNative01();
                    AdsImplements.LoadFbBannerNative01();
                    AdsImplements.LoadFbBannerNative();
                    SplashActivity.this.adshowed = 0;
                    if (SplashActivity.isAdmob) {
                        SplashActivity.appOpenManager = new AppOpenManager(AppApplicationClass.getInstance(), SplashActivity.this.onAppOpenClose);
                        return;
                    }
                    SplashActivity.this.adshowed = 1;
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    SplashActivity.this.finish();
                } catch (Exception e) {
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }));
    }


    public void onInstallReferrerSetupFinished(int responseCode) {
        switch (responseCode) {
            case 0:
                try {
                    getReferralUser();
                    return;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }

    private void getReferralUser() throws RemoteException {
        mReferral = this.mReferrerClient.getInstallReferrer().getInstallReferrer();
    }

    public void onInstallReferrerServiceDisconnected() {
    }
}
