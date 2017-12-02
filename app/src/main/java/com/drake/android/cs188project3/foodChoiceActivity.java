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
    Food Opt1 = new Food();
    Food Opt2 = new Food();
    private Integer round;
    private Integer i;

    //Put all in Realm
    private ArrayList<Food> foodData;
    private ArrayList<Food> lastData;
    private ArrayList<Integer> results;
    private ArrayList<Integer> previous;


//    PanAsian = 0
//    Mexican = 1
//    Italian = 2
//    American = 3
//    Seafood = 4
//    Southern = 5
//    Pizza = 6
//    Greek = 7


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_choice);

        foodOne = (ImageButton) findViewById(R.id.foodOne);
        foodTwo = (ImageButton) findViewById(R.id.foodTwo);


        //gets the number round of the game
        Intent myIntent = getIntent();
        round = myIntent.getIntExtra("round", 1);
        Integer type = myIntent.getIntExtra("type", 8);



        //First choice in game
        if (round == 1){
            Opt1 = pull_random(foodData);
            Opt2 = pull_random(foodData);

            checkSame(Opt1, Opt2, foodData);

            while (i < 6){
                results.add(0);
            }
            pictureView(Opt1, foodOne);
            pictureView(Opt2, foodTwo);
        }

        //last choice in game
        else if(round == 10){
            type = getMax(results);
            Opt1 = pull_last(foodData, type);
            type = getMax(results);
            Opt2 = pull_last(foodData, type);

            pictureView(Opt1, foodOne);
            pictureView(Opt2, foodTwo);

            previous.clear();
            results.clear();
        }

        //Every Round
         else{
            Opt1 = pull_last(foodData, type);
            Opt2 = pull_random(foodData);

            checkSame(Opt1, Opt2, foodData);
            checkType(type, previous);

            pictureView(Opt1, foodOne);
            pictureView(Opt2, foodTwo);

        }

        //final Intent next = new Intent(this, lastPage.class);

        foodOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer type = Opt1.getType();
                final Intent intent = getIntent();
                intent.putExtra("type", type);

                int update = results.get(type);
                update ++;
                results.set(type, update);

                int other = Opt2.getType();
                previous.add(other);

                if (round == 10){
                    //startActivity(next);
                }
                else{
                    round ++;
                    startActivity(intent);
                }
            }
        });


        foodTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer type = foodData.get(i).getType();
                final Intent intent = getIntent();
                intent.putExtra("type", type);

                int update = results.get(type);
                update ++;
                results.set(type, update);

                int other = Opt1.getType();
                previous.add(other);

                if (round == 10){
                    //startActivity(next);
                }
                else{
                    round ++;
                    startActivity(intent);
                }
            }
        });
    }



    void pictureView (Food item,  ImageButton food) {
        //int id = getResources().getIdentifier(list.get(i).getName(), "drawable", getPackageName());
        Bitmap Img = item.getFoodImage();
        food.setImageBitmap(Img);
        //include textview assignment
    }

    Food pull_random(ArrayList<Food> list){

        Random rand = new Random();
        int index = rand.nextInt(list.size());

        Food item = list.get(index);

        return item;
    }

    Food pull_last (ArrayList<Food> all, int type){
        ArrayList<Food> last = new ArrayList<>();
        for (i=0; i < all.size(); i++){
            if (all.get(i).getType() == type){
                last.add(all.get(i));
            }
        }

        Random rand = new Random();
        int index = rand.nextInt(last.size());

        Food option = last.get(index);

        last.clear();

        return option;
    }

    Food checkSame (Food one, Food two, ArrayList<Food> data) {
        while (one.getType() == two.getType()){
            two = pull_random(data);
        }
        return two;
    }

    Integer checkType(Integer type, ArrayList<Integer> used){
        if (used.size() == 8){
            return type;
        }
        for (i = 0; i < used.size(); i++){
            if (type == used.get(i)){
                Random rand = new Random();
                type = rand.nextInt(used.size());
                i = 0;
            }
        }
        return type;
    }

    Integer getMax (ArrayList<Integer> results){
        int max = 0;
        int index = 0;
        for (i=0; i < results.size(); i++ ){
            if (results.get(i) >= max ){
                max = results.get(i);
                index = i;
            }
        }
        results.remove(index);
        return index;
    }


//    currently: One Array: Pull random to start, loop to find next list

//    Questions? Which version?
//                Random Function?
//                Get index from max value in array?
//                Thread Timer?

}
