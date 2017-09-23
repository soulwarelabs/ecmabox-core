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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.soulwarelabs.ecmabox.convention.Builder;
import com.soulwarelabs.ecmabox.convention.Nullable;
import com.soulwarelabs.ecmabox.convention.Private;

/**
 * Instance builder.
 * <br/>
 * Used to instantiate objects by special <link>Discoverable</link> types.
 *
 * @param <T> target object type.
 *
 * @see Discoverable
 *
 * @author Ilia Gubarev
 */
@Private
@Builder(Object.class)
public final class InstanceBuilder<T> {

    /**
     * Default instantiation method name.
     */
    public static final String DEFAULT_INSTANTIATION_METHOD = "instantiate";

    private List<Object> arguments = new ArrayList<>();
    private String instantiationMethod = DEFAULT_INSTANTIATION_METHOD;
    private Class<?> instantiationType;

    /**
     * Adds a new instantiation argument.
     *
     * @param argument instantiation argument.
     * @return this instance builder.
     */
    public InstanceBuilder<T> argument(final @Nullable Object argument) {
        this.arguments.add(argument);
        return this;
    }

    /**
     * Sets the name of instantiation method.
     *
     * @param instantiationMethod instantiation method name.
     * @return this instance builder.
     */
    public InstanceBuilder<T> instantiationMethod(final String instantiationMethod) {
        this.instantiationMethod = instantiationMethod;
        return this;
    }

    /**
     * Sets a special instantiation type (not to be confused with target type).
     *
     * @param instantiationType instantiation type.
     * @return this instance builder.
     *
     * @see Discoverable
     */
    public InstanceBuilder<T> instantiationType(final Class<?> instantiationType) {
        this.instantiationType = instantiationType;
        return this;
    }

    /**
     * Creates a new instance of the target type.
     *
     * @return new instance.
     */
    public T build() {
        Objects.requireNonNull(instantiationMethod, "Instantiation method name cannot be null");
        Objects.requireNonNull(instantiationType, "Instantiation type cannot be null");
        try {
            final Method method = instantiationType.getMethod(instantiationMethod, Object[].class);
            final Object instance = method.invoke(null, (Object) arguments.toArray());
            return Casts.cast(instance);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException("Target type cannot be instantiated", e);
        }
    }
}
