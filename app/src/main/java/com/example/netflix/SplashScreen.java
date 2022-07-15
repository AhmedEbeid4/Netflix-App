package com.example.netflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();
        MediaPlayer m= MediaPlayer.create(this,R.raw.netflix_intro_sound);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                m.start();
            }
        },2000);
        new Handler().postDelayed(new Runnable() {
                      @Override
                      public void run() {
                          startActivity(new Intent(SplashScreen.this,Login.class));
                          overridePendingTransition(0,0);
                          m.stop();
                      }
                  }, 5600);
    }
}