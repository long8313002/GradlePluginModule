package com.mb.moduleplugin

import android.app.Instrumentation
import android.content.Context

object ModulePlugin {


    fun inject(context: Context) {
        replaceActivityInstrumentationService(context)

        val packageContext: Context = context.createPackageContext(
            "com.mb.mylibrary.test"
            ,
            Context.CONTEXT_IGNORE_SECURITY or Context.CONTEXT_INCLUDE_CODE
        )
        replaceClassLoader(context.classLoader,packageContext.classLoader)

        replaceClassLoader(packageContext.classLoader,ProxyClassLoader(packageContext,packageContext.classLoader.parent))


    }

    @Throws(Exception::class)
    private fun replaceClassLoader(classLoader:ClassLoader,pClassLoader: ClassLoader){
        val declaredField = ClassLoader::class.java.getDeclaredField("parent")
        declaredField.isAccessible = true
        declaredField.set(classLoader,pClassLoader)
    }

    private  class ProxyClassLoader( packageContext: Context,parent:ClassLoader) :ClassLoader(parent){

        private val list = Utils.getClassName(packageContext)

        override fun findClass(name: String?): Class<*> {

            if(list.contains(name)){
                return super.findClass(name)
            }

            return try{
                val findClass = ClassLoader::class.java.getDeclaredMethod("findClass", String::class.java)
                findClass.isAccessible = true
                findClass.invoke(this.javaClass.classLoader,name) as Class<*>
            }catch (e:java.lang.Exception){
                throw ClassNotFoundException("")
            }
        }


    }



    private fun replaceActivityInstrumentationService(context: Context) {
        try {
            val aClass = Class.forName("android.app.ActivityThread")
            val currentActivityThread =
                aClass.getDeclaredMethod("currentActivityThread")
            currentActivityThread.isAccessible = true
            val activity = currentActivityThread.invoke(null)
            val mInstrumentationField =
                aClass.getDeclaredField("mInstrumentation")
            mInstrumentationField.isAccessible = true
            val mInstrumentation =
                mInstrumentationField[activity] as Instrumentation
            if (mInstrumentation != null) {
                val evilInstrumentation =
                    InstrumentationDelegate(mInstrumentation, context)
                mInstrumentationField[activity] = evilInstrumentation
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}