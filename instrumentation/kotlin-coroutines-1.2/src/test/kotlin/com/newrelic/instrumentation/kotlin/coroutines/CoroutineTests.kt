package com.newrelic.instrumentation.kotlin.coroutines

import com.newrelic.agent.introspec.InstrumentationTestConfig
import com.newrelic.agent.introspec.InstrumentationTestRunner
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(InstrumentationTestRunner::class)
@InstrumentationTestConfig(includePrefixes = ["kotlinx", "com.newrelic.instrumentation"], configName = "distributed_tracing.yml")
class CoroutineTests {

    val cut = CoroutineClass()
    @Test
    fun testIt() {
        val introspector = InstrumentationTestRunner.getIntrospector()
        cut.runIt()
//        Assert.assertEquals(1, introspector.getFinishedTransactionCount(3000).toLong())
        Assert.assertTrue(introspector.transactionNames.contains("OtherTransaction/Spring/ (GET)"))
    }
}

