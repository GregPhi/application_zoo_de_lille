package com.example.zoodelille;

import android.app.Application;

import com.example.zoodelille.data.di.DepencyInjector;
import com.facebook.stetho.Stetho;

public class ZooApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        DepencyInjector.setContext(this);
    }
}
