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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.soulwarelabs.ecmabox.api.content.function.FunctionDescriptor;
import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Equivalent;
import com.soulwarelabs.ecmabox.convention.Nullable;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Type of invoice execution content.
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
@Equivalent
public enum ContentType {

    /**
     * Array object. Represented by {@link java.util.List}.
     */
    ARRAY(List.class),

    /**
     * Boolean object. Represented by {@link Boolean}.
     */
    BOOLEAN(Boolean.class),

    /**
     * ECMA function object. Represented by {@link FunctionDescriptor}.
     */
    FUNCTION(FunctionDescriptor.class),

    /**
     * Numeric object. Represented by {@link Double}.
     */
    NUMBER(Number.class),

    /**
     * Generic composite object, including <code>null</code>. Represented by {@link java.util.Map}.
     */
    OBJECT(Map.class),

    /**
     * Character sequence. Represented by {@link String}.
     */
    STRING(String.class),

    /**
     * No return object available.
     */
    UNDEFINED(null);

    private static final Collection<ContentType> TYPES = Arrays.asList(values());

    /**
     * Gets the type of a specified content value.
     *
     * @param value content value.
     * @return type of the value.
     */
    public static ContentType of(final @Nullable Object value) {
        if (value == null) {
            return OBJECT;
        }
        final Class<?> valueClass = value.getClass();
        for (final ContentType type : TYPES) {
            if (type.represents(valueClass)) {
                return type;
            }
        }
        throw new ContentParserException(value, "Unknown content value type");
    }

    @Nullable
    private final Class<?> javaType;

    ContentType(final @Nullable Class<?> javaType) {
        this.javaType = javaType;
    }

    /**
     * Gets a Java class/interface which represents this content type if any.
     *
     * @return Java type (optional).
     */
    @Nullable
    public Class<?> getJavaType() {
        return javaType;
    }

    /**
     * Checks if any Java type is defined for this type.
     *
     * @return <code>true</code> if Java type is defined.
     */
    public boolean isJavaTypeDefined() {
        return javaType != null;
    }

    /**
     * Checks if this type represents the specified Java type.
     *
     * @param type Java type to be checked.
     * @return <code>true</code> if this type represents the specified Java type.
     */
    public boolean represents(final Class<?> type) {
        Objects.requireNonNull(type, "Target Java-type cannot be null");
        return javaType != null && javaType.isAssignableFrom(type);
    }
}
