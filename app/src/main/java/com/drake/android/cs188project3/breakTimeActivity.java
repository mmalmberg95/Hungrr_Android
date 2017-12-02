package com.drake.android.cs188project3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class breakTimeActivity extends AppCompatActivity {
    private ImageButton foodPlay;
    private TextView breakText1;
    private TextView breakText2;
    private TextView breakText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_time);

        foodPlay = (ImageButton) findViewById(R.id.playBtn);

        breakText1 = (TextView) findViewById(R.id.brkTxt1);
        breakText2 = (TextView) findViewById(R.id.brkTxt2);
        breakText3 = (TextView) findViewById(R.id.brkTxt3);

        Spannable wordToSpan = new SpannableString("Let the FOOD ROUND begin!");
        wordToSpan.setSpan(new ForegroundColorSpan(Color.argb(255, 213, 103, 42)), 8, 18, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        breakText3.setText(wordToSpan);

        foodPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CountdownFoodActivity.class);
                startActivity(intent);
            }
        });
    }
}
