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
package com.soulwarelabs.ecmabox.api.content.function;

import java.util.Objects;

import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Public;
import com.soulwarelabs.ecmabox.utility.Strings;

/**
 * ECMA function descriptor.
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
public final class FunctionDescriptor {

    private final String expression;

    /**
     * Creates a new function descriptor.
     *
     * @param expression ECMA function expression.
     */
    public FunctionDescriptor(final String expression) {
        Objects.requireNonNull(expression, "Function expression cannot be null");
        this.expression = expression;
    }

    /**
     * Gets ECMA expression for this function.
     *
     * @return ECMA expression.
     */
    public String getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
