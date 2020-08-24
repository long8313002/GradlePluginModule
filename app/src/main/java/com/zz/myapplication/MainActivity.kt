package com.zz.myapplication

import android.content.Intent
import android.os.Bundle
import com.example.gradlepluginmodule.R
import com.mb.base.BaseActivity
import com.mb.mylibrary.TestActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvClick.setOnClickListener {
            startActivity(Intent(this,TestActivity::class.java))
        }

    }
}
