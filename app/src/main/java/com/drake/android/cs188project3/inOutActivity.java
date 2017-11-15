package com.drake.android.cs188project3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class inOutActivity extends AppCompatActivity {
    private ImageButton good;
    private ImageButton bad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out);


        good = (ImageButton) findViewById(R.id.goodButton);
        bad = (ImageButton) findViewById(R.id.badButton);

        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(), inOutActivity.class);
//                startActivity(intent);
            }
        });

        bad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(), inOutActivity.class);
//                startActivity(intent);
            }
        });

    }
}
