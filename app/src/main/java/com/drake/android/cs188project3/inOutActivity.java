package com.drake.android.cs188project3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
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


public class inOutActivity extends AppCompatActivity {

    private Handler mHandler;
    private int mInterval = 2000;

    private ImageButton optOne;
    private ImageButton optTwo;
    private String[] options = {"good", "casual", "sweet"};
    private String[] options2 = {"bad", "fancy", "savory"};
    private String[] chosen1 = {"goodchosen", "casualchosen", "sweetchosen"}; //Jedi
    private String[] chosen2 = {"badchosen", "fancychosen", "savorychosen"};
    private int i;
    private ArrayList<Food> foodData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out);

        optOne = (ImageButton) findViewById(R.id.optOne);
        optTwo = (ImageButton) findViewById(R.id.optTwo);

        InputStream is = getResources().openRawResource(R.raw.food_spreadsheet);

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        //loop to read file
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                //split by comma
                //info is a just a identifier for the different splits
                String[] info = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                Food data = new Food();
                data.setName(info[0]);
                data.setType(Integer.parseInt(info[1]));
                data.setTaste((info[2]));
                data.setPrice(info[3]);
                data.setHealth(info[4]);

                foodData.add(data);

            }

        } catch (IOException e) {
            //wtf = What a Terrible Failure
            Log.wtf("OptionsList", "Error reading file at line " + line, e);
            e.printStackTrace();
        }

//        assignImages(foodData);


        Intent myIntent = getIntent();
        int update = myIntent.getIntExtra("update", 0);

        i = update;


        int Img1 = getResources().getIdentifier(options[i], "drawable", getPackageName());
        optOne.setImageResource(Img1);

        int Img2 = getResources().getIdentifier(options2[i], "drawable", getPackageName());
        optTwo.setImageResource(Img2);



        i++;

        final Intent intent = getIntent();
        intent.putExtra("update", i);


        final Intent j = new Intent(this, breakTimeActivity.class);
      
        optOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Img1 = getResources().getIdentifier(chosen1[i-1], "drawable", getPackageName());
                optOne.setImageResource(Img1);

                String choice = setup(i);
                filter(foodData, i, choice);
                //((TransitionDrawable) optOne.getDrawable()).startTransition(500);

                if (i == 3) {

                    startActivity(j);
                }
//                else{startActivity(intent);
//              }

                Thread timer = new Thread(){
                    public void run() {
                        try{
                            sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finally {
                            startActivity(intent);
                            finish();
                        }
                    }
                };
                timer.start();
            }
        });

        optTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //i++;
                // MATT MATT MATT MATT MATT MATT I'M NOT SURE WHAT THIS WAS FOR, I DO NOT WANT
                // BREAK IT. WAS CAUSING PROBLEMS WITH THE STRING ARRAYS FOR CHOSEN OPTIONS.
                // PLEASE CONFIRM IT STILL SERVES INITIAL PURPOSE

                int Img2 = getResources().getIdentifier(chosen2[i-1], "drawable", getPackageName());
                optTwo.setImageResource(Img2);


                String choice = setup(i);
                filter(foodData, i, choice);

                if (i == 3) {
                    startActivity(j);
                }
//                else{startActivity(intent);
//              }

                Thread timer = new Thread(){
                    public void run() {
                        try{
                            sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finally {
                            startActivity(intent);
                            finish();
                        }
                    }
                };
                timer.start();
            }
        });

        mHandler = new Handler();
        startRepeatingTask();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        stopRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try{
               // updateStatus();
            } finally {
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask(){
        mStatusChecker.run();
    }

    void stopRepeatingTask(){
        mHandler.removeCallbacks(mStatusChecker);
    }

    ArrayList<Food> filter (ArrayList<Food> list, int attr, String filter){
        ArrayList<Food> available = new ArrayList<>();
        if (attr == 1){
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).getHealth() == filter){
                    available.add(list.get(i));
                }
            }
            return available;
        }

        else if(attr == 2){
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).getPrice() == filter){
                    available.add(list.get(i));
                }
            }
            return available;
        }

        else if(attr == 3) {
            for (int i = 0; i < list.size(); i++){
                if (list.get(i).getTaste() == filter){
                    available.add(list.get(i));
                }
            }
            return available;
        }
        return available;
    }

    String setup (int n){
        String choice = "null";
        if ( i == 1){
            choice = "Good";
        }
        else if( i == 2){
            choice =  "Bad";
        }
        else if( i == 3){
            choice =  "Pricey";
        }
        else if( i == 4){
            choice =  "Casual";
        }
        else if( i == 5){
            choice =  "Sweet";
        }
        else if( i == 6){
            choice =  "Savory";
        }

        return choice;
    }
//
//    ArrayList<Food> assignImages (ArrayList<Food> list){
//        for (int i = 0; i < list.size(); i++) {
//            int drawableId = getResources().getIdentifier(list.get(i).getName(), "drawable", getPackageName());
//            Drawable image = getResources().getDrawable(drawableId);
//            Bitmap newImage = ((BitmapDrawable) image).getBitmap();
//
//            list.get(i).setFoodImage(newImage);
//        }
//
//        return list;
//    }
}
