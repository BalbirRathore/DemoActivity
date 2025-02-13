package com.example.demoactivity.coroutine

import androidx.lifecycle.viewModelScope
import com.example.demoactivity.AppLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object TestSuperVisorJob {
    private val viewModelScope  = CoroutineScope(Dispatchers.Main+ SupervisorJob())
    fun testViewModel() {
        viewModelScope.launch {
            AppLogger.logd("start first coroutine")
            delay(1000)
            throw RuntimeException("Error in first coroutine")
        }
        viewModelScope.launch {
            AppLogger.logd("start second coroutine")
            delay(2000)
            AppLogger.logd("execute successfully second coroutine")
        }
    }
}