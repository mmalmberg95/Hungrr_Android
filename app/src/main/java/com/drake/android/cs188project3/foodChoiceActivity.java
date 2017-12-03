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

import io.realm.Realm;
import io.realm.RealmResults;

public class foodChoiceActivity extends AppCompatActivity {

    private ImageButton foodOne;
    private ImageButton foodTwo;
    FoodRealm Opt1 = new FoodRealm();
    FoodRealm Opt2 = new FoodRealm();
    private Integer round;
    private Integer i;

    private Realm realm;

    //Put all in Realm
    private ArrayList<FoodRealm> foodData;
    private ArrayList<Food> lastData;
    private ArrayList<Integer> results;
    private ArrayList<String> previous;


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

        realm = Realm.getDefaultInstance();
       // foodData = new ArrayList<FoodRealm>(this, realm.where(FoodRealm.class).findAll());


        //gets the number round of the game
        Intent myIntent = getIntent();
        round = myIntent.getIntExtra("round", 1);
        String type = myIntent.getStringExtra("type");



        //First choice in game
        if (round == 1){
            RealmResults<FoodRealm> all = realm.where(FoodRealm.class).findAll();
            foodData.addAll(realm.copyFromRealm(all));

            Opt1 = pull_random(foodData);
            Opt2 = pull_random(foodData);

            checkSame(Opt1, Opt2, foodData);

            //Initializes the results array to update with count numbers
            //of each category
            while (i < 6){
                results.add(0);
            }


            pictureView(Opt1, foodOne);
            pictureView(Opt2, foodTwo);
        }

        //last choice in game
        else if(round == 10){
            int number = getMax(results);
            type = findString(number);
            Opt1 = pull_last(foodData, type);
            number = getMax(results);
            type = findString(number);
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
            Opt2 = checkType(type, previous, foodData, Opt2);

            pictureView(Opt1, foodOne);
            pictureView(Opt2, foodTwo);

        }

        //final Intent next = new Intent(this, lastPage.class);

        foodOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String type = Opt1.getCategory();
                final Intent intent = getIntent();
                intent.putExtra("type", type);

                int category =findCategory(type);
                int update = results.get(category);
                update ++;
                results.set(category, update);

                String other = Opt2.getCategory();
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

                String type = Opt2.getCategory();
                final Intent intent = getIntent();
                intent.putExtra("type", type);

                int category = findCategory(type);
                int update = results.get(category);
                update ++;
                results.set(category, update);

                String other = Opt1.getCategory();
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



    void pictureView (FoodRealm item,  ImageButton food) {
        //int id = getResources().getIdentifier(list.get(i).getName(), "drawable", getPackageName());
        Bitmap Img = item.getFoodImage();
        food.setImageBitmap(Img);
        //include textview assignment
    }


    //Pulls a random food object from the availble list of foods
    FoodRealm pull_random(ArrayList<FoodRealm> list){

        Random rand = new Random();
        int index = rand.nextInt(list.size());

        FoodRealm item = list.get(index);

        return item;
    }

    //Pulls a food item that is of the same type of the last food chosen in game
    FoodRealm pull_last (ArrayList<FoodRealm> all, String type){
        ArrayList<FoodRealm> last = new ArrayList<>();
        for (i=0; i < all.size(); i++){
            if (all.get(i).getCategory() == type){
                last.add(all.get(i));
            }
        }

        Random rand = new Random();
        int index = rand.nextInt(last.size());

        FoodRealm option = last.get(index);

        last.clear();

        return option;
    }

    //Checks to see if the two Food options have the same category
    FoodRealm checkSame (FoodRealm one, FoodRealm two, ArrayList<FoodRealm> data) {
        while (one.getCategory() == two.getCategory()){
            two = pull_random(data);
        }
        return two;
    }

    //Checks to see if the category of given food has been used before in the game
    FoodRealm checkType(String type, ArrayList<String> used, ArrayList<FoodRealm> available,FoodRealm option){
        if (used.size() == 8){
            return option;
        }
        for (i = 0; i < used.size(); i++){
            if (type == used.get(i)){
                option = pull_random(available);
                type = option.getCategory();
                i = 0;
            }
        }
        return option;
    }

    //Finds the max value of teh results array, with the index
    // corresponding to the correct cuisine
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

    //Converts string name to index
    Integer findCategory(String answer){
        Integer index = 0;
        if (answer == "PanAsian"){
            index = 0;
        }

        if (answer == "Mexican"){
            index = 1;
        }

        if (answer == "Italian"){
            index = 2;
        }

        if (answer == "American"){
            index = 3;
        }

        if (answer == "Seafood"){
            index = 4;
        }

        if (answer == "Southern"){
            index = 5;
        }

        if (answer == "Pizza"){
            index = 6;
        }

        if (answer == "Greek"){
            index = 7;
        }

        return index;
    }

    //converts index to string name
    String findString(int answer){
        String index = "null";
        if (answer == 0){
            index = "PanAsian";
        }

        if (answer == 1){
            index = "Mexican";
        }

        if (answer == 2){
            index = "Italian";
        }

        if (answer == 3){
            index = "American";
        }

        if (answer == 4){
            index = "Seafood";
        }

        if (answer == 5){
            index = "Southern";
        }

        if (answer == 6){
            index = "Pizza";
        }

        if (answer == 7){
            index = "Greek";
        }

        return index;
    }


    //    PanAsian = 0
//    Mexican = 1
//    Italian = 2
//    American = 3
//    Seafood = 4
//    Southern = 5
//    Pizza = 6
//    Greek = 7
//    currently: One Array: Pull random to start, loop to find next list

//    Questions? Which version?
//                Random Function?
//                Get index from max value in array?
//                Thread Timer?

}
