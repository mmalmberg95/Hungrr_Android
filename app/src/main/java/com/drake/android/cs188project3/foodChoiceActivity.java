package com.drake.android.cs188project3;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.graphics.Bitmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

import static java.lang.Boolean.TRUE;

public class foodChoiceActivity extends AppCompatActivity {
    private Context context;
    private ImageButton foodOne;
    private ImageButton foodTwo;
    FoodRealm Opt1 = new FoodRealm();
    FoodRealm Opt2 = new FoodRealm();
    private Integer round;
    private Integer i;

    private Realm realm;

    //Put all in Realm
    private ArrayList<FoodRealm> foodData;

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
        context = this;

        foodOne = (ImageButton) findViewById(R.id.foodOne);
        foodTwo = (ImageButton) findViewById(R.id.foodTwo);

        realm = Realm.getDefaultInstance();

        RealmResults<FoodRealm> all = realm.where(FoodRealm.class).findAll();
        foodData.addAll(realm.copyFromRealm(all));

        final SharedPreferences used = getApplicationContext().getSharedPreferences("usedPref", 0);
        final SharedPreferences.Editor editor = used.edit();

        final SharedPreferences results = getApplicationContext().getSharedPreferences("resultsPref", 0);
        final SharedPreferences.Editor resultsEdit = results.edit();


        //gets the number round of the game
        Intent myIntent = getIntent();
        round = myIntent.getIntExtra("round", 1);
        String type = myIntent.getStringExtra("type");




        //First choice in game
        if (round == 1){
//            RealmResults<FoodRealm> all = realm.where(FoodRealm.class).findAll();
//            foodData.addAll(all);

            //pulls two random options from the data set
            Opt1 = pull_random(foodData);
            Opt2 = pull_random(foodData);

            //makes sure the options aren't of the same Category
            checkSame(Opt1, Opt2, foodData);

            //Initializes the results array to update with count numbers
            //of each category
            resultsEdit.putInt("PanAsian", 0);
            resultsEdit.putInt("Mexican", 0);
            resultsEdit.putInt("Italian", 0);
            resultsEdit.putInt("American", 0);
            resultsEdit.putInt("Seafood", 0);
            resultsEdit.putInt("Southern", 0);
            resultsEdit.putInt("Pizza", 0);
            resultsEdit.putInt("Greek", 0);
            resultsEdit.commit();

            editor.putString("PanAsian", "PanAsian");
            editor.putString("Mexican", "Mexican");
            editor.putString("Italian", "Italian");
            editor.putString("American", "American");
            editor.putString("Seafood", "Seafood");
            editor.putString("Southern", "Southern");
            editor.putString("Pizza", "Pizza");
            editor.putString("Greek", "Greek");
            editor.commit();



//            pictureView(Opt1, foodOne);
//            pictureView(Opt2, foodTwo);
        }

        //last choice in game
        else if(round == 10){
            //Gets the most chosen category and pulls from that category
            type = getMax(results);
            Opt1 = pull_last(foodData, type);

            //Gets second most chosen category and pulls from that category
            type = getMax(results);
            Opt2 = pull_last(foodData, type);

//            pictureView(Opt1, foodOne);
//            pictureView(Opt2, foodTwo);

            //Resets results for next play of game

        }

        //Every Round
         else{
            //pulls one option with the same category as the one previously chosen
            // in the last round
            Opt1 = pull_last(foodData, type);
            //pulls random option
            Opt2 = pull_random(foodData);

            //compares the two options
            checkSame(Opt1, Opt2, foodData);
            //Makes sure that the new category pulled hasn't been used before
            Opt2 = checkType(type, used, foodData, Opt2);

//            pictureView(Opt1, foodOne);
//            pictureView(Opt2, foodTwo);

        }

        final Intent next = new Intent(this, finalPage.class);

        foodOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //gets the category of chosen option and sends it to the activity for
                // future reference
                String type = Opt1.getCategory();
                final Intent intent = getIntent();
                intent.putExtra("round", round);
                intent.putExtra("type", type);

                //updates the correct index of the results array
                int score = results.getInt(type, 0);
                resultsEdit.putInt(type, score+1);
                resultsEdit.commit();

                //adds opposite category to the previous array so that
                // the category wont get chosen again
                editor.remove(type);
                editor.commit();

                //starts next activity based on where in the game we are
                if (round == 10){
                    editor.clear();
                    editor.commit();
                    resultsEdit.clear();
                    resultsEdit.commit();
                    next.putExtra("type", type);
                    startActivity(next);
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

                //gets the category of chosen option and sends it to the activity for
                // future reference
                String type = Opt2.getCategory();
                final Intent intent = getIntent();
                intent.putExtra("round", round);
                intent.putExtra("type", type);

                //updates the correct index of the results array
                int score = results.getInt(type, 0);
                resultsEdit.putInt(type, score+1);
                resultsEdit.commit();

                //adds opposite category to the previous array so that
                // the category wont get chosen again
                editor.remove(type);
                editor.commit();

                //starts next activity based on where in the game we are
                if (round == 10){
                    editor.clear();
                    editor.commit();
                    resultsEdit.clear();
                    resultsEdit.commit();
                    next.putExtra("type", type);
                    startActivity(next);
                }
                else{
                    round ++;
                    startActivity(intent);
                }
            }
        });

        switchTimer();
    }

    public void switchTimer(){
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //insert result
                 if (round == 10) {
                    final Intent j = new Intent(context, finalPage.class);
                    j.putExtra("type", Opt1.getCategory());
                    startActivity(j);
                }
                else{
                    final Intent intent = getIntent();
                    intent.putExtra("round", round);
                    startActivity(intent);}
            }
        }, 3000);
    }


    //sets the imageView with the corresponding image of food
//    void pictureView (FoodRealm item,  ImageButton food) {
//        byte[] image = item.getFoodImage();
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
//        food.setImageBitmap(bitmap);
//
//    }


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
    FoodRealm checkType(String type, SharedPreferences used, ArrayList<FoodRealm> available,FoodRealm option){
        if (used.contains(type) == TRUE){
//            Random rand = new Random();
//            int index = rand.nextInt(7);
//            //type = getString(index);
            option = pull_random(available);
            type = option.getCategory();
            option = checkType(type, used, available, option);
        }

        return option;
    }

    //Finds the max value of teh results array, with the index
    // corresponding to the correct cuisine
    String getMax (SharedPreferences results){
        int max = 0;
        int value = 0;
        String type = null;
        Map<String, ?> scores = results.getAll();
        for(Map.Entry<String, ?> entry : scores.entrySet()){
            String key = entry.getKey();
            value = results.getInt(key, 0);
            if ( value > max ){
                max = value;
                type = key;
            }
        }
        return type;
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

    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }

}
