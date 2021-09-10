package com.asa.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    private final int TIME_OUT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView=findViewById(R.id.im1);
        textView=findViewById(R.id.tv1);

        getSupportActionBar().hide();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashActivity.this, MainActivity.class);
                //Intent is used to switch from one activity to another.
                startActivity(i);

                finish();
                //the current activity will get finished.
            }
        },TIME_OUT);

    }
}