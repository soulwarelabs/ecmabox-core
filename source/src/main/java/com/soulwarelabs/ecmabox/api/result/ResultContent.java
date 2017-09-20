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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Invoice execution result content.
 *
 * @see ResultContentType
 *
 * @author Ilia Gubarev
 */
@Public
@ImmutableByContract
public final class ResultContent {

    /**
     * Creates a new result content.
     *
     * @param type type of result content value.
     * @param value execution result value, immutable by its contract (optional).
     * @return result content.
     *
     * @see ResultContentType
     */
    public static ResultContent of(final ResultContentType type,
                                   final Object value) {
        Objects.requireNonNull(type, "Result content type cannot be null");
        if (value == null && type != ResultContentType.OBJECT) {
            if (type == ResultContentType.UNDEFINED) {
                return UNDEFINED;
            } else {
                throw new NullPointerException("Result content value cannot be null");
            }
        }
        return new ResultContent(type, value);
    }

    /**
     * Gets a predefined result content of undefined.
     *
     * @return result content.
     */
    public static ResultContent undefined() {
        return UNDEFINED;
    }

    private static final ResultContent UNDEFINED = new ResultContent(ResultContentType.UNDEFINED, null);

    private final ResultContentType type;
    private final Object value;

    private ResultContent(final ResultContentType type,
                          final Object value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Gets the type of this result content.
     *
     * @return result content type.
     *
     * @see ResultContentType
     */
    public ResultContentType getType() {
        return type;
    }

    /**
     * Gets the value of this result content (optional).
     * <br/>
     * The value object is immutable by its contract.
     *
     * @return result content value.
     */
    public Object getValue() {
        return copyValueIfRequired();
    }

    /**
     * Gets the value of this result content (optional).
     * <br/>
     * The value object is immutable by its contract.
     *
     * @param valueClass target type for conversion.
     * @param <T> target value type.
     * @return result content value.
     */
    public <T> T getValueAs(Class<? extends T> valueClass) {
        return valueClass.cast(copyValueIfRequired());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    private Object copyValueIfRequired() {
        if (value instanceof List<?>) {
            return new ArrayList<>((List<?>) value);
        }
        return value;
    }
}
