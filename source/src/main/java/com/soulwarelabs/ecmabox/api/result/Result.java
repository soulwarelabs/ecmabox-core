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
package com.soulwarelabs.ecmabox.api.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.soulwarelabs.ecmabox.api.invoice.Invoice;
import com.soulwarelabs.ecmabox.api.log.Record;
import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Invoice execution result.
 * <br/>
 * Contains a result of the specified invoice execution. Designed to be immutable.
 *
 * @see Invoice
 * @see Record
 * @see ResultContent
 * @see ResultTermination
 *
 * @author Ilia Gubarev
 */
@Public
@ImmutableByContract
public final class Result {

    private final ResultContent content;
    private final Invoice invoice;
    private final List<Record> records;
    private final ResultTermination termination;

    /**
     * Creates a new execution result.
     *
     * @param content invoice execution result content.
     * @param invoice original execution invoice.
     * @param records log records produced during execution of the invoice.
     * @param termination execution result termination type.
     *
     * @see Invoice
     * @see Record
     * @see ResultContent
     * @see ResultTermination
     */
    public Result(final ResultContent content,
                  final Invoice invoice,
                  final List<Record> records,
                  final ResultTermination termination) {
        Objects.requireNonNull(content, "Result content cannot be null");
        this.content = content;
        Objects.requireNonNull(invoice, "Execution invoice cannot be null");
        this.invoice = invoice;
        Objects.requireNonNull(records, "Execution log records cannot be null");
        this.records = new ArrayList<>(records);
        Objects.requireNonNull(termination, "Result termination type cannot be null");
        this.termination = termination;
    }

    /**
     * Gets the content of this execution result.
     *
     * @return execution result content.
     *
     * @see ResultContent
     */
    public ResultContent getContent() {
        return content;
    }

    /**
     * Gets the original execution invoice.
     *
     * @return execution invoice.
     *
     * @see Invoice
     */
    public Invoice getInvoice() {
        return invoice;
    }

    /**
     * Gets a list of log records which have been produced during execution of the invoice.
     *
     * @return list of log records.
     *
     * @see Record
     */
    public List<Record> getRecords() {
        return new ArrayList<>(records);
    }

    /**
     * Gets the type of invoice execution termination.
     *
     * @return termination type.
     *
     * @see ResultTermination
     */
    public ResultTermination getTermination() {
        return termination;
    }
}
