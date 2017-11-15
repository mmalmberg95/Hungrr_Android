package com.drake.android.cs188project3;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView welcomeTxt;
    private TextView introTxt;
    private TextView timerTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTxt = (TextView) findViewById(R.id.welcomeTxt);
        introTxt = (TextView) findViewById(R.id.introTxt);
        timerTxt = (TextView) findViewById(R.id.timerTxt);


        Animation one = AnimationUtils.loadAnimation(this, R.anim.first);
        Animation two = AnimationUtils.loadAnimation(this, R.anim.second);
        Animation three = AnimationUtils.loadAnimation(this, R.anim.third);
        welcomeTxt.startAnimation(one);
        introTxt.startAnimation(two);
        timerTxt.startAnimation(three);

        final Intent i = new Intent(this,inOutActivity.class);
        Thread timer = new Thread(){
            public void run() {
                try{
                    sleep(8000);
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
