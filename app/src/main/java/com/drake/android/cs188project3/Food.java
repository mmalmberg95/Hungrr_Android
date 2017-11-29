package com.drake.android.cs188project3;

import android.graphics.Bitmap;
import android.media.Image;

/**
 * Created by Matthew on 11/25/2017.
 */

public class Food {
    private Bitmap foodImage;
    private String name;
    private String type;
    private String health;
    private String price;
    private String taste;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Bitmap getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(Bitmap foodImage) {
        this.foodImage = foodImage;
    }
}

//    BufferedReader reader = new BufferedReader(
//            new InputStreamReader(is, Charset.forName("UTF-8"))
//    );
//    //loop to read file
//    String line = "";
//        try {
////            reader.readLine();
//                while ((line = reader.readLine()) != null) {
//                //split by comma
//                //info is a just a identifier for the different splits
//                String[] info = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

//    EatData data = new EatData();
//                    data.setName(info[0]);
//                            data.setPrice(info[1]);
//                            data.setStars(Integer.parseInt(info[2]));
//                            data.setAddress(info[3]);
//                            data.setShortDesc(info[4]);
//                            data.setLongDesc(info[5]);
//                            data.setLatitude(Float.parseFloat(info[6]));
//                            data.setLongitude(Float.parseFloat(info[7]));
//                            data.setImageName(info[8]);


//        } catch (IOException e) {
//                //wtf = What a Terrible Failure
//                Log.wtf("OptionsList", "Error reading file at line " + line, e);
//                e.printStackTrace();
//                }