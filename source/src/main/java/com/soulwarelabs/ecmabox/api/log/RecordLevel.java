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
package com.soulwarelabs.ecmabox.api.log;

import java.util.Objects;

import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Equivalent;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Log record level.
 *
 * @see Record
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
@Equivalent
public enum RecordLevel {

    /**
     * The finest possible level of execution debug.
     */
    TRACE(100),

    /**
     * Execution debug information.
     */
    DEBUG(200),

    /**
     * Important execution information.
     */
    INFO(300),

    /**
     * Possible deviation from a normal execution.
     */
    WARN(400),

    /**
     * Execution error.
     */
    ERROR(500),

    /**
     * Execution error, most possibly unrecoverable.
     */
    FATAL(600);

    private final int precedence;

    RecordLevel(final int precedence) {
        this.precedence = precedence;
    }

    /**
     * Gets a precedence of this log record level.
     *
     * @return log record level precedence.
     */
    public int getPrecedence() {
        return precedence;
    }

    /**
     * Checks if this log record level has a lower precedence than another one.
     *
     * @param another log record level to compare.
     * @return <code>true</code> if this level has a lower precedence than another.
     */
    public boolean lowerThan(final RecordLevel another) {
        Objects.requireNonNull(another, "Log record level cannot be null");
        return precedence < another.precedence;
    }
}
