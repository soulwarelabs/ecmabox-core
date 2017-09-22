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

import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.utility.Strings;

/**
 * Common cache layout configuration.
 *
 * @author Ilia Gubarev
 */
@Private
@Immutable
public final class CacheLayout {

    private final boolean enabled;

    /**
     * Creates a new cache layout.
     *
     * @param enabled <code>true</code> if caching is enabled.
     */
    public CacheLayout(final boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Checks if caching is enabled.
     *
     * @return <code>true</code> if caching is enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
