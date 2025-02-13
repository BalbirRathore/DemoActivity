package com.example.demoactivity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoactivity.AppLogger
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirstFragmentViewModel : ViewModel() {
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
