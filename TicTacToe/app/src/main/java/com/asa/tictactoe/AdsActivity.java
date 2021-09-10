package com.asa.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class AdsActivity extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5;
    Space s1,s2,s3,s4,s5,s6;

    private AdView mAdView1,mAdView2,mAdView3,mAdView4,mAdView5,mAdView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);

        t1 = findViewById(R.id.textView4);
        t2 = findViewById(R.id.textView6);
        t3 = findViewById(R.id.textView7);
        t4 = findViewById(R.id.textView8);
        t5 = findViewById(R.id.textView9);

        s1 =findViewById(R.id.as1);
        s2 =findViewById(R.id.as2);
        s3 =findViewById(R.id.as3);
        s4 =findViewById(R.id.as4);
        s5 =findViewById(R.id.as5);
        s6 =findViewById(R.id.as6);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView1 = findViewById(R.id.adView1);
        mAdView2 = findViewById(R.id.adView2);
        mAdView3 = findViewById(R.id.adView3);
        mAdView4 = findViewById(R.id.adView4);
        mAdView5 = findViewById(R.id.adView5);
        mAdView6 = findViewById(R.id.adView6);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest);
        mAdView2.loadAd(adRequest);
        mAdView3.loadAd(adRequest);
        mAdView4.loadAd(adRequest);
        mAdView5.loadAd(adRequest);
        mAdView6.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitem2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent = new Intent(AdsActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.share:
                shareApp();
                return true;
            case R.id.app_info:
                Intent i = new Intent(AdsActivity.this, App_Info.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void shareApp(){
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Download Tic Tac Toe Game Play With Friends: https://play.google.com/store/apps/details?id=" + getPackageName() + "&hl=it" );
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Tic Tac Toe");
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent,"Share Via"));
    }
}