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

import com.soulwarelabs.ecmabox.api.invoice.Invoice;
import com.soulwarelabs.ecmabox.api.invoice.InvoiceBuilder;
import com.soulwarelabs.ecmabox.api.log.Record;
import com.soulwarelabs.ecmabox.api.result.Result;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Sandbox API for ECMA script execution.
 *
 * @see Invoice
 * @see Record
 * @see Result
 * @see SandboxInspector
 *
 * @author Ilia Gubarev
 */
@Public
public interface Sandbox {

    /**
     * Drains (clears) a list of currently available log records.
     *
     * @return list of available log records.
     *
     * @see Record
     */
    List<Record> drain();

    /**
     * Executes a specified invoice.
     *
     * @param invoice ECMA execution invoice.
     * @return execution result.
     *
     * @see Invoice
     * @see Result
     */
    Result execute(Invoice invoice);

    /**
     * Performs an inspection of this sandbox with a specified inspector.
     *
     * @param inspector sandbox inspector.
     * @param <T> inspection result type.
     * @return inspection result.
     *
     * @see SandboxInspector
     */
    <T> T inspect(SandboxInspector<T> inspector);

    /**
     * Creates a new invoice builder.
     *
     * @return new invoice builder.
     *
     * @see InvoiceBuilder
     */
    default InvoiceBuilder invoiceBuilder() {
        return new InvoiceBuilder();
    }
}
