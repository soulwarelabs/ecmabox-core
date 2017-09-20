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
package com.soulwarelabs.ecmabox.api.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(UrlHelper.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({URL.class, UrlHelper.class})
public class UrlHelperTest {

    private final String specificationInvalid = "somedomain/resource";
    private final String specificationValid = "https://somedomain.com/resource";

    private URL urlMock;

    @Before
    public void prepareMocks() throws Exception {
        urlMock = PowerMockito.mock(URL.class);
        PowerMockito
                .whenNew(URL.class)
                .withArguments(specificationValid)
                .thenReturn(urlMock);
        PowerMockito
                .whenNew(URL.class)
                .withArguments(specificationInvalid)
                .thenThrow(MalformedURLException.class);
    }

    @Test
    public void checkInvalidUrl() {
        Assert.assertFalse(UrlHelper.validUrl(specificationInvalid));
    }

    @Test
    public void checkValidUrl() {
        Assert.assertTrue(UrlHelper.validUrl(specificationValid));
    }

    @Test(expected = NullPointerException.class)
    public void failToParseNullUrl() {
        UrlHelper.parseSafely(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToParseInvalidUrl() {
        UrlHelper.parse(specificationInvalid);
    }

    @Test
    public void parseValidUrl() {
        final URL url = UrlHelper.parse(specificationValid);
        Assert.assertEquals(urlMock, url);
    }

    @Test
    public void parseInvalidUrlOptional() {
        final Optional<URL> url = UrlHelper.parseSafely(specificationInvalid);
        Assert.assertFalse(url.isPresent());
    }

    @Test
    public void parseValidUrlOptional() {
        final Optional<URL> url = UrlHelper.parseSafely(specificationValid);
        Assert.assertTrue(url.isPresent());
        Assert.assertEquals(urlMock, url.get());
    }
}
