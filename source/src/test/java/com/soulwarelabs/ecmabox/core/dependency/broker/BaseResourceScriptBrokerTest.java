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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(BaseResourceScriptBroker.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({BaseResourceScriptBroker.class})
public class BaseResourceScriptBrokerTest {

    @Mock
    private ResourceScriptBroker failoverMock;

    @Mock
    private String resourceMock;

    @Mock
    private String scriptMock;

    @Before
    public void prepareMocks() throws Exception {
        PowerMockito
                .when(failoverMock, "receive", resourceMock)
                .thenReturn(scriptMock);
    }

    @Test
    public void createNewValidInstanceWithFailover() {
        final BaseResourceScriptBroker broker = createNewInstanceWhichSucceeds(failoverMock);
        Assert.assertNotNull(broker);
    }

    @Test
    public void createNewValidInstanceWithoutFailover() {
        final BaseResourceScriptBroker broker = createNewInstanceWhichSucceeds(null);
        Assert.assertNotNull(broker);
    }

    @Test(expected = NullPointerException.class)
    public void failToReceiveNullResource() {
        final BaseResourceScriptBroker broker = createNewInstanceWhichSucceeds(failoverMock);
        broker.receive(null);
    }

    @Test(expected = RuntimeException.class)
    public void failToReceiveScript() throws Exception {
        final BaseResourceScriptBroker broker = createNewInstanceWhichFails(null);
        final String script = broker.receive(resourceMock);
        Assert.assertSame(scriptMock, script);
    }

    @Test
    public void receiveScript() throws Exception {
        final BaseResourceScriptBroker broker = createNewInstanceWhichSucceeds(null);
        final String script = broker.receive(resourceMock);
        Assert.assertSame(scriptMock, script);
    }

    @Test
    public void receiveScriptFromFailover() throws Exception {
        final BaseResourceScriptBroker broker = createNewInstanceWhichFails(failoverMock);
        final String script = broker.receive(resourceMock);
        Assert.assertSame(scriptMock, script);
    }

    private BaseResourceScriptBroker createNewInstanceWhichSucceeds(final ResourceScriptBroker failover) {
        return new BaseResourceScriptBroker(failover) {

            @Override
            protected String receiveSpecific(final String resource) {
                return scriptMock;
            }
        };
    }

    private BaseResourceScriptBroker createNewInstanceWhichFails(final ResourceScriptBroker failover) {
        return new BaseResourceScriptBroker(failover) {

            @Override
            protected String receiveSpecific(final String resource) {
                throw new RuntimeException();
            }
        };
    }
}
