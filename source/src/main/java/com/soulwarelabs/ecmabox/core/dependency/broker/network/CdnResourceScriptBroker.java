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

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.soulwarelabs.ecmabox.convention.Immutable;
import com.soulwarelabs.ecmabox.convention.Private;
import com.soulwarelabs.ecmabox.core.dependency.broker.ResourceScriptBroker;
import com.soulwarelabs.ecmabox.utility.Urls;

/**
 * CDN-based script broker.
 *
 * @see NetworkLayout
 * @see NetworkResourceScriptBroker
 * @see ResourceScriptBroker
 *
 * @author Ilia Gubarev
 */
@Private
@Immutable
public final class CdnResourceScriptBroker extends NetworkResourceScriptBroker {

    /**
     * Creates a new resource script broker.
     *
     * @param failover a failover broker (optional).
     * @param networkLayout current network layout.
     *
     * @see NetworkLayout
     */
    public CdnResourceScriptBroker(final ResourceScriptBroker failover,
                                   final NetworkLayout networkLayout) {
        super(failover, networkLayout);
    }

    @Override
    protected String receiveSpecific(final String resource) {
        try {
            // TODO: use network properties
            return IOUtils.toString(Urls.parse(resource), "UTF-8");
        } catch (final IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
