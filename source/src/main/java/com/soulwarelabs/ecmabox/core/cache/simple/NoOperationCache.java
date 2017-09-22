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
package com.soulwarelabs.ecmabox.core.cache.simple;

import java.util.function.Function;

import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.core.cache.CacheLayout;
import com.soulwarelabs.ecmabox.core.cache.BaseCache;

/**
 * No-operation cache implementation.
 *
 * @param <V> cache item value type.
 * @param <K> cache item key type.
 *
 * @see BaseCache
 * @see CacheLayout
 *
 * @author Ilia Gubarev
 */
@Private
@Immutable
public final class NoOperationCache<V, K> extends BaseCache<V, K> {

    /**
     * Creates a new cache.
     *
     * @param layout cache layout configuration.
     *
     * @see CacheLayout
     */
    public NoOperationCache(final CacheLayout layout) {
        super(layout);
    }

    @Override
    protected V getOrResolveSpecific(final K key, final Function<K, V> resolver) {
        return resolver.apply(key);
    }
}
