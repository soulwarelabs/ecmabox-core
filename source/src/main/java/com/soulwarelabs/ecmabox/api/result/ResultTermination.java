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

import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Equivalent;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Invoice execution result termination type.
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
@Equivalent
public enum ResultTermination {

    /**
     * Execution has been terminated because it had produced an exception.
     */
    EXCEPTION(true),

    /**
     * Execution could not be successfully finished because of restrictions applied.
     */
    RESTRICTION(true),

    /**
     * Execution has been finished normally.
     */
    SUCCESS(false),

    /**
     * Execution has been terminated because it had reached its timeout.
     */
    TIMEOUT(true);

    private final boolean failure;

    ResultTermination(final boolean failure) {
        this.failure = failure;
    }

    /**
     * Checks if this termination has failure status.
     *
     * @return <code>true</code> if termination has failure status.
     */
    public boolean isFailure() {
        return failure;
    }
}
