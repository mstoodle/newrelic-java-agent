package com.newrelic.instrumentation.kotlin.coroutines

import com.newrelic.api.agent.Trace
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CoroutineClass {
    @Trace(dispatcher = true)
    fun runIt() = runBlocking  {
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
        println("Hello") // main coroutine continues while a previous one is delayed
    }

    suspend fun doSomethingUsefulOne(): Int {
        delay(1000L) // pretend we are doing something useful here
        return 13
    }

    suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L) // pretend we are doing something useful here, too
        return 29
    }
}
