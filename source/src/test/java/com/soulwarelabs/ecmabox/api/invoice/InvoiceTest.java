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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(Invoice.class)
@RunWith(PowerMockRunner.class)
public class InvoiceTest {

    private final String description = "This is a test invoice";
    private final boolean restricted = true;
    private final String script = "1 + 1";
    private final boolean timeoutEnabled = true;
    private final long timeoutInMilliseconds = 42;
    private final String version = "1.0";

    @Test
    public void createNewValidInstance() {
        final Invoice invoice = new Invoice(
                description,
                restricted,
                script,
                timeoutEnabled,
                timeoutInMilliseconds,
                version
        );
        Assert.assertEquals(description, invoice.getDescription());
        Assert.assertEquals(restricted, invoice.isRestricted());
        Assert.assertEquals(script, invoice.getScript());
        Assert.assertEquals(timeoutEnabled, invoice.isTimeoutEnabled());
        Assert.assertEquals(timeoutInMilliseconds, invoice.getTimeoutInMilliseconds());
        Assert.assertEquals(version, invoice.getVersion());
    }

    @Test
    public void createNewValidInstanceWithNullDescription() {
        final Invoice invoice = new Invoice(
                null,
                restricted,
                script,
                timeoutEnabled,
                timeoutInMilliseconds,
                version
        );
        Assert.assertNull(invoice.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateNewInstanceWithNegativeTimeout() {
        new Invoice(
                description,
                restricted,
                script,
                timeoutEnabled,
                -1,
                version
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullScript() {
        new Invoice(
                description,
                restricted,
                null,
                timeoutEnabled,
                timeoutInMilliseconds,
                version
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullVersion() {
        new Invoice(
                description,
                restricted,
                script,
                timeoutEnabled,
                timeoutInMilliseconds,
                null
        );
    }
}
