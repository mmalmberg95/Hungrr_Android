package com.drake.android.cs188project3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

//        LottieAnimationView countdown = (LottieAnimationView) findViewById(R.id.countdown);
//        countdown.setAnimation("countdown.json");
//        countdown.loop(false);
//        countdown.playAnimation();

        final Intent i = new Intent(this,inOutActivity.class);
        Thread timer = new Thread(){
            public void run() {
                try{
                    sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();


    }
}
