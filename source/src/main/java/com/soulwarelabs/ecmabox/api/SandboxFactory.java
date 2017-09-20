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

import com.soulwarelabs.ecmabox.api.layout.Layout;
import com.soulwarelabs.ecmabox.api.layout.LayoutBuilder;
import com.soulwarelabs.ecmabox.convention.Factory;
import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * ECMA sandbox factory API.
 *
 * @see Layout
 * @see Sandbox
 *
 * @author Ilia Gubarev
 */
@Public
@ImmutableByContract
@Factory(Sandbox.class)
public interface SandboxFactory {

    /**
     * Creates a new ECMA sandbox.
     *
     * @param layout sandbox layout configuration.
     * @return new ECMA sandbox.
     *
     * @see Layout
     * @see Sandbox
     */
    Sandbox create(Layout layout);

    /**
     * Creates a new sandbox layout builder.
     *
     * @return new layout builder.
     *
     * @see LayoutBuilder
     */
    default LayoutBuilder layoutBuilder() {
        return new LayoutBuilder();
    }
}
