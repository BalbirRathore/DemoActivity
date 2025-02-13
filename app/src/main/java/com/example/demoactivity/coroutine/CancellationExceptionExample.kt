package com.example.demoactivity.coroutine

import com.example.demoactivity.AppLogger
import kotlinx.coroutines.*

fun main() = runBlocking {
    cancellationExceptionExampleWithPrintln()
}

class MyCancellationException(name: String) : CancellationException(name)

val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    throwable.message?.let { AppLogger.logd(it) }
}

// CancellationException is not propagated to parent coroutines even if they are thrown inside coroutineScope.
// If a child throw a CancellationException only that coroutine and its children are cancelled but other sibling and parent coroutines are not affected
fun cancellationExceptionExample() {
    val scope = CoroutineScope(Dispatchers.Unconfined)
    scope.launch(exceptionHandler) {
        launch {
            launch {
                delay(1000)
                AppLogger.logd("Parent -> Child1 -> Child1")
                throw CancellationException("my custom exception")
            }
            launch {
                delay(1500)
                AppLogger.logd("Parent->Child1 -> Child2")
            }
        }
        launch {
            delay(2000)
            AppLogger.logd("Parent->Child2 -> Child1")
        }
    }
}

suspend fun cancellationExceptionExampleWithPrintln() {
    coroutineScope {
        launch {
            launch {
                launch {
                    delay(1000)
                    AppLogger.logd("Parent -> Child1 -> Child1")
                    throw CancellationException("my custom exception")
                }
                launch {
                    delay(1500)
                    AppLogger.logd("Parent->Child1 -> Child2")
                }
            }
            launch {
                delay(2000)
                AppLogger.logd("Parent->Child2 -> Child1")
            }
        }
    }
}