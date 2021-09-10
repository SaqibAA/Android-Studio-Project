package com.asa.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    TextView textView1, textView2, textView3;
    Space sp1,sp2,sp3;
    int X = 0;
    int O = 0;
    int win=0;

    int PLAYER_O = 0;
    int PLAYER_X = 1;

    int activePlayer = PLAYER_X;

    int[] filledPos = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    int[][] winningPos = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}, {1, 4, 7}, {2, 5, 8}, {3, 6, 0}, {1, 5, 0}, {3, 5, 7}};

    boolean isGameActive = true;

    private AdView mAdView1,mAdView2;

    private long pressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp1 = findViewById(R.id.ms1);
        sp2 = findViewById(R.id.ms2);
        sp3 = findViewById(R.id.ms3);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView1 = findViewById(R.id.textView1);
        textView1.setText("Player X Turn");


        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn0);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        mAdView1 = findViewById(R.id.adView1);
        mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest);
        mAdView2.loadAd(adRequest);

    }

    @Override
    public void onClick(View v) {
        if (!isGameActive)
            return;

        Button clickedBtn = findViewById(v.getId());
        int clickedTag = Integer.parseInt(v.getTag().toString());

        if (filledPos[clickedTag] != -1) {
            return;
        }

        filledPos[clickedTag] = activePlayer;

        if (activePlayer == PLAYER_X) {
            clickedBtn.setText("X");
            activePlayer = PLAYER_O;
            textView1.setText("Player O Turn");
        } else {
            clickedBtn.setText("O");
            activePlayer = PLAYER_X;
            textView1.setText("Player X Turn");
        }
        checkForWin();
    }


    private void checkForWin() {

        for (int i = 0; i < 8; i++) {
            int val0 = winningPos[i][0];
            int val1 = winningPos[i][1];
            int val2 = winningPos[i][2];

            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]) {
               if (filledPos[val0] != -1) {

                    isGameActive = false;

                   if (filledPos[val0] == PLAYER_X) {
                        showDialog("X is winner");
                        X++;
                        win=1;
                        textView2.setText("Player X = " + X);

                   }else {
                        showDialog("O is winner");
                        O++;
                        win=1;
                        textView3.setText("Player O = " + O);
                   }
               }
            }
        }
        if (filledPos[0]!=-1 && filledPos[1]!=-1 && filledPos[2]!=-1 && filledPos[3]!=-1 && filledPos[4]!=-1 && filledPos[5]!=-1 && filledPos[6]!=-1 && filledPos[7]!=-1 && filledPos[8]!=-1 && win==0){
            showDialog("Match Draw or Tie");
        }
    }

    private void showDialog(String winnerText) {
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Reset();
                    }
                })
                .show();
        dialog.setCancelable(false);
    }

    private void new_game(){

        activePlayer = PLAYER_X;
        textView1.setText("Player X Turn");
        filledPos = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        textView2.setText("Player X = 0");
        textView3.setText("Player O = 0");
        X = 0;
        O = 0;
        win=0;
        isGameActive = true;
    }

    private void Reset() {

//        activePlayer = PLAYER_X;
//        textView1.setText("Player X Turn");
        filledPos = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");
        win=0;
        isGameActive = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuitem,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.HTP:
                Intent intent = new Intent(MainActivity.this, AdsActivity.class);
                startActivity(intent);
                return true;
            case R.id.new_game:
                new_game();
                return true;
            case R.id.share:
                shareApp();
                return true;
            case R.id.app_info:
                Intent i = new Intent(MainActivity.this, App_Info.class);
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

//    @Override
//    public void onBackPressed() {
//
//        if (pressedTime + 2000 > System.currentTimeMillis()) {
//            super.onBackPressed();
//            finish();
//        } else {
//            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
//        }
//        pressedTime = System.currentTimeMillis();
//    }
}


