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

import java.util.Optional;
import java.util.function.Function;

import com.soulwarelabs.ecmabox.convention.ConcurrentByContract;
import com.soulwarelabs.ecmabox.convention.Private;

/**
 * Generic cache API.
 *
 * @author Ilia Gubarev
 */
@Private
@ConcurrentByContract
public interface Cache<V, K> {

    /**
     * Gets a cached value by specified key or tries to resolve it.
     *
     * @param key cache key.
     * @param resolver function to resolve a value from the key if needed.
     * @return cached value.
     */
    Optional<V> getOrResolve(K key, Function<K, V> resolver);
}
