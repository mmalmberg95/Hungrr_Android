package com.drake.android.cs188project3;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.util.Log;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    private TextView welcomeTxt;
    private TextView introTxt;
    private ImageButton playButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTxt = (TextView) findViewById(R.id.welcomeTxt);
        introTxt = (TextView) findViewById(R.id.introTxt);
        playButton = (ImageButton) findViewById(R.id.playButton);


        //Theming the word 'TWO' to orange text
        Spannable wordToSpan = new SpannableString("You have TWO seconds to pick between two options on your quest to conquer your craving.");
        wordToSpan.setSpan(new ForegroundColorSpan(Color.argb(255, 213, 103, 42)), 9, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        introTxt.setText(wordToSpan);




        Animation one = AnimationUtils.loadAnimation(this, R.anim.first);
        Animation two = AnimationUtils.loadAnimation(this, R.anim.second);
        welcomeTxt.startAnimation(one);
        introTxt.startAnimation(two);
        playButton.startAnimation(two);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),CountdownActivity.class);
                startActivity(intent);
            }
        });


    }
}



