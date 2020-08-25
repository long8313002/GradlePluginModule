package com.mb.mylibrary

import android.os.Bundle
import com.mb.base.BaseActivity
import com.mb.mylibrary.ToastUtil.toast

class TestActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_test)
        toast("000000")
    }

}