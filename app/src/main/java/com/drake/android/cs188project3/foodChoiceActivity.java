package com.drake.android.cs188project3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class foodChoiceActivity extends AppCompatActivity {

    private ImageButton foodOne;
    private ImageButton foodTwo;
    private Integer round;
    private Integer i;

    //Put all in Realm
    private ArrayList<Food> foodData;
    private ArrayList<Integer> results;
    private ArrayList<Integer> previous;

//    American = 0
//    PanAsian = 1
//    Greek = 2
//    Italian = 3
//    Southern = 4
//    Mexican = 5
//    Pizza = 6
//    Seafood = 7


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_choice);

        foodOne = (ImageButton) findViewById(R.id.foodOne);
        foodTwo = (ImageButton) findViewById(R.id.foodTwo);

        //gets the number round of the game
        Intent myIntent = getIntent();
        round = myIntent.getIntExtra("round", 1);
        String last = myIntent.getStringExtra("last");

//        if (round == 1){
            Food Opt1 = pull_random(foodData);
            Food Opt2 = pull_random(foodData);

            checkSame(Opt1, Opt2, foodData);

            while (i < 6){
                results.add(0);
            }
            pictureView(Opt1, i, foodOne);
            pictureView(Opt2, i, foodTwo);
//        }
//        else if(round == 10){
//
//        }

//         else{
//          Food Opt1 = pull_random(lastData);
//            Food Opt2 = pull_random(foodData);
//            checkSame(Opt1, Opt2, foodData);
//        pictureView(Opt1, i, foodOne);
//        pictureView(Opt2, i, foodTwo);

//        }




        final Intent intent = getIntent();












        foodOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

       //         Integer type = foodData.get(i).getType();
                final Intent intent = getIntent();
                intent.putExtra("last", type);

                int update = results.get(type);
                update ++;
                results.set(i, update);

                round ++;
                startActivity(intent);
            }
        });


        foodTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //         Integer type = foodData.get(i).getType();
                final Intent intent = getIntent();
                intent.putExtra("last", type);

                int update = results.get(type);
                update ++;
                results.set(i, update);

                round++;
                startActivity(intent);
            }
        });
    }


    void pictureView (Food item, int i, ImageButton food) {
        //int id = getResources().getIdentifier(list.get(i).getName(), "drawable", getPackageName());
        Bitmap Img = item.getFoodImage();
        food.setImageBitmap(Img);
        //include textview assignment

    }

    Food pull_random(ArrayList<Food> list){

        Random rand = new Random();
        int index = rand.nextInt(list.size());

        Food thing = list.get(index);

        return thing;
    }

//    ArrayList<Food> findArray (int type){
//        if (type == 0){
//            return americanList;
//        }
//
//        return foodData;
//    }

    Food checkSame (Food one, Food two, ArrayList<Food> data) {
        while (one.getType() == two.getType()){
            two = pull_random(data);
        }
        return two;
    }

    Integer checkType(Integer type, ArrayList<Integer> used){
        for (i = 0; i < used.size(); i++){
            if (type == used.get(i)){
//                type = Rand
                checkType(type, used);
            }
        }
        return type;
    }

//    Food pullLast (Integer type, ArrayList<Food> data){
//
//    }


//    currently: One Array: Pull random to start, loop to find next list
//    possibly?: 8 Arrays, Random Int to start, pull straight from array for next

//    Questions? Which version?
//                Random Function?
//                Get index from max value in array?
//                Thread Timer?

}
