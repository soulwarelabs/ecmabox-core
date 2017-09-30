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
package com.soulwarelabs.ecmabox.autotest.blackbox.general;

import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.soulwarelabs.ecmabox.api.content.ContentType;
import com.soulwarelabs.ecmabox.api.result.Result;
import com.soulwarelabs.ecmabox.autotest.blackbox.BlackboxTestTemplate;
import com.soulwarelabs.ecmabox.autotest.convention.BlackboxTest;

@BlackboxTest
@RunWith(JUnit4.class)
public class ContentTypeTest extends BlackboxTestTemplate {

    @Ignore
    @Test
    public void receiveArrayOfNumbers() throws Exception {
        final String givenScript = "[1, 2, 3]";
        final ContentType expectedType = ContentType.ARRAY;
        final Object expectedValue = Arrays.asList(1.0, 2.0, 3.0);
        executeAndAssert(givenScript, expectedType, expectedValue);
    }

    @Ignore
    @Test
    public void executeInvoiceAndReceiveArrayOfComplexObjects() throws Exception {
        final Result result = execute(sandbox(), "[{p: 1}, {p: 2}, {p: 3}]");
        assertTerminationSuccess(result);
        assertType(result, ContentType.ARRAY);
        assertValue(result, Arrays.asList(1.0, 1.0, 2.0, 3.0, 5.0));
    }

    @Ignore
    @Test
    public void executeInvoiceAndReceiveBoolean() throws Exception {
        final Result result = execute(sandbox(), "1 === 1");
        assertTerminationSuccess(result);
        assertType(result, ContentType.BOOLEAN);
        assertValue(result, true);
    }

    @Ignore
    @Test
    public void executeInvoiceAndReceiveComplexObject() throws Exception {
        // TODO: implement this test
    }

    @Ignore
    @Test
    public void executeInvoiceAndReceiveFunction() throws Exception {
        final Result result = execute(sandbox(), "function () {}");
        assertTerminationSuccess(result);
        assertType(result, ContentType.FUNCTION);
        assertValueNotNull(result);
    }

    @Ignore
    @Test
    public void executeInvoiceAndReceiveNullObject() throws Exception {
        final Result result = execute(sandbox(), "null");
        assertTerminationSuccess(result);
        assertType(result, ContentType.OBJECT);
        assertValueNull(result);
    }

    @Ignore
    @Test
    public void executeInvoiceAndReceiveNumber() throws Exception {
        final Result result = execute(sandbox(), "40 + 2");
        assertTerminationSuccess(result);
        assertType(result, ContentType.NUMBER);
        assertValue(result, 42.0);
    }

    @Ignore
    @Test
    public void executeInvoiceAndReceiveString() throws Exception {
        final Result result = execute(sandbox(), "'test' +  ' ' + 'string'");
        assertTerminationSuccess(result);
        assertType(result, ContentType.STRING);
        assertValue(result,"test string");
    }

    @Ignore
    @Test
    public void executeInvoiceAndReceiveUndefined() throws Exception {
        final Result result = execute(sandbox(), "var x = {}; x.p");
        assertTerminationSuccess(result);
        assertType(result, ContentType.UNDEFINED);
        assertValueNull(result);
    }

    private void executeAndAssert(final String givenScript,
                                  final ContentType expectedType,
                                  final Object expectedValue) {
        final Result result = execute(sandbox(), givenScript);
        assertTerminationSuccess(result);
        assertType(result, expectedType);
        assertValue(result, expectedValue);
    }
}
