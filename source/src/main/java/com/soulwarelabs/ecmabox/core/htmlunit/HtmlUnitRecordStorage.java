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

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import com.gargoylesoftware.htmlunit.WebConsole.Logger;

import com.soulwarelabs.ecmabox.api.layout.LogLayout;
import com.soulwarelabs.ecmabox.api.log.Record;
import com.soulwarelabs.ecmabox.api.log.RecordLevel;
import com.soulwarelabs.ecmabox.convention.Private;

/**
 * HtmlUnit logger implementation for log records interception.
 *
 * @see Logger
 * @see Record
 *
 * @author Ilia Gubarev
 */
@Private
public final class HtmlUnitRecordStorage implements Logger {

    private final Set<Consumer<Record>> consumers = new HashSet<>();
    private final LogLayout logLayout;
    private final List<Record> records = new ArrayList<>();

    /**
     * Creates a new instance of record storage.
     *
     * @param logLayout logging configuration.
     *
     * @see LogLayout
     */
    public HtmlUnitRecordStorage(final LogLayout logLayout) {
        Objects.requireNonNull(logLayout, "Log layout cannot be null");
        this.logLayout = logLayout;
    }

    @Override
    public boolean isTraceEnabled() {
        return enabledForType(RecordLevel.TRACE);
    }

    @Override
    public void trace(final Object message) {
        accept(message, RecordLevel.TRACE);
    }

    @Override
    public boolean isDebugEnabled() {
        return enabledForType(RecordLevel.DEBUG);
    }

    @Override
    public void debug(final Object message) {
        accept(message, RecordLevel.DEBUG);
    }

    @Override
    public boolean isInfoEnabled() {
        return enabledForType(RecordLevel.INFO);
    }

    @Override
    public void info(final Object message) {
        accept(message, RecordLevel.INFO);
    }

    @Override
    public boolean isWarnEnabled() {
        return enabledForType(RecordLevel.WARN);
    }

    @Override
    public void warn(final Object message) {
        accept(message, RecordLevel.WARN);
    }

    @Override
    public boolean isErrorEnabled() {
        return enabledForType(RecordLevel.ERROR);
    }

    @Override
    public void error(final Object message) {
        accept(message, RecordLevel.ERROR);
    }

    /**
     * Gets a list of stored records.
     *
     * @return stored records.
     *
     * @see Record
     */
    public List<Record> getRecords() {
        return new ArrayList<>(records);
    }

    /**
     * Registers a new record consumer
     *
     * @param consumer new consumer.
     *
     * @see Record
     */
    public void register(final Consumer<Record> consumer) {
        requireNonNullConsumer(consumer);
        consumers.add(consumer);
    }

    /**
     * Unregisters an existing record consumer
     *
     * @param consumer existing consumer.
     *
     * @see Record
     */
    public void unregister(final Consumer<Record> consumer) {
        requireNonNullConsumer(consumer);
        consumers.remove(consumer);
    }

    private void accept(final Object message, final RecordLevel level) {
        if (message == null) {
            return;
        }
        final Record record = new Record(message.toString(), level, Instant.now());
        records.add(record);
        consume(record);
    }

    private void consume(final Record record) {
        for (final Consumer<Record> consumer : consumers) {
            consumer.accept(record);
        }
    }

    private boolean enabledForType(final RecordLevel level) {
        return logLayout.isEnabled() && !level.lowerThan(logLayout.getLevel());
    }

    private void requireNonNullConsumer(final Consumer<Record> consumer) {
        Objects.requireNonNull(consumer, "Log record consumer cannot be null");
    }
}
