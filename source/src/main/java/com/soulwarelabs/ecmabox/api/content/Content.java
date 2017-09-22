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
package com.soulwarelabs.ecmabox.api.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Public;
import com.soulwarelabs.ecmabox.utility.Strings;

/**
 * Invoice execution result content.
 *
 * @see ContentType
 *
 * @author Ilia Gubarev
 */
@Public
@ImmutableByContract
public final class Content {

    /**
     * Creates a new result content.
     *
     * @param type type of content value.
     * @param value execution result value (optional).
     * @return result content.
     *
     * @see ContentType
     */
    public static Content of(final ContentType type,
                             final Object value) {
        Objects.requireNonNull(type, "Result content type cannot be null");
        return new Content(type, value);
    }

    /**
     * Gets a predefined content of undefined.
     *
     * @return result content.
     */
    public static Content undefined() {
        return UNDEFINED;
    }

    private static final Content UNDEFINED = new Content(ContentType.UNDEFINED, null);

    private final ContentType type;
    private final Object value;

    private Content(final ContentType type,
                    final Object value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Checks if the value is null.
     *
     * @return <code>true</code> if the value is null.
     */
    public boolean isNull() {
        return value == null;
    }

    /**
     * Gets the type of this content.
     *
     * @return content type.
     *
     * @see ContentType
     */
    public ContentType getType() {
        return type;
    }

    /**
     * Gets the value of this content (optional).
     *
     * @return content value.
     */
    public Object getValue() {
        return copyValueIfRequired();
    }

    /**
     * Gets the value of this content (optional).
     *
     * @return content value.
     */
    public Boolean getValueAsBoolean() {
        return (Boolean) copyValueIfRequired();
    }

    /**
     * Gets the value of this content (optional).
     *
     * @return content value.
     */
    public Double getValueAsDouble() {
        return (Double) copyValueIfRequired();
    }

    /**
     * Gets the value of this content (optional).
     *
     * @return content value.
     */
    @SuppressWarnings("unchecked")
    public List<Content> getValueAsList() {
        return (List<Content>) copyValueIfRequired();
    }

    /**
     * Gets the value of this content (optional).
     *
     * @return content value.
     */
    @SuppressWarnings("unchecked")
    public Map<String, Content> getValueAsMap() {
        return (Map<String, Content>) copyValueIfRequired();
    }

    /**
     * Gets the value of this content (optional).
     *
     * @return content value.
     */
    public String getValueAsString() {
        return (String) copyValueIfRequired();
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }

    private Object copyValueIfRequired() {
        if (value == null) {
            return null;
        }
        if (value instanceof List<?>) {
            return new ArrayList<>((List<?>) value);
        }
        if (value instanceof Map<?, ?>) {
            return new HashMap<>((Map<?, ?>) value);
        }
        return value;
    }
}
