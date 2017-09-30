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
package com.soulwarelabs.ecmabox.autotest.blackbox;

import org.junit.Assert;

import com.soulwarelabs.ecmabox.api.Sandbox;
import com.soulwarelabs.ecmabox.api.SandboxFactory;
import com.soulwarelabs.ecmabox.api.SandboxFactoryProvider;
import com.soulwarelabs.ecmabox.api.content.ContentType;
import com.soulwarelabs.ecmabox.api.invoice.Invoice;
import com.soulwarelabs.ecmabox.api.invoice.InvoiceBuilder;
import com.soulwarelabs.ecmabox.api.layout.Layout;
import com.soulwarelabs.ecmabox.api.layout.LayoutBuilder;
import com.soulwarelabs.ecmabox.api.result.Result;
import com.soulwarelabs.ecmabox.api.result.ResultTermination;

/**
 * Common implementation of a black-box test class.
 *
 * @author Ilia Gubarev
 */
public abstract class BlackboxTestTemplate {

    /**
     * Asserts an expected result termination.
     *
     * @param result given execution result.
     * @param termination expected result termination
     */
    protected void assertTermination(final Result result, final ResultTermination termination) {
        Assert.assertEquals(termination, result.getTermination());
    }

    /**
     * Asserts successful result termination.
     *
     * @param result given execution result.
     */
    protected void assertTerminationSuccess(final Result result) {
        assertTermination(result, ResultTermination.SUCCESS);
    }

    /**
     * Asserts an expected content type of a given execution result.
     *
     * @param result given execution result.
     * @param type expected content type.
     */
    protected void assertType(final Result result, final ContentType type) {
        Assert.assertEquals(type, result.getContent().getType());
    }

    /**
     * Asserts an expected content value of a given execution result.
     *
     * @param result given execution result.
     * @param value expected content value.
     */
    protected void assertValue(final Result result, final Object value) {
        Assert.assertEquals(value, result.getContent().getValue());
    }

    /**
     * Asserts a non-null value of a given execution result.
     *
     * @param result given execution result.
     */
    protected void assertValueNotNull(final Result result) {
        Assert.assertNotNull(result.getContent().getValue());
    }

    /**
     * Asserts a null value of a given execution result.
     *
     * @param result given execution result.
     */
    protected void assertValueNull(final Result result) {
        Assert.assertNull(result.getContent().getValue());
    }

    /**
     * Creates a new sandbox with a default implementation.
     *
     * @return new sandbox.
     */
    protected Sandbox sandbox() {
        final SandboxFactory factory = SandboxFactoryProvider.factory();
        final Layout layout = layout();
        return factory.create(layout);
    }

    /**
     * Creates a new default layout.
     *
     * @return new layout.
     */
    protected Layout layout() {
        return new LayoutBuilder().build();
    }

    /**
     * Creates and executes a simple invoice.
     *
     * @param sandbox target sandbox for execution.
     * @param script invoice script.
     * @return invoice execution result.
     */
    protected Result execute(final Sandbox sandbox, final String script) {
        final Invoice invoice = invoice(script);
        return sandbox.execute(invoice);
    }

    /**
     * Creates a new simple invoice.
     *
     * @param script invoice script.
     * @return new invoice.
     */
    protected Invoice invoice(final String script) {
        return new InvoiceBuilder()
                .script(script)
                .build();
    }
}
