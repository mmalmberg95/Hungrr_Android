package com.drake.android.cs188project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class foodChoiceActivity extends AppCompatActivity {

    private ImageButton foodOne;
    private ImageButton foodTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_choice);

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
    }



}
