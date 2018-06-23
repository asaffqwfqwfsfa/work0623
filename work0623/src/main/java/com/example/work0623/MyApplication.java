package com.example.work0623;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by john on 2018/6/23.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
