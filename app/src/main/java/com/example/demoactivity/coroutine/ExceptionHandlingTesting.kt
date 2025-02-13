package com.example.demoactivity.coroutine

import com.example.demoactivity.AppLogger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


object ExceptionHandlingTesting {
    fun testLaunch() {
        val scope = CoroutineScope(CoroutineName("ParentCoroutine"))
        AppLogger.logd("scope coroutineContext= ${scope.coroutineContext}")
        scope.launch {
            AppLogger.logd("coroutine name1  coroutineContext = ${this.coroutineContext}")
            try {
                launch {
                    AppLogger.logd("coroutine name2 coroutineContext = ${this.coroutineContext}")
                    throw RuntimeException("Uncaught Exception")
                }
            } catch (e: Exception) {
                AppLogger.logd("Exception = $e")
            }

        }
    }

    fun testCoroutineScope(){
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.message?.let { AppLogger.logd(it) }
        }
        val scope = CoroutineScope(CoroutineName("ParentCoroutine"))
        //AppLogger.log("scope coroutineContext= ${scope.coroutineContext}")
        scope.launch {
              coroutineScope {
                   AppLogger.logd("start first coroutine scope")
                   val job1 = launch {
                       val result = getResult(1)
                       AppLogger.logd("Job11 result = $result")
                   }
                   val job2 = launch {
                       val result = getResult(2)
                       AppLogger.logd("Job12 result = $result")
                  /*     try {
                           val result = getResult(2)
                           AppLogger.logd("Job12 result = $result")
                       }
                       catch (e: CancellationException) {
                           AppLogger.loge("Job12 cancellation exception caught")
                           throw e
                       }
                       catch (e: Exception) {
                           AppLogger.loge("Job12 normal exception caught")
                       }*/
                   }
                   val job3 = launch {
                       val result = getResult(3)
                       //ensureActive()
                       AppLogger.logd("Job13 result = $result")
                   }
                  //delay(400)
                  //job2.cancel()
               }
       /*     coroutineScope {
                AppLogger.logd("start second coroutine scope")
                val job1 = launch {
                    val result = getResult(1)
                    AppLogger.logd("Job21 result = $result")
                }
                val job2 = launch(exceptionHandler) {
                    val result = getResult(2)
                    AppLogger.logd("Job22 result = $result")
                }
                val job3 = launch {
                    val result = getResult(3)
                    AppLogger.logd("Job23 result = $result")
                }
            }*/

        }
    }


    fun testMultipleLaunch(){
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            throwable.message?.let { AppLogger.logd(it) }
        }
        val scope = CoroutineScope(CoroutineName("ParentCoroutine"))
        //AppLogger.log("scope coroutineContext= ${scope.coroutineContext}")
        scope.launch {
                AppLogger.logd("start first coroutine scope")
                val job1 = launch {
                    val result = getResult(1)
                    AppLogger.logd("Job11 result = $result")
                }
                val job2 = launch {
                    val result = getResult(2)
                    AppLogger.logd("Job12 result = $result")
                }
                val job3 = launch {
                    val result = getResult(3)
                    //ensureActive()
                    AppLogger.logd("Job13 result = $result")
                }
                delay(400)
                job2.cancel()
            }
        }
    }

    private suspend fun getResult(number: Int): Int {
        delay(500 * number.toLong())
        if (number == 2) {
            throw RuntimeException("Job$number got  exception ")
        }
        return number * 2
    }
