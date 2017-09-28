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

import com.soulwarelabs.ecmabox.convention.Equivalent;
import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Log record produced during an execution.
 *
 * @see RecordLevel
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
@Equivalent
public final class Record implements Comparable<Record> {

    private final String content;
    private final RecordLevel level;
    private final Instant when;

    /**
     * Create a new log record.
     *
     * @param content record text content.
     * @param level record level.
     * @param when record registration timestamp.
     *
     * @see RecordLevel
     */
    public Record(final String content,
                  final RecordLevel level,
                  final Instant when) {
        Objects.requireNonNull(content, "Log record content cannot be null");
        this.content = content;
        Objects.requireNonNull(level, "Log record level cannot be null");
        this.level = level;
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
     * Gets the level of this log record.
     *
     * @return log record level.
     *
     * @see RecordLevel
     */
    public RecordLevel getLevel() {
        return level;
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
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final Record record = (Record) object;
        return Objects.equals(content, record.content)
                && level == record.level
                && Objects.equals(when, record.when);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, level, when);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("content", content)
                .append("level", level)
                .append("when", when)
                .toString();
    }
}
