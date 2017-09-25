package com.soulwarelabs.ecmabox.autotest.blackbox.general;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.soulwarelabs.ecmabox.autotest.convention.BlackboxTest;

@BlackboxTest
@RunWith(JUnit4.class)
public class TimeoutLimitationTest {

    @Before
    public void prepare() throws Exception {

    }

    @Ignore
    @Test
    public void executeInvoiceWithinTimeout() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    public void failToExecuteInvoiceBecauseOfTimeout() throws Exception {
        // TODO: implement this test
    }
}
