package com.example.demoactivity

import android.util.Log

const val TAG = "AppLogger"
object AppLogger {
    fun log(message:String,tag:String = TAG) {
        println(message)
        Log.d(tag,message)
    }
}