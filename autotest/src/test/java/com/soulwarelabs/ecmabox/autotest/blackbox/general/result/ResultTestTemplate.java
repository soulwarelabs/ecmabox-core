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

import com.soulwarelabs.ecmabox.api.content.ContentType;
import com.soulwarelabs.ecmabox.api.result.Result;
import com.soulwarelabs.ecmabox.autotest.blackbox.BlackboxTestTemplate;

/**
 * Common template of a result-related blackbox test
 *
 * @see BlackboxTestTemplate
 *
 * @author Ilia Gubarev
 */
public abstract class ResultTestTemplate extends BlackboxTestTemplate {

    /**
     * Executes an invoice and asserts its execution result.
     */
    protected void executeInvoiceAndAssertResult() {
        final Result result = execute(sandbox(), getGivenScript());
        assertTerminationSuccess(result);
        assertType(result, getExpectedType());
        assertValue(result, getExpectedValue());
    }

    /**
     * Gets an expected result content type.
     *
     * @return expected result type.
     *
     * @see ContentType
     */
    protected abstract ContentType getExpectedType();

    /**
     * Gets an expected result content value.
     *
     * @return expected result value.
     */
    protected abstract Object getExpectedValue();

    /**
     * Gets a given invoice script to be executed.
     *
     * @return given script.
     */
    protected abstract String getGivenScript();
}
