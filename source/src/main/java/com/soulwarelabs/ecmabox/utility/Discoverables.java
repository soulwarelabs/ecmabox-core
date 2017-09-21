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

import org.reflections.Reflections;

import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.convention.Static;

/**
 * Utility helper class to work with <link>Discoverable</link> types.
 *
 * @see Discoverable
 *
 * @author Ilia Gubarev
 */
@Private
@Static
public final class Discoverables {

    private static final Reflections REFLECTION = new Reflections("com.soulwarelabs.ecmabox");

    /**
     * Finds a discoverable type which has a specified identifier if any.
     *
     * @param parent super type for the target type.
     * @param tag unique target type tag.
     * @param <T> super type.
     * @return found discoverable type (optional).
     *
     * @see Discoverable
     */
    public static <T extends Class<?>> Optional<T> discover(final T parent, final String tag) {
        Objects.requireNonNull(parent, "Parent type cannot be null");
        Objects.requireNonNull(tag, "Type tag cannot be null");
        return REFLECTION
                .getTypesAnnotatedWith(Discoverable.class)
                .stream()
                .filter(t -> t.getAnnotation(Discoverable.class).tag().equals(tag))
                .filter(parent::isAssignableFrom)
                .map(t -> (T) t)
                .findFirst();
    }

    /**
     * Instantiates a specified type.
     *
     * @param type a type to be instantiated.
     * @param <T> target type.
     * @return new instance of the target type.
     */
    public static <T> T instantiate(final Class<? extends T> type) {
        Objects.requireNonNull(type, "Type cannot be null");
        try {
            return type.newInstance();
        } catch (IllegalAccessException | InstantiationException exception) {
            throw new RuntimeException("Type cannot be instantiated", exception);
        }
    }

    private Discoverables() {

    }
}
