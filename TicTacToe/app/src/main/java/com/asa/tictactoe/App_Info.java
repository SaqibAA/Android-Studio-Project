package com.asa.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class App_Info extends AppCompatActivity {

    TextView t1,t2,t3;
    ImageView m1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);


        getSupportActionBar().hide();
        t1 = findViewById(R.id.name);
        t2 = findViewById(R.id.version);
        t3 = findViewById(R.id.copyright);
        m1 = findViewById(R.id.logo);

    }
}