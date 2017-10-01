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
package com.soulwarelabs.ecmabox.autotest.blackbox.general.result;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.soulwarelabs.ecmabox.api.content.ContentType;
import com.soulwarelabs.ecmabox.autotest.convention.BlackboxTest;

@BlackboxTest
@RunWith(JUnit4.class)
public class NullResultTest extends ResultTestTemplate {

    @Test
    public void receiveContent() {
        executeInvoiceAndAssertResult();
    }

    @Override
    protected ContentType getExpectedType() {
        return ContentType.OBJECT;
    }

    @Override
    protected Object getExpectedValue() {
        return null;
    }

    @Override
    protected String getGivenScript() {
        return "null";
    }
}