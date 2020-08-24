package com.mb.base;

import android.content.Context;

public class Core {

    private static Context context;

    public static void init(Context context){

        Core.context = context;
    }

    public static Context getContext() {
        return context;
    }
}
