package com.mb.mylibrary

import android.os.Bundle
import com.mb.base.BaseActivity
import com.mb.mylibrary.ToastUtil.toast
import java.lang.Exception
import java.lang.reflect.Field

class TestActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_test)
        toast("777777${getMTheme(javaClass)}")
    }

    public fun getString():String{
        return "555555"
    }

    private fun getMTheme(clazz:Class<in Any>?):Field?{
        if(clazz == null) return null
        return try {
           getDeclaredField(clazz,"mTheme")
        }catch (e:Exception){
            getMTheme(clazz.superclass)
        }
    }

    private fun getDeclaredField(clazz:Class<in Any>?,name:String):Field{
        val declaredMethod =
            Class::class.java.getDeclaredMethod("getDeclaredField", String::class.java)
        declaredMethod.isAccessible = true
        return declaredMethod.invoke(clazz,name) as Field
    }
}