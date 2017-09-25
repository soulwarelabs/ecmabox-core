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
package com.soulwarelabs.ecmabox.api.dependency;

import java.util.Objects;

import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Dependency resolution exception.
 *
 * @see RuntimeException
 *
 * @author Ilia Gubarev
 */
@Public
@ImmutableByContract
public class DependencyResolutionException extends RuntimeException {

    private final Dependency dependency;

    /**
     * Creates a new exception.
     *
     * @param message exception detailed comment.
     * @param dependency dependency which caused the exception.
     *
     * @see Dependency
     */
    public DependencyResolutionException(final String message,
                                         final Dependency dependency) {
        super(message);
        requireNotNullDependency(dependency);
        this.dependency = dependency;
    }

    /**
     * Creates a new exception.
     *
     * @param message exception detailed comment.
     * @param dependency dependency which caused the exception.
     * @param cause the root cause of this exception (optional).
     *
     * @see Dependency
     */
    public DependencyResolutionException(final String message,
                                         final Dependency dependency,
                                         final Throwable cause) {
        super(message, cause);
        requireNotNullDependency(dependency);
        this.dependency = dependency;
    }

    /**
     * Gets a dependency which caused the exception.
     *
     * @return dependency.
     *
     * @see Dependency
     */
    public Dependency getDependency() {
        return dependency;
    }

    private void requireNotNullDependency(final Dependency dependency) {
        Objects.requireNonNull(dependency, "Dependency cannot be null");
    }
}
