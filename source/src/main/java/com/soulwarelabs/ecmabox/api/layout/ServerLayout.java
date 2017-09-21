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
package com.soulwarelabs.ecmabox.api.layout;

import java.util.Objects;

import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Public;
import com.soulwarelabs.ecmabox.utility.Strings;

/**
 * Server layout configuration.
 *
 * @see ServerType
 *
 * @author Ilia Gubarev
 */
@Public
@Immutable
public final class ServerLayout {

    private final ServerType type;

    /**
     * Creates a new server layout configuration.
     *
     * @param type ECMA server type to be used in sandbox.
     *
     * @see ServerType
     */
    public ServerLayout(final ServerType type) {
        Objects.requireNonNull(type, "Server type cannot be null");
        this.type = type;
    }

    /**
     * Gets ECMA server type to be used in sandbox.
     *
     * @return server type.
     *
     * @see ServerType
     */
    public ServerType getType() {
        return type;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
