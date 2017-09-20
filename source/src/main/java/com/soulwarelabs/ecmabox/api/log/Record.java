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

import java.time.Instant;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Log record produced during an execution.
 *
 * @see RecordType
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
public final class Record implements Comparable<Record> {

    private final String content;
    private final RecordType type;
    private final Instant when;

    /**
     * Create a new log record.
     *
     * @param content record text content.
     * @param type record type (level).
     * @param when record registration timestamp.
     *
     * @see RecordType
     */
    public Record(final String content,
                  final RecordType type,
                  final Instant when) {
        Objects.requireNonNull(content, "Log record content cannot be null");
        this.content = content;
        Objects.requireNonNull(type, "Log record type cannot be null");
        this.type = type;
        Objects.requireNonNull(when, "Log record registration timestamp cannot be null");
        this.when = when;
    }

    /**
     * Gets the text content of this log record.
     *
     * @return log record content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the type (level) of this log record.
     *
     * @return log record type.
     *
     * @see RecordType
     */
    public RecordType getType() {
        return type;
    }

    /**
     * Gets a registration timestamp for this log record.
     *
     * @return log record timestamp.
     */
    public Instant getWhen() {
        return when;
    }

    @Override
    public int compareTo(final Record other) {
        Objects.requireNonNull(other, "Log record to compare cannot be null");
        return when.compareTo(other.when);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
