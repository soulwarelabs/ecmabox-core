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

import java.util.List;

import com.soulwarelabs.ecmabox.api.Sandbox;
import com.soulwarelabs.ecmabox.api.inspection.Inspector;
import com.soulwarelabs.ecmabox.api.invoice.Invoice;
import com.soulwarelabs.ecmabox.api.log.Record;
import com.soulwarelabs.ecmabox.api.result.Result;
import com.soulwarelabs.ecmabox.convention.Private;

/**
 * HtmlUnit-specific implementation of sandbox API.
 *
 * @see Sandbox
 *
 * @author Ilia Gubarev
 */
@Private
public final class HtmlUnitSandbox implements Sandbox {

    @Override
    public List<Record> drain() {
        // TODO: implement log records draining
        throw new UnsupportedOperationException();
    }

    @Override
    public Result execute(final Invoice invoice) {
        // TODO: implement invoice execution
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T inspect(final Inspector<T> inspector) {
        // TODO: implement sandbox inspection
        throw new UnsupportedOperationException();
    }
}
