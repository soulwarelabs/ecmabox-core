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
package com.soulwarelabs.ecmabox.core.dependency.broker.network;

import java.util.Objects;

import com.soulwarelabs.ecmabox.convention.ImmutableByContract;
import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.core.dependency.broker.BaseResourceScriptBroker;
import com.soulwarelabs.ecmabox.core.dependency.broker.ResourceScriptBroker;

/**
 * Basic implementation of network-aware broker.
 *
 * @see BaseResourceScriptBroker
 * @see NetworkLayout
 * @see ResourceScriptBroker
 *
 * @author Ilia Gubarev
 */
@Private
@ImmutableByContract
public abstract class NetworkResourceScriptBroker extends BaseResourceScriptBroker {

    private final NetworkLayout networkLayout;

    /**
     * Creates a new resource script broker.
     *
     * @param failover a failover broker (optional).
     * @param networkLayout current network layout.
     *
     * @see NetworkLayout
     */
    protected NetworkResourceScriptBroker(final ResourceScriptBroker failover,
                                          final NetworkLayout networkLayout) {
        super(failover);
        Objects.requireNonNull(networkLayout, "Network layout cannot be null");
        this.networkLayout = networkLayout;
    }

    /**
     * Gets the current network layout.
     *
     * @return network layout.
     *
     * @see NetworkLayout
     */
    protected NetworkLayout getNetworkLayout() {
        return networkLayout;
    }
}
