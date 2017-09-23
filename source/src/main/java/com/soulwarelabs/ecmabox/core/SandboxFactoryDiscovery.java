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

import com.soulwarelabs.ecmabox.api.SandboxFactory;
import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.convention.Static;
import com.soulwarelabs.ecmabox.utility.Discoverable;

/**
 * Discoverable instantiation type for a default sandbox factory.
 *
 * @see Discoverable
 * @see SandboxFactory
 *
 * @author Ilia Gubarev
 */
@Private
@Static
@Discoverable("defaultSandboxFactory")
public final class SandboxFactoryDiscovery {

    /**
     * Creates a new instance of sandbox factory.
     *
     * @param arguments instantiation parameters (currently ignored).
     * @return new instance of sandbox factory.
     *
     * @see SandboxFactory
     */
    public static SandboxFactory instantiate(final Object... arguments) {
        throw new UnsupportedOperationException();
    }
}
