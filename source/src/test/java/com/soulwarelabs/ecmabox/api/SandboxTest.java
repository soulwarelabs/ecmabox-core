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
package com.soulwarelabs.ecmabox.api;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.api.inspection.Inspector;
import com.soulwarelabs.ecmabox.api.invoice.Invoice;
import com.soulwarelabs.ecmabox.api.invoice.InvoiceBuilder;
import com.soulwarelabs.ecmabox.api.log.Record;
import com.soulwarelabs.ecmabox.api.result.Result;
import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(Sandbox.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({Sandbox.class, InvoiceBuilder.class})
public class SandboxTest {

    private Sandbox sandbox;

    @Mock
    private InvoiceBuilder invoiceBuilderMock;

    @Before
    public void prepare() throws Exception {
        PowerMockito
                .mockStatic(InvoiceBuilder.class);
        PowerMockito
                .whenNew(InvoiceBuilder.class)
                .withNoArguments()
                .thenReturn(invoiceBuilderMock);
    }

    @Before
    public void prepareSandbox() throws Exception {
        sandbox = new Sandbox() {

            @Override
            public List<Record> drain() {
                return null;
            }

            @Override
            public Result execute(Invoice invoice) {
                return null;
            }

            @Override
            public <T> T inspect(Inspector<T> inspector) {
                return null;
            }
        };
    }

    @Test
    public void invokeInvoiceBuilderConstructor() throws Exception {
        final InvoiceBuilder builder = sandbox.invoiceBuilder();
        Assert.assertSame(invoiceBuilderMock, builder);
        PowerMockito.verifyNew(InvoiceBuilder.class);
    }
}
