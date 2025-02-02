package com.example.demoactivity.coroutine

import com.example.demoactivity.AppLogger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlin.time.times


object ExceptionHandlingTesting {
    fun testLaunch() {
        val scope = CoroutineScope(CoroutineName("ParentCoroutine"))
        AppLogger.log("scope coroutineContext= ${scope.coroutineContext}")
        scope.launch {
            AppLogger.log("coroutine name1  coroutineContext = ${this.coroutineContext}")
            try {
                launch {
                    AppLogger.log("coroutine name2 coroutineContext = ${this.coroutineContext}")
                    throw RuntimeException("Uncaught Exception")
                }
            } catch (e: Exception) {
                AppLogger.log("Exception = $e")
            }

        }
    }

    fun testSuperVisorScope(){
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.message?.let { AppLogger.log(it) }
        }
        val scope = CoroutineScope(CoroutineName("ParentCoroutine"))
        //AppLogger.log("scope coroutineContext= ${scope.coroutineContext}")
        scope.launch {
            supervisorScope {
                val job1 = launch {
                    val result = getResult(1)
                    AppLogger.log("Job1 result = $result")
                }
                val job2 = launch(exceptionHandler) {
                    val result = getResult(2)
                    AppLogger.log("Job2 result = $result")
                }
                val job3 = launch {
                    val result = getResult(3)
                    AppLogger.log("Job3 result = $result")
                }
                //delay(900)
                //job2.cancel()
            }
        }
    }

    suspend fun getResult(number: Int): Int {
        delay(500 * number.toLong())
        if (number == 2) {
            throw RuntimeException("Job$number got  exception ")
        }
        return number * 2
    }
}