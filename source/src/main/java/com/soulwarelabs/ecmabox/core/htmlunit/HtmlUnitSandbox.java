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
package com.soulwarelabs.ecmabox.core.htmlunit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.TimeoutError;

import com.soulwarelabs.ecmabox.api.Sandbox;
import com.soulwarelabs.ecmabox.api.content.Content;
import com.soulwarelabs.ecmabox.api.inspection.Inspector;
import com.soulwarelabs.ecmabox.api.invoice.Invoice;
import com.soulwarelabs.ecmabox.api.layout.Layout;
import com.soulwarelabs.ecmabox.api.log.Record;
import com.soulwarelabs.ecmabox.api.result.Result;
import com.soulwarelabs.ecmabox.api.result.ResultTermination;
import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.core.content.parser.ContentParser;

/**
 * HtmlUnit-specific implementation of sandbox API.
 *
 * @see Sandbox
 *
 * @author Ilia Gubarev
 */
@Private
public final class HtmlUnitSandbox implements Sandbox {

    private final Layout layout;
    private final WebClient client;
    private final HtmlPage page;
    private final ContentParser parser;
    private final List<Record> recordsToDrain;
    private final HtmlUnitRecordStorage recordStorage;

    public HtmlUnitSandbox(final Layout layout,
                           final WebClient client,
                           final HtmlPage page,
                           final HtmlUnitRecordStorage recordStorage) {
        Objects.requireNonNull(client, "Sandbox layout cannot be null");
        Objects.requireNonNull(client, "Sandbox web client cannot be null");
        Objects.requireNonNull(client, "Sandbox web page cannot be null");
        Objects.requireNonNull(client, "Sandbox record storage cannot be null");
        this.layout = layout;
        this.client = client;
        this.page = page;
        this.parser = HtmlUnitContentParser.getInstance();
        this.recordsToDrain = new ArrayList<>();
        this.recordStorage = recordStorage;
    }

    @Override
    public Layout getLayout() {
        return layout;
    }

    @Override
    public List<Record> drain() {
        final List<Record> result = new ArrayList<>(recordsToDrain);
        recordsToDrain.clear();
        return result;
    }

    @Override
    public Result execute(final Invoice invoice) {
        Objects.requireNonNull(invoice, "Execution invoice cannot be null");
        final List<Record> currentRecords = new ArrayList<>();
        try {
            recordStorage.register(currentRecords::add);
            setupTimeout(invoice);
            final ScriptResult scriptResult = this.page.executeJavaScript(invoice.getScript());
            final Content content = parser.parse(scriptResult.getJavaScriptResult());
            final ResultTermination termination = ResultTermination.SUCCESS;
            return new Result(content, invoice, currentRecords, termination);
        } catch (RuntimeException e) {
            client.getJavaScriptEngine().shutdown();
            final ResultTermination termination = terminationType(e);
            return new Result(Content.undefined(), invoice, currentRecords, termination);
        }
    }

    @Override
    public <T> T inspect(final Inspector<T> inspector) {
        Objects.requireNonNull(inspector, "Sandbox inspector cannot be null");
        return inspector.apply(page, client, recordStorage);
    }

    private void setupTimeout(final Invoice invoice) {
        if (invoice.isTimeoutEnabled()) {
            client.getJavaScriptEngine().setJavaScriptTimeout(invoice.getTimeoutInMilliseconds());
        } else {
            client.getJavaScriptEngine().setJavaScriptTimeout(0);
        }
    }

    private ResultTermination terminationType(final Throwable throwable) {
        final Throwable cause = throwable.getCause();
        if (cause instanceof TimeoutError) {
            return ResultTermination.TIMEOUT;
        }
        return ResultTermination.EXCEPTION;
    }
}
