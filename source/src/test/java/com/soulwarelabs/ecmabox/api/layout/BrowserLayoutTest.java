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

import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(BrowserLayout.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest(BrowserType.class)
public class BrowserLayoutTest {

    private final String html = "<html></html>";

    private BrowserType typeMock;
    private URL urlMock;

    @Before
    public void prepareMocks() {
        typeMock = PowerMockito.mock(BrowserType.class);
        urlMock = PowerMockito.mock(URL.class);
    }

    @Test
    public void createNewValidInstance() {
        final BrowserLayout layout = new BrowserLayout(
                html,
                typeMock,
                urlMock
        );
        Assert.assertEquals(html, layout.getHtml());
        Assert.assertEquals(typeMock, layout.getType());
        Assert.assertEquals(urlMock, layout.getUrl());
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullHtml() {
        new BrowserLayout(
                null,
                typeMock,
                urlMock
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullType() {
        new BrowserLayout(
                html,
                null,
                urlMock
        );
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullUrl() {
        new BrowserLayout(
                html,
                typeMock,
                null
        );
    }
}
