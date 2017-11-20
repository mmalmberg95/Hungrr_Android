package com.drake.android.cs188project3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.util.stream.IntStream.range;

public class inOutActivity extends AppCompatActivity {
    private ImageButton optOne;
    private ImageButton optTwo;
    private String[] options = {"one", "two", "three", "four"};
    private ArrayList<Integer> results;
    private int i;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out);

        optOne = (ImageButton) findViewById(R.id.optOne);
        optTwo = (ImageButton) findViewById(R.id.optTwo);
        results = new ArrayList<Integer>();

        Intent myIntent = getIntent();
        int update = myIntent.getIntExtra("update", 0);
        int one = myIntent.getIntExtra("one", 0);
        int two = myIntent.getIntExtra("two", 0);

        i = update;

        int Img1 = getResources().getIdentifier(options[i], "drawable", getPackageName());
        optOne.setImageResource(Img1);

        i++;

        int Img2 = getResources().getIdentifier(options[i], "drawable", getPackageName());
        optTwo.setImageResource(Img2);


        final Intent intent = getIntent();
        intent.putExtra("update", i);

        optOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                results.add(1);
                Log.d("one","total: " + i);
                finish();
                startActivity(intent);

            }
        });

        optTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                results.add(2);
                Log.d("two","total: " + i);
                finish();
                startActivity(intent);
            }
        });


    }
}
