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

import java.net.URL;

import org.apache.commons.io.IOUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;
import com.soulwarelabs.ecmabox.utility.Urls;

@UnitTest(CdnResourceScriptBroker.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({NetworkLayout.class, IOUtils.class, Urls.class})
public class CdnResourceScriptBrokerTest {

    @Mock
    private NetworkLayout networkLayoutMock;

    @Mock
    private String resourceMock;

    @Mock
    private String scriptMock;

    @Before
    public void prepareMocks() throws Exception {
        final URL urlMock = PowerMockito.mock(URL.class);
        PowerMockito
                .mockStatic(Urls.class);
        PowerMockito
                .when(Urls.class, "parse", resourceMock)
                .thenReturn(urlMock);
        PowerMockito
                .mockStatic(IOUtils.class);
        PowerMockito
                .when(IOUtils.class, "toString", urlMock, "UTF-8")
                .thenReturn(scriptMock);
    }

    @Test
    public void receiveScript() throws Exception {
        final CdnResourceScriptBroker broker = new CdnResourceScriptBroker(null, networkLayoutMock);
        final String script = broker.receive(resourceMock);
        Assert.assertSame(scriptMock, script);
        PowerMockito.verifyStatic(IOUtils.class);
    }
}
