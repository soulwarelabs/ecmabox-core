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

import com.soulwarelabs.ecmabox.api.content.function.FunctionDescriptor;
import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Nullable;
import com.soulwarelabs.ecmabox.convention.Public;
import com.soulwarelabs.ecmabox.utility.Casts;
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
     * @param value execution result value (optional).
     * @return result content.
     */
    public static Content of(final @Nullable Object value) {
        final ContentType type = ContentType.of(value);
        return new Content(type, value);
    }

    /**
     * Gets a predefined content of null.
     *
     * @return result content.
     */
    public static Content nullObject() {
        return NULL;
    }

    /**
     * Gets a predefined content of undefined.
     *
     * @return result content.
     */
    public static Content undefined() {
        return UNDEFINED;
    }

    private static final Content NULL = new Content(ContentType.OBJECT, null);
    private static final Content UNDEFINED = new Content(ContentType.UNDEFINED, null);

    private final ContentType type;

    @Nullable
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
    @Nullable
    public Object getValue() {
        return copyAndCastValueIfRequired();
    }

    /**
     * Gets the value of this content (optional).
     *
     * @return content value.
     */
    @Nullable
    public Boolean getValueAsBoolean() {
        return copyAndCastValueIfRequired();
    }

    /**
     * Gets the value of this content (optional).
     *
     * @return content value.
     */
    @Nullable
    public Double getValueAsDouble() {
        return copyAndCastValueIfRequired();
    }

    /**
     * Gets the value of this content (optional).
     *
     * @return content value.
     *
     * @see FunctionDescriptor
     */
    @Nullable
    public FunctionDescriptor getValueAsFunctionDescriptor() {
        return copyAndCastValueIfRequired();
    }

    /**
     * Gets the value of this content (optional).
     *
     * @return content value.
     */
    @Nullable
    public List<Content> getValueAsList() {
        return copyAndCastValueIfRequired();
    }

    /**
     * Gets the value of this content (optional).
     *
     * @return content value.
     */
    @Nullable
    public Map<String, Content> getValueAsMap() {
        return copyAndCastValueIfRequired();
    }

    /**
     * Gets the value of this content (optional).
     *
     * @return content value.
     */
    @Nullable
    public String getValueAsString() {
        return copyAndCastValueIfRequired();
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }

    private <T> T copyAndCastValueIfRequired() {
        if (value == null) {
            return null;
        }
        if (type == ContentType.ARRAY) {
            return Casts.cast(new ArrayList<>((List<?>) value));
        }
        if (type == ContentType.OBJECT) {
            return Casts.cast(new HashMap<>((Map<?, ?>) value));
        }
        return Casts.cast(value);
    }
}
