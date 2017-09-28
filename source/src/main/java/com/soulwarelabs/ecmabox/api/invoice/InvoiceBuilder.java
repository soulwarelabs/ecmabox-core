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
package com.soulwarelabs.ecmabox.api.invoice;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.soulwarelabs.ecmabox.convention.Builder;
import com.soulwarelabs.ecmabox.convention.Equivalent;
import com.soulwarelabs.ecmabox.convention.Nullable;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * ECMA script execution invoice builder.
 *
 * @see Invoice
 *
 * @author Ilia Gubarev
 */
@Public
@Builder(Invoice.class)
@Equivalent
public final class InvoiceBuilder {

    /**
     * Default invoice description.
     */
    public static final String DEFAULT_DESCRIPTION = "";

    /**
     * Default invoice restriction flag.
     */
    public static final boolean DEFAULT_RESTRICTED = false;

    /**
     * Default invoice execution timeout flag.
     */
    public static final boolean DEFAULT_TIMEOUT_ENABLED = false;

    /**
     * Default invoice execution timeout value in milliseconds.
     */
    public static final long DEFAULT_TIMEOUT_IN_MILLISECONDS = 1000;

    /**
     * Default invoice API version.
     */
    public static final String DEFAULT_VERSION = "1.0";

    private boolean restricted;
    private Set<InvoiceRestriction> restrictions;
    private String script;
    private boolean timeoutEnabled;
    private long timeoutInMilliseconds;
    private String version;

    @Nullable
    private String description;

    /**
     * Creates a new invoice builder.
     */
    public InvoiceBuilder() {
        this.description = DEFAULT_DESCRIPTION;
        this.restricted = DEFAULT_RESTRICTED;
        this.restrictions = new HashSet<>();
        this.script = null;
        this.timeoutEnabled = DEFAULT_TIMEOUT_ENABLED;
        this.timeoutInMilliseconds = DEFAULT_TIMEOUT_IN_MILLISECONDS;
        this.version = DEFAULT_VERSION;
    }

    /**
     * Creates a new invoice builder.
     *
     * @param invoice an existing invoice to be copied.
     *
     * @see Invoice
     */
    public InvoiceBuilder(final Invoice invoice) {
        Objects.requireNonNull(invoice, "Invoice cannot be null");
        this.description = invoice.getDescription();
        this.restricted = invoice.isRestricted();
        this.restrictions = new HashSet<>(invoice.getRestrictions());
        this.script = invoice.getScript();
        this.timeoutEnabled = invoice.isTimeoutEnabled();
        this.timeoutInMilliseconds = invoice.getTimeoutInMilliseconds();
        this.version = invoice.getVersion();
    }

    /**
     * Creates a new invoice builder.
     *
     * @param builder another invoice builder to be copied.
     */
    public InvoiceBuilder(final InvoiceBuilder builder) {
        Objects.requireNonNull(builder, "Invoice builder cannot be null");
        this.description = builder.description;
        this.restricted = builder.restricted;
        this.restrictions = new HashSet<>(builder.restrictions);
        this.script = builder.script;
        this.timeoutEnabled = builder.timeoutEnabled;
        this.timeoutInMilliseconds = builder.timeoutInMilliseconds;
        this.version = builder.version;
    }

    /**
     * Sets a brief invoice description (optional).
     *
     * @param description brief invoice description (optional).
     * @return this invoice builder.
     */
    public InvoiceBuilder description(final @Nullable String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets invoice restriction flag.
     *
     * @param restricted <code>true</code> if invoice execution must be restricted.
     * @return this invoice builder.
     */
    public InvoiceBuilder restricted(final boolean restricted) {
        this.restricted = restricted;
        return this;
    }

    /**
     * Adds a new execution restriction.
     *
     * @param restriction execution restriction to be applied.
     * @return this invoice builder.
     *
     * @see InvoiceRestriction
     */
    public InvoiceBuilder restriction(final InvoiceRestriction restriction) {
        Objects.requireNonNull(restriction, "Invoice restriction cannot be null");
        this.restrictions.add(restriction);
        return this;
    }

    /**
     * Sets invoice ECMA script.
     *
     * @param script ECMA script to be executed.
     * @return this invoice builder.
     */
    public InvoiceBuilder script(final String script) {
        this.script = script;
        return this;
    }

    /**
     * Sets invoice execution timeout flag.
     *
     * @param timeoutEnabled <code>true</code> if invoice execution timeout is enabled.
     * @return this invoice builder.
     */
    public InvoiceBuilder timeoutEnabled(final boolean timeoutEnabled) {
        this.timeoutEnabled = timeoutEnabled;
        return this;
    }

    /**
     * Sets invoice execution timeout value.
     *
     * @param timeoutInMilliseconds invoice execution time limit in milliseconds.
     * @return this invoice builder.
     */
    public InvoiceBuilder timeoutInMilliseconds(final long timeoutInMilliseconds) {
        this.timeoutInMilliseconds = timeoutInMilliseconds;
        return this;
    }

    /**
     * Sets invoice API version.
     *
     * @param version invoice API version.
     * @return this invoice builder.
     */
    public InvoiceBuilder version(final String version) {
        this.version = version;
        return this;
    }

    /**
     * Creates a new invoice.
     *
     * @return new invoice.
     */
    public Invoice build() {
        return new Invoice(
                description,
                restricted,
                restrictions,
                script,
                timeoutEnabled,
                timeoutInMilliseconds,
                version
        );
    }

    /**
     * Creates a copy of this builder.
     *
     * @return new invoice builder.
     */
    public InvoiceBuilder copy() {
        return new InvoiceBuilder(this);
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final InvoiceBuilder that = (InvoiceBuilder) object;
        return restricted == that.restricted
                && timeoutEnabled == that.timeoutEnabled
                && timeoutInMilliseconds == that.timeoutInMilliseconds
                && Objects.equals(restrictions, that.restrictions)
                && Objects.equals(script, that.script)
                && Objects.equals(version, that.version)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                restricted,
                restrictions,
                script,
                timeoutEnabled,
                timeoutInMilliseconds,
                version,
                description
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("restricted", restricted)
                .append("restrictions", restrictions)
                .append("script", script)
                .append("timeoutEnabled", timeoutEnabled)
                .append("timeoutInMilliseconds", timeoutInMilliseconds)
                .append("version", version)
                .append("description", description)
                .toString();
    }
}
