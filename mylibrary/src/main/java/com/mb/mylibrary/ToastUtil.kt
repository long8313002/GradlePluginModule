package com.mb.mylibrary

import android.widget.Toast
import com.mb.base.Core

object ToastUtil {
    fun toast(message: String?) {
        Toast.makeText(Core.getContext(), message, Toast.LENGTH_SHORT).show()
    }
}