package com.drake.android.cs188project3;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class finalPage extends AppCompatActivity {
private ImageButton foodReset;
private ImageButton startOver;
private ImageView imageView;
private TextView cuisineText;
private ListView nearByList;
private ArrayList<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page);

        Intent myIntent = getIntent();
        String category = myIntent.getStringExtra("type");

        InputStream is = getResources().openRawResource(R.raw.restaurant_data);

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

                Restaurant data = new Restaurant();
                data.setName(info[0]);
                data.setCategory(info[1]);
                data.setPrice(info[2]);
                data.setLatitude(Float.parseFloat(info[3]));
                data.setLongitude(Float.parseFloat(info[4]));
                data.setAddress(info[5]);
                data.setPhoneNumber(info[6]);


                restaurantList.add(data);

            }

        } catch (IOException e) {
            //wtf = What a Terrible Failure
            Log.wtf("OptionsList", "Error reading file at line " + line, e);
            e.printStackTrace();
        }

        for (int i=0; i <= restaurantList.size() - 1; i++){
            Restaurant current = restaurantList.get(i);

            if(current.getCategory() != category){
                restaurantList.remove(current);
            }
        }

        foodReset = (ImageButton) findViewById(R.id.FoodRoundReset);
        startOver = (ImageButton) findViewById(R.id.resetAllBtn);
        nearByList = (ListView) findViewById(R.id.nearByList);
        imageView = (ImageView) findViewById(R.id.cuisineImg);
        cuisineText = (TextView)findViewById(R.id.cuisineText);

        foodAdapter foodAdapter = new foodAdapter();

        foodReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), foodChoiceActivity.class);
                startActivity(intent);
            }
        });

        startOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), inOutActivity.class);
                startActivity(intent);
            }
        });

        nearByList.setAdapter(foodAdapter);
    }

    class foodAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return restaurantList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.restaurant_list,null);

            final ImageView image = (ImageView) findViewById(R.id.restaurantImage);
            final TextView name = (TextView) findViewById(R.id.name);
            final TextView address = (TextView) findViewById(R.id.address);
            final TextView phoneNumber = (TextView) findViewById(R.id.phoneNumber);


            name.setText(restaurantList.get(i).getName());
            address.setText(restaurantList.get(i).getAddress());
            phoneNumber.setText(restaurantList.get(i).getPhoneNumber());

            return view;
        }
    }

}
