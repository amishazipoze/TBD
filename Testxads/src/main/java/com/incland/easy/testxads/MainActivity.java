package com.incland.easy.testxads;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.adsfb.testadxvideo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("testadxvideo");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());
        Log.e(TAG, "Native ad finished downloading all assets.");


        Log.e(TAG, "Native ad finished downloading all assets.");



        Log.e(TAG, "Native ad finished downloading all assets.");


    }

    /**
     * A native method that is implemented by the 'testadxvideo' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}