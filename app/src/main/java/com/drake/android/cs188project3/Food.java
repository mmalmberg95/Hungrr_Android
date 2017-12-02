package com.drake.android.cs188project3;

import android.media.Image;

import io.realm.RealmObject;

/**
 * Created by Matthew on 11/25/2017.
 */

public class Food {
    private Image foodImage;
    private Integer tag;

    public Image getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(Image foodImage) {
        this.foodImage = foodImage;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

}
