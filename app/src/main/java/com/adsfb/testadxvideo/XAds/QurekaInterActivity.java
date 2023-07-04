package com.adsfb.testadxvideo.XAds;

import static com.adsfb.testadxvideo.SplashActivity.directQureka;
import static com.adsfb.testadxvideo.SplashActivity.qurekaLink;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import com.adsfb.testadxvideo.R;

import java.util.Random;

public class QurekaInterActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        int drawablebanner;
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        try {
            getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView((int) R.layout.activity_qureka_inter);
        if (directQureka) {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();

            customTabsIntent.intent.setPackage("com.android.chrome");
            builder.setToolbarColor(ContextCompat.getColor(this, R.color.black));
            builder.setShowTitle(true);
            builder.addDefaultShareMenuItem();
            customTabsIntent.intent.setData(Uri.parse(qurekaLink));
            startActivityForResult(customTabsIntent.intent, 51, customTabsIntent.startAnimationBundle);

        }
    }
}
