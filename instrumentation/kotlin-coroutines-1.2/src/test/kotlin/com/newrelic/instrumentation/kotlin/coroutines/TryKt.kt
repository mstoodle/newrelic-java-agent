package com.newrelic.instrumentation.kotlin.coroutines

import com.newrelic.agent.introspec.InstrumentationTestConfig
import com.newrelic.agent.introspec.InstrumentationTestRunner
import com.newrelic.api.agent.Trace
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(InstrumentationTestRunner::class)
@InstrumentationTestConfig(includePrefixes = ["kotlinx", "com.newrelic.instrumentation"], configName = "distributed_tracing.yml")
class TryKt {
    @Test
    fun testIt() {
        runIt()
        val introspector = InstrumentationTestRunner.getIntrospector()
        //        Assert.assertEquals(1, introspector.getFinishedTransactionCount(3000).toLong())
        println(introspector.transactionNames)
        Assert.assertFalse(introspector.transactionNames.isEmpty())
    }

    @Trace(dispatcher = true)
    fun runIt() {
        println("Hi")
    }


}