package com.zz.myapplication;

import android.app.Application;
import android.app.Instrumentation;
import android.content.res.AssetManager;
import android.content.res.Resources;


import com.mb.base.Core;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Core.init(this);

        replaceActivityInstrumentationService();
    }

    public void replaceActivityInstrumentationService() {
        try {

            Class<?> aClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = aClass.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);

            Object activity = currentActivityThread.invoke(null, null);


            Field mInstrumentationField = aClass.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);
            Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(activity);
            if (mInstrumentation != null) {
                com.zz.myapplication.InstrumentationDelegate evilInstrumentation = new com.zz.myapplication.InstrumentationDelegate(mInstrumentation, this);
                mInstrumentationField.set(activity, evilInstrumentation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void mergePluginResources(Application application, File optDexFile)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 创建一个新的 AssetManager 对象
        AssetManager newAssetManagerObj = AssetManager.class.newInstance();
        Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
        // 塞入原来宿主的资源
        addAssetPath.invoke(newAssetManagerObj, application.getBaseContext().getPackageResourcePath());
        // 塞入插件的资源
        addAssetPath.invoke(newAssetManagerObj, optDexFile.getAbsolutePath());

        // ----------------------------------------------

        // 创建一个新的 Resources 对象
        Resources newResourcesObj = new Resources(newAssetManagerObj,
                application.getBaseContext().getResources().getDisplayMetrics(),
                application.getBaseContext().getResources().getConfiguration());

        // ----------------------------------------------

        // 获取 ContextImpl 中的 Resources 类型的 mResources 变量，并替换它的值为新的 Resources 对象
        Field resourcesField = application.getBaseContext().getClass().getDeclaredField("mResources");
        resourcesField.setAccessible(true);
        resourcesField.set(application.getBaseContext(), newResourcesObj);

        // ----------------------------------------------

        // 获取 ContextImpl 中的 LoadedApk 类型的 mPackageInfo 变量
        Field packageInfoField = application.getBaseContext().getClass().getDeclaredField("mPackageInfo");
        packageInfoField.setAccessible(true);
        Object packageInfoObj = packageInfoField.get(application.getBaseContext());

        // 获取 mPackageInfo 变量对象中类的 Resources 类型的 mResources 变量，，并替换它的值为新的 Resources 对象
        // 注意：这是最主要的需要替换的，如果不需要支持插件运行时更新，只留这一个就可以了
        Field resourcesField2 = packageInfoObj.getClass().getDeclaredField("mResources");
        resourcesField2.setAccessible(true);
        resourcesField2.set(packageInfoObj, newResourcesObj);

        // ----------------------------------------------

        // 获取 ContextImpl 中的 Resources.Theme 类型的 mTheme 变量，并至空它
        // 注意：清理mTheme对象，否则通过inflate方式加载资源会报错, 如果是activity动态加载插件，则需要把activity的mTheme对象也设置为null
        Field themeField = application.getBaseContext().getClass().getDeclaredField("mTheme");
        themeField.setAccessible(true);
        themeField.set(application.getBaseContext(), null);
    }


}
