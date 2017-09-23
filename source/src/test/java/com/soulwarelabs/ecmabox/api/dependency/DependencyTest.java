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
package com.soulwarelabs.ecmabox.api.dependency;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.soulwarelabs.ecmabox.utility.Urls;
import com.soulwarelabs.ecmabox.test.convention.UnitTest;

@UnitTest(Dependency.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({Urls.class})
public class DependencyTest {

    private String cdnResourceValid = "http://resource.com";
    private String cdnResourceInvalid = "resource";

    @Before
    public void prepareMocks() throws Exception {
        PowerMockito.mockStatic(Urls.class);
        PowerMockito
                .when(Urls.class, "validUrl", cdnResourceValid)
                .thenReturn(true);
        PowerMockito
                .when(Urls.class, "validUrl", cdnResourceInvalid)
                .thenReturn(false);
    }

    @Test
    public void createNewValidInstanceForCdn() throws Exception {
        final Dependency dependency = Dependency.cdn(cdnResourceValid);
        Assert.assertEquals(DependencyOrigin.CDN, dependency.getOrigin());
        Assert.assertEquals(cdnResourceValid, dependency.getResource());
    }

    @Test
    public void createNewValidInstanceForNpm() throws Exception {
        final String npmResourceValid = "resource:id:2.3";
        final Dependency dependency = Dependency.npm(npmResourceValid);
        Assert.assertEquals(DependencyOrigin.NPM, dependency.getOrigin());
        Assert.assertEquals(npmResourceValid, dependency.getResource());
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceWithNullOrigin() throws Exception {
        Dependency.of(cdnResourceInvalid, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failToCreateNewInstanceForCdnWithInvalidUrl() throws Exception {
        Dependency.cdn(cdnResourceInvalid);
    }

    @Test(expected = NullPointerException.class)
    public void failToCreateNewInstanceForCdnWithNullUrl() throws Exception {
        Dependency.cdn(null);
    }
}
