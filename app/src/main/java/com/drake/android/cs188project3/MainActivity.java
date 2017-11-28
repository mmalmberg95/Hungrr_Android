package com.drake.android.cs188project3;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private TextView welcomeTxt;
    private TextView introTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTxt = (TextView) findViewById(R.id.welcomeTxt);
        introTxt = (TextView) findViewById(R.id.introTxt);




        Animation one = AnimationUtils.loadAnimation(this, R.anim.first);
        Animation two = AnimationUtils.loadAnimation(this, R.anim.second);
        welcomeTxt.startAnimation(one);
        introTxt.startAnimation(two);

        final Intent i = new Intent(this,CountdownActivity.class);
        Thread timer = new Thread(){
            public void run() {
                try{
                    sleep(5000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();

    }
}



