package com.example.demoactivity

import android.app.Application

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Hilt
        // Initialize Timber
       /* Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            // Custom handling of uncaught exceptions
            AppLogger.loge("UncaughtException")
            // Optionally restart the app, send a report, etc.
        }*/
    }
}