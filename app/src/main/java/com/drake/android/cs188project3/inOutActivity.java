package com.drake.android.cs188project3;

import android.content.Intent;
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
    private ArrayList<Integer> options;
    //private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out);

        optOne = (ImageButton) findViewById(R.id.optOne);
        optTwo = (ImageButton) findViewById(R.id.optTwo);
        options = new ArrayList<Integer>();


       // i = 0;

        optOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options.add(1);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        optTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                options.add(2);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });


    }
}
