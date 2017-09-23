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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.convention.Static;

/**
 * Helper class for URL-related actions.
 *
 * @see URL
 *
 * @author Ilia Gubarev
 */
@Private
@Static
public final class Urls {

    /**
     * Creates a new URL from its specification.
     *
     * @param specification URL text specification.
     * @return new URL.
     *
     * @see URL
     */
    public static URL parse(final String specification) {
        return parseSafely(specification)
                .orElseThrow(() -> new IllegalArgumentException("URL specification is malformed"));
    }

    /**
     * Creates a new URL from its specification if possible.
     *
     * @param specification URL text specification.
     * @return new optional URL (optional).
     *
     * @see URL
     */
    public static Optional<URL> parseSafely(final String specification) {
        Objects.requireNonNull(specification, "URL specification cannot be null");
        try {
            final URL url = new URL(specification);
            return Optional.of(url);
        } catch (MalformedURLException exception) {
            return Optional.empty();
        }
    }

    /**
     * Checks if specified string is a valid URL specification.
     *
     * @param specification text URL specification.
     * @return <code>true</code> if specification is valid.
     */
    public static boolean validUrl(final String specification) {
        return parseSafely(specification).isPresent();
    }

    private Urls() {

    }
}
