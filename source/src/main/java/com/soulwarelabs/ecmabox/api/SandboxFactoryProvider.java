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

import com.soulwarelabs.ecmabox.convention.Public;
import com.soulwarelabs.ecmabox.convention.Static;

/**
 * Static provider of sandbox implementations.
 *
 * @see SandboxConfiguration
 * @see SandboxFactory
 *
 * @author Ilia Gubarev
 */
@Public
@Static
public final class SandboxFactoryProvider {

    // TODO: add a method for creating a new sandbox configuration

    /**
     * Creates a new sandbox factory with a default configuration.
     *
     * @return new sandbox factory.
     *
     * @see SandboxFactory
     */
    public static SandboxFactory factory() {
        // TODO: provide a factory implementation with default configuration
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new sandbox factory with a specified configuration.
     *
     * @param configuration custom sandbox configuration.
     * @return new sandbox factory.
     *
     * @see SandboxConfiguration
     * @see SandboxFactory
     */
    public static SandboxFactory factory(final SandboxConfiguration configuration) {
        Objects.requireNonNull(configuration, "Sandbox configuration cannot be null");
        // TODO: provide a factory implementation with custom configuration
        throw new UnsupportedOperationException();
    }

    private SandboxFactoryProvider() {

    }
}
