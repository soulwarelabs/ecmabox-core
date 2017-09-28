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
package com.soulwarelabs.ecmabox.api.layout;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.soulwarelabs.ecmabox.api.log.RecordLevel;
import com.soulwarelabs.ecmabox.convention.Equivalent;
import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Sandbox logging layout configuration.
 *
 * @see RecordLevel
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
@Equivalent
public final class LogLayout {

    private final boolean enabled;
    private final RecordLevel level;

    /**
     * Creates a new logging leyout configuration.
     *
     * @param enabled <code>true</code> if logging is enabled.
     * @param level logging level threshold.
     *
     * @see RecordLevel
     */
    public LogLayout(final boolean enabled,
                     final RecordLevel level) {
        this.enabled = enabled;
        Objects.requireNonNull(level, "Log level cannot be null");
        this.level = level;
    }

    /**
     * Gets the logging flag - enabled or disabled.
     *
     * @return <code>true</code> if logging is enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Gets a logging level threshold.
     *
     * @return logging level threshold .
     *
     * @see RecordLevel
     */
    public RecordLevel getLevel() {
        return level;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final LogLayout logLayout = (LogLayout) object;
        return enabled == logLayout.enabled
                && level == logLayout.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled, level);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("enabled", enabled)
                .append("level", level)
                .toString();
    }
}
