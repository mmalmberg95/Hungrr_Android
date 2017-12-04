package com.drake.android.cs188project3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class inOutActivity extends AppCompatActivity {

    private Context context;
  
    private Handler mHandler;
    private int mInterval = 2000;

    private ImageButton optOne;
    private ImageButton optTwo;
    private String[] options = {"good", "casual", "sweet"};
    private String[] options2 = {"bad", "fancy", "savory"};
    private String[] chosen1 = {"goodchosen", "casualchosen", "sweetchosen"}; //Jedi
    private String[] chosen2 = {"badchosen", "fancychosen", "savorychosen"};
    private int i;
    private ArrayList<FoodRealm> foodData = new ArrayList<>();
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_out);



        optOne = (ImageButton) findViewById(R.id.optOne);
        optTwo = (ImageButton) findViewById(R.id.optTwo);

//        realm = Realm.getDefaultInstance();
//        RealmResults<FoodRealm> all = realm.where(FoodRealm.class).findAll();
//        foodData.addAll(realm.copyFromRealm(all));


//        assignImages(foodData);


        Intent myIntent = getIntent();
        int update = myIntent.getIntExtra("update", 0);

        i = update;

        //If opening round, pull down all available data and assign images to it to
        // manipulate later.
        if (i==0){
//            RealmResults<FoodRealm> all = realm.where(FoodRealm.class).findAll();
//            foodData.addAll(realm.copyFromRealm(all));

//            ArrayList<FoodRealm> assignImages (ArrayList<FoodRealm> list){
//                realm.executeTransaction(new Realm.Transaction(){
//                    @Override
//                    public void execute(Realm realm){
//                        for (int i = 0; i < foodData.size(); i++) {
//                            int drawableId = getResources().getIdentifier(foodData.get(i).getDrawable(), "drawable", getPackageName());
//                            Drawable drawable = getResources().getDrawable(drawableId);
//                            BitmapDrawable image = ((BitmapDrawable) drawable);
//                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                            image.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                            byte[] imageInByte = baos.toByteArray();
//
//                            foodData.get(i).setFoodImage(imageInByte);
//                            realm.copyToRealm(foodData.get(i));
//                        }
//                        finish();
//                    }
                //});
        }


        int Img1 = getResources().getIdentifier(options[i], "drawable", getPackageName());
        optOne.setImageResource(Img1);

        int Img2 = getResources().getIdentifier(options2[i], "drawable", getPackageName());
        optTwo.setImageResource(Img2);



        i++;

        final Intent intent = getIntent();
        intent.putExtra("update", i);

        context = this;
        final Intent j = new Intent(this, breakTimeActivity.class);
      
        optOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Img1 = getResources().getIdentifier(chosen1[i-1], "drawable", getPackageName());
                optOne.setImageResource(Img1);

                String choice = setup(i);
                intent.putExtra("choice", choice);
//                filter(foodData, i, choice);

                //Changes Background of image to show selection
                Thread timer = new Thread(){
                    public void run() {
                        try{
                            sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finally {
                            if (i==3){
                                startActivity(j);
                            }
                            else{
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                };
                timer.start();
            }
        });

        optTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int Img2 = getResources().getIdentifier(chosen2[i-1], "drawable", getPackageName());
                optTwo.setImageResource(Img2);

                //filters the available data based on the choice
                String choice = setup(i);
                intent.putExtra("choice", choice);

//                filter(foodData, i, choice);

                //changes background of image to show selection
                Thread timer = new Thread(){
                    public void run() {
                        try{
                            sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        finally {
                            if (i==3){
                                startActivity(j);
                            }
                            else{
                                startActivity(intent);
                                finish();
                            }
                        }
                    }
                };
                timer.start();
            }
        });


        switchTimer();
    }

    public void switchTimer() {
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //insert results
                if (i == 3) {
                    final Intent j = new Intent(context, foodChoiceActivity.class);
                    startActivity(j);
                } else {
                    final Intent intent = getIntent();
                    intent.putExtra("update", i);
                    startActivity(intent);
                }
            }
        }, 3000);
    }
      

    //filters the available data by removing the data that doesn't fit the description
//    ArrayList<FoodRealm> filter (ArrayList<FoodRealm> list, int attr, String filter){
//        ArrayList<FoodRealm> available = new ArrayList<>();
//        if (attr == 1){
//            for (int i = 0; i < list.size(); i++){
//                if (list.get(i).getGoodOrBad() == filter){
//                    available.add(list.get(i));
//                }
//            }
//            return available;
//        }
//
//        else if(attr == 2){
//            for (int i = 0; i < list.size(); i++){
//                if (list.get(i).getPriceyOrCasual() == filter){
//                    available.add(list.get(i));
//                }
//            }
//            return available;
//        }
//
//        else if(attr == 3) {
//            for (int i = 0; i < list.size(); i++){
//                if (list.get(i).getSweetOrSavory() == filter){
//                    available.add(list.get(i));
//                }
//            }
//            return available;
//        }
//
//
//        return available;
//    }

    //Translates integers to strings
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


//    @Override
//    protected void onDestroy(){
//        super.onDestroy();
//        realm.close();
//    }

}
