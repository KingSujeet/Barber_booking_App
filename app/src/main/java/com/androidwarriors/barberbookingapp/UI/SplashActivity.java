package com.androidwarriors.barberbookingapp.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.androidwarriors.barberbookingapp.R;
import com.androidwarriors.barberbookingapp.Utils.Constants;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    /** Duration of wait **/
    private final static int SPLASH_DISPLAY_LENGTH = 2000;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){

            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isNetworkConnected()){

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();

                }else {

                    Toast.makeText(getApplicationContext(),"Please check your Internet Connection",Toast.LENGTH_LONG).show();
                    finish();

                }

            }
        },Constants.SPLASH_DISPLAY_LENGTH);



    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public boolean isInternetAvailable() {
        try {

            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}