package com.drake.android.cs188project3;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by joely on 12/2/2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Config Realm for the application
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("foodDatabase.realm");
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
