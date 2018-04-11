package com.ptp.phamtanphat.seekbarrace1903;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton imgplay;
    CheckBox ckOne,ckTwo,ckThree;
    SeekBar skOne,skTwo,skthree;
    int indexOne,indexTwo,indexThree;
    int winner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();

        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CountDownTimer countDownTimer = new CountDownTimer(60000,500) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        Random random = new Random();

                        indexOne = random.nextInt(10) + 1;
                        indexTwo = random.nextInt(10) + 1;
                        indexThree = random.nextInt(10) + 1;

                        skOne.setProgress(skOne.getProgress() + indexOne);
                        skTwo.setProgress(skTwo.getProgress() + indexTwo);
                        skthree.setProgress(skthree.getProgress() + indexThree);

                        Hidden();

                        //Kiem tra sai so khi vuot qua max
                        if (skOne.getProgress() >= skOne.getMax()){
                            Toast.makeText(MainActivity.this, "One chien thang", Toast.LENGTH_SHORT).show();
                            this.cancel();
                            PlayAgain();
                            winner = 1;
                        }else if (skTwo.getProgress() >= skTwo.getMax()){
                            Toast.makeText(MainActivity.this, "Two chien thang", Toast.LENGTH_SHORT).show();
                            this.cancel();
                            PlayAgain();
                            winner = 2;
                        }else if (skthree.getProgress() >= skthree.getMax()){
                            Toast.makeText(MainActivity.this, "Three chien thang", Toast.LENGTH_SHORT).show();
                            this.cancel();
                            PlayAgain();
                            winner = 3;
                        }

                        if (winner == SelectOneCheckBox() && SelectOneCheckBox() != 0){
                            Toast.makeText(MainActivity.this, "Ban da chon chinh xac!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFinish() {

                    }
                };
                countDownTimer.start();
//                final Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Random random = new Random();
////
//                        indexOne = random.nextInt(10) + 1;
//                        indexTwo = random.nextInt(10) + 1;
//                        indexThree = random.nextInt(10) + 1;
//
//                        skOne.setProgress(skOne.getProgress() + indexOne);
//                        skTwo.setProgress(skTwo.getProgress() + indexTwo);
//                        skthree.setProgress(skthree.getProgress() + indexThree);
//
//                        //Kiem tra sai so khi vuot qua max
//                        if (skOne.getProgress() >= skOne.getMax()){
//                            Toast.makeText(MainActivity.this, "One chien thang", Toast.LENGTH_SHORT).show();
//                            handler.removeCallbacks(this);
//                        }else if (skTwo.getProgress() >= skTwo.getMax()){
//                            Toast.makeText(MainActivity.this, "Two chien thang", Toast.LENGTH_SHORT).show();
//                            handler.removeCallbacks(this);
//                        }else if (skthree.getProgress() >= skthree.getMax()){
//                            Toast.makeText(MainActivity.this, "Three chien thang", Toast.LENGTH_SHORT).show();
//                            handler.removeCallbacks(this);
//                        }else {
//                            handler.postDelayed(this,400);
//                        }
//                    }
//                },400);
            }
        });
    }

    private void anhxa() {
        imgplay = findViewById(R.id.imagebuttonplay);
        ckOne = findViewById(R.id.checkboxOne);
        ckTwo = findViewById(R.id.checkboxTwo);
        ckThree = findViewById(R.id.checkboxThree);
        skOne = findViewById(R.id.seekbarOne);
        skTwo = findViewById(R.id.seekbarTwo);
        skthree = findViewById(R.id.seekbarThree);
    }
    public void Hidden(){
        imgplay.setVisibility(View.INVISIBLE);
        skOne.setEnabled(false);
        skTwo.setEnabled(false);
        skthree.setEnabled(false);
    }
    public void Showed(){
        imgplay.setVisibility(View.VISIBLE);
        skOne.setEnabled(true);
        skTwo.setEnabled(true);
        skthree.setEnabled(true);
    }
    public void PlayAgain(){
        CountDownTimer countDownTimer = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Showed();
                skOne.setProgress(0);
                skTwo.setProgress(0);
                skthree.setProgress(0);
            }
        };
        countDownTimer.start();

    }
    public int SelectOneCheckBox(){
        if (ckOne.isChecked()){
            ckThree.setChecked(false);
            ckTwo.setChecked(false);
            return 1;
        }else if (ckTwo.isChecked()){
            ckOne.setChecked(false);
            ckThree.setChecked(false);
            return 2;
        }else if (ckThree.isChecked()){
            ckTwo.setChecked(false);
            ckOne.setChecked(false);
            return 3;
        }
         return 0;
    }
}
