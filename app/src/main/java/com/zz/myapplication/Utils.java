package com.zz.myapplication;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

public class Utils {

    public static  List<String> getClassName(Context context) {
        List<String> classNameList = new ArrayList<String>();
        try {

            DexFile df = new DexFile(context.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = (String) enumeration.nextElement();
                if(className.contains("com.mb"))
                classNameList.add(className);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classNameList;
    }

}
