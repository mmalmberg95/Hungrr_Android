package com.drake.android.cs188project3;

import android.content.res.Resources;

import java.io.InputStream;

import io.realm.Realm;

/**
 * Created by joely on 12/2/2017.
 */

public class RealmImporter {
    Resources resources;
    //TransactionTime transactionTime;

    public RealmImporter(Resources resources) {
        this.resources = resources;
    }

    public void importFromJson() {
        Realm realm = Realm.getDefaultInstance();

        // Transaction timer
        //transactionTime = new TransactionTime(System.currentTimeMillis());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                InputStream inputStream = resources.openRawResource(R.raw.food_data);
                try {
                    realm.createAllFromJson(FoodRealm.class, inputStream);
                    //transactionTime.setEnd(System.currentTimeMillis());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    realm.close();
                }
            }
        });
    }
}
