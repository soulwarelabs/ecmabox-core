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
package com.soulwarelabs.ecmabox.core.dependency.broker;

import java.util.Objects;

import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Private;

/**
 * Basic implementation of a resource script broker.
 *
 * @see ResourceScriptBroker
 *
 * @author Ilia Gubarev
 */
@Private
@ImmutableByContract
public abstract class BaseResourceScriptBroker implements ResourceScriptBroker {

    private final ResourceScriptBroker failover;

    /**
     * Creates a new resource script broker.
     *
     * @param failover a failover broker (optional).
     */
    protected BaseResourceScriptBroker(final ResourceScriptBroker failover) {
        this.failover = failover;
    }

    @Override
    public String receive(final String resource) {
        Objects.requireNonNull(resource, "Script resource cannot be null");
        try {
            return receiveSpecific(resource);
        } catch (RuntimeException exception) {
            if (failover == null) {
                throw exception;
            }
            return failover.receive(resource);
        }
    }

    /**
     * Receives a script in an implementation-specific way.
     *
     * @param resource script resource.
     * @return received ECMA script.
     */
    protected abstract String receiveSpecific(final String resource);
}
