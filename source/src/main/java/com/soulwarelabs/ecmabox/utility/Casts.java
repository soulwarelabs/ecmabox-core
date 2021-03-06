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

import com.soulwarelabs.ecmabox.convention.Nullable;
import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.convention.Static;

/**
 * Helper class for type casting.
 *
 * @author Ilia Gubarev
 */
@Private
@Static
public final class Casts {

    /**
     * Tries to cast a specified object to a target type.
     *
     * @param object object to be casted.
     * @param <T> target casting type.
     * @return casted object.
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(final @Nullable Object object) {
        return (T) object;
    }

    private Casts() {

    }
}
