package com.example.demoactivity.coroutine

import com.example.demoactivity.AppLogger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val exceptionHandler1: CoroutineExceptionHandler =
    CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.message?.let {
            AppLogger.logd(it)
        }
    }

object AsyncTesting {
    fun testAsync() {
        val coroutineScope = CoroutineScope(Dispatchers.Default)
        coroutineScope.launch(exceptionHandler1) {
            AppLogger.logd("this==$this")
            AppLogger.logd("coroutineScopethis==$coroutineScope")
            val deferred1 = coroutineScope.async {
                AppLogger.logd("asnc1 this==$this")
                delay(1000)
                throw RuntimeException("Custom Exception")
            }
            val deferred2 = async {
                AppLogger.logd("asnc2 this==$this")
                delay(1500)
                AppLogger.logd("Second async")
            }
            awaitAll(deferred1, deferred2)
        }
        val  coroutineScope1 = CoroutineScope(Dispatchers.Default)
        coroutineScope1.launch{

        }
    }
}