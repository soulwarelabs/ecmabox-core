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

import com.soulwarelabs.ecmabox.convention.Public;
import com.soulwarelabs.ecmabox.convention.Static;
import com.soulwarelabs.ecmabox.utility.Discoverables;
import com.soulwarelabs.ecmabox.utility.InstanceBuilder;

/**
 * Sandbox factory provider.
 * <br/>
 * Automatically discovers a default sandbox factory on the classpath.
 *
 * @see SandboxFactory
 *
 * @author Ilia Gubarev
 */
@Public
@Static
public final class SandboxFactoryProvider {

    /**
     * Discovers a default sandbox factory available on the classpath.
     *
     * @return default sandbox factory.
     *
     * @see SandboxFactory
     */
    public static SandboxFactory factory() {
        return new InstanceBuilder<SandboxFactory>()
                .instantiationType(discover())
                .build();
    }

    private static Class<?> discover() {
        return Discoverables
                .discover("defaultSandboxFactory")
                .orElseThrow(() -> new RuntimeException("No instantiation type is discovered"));
    }

    private SandboxFactoryProvider() {

    }
}
