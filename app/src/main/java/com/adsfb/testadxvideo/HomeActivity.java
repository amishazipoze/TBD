package com.adsfb.testadxvideo;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.adsfb.testadxvideo.XAds.AdsImplements;

public class HomeActivity extends AppCompatActivity {
    protected void onResume() {
        super.onResume();

        AdsImplements.NativeFinalLoad(this, findViewById(R.id.fl_adplaceholder));
        AdsImplements.NativeFinalBannerLoad(this, findViewById(R.id.fl_adplaceholder1));
        AdsImplements.BanerLoad(this, findViewById(R.id.bannerAds));


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



    }

    public void click(View view) {

        AdsImplements.InterLoad(this, new AdsImplements.OnAdListner() {
            @Override
            public void OnClose() {

                Log.e(TAG, "Native ad finished downloading all assets.");

                Toast.makeText(HomeActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });


    }
}