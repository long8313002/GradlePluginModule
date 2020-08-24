package com.zz.myapplication;

import android.app.Application;


import com.mb.base.Core;
import com.mb.moduleplugin.ModulePlugin;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ModulePlugin.INSTANCE.inject2(this);

        Core.init(this);
    }



}
