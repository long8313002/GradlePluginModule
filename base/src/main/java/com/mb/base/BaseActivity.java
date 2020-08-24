package com.mb.base;

import android.app.Activity;

public class BaseActivity extends Activity {

    @Override
    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }
}
