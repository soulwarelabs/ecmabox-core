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
package com.soulwarelabs.ecmabox.api.dependency;

import com.soulwarelabs.ecmabox.api.invoice.Invoice;
import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Public;

/**
 * Dependency resolver API.
 *
 * @see Dependency
 * @see Invoice
 *
 * @author Ilia Gubarev
 */
@Public
@ImmutableByContract
public interface DependencyResolver {

    /**
     * Resolves a specified dependency into an execution invoice.
     *
     * @param dependency dependency to be resolved.
     * @return new execution invoice.
     *
     * @see Dependency
     * @see Invoice
     */
    Invoice resolve(Dependency dependency);
}
