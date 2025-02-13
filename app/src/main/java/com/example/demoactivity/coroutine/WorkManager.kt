package com.example.demoactivity.coroutine

import com.example.demoactivity.AppLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class WorkManager {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    fun doWork1() {
        scope.launch {
           AppLogger.logd("do work 1")
        }
    }

    fun doWork2() {
        scope.launch {
            AppLogger.logd("do work 2")
        }
    }

    fun cancelAllWork() {
        //job.cancelChildren()
        scope.coroutineContext.cancelChildren()
    }
}

fun main() {
    val workManager = WorkManager()

    workManager.doWork1()
    workManager.doWork2()
    workManager.cancelAllWork()
    workManager.doWork1() // (1)
}