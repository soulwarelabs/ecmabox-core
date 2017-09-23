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
package com.soulwarelabs.ecmabox.utility;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import org.reflections.Reflections;

import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.convention.Static;

/**
 * Utility class to work with <link>Discoverable</link> types.
 *
 * @see Discoverable
 *
 * @author Ilia Gubarev
 */
@Private
@Static
public final class Discoverables {

    /**
     * Default name of a package to scan.
     */
    public static final String DEFAULT_PACKAGE_TO_SCAN = "com.soulwarelabs.ecmabox";

    /**
     * Finds a discoverable type which has a specified identifier tag if any.
     *
     * @param tag unique discoverable type tag.
     * @return found discoverable type (optional).
     *
     * @see Discoverable
     */
    public static Optional<Class<?>> discover(final String tag) {
        return discover(tag, DEFAULT_PACKAGE_TO_SCAN);
    }

    /**
     * Finds a discoverable type which has a specified identifier tag if any.
     *
     * @param tag unique discoverable type tag.
     * @param packageToScan name of a package to scan.
     * @return found discoverable type (optional).
     *
     * @see Discoverable
     */
    public static Optional<Class<?>> discover(final String tag, final String packageToScan) {
        Objects.requireNonNull(tag, "Discoverable type identifier tag cannot be null");
        Objects.requireNonNull(packageToScan, "Target package name cannot be null");
        final Reflections reflections = new Reflections(packageToScan);
        return reflections
                .getTypesAnnotatedWith(Discoverable.class)
                .stream()
                .filter(filterByTag(tag))
                .findFirst();
    }

    private static Predicate<Class<?>> filterByTag(final String tag) {
        return type -> type.getAnnotation(Discoverable.class).value().equals(tag);
    }

    private Discoverables() {

    }
}
