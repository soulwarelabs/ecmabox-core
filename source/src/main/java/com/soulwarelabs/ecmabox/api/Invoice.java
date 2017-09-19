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

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * ECMA script execution invoice.
 * <br/>
 * Contains all information required to execute the attached script.
 *
 * @see InvoiceBuilder
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
public final class Invoice {

    private final String description;
    private final boolean restricted;
    private final String script;
    private final boolean timeoutEnabled;
    private final long timeoutInMilliseconds;
    private final String version;

    /**
     * Creates a new invoice.
     *
     * @param description brief invoice description (optional).
     * @param restricted <code>true</code> if this invoice must be executed with restrictions applied.
     * @param script attached ECMA script to be executed.
     * @param timeoutEnabled <code>true</code> if this invoice execution is bound to a time limit.
     * @param timeoutInMilliseconds execution time limit value in milliseconds (non-negative).
     * @param version invoice API version for further back-compatibility.
     */
    public Invoice(final String description,
                   final boolean restricted,
                   final String script,
                   final boolean timeoutEnabled,
                   final long timeoutInMilliseconds,
                   final String version) {
        this.description = description;
        this.restricted = restricted;
        Objects.requireNonNull(script, "Execution script cannot be null");
        this.script = script;
        this.timeoutEnabled = timeoutEnabled;
        if (timeoutInMilliseconds < 0) {
            throw new IllegalArgumentException("Execution timeout value cannot be negative");
        }
        this.timeoutInMilliseconds = timeoutInMilliseconds;
        Objects.requireNonNull(version, "Execution version cannot be null");
        if (version.isEmpty()) {
            throw new IllegalArgumentException("Execution version cannot be empty");
        }
        this.version = version;
    }

    /**
     * Gets a brief description of this invoice (optional).
     *
     * @return invoice description or <code>null</code>.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if this invoice must be executed with restrictions applied.
     * <br/>
     * In most cases it means that execution has no access to potentially dangerous instructions.
     *
     * @return <code>true</code> if this invoice is restricted.
     */
    public boolean isRestricted() {
        return restricted;
    }

    /**
     * Gets the attached ECMA script to be executed.
     *
     * @return ECMA script.
     */
    public String getScript() {
        return script;
    }

    /**
     * Checks if this invoice execution is bound to a time limit.
     *
     * @return <code>true</code> if timeout is enabled.
     */
    public boolean isTimeoutEnabled() {
        return timeoutEnabled;
    }

    /**
     * Gets a value of a time limit for this execution in milliseconds (non-negative).
     *
     * @return timeout value in milliseconds.
     */
    public long getTimeoutInMilliseconds() {
        return timeoutInMilliseconds;
    }

    /**
     * Gets the version of this invoice to support its back-compatibility in future.
     *
     * @return invoice version.
     */
    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
