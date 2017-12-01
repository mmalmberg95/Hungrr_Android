package com.drake.android.cs188project3;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class finalPage extends AppCompatActivity {
private Button tryAgain;
private ImageView imageView;
private TextView cuisineText;
private ListView nearByList;
private ArrayList<Restaurant> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page);

        tryAgain = (Button) findViewById(R.id.tryAgain);
        nearByList = (ListView) findViewById(R.id.nearByList);
        imageView = (ImageView) findViewById(R.id.imageView);
        cuisineText = (TextView)findViewById(R.id.cuisineText);

        foodAdapter foodAdapter = new foodAdapter();

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), foodChoiceActivity.class);
                startActivity(intent);
            }
        });

        nearByList.setAdapter(foodAdapter);
    }

    class foodAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1;
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

//            name.setText("Test");
//            address.setText("Test Place");
//            address.setText("Test Number");

            return view;
        }
    }

        //public ArrayList<EatData>
    void checkDistance(int eventID, int maxDistance)
    {
        Location user =  new Location("user");
        Location event = new Location("event");

        //Location of Iowa Capitol Building
        user.setLatitude(41.6005448);
        user.setLongitude(-93.6091064);



        event.setLatitude(restaurantList.get(eventID).getLatitude());
        event.setLongitude(restaurantList.get(eventID).getLongitude());

        double distance = user.distanceTo(event); //meters to miles
//                /1609.39;

//            if (distance <= maxDistance) {
        //}

    }


}
