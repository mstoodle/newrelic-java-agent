package com.nr.tryit;

import com.newrelic.agent.introspec.InstrumentationTestConfig;
import com.newrelic.agent.introspec.InstrumentationTestRunner;
import com.newrelic.agent.introspec.Introspector;
import com.newrelic.api.agent.Trace;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(InstrumentationTestRunner.class)
@InstrumentationTestConfig(includePrefixes = {"kotlinx", "com.newrelic.instrumentation"}, configName = "distributed_tracing.yml")
public class TryJava {
    @Test
    public void testIt() {
        runIt();
        Introspector introspector = InstrumentationTestRunner.getIntrospector();
//        Assert.assertEquals(1, introspector.getFinishedTransactionCount(3000).toLong())
        System.out.println(introspector.getTransactionNames());
        Assert.assertFalse(introspector.getTransactionNames().isEmpty());
    }

    @Trace(dispatcher = true)
    void runIt() {
        System.out.println("Hi");
    }

}
