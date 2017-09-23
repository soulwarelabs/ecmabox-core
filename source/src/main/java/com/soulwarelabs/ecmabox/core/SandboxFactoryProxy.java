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
package com.soulwarelabs.ecmabox.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.soulwarelabs.ecmabox.api.Sandbox;
import com.soulwarelabs.ecmabox.api.SandboxFactory;
import com.soulwarelabs.ecmabox.api.layout.EnvironmentType;
import com.soulwarelabs.ecmabox.api.layout.Layout;
import com.soulwarelabs.ecmabox.convention.Factory;
import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Private;

/**
 * Proxy-implementation of sandbox factory.
 *
 * @see SandboxFactory
 *
 * @author Ilia Gubarev
 */
@Private
@Immutable
@Factory(Sandbox.class)
public final class SandboxFactoryProxy implements SandboxFactory {

    private final static SandboxFactoryProxy INSTANCE;

    static {
        final Map<EnvironmentType, SandboxFactory> factories = new HashMap<>();
        INSTANCE = new SandboxFactoryProxy(factories);
    }

    /**
     * Gets an instance of sandbox factory proxy.
     *
     * @return sandbox factory proxy instance.
     */
    public static SandboxFactoryProxy getInstance() {
        return INSTANCE;
    }

    private final Map<EnvironmentType, SandboxFactory> factories;

    private SandboxFactoryProxy(final Map<EnvironmentType, SandboxFactory> factories) {
        this.factories = new HashMap<>(factories);
    }

    @Override
    public Sandbox create(final Layout layout) {
        Objects.requireNonNull(layout, "Sandbox layout cannot be null");
        final SandboxFactory factory = factoryByEnvironmentType(layout.getEnvironmentType());
        return factory.create(layout);
    }

    private SandboxFactory factoryByEnvironmentType(final EnvironmentType type) {
        final SandboxFactory result = factories.get(type);
        if (result == null) {
            final String message = String.format("Environment type %s is not yet supported", type);
            throw new UnsupportedOperationException(message);
        }
        return result;
    }
}
