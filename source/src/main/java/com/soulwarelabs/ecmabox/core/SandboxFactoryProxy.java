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
import com.soulwarelabs.ecmabox.api.dependency.Dependency;
import com.soulwarelabs.ecmabox.api.dependency.DependencyOrigin;
import com.soulwarelabs.ecmabox.api.dependency.DependencyResolver;
import com.soulwarelabs.ecmabox.api.invoice.Invoice;
import com.soulwarelabs.ecmabox.api.invoice.InvoiceBuilder;
import com.soulwarelabs.ecmabox.api.layout.EnvironmentType;
import com.soulwarelabs.ecmabox.api.layout.Layout;
import com.soulwarelabs.ecmabox.convention.Factory;
import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.core.cache.Cache;
import com.soulwarelabs.ecmabox.core.cache.CacheLayout;
import com.soulwarelabs.ecmabox.core.cache.simple.PerpetualCache;
import com.soulwarelabs.ecmabox.core.dependency.broker.ResourceScriptBroker;
import com.soulwarelabs.ecmabox.core.dependency.broker.network.CdnResourceScriptBroker;
import com.soulwarelabs.ecmabox.core.dependency.broker.network.NetworkLayout;
import com.soulwarelabs.ecmabox.core.dependency.resolver.CacheAwareDependencyResolver;
import com.soulwarelabs.ecmabox.core.htmlunit.HtmlUnitSandboxFactory;

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

    private static SandboxFactoryProxy INSTANCE;

    /**
     * Gets an instance of sandbox factory proxy.
     *
     * @return sandbox factory proxy instance.
     */
    public static SandboxFactoryProxy getInstance() {
        if (INSTANCE == null) {
            INSTANCE = createInstance();
        }
        return INSTANCE;
    }

    private static SandboxFactoryProxy createInstance() {
        final Map<EnvironmentType, SandboxFactory> factories = createFactories();
        return new SandboxFactoryProxy(factories);
    }

    private static Map<EnvironmentType, SandboxFactory> createFactories() {
        final Map<EnvironmentType, SandboxFactory> result = new HashMap<>();
        final SandboxFactory htmlUnitFactory = createHtmlUnitFactory();
        result.put(EnvironmentType.BROWSER, htmlUnitFactory);
        return result;
    }

    private static SandboxFactory createHtmlUnitFactory() {
        final DependencyResolver defaultResolver = createDependencyResolver();
        return new HtmlUnitSandboxFactory(defaultResolver);
    }

    private static DependencyResolver createDependencyResolver() {
        final Map<DependencyOrigin, ResourceScriptBroker> brokers = createBrokers();
        final Cache<Invoice, Dependency> cache = createCache();
        final InvoiceBuilder template = createTemplate();
        return new CacheAwareDependencyResolver(brokers, cache, template);
    }

    private static Map<DependencyOrigin, ResourceScriptBroker> createBrokers() {
        final Map<DependencyOrigin, ResourceScriptBroker> result = new HashMap<>();
        final NetworkLayout networkLayout = new NetworkLayout();
        final ResourceScriptBroker cdnBroker = new CdnResourceScriptBroker(null, networkLayout);
        result.put(DependencyOrigin.CDN, cdnBroker);
        return result;
    }

    private static Cache<Invoice, Dependency> createCache() {
        final CacheLayout cacheLayout = new CacheLayout(true);
        return new PerpetualCache<>(cacheLayout);
    }

    private static InvoiceBuilder createTemplate() {
        return new InvoiceBuilder()
                .restricted(false)
                .timeoutEnabled(false);
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
