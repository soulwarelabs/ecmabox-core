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
package com.soulwarelabs.ecmabox.api.dependecy;

import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Key;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Dependency origin type.
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
@Key
public enum DependencyOrigin {

    /**
     * Content delivery network.
     */
    CDN,

    /**
     * NPM dependency repository.
     */
    NPM
}