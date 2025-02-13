package com.example.demoactivity

import android.util.Log

const val TAG = "AppLogger"
object AppLogger {
    fun logd(message:String, tag:String = TAG) {
        //println(Thread.currentThread().name)
        //println(message)
        Log.d(tag,message)
    }

    fun loge(message:String, tag:String = TAG) {
        //println(message)
        Log.e(tag,message)
    }
}