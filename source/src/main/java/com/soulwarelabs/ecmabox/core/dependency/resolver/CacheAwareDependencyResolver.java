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
package com.soulwarelabs.ecmabox.core.dependency.resolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.soulwarelabs.ecmabox.api.dependency.Dependency;
import com.soulwarelabs.ecmabox.api.dependency.DependencyOrigin;
import com.soulwarelabs.ecmabox.api.dependency.DependencyResolutionException;
import com.soulwarelabs.ecmabox.api.dependency.DependencyResolver;
import com.soulwarelabs.ecmabox.api.invoice.Invoice;
import com.soulwarelabs.ecmabox.api.invoice.InvoiceBuilder;
import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.core.cache.Cache;
import com.soulwarelabs.ecmabox.core.dependency.broker.ResourceScriptBroker;

/**
 * Cache-based implementation of dependency resolver.
 *
 * @see Cache
 * @see DependencyResolver
 * @see InvoiceBuilder
 *
 * @author Ilia Gubarev
 */
@Private
@ImmutableByContract
public final class CacheAwareDependencyResolver implements DependencyResolver {

    private final Map<DependencyOrigin, ResourceScriptBroker> brokers;
    private final Cache<Invoice, Dependency> cache;
    private final InvoiceBuilder template;

    /**
     * Creates a new dependency resolver.
     *
     * @param brokers script resource brokers.
     * @param cache resolved dependence cache.
     * @param template an invoice builder to be used as a template.
     *
     * @see Cache
     * @see InvoiceBuilder
     * @see ResourceScriptBroker
     */
    public CacheAwareDependencyResolver(final Map<DependencyOrigin, ResourceScriptBroker> brokers,
                                        final Cache<Invoice, Dependency> cache,
                                        final InvoiceBuilder template) {
        Objects.requireNonNull(brokers, "Resource script brokers cannot be null");
        Objects.requireNonNull(cache, "Resource script cache cannot be null");
        Objects.requireNonNull(template, "Template invoice builder cannot be null");
        this.brokers = new HashMap<>(brokers);
        this.cache = cache;
        this.template = template.copy();
    }

    @Override
    public Invoice resolve(final Dependency dependency) {
        Objects.requireNonNull(dependency, "Dependency cannot be null");
        return cache
                .getOrResolve(dependency, this::resolveWithBroker)
                .orElseThrow(() -> new RuntimeException("Dependency is not resolved"));
    }

    private Invoice resolveWithBroker(final Dependency dependency) {
        return template
                .copy()
                .description(describe(dependency))
                .script(receiveScriptFromBroker(dependency))
                .build();
    }

    private String receiveScriptFromBroker(final Dependency dependency) {
        final ResourceScriptBroker broker = broker(dependency);
        final String result = broker.receive(dependency.getResource());
        if (result == null) {
            throw new DependencyResolutionException(dependency, "Resource script is not received");
        }
        return result;
    }

    private ResourceScriptBroker broker(final Dependency dependency) {
        final ResourceScriptBroker result = brokers.get(dependency.getOrigin());
        if (result == null) {
            throw new DependencyResolutionException(dependency, "Suitable broker is not found");
        }
        return result;
    }

    private String describe(final Dependency dependency) {
        return String.format(
                "Dependency invoice for %s %s",
                dependency.getOrigin(),
                dependency.getResource()
        );
    }
}
