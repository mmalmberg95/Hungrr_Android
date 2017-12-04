package com.drake.android.cs188project3;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by joely on 12/2/2017.
 */

public class FoodRealm extends RealmObject {
    private String Food;
    private String Category;
    private String SweetOrSavory;
    private String PriceyOrCasual;
    private String GoodOrBad;
//    private String drawable;
//    private byte[] foodImage;
    private RealmList<FoodRealm> foodData;
    private RealmList<FoodRealm> available;

    public String getFood() {
        return Food;
    }

    public void setFood(String food) {
        Food = food;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getSweetOrSavory() {
        return SweetOrSavory;
    }

    public void setSweetOrSavory(String sweetOrSavory) {
        SweetOrSavory = sweetOrSavory;
    }

    public String getPriceyOrCasual() {
        return PriceyOrCasual;
    }

    public void setPriceyOrCasual(String priceyOrCasual) {
        PriceyOrCasual = priceyOrCasual;
    }

    public String getGoodOrBad() {
        return GoodOrBad;
    }

    public void setGoodOrBad(String goodOrBad) {
        GoodOrBad = goodOrBad;
    }

    public RealmList<FoodRealm> getFoodData() {
        return foodData;
    }

    public void setFoodData(RealmList<FoodRealm> foodData) {
        this.foodData = foodData;
    }

    public RealmList<FoodRealm> getAvailable() {

        return available;
    }

    public void setAvailable(RealmList<FoodRealm> available) {
        this.available = available;
    }
//
//    public byte[] getFoodImage() {
//        return foodImage;
//    }
//
//    public void setFoodImage(byte[] foodImage) {
//        this.foodImage = foodImage;
//    }
//
//    public String getDrawable() {
//        return drawable;
//    }
//
//    public void setDrawable(String drawable) {
//        this.drawable = drawable;
//    }
}
