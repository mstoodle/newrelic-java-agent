package com.newrelic.instrumentation.kotlin.coroutines

import com.newrelic.agent.introspec.InstrumentationTestConfig
import com.newrelic.agent.introspec.InstrumentationTestRunner
import com.newrelic.api.agent.Trace
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(InstrumentationTestRunner::class)
@InstrumentationTestConfig(includePrefixes = ["kotlinx", "com.newrelic.instrumentation"], configName = "distributed_tracing.yml")
class CoroutineTest {

    val cut = CoroutineClass()
    @Test
    fun testIt() {
        runKt()
        val introspector = InstrumentationTestRunner.getIntrospector()
//        Assert.assertEquals(1, introspector.getFinishedTransactionCount(3000).toLong())
        println(introspector.transactionNames)
        Assert.assertFalse(introspector.transactionNames.isEmpty())
    }

    @Trace(dispatcher = true)
    fun runKt() {
        println("Hi")
    }

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

