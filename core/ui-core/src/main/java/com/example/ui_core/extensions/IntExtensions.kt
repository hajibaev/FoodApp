package com.example.ui_core.extensions

import android.content.res.Resources

val Int.toDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

