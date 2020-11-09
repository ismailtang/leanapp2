package com.leanapp.leanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

public class SplashScreen extends AppCompatActivity{

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        android.net.ConnectivityManager cm = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {

            splashScreen();
            //connectionSuccess();
        }
        else {

            connectionErrorNotification();
            //startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
        }

    }

    private void splashScreen(){

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this,LoginActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);

            }
        }, SPLASH_DISPLAY_LENGTH);

    }

    private void connectionErrorNotification(){

        SuperActivityToast.create(SplashScreen.this, new Style(), Style.TYPE_BUTTON)
                .setText("Connection Not Found")
                .setDuration(Style.DURATION_VERY_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setGravity(Gravity.BOTTOM)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_FLY).show();

    }

    protected void connectionSuccess(){

        SuperActivityToast.create(SplashScreen.this, new Style(),Style.TYPE_BUTTON)
                .setText("Connection is Active!")
                .setDuration(Style.DURATION_VERY_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setGravity(Gravity.BOTTOM)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_LIGHT_GREEN))
                .setAnimations(Style.ANIMATIONS_FADE).show();

    }

    private void gpsConnectionSuccess(){

        SuperActivityToast.create(SplashScreen.this, new Style(), Style.TYPE_BUTTON)
                .setText("GPS Not Found")
                .setDuration(Style.DURATION_VERY_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setGravity(Gravity.BOTTOM)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_FLY).show();


    }

}