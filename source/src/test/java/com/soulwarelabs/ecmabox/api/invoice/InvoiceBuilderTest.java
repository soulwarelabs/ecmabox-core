/*
   Copyright 2017 Ilia Gubarev

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package com.soulwarelabs.ecmabox.api.invoice;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(InvoiceBuilder.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({Invoice.class, InvoiceBuilder.class})
public class InvoiceBuilderTest {

    private String description = "Test description";
    private boolean restricted = true;
    private String script = "1 + 1";
    private boolean timeoutEnabled = true;
    private long timeoutInMilliseconds = 42;
    private String version = "2.0";

    @Mock
    private Invoice invoiceMock;

    private InvoiceBuilder builder;

    @Before
    public void prepareBuilder() throws Exception {
        this.builder = new InvoiceBuilder();
    }

    @Before
    public void prepareInvoiceMock() throws Exception {
        PowerMockito
                .whenNew(Invoice.class)
                .withAnyArguments()
                .thenReturn(invoiceMock);
    }

    @Test
    public void invokeInvoiceConstructorWithCustomProperties() throws Exception {
        final Set<InvoiceRestriction> restrictions = Collections.singleton(InvoiceRestriction.EVAL);
        final Invoice invoice = builder
                .description(description)
                .restricted(restricted)
                .restriction(InvoiceRestriction.EVAL)
                .script(script)
                .timeoutEnabled(timeoutEnabled)
                .timeoutInMilliseconds(timeoutInMilliseconds)
                .version(version)
                .build();
        Assert.assertEquals(invoiceMock, invoice);
        PowerMockito
                .verifyNew(Invoice.class)
                .withArguments(
                        ArgumentMatchers.eq(description),
                        ArgumentMatchers.eq(restricted),
                        ArgumentMatchers.eq(restrictions),
                        ArgumentMatchers.eq(script),
                        ArgumentMatchers.eq(timeoutEnabled),
                        ArgumentMatchers.eq(timeoutInMilliseconds),
                        ArgumentMatchers.eq(version)
                );
    }

    @Test
    public void invokeInvoiceConstructorWhenCopyWithDefaultProperties() throws Exception {
        final String defaultScript = null;
        final Invoice invoice = builder.copy().build();
        Assert.assertEquals(invoiceMock, invoice);
        PowerMockito
                .verifyNew(Invoice.class)
                .withArguments(
                        ArgumentMatchers.eq(InvoiceBuilder.DEFAULT_DESCRIPTION),
                        ArgumentMatchers.eq(InvoiceBuilder.DEFAULT_RESTRICTED),
                        ArgumentMatchers.eq(new HashSet<>()),
                        ArgumentMatchers.eq(defaultScript),
                        ArgumentMatchers.eq(InvoiceBuilder.DEFAULT_TIMEOUT_ENABLED),
                        ArgumentMatchers.eq(InvoiceBuilder.DEFAULT_TIMEOUT_IN_MILLISECONDS),
                        ArgumentMatchers.eq(InvoiceBuilder.DEFAULT_VERSION)
                );
    }

    @Ignore
    @Test
    public void createCopy() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void createNewInstanceFromInvoice() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void failToAddNullRestriction() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceFromNullBuilder() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceFromNullInvoice() throws Exception {
        // TODO: implement this test
    }
}
