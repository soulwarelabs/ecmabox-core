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
package com.soulwarelabs.ecmabox.api;

import java.util.Objects;

import com.soulwarelabs.ecmabox.api.layout.Layout;
import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Nullable;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Sandbox factory exception.
 *
 * @see RuntimeException
 *
 * @author Ilia Gubarev
 */
@Public
@ImmutableByContract
public class SandboxFactoryException extends RuntimeException {

    private final Layout layout;

    /**
     * Creates a new exception.
     *
     * @param layout sandbox layout which caused the exception.
     *
     * @see Layout
     */
    public SandboxFactoryException(final Layout layout) {
        requireNotNullLayout(layout);
        this.layout = layout;
    }

    /**
     * Creates a new exception.
     *
     * @param layout sandbox layout which caused the exception.
     * @param message exception detailed comment.
     *
     * @see Layout
     */
    public SandboxFactoryException(final Layout layout,
                                   final @Nullable String message) {
        super(message);
        requireNotNullLayout(layout);
        this.layout = layout;
    }

    /**
     * Creates a new exception.
     *
     * @param layout sandbox layout which caused the exception.
     * @param message exception detailed comment.
     * @param cause the root cause of this exception (optional).
     *
     * @see Layout
     */
    public SandboxFactoryException(final Layout layout,
                                   final @Nullable String message,
                                   final @Nullable Throwable cause) {
        super(message, cause);
        requireNotNullLayout(layout);
        this.layout = layout;
    }

    /**
     * Gets a sandbox layout which caused the exception.
     *
     * @return sandbox layout.
     *
     * @see Layout
     */
    public Layout getLayout() {
        return layout;
    }

    private void requireNotNullLayout(final Layout layout) {
        Objects.requireNonNull(layout, "Layout cannot be null");
    }
}
