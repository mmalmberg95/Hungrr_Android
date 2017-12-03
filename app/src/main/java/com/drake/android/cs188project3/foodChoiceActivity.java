package com.drake.android.cs188project3;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class foodChoiceActivity extends AppCompatActivity {
    private Context context;
    private ImageButton foodOne;
    private ImageButton foodTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_choice);
        context = this;

        foodOne = (ImageButton) findViewById(R.id.foodOne);
        foodTwo = (ImageButton) findViewById(R.id.foodTwo);


        foodOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        foodTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        switchTimer();
    }

    public void switchTimer(){
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //insert results
                 if (i == 10) {
                    final Intent j = new Intent(context, finalPage.class);
                    startActivity(j);
                }
                else{
                    final Intent intent = getIntent();
                    intent.putExtra("update", i);
                    startActivity(intent);}
            }
        }, 2000);
    }
}
