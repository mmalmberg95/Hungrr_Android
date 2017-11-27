package com.drake.android.cs188project3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class finalPage extends AppCompatActivity {
private Button tryAgain;
private ImageView imageView;
private TextView cuisineText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page);

        tryAgain = (Button) findViewById(R.id.tryAgain);
        imageView = (ImageView) findViewById(R.id.imageView);
        cuisineText = (TextView)findViewById(R.id.cuisineText);

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), foodChoiceActivity.class);
                startActivity(intent);
            }
        });
    }
}
