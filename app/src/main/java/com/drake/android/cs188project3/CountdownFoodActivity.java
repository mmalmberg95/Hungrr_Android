package com.drake.android.cs188project3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CountdownFoodActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

//        LottieAnimationView countdown = (LottieAnimationView) findViewById(R.id.countdown);
//        countdown.setAnimation("countdown.json");
//        countdown.loop(false);
//        countdown.playAnimation();

        final Intent i = new Intent(this,foodChoiceActivity.class);
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
