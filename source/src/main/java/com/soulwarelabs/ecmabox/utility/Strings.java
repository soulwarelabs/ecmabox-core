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
package com.soulwarelabs.ecmabox.utility;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.soulwarelabs.ecmabox.convention.Nullable;
import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.convention.Static;

/**
 * Helper class for string-related actions.
 *
 * @author Ilia Gubarev
 */
@Private
@Static
public final class Strings {

    /**
     * Creates a string representation of a specified object.
     *
     * @param object object to be presented as a string (optional).
     * @return string representation of the object.
     */
    public static String toString(final @Nullable Object object) {
        return ToStringBuilder.reflectionToString(object);
    }

    private Strings() {

    }
}
