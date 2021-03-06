package com.mb.moduleplugin;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class InstrumentationDelegate extends Instrumentation {

    private Instrumentation mProxy;
    private Context context;

    public InstrumentationDelegate(Instrumentation proxy, Context context) {
        mProxy = proxy;
        this.context = context;
    }


    @Override
    public void addMonitor(ActivityMonitor monitor) {
        mProxy.addMonitor(monitor);
    }

    @Override
    public ActivityMonitor addMonitor(IntentFilter filter, ActivityResult result, boolean block) {
        return mProxy.addMonitor(filter, result, block);
    }

    @Override
    public ActivityMonitor addMonitor(String cls, ActivityResult result, boolean block) {
        return mProxy.addMonitor(cls, result, block);
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
//        Log.e("pluginDemoN","InstrumentationDelegate <callActivityOnCreate> "+activity.getClass().getName());
        mProxy.callActivityOnCreate(activity, icicle);

    }

    @Override
    public void callActivityOnDestroy(Activity activity) {
        mProxy.callActivityOnDestroy(activity);

    }

    @Override
    public void callActivityOnNewIntent(Activity activity, Intent intent) {
        mProxy.callActivityOnNewIntent(activity, intent);

    }

    @Override
    public void callActivityOnPause(Activity activity) {
        mProxy.callActivityOnPause(activity);

    }

    @Override
    public void callActivityOnPostCreate(Activity activity, Bundle icicle) {
        mProxy.callActivityOnPostCreate(activity, icicle);
    }

    @Override
    public void callActivityOnRestart(Activity activity) {
        mProxy.callActivityOnRestart(activity);
    }

    @Override
    public void callActivityOnRestoreInstanceState(Activity activity, Bundle savedInstanceState) {
        mProxy.callActivityOnRestoreInstanceState(activity, savedInstanceState);
    }

    static WindowManager.LayoutParams layoutParams;

    @Override
    public void callActivityOnResume(Activity activity) {
        mProxy.callActivityOnResume(activity);

    }

    @Override
    public void callActivityOnSaveInstanceState(Activity activity, Bundle outState) {
        mProxy.callActivityOnSaveInstanceState(activity, outState);
    }

    @Override
    public void callActivityOnStart(Activity activity) {
        mProxy.callActivityOnStart(activity);

    }

    @Override
    public void callActivityOnStop(Activity activity) {
        mProxy.callActivityOnStop(activity);

    }

    @Override
    public void callActivityOnUserLeaving(Activity activity) {
        mProxy.callActivityOnUserLeaving(activity);
    }

    @Override
    public void callApplicationOnCreate(Application app) {
        mProxy.callApplicationOnCreate(app);
    }

    @Override
    public boolean checkMonitorHit(ActivityMonitor monitor, int minHits) {
        return mProxy.checkMonitorHit(monitor, minHits);
    }

    @Override
    public void endPerformanceSnapshot() {
        mProxy.endPerformanceSnapshot();
    }

    @Override
    public void finish(int resultCode, Bundle results) {
        mProxy.finish(resultCode, results);
    }

    @Override
    public Bundle getAllocCounts() {
        return mProxy.getAllocCounts();
    }

    @Override
    public Bundle getBinderCounts() {
        return mProxy.getBinderCounts();
    }

    @Override
    public ComponentName getComponentName() {
        return mProxy.getComponentName();
    }

    @Override
    public Context getContext() {
        return mProxy.getContext();
    }

    @Override
    public Context getTargetContext() {
        return mProxy.getTargetContext();
    }

    @Override
    public boolean invokeContextMenuAction(Activity targetActivity, int id, int flag) {
        return mProxy.invokeContextMenuAction(targetActivity, id, flag);
    }

    @Override
    public boolean invokeMenuActionSync(Activity targetActivity, int id, int flag) {
        return mProxy.invokeMenuActionSync(targetActivity, id, flag);
    }

    @Override
    public boolean isProfiling() {
        return mProxy.isProfiling();
    }

    @Override
    public Activity newActivity(Class<?> clazz, Context context, IBinder token, Application application, Intent intent, ActivityInfo info, CharSequence title, Activity parent, String id, Object lastNonConfigurationInstance)
            throws InstantiationException, IllegalAccessException {
        return mProxy.newActivity(clazz, context, token, application, intent, info, title, parent, id, lastNonConfigurationInstance);
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

        try {
            Context packageContext = context.createPackageContext("com.mb.mylibrary.test"
                    , Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE);

            Activity activity;
            try {
                activity = mProxy.newActivity(cl, className, intent);

                if (activity.getClass().getClassLoader() != cl) {
                    Field mResources = getField(ContextThemeWrapper.class, "mResources");
                    if(mResources!=null){
                        mResources.setAccessible(true);
                        mResources.set(activity, new ResourcesProxy(context.getResources(), packageContext.getResources()));
                    }
                }

                Field mTheme = getField(ContextThemeWrapper.class, "mTheme");
                if(mTheme!=null){
                    mTheme.setAccessible(true);
                    mTheme.set(activity, packageContext.getTheme());
                }

            } catch (Exception e) {
                activity = mProxy.newActivity(cl, className, intent);
            }
            return activity;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        return mProxy.newActivity(cl, className, intent);
    }

    private Field getField(Class clazz, String fieldName) {
        try {
            Method getDeclaredField = Class.class.getDeclaredMethod("getDeclaredField",String.class);
            getDeclaredField.setAccessible(true);
            return (Field) getDeclaredField.invoke(clazz, fieldName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {


        Application application = mProxy.newApplication(cl, className, context);

        return application;
    }

    @Override
    public void onCreate(Bundle arguments) {
        mProxy.onCreate(arguments);
    }

    @Override
    public void onDestroy() {
        mProxy.onDestroy();
    }

    @Override
    public boolean onException(Object obj, Throwable e) {
        e.printStackTrace();
        return mProxy.onException(obj, e);
    }

    @Override
    public void onStart() {
        mProxy.onStart();
    }

    @Override
    public void removeMonitor(ActivityMonitor monitor) {
        mProxy.removeMonitor(monitor);
    }

    @Override
    public void runOnMainSync(Runnable runner) {
        mProxy.runOnMainSync(runner);
    }

    @Override
    public void sendCharacterSync(int keyCode) {
        mProxy.sendCharacterSync(keyCode);
    }

    @Override
    public void sendKeyDownUpSync(int key) {
        mProxy.sendKeyDownUpSync(key);
    }

    @Override
    public void sendKeySync(KeyEvent event) {
        mProxy.sendKeySync(event);
    }

    @Override
    public void sendPointerSync(MotionEvent event) {
        mProxy.sendPointerSync(event);
    }

    @Override
    public void sendStatus(int resultCode, Bundle results) {
        mProxy.sendStatus(resultCode, results);
    }

    @Override
    public void sendStringSync(String text) {
        mProxy.sendStringSync(text);
    }

    @Override
    public void sendTrackballEventSync(MotionEvent event) {
        mProxy.sendTrackballEventSync(event);
    }

    @Override
    public void setAutomaticPerformanceSnapshots() {
        mProxy.setAutomaticPerformanceSnapshots();
    }

    @Override
    public void setInTouchMode(boolean inTouch) {
        mProxy.setInTouchMode(inTouch);
    }

    @Override
    public void start() {
        mProxy.start();
    }

    @Override
    public Activity startActivitySync(Intent intent) {
        return mProxy.startActivitySync(intent);
    }

    @Override
    public void startAllocCounting() {
        mProxy.startAllocCounting();
    }

    @Override
    public void startPerformanceSnapshot() {
        mProxy.startPerformanceSnapshot();
    }

    @Override
    public void startProfiling() {
        mProxy.startProfiling();
    }

    @Override
    public void stopAllocCounting() {
        mProxy.stopAllocCounting();
    }

    @Override
    public void stopProfiling() {
        mProxy.stopProfiling();
    }

    @Override
    public void waitForIdle(Runnable recipient) {
        mProxy.waitForIdle(recipient);
    }

    @Override
    public void waitForIdleSync() {
        mProxy.waitForIdleSync();
    }

    @Override
    public Activity waitForMonitor(ActivityMonitor monitor) {
        return mProxy.waitForMonitor(monitor);
    }

    @Override
    public Activity waitForMonitorWithTimeout(ActivityMonitor monitor, long timeOut) {
        return mProxy.waitForMonitorWithTimeout(monitor, timeOut);
    }
}
