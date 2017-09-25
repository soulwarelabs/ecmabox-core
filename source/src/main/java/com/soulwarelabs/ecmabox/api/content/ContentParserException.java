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

import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Nullable;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Content parser exception.
 *
 * @see RuntimeException
 *
 * @author Ilia Gubarev
 */
@Public
@ImmutableByContract
public class ContentParserException extends RuntimeException {

    @Nullable
    private final Object value;

    /**
     * Creates a new exception.
     *
     * @param value value object which caused the exception (optional).
     */
    public ContentParserException(final @Nullable Object value) {
        this.value = value;
    }

    /**
     * Creates a new exception.
     *
     * @param value value object which caused the exception (optional).
     * @param message exception detailed comment (optional).
     */
    public ContentParserException(final @Nullable Object value,
                                  final @Nullable String message) {
        super(message);
        this.value = value;
    }

    /**
     * Creates a new exception.
     *
     * @param value value object which caused the exception (optional).
     * @param message exception detailed comment (optional).
     * @param cause the root cause of this exception (optional).
     */
    public ContentParserException(final @Nullable Object value,
                                  final @Nullable String message,
                                  final @Nullable Throwable cause) {
        super(message, cause);
        this.value = value;
    }

    /**
     * Gets a value which caused the exception.
     *
     * @return value object (optional).
     */
    @Nullable
    public Object getValue() {
        return value;
    }
}
