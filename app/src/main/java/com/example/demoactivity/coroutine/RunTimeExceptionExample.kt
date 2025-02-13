package com.example.demoactivity.coroutine

import com.example.demoactivity.AppLogger
import kotlinx.coroutines.*

fun main() {
    //cancellationExceptionExample()
    runTimeExceptionExample()
}

class RunTimeExceptionExample(name: String) : CancellationException(name)



// CancellationException is not propagated to parent coroutines even if they are thrown inside coroutineScope.
// If a child throw a CancellationException only that coroutine and its children are cancelled but other sibling and parent coroutines are not affected
fun runTimeExceptionExample() {
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.message?.let { AppLogger.logd(it) }
    }
    val scope = CoroutineScope(Dispatchers.Unconfined)
    scope.launch(exceptionHandler) {
        launch {
            launch {
                delay(1000)
                AppLogger.logd("Parent -> Child1 -> Child1")
                throw RuntimeException("my custom exception")
            }
            launch {
                delay(1500)
                AppLogger.logd("Parent -> Child1 -> Child2")
            }
        }
        launch {
            delay(2000)
            AppLogger.logd("Parent -> Child2 -> Child1")
        }
    }
}