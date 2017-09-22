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
package com.soulwarelabs.ecmabox.core.cache;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import com.soulwarelabs.ecmabox.convention.ConcurrentByContract;
import com.soulwarelabs.ecmabox.convention.Private;

/**
 * Basic implementation of generic cache API.
 *
 * @param <V> cache item value type.
 * @param <K> cache item key type.
 *
 * @see Cache
 * @see CacheLayout
 *
 * @author Ilia Gubarev
 */
@Private
@ConcurrentByContract
public abstract class BaseCache<V, K> implements Cache<V, K> {

    private final CacheLayout layout;

    /**
     * Creates a new cache.
     *
     * @param layout cache layout configuration.
     *
     * @see CacheLayout
     */
    protected BaseCache(final CacheLayout layout) {
        Objects.requireNonNull(layout, "Cache layout cannot be null");
        this.layout = layout;
    }

    @Override
    public Optional<V> getOrResolve(final K key, final Function<K, V> resolver) {
        Objects.requireNonNull(key, "Cache key cannot be null");
        Objects.requireNonNull(resolver, "Cache value resolver cannot be null");
        final V result = getOrResolveSpecific(key, resolver);
        return Optional.ofNullable(result);
    }

    /**
     * Gets the current cache layout configuration.
     *
     * @return current cache layout.
     *
     * @see CacheLayout
     */
    protected CacheLayout getLayout() {
        return layout;
    }

    /**
     * Gets or resolves cached value in implementation-specific way.
     *
     * @param key cache key.
     * @param resolver function to resolve a value from the key if needed.
     * @return cached value (optional).
     */
    protected abstract V getOrResolveSpecific(K key, Function<K, V> resolver);
}
