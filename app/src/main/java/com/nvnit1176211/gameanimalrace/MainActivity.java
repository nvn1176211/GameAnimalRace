package com.nvnit1176211.gameanimalrace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView score;
    ImageButton start, restart;
    CheckBox cb1, cb2, cb3;
    SeekBar sb1, sb2, sb3;
    CountDownTimer cd;
    int originalPoint = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        disableSeekBar();

        cd = new CountDownTimer(10100, 100) {
            @Override
            public void onTick(long l) {
                if(sb1.getProgress() == sb1.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this, "Horse win", Toast.LENGTH_SHORT).show();
                    if(cb1.isChecked()){
                        Toast.makeText(MainActivity.this, "You win!\n +10 points", Toast.LENGTH_SHORT).show();
                        score.setText(String.valueOf(originalPoint +10));
                    }else{
                        Toast.makeText(MainActivity.this, "You lose!\n -5 points", Toast.LENGTH_SHORT).show();
                        score.setText(String.valueOf(originalPoint -5));
                    }
                }else if(sb2.getProgress() == sb2.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this, "Pig win", Toast.LENGTH_SHORT).show();
                    if(cb2.isChecked()){
                        Toast.makeText(MainActivity.this, "You win!\n +10 points", Toast.LENGTH_SHORT).show();
                        score.setText(String.valueOf(originalPoint +10));
                    }else{
                        Toast.makeText(MainActivity.this, "You lose!\n -5 points", Toast.LENGTH_SHORT).show();
                        score.setText(String.valueOf(originalPoint -5));
                    }
                }else if(sb3.getProgress() == sb3.getMax()){
                    this.cancel();
                    Toast.makeText(MainActivity.this, "Bird win", Toast.LENGTH_SHORT).show();
                    if(cb3.isChecked()){
                        Toast.makeText(MainActivity.this, "You win!\n +10 points", Toast.LENGTH_SHORT).show();
                        score.setText(String.valueOf(originalPoint +10));
                    }else{
                        Toast.makeText(MainActivity.this, "You lose!\n -5 points", Toast.LENGTH_SHORT).show();
                        score.setText(String.valueOf(originalPoint -5));
                    }
                }
                Random rd = new Random();
                int speed1 = rd.nextInt(5);
                int speed2 = rd.nextInt(5);
                int speed3 = rd.nextInt(5);
                sb1.setProgress(sb1.getProgress()+speed1);
                sb2.setProgress(sb2.getProgress()+speed2);
                sb3.setProgress(sb3.getProgress()+speed3);
            }

            @Override
            public void onFinish() {

            }
        };

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                }
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb1.isChecked() || cb2.isChecked() || cb3.isChecked()){
                    disableCheckBox();
                    start.setVisibility(View.INVISIBLE);
                    restart.setVisibility(View.VISIBLE);
                    cd.start();
                }else{
                    Toast.makeText(MainActivity.this, "You must place a bet before you play", Toast.LENGTH_SHORT).show();
                }
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableCheckBox();
                restart.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);
                sb1.setProgress(0);
                sb2.setProgress(0);
                sb3.setProgress(0);
            }
        });
    }

    private void disableSeekBar(){
        sb1.setEnabled(false);
        sb2.setEnabled(false);
        sb3.setEnabled(false);
    }

    private void disableCheckBox(){
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
    }

    private void enableCheckBox(){
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
    }

    private void anhXa(){
        score   = findViewById(R.id.textViewScore);
        start   = findViewById(R.id.imgBtnPlay);
        restart = findViewById(R.id.imgBtnRestart);
        cb1     = findViewById(R.id.checkBox1);
        cb2     = findViewById(R.id.checkBox2);
        cb3     = findViewById(R.id.checkBox3);
        sb1     = findViewById(R.id.seekBar1);
        sb2     = findViewById(R.id.seekBar2);
        sb3     = findViewById(R.id.seekBar3);
    }
}
