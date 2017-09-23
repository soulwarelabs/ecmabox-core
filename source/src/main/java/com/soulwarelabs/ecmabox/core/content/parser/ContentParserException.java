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
package com.soulwarelabs.ecmabox.core.content.parser;

import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Nullable;
import com.soulwarelabs.ecmabox.convention.Private;

/**
 * Exception which can be produced during parsing a content of script execution content.
 *
 * @author Ilia Gubarev
 */
@Private
@ImmutableByContract
public final class ContentParserException extends RuntimeException {

    private final Object raw;

    /**
     * Creates a new parser exception
     *
     * @param message exceptions detailed message
     */
    public ContentParserException(final String message) {
        this(message, null);
    }

    /**
     * Creates a new parser exception
     *
     * @param message exceptions detailed message
     * @param raw a raw object which caused the exception (optional).
     */
    public ContentParserException(final String message, final @Nullable Object raw) {
        super(message);
        this.raw = raw;
    }

    /**
     * Gets a raw object which caused the exception if any.
     *
     * @return raw object (optional).
     */
    @Nullable
    public Object getRaw() {
        return raw;
    }
}
