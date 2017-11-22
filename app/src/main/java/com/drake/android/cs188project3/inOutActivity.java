package com.drake.android.cs188project3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;


public class inOutActivity extends AppCompatActivity {
    private ImageButton optOne;
    private ImageButton optTwo;
    private String[] options = {"one.jpg", "two.png", "three.png", "four.png", "five.jpg", "six.jpg"};
    private ArrayList<Integer> results;
    private int i;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out);

        optOne = (ImageButton) findViewById(R.id.optOne);
        optTwo = (ImageButton) findViewById(R.id.optTwo);
        results = new ArrayList<Integer>();

        Intent myIntent = getIntent();
        int update = myIntent.getIntExtra("update", 0);

        i = update;


        int Img1 = getResources().getIdentifier(options[i], "drawable", getPackageName());
        optOne.setImageResource(Img1);

        i++;

        int Img2 = getResources().getIdentifier(options[i], "drawable", getPackageName());
        optTwo.setImageResource(Img2);

        i++;

        final Intent intent = getIntent();
        intent.putExtra("update", i);

        final Intent j = new Intent(this, foodChoiceActivity.class);

        optOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                results.add(1);
                Log.d("one","total: " + i);
                finish();
                if (i == 6) {

                    startActivity(j);
                }
                else{startActivity(intent);}

            }
        });

        optTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                results.add(2);
                Log.d("two","total: " + i);
                finish();
                if (i == 6) {
                    startActivity(j);
                }
                else{startActivity(intent);}
            }
        });


    }
}
